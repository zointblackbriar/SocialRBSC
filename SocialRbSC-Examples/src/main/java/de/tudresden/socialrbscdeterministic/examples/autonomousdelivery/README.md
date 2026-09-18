# Autonomous Medical Delivery Fleet Management Example

## Overview

This example demonstrates a complete multi-agent system for autonomous medical supply delivery using UAV (drone) fleet coordination. It showcases the SocialRbSC framework's capabilities for:

- **Annotation Processing**: Uses `@Role`, `@SocialAgent`, `@Compartment`, `@StateVariable`, `@SocialAgentState`, `@RoleMethod`, and `@Precondition` annotations
- **Multi-Compartment Architecture**: Three compartments for fleet coordination, route optimization, and emergency response
- **Complex Role Interactions**: Coordinator, UAV agents, and emergency response agents working in concert
- **Smart Contract Generation**: Annotations are processed to generate Solidity smart contracts via Velocity templates
- **Web3j Integration**: Runtime environment for blockchain interaction

## Project Structure

```
autonomousdelivery/
├── compartments/
│   ├── FleetCoordinationCompartment.java
│   ├── RouteOptimizationCompartment.java
│   └── EmergencyResponseCompartment.java
├── roles/
│   ├── MedicalSupplyCoordinatorRole.java
│   ├── UAVDeliveryAgentRole.java
│   └── EmergencyResponseAgentRole.java
├── agents/
│   ├── MedicalSupplyCoordinatorAgent.java
│   ├── UAVDeliveryAgent.java
│   └── EmergencyResponseAgent.java
├── data/
│   ├── MedicalSupplyRequest.java
│   ├── DeliveryAssignment.java
│   └── DisruptionEvent.java
├── society/
│   └── DeliveryFleetSociety.java
└── AutonomousDeliveryFleetCoordinator.java
```

## Annotation Usage

### Compartments
```java
@Compartment
public class FleetCoordinationCompartment {}
```
Three compartments define different operational contexts:
- **FleetCoordinationCompartment**: Central coordination logic
- **RouteOptimizationCompartment**: Route planning and optimization
- **EmergencyResponseCompartment**: Emergency handling and disruption response

### Roles
```java
@Role
public class MedicalSupplyCoordinatorRole {
    @StateVariable
    public static int totalDeliveriesCoordinated = 0;
    
    @RoleMethod
    @Precondition("supplyRequests != null && supplyRequests.length > 0")
    public DeliveryAssignment[] coordinateDeliveryPlan(MedicalSupplyRequest[] supplyRequests) { ... }
}
```

Three roles encapsulate agent capabilities:
- **MedicalSupplyCoordinatorRole**: Central decision-making
- **UAVDeliveryAgentRole**: Individual delivery execution
- **EmergencyResponseAgentRole**: Disruption handling

### Social Agents
```java
@SocialAgent(agentName = "MedicalSupplyCoordinator", addRole = true)
public class MedicalSupplyCoordinatorAgent {
    @SocialAgentState
    public static String coordinatorState = "INITIALIZED";
    
    @StateVariable
    public static int totalCoordinationSessions = 0;
}
```

Three social agents implement the active entities:
- **MedicalSupplyCoordinatorAgent**: Fleet orchestration
- **UAVDeliveryAgent**: Individual UAV (5 in fleet)
- **EmergencyResponseAgent**: Emergency response

### State Management
- **@StateVariable**: Persistent state (totalDeliveriesCoordinated)
- **@SocialAgentState**: Agent behavioral state (COORDINATING, IN_FLIGHT, STANDBY)
- **@Precondition**: Method execution guards

## Key Features

### 1. Fleet Coordination
The coordinator agent:
- Receives medical supply requests
- Creates delivery assignments
- Optimizes delivery sequence
- Dispatches to available UAVs

### 2. Adaptive Delivery
UAV agents:
- Execute delivery assignments
- Check environmental conditions
- Dynamically recalculate routes
- Update battery status

### 3. Emergency Response
Emergency agent:
- Monitors disruption events
- Evaluates event type
- Initiates appropriate responses (GROUND_ALL_FLIGHTS, REROUTE_OR_RESCHEDULE, etc.)

## Velocity Template Mapping

The annotation processor uses these templates from `SocialRBSC-core/velocitytemplates`:

| Component | Templates |
|-----------|-----------|
| Compartments | `Compartment.vm`, `CompartmentInitiatorTemplate.vm`, `CompartmentActivatorRuntime.vm` |
| Roles | `Role.vm`, `RoleOne.vm`, `RoleCreatorTemplate.vm` |
| Social Agents | `SocialAgentTemplate.vm`, `SocialAgentAddRole.vm`, `SocialAgentRemoveRole.vm` |
| Society Pattern | `Society.sol.vm`, `SocialAgent.sol.vm` (contracts/societypattern/) |
| Goal-Plan Trees | `GoalPlanTree.sol.vm` |

## Solidity Smart Contract Generation

The annotation processor generates Solidity contracts:

1. **CompartmentCore.sol** - Compartment contract base
2. **MedicalSupplyCoordinatorRole.sol** - Coordinator role contract
3. **UAVDeliveryAgentRole.sol** - UAV agent role contract  
4. **EmergencyResponseAgentRole.sol** - Emergency response contract
5. **DeliveryFleetSociety.sol** - Society orchestration contract
6. **AutonomousDeliveryFleetCoordinator.sol** - System coordinator contract

contracts are generated in: `generatedsmartcontract/contracts/`

## Running the Example

### Java Execution
```bash
cd SocialRbSC-Examples
mvn clean install
java de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.AutonomousDeliveryFleetCoordinator
```

### Expected Output
```
=== Autonomous Delivery Fleet Management Example ===

[SOCIAL_AGENT] Autonomous Delivery Fleet Coordinator initializing...
[AGENT] Medical Supply Coordinator Agent initialized
[UAV_AGENT] UAV UAV-1 initialized
[UAV_AGENT] UAV UAV-2 initialized
...
[EMERGENCY_AGENT] Emergency Response Agent initialized
[SOCIAL_AGENT] Fleet coordination system ready with 5 UAVs

=== Coordinating Deliveries ===

[SOCIAL_AGENT] Processing 3 supply requests
[COORDINATOR] Coordinated 3 deliveries
[COORDINATOR_PLAN] Optimizing sequence for 3 deliveries
...
```

### Solidity Contract Compilation
```bash
cd SOLMASFramework
npm install
npx hardhat compile
```

### Web3j Integration
```bash
# Generate Java bindings from compiled contracts
web3j solidity generate -a build/contracts/DeliveryFleetSociety.abi -b build/contracts/DeliveryFleetSociety.bin -o src/main/java -p de.tudresden.delivery.contracts
```

## Integration Points with Smart Contracts

The Java classes integrate with blockchain through:

1. **Annotation Processing Layer**: Annotations trigger Solidity generation
2. **State Synchronization**: @StateVariable fields map to smart contract storage
3. **Method Execution**: @RoleMethod calls translate to contract functions
4. **Event Emission**: Critical state changes trigger blockchain events
5. **Web3j Runtime**: Java environment communicates with contracts via RPC

## Data Classes

### MedicalSupplyRequest
Fields: supplyType, destination, urgencyLevel

### DeliveryAssignment  
Fields: requestId, supplyType, destination, urgencyLevel, timestamp

### DisruptionEvent
Fields: eventType, eventTime

## Future Enhancements

1. **Blockchain Storage**: Persist delivery assignments and UAV states on-chain
2. **Payment Integration**: Cryptocurrency transactions for delivery services
3. **Insurance Contracts**: Multi-signature insurance for critical deliveries
4. **Reputation System**: Track UAV reliability and coordinator performance
5. **Oracle Integration**: Real-time weather and airspace data
6. **Advanced Deliberation**: BDI (Belief-Desire-Intention) reasoning cycles
7. **Machine Learning**: Predictive route optimization

## Author

SocialRbSC Framework
