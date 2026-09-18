/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.compartments;

import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentGoal;


/**
 * SocialRbSC Examples Application - Main entry point for demonstrating SocialRbSC framework capabilities.
 * This application demonstrates the creation and interaction of social agents, compartments, and roles.
 *
 * @author SocialRbSC Framework
 */
@SocialAgentGoal
public class ExamplesApp {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Main method - Entry point of the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("SocialRbSC Examples Application");
        System.out.println("====================================");
        
        // Initialize the supply chain compartment
        SupplyChainCompartment compartment = new SupplyChainCompartment();
        compartment.initialize();
        
        System.out.println("Compartment initialized successfully!");
        System.out.println("Ready for transaction execution.");
    }

}
