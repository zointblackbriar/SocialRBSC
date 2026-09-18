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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.tools.JavaFileObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;

import de.tudresden.codegenerator.soliditygenerator.SolidityGenerator;
import de.tudresden.multiagentsystem.annotationprocessor.SocialAgentProcessor;
import de.tudresden.multiagentsystem.util.Util;


/**
 * Mock Testing.
 *
 * @author $author$
 */
public class SocialAgentProcessorMockTest {

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
    private SocialAgentProcessor socialAgentProcessor;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        socialAgentProcessor = new SocialAgentProcessor(mockLogger, mockSolidityGenerator);
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSocialAgentAddRole() throws IOException {
        MockitoAnnotations.initMocks(this);

        String source = """
            package de.tudresden.socialrbscdeterministic;

            import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
            import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
            import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
            import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

            @SocialAgent(agentName = \"SocialAgentWorker\", addRole = true)
            public class SocialAgentWorkerAddRole {
                // Define fields, methods, etc.
            }
            """;


        JavaFileObject sourceFileSocialAgentAddRole = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.SocialAgentWorkerAddRole", source);
        Compilation compilation = Compiler.javac().withProcessors(socialAgentProcessor).compile(sourceFileSocialAgentAddRole);
        System.out.println(compilation.status());
        assertEquals(compilation.status().toString(), "SUCCESS");
        Path filePathSocialAgentWorkerAddRole = Paths.get("generatedsmartcontract", "contracts", "SocialAgentWorkerAddRole");
        String content = Util.readACompiledFile(filePathSocialAgentWorkerAddRole + ".sol");
        assertNotNull(content);

    }

    @Test
    public void testSocialAgentRemoveRole() throws IOException {
        MockitoAnnotations.initMocks(this);
        String source = """
            package de.tudresden.socialrbscdeterministic;

            import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
            import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
            import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
            import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

            @SocialAgent(agentName = \"SocialAgentWorker\", removeRole = true)
            public class SocialAgentWorkerRemoveRole {
                // Define fields, methods, etc.
            }
            """;
        JavaFileObject sourceFileSocialAgentRemoveRole = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.SocialAgentWorkerRemoveRole", source);
        Compilation compilation = Compiler.javac().withProcessors(socialAgentProcessor).compile(sourceFileSocialAgentRemoveRole);
        System.out.println(compilation.status());
        assertEquals(compilation.status().toString(), "SUCCESS");
        Path filePathSocialAgentWorkerRemoveRole = Paths.get("generatedsmartcontract", "contracts", "SocialAgentWorkerRemoveRole");
        String content = Util.readACompiledFile(filePathSocialAgentWorkerRemoveRole + ".sol");
        assertNotNull(content);

    }
    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    @Test
    public void testSocialAgentProcessor() throws IOException {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Create the source file
        JavaFileObject sourceFile = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.TestSocialAgent",
                "package de.tudresden.socialrbscdeterministic;\n" +
                    "import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;\n" +
                    "@SocialAgent(agentName = \"SocialAgentBuyer\")\n" + "public class TestSocialAgent {}");

        // Compile the source file with the annotation processor
        Compilation compilation = Compiler.javac().withProcessors(socialAgentProcessor).compile(sourceFile);

        System.out.println(compilation.status());

        // Assert that the compilation was successful
        assertNotNull(compilation);

        Path filePath = Paths.get("generatedsmartcontract", "contracts", "SocialAgentBuyer");

        String content = Util.readACompiledFile(filePath + ".sol");
        assertNotNull(content);
        assertEquals(compilation.status().toString(), "SUCCESS");

    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testWithAnnotationSocialAgentProcessor() throws IOException {
        MockitoAnnotations.initMocks(this);

        // Create the source files
        JavaFileObject buyerFile = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.SocialAgentBuyer",
                "package de.tudresden.socialrbscdeterministic;\n" +
                    "import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;\n" +
                    "@SocialAgent(agentName = \"SocialAgentBuyer\")\n" + "public class SocialAgentBuyer {}");

        JavaFileObject sellerFile = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.SocialAgentSeller",
                "package de.tudresden.socialrbscdeterministic;\n" +
                    "import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;\n" + "/**\n" +
                    " * Test annotation.\n" + " *\n" + " * @author $author$\n" + " */\n" +
                    "@SocialAgent(agentName = \"SocialAgentSeller\")\n" + "public class SocialAgentSeller {}");

        // Compile the source files with the annotation processor
        Compilation compilation = Compiler.javac().withProcessors(socialAgentProcessor).compile(buyerFile, sellerFile);

        System.out.println("compilation status for the first file: " + compilation.status().toString());
        assertNotNull(compilation);
        assertEquals(compilation.status().toString(), "SUCCESS");

        Path filePath = Paths.get("generatedsmartcontract", "contracts", "SocialAgentSeller");
        String content = Util.readACompiledFile(filePath + ".sol");
        assertNotNull(content);
    }

    @Test
    public void testPreconditionVariable() {
        String testForClassContent = """
                package de.tudresden.socialRbscFramework;
                import de.tudresden.socialrbscdeterministic.mas.annotation.PreconditionSocialAgent;
                import de.tudresden.socialrbscdeterministic.mas.annotation.PreconditionVariable;
                import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
                import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
                import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
                import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;
                                
                @SocialAgent(agentName = "SocialAgentBuyer")
                class Worker {

                    @PreconditionVariable
                    public static int moneyToBeSent;

                    @PreconditionSocialAgent
                    public boolean moneyToBeSent() {
                        if (moneyToBeSent > 0) {
                            return true;
                        }

                        return false;
                    }
                }
                """;
        JavaFileObject javaFileObjectPreconditionVariable = JavaFileObjects.forSourceString("de.tudresden.socialrbscdeterministic.Worker", testForClassContent);
        // Compile the source files with the annotation processor
        Compilation compilation = Compiler.javac().withProcessors(socialAgentProcessor).compile(javaFileObjectPreconditionVariable);
        System.out.println("compilation status for the javaFileObjectPreconditionVariable: " + compilation.status().toString());
        assertNotNull(compilation);
        assertEquals(compilation.status().toString(), "SUCCESS");
    }
}
