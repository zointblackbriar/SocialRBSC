/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You should not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.compartments;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;

/**
 * Emergency Response Compartment - Handles emergency situations and disruptions.
 * This compartment manages the context for emergency response agents.
 *
 * @author SocialRbSC Framework
 */
@Compartment
public interface EmergencyResponseCompartment {

    /**
     * Initialize emergency response
     */
    void initialize();

    /**
     * Handle emergency event
     */
    void handleEmergencyEvent();
}
