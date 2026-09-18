///*
// * Copyright 2022-2024 Orcun Oruc
// *
// * You should have received a copy of a license with this program.
// *
// * You may not use, copy, modify, sublicense, or distribute the Program or any
// * portion of it, except as expressly provided under the given license.
// */
//package de.tudresden.socialrbscdeterministic.mas.annotation;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//
//// • +?g (test-goal addition)
//// • -?g (test-goal deletion)
//
///**
// * TODO DOCUMENT ME!
// *
// * @author $author$
// */
//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
//public @interface SocialAgentGoal {
//    // it takes the state value e.g. protected String text
//    // This can be string array and we can have multiple parameter
//    String goalparameter() default "sampleparam";
//
//    // It takes belief argument e.g. @goalcreationcondition(beliefs="hello")
//    String goalcreationcondition() default "samplecreation";
//
//    // it takes target condition argument e.g. @goalcreationcondition(parameters="text")
//    String goaltargetcondition() default "sampletarget";
//
//    // it takes drop condition argument e.g. @goaldropcondition(beliefs="garbages")
//    String goaldropcondition() default "sampledrop";
//
//    // it takes the goal finishing condition
//    String goalresultcondition() default "sampleresult";
//}

/*
 * Copyright 2022–2024 Orcun Oruc
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
 * Represents the agent's desired states or objectives.
 *
 * Agents use goals to specify what they wish to achieve. Goals guide the agent's deliberation
 * and action selection mechanisms. This annotation specifies the conditions and parameters
 * related to the creation, targeting, dropping, and completion of a goal.
 *
 * Usage examples:
 * - +?g (goal addition/creation)
 * - -?g (goal deletion/dropping)
 *
 * Each element corresponds to a specific aspect of the goal lifecycle:
 * - goalcreationcondition: When to create the goal
 * - goaltargetcondition: What the goal should achieve
 * - goaldropcondition: When to abandon the goal
 * - goalresultcondition: When the goal is satisfied
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SocialAgentGoal {

    /**
     * Defines goal parameters. These are typically state values (e.g., a variable like {@code protected String text}).
     * Can be used to define multiple parameters (e.g., {@code {"param1", "param2"}}).
     */
    String goalparameter() default "sampleparam";

    /**
     * Condition to trigger the creation of the goal based on beliefs.
     * Example: {@code @SocialAgentGoal(goalcreationcondition = "hello")}
     */
    String goalcreationcondition() default "samplecreation";

    /**
     * Condition that specifies the goal's target.
     * Often depends on parameters (e.g., {@code "text"}).
     */
    String goaltargetcondition() default "sampletarget";

    /**
     * Condition to drop the goal based on beliefs.
     * Example: {@code "garbages"}
     */
    String goaldropcondition() default "sampledrop";

    /**
     * Condition that determines when the goal is considered successfully completed.
     */
    String goalresultcondition() default "sampleresult";
}

