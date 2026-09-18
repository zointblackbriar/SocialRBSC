/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.agents;

import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.roles.MedicalSupplyCoordinatorRole;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.MedicalSupplyRequest;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DeliveryAssignment;

/**
 * Medical Supply Coordinator Agent - Central decision-making social agent.
 * Orchestrates fleet coordination for medical supply delivery.
 *
 * @author SocialRbSC Framework
 */
@SocialAgent(agentName = "MedicalSupplyCoordinator", addRole = true)
public class MedicalSupplyCoordinatorAgent {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * Role instance for coordination
     */
    private MedicalSupplyCoordinatorRole coordinatorRole;

    private String coordinatorState = "INITIALIZED";
    private int totalCoordinationSessions = 0;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Initialize coordinator agent.
     */
    public void initialize() {
        coordinatorRole = new MedicalSupplyCoordinatorRole();
        coordinatorState = "READY";
        System.out.println("[AGENT] Medical Supply Coordinator Agent initialized");
    }

    /**
     * Coordinate fleet for given supply requests.
     * 
     * @param requests Array of medical supply requests
     * @return Array of delivery assignments
     */
    @RoleMethod
    public DeliveryAssignment[] coordinateFleet(MedicalSupplyRequest[] requests) {
        if (requests == null || requests.length == 0) {
            System.out.println("[AGENT] No supply requests to coordinate");
            return null;
        }

        totalCoordinationSessions++;
        coordinatorState = "COORDINATING";

        DeliveryAssignment[] assignments = coordinatorRole.coordinateDeliveryPlan(requests);

        if (assignments != null && assignments.length > 0) {
            coordinatorRole.optimizeDeliverySequence(assignments);
            coordinatorState = "OPTIMIZATION_COMPLETE";
        }

        return assignments;
    }

    /**
     * Get current coordinator state.
     * 
     * @return Coordinator state string
     */
    public String getCoordinatorState() {
        return coordinatorState;
    }
}
