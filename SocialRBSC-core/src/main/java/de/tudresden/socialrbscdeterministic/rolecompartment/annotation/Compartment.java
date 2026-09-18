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
 * Defines a contextual boundary for roles. Specifies the CompartmentType for role grouping and isolation.
 *
 * Compartments represent a bounded context within which roles can be played by agents. They provide
 * a scope for organizing behavioral responsibilities and state relevant to role execution.
 *
 * Example usage:
 * <pre>
 * {@code
 * @Compartment(bindingRole = "Transaction")
 * public interface BankCompartment {
 *     // compartment definition
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Compartment {
    /**
     * Identity of the context in the smart contract (Solidity) - corresponds to an interface name.
     */
    String bindingRole() default "";

    /**
     * Enables activation logic for the compartment.
     */
    boolean activateCompartment() default false;

    /**
     * Enables deactivation logic for the compartment.
     */
    boolean deactivateCompartment() default false;
}
