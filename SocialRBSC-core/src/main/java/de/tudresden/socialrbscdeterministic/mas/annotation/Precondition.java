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
 * Method-level guard condition for controlled method execution.
 *
 * Preconditions specify conditions that must be satisfied before a method is allowed to execute.
 * The method executes only if the precondition evaluates to true before invocation. This enables
 * guarded execution and safe method invocation patterns.
 *
 * Example usage:
 * <pre>
 * {@code
 * @RoleMethod
 * @Precondition(internalparam = "balance > 0")
 * public void withdraw(double amount) {
 *     // method only executes if balance > 0
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Precondition {
    /**
     * Alias form for the precondition expression.
     */
    String value() default "";

    /**
     * The precondition expression that must evaluate to true for method execution.
     */
    String internalparam() default "no precondition";
}
