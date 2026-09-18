/*
 * Copyright 2022-2025 Orcun Oruc
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
 * Marks executable actions within plans or roles.
 *
 * Actions represent concrete executable operations that agents can perform to achieve their goals.
 * Actions are typically part of plans and are executed as part of the agent's behavior to
 * modify the environment or internal state.
 *
 * Example usage:
 * <pre>
 * {@code
 * @Action("Fill the pot with water")
 * public void fillPotWithWater() {
 *     // action implementation
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /**
     * The description or name of the action.
     */
    String value();
}
