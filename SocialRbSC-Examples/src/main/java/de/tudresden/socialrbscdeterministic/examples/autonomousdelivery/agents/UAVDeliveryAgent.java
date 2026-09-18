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
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.roles.UAVDeliveryAgentRole;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DeliveryAssignment;

/**
 * UAV Delivery Agent - Individual UAV social agent for delivery execution.
 * Executes delivery assignments with adaptive route planning.
 *
 * @author SocialRbSC Framework
 */
@SocialAgent(agentName = "UAVDeliveryAgent", addRole = true)
public class UAVDeliveryAgent {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * UAV delivery role
     */
    private UAVDeliveryAgentRole deliveryRole;

    /**
     * UAV identifier
     */
    private String uavId;

    private String uavState = "INITIALIZED";
    private int deliveriesCompleted = 0;

    //~ Constructor ------------------------------------------------------------------------------------------------------------------

    /**
     * Create UAV delivery agent with ID.
     * 
     * @param id UAV identifier
     */
    public UAVDeliveryAgent(String id) {
        this.uavId = id;
        initialize();
    }

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Initialize UAV agent.
     */
    public void initialize() {
        deliveryRole = new UAVDeliveryAgentRole();
        uavState = "READY_FOR_DISPATCH";
        System.out.println("[UAV_AGENT] UAV " + uavId + " initialized");
    }

    /**
     * Execute delivery assignment.
     * 
     * @param assignment The delivery assignment
     * @return true if successful
     */
    @RoleMethod
    public boolean executeDelivery(DeliveryAssignment assignment) {
        if (assignment == null) {
            System.out.println("[UAV_AGENT] Invalid assignment for " + uavId);
            return false;
        }

        uavState = "IN_FLIGHT";
        boolean result = deliveryRole.executeDelivery(assignment);

        if (result) {
            deliveriesCompleted++;
            uavState = "DELIVERY_COMPLETE";
        } else {
            uavState = "DELIVERY_FAILED";
        }

        return result;
    }

    /**
     * Get UAV identifier.
     * 
     * @return UAV ID
     */
    public String getUavId() {
        return uavId;
    }

    /**
     * Get current UAV state.
     * 
     * @return UAV state string
     */
    public String getUavState() {
        return uavState;
    }

    /**
     * Get current battery level.
     * 
     * @return Battery level percentage
     */
    public float getBatteryLevel() {
        return deliveryRole.batteryLevel;
    }
}
