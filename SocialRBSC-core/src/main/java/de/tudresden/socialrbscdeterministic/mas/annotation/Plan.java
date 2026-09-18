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
 * Specifies a concrete strategy or method to achieve goals.
 *
 * Plans define the specific approaches and methods that agents can use to achieve their goals.
 * Each plan includes executable actions and decision logic. Plans can have associated avoidance
 * conditions (situations to avoid) and willingness conditions (agent readiness to execute).
 *
 * Example usage:
 * <pre>
 * {@code
 * @Plan(value = "MakePasta",
 *       avoidance = "peak electricity hours",
 *       willingness = "agent prefers warm meals")
 * public void makePastaStrategy() {
 *     // plan implementation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Plan {
    /**
     * The name or identifier of the plan.
     */
    String value() default "";

    /**
     * Alias for value used by higher-level DSL examples.
     */
    String name() default "";

    /**
     * Situations or conditions to avoid while executing this plan.
     */
    String avoidance() default "";

    /**
     * Agent's willingness or readiness conditions for executing this plan.
     */
    String willingness() default "";
}
