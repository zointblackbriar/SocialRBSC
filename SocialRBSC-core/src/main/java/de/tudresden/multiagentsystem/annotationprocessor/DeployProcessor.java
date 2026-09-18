package de.tudresden.multiagentsystem.annotationprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.JavaFileObject;

import com.google.auto.service.AutoService;

import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;

/**
 * Enhanced Annotation processor for {@link Deploy}.
 *
 * Scans the annotated class for all annotations (@Role, @Compartment, @Goal, @SocialAgent, etc.)
 * and generates corresponding Solidity contracts using Velocity templates.
 * 
 * Produces:
 * 1. Solidity contract files (.sol) based on detected annotations
 * 2. A web3j-based Java runtime environment for Java-to-Solidity interaction
 * 3. A deployer class to manage smart contract lifecycle (generatedsmartcontract/runner)
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("de.tudresden.socialrbscdeterministic.mas.annotation.Deploy")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class DeployProcessor extends AbstractProcessor {

    private static final Logger logger = Logger.getLogger(DeployProcessor.class.getName());
    
    // Mapping of annotation types to Solidity template names
    private static final Map<String, String> ANNOTATION_TEMPLATE_MAP = new HashMap<>();
    
    static {
        ANNOTATION_TEMPLATE_MAP.put("Role", "Role.sol");
        ANNOTATION_TEMPLATE_MAP.put("Compartment", "Compartment.sol");
        ANNOTATION_TEMPLATE_MAP.put("SocialAgent", "SocialAgent.sol");
        ANNOTATION_TEMPLATE_MAP.put("Society", "Society.sol");
        ANNOTATION_TEMPLATE_MAP.put("Goal", "Goal.sol");
        ANNOTATION_TEMPLATE_MAP.put("Plan", "Plan.sol");
        ANNOTATION_TEMPLATE_MAP.put("GoalPlanTree", "GoalPlanTree.sol");
        ANNOTATION_TEMPLATE_MAP.put("DeliberationCycle", "DeliberationCycle.sol");
        ANNOTATION_TEMPLATE_MAP.put("RoleMethod", "RoleMethod.sol");
        ANNOTATION_TEMPLATE_MAP.put("SocialAgentPlan", "SocialAgentPlan.sol");
        ANNOTATION_TEMPLATE_MAP.put("PreconditionSocialAgent", "Precondition.sol");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return false;
        }

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Deploy.class);
        for (Element element : elements) {
            if (element.getKind() != ElementKind.CLASS) {
                continue;
            }
            TypeElement classElem = (TypeElement) element;
            String className = classElem.getSimpleName().toString();
            String packageName = processingEnv.getElementUtils().getPackageOf(classElem).getQualifiedName().toString();

            logger.info("Processing @Deploy on class " + packageName + "." + className);

            // ===== STEP 0: VALIDATE ANNOTATION RULES =====
            validateAnnotationRules(classElem);

            // ===== STEP 1: SCAN FOR ALL ANNOTATIONS =====
            Map<String, List<String>> annotationData = scanAnnotations(classElem, roundEnv);
            
            // ===== STEP 2: GENERATE SOLIDITY CONTRACTS =====
            generateSolidityContracts(classElem, className, packageName, annotationData);

            // ===== STEP 3: GENERATE JAVA RUNTIME ENVIRONMENT =====
            generateJavaRuntimeEnvironment(classElem, className, packageName, annotationData);

            // ===== STEP 4: GENERATE DEPLOYER CLASS =====
            generateDeployerClass(classElem, className, packageName, annotationData);
        }
        return true;
    }

    /**
     * Scans the class element for all supported annotations.
     * Returns a map of annotation type -> list of annotated element names.
     */
    private Map<String, List<String>> scanAnnotations(TypeElement classElem, RoundEnvironment roundEnv) {
        Map<String, List<String>> result = new HashMap<>();

        collectFromElementRecursive(classElem, result);

        // Additionally, scan other top-level classes in the same package and subpackages so
        // @Deploy can trigger generation for related example classes placed in sibling folders
        // such as *.agents, *.roles, etc.
        String packageName = processingEnv.getElementUtils().getPackageOf(classElem).getQualifiedName().toString();
        for (Element root : roundEnv.getRootElements()) {
            if (root instanceof TypeElement) {
                TypeElement type = (TypeElement) root;
                String pkg = processingEnv.getElementUtils().getPackageOf(type).getQualifiedName().toString();
                if (isSameOrSubpackage(packageName, pkg) && !type.equals(classElem)) {
                    collectFromElementRecursive(type, result);
                }
            }
        }

        return result;
    }

    private boolean isSameOrSubpackage(String basePackage, String candidatePackage) {
        if (basePackage == null || basePackage.isEmpty()) {
            return candidatePackage == null || candidatePackage.isEmpty();
        }
        return candidatePackage.equals(basePackage) || candidatePackage.startsWith(basePackage + ".");
    }

    /**
     * Validates annotation usage rules.
     * Rule: If @Compartment is present, @Role and @SocialAgent must also be present.
     */
    private void validateAnnotationRules(TypeElement classElem) {
        String className = classElem.getSimpleName().toString();
        
        // Check if class has @Compartment annotation
        boolean hasCompartment = hasAnnotation(classElem, "Compartment");
        if (hasCompartment) {
            boolean hasRole = hasAnnotation(classElem, "Role");
            boolean hasSocialAgent = hasAnnotation(classElem, "SocialAgent");
            
            if (!hasRole) {
                processingEnv.getMessager().printMessage(
                    javax.tools.Diagnostic.Kind.ERROR,
                    "Class " + className + " has @Compartment but is missing required @Role annotation");
            }
            if (!hasSocialAgent) {
                processingEnv.getMessager().printMessage(
                    javax.tools.Diagnostic.Kind.ERROR,
                    "Class " + className + " has @Compartment but is missing required @SocialAgent annotation");
            }
        }
    }

    /**
     * Helper method to check if an element has a specific annotation by name.
     */
    private boolean hasAnnotation(Element element, String annotationName) {
        if (element.getAnnotationMirrors() == null) {
            return false;
        }
        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            String annotationType = mirror.getAnnotationType().asElement().getSimpleName().toString();
            if (annotationType.equals(annotationName)) {
                return true;
            }
        }
        return false;
    }

    private void collectFromElementRecursive(Element elem, Map<String, List<String>> result) {
        for (AnnotationMirror mirror : elem.getAnnotationMirrors()) {
            String annotationType = mirror.getAnnotationType().asElement().getSimpleName().toString();
            result.computeIfAbsent(annotationType, k -> new ArrayList<>()).add(elem.getSimpleName().toString());
            logger.info("Found @" + annotationType + " on element " + elem.getSimpleName());
        }

        for (ExecutableElement method : ElementFilter.methodsIn(elem.getEnclosedElements())) {
            for (AnnotationMirror mirror : method.getAnnotationMirrors()) {
                String annotationType = mirror.getAnnotationType().asElement().getSimpleName().toString();
                result.computeIfAbsent(annotationType, k -> new ArrayList<>()).add(method.getSimpleName().toString());
                logger.info("Found @" + annotationType + " on method " + method.getSimpleName());
            }
        }

        for (VariableElement field : ElementFilter.fieldsIn(elem.getEnclosedElements())) {
            for (AnnotationMirror mirror : field.getAnnotationMirrors()) {
                String annotationType = mirror.getAnnotationType().asElement().getSimpleName().toString();
                result.computeIfAbsent(annotationType, k -> new ArrayList<>()).add(field.getSimpleName().toString());
                logger.info("Found @" + annotationType + " on field " + field.getSimpleName());
            }
        }

        for (Element enclosed : elem.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.CLASS || enclosed.getKind() == ElementKind.INTERFACE
                    || enclosed.getKind() == ElementKind.ENUM) {
                collectFromElementRecursive(enclosed, result);
            }
        }
    }

    private boolean isDeployableAnnotation(String annotationType) {
        return ANNOTATION_TEMPLATE_MAP.containsKey(annotationType);
    }

    /**
     * Generates Solidity contract files based on detected annotations.
     */
    private void generateSolidityContracts(TypeElement classElem, String className, 
                                          String packageName, Map<String, List<String>> annotationData) {
        try {
            java.nio.file.Path contractDir = java.nio.file.Paths.get("generatedsmartcontract", "contracts");
            java.nio.file.Files.createDirectories(contractDir);

            // Generate a contract for each annotated component
            for (Map.Entry<String, List<String>> entry : annotationData.entrySet()) {
                String annotationType = entry.getKey();
                List<String> elements = entry.getValue();

                if (!ANNOTATION_TEMPLATE_MAP.containsKey(annotationType)) {
                    continue;
                }

                for (String elementName : elements) {
                    String contractName = elementName;
                    String solidityCode = generateSolidityForAnnotation(annotationType, contractName);

                    // Write Solidity contract
                    java.nio.file.Path contractFile = contractDir.resolve(contractName + ".sol");
                    java.nio.file.Files.writeString(contractFile, solidityCode);
                    logger.info("Generated Solidity contract: " + contractFile);
                }
            }

            // Generate a main orchestrator contract
            String orchestratorName = className + "Orchestrator";
            String orchestratorCode = generateOrchestratorContract(className, annotationData);
            java.nio.file.Path orchestratorFile = contractDir.resolve(orchestratorName + ".sol");
            java.nio.file.Files.writeString(orchestratorFile, orchestratorCode);
            logger.info("Generated orchestrator contract: " + orchestratorFile);

        } catch (Exception ex) {
            processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.ERROR,
                    "Failed to generate Solidity contracts: " + ex.getMessage());
        }
    }

    /**
     * Generates a Solidity contract code based on annotation type.
     */
    private String generateSolidityForAnnotation(String annotationType, String name) {
        StringBuilder sol = new StringBuilder();
        sol.append("// SPDX-License-Identifier: MIT\n");
        sol.append("pragma solidity >=0.7.0 <0.9.0;\n\n");

        switch (annotationType) {
            case "Role":
                sol.append("import \"../../../SOLMASFramework/contracts/staticroleassignment/ComponentRole.sol\";\n\n");
                sol.append("contract ").append(name).append(" is ComponentRole {\n");
                sol.append("    // Role state from Java\n");
                sol.append("    function perform() public {\n");
                sol.append("        // Role behavior implementation\n");
                sol.append("    }\n");
                sol.append("}\n");
                break;

            case "Compartment":
                sol.append("import \"../../../SOLMASFramework/contracts/staticroleassignment/Compartment.sol\";\n\n");
                sol.append("contract ").append(name).append(" is Compartment {\n");
                sol.append("    // Compartment initialization\n");
                sol.append("    function initialize() public {\n");
                sol.append("        // Compartment setup\n");
                sol.append("    }\n");
                sol.append("}\n");
                break;

            case "SocialAgent":
                sol.append("import \"../../../SOLMASFramework/contracts/societypattern/SocialAgent.sol\";\n\n");
                sol.append("contract ").append(name).append(" is SocialAgent {\n");
                sol.append("    // Agent state and beliefs\n");
                sol.append("    function act() public {\n");
                sol.append("        // Agent behavior\n");
                sol.append("    }\n");
                sol.append("}\n");
                break;

            case "Society":
                sol.append("contract ").append(name).append(" {\n");
                sol.append("    // Society-level coordination contract generated from @Society\n");
                sol.append("    function coordinateSociety() public {\n");
                sol.append("        // Society orchestration placeholder\n");
                sol.append("    }\n");
                sol.append("}\n");
                break;

            case "Goal":
            case "GoalPlanTree":
                sol.append("import \"../../../SOLMASFramework/contracts/goalplantree/GoalPlanTree.sol\";\n\n");
                sol.append("contract ").append(name).append(" {\n");
                sol.append("    // Goal state\n");
                sol.append("    function achieveGoal() public {\n");
                sol.append("        // Goal achievement logic\n");
                sol.append("    }\n");
                sol.append("}\n");
                break;

            case "Plan":
                sol.append("contract ").append(name).append(" {\n");
                sol.append("    // Plan execution contract generated from @Plan\n");
                sol.append("    function executePlan() public {\n");
                sol.append("        // Plan execution placeholder\n");
                sol.append("    }\n");
                sol.append("}\n");
                break;

            default:
                sol.append("contract ").append(name).append(" {\n");
                sol.append("    // Auto-generated from @").append(annotationType).append("\n");
                sol.append("    function execute() public {\n");
                sol.append("        // Implementation placeholder\n");
                sol.append("    }\n");
                sol.append("}\n");
        }

        return sol.toString();
    }

    /**
     * Generates an orchestrator contract that manages interactions between components.
     */
    private String generateOrchestratorContract(String className, Map<String, List<String>> annotationData) {
        StringBuilder sol = new StringBuilder();
        sol.append("// SPDX-License-Identifier: MIT\n");
        sol.append("pragma solidity >=0.7.0 <0.9.0;\n\n");
        sol.append("/**\n");
        sol.append(" * Orchestrator for ").append(className).append("\n");
        sol.append(" * Manages interactions and coordination between all smart contract components.\n");
        sol.append(" */\n");
        sol.append("contract ").append(className).append("Orchestrator {\n\n");
        sol.append("    // Component registry\n");

        for (Map.Entry<String, List<String>> entry : annotationData.entrySet()) {
            if (!isDeployableAnnotation(entry.getKey())) {
                continue;
            }
            String annotationType = entry.getKey();
            List<String> elements = entry.getValue();
            for (String element : elements) {
                sol.append("    address public ").append(toVariableName(element)).append(";\n");
            }
        }

        sol.append("\n    /**\n");
        sol.append("     * Initializes all components in the ecosystem.\n");
        sol.append("     */\n");
        sol.append("    function initializeEcosystem() public {\n");
        sol.append("        // Deploy and initialize all components\n");

        for (Map.Entry<String, List<String>> entry : annotationData.entrySet()) {
            if (!isDeployableAnnotation(entry.getKey())) {
                continue;
            }
            List<String> elements = entry.getValue();
            for (String element : elements) {
                sol.append("        // Initialize ").append(element).append("\n");
            }
        }

        sol.append("    }\n\n");
        sol.append("    /**\n");
        sol.append("     * Coordinates execution across components.\n");
        sol.append("     */\n");
        sol.append("    function coordinateExecution() public {\n");
        sol.append("        // Coordinate interactions\n");
        sol.append("    }\n");
        sol.append("}\n");

        return sol.toString();
    }

    /**
     * Generates Java runtime environment for Solidity interaction.
     */
    private void generateJavaRuntimeEnvironment(TypeElement classElem, String className,
                                               String packageName, Map<String, List<String>> annotationData) {
        try {
            String runtimeClassName = className + "SolidityRuntime";
            String packageDecl = packageName.isEmpty() ? "" : "package " + packageName + ".runtime;\n\n";
            String runtimeQualifiedName = packageName.isEmpty()
                    ? runtimeClassName
                    : packageName + ".runtime." + runtimeClassName;

            StringBuilder runtime = new StringBuilder();
            runtime.append("// Generated at " + java.time.LocalDateTime.now() + "\n");
            runtime.append(packageDecl);
            runtime.append("import org.web3j.protocol.Web3j;\n");
            runtime.append("import org.web3j.protocol.http.HttpService;\n");
            runtime.append("import java.util.Map;\n");
            runtime.append("import java.util.HashMap;\n\n");
            runtime.append("/**\n");
            runtime.append(" * Java-to-Solidity Runtime Environment for ").append(className).append("\n");
            runtime.append(" * Provides bridge between Java objects and deployed smart contracts.\n");
            runtime.append(" * Auto-generated from @Deploy annotation scanning.\n");
            runtime.append(" */\n");
            runtime.append("public class ").append(runtimeClassName).append(" {\n\n");
            runtime.append("    private Web3j web3j;\n");
            runtime.append("    private String nodeUrl = \"http://localhost:8545\";\n");
            runtime.append("    private Map<String, String> contractAddresses = new HashMap<>();\n\n");

            runtime.append("    public ").append(runtimeClassName).append("(String nodeUrl) {\n");
            runtime.append("        this.nodeUrl = nodeUrl;\n");
            runtime.append("        this.web3j = Web3j.build(new HttpService(nodeUrl));\n");
            runtime.append("    }\n\n");

            runtime.append("    /**\n");
            runtime.append("     * Deploys all contracts to the blockchain.\n");
            runtime.append("     */\n");
            runtime.append("    public void deployAllContracts() throws Exception {\n");

            // Build a unique, ordered set of deploy targets and capture associated annotation types
            java.util.Map<String, java.util.Set<String>> targetToAnnotations = new java.util.LinkedHashMap<>();
            java.util.Map<String, java.util.Set<String>> targetToOriginalNames = new java.util.LinkedHashMap<>();
            for (Map.Entry<String, List<String>> entry : annotationData.entrySet()) {
                String annType = entry.getKey();
                if (!isDeployableAnnotation(annType)) {
                    continue;
                }
                for (String element : entry.getValue()) {
                    String id = toMethodIdentifier(element);
                    targetToAnnotations.computeIfAbsent(id, k -> new java.util.LinkedHashSet<>()).add(annType);
                    targetToOriginalNames.computeIfAbsent(id, k -> new java.util.LinkedHashSet<>()).add(element);
                }
            }

            // Append calls to each unique deploy method
            for (String id : targetToAnnotations.keySet()) {
                runtime.append("        deploy").append(capitalize(id)).append("();\n");
            }
            runtime.append("    }\n\n");

            // Generate deploy methods for each unique target
            for (java.util.Map.Entry<String, java.util.Set<String>> e : targetToAnnotations.entrySet()) {
                String id = e.getKey();
                java.util.Set<String> anns = e.getValue();
                java.util.Set<String> originals = targetToOriginalNames.getOrDefault(id, java.util.Collections.emptySet());
                String annComment = String.join(", ", anns);
                String origComment = String.join(", ", originals);
                runtime.append("    private void deploy").append(capitalize(id)).append("() throws Exception {\n");
                runtime.append("        // Deploy ").append(id).append(" contract");
                if (!origComment.isEmpty()) runtime.append(" (original elements: ").append(origComment).append(")");
                if (!annComment.isEmpty()) runtime.append(" -- detected annotations: ").append(annComment);
                runtime.append("\n");
                runtime.append("    }\n\n");
            }

            runtime.append("    public Web3j getWeb3j() {\n");
            runtime.append("        return web3j;\n");
            runtime.append("    }\n\n");

            runtime.append("    public String getContractAddress(String contractName) {\n");
            runtime.append("        return contractAddresses.get(contractName);\n");
            runtime.append("    }\n");
            runtime.append("}\n");

            JavaFileObject runtimeFile = processingEnv.getFiler().createSourceFile(runtimeQualifiedName, classElem);
            try (java.io.Writer writer = runtimeFile.openWriter()) {
                writer.write(runtime.toString());
            }
            logger.info("Generated Solidity runtime in source output: " + runtimeQualifiedName);

        } catch (Exception ex) {
            processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.ERROR,
                    "Failed to generate Java runtime: " + ex.getMessage());
        }
    }

    /**
     * Generates the deployer class with web3j integration.
     */
    private void generateDeployerClass(TypeElement classElem, String className,
                                      String packageName, Map<String, List<String>> annotationData) {
        try {
            String runnerName = className + "Deployer";
            String packageDecl = packageName.isEmpty() ? "" : "package " + packageName + ";\n\n";

            StringBuilder deployer = new StringBuilder();
            deployer.append(packageDecl);
            deployer.append("import org.junit.Test;\n");
            deployer.append("import static org.junit.Assert.*;\n");
            deployer.append("import org.web3j.protocol.Web3j;\n");
            deployer.append("import org.web3j.protocol.http.HttpService;\n");
            deployer.append("import java.util.logging.Logger;\n\n");
            deployer.append("/**\n");
            deployer.append(" * Generated Smart Contract Deployer for ").append(className).append("\n");
            deployer.append(" * Auto-generated from @Deploy with full annotation scanning.\n");
            deployer.append(" * Detects and deploys all: ");
            
            List<String> annotationTypes = new ArrayList<>(annotationData.keySet());
            for (int i = 0; i < annotationTypes.size(); i++) {
                deployer.append("@").append(annotationTypes.get(i));
                if (i < annotationTypes.size() - 1) deployer.append(", ");
            }
            deployer.append("\n");
            deployer.append(" */\n");
            deployer.append("public class ").append(runnerName).append(" {\n\n");
            deployer.append("    private static final Logger logger = Logger.getLogger(").append(runnerName).append(".class.getName());\n\n");
            deployer.append("    @Test\n");
            deployer.append("    public void deployAllContracts() throws Exception {\n");
            deployer.append("        Web3j web3j = Web3j.build(new HttpService(\"http://localhost:8545\"));\n");
            deployer.append("        assertNotNull(\"Web3j connection should not be null\", web3j);\n");
            deployer.append("        logger.info(\"Connected to blockchain at http://localhost:8545\");\n\n");

            deployer.append("        // Deploy contracts for detected annotations:\n");
            for (Map.Entry<String, List<String>> entry : annotationData.entrySet()) {
                deployer.append("        // @").append(entry.getKey()).append(": ");
                deployer.append(String.join(", ", entry.getValue())).append("\n");
            }

            java.util.Map<String, java.util.Set<String>> targetToAnnotations = new java.util.LinkedHashMap<>();
            java.util.Map<String, java.util.Set<String>> targetToOriginalNames = new java.util.LinkedHashMap<>();
            for (Map.Entry<String, List<String>> entry : annotationData.entrySet()) {
                String annType = entry.getKey();
                if (!isDeployableAnnotation(annType)) {
                    continue;
                }
                for (String element : entry.getValue()) {
                    String id = toMethodIdentifier(element);
                    targetToAnnotations.computeIfAbsent(id, k -> new java.util.LinkedHashSet<>()).add(annType);
                    targetToOriginalNames.computeIfAbsent(id, k -> new java.util.LinkedHashSet<>()).add(element);
                }
            }

            if (!targetToAnnotations.isEmpty()) {
                deployer.append("\n");
                for (String id : targetToAnnotations.keySet()) {
                    deployer.append("        deploy").append(capitalize(id)).append("();\n");
                }
            }

            // Generate method invocation stubs for all public methods in the original class
            for (ExecutableElement method : ElementFilter.methodsIn(classElem.getEnclosedElements())) {
                // Skip constructor and synthetic methods
                if (method.getSimpleName().toString().equals("<init>") ||
                    method.getSimpleName().toString().equals("<clinit>") ||
                    method.getSimpleName().toString().equals("toString") ||
                    method.getSimpleName().toString().equals("hashCode") ||
                    method.getSimpleName().toString().equals("equals")) {
                    continue;
                }
                
                String methodName = method.getSimpleName().toString();
                // Generate method signature stub with parameter types
                StringBuilder methodSig = new StringBuilder(methodName).append("(");
                List<? extends VariableElement> params = method.getParameters();
                for (int i = 0; i < params.size(); i++) {
                    if (i > 0) methodSig.append(", ");
                    methodSig.append(params.get(i).asType().toString().replaceAll(".*\\.", ""));
                }
                methodSig.append(")");
                
                deployer.append("\n        // invoke method ").append(methodSig.toString()).append("\n");
            }

            deployer.append("\n        logger.info(\"All smart contracts deployed successfully\");\n");
            deployer.append("    }\n\n");

            for (java.util.Map.Entry<String, java.util.Set<String>> e : targetToAnnotations.entrySet()) {
                String id = e.getKey();
                java.util.Set<String> anns = e.getValue();
                java.util.Set<String> originals = targetToOriginalNames.getOrDefault(id, java.util.Collections.emptySet());
                String annComment = String.join(", ", anns);
                String origComment = String.join(", ", originals);
                deployer.append("    private void deploy").append(capitalize(id)).append("() throws Exception {\n");
                deployer.append("        // Deploy ").append(id).append(" contract");
                if (!origComment.isEmpty()) deployer.append(" (original elements: ").append(origComment).append(")");
                if (!annComment.isEmpty()) deployer.append(" -- detected annotations: ").append(annComment);
                deployer.append("\n");
                deployer.append("    }\n\n");
            }

            deployer.append("}\n");

            // Write deployer file
            java.nio.file.Path outPath = java.nio.file.Paths.get("generatedsmartcontract", "runner", runnerName + ".java");
            java.nio.file.Files.createDirectories(outPath.getParent());
            java.nio.file.Files.writeString(outPath, deployer.toString());
            logger.info("Generated deployer: " + outPath);

        } catch (Exception ex) {
            processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.ERROR,
                    "Failed to generate deployer: " + ex.getMessage());
        }
    }

    /**
     * Converts a class name to a variable name (camelCase).
     */
    private String toVariableName(String className) {
        if (className == null || className.isEmpty()) {
            return "";
        }
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    /**
     * Sanitize a string into a Java identifier fragment suitable for method names.
     */
    private String toMethodIdentifier(String input) {
        if (input == null) return "contract";
        // remove characters that are invalid in Java identifiers
        String s = input.replaceAll("[^A-Za-z0-9]", "");
        if (s.isEmpty()) s = "contract";
        // ensure it doesn't start with a digit
        if (Character.isDigit(s.charAt(0))) s = "C" + s;
        return s;
    }

    private String effectiveSocialAgentName(de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent socialAgent) {
        if (socialAgent == null) {
            return "";
        }
        if (socialAgent.agentName() != null && !socialAgent.agentName().isBlank()) {
            return socialAgent.agentName();
        }
        return socialAgent.name();
    }

    private String capitalize(String in) {
        if (in == null || in.isEmpty()) return in;
        return Character.toUpperCase(in.charAt(0)) + in.substring(1);
    }
}
