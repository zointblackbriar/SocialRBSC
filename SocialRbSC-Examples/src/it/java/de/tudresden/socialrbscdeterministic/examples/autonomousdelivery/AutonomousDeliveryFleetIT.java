package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.agents.EmergencyResponseAgent;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.agents.MedicalSupplyCoordinatorAgent;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.agents.UAVDeliveryAgent;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DisruptionEvent;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.MedicalSupplyRequest;

public class AutonomousDeliveryFleetIT {

    private AutonomousDeliveryFleetCoordinator fleetCoordinator;

    @Before
    public void setUp() {
        fleetCoordinator = new AutonomousDeliveryFleetCoordinator();
    }

    @Test
    public void initializationBuildsReadyFleet() {
        MedicalSupplyCoordinatorAgent coordinatorAgent = extractField(
                fleetCoordinator,
                "coordinator",
                MedicalSupplyCoordinatorAgent.class);
        EmergencyResponseAgent emergencyAgent = extractField(
                fleetCoordinator,
                "emergencyResponse",
                EmergencyResponseAgent.class);
        UAVDeliveryAgent[] uavFleet = extractField(fleetCoordinator, "uavFleet", UAVDeliveryAgent[].class);

        assertEquals("READY", fleetCoordinator.getSystemState());
        assertEquals("READY", coordinatorAgent.getCoordinatorState());
        assertEquals("MONITORING", emergencyAgent.getEmergencyState());
        assertNotNull(uavFleet);
        assertEquals("The autonomous fleet should contain five UAV agents", 5, uavFleet.length);
        assertTrue(IntStream.range(0, uavFleet.length)
                .allMatch(index -> "READY_FOR_DISPATCH".equals(uavFleet[index].getUavState())));

        String statistics = fleetCoordinator.getFleetStatistics();
        assertTrue(statistics.contains("Total UAVs: 5"));
        assertTrue(statistics.contains("System State: READY"));
        assertTrue(statistics.contains("Coordinator State: READY"));
        assertTrue(statistics.contains("Emergency State: MONITORING"));
    }

    @Test
    public void singleDeliveryRequestDispatchesFleet() {
        MedicalSupplyRequest[] requests = {
                new MedicalSupplyRequest("Emergency Blood Type O", "Emergency Ward - Hospital", "CRITICAL")
        };

        fleetCoordinator.coordinateFleet(requests);

        MedicalSupplyCoordinatorAgent coordinatorAgent = extractField(
                fleetCoordinator,
                "coordinator",
                MedicalSupplyCoordinatorAgent.class);
        UAVDeliveryAgent[] uavFleet = extractField(fleetCoordinator, "uavFleet", UAVDeliveryAgent[].class);

        assertEquals("FLEET_DISPATCHED", fleetCoordinator.getSystemState());
        assertEquals("OPTIMIZATION_COMPLETE", coordinatorAgent.getCoordinatorState());
        assertEquals("DELIVERY_COMPLETE", uavFleet[0].getUavState());
        assertTrue(uavFleet[0].getBatteryLevel() >= 70.0f && uavFleet[0].getBatteryLevel() <= 85.0f);
        assertEquals("Unused UAVs should remain ready", "READY_FOR_DISPATCH", uavFleet[1].getUavState());
    }

    @Test
    public void multiRequestCoordinationUsesAvailableFleetMembers() {
        MedicalSupplyRequest[] requests = new MedicalSupplyRequest[7];
        for (int index = 0; index < requests.length; index++) {
            requests[index] = new MedicalSupplyRequest(
                    "Supply-" + index,
                    "Destination-" + index,
                    index % 2 == 0 ? "HIGH" : "MEDIUM");
        }

        fleetCoordinator.coordinateFleet(requests);

        UAVDeliveryAgent[] uavFleet = extractField(fleetCoordinator, "uavFleet", UAVDeliveryAgent[].class);
        long completedDeliveries = IntStream.range(0, uavFleet.length)
                .filter(index -> "DELIVERY_COMPLETE".equals(uavFleet[index].getUavState()))
                .count();

        assertEquals("FLEET_DISPATCHED", fleetCoordinator.getSystemState());
        assertEquals("Only the five available UAVs should be dispatched", 5L, completedDeliveries);
    }

    @Test
    public void emergencyHandlingResolvesDisruptions() {
        DisruptionEvent event = new DisruptionEvent("NO_FLY_ZONE");

        boolean handled = fleetCoordinator.handleEmergency(event);

        EmergencyResponseAgent emergencyAgent = extractField(
                fleetCoordinator,
                "emergencyResponse",
                EmergencyResponseAgent.class);

        assertTrue(handled);
        assertEquals("EMERGENCY_RESOLVED", fleetCoordinator.getSystemState());
        assertEquals("RESPONSE_COMPLETE", emergencyAgent.getEmergencyState());
        assertEquals(1, emergencyAgent.getDisruptionsHandled());
    }

    @Test
    public void emptyRequestBatchKeepsSystemReady() {
        fleetCoordinator.coordinateFleet(new MedicalSupplyRequest[0]);

        EmergencyResponseAgent emergencyAgent = extractField(
                fleetCoordinator,
                "emergencyResponse",
                EmergencyResponseAgent.class);
        MedicalSupplyCoordinatorAgent coordinatorAgent = extractField(
                fleetCoordinator,
                "coordinator",
                MedicalSupplyCoordinatorAgent.class);

        assertEquals("READY", fleetCoordinator.getSystemState());
        assertEquals("READY", coordinatorAgent.getCoordinatorState());
        assertEquals("MONITORING", emergencyAgent.getEmergencyState());
        assertEquals(0, emergencyAgent.getDisruptionsHandled());
    }

    @Test
    public void generatedContractsExistForAutonomousDeliveryScenario() {
        Path contractsDir = Paths.get("generatedsmartcontract", "contracts");
        assertTrue("generatedsmartcontract/contracts directory should exist", Files.isDirectory(contractsDir));

        assertGeneratedContractExists(contractsDir, "AutonomousDeliveryFleetCoordinator.sol");
        assertGeneratedContractExists(contractsDir, "DeliveryFleetSociety.sol");
        assertGeneratedContractExists(contractsDir, "EmergencyResponseAgent.sol");
        assertGeneratedContractExists(contractsDir, "EmergencyResponseAgentRole.sol");
        assertGeneratedContractExists(contractsDir, "EmergencyResponseCompartment.sol");
        assertGeneratedContractExists(contractsDir, "FleetCoordinationCompartment.sol");
        assertGeneratedContractExists(contractsDir, "MedicalSupplyCoordinatorAgent.sol");
        assertGeneratedContractExists(contractsDir, "MedicalSupplyCoordinatorRole.sol");
        assertGeneratedContractExists(contractsDir, "RouteOptimizationCompartment.sol");
        assertGeneratedContractExists(contractsDir, "UAVDeliveryAgent.sol");
        assertGeneratedContractExists(contractsDir, "UAVDeliveryAgentRole.sol");
        assertGeneratedContractExists(contractsDir, "DeployExampleOrchestrator.sol");
    }

    private static void assertGeneratedContractExists(Path contractsDir, String fileName) {
        assertTrue("Expected generated contract not found: " + fileName, Files.exists(contractsDir.resolve(fileName)));
    }

    private static <T> T extractField(Object target, String fieldName, Class<T> fieldType) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return fieldType.cast(field.get(target));
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError(
                    "Unable to access field '" + fieldName + "' on " + target.getClass().getName(),
                    exception);
        }
    }
}

