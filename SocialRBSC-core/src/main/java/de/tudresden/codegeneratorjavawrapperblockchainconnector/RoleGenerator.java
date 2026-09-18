/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.codegeneratorjavawrapperblockchainconnector;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import javax.lang.model.element.Modifier;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class RoleGenerator {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final String FOUR_WHITESPACES = "    ";

    /**
     * TODO DOCUMENT ME!
     */
    private static final String PERSON_PACKAGE_NAME = "de.tudresden.codegenerator.autogen";

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(RoleGenerator.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static boolean generationForRole() {
        try {
            RoleGenerator personGen = new RoleGenerator();
            personGen.writeToOutputFile(PERSON_PACKAGE_NAME, personGen.getStudentClass());
            personGen.writeToOutputFile(PERSON_PACKAGE_NAME, personGen.getPersonInterface());
            personGen.writeToOutputFile(PERSON_PACKAGE_NAME, personGen.getGenderEnum());

            // personGen.writeToOutputFile(PERSON_PACKAGE_NAME, personGen.getComparatorAnonymousClass());
            logger.info("PersonGenerator active");
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * Test role writing.
     *
     * @param  interfaceName TODO DOCUMENT ME!
     * @param  className     TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static boolean generationForSpecificCompartmentRoles(String interfaceName, String className) {
        try {
            RoleGenerator role = new RoleGenerator();
            role.writeToOutputFile(PERSON_PACKAGE_NAME, role.setRoleInterface(interfaceName));
            role.writeToOutputFile(PERSON_PACKAGE_NAME, role.setRoleClass(className, interfaceName));
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    // Get package name
    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static String getPersonPackageName() {
        return PERSON_PACKAGE_NAME;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    public void generateGenderEnum() throws IOException {
        writeToOutputFile(getPersonPackageName(), getGenderEnum());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    public void generatePersonInterface() throws IOException {
        writeToOutputFile(getPersonPackageName(), getPersonInterface());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public TypeSpec getComparatorAnonymousClass() {
        return TypeSpec
                    .anonymousClassBuilder("")
                    .addSuperinterface(ParameterizedTypeName.get(Comparator.class, String.class))
                    .addMethod(MethodSpec.methodBuilder("compare")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .addParameter(String.class, "a")
                        .addParameter(String.class, "b")
                        .returns(int.class)
                        .addStatement("return a.length() - b.length()")
                        .build())
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public FieldSpec getDefaultNameField() {
        return FieldSpec
                    .builder(String.class, "DEFAULT_NAME")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer("$S", "Alice")
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public TypeSpec getGenderEnum() {
        return TypeSpec
                    .enumBuilder("Gender")
                    .addModifiers(Modifier.PUBLIC)
                    .addEnumConstant("MALE")
                    .addEnumConstant("FEMALE")
                    .addEnumConstant("UNSPECIFIED")
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public TypeSpec getPersonInterface() {
        return TypeSpec
                    .interfaceBuilder("Person")
                    .addModifiers(Modifier.PUBLIC)
                    .addField(getDefaultNameField())
                    .addMethod(MethodSpec.methodBuilder("getName")
                        .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                        .returns(String.class)
                        .build())
                    .addMethod(MethodSpec.methodBuilder("getDefaultName")
                        .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                        .returns(String.class)
                        .addCode(CodeBlock.builder().addStatement("return DEFAULT_NAME").build())
                        .build())
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public CodeBlock getPrintNameMultipleTimesImpl() {
        return CodeBlock
                    .builder()
                    .beginControlFlow("for (int i = $L; i < $L; i++)")
                    .addStatement("System.out.println(name)")
                    .endControlFlow()
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public CodeBlock getPrintNameMultipleTimesLambdaImpl() {
        return CodeBlock
                    .builder()
                    .addStatement("$T<$T> names = new $T<>()", List.class, String.class, ArrayList.class)
                    .addStatement("$T.range($L, $L).forEach(i -> names.add(name))", IntStream.class, 0, 10)
                    .addStatement("names.forEach(System.out::println)")
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public MethodSpec getPrintNameMultipleTimesMethod() {
        return MethodSpec
                    .methodBuilder("printNameMultipleTimes")
                    .addModifiers(Modifier.PUBLIC)
                    .addCode(getPrintNameMultipleTimesLambdaImpl())
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public MethodSpec getSortByLength() {
        return MethodSpec
                    .methodBuilder("sortByLength")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(ParameterSpec.builder(
                                ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(String.class)), "strings")
                        .build())
                    .addStatement("$T.sort($N, $L)", Collections.class, "strings", getComparatorAnonymousClass())
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public TypeSpec getStudentClass() {
        return TypeSpec
                    .classBuilder("Student")
                    .addSuperinterface(ClassName.get(PERSON_PACKAGE_NAME, "Person"))
                    .addModifiers(Modifier.PUBLIC)
                    .addField(FieldSpec.builder(String.class, "name").addModifiers(Modifier.PRIVATE).build())
                    .addMethod(MethodSpec.methodBuilder("getName")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .returns(String.class)
                        .addStatement("return this.name")
                        .build())
                    .addMethod(MethodSpec.methodBuilder("setName")
                        .addParameter(String.class, "name")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("this.name = name")
                        .build())
                    .addMethod(getPrintNameMultipleTimesMethod())
                    .addMethod(getSortByLength())
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  className     TODO DOCUMENT ME!
     * @param  interfaceName TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public TypeSpec setRoleClass(String className, String interfaceName) {
        return TypeSpec
                    .classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    // .addSuperinterface(de.tudresden.codegenerator.autogen.Player.class)
                    .addField(FieldSpec.builder(String.class, "name").addModifiers(Modifier.PRIVATE).build())
                    .addMethod(MethodSpec.methodBuilder("getName")
                        // .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC).returns(String.class).addStatement("return this.name").build())
                    .addMethod(MethodSpec.methodBuilder("setName")
                        .addParameter(String.class, "name")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("this.name = name")
                        .build())
                    .addMethod(getPrintNameMultipleTimesMethod())
                    .addMethod(getSortByLength())
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  interfaceName TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public TypeSpec setRoleInterface(String interfaceName) {
        return TypeSpec
                    .interfaceBuilder(interfaceName)
                    .addModifiers(Modifier.PUBLIC)
                    // .addField(getDefaultNameField())
                    // .addMethod(MethodSpec
                    // .methodBuilder("getName")
                    // .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                    // .returns(String.class)
                    // .build())
                    // .addMethod(MethodSpec
                    // .methodBuilder("getDefaultName")
                    // .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                    // .returns(String.class)
                    // .addCode(CodeBlock
                    // .builder()
                    // .addStatement("return DEFAULT_NAME")
                    // .build())
                    // .build())
                    .build();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  packageName TODO DOCUMENT ME!
     * @param  typeSpec    TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    private void writeToOutputFile(String packageName, TypeSpec typeSpec) throws IOException {
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec).indent(FOUR_WHITESPACES).build();

        // javaFile.writeTo(fileObject);
        javaFile.writeTo(Paths.get("./src/main/java"));
    }
}
