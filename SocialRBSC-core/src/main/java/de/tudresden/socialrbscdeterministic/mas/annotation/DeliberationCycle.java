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
 * Triggers the BDI (Belief-Desire-Intention) reasoning cycle.
 *
 * This annotation enables autonomous agent deliberation and planning by triggering the core
 * reasoning cycle. The BDI model allows agents to reason about their beliefs (knowledge),
 * desires (goals), and intentions (commitments to action), resulting in autonomous behavior.
 *
 * The deliberation cycle typically:
 * 1. Updates beliefs based on perceptions
 * 2. Determines applicable goals
 * 3. Selects intention (plan) to pursue
 * 4. Executes actions
 *
 * Example usage:
 * <pre>
 * {@code
 * @DeliberationCycle
 * public void reasoningCycle() {
 *     // BDI reasoning implementation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DeliberationCycle {
}
