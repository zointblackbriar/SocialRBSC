# Supply Chain Use Case - SocialRbSC-Examples

## Overview

This document describes the Supply Chain use case implemented in the SocialRbSC-Examples project. The use case demonstrates a complete implementation of a supply chain society with multiple roles, compartments, and intentions using the SocialRbSC framework.

## Architecture

### Directory Structure

```
src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/
├── roles/
│   ├── Retailer.java
│   ├── Wholesaler.java
│   └── Producer.java
├── compartments/
│   └── Factory.java
├── intentions/
│   ├── UserRegistration.java
│   ├── UserExist.java
│   └── ProductReady.java
└── HumanOrganization.java (Society)

src/test/java/de/tudresden/socialrbscdeterministic/examples/supplychain/
└── SupplyChainUseCaseTest.java
```

## Components

### 1. Roles

#### Retailer Role
- **File**: [supplychain/roles/Retailer.java](src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/roles/Retailer.java)
- **Responsibility**: Manages supply chain interactions with customers
- **Key Methods**:
  - `supplyChainCalculator(int demand)`: Calculates supply as demand + 1
  - Enforces postcondition: `supplyChainTestParam > 0`
- **State Variables**:
  - `supplyChainTestParam`: Stores the calculated supply value
  - `agentState`: Current agent state (initialized to "RUNNABLEAGENT")

#### Wholesaler Role
- **File**: [supplychain/roles/Wholesaler.java](src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/roles/Wholesaler.java)
- **Responsibility**: Manages inventory and distribution
- **Key Methods**:
  - `receiveProducts(int quantity)`: Adds products to inventory
  - `distributeProducts(int quantity)`: Removes products from inventory
- **State**: Maintains inventory level

#### Producer Role
- **File**: [supplychain/roles/Producer.java](src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/roles/Producer.java)
- **Responsibility**: Manufactures goods based on forecasts
- **Key Methods**:
  - `produceGoods(int demandForecast)`: Produces goods up to production capacity (100 units)
- **Constraints**: Production capacity limited to 100 units

### 2. Compartment

#### Factory Compartment
- **File**: [supplychain/compartments/Factory.java](src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/compartments/Factory.java)
- **Purpose**: Orchestrates interactions between roles
- **Key Methods**:
  - `runCycle()`: Executes a basic supply chain cycle
  - `processSupplyChainCycle(int customerDemand)`: Demand-driven supply chain processing

### 3. Intentions

#### UserRegistration Intention
- **File**: [supplychain/intentions/UserRegistration.java](src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/intentions/UserRegistration.java)
- **Purpose**: Manages user registration in the system
- **Method**: `registeringUser()` - Registers a user with the name "sampleName"

#### UserExist Intention
- **File**: [supplychain/intentions/UserExist.java](src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/intentions/UserExist.java)
- **Purpose**: Verifies user existence
- **Method**: `checkingFunc()` - Returns true if both username and userID are set

#### ProductReady Intention
- **File**: [supplychain/intentions/ProductReady.java](src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/intentions/ProductReady.java)
- **Purpose**: Verifies product readiness for delivery
- **Method**: `productStatusCheck()` - Sets product status to true

### 4. Society

#### HumanOrganization Society
- **File**: [supplychain/HumanOrganization.java](src/main/java/de/tudresden/socialrbscdeterministic/examples/supplychain/HumanOrganization.java)
- **Annotations**: `@Society`, `@DeliberationCycle`
- **Purpose**: Defines the organizational structure for supply chain agents

## Test Suite

### Test Class: SupplyChainUseCaseTest
- **File**: [SupplyChainUseCaseTest.java](src/test/java/de/tudresden/socialrbscdeterministic/examples/supplychain/SupplyChainUseCaseTest.java)
- **Total Tests**: 20
- **Status**: ✅ All tests passing

#### Test Categories

##### Retailer Role Tests (4 tests)
1. `testRetailerSupplyChainCalculator()` - Tests supply calculation
2. `testRetailerPostconditionEnforcement()` - Verifies postcondition enforcement
3. `testRetailerAgentState()` - Checks initial agent state
4. Postcondition validation tests

##### Wholesaler Role Tests (4 tests)
1. `testWholesalerReceiveProducts()` - Tests receiving goods
2. `testWholesalerDistributeProducts()` - Tests distribution
3. `testWholesalerDistributeConstraint()` - Validates constraints
4. `testWholesalerInitialInventory()` - Checks initial state

##### Producer Role Tests (3 tests)
1. `testProducerProduceGoods()` - Tests production logic
2. `testProducerCapacityLimit()` - Verifies capacity constraints
3. `testProducerInitialCapacity()` - Checks initial capacity

##### Factory Compartment Tests (3 tests)
1. `testFactoryInitialization()` - Verifies roles are initialized
2. `testFactoryRunCycle()` - Tests cycle execution
3. `testFactoryProcessSupplyChainCycle()` - Tests demand-driven processing

##### Intention Tests (4 tests)
1. `testUserRegistrationIntention()` - Tests user registration
2. `testUserExistIntention()` - Tests user existence verification
3. `testUserExistPartialPrecondition()` - Tests partial conditions
4. `testProductReadyIntention()` - Tests product readiness

##### Integration Tests (2 tests)
1. `testCompleteSupplyChainFlow()` - End-to-end workflow
2. `testMultipleCycles()` - Multiple cycle execution

## Running the Tests

### Command
```bash
cd d:\Projects\mountidablog\socialagentrolebasedsmartcontract\SocialRbSC-Examples
mvn clean test -Dtest=SupplyChainUseCaseTest
```

### Expected Output
```
[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Key Features Demonstrated

1. **Role-Based Design**: Multiple roles with distinct responsibilities
2. **State Management**: Static state variables for role state tracking
3. **Constraint Enforcement**: Postconditions and state constraints
4. **Compartment Orchestration**: Roles interact within compartment context
5. **Intention-Based Operations**: Goals and intentions drive agent behavior
6. **Integration Testing**: Complete end-to-end supply chain workflows

## Use Case Flow

### Basic Flow
```
Customer Demand 
  → Retailer.supplyChainCalculator()
    → Producer.produceGoods()
      → Wholesaler.receiveProducts()
        → Wholesaler.distributeProducts()
```

### Complete Scenario
1. Customer places order with demand quantity
2. Retailer calculates required supply (demand + 1)
3. Producer manufactures goods (respecting capacity limit of 100)
4. Wholesaler receives manufactured goods
5. Wholesaler distributes to retailers
6. User registration and product readiness intentions are verified

## Example Test Output

```
Supply chain result: 11
Producer produced: 15
Wholesaler inventory after receiving: 15
Wholesaler inventory after distribution: 5
Customer Demand: 20
Calculated Supply: 21
Produced: 21
Wholesaler Inventory: 21
```

## Annotations Used

- `@Role`: Marks a class as a role in the system
- `@RoleMethod`: Marks methods that are part of role behavior
- `@Compartment`: Marks a class as a compartment for role coordination
- `@Society`: Marks an interface as a society definition
- `@DeliberationCycle`: Indicates deliberation cycle behavior

## Future Enhancements

1. Add smart contract generation from supply chain roles
2. Implement dynamic demand forecasting
3. Add inventory optimization algorithms
4. Support for multiple factories and suppliers
5. Real-time monitoring and analytics

## References

- SocialRbSC Framework Documentation: See parent project README
- Annotations: Located in SocialRbSC-core project
- Related Examples: TradingSystemCompartment in SocialRbSC-Examples

