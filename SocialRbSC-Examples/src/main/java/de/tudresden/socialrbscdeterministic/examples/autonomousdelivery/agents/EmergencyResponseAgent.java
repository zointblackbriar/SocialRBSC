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
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.roles.EmergencyResponseAgentRole;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DisruptionEvent;

/**
 * Emergency Response Agent - Handles delivery disruptions and emergencies.
 * Responds to system failures and adverse conditions.
 *
 * @author SocialRbSC Framework
 */
@SocialAgent(agentName = "EmergencyResponseAgent", addRole = true)
public class EmergencyResponseAgent {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * Emergency response role
     */
    private EmergencyResponseAgentRole responseRole;

    private String emergencyState = "STANDBY";
    private int disruptionsHandled = 0;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Initialize emergency response agent.
     */
    public void initialize() {
        responseRole = new EmergencyResponseAgentRole();
        emergencyState = "MONITORING";
        System.out.println("[EMERGENCY_AGENT] Emergency Response Agent initialized");
    }

    /**
     * Handle disruption event.
     * 
     * @param event The disruption event
     * @return true if handling successful
     */
    @RoleMethod
    public boolean handleDisruption(DisruptionEvent event) {
        if (event == null) {
            System.out.println("[EMERGENCY_AGENT] Invalid disruption event");
            return false;
        }

        emergencyState = "RESPONDING";
        disruptionsHandled++;

        boolean result = responseRole.handleDisruption(event);

        if (result) {
            emergencyState = "RESPONSE_COMPLETE";
        } else {
            emergencyState = "RESPONSE_PENDING";
        }

        return result;
    }

    /**
     * Get current emergency state.
     * 
     * @return Emergency state string
     */
    public String getEmergencyState() {
        return emergencyState;
    }

    /**
     * Get total disruptions handled.
     * 
     * @return Count of disruptions handled
     */
    public int getDisruptionsHandled() {
        return disruptionsHandled;
    }
}
