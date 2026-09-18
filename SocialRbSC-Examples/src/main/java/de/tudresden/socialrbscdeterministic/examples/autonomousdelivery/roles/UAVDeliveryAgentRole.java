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
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DeliveryAssignment;

/**
 * UAV (Drone) Agent Role - Individual delivery execution role.
 * Executes assigned medical supply delivery with dynamic route adaptation.
 *
 * @author SocialRbSC Framework
 */
@Role
public class UAVDeliveryAgentRole {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    // Instance fields for state tracking
    public float batteryLevel = 85.0f;
    public float payloadCapacity = 10.0f;
    public String currentRoute = "NONE";
    public String agentState = "READY_FOR_DISPATCH";

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Execute delivery assignment.
     * Adaptively handles environmental conditions and reroutes if necessary.
     * 
     * @param assignment The delivery assignment
     * @return true if delivery execution successful
     */
    @RoleMethod
    public boolean executeDelivery(DeliveryAssignment assignment) {
        currentRoute = assignment.getDestination();

        // Simulate route execution with dynamic adaptation
        boolean routeCheckResult = checkEnvironmentalConditions();

        if (!routeCheckResult) {
            System.out.println("[UAV] Adverse weather detected. Initiating re-route protocol");
            return recalculateRoute(assignment);
        }

        batteryLevel -= 15.0f; // Battery consumption
        System.out.println("[UAV] Delivery to " + assignment.getDestination() +
            " in progress. Battery: " + batteryLevel + "%");

        return true;
    }

    /**
     * Recalculate route during deliberation cycle.
     * Checks airspace, battery reserves, and identifies alternate landing zones.
     * 
     * @param assignment The delivery assignment
     * @return true if alternate route found
     */
    @RoleMethod
    public boolean recalculateRoute(DeliveryAssignment assignment) {
        System.out.println("[UAV_DELIBERATION] Evaluating alternate routes");
        System.out.println("  - Checking airspace restrictions");
        System.out.println("  - Calculating battery reserves");
        System.out.println("  - Identifying alternate landing zones");

        // Belief update about environmental conditions
        if (batteryLevel > 30.0f) {
            System.out.println("[UAV_BELIEF_UPDATE] Sufficient battery for alternate route");
            currentRoute = "ALTERNATE_" + assignment.getDestination();
            return true;
        }
        return false;
    }

    /**
     * Check environmental conditions for flight.
     * 
     * @return true if conditions are favorable
     */
    private boolean checkEnvironmentalConditions() {
        boolean favorableWeather = Math.random() > 0.3; // 70% success rate
        return favorableWeather;
    }

    /**
     * Update battery status.
     * 
     * @param consumption Battery consumption amount
     * @return true if battery remaining
     */
    private boolean updateBatteryStatus(float consumption) {
        batteryLevel -= consumption;
        return batteryLevel > 0;
    }
}
