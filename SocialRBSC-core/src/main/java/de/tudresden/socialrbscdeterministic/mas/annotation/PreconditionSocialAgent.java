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
 * Agent-level precondition governing deliberation cycle initiation and continuation.
 *
 * A precondition is a logical condition that evaluates to true before an action is executed,
 * a plan is adopted, or a role is taken. PreconditionSocialAgent governs the agent's deliberation
 * cycle at a higher level, determining whether the agent should continue reasoning and planning.
 *
 * If the precondition is not met, the action / role is skipped or postponed.
 *
 * Examples in social agent settings:
 * <ul>
 *   <li><b>Conversational agent:</b> Precondition: user_mood == 'friendly' AND topic != 'weather'</li>
 *   <li><b>Role-based agent:</b> Precondition: persuasion_skill >= 0.8 AND current_role != 'Observer'</li>
 *   <li><b>Trading agent:</b> Precondition: has_surplus_resources == True AND trust_level > 0.5</li>
 * </ul>
 *
 * Why preconditions matter:
 * - Consistency: Ensures agents don't execute invalid actions
 * - Safety: Prevents unsafe state transitions
 * - Efficiency: Avoids wasted computation on impossible objectives
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PreconditionSocialAgent {
}
