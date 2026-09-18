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
 * Indicates a class is a social agent capable of goal-driven behavior and autonomous agent-oriented programming.
 *
 * Social agents are autonomous entities that can engage in goal-driven reasoning, planning, and action
 * execution. They can play multiple roles within compartments and participate in multi-agent societies.
 *
 * Example usage:
 * <pre>
 * {@code
 * @SocialAgent(agentName = "CustomerAgent", addRole = true)
 * public class Customer {
 *     // agent definition
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SocialAgent {
    /**
     * The name of the social agent.
     */
    String agentName() default "";

    /**
     * Alias for agentName used by higher-level DSL examples.
     */
    String name() default "";

    /**
     * Whether the agent can dynamically add roles at runtime.
     */
    boolean addRole() default false;

    /**
     * Whether the agent can dynamically remove roles at runtime.
     */
    boolean removeRole() default false;
}
