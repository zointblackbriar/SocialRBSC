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
 * Route Optimization Compartment - Handles route planning and optimization for UAV deliveries.
 * This compartment manages the context for route optimization agents.
 *
 * @author SocialRbSC Framework
 */
@Compartment
public interface RouteOptimizationCompartment {

    /**
     * Initialize route optimization
     */
    void initialize();

    /**
     * Run route optimization cycle
     */
    void runOptimizationCycle();
}
