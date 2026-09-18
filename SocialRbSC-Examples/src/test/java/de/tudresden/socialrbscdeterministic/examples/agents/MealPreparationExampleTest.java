/*
 * Copyright 2022-2025 Orcun Oruc
 */
package de.tudresden.socialrbscdeterministic.examples.agents;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import de.tudresden.socialrbscdeterministic.mas.annotation.AvoidanceCondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.Goal;
import de.tudresden.socialrbscdeterministic.mas.annotation.Plan;
import de.tudresden.socialrbscdeterministic.mas.annotation.WillingnessCondition;

public class MealPreparationExampleTest {

    @Test
    public void testAnnotationsPresent() {
        Class<?> top = MealPreparationExample.class;
        assertTrue(top.isAnnotationPresent(Goal.class), "Top-level class must be annotated with @Goal");

        Class<?> makePasta = MealPreparationExample.MakePasta.class;
        assertTrue(makePasta.isAnnotationPresent(Plan.class), "MakePasta must be annotated with @Plan");
        Plan p = makePasta.getAnnotation(Plan.class);
        assertEquals("Make pasta", p.value());
        assertTrue(p.avoidance().contains("peak"), "Avoidance info should be present");

        // Check an action method has an avoidance condition
        try {
            assertNotNull(MealPreparationExample.MakePasta.BoilWater.class.getMethod("turnOnStove"), "turnOnStove exists");
            assertTrue(MealPreparationExample.MakePasta.BoilWater.class.getMethod("turnOnStove").isAnnotationPresent(AvoidanceCondition.class), "turnOnStove should have AvoidanceCondition");

            Annotation a = MealPreparationExample.MakePasta.ServePasta.class.getMethod("serve").getAnnotation(WillingnessCondition.class);
            assertNotNull(a, "serve should have willingness condition");

        } catch (NoSuchMethodException e) {
            fail("Expected methods not found: " + e.getMessage());
        }
    }
}
