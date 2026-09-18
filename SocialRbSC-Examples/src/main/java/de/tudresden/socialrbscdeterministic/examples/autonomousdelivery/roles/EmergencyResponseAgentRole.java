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
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DisruptionEvent;

/**
 * Emergency Response Agent Role - Handles disruptions and emergency situations.
 * Responds to delivery disruptions with appropriate actions.
 *
 * @author SocialRbSC Framework
 */
@Role
public class EmergencyResponseAgentRole {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    // Instance fields for state tracking
    public int emergenciesHandled = 0;
    public String lastEmergencyType = "NONE";
    public String agentState = "STANDBY";

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Handle disruption event.
     * Determines and initiates appropriate response action.
     * 
     * @param disruptionEvent The disruption event to handle
     * @return true if handling successful
     */
    @RoleMethod
    @Precondition(internalparam = "disruptionEvent != null")
    public boolean handleDisruption(DisruptionEvent disruptionEvent) {
        lastEmergencyType = disruptionEvent.getEventType();
        emergenciesHandled++;

        String action = determineEmergencyAction(disruptionEvent);
        System.out.println("[EMERGENCY] " + lastEmergencyType +
            " - Action: " + action);

        return true;
    }

    /**
     * Determine appropriate emergency response action.
     * Evaluates disruption type and recommends corrective action.
     * 
     * @param event The disruption event
     * @return Recommended action string
     */
    private String determineEmergencyAction(DisruptionEvent event) {
        if (event.getEventType().equals("SEVERE_WEATHER")) {
            return "GROUND_ALL_FLIGHTS";
        } else if (event.getEventType().equals("NO_FLY_ZONE")) {
            return "REROUTE_OR_RESCHEDULE";
        } else if (event.getEventType().equals("UAV_MALFUNCTION")) {
            return "ACTIVATE_BACKUP_UAV";
        }
        return "ESCALATE_TO_HUMAN_OPERATOR";
    }
}
