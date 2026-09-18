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
 * Marks a class as a role that can be played by agents.
 *
 * Roles encapsulate behavioral responsibilities and state relevant to role execution within a compartment.
 * A role represents a set of expected behaviors and responsibilities that an agent can assume.
 *
 * Example usage:
 * <pre>
 * {@code
 * @Role
 * public class CustomerRole {
 *     // role behavior and state
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Role {
}
