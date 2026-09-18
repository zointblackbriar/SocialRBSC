/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.mas.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Provides hierarchical structure for goal decomposition into plans and subgoals.
 *
 * This annotation enables complex planning by organizing goals into a hierarchical tree structure
 * where high-level goals are decomposed into lower-level plans and subgoals. This hierarchical
 * approach facilitates structured problem-solving and modular planning strategies.
 *
 * Example usage:
 * <pre>
 * {@code
 * @GoalPlanTree
 * public class MealPlanningHierarchy {
 *     // Hierarchical goal structure for meal preparation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface GoalPlanTree {
	/**
	 * Optional root goal identifier for goal-plan-tree definitions.
	 */
	String root() default "";
}
