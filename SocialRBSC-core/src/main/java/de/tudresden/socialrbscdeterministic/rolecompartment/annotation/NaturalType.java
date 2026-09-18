/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.rolecompartment.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Represents human or external actors integrated into the agent system.
 *
 * NaturalType enables mixed human-agent interaction by marking classes that represent
 * human participants or external entities that interact with the multi-agent system.
 * This allows seamless integration of human decision-making with autonomous agent behavior.
 *
 * Example usage:
 * <pre>
 * {@code
 * @NaturalType
 * public class HumanUser {
 *     // human user representation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NaturalType {
}
