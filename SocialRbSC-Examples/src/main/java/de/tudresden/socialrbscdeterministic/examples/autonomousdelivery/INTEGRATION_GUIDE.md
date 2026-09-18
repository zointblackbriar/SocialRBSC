# Integration & Execution Guide

## Quick Start

### 1. Build the Example

```bash
cd SocialRbSC-Examples
mvn clean install
```

### 2. Run the Java Example

```bash
java -cp target/SocialRbSC-Examples-1.0.jar de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.AutonomousDeliveryFleetCoordinator
```

Or run the test suite:

```bash
java -cp target/SocialRbSC-Examples-1.0.jar de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.AutonomousDeliveryFleetTest
```

### 3. Generate Solidity Smart Contracts

The annotation processor automatically generates Solidity contracts during Maven build:

```bash
# Generated contracts are in:
ls generatedsmartcontract/contracts/
```

Expected output files:
- `MedicalSupplyCoordinator.sol`
- `UAVDeliveryAgent.sol`
- `EmergencyResponseAgent.sol`
- `DeliveryFleetSociety.sol`

### 4. Compile Solidity Contracts

```bash
cd generatedsmartcontract/
npm install
npx hardhat compile
```

Compiled artifacts:
- `artifacts/contracts/MedicalSupplyCoordinator.json`
- `artifacts/contracts/UAVDeliveryAgent.json`

### 5. Generate Web3j Bindings

```bash
# Generate Java wrapper classes from contracts
web3j solidity generate \
  -a artifacts/contracts/MedicalSupplyCoordinator.json \
  -o ../src/main/java \
  -p de.tudresden.delivery.contracts

web3j solidity generate \
  -a artifacts/contracts/UAVDeliveryAgent.json \
  -o ../src/main/java \
  -p de.tudresden.delivery.contracts
```

Java contracts generated in: `src/main/java/de/tudresden/delivery/contracts/`

### 6. Deploy to Ethereum Test Network

```bash
# Using HardHat
npx hardhat run scripts/deploy.js --network sepolia

# Or using Web3j directly
java -cp target/SocialRbSC-Examples-1.0.jar de.tudresden.delivery.deploy.ContractDeployer
```

## Directory Structure After Generation

```
SocialRbSC-Examples/
├── src/main/java/de/tudresden/
│   ├── socialrbscdeterministic/examples/autonomousdelivery/  # Our example code
│   │   ├── AutonomousDeliveryFleetCoordinator.java
│   │   ├── AutonomousDeliveryFleetTest.java
│   │   ├── agents/
│   │   │   ├── MedicalSupplyCoordinatorAgent.java
│   │   │   ├── UAVDeliveryAgent.java
│   │   │   └── EmergencyResponseAgent.java
│   │   ├── roles/
│   │   │   ├── MedicalSupplyCoordinatorRole.java
│   │   │   ├── UAVDeliveryAgentRole.java
│   │   │   └── EmergencyResponseAgentRole.java
│   │   ├── compartments/
│   │   │   ├── FleetCoordinationCompartment.java
│   │   │   ├── RouteOptimizationCompartment.java
│   │   │   └── EmergencyResponseCompartment.java
│   │   ├── data/
│   │   │   ├── MedicalSupplyRequest.java
│   │   │   ├── DeliveryAssignment.java
│   │   │   └── DisruptionEvent.java
│   │   ├── society/
│   │   │   └── DeliveryFleetSociety.java
│   │   ├── README.md
│   │   ├── ANNOTATION_PROCESSING.md
│   │   ├── INTEGRATION_GUIDE.md
│   │   ├── MedicalSupplyCoordinator.sol
│   │   └── UAVDeliveryAgent.sol
│   └── delivery/contracts/  # Generated Web3j bindings
│       ├── MedicalSupplyCoordinator.java
│       └── UAVDeliveryAgent.java
│
├── generatedsmartcontract/
│   ├── contracts/
│   │   ├── MedicalSupplyCoordinator.sol
│   │   ├── UAVDeliveryAgent.sol
│   │   ├── EmergencyResponseAgent.sol
│   │   └── DeliveryFleetSociety.sol
│   └── javafiles/
│       ├── MedicalSupplyCoordinator.java
│       └── UAVDeliveryAgent.java
│
└── pom.xml
```

## Annotation Processing Workflow

### Step 1: Source Code Compilation with Annotation Processing

```
Java Source Files
        ↓
    Javac with Annotation Processor
        ↓
    Annotation Detection & Processing
        ↓
    Model Extraction (Classes, Methods, Fields)
        ↓
    Template Selection
        ↓
    Context Creation (Velocity Variables)
        ↓
    Velocity Template Rendering
        ↓
    Solidity Contract Generation
        ↓
    File Output to generatedsmartcontract/contracts/
```

### Step 2: Solidity Compilation

```
Solidity Source Files (.sol)
        ↓
    Solc Compiler
        ↓
    ABI Generation
        ↓
    Bytecode Generation
        ↓
    Artifact Output (JSON)
```

### Step 3: Web3j Code Generation

```
Contract Artifacts (ABI + Bytecode)
        ↓
    Web3j Code Generator
        ↓
    Java Wrapper Classes
        ↓
    Output to src/main/java/
```

## Example Usage in Java

### Initialize Fleet Coordinator

```java
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.AutonomousDeliveryFleetCoordinator;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.MedicalSupplyRequest;
import de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data.DisruptionEvent;

// Create coordinator
AutonomousDeliveryFleetCoordinator coordinator = 
    new AutonomousDeliveryFleetCoordinator();

// Create supply requests
MedicalSupplyRequest[] requests = new MedicalSupplyRequest[2];
requests[0] = new MedicalSupplyRequest("Blood", "Hospital A", "CRITICAL");
requests[1] = new MedicalSupplyRequest("Medicine", "Clinic B", "HIGH");

// Coordinate deliveries
coordinator.coordinateFleet(requests);

// Handle emergency
DisruptionEvent emergency = new DisruptionEvent("SEVERE_WEATHER");
coordinator.handleEmergency(emergency);

// Get statistics
System.out.println(coordinator.getFleetStatistics());
```

## Example Usage with Web3j (On-Chain)

### Deploy and Interact with Smart Contract

```java
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import de.tudresden.delivery.contracts.MedicalSupplyCoordinator;

// Setup Web3j connection
Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

// Load credentials
Credentials credentials = Credentials.create("YOUR_PRIVATE_KEY");

// Deploy contract
MedicalSupplyCoordinator contract = 
    MedicalSupplyCoordinator.deploy(web3j, credentials, 
        new DefaultGasProvider()).send();

System.out.println("Contract deployed at: " + contract.getContractAddress());

// Interact with contract
BigInteger deliveries = contract.getTotalDeliveries().send();
System.out.println("Total deliveries: " + deliveries);
```

## Testing

### Unit Tests for Java Classes

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=AutonomousDeliveryFleetTest

# Run with coverage
mvn test jacoco:report
```

### Integration Tests with Smart Contracts

```bash
cd generatedsmartcontract/
npm test
```

## Troubleshooting

### Issue: Annotation Processor Not Running

**Solution:**
```bash
# Verify processor is in classpath
mvn dependency:tree | grep socialrbsc-processor

# Force recompilation
mvn clean compile -X
```

### Issue: Solidity Compilation Errors

**Solution:**
```bash
# Check Solidity version
npx hardhat --version

# Update hardhat
npm install --save-dev hardhat@latest

# Check generated contracts
cat generatedsmartcontract/contracts/MedicalSupplyCoordinator.sol
```

### Issue: Web3j Generation Fails

**Solution:**
```bash
# Verify ABI file exists
ls -la artifacts/contracts/*.json

# Regenerate with verbose output
web3j solidity generate -a artifacts/contracts/MedicalSupplyCoordinator.json \
  -o src/main/java -p de.tudresden.delivery.contracts -v
```

### Issue: Maven Build Hangs

**Solution:**
```bash
# Increase memory
export MAVEN_OPTS="-Xmx2G -XX:+UseG1GC"
mvn clean install
```

## Performance Metrics

### Compilation Times
- Java annotation processing: ~5-10 seconds
- Solidity compilation: ~2-5 seconds  
- Web3j code generation: ~1-2 seconds
- Full build: ~20-30 seconds

### Generated Code Sizes
- Java source: ~15 KB
- Solidity contracts: ~20 KB
- Compiled bytecode: ~50 KB
- Web3j bindings: ~10 KB

## CI/CD Integration

### GitHub Actions Example

```yaml
name: Build and Deploy

on: [push]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      
      - name: Set up Java
        uses: actions/setup-java@v2
        with:
          java-version: 11
          
      - name: Build with Maven
        run: mvn clean install
        
      - name: Generate Contracts
        run: |
          cd generatedsmartcontract/
          npm install
          npx hardhat compile
          
      - name: Run Tests
        run: mvn test
        
      - name: Deploy to Testnet
        if: github.ref == 'refs/heads/main'
        run: npx hardhat run scripts/deploy.js --network sepolia
```

## Documentation Files

1. **README.md** - Overview and project structure
2. **ANNOTATION_PROCESSING.md** - Detailed annotation processing configuration
3. **INTEGRATION_GUIDE.md** - This file - execution and integration
4. **MedicalSupplyCoordinator.sol** - Example generated Solidity contract
5. **UAVDeliveryAgent.sol** - Example generated UAV agent contract

## Next Steps

1. ✅ Understand the example structure
2. ✅ Run the Java example locally
3. ✅ Generate Solidity contracts
4. ✅ Compile and test contracts
5. ✅ Deploy to local test network
6. ⬜ Deploy to public testnet (Sepolia)
7. ⬜ Extend with additional features
8. ⬜ Integrate with real weather APIs
9. ⬜ Implement payment mechanisms
10. ⬜ Add governance and voting

## Support & References

- SocialRbSC Framework: [SocialRBSC-core/](../../SocialRBSC-core/)
- Solidity Documentation: https://docs.soliditylang.org/
- Web3j Documentation: https://web3j.io/
- HardHat Documentation: https://hardhat.org/docs
- Velocity Templates: https://velocity.apache.org/
