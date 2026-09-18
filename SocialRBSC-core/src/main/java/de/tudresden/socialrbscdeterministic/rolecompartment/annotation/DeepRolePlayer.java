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
 * Marks a role with complex nested behavioral patterns and multiple abstraction levels.
 *
 * DeepRolePlayer enables sophisticated role hierarchies by allowing roles to contain
 * other roles with complex behavioral compositions. This supports deep nesting of role
 * structures for modeling intricate agent interactions and responsibilities.
 *
 * Example usage:
 * <pre>
 * {@code
 * @DeepRolePlayer
 * @Role
 * public class ManagerRole {
 *     // manager-specific behavior with nested subordinate roles
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DeepRolePlayer {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * Class<? extends Compartment> playedBy: This field, playedBy, is a placeholder for a class that must extend Compartment. An
     * annotation field like this typically defines a configurable parameter for the annotation; here, it would denote a
     * "compartment" that this role player is associated with. However, the syntax used here (an instance field assignment within
     * an annotation) is incorrect for an annotation. Instead, it should be defined as a method like this: Class<? extends
     * Compartment> playedBy();
     */
    Class<? extends Compartment> playedBy = null;
}
