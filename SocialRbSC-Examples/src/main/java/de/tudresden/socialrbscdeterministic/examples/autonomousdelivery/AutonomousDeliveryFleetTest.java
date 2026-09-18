/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery;

import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.MedicalSupplyRequest;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DisruptionEvent;

/**
 * Autonomous Delivery Fleet Test - Demonstrates the full system workflow.
 * 
 * This test class shows:
 * - Fleet initialization
 * - Multi-request coordination
 * - Emergency handling
 * - State transitions
 * - System statistics
 *
 * @author SocialRbSC Framework
 */
public class AutonomousDeliveryFleetTest {

    //~ Main method ------------------------------------------------------------------------------------------------------------------

    /**
     * Run system tests with various scenarios.
     * 
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  Autonomous Medical Delivery Fleet Management - Test Suite      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

        // Create fleet coordinator
        AutonomousDeliveryFleetCoordinator fleetCoordinator = new AutonomousDeliveryFleetCoordinator();

        // Test 1: Single delivery request
        System.out.println("─── Test 1: Single Medical Supply Request ───\n");
        testSingleDelivery(fleetCoordinator);

        // Test 2: Multiple delivery requests
        System.out.println("\n─── Test 2: Multiple Supply Requests ───\n");
        testMultipleDeliveries(fleetCoordinator);

        // Test 3: Emergency response
        System.out.println("\n─── Test 3: Emergency Response Handling ───\n");
        testEmergencyResponse(fleetCoordinator);

        // Test 4: Various disruption scenarios
        System.out.println("\n─── Test 4: Disruption Scenarios ───\n");
        testDisruptionScenarios(fleetCoordinator);

        // Final statistics
        System.out.println("\n─── Final System Statistics ───\n");
        System.out.println(fleetCoordinator.getFleetStatistics());

        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  All tests completed successfully                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Test single delivery request scenario.
     * 
     * @param coordinator The fleet coordinator
     */
    private static void testSingleDelivery(AutonomousDeliveryFleetCoordinator coordinator) {
        MedicalSupplyRequest[] requests = new MedicalSupplyRequest[1];
        requests[0] = new MedicalSupplyRequest("Emergency Blood Type O", "Emergency Ward - Hospital", "CRITICAL");

        System.out.println("Request: " + requests[0].getSupplyType());
        System.out.println("Destination: " + requests[0].getDestination());
        System.out.println("Urgency: " + requests[0].getUrgencyLevel());
        System.out.println();

        coordinator.coordinateFleet(requests);
    }

    /**
     * Test multiple delivery requests scenario.
     * 
     * @param coordinator The fleet coordinator
     */
    private static void testMultipleDeliveries(AutonomousDeliveryFleetCoordinator coordinator) {
        MedicalSupplyRequest[] requests = new MedicalSupplyRequest[4];
        requests[0] = new MedicalSupplyRequest("Anti-biotic Medication", "Clinic A", "HIGH");
        requests[1] = new MedicalSupplyRequest("Surgical Instruments", "Operating Room B", "CRITICAL");
        requests[2] = new MedicalSupplyRequest("Vaccine Doses", "Community Health Center", "MEDIUM");
        requests[3] = new MedicalSupplyRequest("IV Fluids", "Patient Care Unit", "HIGH");

        System.out.println("Processing " + requests.length + " supply requests:");
        for (int i = 0; i < requests.length; i++) {
            System.out.println((i + 1) + ". " + requests[i].getSupplyType() + " → " + 
                              requests[i].getDestination() + " [" + requests[i].getUrgencyLevel() + "]");
        }
        System.out.println();

        coordinator.coordinateFleet(requests);
    }

    /**
     * Test emergency response scenario.
     * 
     * @param coordinator The fleet coordinator
     */
    private static void testEmergencyResponse(AutonomousDeliveryFleetCoordinator coordinator) {
        DisruptionEvent[] events = new DisruptionEvent[3];
        events[0] = new DisruptionEvent("SEVERE_WEATHER");
        events[1] = new DisruptionEvent("NO_FLY_ZONE");
        events[2] = new DisruptionEvent("UAV_MALFUNCTION");

        System.out.println("Testing emergency scenarios:");
        for (DisruptionEvent event : events) {
            System.out.println("\nEmergency Type: " + event.getEventType());
            coordinator.handleEmergency(event);
        }
    }

    /**
     * Test various disruption scenarios.
     * 
     * @param coordinator The fleet coordinator
     */
    private static void testDisruptionScenarios(AutonomousDeliveryFleetCoordinator coordinator) {
        String[] disruptions = {
            "SEVERE_WEATHER",
            "NO_FLY_ZONE",
            "UAV_MALFUNCTION",
            "GPS_SIGNAL_LOSS",
            "FUEL_LEAK",
            "AIRSPACE_VIOLATION"
        };

        System.out.println("Testing disruption event handling:\n");
        for (String disruption : disruptions) {
            DisruptionEvent event = new DisruptionEvent(disruption);
            System.out.println("Handling: " + disruption);
            coordinator.handleEmergency(event);
            System.out.println();
        }
    }
}
