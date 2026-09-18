/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.compartments;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;

/**
 * Fleet Coordination Compartment - Represents the compartment containing fleet coordination logic.
 * This compartment defines the context in which fleet coordination agents operate.
 *
 * @author SocialRbSC Framework
 */
@Compartment
public interface FleetCoordinationCompartment {

    /**
     * Initialization logic for compartment
     */
    void initialize();

    /**
     * Run coordination cycle
     */
    void runCoordinationCycle();
}
