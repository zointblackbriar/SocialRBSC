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
 * Coordinates multiple agents and roles with defined communication patterns and interaction protocols.
 *
 * A Society represents a collection of social agents that interact according to defined rules,
 * communication patterns, and protocols. Societies enable multi-agent coordination and provide
 * the organizational structure for agent interaction.
 *
 * Key responsibilities of a Society:
 * - Coordinate multiple agents
 * - Define communication patterns and protocols
 * - Manage shared resources and constraints
 * - Enforce organizational rules and norms
 *
 * Example usage:
 * <pre>
 * {@code
 * @Society
 * public class MealPreparationSociety {
 *     // society definition with multiple coordinated agents
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Society {
	/**
	 * Optional society name used by higher-level DSL examples.
	 */
	String name() default "";
}
