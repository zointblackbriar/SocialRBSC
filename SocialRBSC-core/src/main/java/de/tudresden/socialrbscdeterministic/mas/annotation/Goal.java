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
 * Represents a desired objective or state.
 *
 * Goals specify the desired outcomes that agents aim to achieve. Each goal includes success criteria
 * and activation logic for goal achievement. Goals are the primary drivers of agent behavior in
 * goal-driven systems.
 *
 * Example usage:
 * <pre>
 * {@code
 * @Goal("PrepareMeal")
 * public void mealPreparationGoal() {
 *     // goal definition and conditions
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Goal {
    /**
     * The name or description of the goal.
     */
    String value();
}
