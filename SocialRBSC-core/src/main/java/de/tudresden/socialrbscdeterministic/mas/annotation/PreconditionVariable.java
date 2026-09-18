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
 * Field-level assertion validating variable state before use in computations.
 *
 * This annotation marks fields that have associated preconditions which must be satisfied
 * before the variable is used in any computations or operations. It enables runtime validation
 * of variable state and ensures data integrity during agent reasoning.
 *
 * Example usage:
 * <pre>
 * {@code
 * @PreconditionVariable
 * private double balance; // must be non-negative before use
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PreconditionVariable {
}
