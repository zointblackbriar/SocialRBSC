/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.metamodelannotationtest;

import java.lang.annotation.Annotation;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;


/**
 * Runtime Environment for Web3j should be here.
 *
 * @author $author$
 */
@Compartment(bindingRole = "Doctor")
interface SampleCompartmentInterface {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    void testMethod();
}

/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class CompartmentAnnotationTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    Logger logger = Logger.getLogger(CompartmentAnnotationTest.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void compartmentFunctionTest() {
        // Check if SampleInterface has the @Compartment annotation
        Annotation compartmentAnnotation = SampleCompartmentInterface.class.getAnnotation(Compartment.class);
        assertNotNull(compartmentAnnotation, "SampleInterface should have the @Compartment annotation");

        // Verify that the annotation is indeed of type @Compartment
        Compartment compartment = (Compartment) compartmentAnnotation;

        // Verify that the bindingRole value is "Doctor"
        String bindingRole = compartment.bindingRole();
        assertEquals("Doctor", bindingRole, "Compartment bindingRole should be Doctor");
        logger.info("SampleInterface has @Compartment annotation with bindingRole: " + bindingRole);

        try {
            Class<?> clazz = SampleCompartmentInterface.class; // We have Role annotation on this class
            System.out.println(clazz);
            // Annotation annotation = clazz.getAnnotation(SampleCompartmentInterface.class);
            // String nameOfTheAbstractedObject = annotation.annotationType().getSimpleName();
            // System.out.println("Compartment annotation: " + nameOfTheAbstractedObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
