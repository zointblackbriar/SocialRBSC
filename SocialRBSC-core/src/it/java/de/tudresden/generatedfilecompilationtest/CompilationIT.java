/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.generatedfilecompilationtest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.tools.JavaFileObject;
import static javax.tools.JavaFileObject.Kind.SOURCE;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import com.google.testing.compile.Compilation;
import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;
import com.google.testing.compile.JavaFileObjects;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class CompilationIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(CompilationIT.class.getName());

    /**
     * TODO DOCUMENT ME!
     */
    private static final JavaFileObject source1 = JavaFileObjects.forSourceLines("test.Source1", // format one per line
            "package test;", "", "class Source1 {}");

    /**
     * TODO DOCUMENT ME!
     */
    private static final JavaFileObject source2 = JavaFileObjects.forSourceLines("test.Source2", // format one per line
            "package test;", "", "interface Source2 {}");

    /**
     * Role-based Java Source code generation through tests.
     */
    private static final JavaFileObject ROLE_JAVA_SOURCE_CODE = JavaFileObjects.forSourceLines("test.HelloWorld", "package test;",
            "", "import " + Role.class.getCanonicalName() + ";", "", "@Role", "public class HelloWorld {", "  String weird() {",
            "    return \"sample string \";", "  }", "}");

    /**
     * TODO DOCUMENT ME!
     */
    private static final JavaFileObject COMPARTMENT_JAVA_SOURCE_CODE = JavaFileObjects.forSourceLines("test.HelloWorld",
            "package test;", "", "import " + Compartment.class.getCanonicalName() + ";", "",
            "@Compartment(bindingRole=\"sample\", activateCompartment=true, deactivateCompartment=false)",
            "public class HelloWorld {", "  String weird() {", "    return \"sample string \";", "  }", "}");

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @AfterClass
    public static void deinitializer() {
        logger.info("Operation completed");
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    @BeforeClass
    // We need to copy all files from the autogen to samples folder
    public static void testSetupFiles() throws IOException {
        Path sourcePath = Paths.get("src", "main", "java", "de", "tudresden", "codegenerator", "autogen");
        Path destinationPath = Paths.get("src", "test", "resources", "samples");
        logger.info("setup function for before all has been activated");

        // CompilationTest.copyFileUsingApache("src/main/java/de/tudresden/codegenerator/autogen/",
        // "src/test/resources/samples/");
        CompilationIT.copyFileUsingApache(sourcePath.toString(), destinationPath.toString());
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testBaseRoleJavaSourceCodeCheck() {
        // Fix: Use a relative path as a string directly if the file is located in the resources directory,
        // or adjust the resource handling to align with the file location.
        // Do not use Path library for these kinds of tests
        // The JavaFileObjects.forResource(resourcePath.toString()) call expects a path relative to the classpath.
        // However, Paths.get() generates a file system path, which might not map to the resource location as expected.

        JavaFileObject targetFile = getJavaFileObject("samples/autogen/BaseRole.java", "BaseRole");

        if (targetFile != null) {
            Compilation compilationJadexTemplate = javac().compile(targetFile);
            assertThat(compilationJadexTemplate.status()).isEqualTo(Compilation.Status.SUCCESS);
        }

        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.isNameCompatible("BaseRole", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testCompiler() {
        Compilation compilation = javac().compile(source1, source2);
        assertThat(compilation.status()).isEqualTo(Compilation.Status.SUCCESS);
        assertThat(compilation.sourceFiles()).containsExactly(source1, source2).inOrder();
    }

    /**
     * Sample Compartment code for tests.
     */
    @Test
    public void testHardCodedCompilationForCompartment() {
        assertThat(javac().compile(COMPARTMENT_JAVA_SOURCE_CODE)).succeeded();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testHardCodedCompilationForRoleAnnotation() {
        assertThat(javac().compile(ROLE_JAVA_SOURCE_CODE)).succeeded();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccesfullSocialAgentIntentionClassCreator() {
        final String targetFileString = "samples/autogen/SocialAgentIntention.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "SocialAgentIntention");
        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.toUri().getPath()).endsWith(targetFileString);
        assertThat(targetFile.getName()).endsWith(targetFileString);
        assertThat(targetFile.isNameCompatible("SocialAgentIntention", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccesfullSolidityWrapperClassRoleCreator() {
        final String targetFileString = "samples/autogen/RoleCreator.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "RoleCreator");
        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.toUri().getPath()).endsWith(targetFileString);
        assertThat(targetFile.getName()).endsWith(targetFileString);
        assertThat(targetFile.isNameCompatible("RoleCreator", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccesfullSolidityWrapperComponentCore() {
        final String targetFileString = "samples/autogen/ComponentCore.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "ComponentCore");
        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.toUri().getPath()).endsWith(targetFileString);
        assertThat(targetFile.getName()).endsWith(targetFileString);
        assertThat(targetFile.isNameCompatible("ComponentCore", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccesfullSolidityWrapperComponentRole() {
        final String targetFileString = "samples/autogen/ComponentRole.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "ComponentRole");
        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.toUri().getPath()).endsWith(targetFileString);
        assertThat(targetFile.getName()).endsWith(targetFileString);
        assertThat(targetFile.isNameCompatible("ComponentRole", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccessfulClassAgentSenderClassCodeGenerator() {
        // Path resourcePath = Paths.get("de", "tudresden", "codegenerator", "autogen", "DeepRolePlayer.class");
        final String targetFileString = "samples/autogen/DeepRolePlayer.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "DeepRolePlayer");
        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.toUri().getPath()).endsWith(targetFileString);
        assertThat(targetFile.getName()).endsWith(targetFileString);
        assertThat(targetFile.isNameCompatible("DeepRolePlayer", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccessfulClassDeliberationCycleClassCodeGenerator() {
        final String targetFileString = "samples/autogen/SocialAgentDeliberationCycle.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "SocialAgentDeliberationCycle");
        assertThat(targetFile).isNotNull();

        Compilation compilationDeliberationCycle = javac().compile(targetFile);
        assertThat(compilationDeliberationCycle).isNotNull();
        assertThat(compilationDeliberationCycle.status()).isEqualTo(Compilation.Status.SUCCESS);
        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.isNameCompatible("SocialAgentDeliberationCycle", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccessfulCompilationAgentGoalClassCreation() {
        // Path resourcePath = Paths.get("samples", "autogen", "Compartment.java");
        final String targetFileString = "samples/autogen/Compartment.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "Compartment");

        if (targetFile != null) {
            Compilation compilationSample = javac().compile(targetFile);
            assertThat(compilationSample.status()).isEqualTo(Compilation.Status.SUCCESS);
        }

        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.isNameCompatible("Compartment", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccessfulCompilationForSolContractComponent() {
        final String targetFileString = "samples/autogen/Component.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "Component");

        if (targetFile != null) {
            Compilation compilationRoleComponent = javac().compile(targetFile);
            assertThat(compilationRoleComponent.status()).isEqualTo(Compilation.Status.SUCCESS);
        }

        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.isNameCompatible("Component", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccessfulCompilationForSolContractComponentCore() {
        final String targetFileString = "samples/autogen/ComponentCore.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "ComponentCore");

        if (targetFile != null) {
            Compilation compilationRoleComponent = javac().compile(targetFile);
            assertThat(compilationRoleComponent.status()).isEqualTo(Compilation.Status.SUCCESS);
        }

        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.isNameCompatible("ComponentCore", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccessfulCompilationForSolContractComponentRole() {
        final String targetFileString = "samples/autogen/ComponentRole.java";

        JavaFileObject targetFile = getJavaFileObject(targetFileString, "ComponentRole");

        if (targetFile != null) {
            Compilation compilationRoleComponent = javac().compile(targetFile);
            assertThat(compilationRoleComponent.status()).isEqualTo(Compilation.Status.SUCCESS);
        }

        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.isNameCompatible("ComponentRole", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccessfulCompilationForSolContractPlayer() {
        // Player is proxy contract
        // Path resourcePath = Paths.get("samples", "autogen", "Proxiable.java");
        final String targetFileString = "samples/autogen/Proxiable.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "Proxiable");

        if (targetFile != null) {
            Compilation compilationRoleComponent = javac().compile(targetFile);
            assertThat(compilationRoleComponent.status()).isEqualTo(Compilation.Status.SUCCESS);
        }

        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.isNameCompatible("Proxiable", SOURCE)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSuccessfulGoalPlanTree() {
        final String targetFileString = "samples/autogen/GoalPlanTree.java";
        JavaFileObject targetFile = getJavaFileObject(targetFileString, "GoalPlanTree");

        if (targetFile != null) {
            Compilation compilationJadexTemplate = javac().compile(targetFile);
            assertThat(compilationJadexTemplate.status()).isEqualTo(Compilation.Status.SUCCESS);
        }

        assertThat(targetFile.getKind()).isEqualTo(SOURCE);
        assertThat(targetFile.isNameCompatible("GoalPlanTree", SOURCE)).isTrue();
    }

    /**
     * Util Function.
     *
     * @param  from from a folder
     * @param  to   to a folder - copy operation
     *
     * @return TODO DOCUMENT ME!
     */
    private static boolean copyFileUsingApache(String from, String to) {
        try {
            FileUtils.copyDirectoryToDirectory(new File(from), new File(to));
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    // Helper to safely get a JavaFileObject from the classpath resource or fall back to file system
    private static JavaFileObject getJavaFileObject(String resourcePath, String simpleName) {
        try {
            return JavaFileObjects.forResource(resourcePath);
        } catch (IllegalArgumentException ex) {
            try {
                java.nio.file.Path p = java.nio.file.Paths.get("src", "test", "resources", resourcePath);
                if (java.nio.file.Files.exists(p)) {
                    java.util.List<String> lines = java.nio.file.Files.readAllLines(p);
                    return JavaFileObjects.forSourceLines("samples.autogen." + simpleName, lines.toArray(new String[0]));
                }
                java.nio.file.Path p2 = java.nio.file.Paths.get("src", "main", "java", "de", "tudresden", "codegenerator", "autogen", simpleName + ".java");
                if (java.nio.file.Files.exists(p2)) {
                    java.util.List<String> lines = java.nio.file.Files.readAllLines(p2);
                    return JavaFileObjects.forSourceLines("samples.autogen." + simpleName, lines.toArray(new String[0]));
                }
                throw ex;
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }
}
