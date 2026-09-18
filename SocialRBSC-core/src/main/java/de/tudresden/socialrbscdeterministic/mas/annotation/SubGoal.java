/*
 * Copyright 2022-2025 Orcun Oruc
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
 * Marks intermediate goals decomposed from a parent goal.
 *
 * SubGoals enable hierarchical goal refinement and achievement by breaking down complex goals
 * into more manageable sub-objectives. This supports goal decomposition and facilitates
 * structured planning approaches.
 *
 * Example usage:
 * <pre>
 * {@code
 * @SubGoal("BoilWater")
 * public void boilWaterSubGoal() {
 *     // subgoal implementation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SubGoal {
    /**
     * The name or identifier of the subgoal.
     */
    String value();
}
