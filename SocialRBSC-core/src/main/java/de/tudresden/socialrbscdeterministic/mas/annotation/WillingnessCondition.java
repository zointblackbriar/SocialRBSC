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
 * Specifies the agent's willingness or readiness to play a role.
 *
 * Willingness conditions determine whether an agent is willing and ready to adopt and execute
 * a particular role. These conditions establish role activation criteria based on agent
 * preferences, capabilities, and internal state.
 *
 * Example usage:
 * <pre>
 * {@code
 * @Role
 * @WillingnessCondition("agent energy > 50 AND agent prefers warm meals")
 * public class ChefRole {
 *     // role implementation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WillingnessCondition {
    /**
     * The willingness condition expression.
     */
    String value();
}
