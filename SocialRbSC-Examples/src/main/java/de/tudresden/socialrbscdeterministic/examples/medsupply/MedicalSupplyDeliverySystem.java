package de.tudresden.socialrbscdeterministic.examples.medsupply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tudresden.socialrbscdeterministic.mas.annotation.AccessControl;
import de.tudresden.socialrbscdeterministic.mas.annotation.AvoidanceMotive;
import de.tudresden.socialrbscdeterministic.mas.annotation.Belief;
import de.tudresden.socialrbscdeterministic.mas.annotation.Decomposition;
import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
import de.tudresden.socialrbscdeterministic.mas.annotation.DeliberationCycle;
import de.tudresden.socialrbscdeterministic.mas.annotation.Desire;
import de.tudresden.socialrbscdeterministic.mas.annotation.GoalPlanTree;
import de.tudresden.socialrbscdeterministic.mas.annotation.Motivation;
import de.tudresden.socialrbscdeterministic.mas.annotation.MotivationalModel;
import de.tudresden.socialrbscdeterministic.mas.annotation.Plan;
import de.tudresden.socialrbscdeterministic.mas.annotation.PlayableRoles;
import de.tudresden.socialrbscdeterministic.mas.annotation.Postcondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.Priority;
import de.tudresden.socialrbscdeterministic.mas.annotation.RoleSpecific;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.Society;
import de.tudresden.socialrbscdeterministic.mas.annotation.Transition;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.PlayingConstraint;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Properties;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Property;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleCardinality;

/**
 * Medical supply delivery example arranged in a format that the current annotation processors can scan.
 * The outer class is annotated with @Deploy so compilation of this file triggers generation.
 */
@Deploy
public class MedicalSupplyDeliverySystem {

    @Society(name = "HealthcareNetwork")
    public static class HealthcareSociety {

        @Compartment
        @Properties({
                @Property(name = "deliveryDeadline", type = "uint256"),
                @Property(name = "requiredSupplies", type = "bytes32[]"),
                @Property(name = "hospitalLocation", type = "address")
        })
        public static class MissionCompartment {

            @Role
            @RoleCardinality(maxInstances = 1)
            @PlayingConstraint("Objects may only play this role in one compartment at a time")
            public static class DeliveryProviderRole {
                private SupplyPackage currentPayload;
                private DeliveryStatus status = DeliveryStatus.PENDING;
                private Location lastKnownLocation = new Location("warehouse");
                private long deliveryTime;

                @Precondition("belief.capacity >= required_supplies.weight")
                @Postcondition("intention.goal = DELIVER_SUPPLIES")
                public void acceptDelivery(SupplyPackage supplies) {
                    this.currentPayload = supplies;
                    this.status = DeliveryStatus.ACCEPTED;
                }

                @AccessControl(restrictedTo = {"DeliveryProvider"})
                public void updateDeliveryStatus(Location currentLocation) {
                    this.lastKnownLocation = currentLocation;
                    this.status = DeliveryStatus.IN_TRANSIT;
                }

                @Transition(from = "IN_TRANSIT", to = "DELIVERED")
                public void completeDelivery() {
                    this.deliveryTime = System.currentTimeMillis();
                    this.status = DeliveryStatus.DELIVERED;
                }
            }

            @Role
            @RoleCardinality(maxInstances = 1)
            public static class ValidatorRole {

                @AccessControl(restrictedTo = {"Validator"})
                @Postcondition("intention.satisfied = true")
                public boolean confirmReceipt(SupplyPackage supplies, Certificate signedByValidator) {
                    if (!verifySignature(signedByValidator)) {
                        throw new IllegalArgumentException("Invalid signature");
                    }
                    return supplies != null;
                }

                private boolean verifySignature(Certificate signedByValidator) {
                    return signedByValidator != null && signedByValidator.valid;
                }
            }

            @Role
            public static class OrderCoordinatorRole {

                @Belief(name = "availableInventory", type = "mapping(bytes32 => uint256)")
                private Map<String, Long> inventory = new HashMap<>();

                @Postcondition("event.SupplyMatched emitted")
                public DeliveryAssignment matchSupplyWithDelivery(
                        SupplyRequest request,
                        List<DeliveryProvider> availableProviders) {
                    return assignBestProvider(request, availableProviders);
                }

                private DeliveryAssignment assignBestProvider(
                        SupplyRequest request,
                        List<DeliveryProvider> availableProviders) {
                    DeliveryProvider selectedProvider = availableProviders.isEmpty()
                            ? new DeliveryProvider("fallback-provider")
                            : availableProviders.get(0);
                    return new DeliveryAssignment(request, selectedProvider.id);
                }
            }
        }

        @SocialAgent(name = "UAVDeliveryAgent")
        @PlayableRoles({"DeliveryProvider", "InventoryMonitor", "RoutePlanner"})
        @MotivationalModel({
                @Motivation(type = Affiliation.class, value = 0.8),
                @Motivation(type = Avoidance.class, target = "hazardous_zones", value = 0.9)
        })
        public static class UAVAgent {

            @Belief(name = "currentBattery", type = "uint256")
            private long batteryLevel = 100L;

            @Belief(name = "currentLocation", type = "Location")
            private Location location = new Location("dispatch-center");

            @Belief(name = "availableRoutes", type = "Route[]")
            private List<Route> routes = new ArrayList<>();

            @Desire(name = "deliverSuppliesOnTime")
            @Priority(level = 1)
            private GoalState primaryGoal = new GoalState("deliverSuppliesOnTime");

            @Desire(name = "minimizeBatteryUsage")
            @Priority(level = 2)
            private GoalState secondaryGoal = new GoalState("minimizeBatteryUsage");

            @Desire(name = "avoidHazards")
            @Priority(level = 3)
            @AvoidanceMotive
            private GoalState avoidanceGoal = new GoalState("avoidHazards");

            @DeliberationCycle
            @GoalPlanTree(root = "deliverSuppliesOnTime")
            @Plan(name = "OptimizedDeliveryPlan")
            @Decomposition({
                    "navigateToHospital -> minimizeBatteryUsage",
                    "avoidWeatherHazards -> avoidHazards",
                    "validateDelivery -> completeMission"
            })
            public void deliberate() {
                updateBeliefs();
                evaluateGoals();
                selectIntention();
                executeIntention();
            }

            private void updateBeliefs() {
                this.batteryLevel = Math.max(0L, batteryLevel - 1L);
            }

            private void evaluateGoals() {
                if (routes.isEmpty()) {
                    routes.add(new Route("primary-route"));
                }
            }

            private void selectIntention() {
                this.location = new Location("mission-corridor");
            }

            private void executeIntention() {
                if (batteryLevel < 20L) {
                    avoidanceGoal = new GoalState("returnToBaseSafely");
                }
            }
        }

        @SocialAgent(name = "DoctorAgent")
        @PlayableRoles({"SupplyRequester", "Validator", "Collaborator"})
        public static class DoctorAgent {

            @Belief(name = "currentSupplyStatus", type = "SupplyInventory")
            private SupplyInventory supplies = new SupplyInventory();

            @Desire(name = "ensureMedicalSupplyAvailability")
            @Priority(level = 1)
            private GoalState primaryGoal = new GoalState("ensureMedicalSupplyAvailability");

            @RoleSpecific(role = "SupplyRequester")
            @Postcondition("event.SupplyRequested emitted")
            public SupplyRequest requestSupplies(String[] requiredItems, long deadline) {
                supplies.lastRequestedItems = requiredItems;
                return new SupplyRequest(requiredItems, deadline);
            }
        }
    }

    public enum DeliveryStatus {
        PENDING,
        ACCEPTED,
        IN_TRANSIT,
        DELIVERED
    }

    public static final class GoalState {
        private final String name;

        public GoalState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static final class SupplyPackage {
        private final String id;

        public SupplyPackage(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public static final class Certificate {
        private final boolean valid;

        public Certificate(boolean valid) {
            this.valid = valid;
        }
    }

    public static final class Location {
        private final String label;

        public Location(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public static final class SupplyRequest {
        private final String[] requiredItems;
        private final long deadline;

        public SupplyRequest(String[] requiredItems, long deadline) {
            this.requiredItems = requiredItems;
            this.deadline = deadline;
        }

        public String[] getRequiredItems() {
            return requiredItems;
        }

        public long getDeadline() {
            return deadline;
        }
    }

    public static final class DeliveryProvider {
        private final String id;

        public DeliveryProvider(String id) {
            this.id = id;
        }
    }

    public static final class DeliveryAssignment {
        private final SupplyRequest request;
        private final String providerId;

        public DeliveryAssignment(SupplyRequest request, String providerId) {
            this.request = request;
            this.providerId = providerId;
        }

        public SupplyRequest getRequest() {
            return request;
        }

        public String getProviderId() {
            return providerId;
        }
    }

    public static final class Route {
        private final String identifier;

        public Route(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return identifier;
        }
    }

    public static final class SupplyInventory {
        private String[] lastRequestedItems = new String[0];

        public String[] getLastRequestedItems() {
            return lastRequestedItems;
        }
    }

    public static final class Affiliation {
        private Affiliation() {
        }
    }

    public static final class Avoidance {
        private Avoidance() {
        }
    }
}

