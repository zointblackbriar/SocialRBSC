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
 * Marks methods callable within a role context.
 *
 * Methods annotated with @RoleMethod are intended to be executed as part of role behavior.
 * Typically combined with @Precondition for guarded method execution to ensure preconditions
 * are satisfied before method invocation.
 *
 * Example usage:
 * <pre>
 * {@code
 * @RoleMethod
 * @Precondition("balance > 0")
 * public void withdraw(double amount) {
 *     // method implementation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RoleMethod {
}
