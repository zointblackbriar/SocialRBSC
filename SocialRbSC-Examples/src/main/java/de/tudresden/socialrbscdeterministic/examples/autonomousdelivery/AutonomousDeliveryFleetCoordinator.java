/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery;

import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.agents.MedicalSupplyCoordinatorAgent;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.agents.UAVDeliveryAgent;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.agents.EmergencyResponseAgent;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.MedicalSupplyRequest;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DeliveryAssignment;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DisruptionEvent;

/**
 * Autonomous Delivery Fleet Coordinator - System coordinator social agent.
 * Ensures timely medical supply delivery through autonomous UAV coordination.
 * 
 * This example demonstrates:
 * - Multi-agent coordination for logistics
 * - Real-time route optimization
 * - Emergency response handling
 * - State management and deliberation cycles
 * - Integration with blockchain smart contracts via Solidity generation
 *
 * @author SocialRbSC Framework
 */
@SocialAgent(agentName = "AutonomousDeliveryFleetCoordinator", addRole = true)
public class AutonomousDeliveryFleetCoordinator {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * Medical supply coordinator agent
     */
    private MedicalSupplyCoordinatorAgent coordinator;

    /**
     * Fleet of UAV agents
     */
    private UAVDeliveryAgent[] uavFleet;

    /**
     * Emergency response agent
     */
    private EmergencyResponseAgent emergencyResponse;

    private int totalSystemUptime = 0;
    private String systemState = "INITIALIZED";

    //~ Constructor ------------------------------------------------------------------------------------------------------------------

    /**
     * Initialize the autonomous delivery fleet coordinator.
     */
    public AutonomousDeliveryFleetCoordinator() {
        initialize();
    }

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Initialize all fleet components.
     */
    public void initialize() {
        System.out.println("[SOCIAL_AGENT] Autonomous Delivery Fleet Coordinator initializing...");

        // Initialize coordinator
        coordinator = new MedicalSupplyCoordinatorAgent();
        coordinator.initialize();

        // Initialize UAV fleet
        uavFleet = new UAVDeliveryAgent[5];
        for (int i = 0; i < uavFleet.length; i++) {
            uavFleet[i] = new UAVDeliveryAgent("UAV-" + (i + 1));
        }

        // Initialize emergency response
        emergencyResponse = new EmergencyResponseAgent();
        emergencyResponse.initialize();

        systemState = "READY";
        System.out.println("[SOCIAL_AGENT] Fleet coordination system ready with " + uavFleet.length + " UAVs");
    }

    /**
     * Coordinate fleet for medical supply requests.
     * This is the main orchestration method.
     * 
     * @param requests Array of medical supply requests
     */
    public void coordinateFleet(MedicalSupplyRequest[] requests) {
        if (requests == null || requests.length == 0) {
            System.out.println("[SOCIAL_AGENT] No supply requests received");
            return;
        }

        System.out.println("[SOCIAL_AGENT] Processing " + requests.length + " supply requests");
        systemState = "COORDINATING_FLEET";

        // Get delivery assignments from coordinator
        DeliveryAssignment[] assignments = coordinator.coordinateFleet(requests);

        if (assignments != null && assignments.length > 0) {
            // Dispatch deliveries to UAV fleet
            dispatchDeliveries(assignments);
            systemState = "FLEET_DISPATCHED";
        } else {
            systemState = "COORDINATION_FAILED";
        }
    }

    /**
     * Dispatch delivery assignments to UAV fleet.
     * 
     * @param assignments Array of delivery assignments
     */
    private void dispatchDeliveries(DeliveryAssignment[] assignments) {
        System.out.println("[SOCIAL_AGENT] Dispatching " + assignments.length + " deliveries to UAV fleet");

        for (int i = 0; i < assignments.length && i < uavFleet.length; i++) {
            DeliveryAssignment assignment = assignments[i];
            UAVDeliveryAgent uav = uavFleet[i];

            System.out.println("[SOCIAL_AGENT] Assigning delivery to " + uav.getUavId());
            uav.executeDelivery(assignment);
        }
    }

    /**
     * Handle emergency disruption event.
     * 
     * @param event The disruption event
     * @return true if handled successfully
     */
    public boolean handleEmergency(DisruptionEvent event) {
        System.out.println("[SOCIAL_AGENT] Emergency event received: " + event.getEventType());
        systemState = "HANDLING_EMERGENCY";

        boolean handled = emergencyResponse.handleDisruption(event);

        if (handled) {
            systemState = "EMERGENCY_RESOLVED";
        } else {
            systemState = "EMERGENCY_ESCALATED";
        }

        return handled;
    }

    /**
     * Get current system state.
     * 
     * @return System state string
     */
    public String getSystemState() {
        return systemState;
    }

    /**
     * Get fleet statistics.
     * 
     * @return String with fleet information
     */
    public String getFleetStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("Fleet Statistics:\n");
        stats.append("  Total UAVs: ").append(uavFleet.length).append("\n");
        stats.append("  System State: ").append(systemState).append("\n");
        stats.append("  Coordinator State: ").append(coordinator.getCoordinatorState()).append("\n");
        stats.append("  Emergency State: ").append(emergencyResponse.getEmergencyState()).append("\n");

        for (UAVDeliveryAgent uav : uavFleet) {
            stats.append("  ").append(uav.getUavId())
                .append(" - State: ").append(uav.getUavState())
                .append(", Battery: ").append(String.format("%.1f", uav.getBatteryLevel())).append("%\n");
        }

        return stats.toString();
    }

    /**
     * Example main method demonstrating usage.
     * 
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println("=== Autonomous Delivery Fleet Management Example ===\n");

        // Create coordinator
        AutonomousDeliveryFleetCoordinator fleetCoordinator = new AutonomousDeliveryFleetCoordinator();

        // Create sample supply requests
        MedicalSupplyRequest[] requests = new MedicalSupplyRequest[3];
        requests[0] = new MedicalSupplyRequest("Blood Supplies", "Hospital A", "CRITICAL");
        requests[1] = new MedicalSupplyRequest("Medicine Kit", "Clinic B", "HIGH");
        requests[2] = new MedicalSupplyRequest("Vaccines", "Community Center", "MEDIUM");

        System.out.println("\n=== Coordinating Deliveries ===\n");
        fleetCoordinator.coordinateFleet(requests);

        System.out.println("\n=== Handling Emergency ===\n");
        DisruptionEvent emergency = new DisruptionEvent("SEVERE_WEATHER");
        fleetCoordinator.handleEmergency(emergency);

        System.out.println("\n=== Fleet Statistics ===\n");
        System.out.println(fleetCoordinator.getFleetStatistics());
    }
}
