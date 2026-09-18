/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.supplychain;

import de.tudresden.socialrbscdeterministic.mas.annotation.Society;

/**
 * Supply Chain Society definition.
 * Defines the society structure with roles and deliberation cycles.
 *
 * @author Generated from Supply Chain Use Case
 */
@Society
public interface HumanOrganization {
    /**
     * Society name property.
     */
    String SOCIETY_NAME = "SupplyChainSociety";
    
    /**
     * Define the society name.
     *
     * @param name the name to assign to the society
     */
    void defineSocietyName(String name);
}
