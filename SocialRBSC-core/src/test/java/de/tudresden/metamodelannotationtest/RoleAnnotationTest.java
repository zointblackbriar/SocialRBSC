/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.metamodelannotationtest;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import de.tudresden.codegenerator.soliditygenerator.SolidityGenerator;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;


/**
 * Class representing a role for testing purposes.
 */
@Role
class TestRole {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Example method with RoleMethod annotation.
     */
    @RoleMethod
    public void roleMethod1() {
        System.out.println("Role Method Invocation 1 ");
    }

    /**
     * Another example method with RoleMethod annotation.
     */
    @RoleMethod
    public void roleMethod2() {
        System.out.println("Role Method Invocation 2 ");
    }
}

/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
class NaturalTypeClass {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    public static void sampleMethod() {
        System.out.println("NaturalTypeClass method");
    }
}

/**
 * Class for testing Role annotation and its methods.
 */
public class RoleAnnotationTest {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(RoleAnnotationTest.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Test method to verify that the @Role and @RoleMethod annotations are present and processed correctly.
     *
     * @throws IOException
     */
    @Test
    public void roleAnnotationTest() throws IOException {
        // Get the class annotated with @Role
        Class<?> clazz = TestRole.class;

        // Get the Role annotation
        Role roleAnnotation = clazz.getAnnotation(Role.class);
        assertNotNull(roleAnnotation, "TestRole class should have a @Role annotation");

        // Print the annotation's type name
        String nameOfTheRole = roleAnnotation.annotationType().getName();
        System.out.println("Class annotation: " + nameOfTheRole);

        // Log and print all declared methods
        System.out.println("Declared Methods:");

        for (Method method : clazz.getDeclaredMethods()) {
            logger.info("Method: " + method.getName());
            System.out.println(method);

            // Check if the method has @RoleMethod annotation
            if (method.isAnnotationPresent(RoleMethod.class)) {
                RoleMethod roleMethodAnnotation = method.getAnnotation(RoleMethod.class);
                assertNotNull(roleMethodAnnotation, "Method " + method.getName() + " should have @RoleMethod annotation");
                System.out.println("Method " + method.getName() + " has @RoleMethod annotation: " + roleMethodAnnotation);
                logger.info("Method " + method.getName() + " has @RoleMethod annotation: " + roleMethodAnnotation);
            }
        }

        // Log and print all methods, including inherited ones
        System.out.println("\nAll Methods (including inherited ones):");

        for (Method method : clazz.getMethods()) {
            logger.info("Method (inherited): " + method.getName());
            System.out.println(method);
        }

        // Log and print all declared fields
        System.out.println("\nDeclared Fields:");

        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field);
        }

        // Log and print all fields, including inherited ones
        System.out.println("\nAll Fields (including inherited ones):");

        for (Field field : clazz.getFields()) {
            System.out.println(field);
        }

        // Assert that the Solidity generator works for the role
        assertTrue(SolidityGenerator.solidityForSocialRbSCRoleGenerateOnlyRole(nameOfTheRole),
            "Solidity generation for role should succeed");
    }
}
