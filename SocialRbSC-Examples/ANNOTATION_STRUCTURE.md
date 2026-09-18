# SocialRbSC Examples - Organized Annotation-Based Code Generation

## Project Structure

The SocialRbSC Examples project now follows a well-organized folder structure within `src/main/java/de/tudresden/socialrbscdeterministic/examples/`:

```
src/main/java/de/tudresden/socialrbscdeterministic/examples/
├── agents/
│   ├── SocialAgentBuyer.java
│   ├── SocialAgentSeller.java
│   └── SocialAgentInitiator.java
├── roles/
│   ├── BuyerRole.java
│   └── SellerRole.java
└── compartments/
    ├── SupplyChainCompartment.java
    └── ExamplesApp.java
```

## Components

### 1. Social Agents (`agents/`)
Social agents are autonomous entities that participate in the system:

- **SocialAgentBuyer**: Represents a buying entity with preconditions for purchase activities
- **SocialAgentSeller**: Represents a selling entity with inventory verification capabilities
- **SocialAgentInitiator**: Coordinates interactions between buyers and sellers

**Annotations Used:**
- `@SocialAgent`: Defines a class as a social agent
- `@Precondition`: Specifies conditions that must be met before method execution
- `@RoleMethod`: Marks methods that are role-specific

### 2. Roles (`roles/`)
Roles define specific responsibilities and behaviors within the system:

- **BuyerRole**: Encapsulates buyer-specific behavior (quotation request, order placement, delivery processing)
- **SellerRole**: Encapsulates seller-specific behavior (quotation sending, order acceptance, goods shipment)

**Annotations Used:**
- `@Role`: Defines a class as a role
- `@RoleMethod`: Marks methods as role-specific actions

### 3. Compartments (`compartments/`)
Compartments are contexts in which agents can play roles:

- **SupplyChainCompartment**: Creates a context for supply chain interactions, containing both buyer and seller roles
- **ExamplesApp**: Main entry point that demonstrates framework capabilities

**Annotations Used:**
- `@Compartment`: Defines a class as a compartment
- `@SocialAgentGoal`: Marks the goal/objective of social agents

## Code Generation from Annotations

The framework uses annotations to automatically generate:

1. **Smart Contracts** (Solidity): From annotated Java classes, smart contracts are generated and placed in `generatedsmartcontract/contracts/`
2. **Wrapper Classes**: Java wrapper classes for contract interaction
3. **Role Implementations**: Concrete implementations of role behaviors
4. **Compartment Management Code**: Code to manage agent-role assignments within compartments

## How to Use

1. **Define Social Agents** in `agents/` with `@SocialAgent` annotation
2. **Define Roles** in `roles/` with `@Role` annotation
3. **Define Compartments** in `compartments/` with `@Compartment` annotation
4. **Run Maven Build**: The annotation processor will generate corresponding smart contracts and Java wrappers
5. **Check Generated Artifacts**: View generated files in:
   - `generatedsmartcontract/contracts/` (Solidity contracts)
   - `target/generated-sources/` (Generated Java code)

## Building the Project

```bash
# Build with annotation processing
mvn clean install

# Run tests
mvn test

# Generate documentation
mvn javadoc:javadoc
```

## Related Configuration

The `pom.xml` includes:
- Annotation processing configuration
- Web3j Maven plugin for Solidity compilation
- Smart contract generation from annotations

## Next Steps for Customization

1. Add new social agents in `agents/` directory
2. Create new roles in `roles/` directory
3. Extend compartments in `compartments/` directory
4. Implement business logic in the TODO sections
5. Run Maven to generate smart contracts automatically
