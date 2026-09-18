/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.annotationprocessortest;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;

import de.tudresden.codegenerator.soliditygenerator.SolidityGenerator;
import de.tudresden.multiagentsystem.annotationprocessor.RoleProcessor;
import de.tudresden.multiagentsystem.annotationprocessor.SocialAgentProcessor;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class RoleProcessorMockTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Mock
    private Logger mockLogger;

    /**
     * TODO DOCUMENT ME!
     */
    @Mock
    private SolidityGenerator mockSolidityGenerator;

    /**
     * TODO DOCUMENT ME!
     */
    private RoleProcessor roleProcessor;

    /**
     * TODO DOCUMENT ME!
     */
    private SocialAgentProcessor socialAgentProcessor;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        roleProcessor = new RoleProcessor(mockLogger, mockSolidityGenerator);
        socialAgentProcessor = new SocialAgentProcessor(mockLogger, mockSolidityGenerator);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    @Test
    public void testRoleProcessor() throws IOException {
        // Mocks are already initialized in setUp()

        JavaFileObject sampleRoleAndAgentFile = JavaFileObjects.forSourceString(
                "de.tudresden.gasmaskexamples.GasMask_Examples.Professor",
                "package de.tudresden.gasmaskexamples.GasMask_Examples;\n" + "\n" +
                    "import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;\n" +
                    "import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentGoal;\n" +
                    "import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;\n" +
                    "import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;\n" +
                    "import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;\n" + "\n" + "@Role\n" +
                    "@SocialAgent(agentName = \"SocialAgentBuyer\")\n" + "class Professor {\n" + "\n" + "    @RoleMethod\n" +
                    "    public void sampleMethod() {\n" + "    }\n" + "\n" + "    @Compartment\n" + "    @SocialAgentGoal\n" +
                    "    public static class App {\n" + "\n" + "        public static void main(String[] args) {\n" +
                    "            System.out.println(\"Hello World!\");\n" + "        }\n" + "\n" +
                    "        public void secondMethod() {\n" + "        }\n" + "\n" + "        public String testMethod() {\n" +
                    "            return \"test String\";\n" + "        }\n" + "\n" + "        interface Compartment {\n" + "\n" +
                    "            public void helloworld();\n" + "        }\n" + "    }\n" + "}\n" + "\n" + "@Role\n" +
                    "class Student {\n" + "}\n" + "\n" + "@Compartment\n" + "interface UniversityCompartment {\n" + "}");

        JavaFileObject sellerAgent = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.Player",
                "package de.tudresden.socialrbscdeterministic;\n" +
                    "import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;\n" + "/**\n" +
                    " * Test annotation.\n" + " *\n" + " * @author $author$\n" + " */\n" +
                    "@SocialAgent(agentName = \"Player\")\n" + "public class Player {}");

        // Compile the source file with the annotation processor
        Compilation compilation = Compiler
                    .javac()
                    .withProcessors(roleProcessor, socialAgentProcessor)
                    .compile(sampleRoleAndAgentFile, sellerAgent);

        System.out.println(compilation.status());

        // Assert that the compilation was successful
        assertNotNull(compilation);
        assertEquals(Compilation.Status.SUCCESS, compilation.status(), "Compilation should be successful");

        // Verify the generated file exists and check its content
        Optional<JavaFileObject> generatedFile = compilation.generatedFile(StandardLocation.SOURCE_OUTPUT, "generatedsmartcontract/contracts/Player.sol");
        if (generatedFile.isPresent()) {
            String generatedFileContent = generatedFile.get().getCharContent(true).toString();
            assertNotNull(generatedFileContent);
        }
    }

    @Test
    public void testRoleProcessorWithAnnotations() throws IOException {
        JavaFileObject sourceFile = JavaFileObjects.forSourceString("de.tudresden.gasmaskexamples.SocialRbSC_Examples.Professor",
                "package de.tudresden.gasmaskexamples.SocialRbSC_Examples;\n" + "\n" +
                        "import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;\n" +
                        "import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;\n" +
                        "import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;\n" +
                        "import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;\n" + "\n" + "@Role\n" +
                        "@SocialAgent(agentName = \"SocialAgentBuyerTest\")\n" + "class Professor {\n" + "\n" + "    @RoleMethod\n" +
                        "    public void sampleMethod() {\n" + "    }\n" + "\n" + "    @Compartment\n" +
                        "    public static class App {\n" + "\n" + "        public static void main(String[] args) {\n" +
                        "            System.out.println(\"Hello World!\");\n" + "        }\n" + "\n" +
                        "        public void secondMethod() {\n" + "        }\n" + "\n" + "        public String testMethod() {\n" +
                        "            return \"test String\";\n" + "        }\n" + "\n" + "        interface Student {\n" +
                        "            public void helloworld();\n" + "        }\n" + "    }\n" + "}\n" + "\n" + "@Role\n" +
                        "class Student {\n" + "}\n" + "\n" + "@Compartment\n" + "interface UniversityCompartment {\n" + "}");

        // Compile the source file with the annotation processor
        Compilation compilation = Compiler.javac().withProcessors(roleProcessor, socialAgentProcessor).compile(sourceFile);

        // Assert that the compilation was successful
        assertEquals(Compilation.Status.SUCCESS, compilation.status(), "Compilation should be successful");

        // Check generated files
        Optional<JavaFileObject> studentFile = compilation.generatedFile(StandardLocation.SOURCE_OUTPUT, "generatedsmartcontract/contracts/Student.sol");
        Optional<JavaFileObject> professorFile = compilation.generatedFile(StandardLocation.SOURCE_OUTPUT, "generatedsmartcontract/contracts/Professor.sol");
        Optional<JavaFileObject> universityFile = compilation.generatedFile(StandardLocation.SOURCE_OUTPUT, "generatedsmartcontract/contracts/UniversityCompartment.sol");
        System.out.println(studentFile);

        // Read file contents if they exist
        if (studentFile.isPresent() && professorFile.isPresent() && universityFile.isPresent()) {
            String content1 = studentFile.get().getCharContent(true).toString();
            String content2 = professorFile.get().getCharContent(true).toString();
            String content3 = universityFile.get().getCharContent(true).toString();

            // Additional assertions to verify file contents are not empty
            assertFalse(content1.trim().isEmpty(), "Student.sol content should not be empty");
            assertFalse(content2.trim().isEmpty(), "Professor.sol content should not be empty");
            assertFalse(content3.trim().isEmpty(), "UniversityCompartment.sol content should not be empty");

            // You might also want to add specific content assertions based on your expected output
            // For example:
            assertTrue(content1.contains("contract Student"), "Student.sol should contain contract definition");
            assertTrue(content2.contains("SocialAgentBuyerTest"), "Professor.sol should contain SocialAgentBuyerTest");
        }

    }
}
