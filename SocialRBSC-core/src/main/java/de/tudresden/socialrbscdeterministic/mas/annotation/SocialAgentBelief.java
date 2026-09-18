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
 * Marks agent knowledge or beliefs about environment state.
 *
 * Beliefs represent the agent's understanding and knowledge about the world state. They are
 * updated during deliberation cycles to reflect perception changes and new information.
 * Beliefs form the basis of the agent's reasoning about its environment.
 *
 * Example usage:
 * <pre>
 * {@code
 * @SocialAgentBelief
 * private String weatherCondition;
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SocialAgentBelief {
    // • +b (belief addition)
    // • -b (belief deletion)
}
