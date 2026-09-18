package de.tudresden.socialrbscdeterministic.examples.medsupply;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MedicalSupplyDeliverySystemTest {

    @Test
    @DisplayName("Doctor agent should create supply requests and remember requested items")
    void doctorAgentCreatesSupplyRequest() {
        MedicalSupplyDeliverySystem.HealthcareSociety.DoctorAgent doctorAgent =
                new MedicalSupplyDeliverySystem.HealthcareSociety.DoctorAgent();
        String[] requestedItems = {"gloves", "insulin"};

        MedicalSupplyDeliverySystem.SupplyRequest request = doctorAgent.requestSupplies(requestedItems, 3600L);

        assertNotNull(request);
        assertArrayEquals(requestedItems, request.getRequiredItems());
        assertEquals(3600L, request.getDeadline());
        assertArrayEquals(requestedItems, extractField(doctorAgent, "supplies", MedicalSupplyDeliverySystem.SupplyInventory.class)
                .getLastRequestedItems());
    }

    @Test
    @DisplayName("Validator role should reject invalid certificates and accept valid ones")
    void validatorRoleValidatesCertificates() {
        MedicalSupplyDeliverySystem.HealthcareSociety.MissionCompartment.ValidatorRole validatorRole =
                new MedicalSupplyDeliverySystem.HealthcareSociety.MissionCompartment.ValidatorRole();
        MedicalSupplyDeliverySystem.SupplyPackage supplyPackage = new MedicalSupplyDeliverySystem.SupplyPackage("PKG-1");

        assertThrows(IllegalArgumentException.class,
                () -> validatorRole.confirmReceipt(supplyPackage, new MedicalSupplyDeliverySystem.Certificate(false)));
        assertTrue(validatorRole.confirmReceipt(supplyPackage, new MedicalSupplyDeliverySystem.Certificate(true)));
    }

    @Test
    @DisplayName("Delivery provider role should move from pending to delivered")
    void deliveryProviderTransitionsThroughLifecycle() {
        MedicalSupplyDeliverySystem.HealthcareSociety.MissionCompartment.DeliveryProviderRole providerRole =
                new MedicalSupplyDeliverySystem.HealthcareSociety.MissionCompartment.DeliveryProviderRole();
        MedicalSupplyDeliverySystem.SupplyPackage supplyPackage = new MedicalSupplyDeliverySystem.SupplyPackage("PKG-2");
        MedicalSupplyDeliverySystem.Location hospitalGate = new MedicalSupplyDeliverySystem.Location("hospital-gate");

        providerRole.acceptDelivery(supplyPackage);
        assertEquals(MedicalSupplyDeliverySystem.DeliveryStatus.ACCEPTED,
                extractField(providerRole, "status", MedicalSupplyDeliverySystem.DeliveryStatus.class));

        providerRole.updateDeliveryStatus(hospitalGate);
        assertEquals(MedicalSupplyDeliverySystem.DeliveryStatus.IN_TRANSIT,
                extractField(providerRole, "status", MedicalSupplyDeliverySystem.DeliveryStatus.class));
        assertEquals("hospital-gate",
                extractField(providerRole, "lastKnownLocation", MedicalSupplyDeliverySystem.Location.class).getLabel());

        providerRole.completeDelivery();
        assertEquals(MedicalSupplyDeliverySystem.DeliveryStatus.DELIVERED,
                extractField(providerRole, "status", MedicalSupplyDeliverySystem.DeliveryStatus.class));
        assertTrue(extractField(providerRole, "deliveryTime", Long.class) > 0L);
    }

    @Test
    @DisplayName("Order coordinator should choose the available provider or fall back when none exist")
    void orderCoordinatorAssignsProviders() {
        MedicalSupplyDeliverySystem.HealthcareSociety.MissionCompartment.OrderCoordinatorRole coordinatorRole =
                new MedicalSupplyDeliverySystem.HealthcareSociety.MissionCompartment.OrderCoordinatorRole();
        MedicalSupplyDeliverySystem.SupplyRequest request =
                new MedicalSupplyDeliverySystem.SupplyRequest(new String[]{"blood"}, 1200L);

        MedicalSupplyDeliverySystem.DeliveryAssignment fallbackAssignment =
                coordinatorRole.matchSupplyWithDelivery(request, List.of());
        assertEquals("fallback-provider", fallbackAssignment.getProviderId());
        assertEquals(request, fallbackAssignment.getRequest());

        MedicalSupplyDeliverySystem.DeliveryAssignment selectedAssignment = coordinatorRole.matchSupplyWithDelivery(
                request,
                List.of(new MedicalSupplyDeliverySystem.DeliveryProvider("uav-7")));
        assertEquals("uav-7", selectedAssignment.getProviderId());
    }

    @Test
    @DisplayName("Medical supply example should generate Solidity contracts during compilation")
    void generatedContractsExist() {
        Path contractsDir = Paths.get("generatedsmartcontract", "contracts");
        assertTrue(Files.isDirectory(contractsDir), "generatedsmartcontract/contracts directory should exist");

        assertGeneratedContractExists(contractsDir, "HealthcareSociety.sol");
        assertGeneratedContractExists(contractsDir, "MissionCompartment.sol");
        assertGeneratedContractExists(contractsDir, "DeliveryProviderRole.sol");
        assertGeneratedContractExists(contractsDir, "ValidatorRole.sol");
        assertGeneratedContractExists(contractsDir, "OrderCoordinatorRole.sol");
        assertGeneratedContractExists(contractsDir, "UAVAgent.sol");
        assertGeneratedContractExists(contractsDir, "DoctorAgent.sol");
        assertGeneratedContractExists(contractsDir, "MedicalSupplyDeliverySystemOrchestrator.sol");
    }

    private static void assertGeneratedContractExists(Path contractsDir, String fileName) {
        assertTrue(Files.exists(contractsDir.resolve(fileName)), () -> "Expected generated contract not found: " + fileName);
    }

    private static <T> T extractField(Object target, String fieldName, Class<T> fieldType) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return fieldType.cast(field.get(target));
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError("Unable to access field '" + fieldName + "' on " + target.getClass().getName(), exception);
        }
    }
}



