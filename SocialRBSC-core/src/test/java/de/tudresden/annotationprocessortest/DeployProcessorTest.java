package de.tudresden.annotationprocessortest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.tools.JavaFileObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;

import de.tudresden.multiagentsystem.annotationprocessor.DeployProcessor;
import de.tudresden.multiagentsystem.util.Util;

/**
 * Simple mock test for the Deploy annotation processor.
 */
public class DeployProcessorTest {

    private String readGeneratedSource(Compilation compilation, String qualifiedName) throws IOException {
        Optional<JavaFileObject> generatedSource = compilation.generatedSourceFile(qualifiedName);
        assertTrue(generatedSource.isPresent(), "Missing generated source for " + qualifiedName);
        return generatedSource.get().getCharContent(true).toString();
    }

    @Test
    public void testDeployRunnerGeneration() throws IOException {
        String source = """
            package de.tudresden.socialrbscdeterministic;

            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;

            @Deploy
            public class SampleClass {
                private int value;
                public void doSomething() {}
                public void anotherMethod(int x) {}
            }
            """;

        JavaFileObject sourceFile = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.SampleClass", source);
        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(sourceFile);
        assertEquals(compilation.status().toString(), "SUCCESS");

        Path runnerPath = Paths.get("generatedsmartcontract", "runner", "SampleClassDeployer.java");
        String content = Util.readACompiledFile(runnerPath.toString());
        assertNotNull(content);
        // ensure that the generated file includes a web3j import and the class name
        assertTrue(content.contains("Web3j"));
        assertTrue(content.contains("SampleClassDeployer"));
        // method stub comments should be present
        assertTrue(content.contains("// invoke method doSomething"));
        assertTrue(content.contains("// invoke method anotherMethod"));

        String runtimeContent = readGeneratedSource(compilation,
                "de.tudresden.socialrbscdeterministic.runtime.SampleClassSolidityRuntime");
        assertTrue(runtimeContent.contains("package de.tudresden.socialrbscdeterministic.runtime;"));
        assertTrue(runtimeContent.contains("class SampleClassSolidityRuntime"));
    }

    @Test
    public void testDeployRunnerWithGoalPlanTreeSignature() throws IOException {
        MockitoAnnotations.initMocks(this);
        String source = """
            package de.tudresden.socialrbscdeterministic;
            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
            @Deploy
            public class GoalPlanTree {
                public void add(String name) {}
                public void update(String name) {}
                public void dropLowPriorityGoals(int a, int b) {}
            }
            """;
        JavaFileObject sourceFile = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.GoalPlanTree", source);
        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(sourceFile);
        assertEquals("SUCCESS", compilation.status().toString());
        Path runnerPath = Paths.get("generatedsmartcontract", "runner", "GoalPlanTreeDeployer.java");
        String content = Util.readACompiledFile(runnerPath.toString());
        assertNotNull(content);
        assertTrue(content.contains("add("));
        assertTrue(content.contains("update("));
        assertTrue(content.contains("dropLowPriorityGoals"));

        String runtimeContent = readGeneratedSource(compilation,
                "de.tudresden.socialrbscdeterministic.runtime.GoalPlanTreeSolidityRuntime");
        assertTrue(runtimeContent.contains("class GoalPlanTreeSolidityRuntime"));
    }

    @Test
    public void testDeployRunnerNoMethods() throws IOException {
        String source = """
            package de.tudresden.socialrbscdeterministic;

            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;

            @Deploy
            public class EmptyClass {
                private int x;
            }
            """;
        JavaFileObject src = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.EmptyClass", source);
        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(src);
        assertEquals("SUCCESS", compilation.status().toString());
        Path runPath = Paths.get("generatedsmartcontract","runner","EmptyClassDeployer.java");
        String cont = Util.readACompiledFile(runPath.toString());
        assertNotNull(cont);
        // runner should still compile and include comment header but have no stub lines
        assertTrue(cont.contains("EmptyClassDeployer"));
        assertFalse(cont.contains("// invoke method"));

        String runtimeContent = readGeneratedSource(compilation,
                "de.tudresden.socialrbscdeterministic.runtime.EmptyClassSolidityRuntime");
        assertTrue(runtimeContent.contains("class EmptyClassSolidityRuntime"));
    }

    @Test
    public void testMultipleAnnotatedClasses() throws IOException {
        String srcA = """
            package de.tudresden.socialrbscdeterministic;
            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
            @Deploy public class A { public void foo() {} }
            """;
        String srcB = """
            package de.tudresden.socialrbscdeterministic;
            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
            @Deploy public class B { public void bar() {} }
            """;
        JavaFileObject fileA = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.A", srcA);
        JavaFileObject fileB = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.B", srcB);
        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(fileA, fileB);
        assertEquals("SUCCESS", compilation.status().toString());
        Path pathA = Paths.get("generatedsmartcontract","runner","ADeployer.java");
        Path pathB = Paths.get("generatedsmartcontract","runner","BDeployer.java");
        assertNotNull(Util.readACompiledFile(pathA.toString()));
        assertNotNull(Util.readACompiledFile(pathB.toString()));
        assertTrue(compilation.generatedSourceFile("de.tudresden.socialrbscdeterministic.runtime.ASolidityRuntime").isPresent());
        assertTrue(compilation.generatedSourceFile("de.tudresden.socialrbscdeterministic.runtime.BSolidityRuntime").isPresent());
    }

    @Test
    public void testScanPackageForAnnotations() throws IOException {
        // compile three separate annotated classes all in the same package
        String src1 = """
            package de.tudresden.socialrbscdeterministic;
            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
            @Deploy public class C1 { public void foo() {} }
            """;
        String src2 = """
            package de.tudresden.socialrbscdeterministic;
            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
            @Deploy public class C2 { public void bar() {} }
            """;
        String src3 = """
            package de.tudresden.socialrbscdeterministic;
            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
            @Deploy public class C3 { public void baz() {} }
            """;

        JavaFileObject file1 = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.C1", src1);
        JavaFileObject file2 = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.C2", src2);
        JavaFileObject file3 = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.C3", src3);
        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(file1, file2, file3);
        assertEquals("SUCCESS", compilation.status().toString());

        Path p1 = Paths.get("generatedsmartcontract","runner","C1Deployer.java");
        Path p2 = Paths.get("generatedsmartcontract","runner","C2Deployer.java");
        Path p3 = Paths.get("generatedsmartcontract","runner","C3Deployer.java");
        assertNotNull(Util.readACompiledFile(p1.toString()));
        assertNotNull(Util.readACompiledFile(p2.toString()));
        assertNotNull(Util.readACompiledFile(p3.toString()));
        assertTrue(compilation.generatedSourceFile("de.tudresden.socialrbscdeterministic.runtime.C1SolidityRuntime").isPresent());
        assertTrue(compilation.generatedSourceFile("de.tudresden.socialrbscdeterministic.runtime.C2SolidityRuntime").isPresent());
        assertTrue(compilation.generatedSourceFile("de.tudresden.socialrbscdeterministic.runtime.C3SolidityRuntime").isPresent());
    }

    @Test
    public void testDeployAllModelClasses() throws IOException {
        // define each of the classes requested with a couple of methods
        String[] classSources = new String[]{
            "package de.tudresden.socialrbscdeterministic;\n" +
            "import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;\n" +
            "@Deploy public class GoalPlanTree { public void add(String s) {} }",

            "package de.tudresden.socialrbscdeterministic;\n" +
            "import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;\n" +
            "@Deploy public class Plan { public void execute() {} }",

            "package de.tudresden.socialrbscdeterministic;\n" +
            "import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;\n" +
            "@Deploy public class Precondition { public void check() {} }",

            "package de.tudresden.socialrbscdeterministic;\n" +
            "import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;\n" +
            "@Deploy public class PreconditionSocialAgent { public void evaluate() {} }",

            "package de.tudresden.socialrbscdeterministic;\n" +
            "import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;\n" +
            "@Deploy public class PreconditionVariable { public void value() {} }",

            "package de.tudresden.socialrbscdeterministic;\n" +
            "import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;\n" +
            "@Deploy public class SocialAgent { public void act() {} }",

            "package de.tudresden.socialrbscdeterministic;\n" +
            "import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;\n" +
            "@Deploy public class SocialAgentGoal { public void pursue() {} }",

            "package de.tudresden.socialrbscdeterministic;\n" +
            "import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;\n" +
            "@Deploy public class SocialAgentPlan { public void plan() {} }"
        };

        JavaFileObject[] files = new JavaFileObject[classSources.length];
        for (int i = 0; i < classSources.length; i++) {
            String className = classSources[i].split("public class ")[1].split(" ")[0];
            files[i] = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic." + className, classSources[i]);
        }

        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(files);
        assertEquals("SUCCESS", compilation.status().toString());

        for (String src : classSources) {
            String className = src.split("public class ")[1].split(" ")[0];
            Path runner = Paths.get("generatedsmartcontract","runner", className + "Deployer.java");
            assertNotNull(Util.readACompiledFile(runner.toString()), "Missing deployer for " + className);
            assertTrue(compilation.generatedSourceFile(
                    "de.tudresden.socialrbscdeterministic.runtime." + className + "SolidityRuntime").isPresent());
        }
    }

    @Test
    public void testMedicalDslAnnotationsAreHandledRecursively() throws IOException {
        String source = """
            package de.tudresden.socialrbscdeterministic.medsupply;

            import de.tudresden.socialrbscdeterministic.mas.annotation.*;
            import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.*;
            import java.util.List;
            import java.util.Map;

            @Deploy
            public class MedicalSupplyDeliverySystem {

                @Society(name = \"HealthcareNetwork\")
                public static class HealthcareSociety {

                    @Compartment
                    @Properties({
                        @Property(name = \"deliveryDeadline\", type = \"uint256\"),
                        @Property(name = \"requiredSupplies\", type = \"bytes32[]\")
                    })
                    public static class MissionCompartment {

                        @Role
                        @RoleCardinality(maxInstances = 1)
                        @PlayingConstraint(\"single mission\")
                        public static class DeliveryProviderRole {
                            @Precondition(\"battery > 20\")
                            @Postcondition(\"status = ACCEPTED\")
                            public void acceptDelivery() {}
                        }
                    }

                    @SocialAgent(name = \"UAVDeliveryAgent\")
                    @PlayableRoles({\"DeliveryProvider\", \"RoutePlanner\"})
                    public static class UAVAgent {
                        @Belief(name = \"currentBattery\", type = \"uint256\")
                        private long batteryLevel;

                        @Desire(name = \"deliverSuppliesOnTime\")
                        @Priority(level = 1)
                        private String primaryGoal;

                        @DeliberationCycle
                        @GoalPlanTree(root = \"deliverSuppliesOnTime\")
                        @Plan(name = \"OptimizedDeliveryPlan\")
                        @Decomposition({\"navigate -> completeMission\"})
                        public void deliberate() {}
                    }
                }
            }
            """;

        JavaFileObject sourceFile = JavaFileObjects.forSourceString(
                "de.tudresden.socialrbscdeterministic.medsupply.MedicalSupplyDeliverySystem", source);
        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(sourceFile);
        assertEquals("SUCCESS", compilation.status().toString());

        Path runnerPath = Paths.get("generatedsmartcontract", "runner", "MedicalSupplyDeliverySystemDeployer.java");
        String content = Util.readACompiledFile(runnerPath.toString());
        assertNotNull(content);
        assertTrue(content.contains("deployHealthcareSociety"));
        assertTrue(content.contains("deployMissionCompartment"));
        assertTrue(content.contains("deployDeliveryProviderRole"));
        assertTrue(content.contains("deployUAVAgent"));
        assertTrue(content.contains("deployDeliberate"));
        assertTrue(content.contains("@Compartment: MissionCompartment"));
        assertTrue(content.contains("detected annotations: Compartment"));
        String obsoleteAnnotationName = "Compartment" + "Definition";
        assertFalse(content.contains(obsoleteAnnotationName));
        assertFalse(content.contains("deployBatteryLevel"));
        assertFalse(content.contains("deployPrimaryGoal"));

        String runtimeContent = readGeneratedSource(compilation,
                "de.tudresden.socialrbscdeterministic.medsupply.runtime.MedicalSupplyDeliverySystemSolidityRuntime");
        assertTrue(runtimeContent.contains("package de.tudresden.socialrbscdeterministic.medsupply.runtime;"));
        assertTrue(runtimeContent.contains("deployMissionCompartment"));
        assertTrue(runtimeContent.contains("detected annotations: Compartment"));
    }

    @Test
    public void testDeployScansAnnotatedTypesInSubpackages() throws IOException {
        String deploySource = """
            package de.tudresden.socialrbscdeterministic.autonomousdelivery;

            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;

            @Deploy
            public class DeployExample {
            }
            """;

        String coordinatorSource = """
            package de.tudresden.socialrbscdeterministic.autonomousdelivery;

            import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;

            @SocialAgent(agentName = "AutonomousDeliveryFleetCoordinator", addRole = true)
            public class AutonomousDeliveryFleetCoordinator {
            }
            """;

        String emergencyAgentSource = """
            package de.tudresden.socialrbscdeterministic.autonomousdelivery.agents;

            import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;

            @SocialAgent(agentName = "EmergencyResponseAgent", addRole = true)
            public class EmergencyResponseAgent {
            }
            """;

        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(
                JavaFileObjects.forSourceString(
                        "de.tudresden.socialrbscdeterministic.autonomousdelivery.DeployExample",
                        deploySource),
                JavaFileObjects.forSourceString(
                        "de.tudresden.socialrbscdeterministic.autonomousdelivery.AutonomousDeliveryFleetCoordinator",
                        coordinatorSource),
                JavaFileObjects.forSourceString(
                        "de.tudresden.socialrbscdeterministic.autonomousdelivery.agents.EmergencyResponseAgent",
                        emergencyAgentSource));

        assertEquals("SUCCESS", compilation.status().toString());

        Path runnerPath = Paths.get("generatedsmartcontract", "runner", "DeployExampleDeployer.java");
        String runnerContent = Util.readACompiledFile(runnerPath.toString());
        assertNotNull(runnerContent);
        assertTrue(runnerContent.contains("deployAutonomousDeliveryFleetCoordinator"));
        assertTrue(runnerContent.contains("deployEmergencyResponseAgent"));
        assertTrue(runnerContent.contains("@SocialAgent: AutonomousDeliveryFleetCoordinator, EmergencyResponseAgent")
                || runnerContent.contains("@SocialAgent: EmergencyResponseAgent, AutonomousDeliveryFleetCoordinator"));

        Path contractPath = Paths.get("generatedsmartcontract", "contracts", "EmergencyResponseAgent.sol");
        String contractContent = Util.readACompiledFile(contractPath.toString());
        assertNotNull(contractContent);
        assertTrue(contractContent.contains("contract EmergencyResponseAgent is SocialAgent"));

        String runtimeContent = readGeneratedSource(compilation,
                "de.tudresden.socialrbscdeterministic.autonomousdelivery.runtime.DeployExampleSolidityRuntime");
        assertTrue(runtimeContent.contains("deployEmergencyResponseAgent"));
    }
}
