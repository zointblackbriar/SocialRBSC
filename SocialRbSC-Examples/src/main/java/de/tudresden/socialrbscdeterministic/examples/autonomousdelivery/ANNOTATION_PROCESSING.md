# Annotation Processing Configuration

## Overview

This document describes how the Autonomous Medical Delivery Fleet Management example is configured for annotation processing and Solidity smart contract generation.

## Annotation Processor Configuration

### Maven Configuration (pom.xml)

The annotation processor is configured in the Maven POM file with the following settings:

```xml
<annotationProcessorPaths>
    <path>
        <groupId>de.tudresden</groupId>
        <artifactId>socialrbsc-processor</artifactId>
        <version>${project.version}</version>
    </path>
</annotationProcessorPaths>
```

### Compiler Settings

```xml
<maven.compiler.source>11</maven.compiler.source>
<maven.compiler.target>11</maven.compiler.target>
<maven.compiler.generatedSourcesDirectory>${project.build.directory}/generated-sources/annotations</maven.compiler.generatedSourcesDirectory>
```

## Code Generation Process

### 1. Annotation Detection Phase

The annotation processor scans for:

- **@Compartment** annotations on interface/class definitions
- **@Role** annotations on role implementation classes
- **@SocialAgent** annotations on agent coordinator classes
- **@StateVariable** annotations on static fields
- **@SocialAgentState** annotations on state fields
- **@RoleMethod** annotations indicating contract-callable methods
- **@Precondition** annotations for method guards

### 2. Model Building Phase

For each discovered annotation, the processor:

1. Extracts class structure (name, package, fields, methods)
2. Parses method signatures and parameters
3. Identifies state variables and their types
4. Builds method contracts with pre/post-conditions
5. Maps inheritance hierarchies

Example for detected class:
```
Class: MedicalSupplyCoordinatorRole
  Package: de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.roles
  Fields:
    - totalDeliveriesCoordinated (StateVariable, int)
    - currentCoordinationStatus (StateVariable, String)
    - agentState (SocialAgentState, String)
  Methods:
    - coordinateDeliveryPlan[Precondition: supplyRequests != null && supplyRequests.length > 0]
    - optimizeDeliverySequence
```

### 3. Template Selection Phase

The processor selects appropriate Velocity templates based on:

| Component Type | Selected Template | Output |
|---|---|---|
| Compartment | `Compartment.vm`, `CompartmentInitiatorTemplate.vm` | CompartmentName.sol |
| Role | `Role.vm`, `RoleCreatorTemplate.vm` | RoleNameRole.sol |
| SocialAgent | `SocialAgentTemplate.vm` | AgentNameAgent.sol |
| Society | `SocialAgent.sol.vm` (contracts/societypattern/) | SocietyName.sol |
| GoalPlan | `GoalPlanTree.sol.vm` | GoalPlanName.sol |

### 4. Velocity Context Building

For each template, the processor builds a context map:

```velocity
#set($className = "MedicalSupplyCoordinator")
#set($package = "de.tudresden.delivery.contracts")
#set($statefulVariables = ["totalDeliveriesCoordinated", "currentCoordinationStatus"])
#set($methods = [
    {
        "name": "coordinateDeliveryPlan",
        "params": ["MedicalSupplyRequest[]"],
        "returns": "DeliveryAssignment[]",
        "visibility": "public"
    }
])
#set($stateVariables = [
    {
        "name": "totalDeliveriesCoordinated",
        "type": "uint256",
        "visibility": "public"
    }
])
#set($preconditions = ["supplyRequests != null && supplyRequests.length > 0"])
```

### 5. Solidity Generation Phase

The processor renders templates with the context:

```solidity
pragma solidity ^0.8.0;

contract MedicalSupplyCoordinator {
    // State variables
    uint256 public totalDeliveriesCoordinated;
    string public currentCoordinationStatus;
    string public agentState;

    // Events
    event DeliveryCoordinated(uint256 indexed deliveryCount);
    event SequenceOptimized(uint256 indexed assignmentCount);

    // Methods
    function coordinateDeliveryPlan(
        MedicalSupplyRequest[] calldata supplyRequests
    ) public returns (DeliveryAssignment[] memory) {
        require(supplyRequests.length > 0, "Supply requests cannot be empty");
        // ... implementation
    }

    function optimizeDeliverySequence(
        DeliveryAssignment[] calldata assignments
    ) public returns (bool) {
        // ... implementation
    }
}
```

### 6. Output Generation Phase

Generated Solidity contracts are written to:
```
generatedsmartcontract/contracts/
├── FleetCoordinationCompartment.sol
├── RouteOptimizationCompartment.sol
├── EmergencyResponseCompartment.sol
├── MedicalSupplyCoordinatorRole.sol
├── UAVDeliveryAgentRole.sol
├── EmergencyResponseAgentRole.sol
├── DeliveryFleetSociety.sol
└── AutonomousDeliveryFleetCoordinator.sol
```

## Velocity Template Variables

### Common Variables Available in All Templates

```velocity
$className          - Simple class name
$fullClassName      - Fully qualified class name
$package            - Package name
$description        - Javadoc description
$author             - Author annotation value
$version            - Version annotation value
```

### Role Template Variables

```velocity
$methods            - List of @RoleMethod methods
$stateVariables     - List of @StateVariable fields
$conditions         - @Precondition conditions
$transitions        - State transitions
$compartment        - Associated @Compartment reference
```

### SocialAgent Template Variables

```velocity
$agentName          - Agent name from annotation
$roles              - List of assigned @Role classes
$beliefs            - @Belief annotations
$intentions         - @Intention annotations
$goals              - @Goal annotations
$plans              - @Plan annotations
$stateVariables     - Agent-level state variables
$agentState         - @SocialAgentState field
```

### Compartment Template Variables

```velocity
$compartmentName    - Compartment name
$roles              - Roles active in compartment
$agents             - Agents participating in compartment
$initialState       - Initial compartment state
$activationEvents   - Compartment activation conditions
```

## Runtime Configuration Files

### annotation.properties

```properties
# Solidity version compatibility
solidity.version=0.8.19
solidity.evm=istanbul

# Code generation options
generate.events=true
generate.modifiers=true
generate.interfaces=true

# Output options
output.directory=generatedsmartcontract/contracts/
output.package=de.tudresden.delivery.contracts
output.naming.format=${ClassName}.sol

# Web3j options
web3j.generate=true
web3j.output=generatedsmartcontract/javafiles/
web3j.package=de.tudresden.delivery.web3j
```

### annotation-processor.xml

```xml
<annotation-processors>
    <processor>
        <name>RoleProcessor</name>
        <annotation>de.tudresden.rolecompartment.annotation.Role</annotation>
        <template>Role.vm</template>
    </processor>
    <processor>
        <name>CompartmentProcessor</name>
        <annotation>de.tudresden.rolecompartment.annotation.Compartment</annotation>
        <template>Compartment.vm</template>
    </processor>
    <processor>
        <name>SocialAgentProcessor</name>
        <annotation>de.tudresden.mas.annotation.SocialAgent</annotation>
        <template>SocialAgentTemplate.vm</template>
    </processor>
    <processor>
        <name>SocietyProcessor</name>
        <annotation>de.tudresden.mas.annotation.Society</annotation>
        <template>contracts/societypattern/Society.sol.vm</template>
    </processor>
</annotation-processors>
```

## Type Mapping

### Java to Solidity Type Mapping

| Java Type | Solidity Type | Notes |
|-----------|---------------|-------|
| int, Integer | int256/uint256 | Uses uint256 for positive only |
| long, Long | int256/uint256 | Uses uint256 for positive only |
| float, Float | uint256 | Multiplied by 10^18 for decimals |
| double, Double | uint256 | Multiplied by 10^18 for decimals |
| boolean | bool | Direct mapping |
| String | string | Variable-length string |
| byte[] | bytes | Fixed or dynamic based on length |
| Object arrays | Type[] | Converted to Solidity arrays |
| ArrayList | Type[] | Converted to dynamic arrays |

## Method Signature Transformation

### Java Signature
```java
@RoleMethod
@Precondition("supplyRequests != null && supplyRequests.length > 0")
public DeliveryAssignment[] coordinateDeliveryPlan(MedicalSupplyRequest[] supplyRequests)
```

### Generated Solidity
```solidity
event CoordinateDeliveryPlanCalled(address indexed caller, uint256 indexed requestCount);

function coordinateDeliveryPlan(
    MedicalSupplyRequest[] calldata supplyRequests
) public returns (DeliveryAssignment[] memory) {
    require(supplyRequests.length > 0, "Supply requests cannot be empty");
    
    emit CoordinateDeliveryPlanCalled(msg.sender, supplyRequests.length);
    
    // ... method implementation
}
```

## Compilation and Build Process

### Step 1: Annotation Processing
```bash
mvn clean process-classes
```
Outputs generated Solidity and Java files.

### Step 2: Solidity Compilation
```bash
cd generatedsmartcontract/
npx hardhat compile
```
Generates ABI and bytecode files.

### Step 3: Web3j Code Generation
```bash
web3j solidity generate \
  --soliditySourceFile contracts/DeliveryFleetSociety.sol \
  --outputDir src/main/java \
  --packageName de.tudresden.delivery.web3j
```
Creates Java wrapper classes for contracts.

### Step 4: Full Maven Build
```bash
mvn clean install
```
Packages everything into JAR for deployment.

## Debugging Annotation Processing

### Enable Verbose Output
```bash
mvn clean process-classes -X
```

### Check Generated Sources
Generated files are output to:
```
target/generated-sources/annotations/
```

### Validation Script
```bash
scripts/validate-annotation-processing.sh
```
Validates that all necessary files were generated.

## Integration with Build Pipeline

### CI/CD Configuration

```yaml
# .github/workflows/build.yml
- name: Process Annotations
  run: mvn clean process-classes

- name: Generate Solidity
  run: ./scripts/compile-generated-contracts.sh

- name: Generate Web3j Bindings
  run: ./scripts/generate-web3j-bindings.sh

- name: Run Tests
  run: mvn test
```

## Troubleshooting

### Common Issues and Solutions

1. **No Solidity files generated**
   - Verify annotations are in classpath
   - Check processor path in pom.xml
   - Enable verbose output with -X flag

2. **Type not found errors**
   - Ensure all imported classes are available
   - Check package imports in generated code

3. **Template not found errors**
   - Verify template paths in properties file
   - Check velocity template directory exists
   - Validate template syntax

4. **Compilation errors in generated Solidity**
   - Review Solidity version in properties
   - Check type mappings for unsupported types
   - Validate method signatures

## Performance Optimization

### Incremental Annotation Processing
```properties
# Only process changed files
incremental.processing=true
cache.directory=.annotation-cache/
```

### Parallel Compilation
```bash
mvn -T 1C clean install
# Uses 1 thread per core for parallel builds
```

### Memory Tuning
```bash
MAVEN_OPTS="-Xmx2G -XX:+UseG1GC" mvn install
```

## References

- Annotation Processor API: javax.annotation.processing
- Velocity Template Engine: velocity.apache.org
- SocialRbSC Documentation: SocialRBSC-core/DEVELOPER.md
- Solidity Compiler: solc documentation
