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
 * Marks an active strategy or plan for achieving goals.
 *
 * Plans represent the agent's commitment to achieve specified goals through a concrete strategy
 * or method. They include executable actions and decision logic to guide the agent's behavior
 * towards goal achievement.
 *
 * Example usage:
 * <pre>
 * {@code
 * @SocialAgentPlan(triggeredPlan = "makeMeal", defineSociety = "domesticSociety")
 * public void executeMealPreparation() {
 *     // plan implementation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SocialAgentPlan {
    /**
     * The name/identifier of the plan that is triggered.
     */
    String triggeredPlan();

    /**
     * Defines the society context in which this plan operates.
     */
    String defineSociety();
}
