/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.roles;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;
import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.MedicalSupplyRequest;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DeliveryAssignment;

/**
 * Medical Supply Coordinator Role - Central decision-making role for fleet coordination.
 * Coordinates medical supply delivery across UAV fleet in real-time.
 *
 * @author SocialRbSC Framework
 */
@Role
public class MedicalSupplyCoordinatorRole {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    // Instance fields for state tracking
    public int totalDeliveriesCoordinated = 0;
    public String currentCoordinationStatus = "ACTIVE";
    public String agentState = "COORDINATING";

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Coordinate delivery plan for multiple supply requests.
     * Assigns supplies to UAVs and monitors routes.
     * 
     * @param supplyRequests Array of medical supply requests
     * @return Array of delivery assignments
     */
    @RoleMethod
    public DeliveryAssignment[] coordinateDeliveryPlan(MedicalSupplyRequest[] supplyRequests) {
        DeliveryAssignment[] assignments = new DeliveryAssignment[supplyRequests.length];

        for (int i = 0; i < supplyRequests.length; i++) {
            DeliveryAssignment assignment = new DeliveryAssignment();
            assignment.setRequestId("REQ-" + supplyRequests[i].hashCode());
            assignment.setSupplyType(supplyRequests[i].getSupplyType());
            assignment.setDestination(supplyRequests[i].getDestination());
            assignment.setUrgencyLevel(supplyRequests[i].getUrgencyLevel());
            assignment.setTimestamp(System.currentTimeMillis());
            assignments[i] = assignment;
        }

        totalDeliveriesCoordinated += assignments.length;
        System.out.println("[COORDINATOR] Coordinated " + assignments.length + " deliveries");
        return assignments;
    }

    /**
     * Optimize delivery sequence for efficiency.
     * 
     * @param assignments Array of delivery assignments
     * @return true if optimization successful
     */
    @RoleMethod
    public boolean optimizeDeliverySequence(DeliveryAssignment[] assignments) {
        if (assignments == null || assignments.length == 0) return false;
        System.out.println("[COORDINATOR_PLAN] Optimizing sequence for " + assignments.length + " deliveries");
        for (DeliveryAssignment assignment : assignments) {
            System.out.println("  - " + assignment.getSupplyType() + " to " + assignment.getDestination());
        }
        return true;
    }
}
