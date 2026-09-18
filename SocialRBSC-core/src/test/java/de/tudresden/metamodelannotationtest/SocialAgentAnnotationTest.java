/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.metamodelannotationtest;

import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentBelief;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import java.util.logging.Logger;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class SocialAgentAnnotationTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    Logger logger = Logger.getLogger(SocialAgentAnnotationTest.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void socialAgentBeliefMetamodelAnnotation() {
        // Check if SampleInterface has the @Compartment annotation
        Annotation socialAgentBuyerAnnotation = SocialAgentBuyer.class.getAnnotation(SocialAgent.class);
        assertNotNull(socialAgentBuyerAnnotation, "SocialAgentBuyer should have the @SocialAgent annotation");

        Annotation socialAgentSellerAnnotation = SocialAgentSeller.class.getAnnotation(SocialAgent.class);
        assertNotNull(socialAgentSellerAnnotation, "SocialAgentBuyer should have the @SocialAgent annotation");

        // Verify that the annotation is indeed of type @Compartment
        SocialAgent socialAgentBuyer = (SocialAgent) socialAgentBuyerAnnotation;
        SocialAgent socialAgentSeller = (SocialAgent) socialAgentSellerAnnotation;

        String agentName1 = socialAgentBuyer.agentName();
        String agentName2 = socialAgentSeller.agentName();

        assertEquals("SocialAgentBuyer", agentName1, "SocialAgent agentName should be SocialAgentBuyer");
        logger.info("SocialAgentClass has @SocialAgent annotationn: " + socialAgentBuyer);
        assertEquals("SocialAgentSeller", agentName2, "SocialAgent agentName should be SocialAgentSeller");
        logger.info("SocialAgentClass has @SocialAgent annotationn: " + socialAgentSeller);

        try {
            Class<?> clazz = SocialAgentBuyer.class; // We have Role annotation on this class
            System.out.println(clazz);

            Annotation annotation = clazz.getAnnotation(SocialAgent.class);
            String nameOfTheAbstractedObject = annotation.annotationType().getSimpleName();
            System.out.println("SocialAgentBuyer annotation: " + nameOfTheAbstractedObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void socialAgentFunctionalTest() {
        // Check if SampleInterface has the @Compartment annotation
        Annotation socialAgentBuyerAnnotation = SocialAgentBuyer.class.getAnnotation(SocialAgent.class);
        assertNotNull(socialAgentBuyerAnnotation, "SocialAgentBuyer should have the @SocialAgent annotation");

        Annotation socialAgentSellerAnnotation = SocialAgentSeller.class.getAnnotation(SocialAgent.class);
        assertNotNull(socialAgentSellerAnnotation, "SocialAgentBuyer should have the @SocialAgent annotation");

        // Verify that the annotation is indeed of type @Compartment
        SocialAgent socialAgentBuyer = (SocialAgent) socialAgentBuyerAnnotation;
        SocialAgent socialAgentSeller = (SocialAgent) socialAgentSellerAnnotation;

        String agentName1 = socialAgentBuyer.agentName();
        String agentName2 = socialAgentSeller.agentName();

        assertEquals("SocialAgentBuyer", agentName1, "SocialAgent agentName should be SocialAgentBuyer");
        logger.info("SocialAgentClass has @SocialAgent annotationn: " + socialAgentBuyer);
        assertEquals("SocialAgentSeller", agentName2, "SocialAgent agentName should be SocialAgentSeller");
        logger.info("SocialAgentClass has @SocialAgent annotationn: " + socialAgentSeller);

        try {
            Class<?> clazz = SocialAgentBuyer.class; // We have Role annotation on this class
            System.out.println(clazz);

            Annotation annotation = clazz.getAnnotation(SocialAgent.class);
            String nameOfTheAbstractedObject = annotation.annotationType().getSimpleName();
            System.out.println("SocialAgentBuyer annotation: " + nameOfTheAbstractedObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

/**
 * Test annotation.
 *
 * @author $author$
 */
@SocialAgent(agentName = "SocialAgentBuyer")
class SocialAgentBuyer {
}

/**
 * Test annotation.
 *
 * @author $author$
 */
@SocialAgent(agentName = "SocialAgentSeller")
class SocialAgentSeller {
}

/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
@SocialAgentBelief
class SocialAgentSampleBelief {
}
