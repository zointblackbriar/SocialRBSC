/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.generatedfilecompilationtest;

import java.io.IOException;

import javax.tools.JavaFileObject;

import org.junit.Ignore;
import org.junit.Test;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.common.truth.Truth.assertThat;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class JavaFileObjectsIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    // Lazy load resource helper to avoid class-init failures when resources are missing from the classpath
    private static JavaFileObject loadResourceOrFallback(String resourcePath, String simpleName) {
        try {
            return com.google.testing.compile.JavaFileObjects.forResource(resourcePath);
        } catch (IllegalArgumentException ex) {
            try {
                // Try typical test resource location first
                java.nio.file.Path p1 = java.nio.file.Paths.get("src", "test", "resources", resourcePath);
                if (java.nio.file.Files.exists(p1)) {
                    java.util.List<String> lines = java.nio.file.Files.readAllLines(p1);
                    return com.google.testing.compile.JavaFileObjects.forSourceLines("samples.autogen." + simpleName, lines.toArray(new String[0]));
                }
                // Fall back to main autogen sources
                java.nio.file.Path p2 = java.nio.file.Paths.get("src", "main", "java", "de", "tudresden", "codegenerator", "autogen", simpleName + ".java");
                if (java.nio.file.Files.exists(p2)) {
                    java.util.List<String> lines = java.nio.file.Files.readAllLines(p2);
                    return com.google.testing.compile.JavaFileObjects.forSourceLines("samples.autogen." + simpleName, lines.toArray(new String[0]));
                }
                // rethrow original for clarity
                throw ex;
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }

    private static JavaFileObject getSolidityWrapperFile() {
        return loadResourceOrFallback("samples/autogen/Proxiable.java", "Proxiable");
    }

    private static JavaFileObject getGoalPlanTreeFile() {
        return loadResourceOrFallback("samples/autogen/GoalPlanTree.java", "GoalPlanTree");
    }

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void compilesWithoutErrorSolidityGreeterContractWrapper() {
        assertAbout(javaSource()).that(getSolidityWrapperFile()).compilesWithoutError();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void compilesWithoutErrorSolidityLanguageWrapper() {
        assertAbout(javaSource()).that(getGoalPlanTreeFile()).compilesWithoutError();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    @Test
    public void testForJavaSourceCode() throws IOException {
        JavaFileObject fileObject = com.google.testing.compile.JavaFileObjects.forSourceLines("example.HelloWorld",
                "package example;", "", "final class HelloWorld {", "  void sayHello() {", "    System.out.println(\"hello!\");",
                "  }", "}");
        assertThat(fileObject.getCharContent(false)).isEqualTo("package example;\n" + "\n" + "final class HelloWorld {\n" +
                "  void sayHello() {\n" + "    System.out.println(\"hello!\");\n" + "  }\n" + "}");
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testJavaFileObjects() {
        javax.tools.JavaFileObject javaFileObjects = com.google.testing.compile.JavaFileObjects.forResource(
                "de/tudresden/generatedfilecompilationtest/JavaFileObjectsIT.class");
        assertThat(javaFileObjects.getKind()).isEqualTo(javax.tools.JavaFileObject.Kind.CLASS);
        assertThat(javaFileObjects.getKind()).isEqualTo(JavaFileObject.Kind.CLASS);
        assertThat(javaFileObjects.toUri().getPath()).endsWith(
            "de/tudresden/generatedfilecompilationtest/JavaFileObjectsIT.class");
        assertThat(javaFileObjects.getName()).endsWith("de/tudresden/generatedfilecompilationtest/JavaFileObjectsIT.class");
        assertThat(javaFileObjects.isNameCompatible("JavaFileObjectsIT", JavaFileObject.Kind.CLASS)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Ignore
    public void testJavaFileObjectsPlayerClassCompileTesting() {
        javax.tools.JavaFileObject playerClass = com.google.testing.compile.JavaFileObjects.forResource(
                "de/tudresden/codegenerator/autogen/Player.class");
        assertThat(playerClass.getKind()).isEqualTo(javax.tools.JavaFileObject.Kind.CLASS);
        assertThat(playerClass.getKind()).isEqualTo(JavaFileObject.Kind.CLASS);
        assertThat(playerClass.toUri().getPath()).endsWith("Player.class");
        assertThat(playerClass.getName()).endsWith("Player.class");
        assertThat(playerClass.isNameCompatible("Player", JavaFileObject.Kind.CLASS)).isTrue();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testJavaFileObjectsSocialAgentBeliefs() {
        javax.tools.JavaFileObject socialAgentBeliefClass = com.google.testing.compile.JavaFileObjects.forResource(
                "de/tudresden/codegenerator/autogen/SocialAgentBelief.class");
        assertThat(socialAgentBeliefClass.getKind()).isEqualTo(javax.tools.JavaFileObject.Kind.CLASS);
        assertThat(socialAgentBeliefClass.getKind()).isEqualTo(JavaFileObject.Kind.CLASS);
        assertThat(socialAgentBeliefClass.toUri().getPath()).endsWith("SocialAgentBelief.class");
        assertThat(socialAgentBeliefClass.getName()).endsWith("SocialAgentBelief.class");
        assertThat(socialAgentBeliefClass.isNameCompatible("SocialAgentBelief", JavaFileObject.Kind.CLASS)).isTrue();
    }
}
