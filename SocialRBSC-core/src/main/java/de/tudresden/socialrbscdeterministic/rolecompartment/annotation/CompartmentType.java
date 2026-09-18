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
 * Enumeration specifying compartment semantics and types.
 *
 * This annotation indicates the semantic type of a compartment, which determines how roles
 * within the compartment are grouped and isolated. Supported types include:
 * <ul>
 *   <li><b>CONTEXTUAL:</b> Context-based compartments for situational role boundaries</li>
 *   <li><b>ORGANIZATIONAL:</b> Structure-based compartments for hierarchical organization</li>
 *   <li><b>CONTRACTUAL:</b> Agreement-based compartments for contractual obligations and boundaries</li>
 * </ul>
 *
 * @author Orcun Oruc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CompartmentType {
    /**
     * The type of compartment semantics: CONTEXTUAL, ORGANIZATIONAL, or CONTRACTUAL.
     */
    String value() default "CONTEXTUAL";
}
