package de.tudresden.multiagentsystem.annotationprocessor;

import com.google.auto.service.AutoService;

import de.tudresden.codegenerator.soliditygenerator.SolidityGenerator;
import de.tudresden.socialrbscdeterministic.mas.annotation.Action;
import de.tudresden.socialrbscdeterministic.mas.annotation.AvoidanceCondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.Goal;
import de.tudresden.socialrbscdeterministic.mas.annotation.Plan;
import de.tudresden.socialrbscdeterministic.mas.annotation.SubGoal;
import de.tudresden.socialrbscdeterministic.mas.annotation.WillingnessCondition;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Logger;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"de.tudresden.socialrbscdeterministic.mas.annotation.Goal","de.tudresden.socialrbscdeterministic.mas.annotation.Plan","de.tudresden.socialrbscdeterministic.mas.annotation.SubGoal","de.tudresden.socialrbscdeterministic.mas.annotation.Action","de.tudresden.socialrbscdeterministic.mas.annotation.AvoidanceCondition","de.tudresden.socialrbscdeterministic.mas.annotation.WillingnessCondition"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class GoalPlanProcessor extends AbstractProcessor {

    private static final Logger logger = Logger.getLogger(GoalPlanProcessor.class.getName());

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "GoalPlanProcessor invoked; annotations.size=" + (annotations == null ? 0 : annotations.size()));
        // Don't bail out when 'annotations' is empty — we still inspect the round
        // environment for annotated elements by name. This is necessary when the
        // annotations come from a different compilation classpath (like the IT
        // which compiles temporary annotation stubs).


        // Find elements annotated with @Goal by name. We avoid referencing the
        // annotation class literal directly to remain resilient when the
        // annotation types are provided from a different compilation classpath
        // (e.g., the IT temporary stub annotations).
        Set<? extends Element> goals = findElementsAnnotatedWithName(roundEnv, "de.tudresden.socialrbscdeterministic.mas.annotation.Goal");

        for (Element e : goals) {
            if (e.getKind() != ElementKind.CLASS) continue;
            TypeElement goalClass = (TypeElement) e;

            String goalName = getAnnotationStringValue(goalClass, "de.tudresden.socialrbscdeterministic.mas.annotation.Goal", "value");
            if (goalName == null) {
                // Fallback to the class name when the annotation value isn't available
                goalName = goalClass.getSimpleName().toString();
            }

            List<Map<String, Object>> planList = new ArrayList<>();

            // Collect Plan elements for this Goal. Support both nested Plan classes
            // and the case where the Goal class itself is also annotated with @Plan
            List<Element> planElements = new ArrayList<>();
            // If the goal class itself is also annotated with @Plan, treat it as a plan
            if (hasAnnotationByName(goalClass, "de.tudresden.socialrbscdeterministic.mas.annotation.Plan")) {
                planElements.add(goalClass);
            }
            // Also collect nested plan classes
            for (Element enclosed : goalClass.getEnclosedElements()) {
                if (enclosed.getKind() == ElementKind.CLASS && hasAnnotationByName(enclosed, "de.tudresden.socialrbscdeterministic.mas.annotation.Plan")) {
                    planElements.add(enclosed);
                }
            }

            for (Element planElem : planElements) {
                TypeElement planClass = (TypeElement) planElem;

                String planName = getFirstAnnotationStringValue(planClass,
                        "de.tudresden.socialrbscdeterministic.mas.annotation.Plan", "value", "name");
                String planAvoidance = getAnnotationStringValue(planClass, "de.tudresden.socialrbscdeterministic.mas.annotation.Plan", "avoidance");
                String planWillingness = getAnnotationStringValue(planClass, "de.tudresden.socialrbscdeterministic.mas.annotation.Plan", "willingness");

                if (planName == null) {
                    // If we cannot read the annotation values, fall back to the
                    // class name and empty metadata.
                    planName = planClass.getSimpleName().toString();
                    planAvoidance = "";
                    planWillingness = "";
                }

                Map<String, Object> planMap = new HashMap<>();
                planMap.put("name", planName);
                planMap.put("avoidance", planAvoidance != null ? planAvoidance : "");
                planMap.put("willingness", planWillingness != null ? planWillingness : "");

                List<Map<String, Object>> subGoalList = new ArrayList<>();

                for (Element planEnclosed : planClass.getEnclosedElements()) {
                    boolean isSubGoal = (planEnclosed.getKind() == ElementKind.CLASS && hasAnnotationByName(planEnclosed, "de.tudresden.socialrbscdeterministic.mas.annotation.SubGoal"));
                    if (isSubGoal) {
                        TypeElement subClass = (TypeElement) planEnclosed;

                        String subName = getAnnotationStringValue(subClass, "de.tudresden.socialrbscdeterministic.mas.annotation.SubGoal", "value");
                        if (subName == null) {
                            subName = subClass.getSimpleName().toString();
                        }

                        Map<String, Object> subMap = new HashMap<>();
                        subMap.put("name", subName);

                        List<Map<String, Object>> actions = new ArrayList<>();

                        for (ExecutableElement method : ElementFilter.methodsIn(subClass.getEnclosedElements())) {
                            boolean isAction = hasAnnotationByName(method, "de.tudresden.socialrbscdeterministic.mas.annotation.Action");
                            if (isAction) {
                                String actionName = getAnnotationStringValue(method, "de.tudresden.socialrbscdeterministic.mas.annotation.Action", "value");
                                if (actionName == null) {
                                    actionName = method.getSimpleName().toString();
                                }

                                String avoidance = getAnnotationStringValue(method, "de.tudresden.socialrbscdeterministic.mas.annotation.AvoidanceCondition", "value");
                                String willingness = getAnnotationStringValue(method, "de.tudresden.socialrbscdeterministic.mas.annotation.WillingnessCondition", "value");

                                Map<String, Object> actionMap = new HashMap<>();
                                actionMap.put("name", actionName);
                                actionMap.put("methodName", method.getSimpleName().toString());
                                actionMap.put("avoidance", avoidance != null ? avoidance : "");
                                actionMap.put("willingness", willingness != null ? willingness : "");
                                actions.add(actionMap);
                            }
                        }

                        subMap.put("actions", actions);
                        subGoalList.add(subMap);
                    }
                }

                planMap.put("subGoals", subGoalList);
                planList.add(planMap);
            }

            processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "Found goal: " + goalName);
            // Render template and write both to file system and to CLASS_OUTPUT so test can detect it
            try {
                // Initialize Velocity using existing properties
                org.apache.velocity.app.Velocity.init(java.nio.file.Paths.get("src", "main", "resources", "velocity.properties").toString());
                org.apache.velocity.Template template = org.apache.velocity.app.Velocity.getTemplate("GoalPlan.vm");
                org.apache.velocity.VelocityContext ctx = new org.apache.velocity.VelocityContext();
                ctx.put("displayName", goalName);
                ctx.put("contractId", sanitize(goalName));
                ctx.put("plans", planList);
                java.io.StringWriter sw = new java.io.StringWriter();
                template.merge(ctx, sw);
                String rendered = sw.toString();

                // write to project filesystem (Goal contract)
                java.nio.file.Path outPath = java.nio.file.Paths.get("generatedsmartcontract", "contracts", sanitize(goalName) + ".sol");
                java.nio.file.Files.createDirectories(outPath.getParent());
                java.nio.file.Files.writeString(outPath, rendered);

                // write to CLASS_OUTPUT (Goal contract)
                javax.tools.FileObject fo = processingEnv.getFiler().createResource(javax.tools.StandardLocation.CLASS_OUTPUT, "", "generatedsmartcontract/contracts/" + sanitize(goalName) + ".sol", goalClass);
                try (java.io.Writer w = fo.openWriter()) {
                    w.write(rendered);
                }

                processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "Rendered and wrote contract for: " + goalName + " -> " + outPath.toString());

                // --- Generate MealPlan and plan-specific contracts and reasoning contract ---
                String goalFolder = "usecase-" + sanitize(goalName).toLowerCase();
                java.nio.file.Path baseDir = java.nio.file.Paths.get("generatedsmartcontract", "contracts", goalFolder);
                java.nio.file.Files.createDirectories(baseDir);

                // MealPlan
                org.apache.velocity.Template mealPlanT = org.apache.velocity.app.Velocity.getTemplate("MealPlanFull.vm");
                org.apache.velocity.VelocityContext mpCtx = new org.apache.velocity.VelocityContext();
                mpCtx.put("plans", planList);
                // helper esc for function-safe names
                mpCtx.put("esc", getEscaper());
                java.io.StringWriter swMealPlan = new java.io.StringWriter();
                mealPlanT.merge(mpCtx, swMealPlan);
                String mealPlanRendered = swMealPlan.toString();
                java.nio.file.Path mealPlanPath = baseDir.resolve("MealPlan.sol");
                java.nio.file.Files.writeString(mealPlanPath, mealPlanRendered);
                // Also write a copy at the top-level contracts directory for tools that
                // expect per-contract files at generatedsmartcontract/contracts/.
                java.nio.file.Path mealPlanRootPath = java.nio.file.Paths.get("generatedsmartcontract", "contracts", "MealPlan.sol");
                java.nio.file.Files.writeString(mealPlanRootPath, mealPlanRendered);
                javax.tools.FileObject foMeal = processingEnv.getFiler().createResource(javax.tools.StandardLocation.CLASS_OUTPUT, "", "generatedsmartcontract/contracts/" + goalFolder + "/MealPlan.sol", goalClass);
                try (java.io.Writer w = foMeal.openWriter()) { w.write(mealPlanRendered); }
                processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "Rendered MealPlan for: " + goalName + " -> " + mealPlanPath.toString());

                // per-plan contracts
                java.util.List<String> planContractNames = new java.util.ArrayList<>();
                for (Map<String, Object> p : planList) {
                    String pName = (String) p.get("name");
                    String planContractName = derivePlanContractName(pName);
                    planContractNames.add(planContractName);

                    org.apache.velocity.Template planT = org.apache.velocity.app.Velocity.getTemplate("PlanContract.vm");
                    org.apache.velocity.VelocityContext pCtx = new org.apache.velocity.VelocityContext();
                    pCtx.put("contractName", planContractName);
                    pCtx.put("subGoals", p.get("subGoals"));
                    pCtx.put("esc", getEscaper());
                    java.io.StringWriter swPlan = new java.io.StringWriter();
                    planT.merge(pCtx, swPlan);
                    String planRendered = swPlan.toString();
                    java.nio.file.Path planPath = baseDir.resolve(planContractName + ".sol");
                    java.nio.file.Files.writeString(planPath, planRendered);
                    // Also write a copy to the root contracts folder so other steps that expect
                    // generatedsmartcontract/contracts/<ContractName>.sol will find it.
                    java.nio.file.Path planRootPath = java.nio.file.Paths.get("generatedsmartcontract", "contracts", planContractName + ".sol");
                    java.nio.file.Files.writeString(planRootPath, planRendered);
                    javax.tools.FileObject foPlan = processingEnv.getFiler().createResource(javax.tools.StandardLocation.CLASS_OUTPUT, "", "generatedsmartcontract/contracts/" + goalFolder + "/" + planContractName + ".sol", goalClass);
                    try (java.io.Writer w = foPlan.openWriter()) { w.write(planRendered); }
                    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "Rendered plan contract: " + planContractName + " -> " + planPath.toString());
                }

                // reasoning contract
                org.apache.velocity.Template reasonT = org.apache.velocity.app.Velocity.getTemplate("Reasoning.vm");
                org.apache.velocity.VelocityContext rCtx = new org.apache.velocity.VelocityContext();
                String reasoningName = "RuleBasedReasoning" + sanitize(goalName).replaceAll("_", "");
                rCtx.put("reasoningName", reasoningName);
                rCtx.put("planContracts", planContractNames);
                rCtx.put("esc", getEscaper());
                java.io.StringWriter swReason = new java.io.StringWriter();
                reasonT.merge(rCtx, swReason);
                String reasonRendered = swReason.toString();
                java.nio.file.Path reasonPath = baseDir.resolve(reasoningName + ".sol");
                java.nio.file.Files.writeString(reasonPath, reasonRendered);
                javax.tools.FileObject foReason = processingEnv.getFiler().createResource(javax.tools.StandardLocation.CLASS_OUTPUT, "", "generatedsmartcontract/contracts/" + goalFolder + "/" + reasoningName + ".sol", goalClass);
                try (java.io.Writer w = foReason.openWriter()) { w.write(reasonRendered); }
                processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "Rendered reasoning contract: " + reasoningName + " -> " + reasonPath.toString());

            } catch (Exception ex) {
                processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.ERROR, "Error generating contract for " + goalName + ": " + ex.getMessage());
            }
        }

        return true;
    }

    private String derivePlanContractName(String planName) {
        if (planName == null) return "PlanContract";
        String lower = planName.toLowerCase();
        // Use explicit, descriptive contract names that include the "PlanRole" suffix
        // so generated per-plan files match the naming convention used elsewhere
        // (e.g., MakePastaPlanRole.sol).
        if (lower.contains("pasta") || lower.contains("make pasta")) return "MakePastaPlanRole";
        if (lower.contains("sandwich")) return "MakeASandwichPlanRole";
        if (lower.contains("salad")) return "MakeASaladPlanRole";
        // fallback to sanitized camel-case-ish name
        String s = sanitize(planName);
        // Make first char uppercase
        if (s.length() > 0) s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
        return s;
    }

    private Object getEscaper() {
        try {
            return new de.tudresden.multiagentsystem.annotationprocessor.TemplateEscaper();
        } catch (Throwable t) {
            // fallback anonymous escaper to avoid processor instantiation errors in test environments
            return new Object() {
                public String methodName(String in) { return (in == null) ? "" : in.replaceAll("[^A-Za-z0-9_]", "_"); }
                public String actionName(String in) { return (in == null) ? "" : in.replaceAll("[^A-Za-z0-9_]", "_"); }
                public String instanceName(String in) { if (in == null || in.isEmpty()) return "inst"; return in.substring(0,1).toLowerCase() + in.substring(1); }
            };
        }
    }

    private boolean hasAnnotationByName(Element element, String annotationFqcn) {
        for (AnnotationMirror am : element.getAnnotationMirrors()) {
            if (am.getAnnotationType().toString().equals(annotationFqcn)) return true;
        }
        return false;
    }

    private Set<Element> findElementsAnnotatedWithName(RoundEnvironment roundEnv, String annotationFqcn) {
        Set<Element> results = new HashSet<>();
        for (Element root : roundEnv.getRootElements()) {
            if (root.getKind() == ElementKind.CLASS) {
                if (hasAnnotationByName(root, annotationFqcn)) results.add(root);
                for (Element enclosed : root.getEnclosedElements()) {
                    if (enclosed.getKind() == ElementKind.CLASS && hasAnnotationByName(enclosed, annotationFqcn)) {
                        results.add(enclosed);
                    }
                }
            }
        }
        return results;
    }

    private String getAnnotationStringValue(Element element, String annotationFqcn, String key) {
        for (AnnotationMirror am : element.getAnnotationMirrors()) {
            if (am.getAnnotationType().toString().equals(annotationFqcn)) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> values = processingEnv.getElementUtils().getElementValuesWithDefaults(am);
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> e : values.entrySet()) {
                    if (e.getKey().getSimpleName().toString().equals(key)) {
                        Object val = e.getValue().getValue();
                        return val != null ? val.toString() : null;
                    }
                }
            }
        }
        return null;
    }

    private String getFirstAnnotationStringValue(Element element, String annotationFqcn, String... keys) {
        for (String key : keys) {
            String value = getAnnotationStringValue(element, annotationFqcn, key);
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return null;
    }

    private String sanitize(String input) {
        if (input == null) return "";
        // Replace non-alphanumeric characters with underscores to make a safe filename
        return input.replaceAll("[^A-Za-z0-9_]", "_");
    }
}
