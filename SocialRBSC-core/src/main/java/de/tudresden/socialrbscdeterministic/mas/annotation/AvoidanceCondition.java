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
 * Marks situations to avoid or escape from.
 *
 * Avoidance conditions specify situations, states, or contexts that the agent should
 * avoid or escape from. When an avoidance condition becomes true, it triggers replanning
 * to find alternative strategies that don't lead to undesirable states.
 *
 * Example usage:
 * <pre>
 * {@code
 * @Plan(value = "MakePasta")
 * @AvoidanceCondition("peak electricity hours")
 * public void makePastaStrategy() {
 *     // plan will be abandoned if peak electricity hours are detected
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidanceCondition {
    /**
     * The avoidance condition expression describing situations to avoid.
     */
    String value();
}
