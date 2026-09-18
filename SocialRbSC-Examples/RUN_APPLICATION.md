# Running the SocialRbSC Examples Application

## ⚠️ Important: Build Parent Framework First

Before running the Examples, you must build the parent SocialRbSC framework. This resolves the required dependency `de.tudresden.mas:SocialRBSC:jar:1.0-SNAPSHOT`.

### Quick Build Setup (Recommended)

From the parent project root directory:

**Windows:**
```batch
build-all.bat
```

**Linux/macOS:**
```bash
chmod +x build-all.sh
./build-all.sh
```

This will automatically:
1. Build the parent framework
2. Install it to local Maven repository
3. Build the Examples project
4. Run all tests

For detailed troubleshooting, see [BUILD_TROUBLESHOOTING.md](../BUILD_TROUBLESHOOTING.md).

---

## Prerequisites

This guide explains how to build and run the SocialRbSC Examples project using Maven on different operating systems.

### Required Software

### 1. Java Development Kit (JDK)
- **Minimum Version**: Java 11 or higher (Java 17 recommended)
- **Installation**:
  - **Windows**: Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or use [OpenJDK](https://jdk.java.net/)
  - **Linux**: `sudo apt-get install openjdk-17-jdk` (Ubuntu/Debian)
  - **macOS**: `brew install openjdk@17` (using Homebrew)

### 2. Apache Maven
- **Version**: 3.6.0 or higher
- **Installation**:
  - **Windows**: Download from [Maven Website](https://maven.apache.org/download.cgi) and add to PATH
  - **Linux**: `sudo apt-get install maven` (Ubuntu/Debian)
  - **macOS**: `brew install maven` (using Homebrew)

Verify installations:
```bash
java -version
mvn --version
```

## Running the Application

### Windows

#### Using the Batch Script
The easiest way is to use the provided batch script:

```bash
# Run full build, test, and application (default)
run-app.bat

# Or with specific command
run-app.bat run      # Full build and run
run-app.bat build    # Build only
run-app.bat test     # Build and test only
run-app.bat clean    # Clean build artifacts
run-app.bat rebuild  # Clean and full rebuild
```

#### Manual Maven Command
```bash
# Build and run tests
mvn clean compile test

# Run the application
mvn exec:java -Dexec.mainClass="de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp"
```

### Linux

#### Using the Bash Script
Make the script executable first:
```bash
chmod +x run-app.sh
```

Then run:
```bash
# Run full build, test, and application (default)
./run-app.sh

# Or with specific command
./run-app.sh run      # Full build and run
./run-app.sh build    # Build only
./run-app.sh test     # Build and test only
./run-app.sh clean    # Clean build artifacts
./run-app.sh rebuild  # Clean and full rebuild
```

#### Manual Maven Command
```bash
# Build and run tests
mvn clean compile test

# Run the application
mvn exec:java -Dexec.mainClass="de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp"
```

### macOS

#### Using the Bash Script
Make the script executable first:
```bash
chmod +x run-app-macos.sh
```

Then run:
```bash
# Run full build, test, and application (default)
./run-app-macos.sh

# Or with specific command
./run-app-macos.sh run      # Full build and run
./run-app-macos.sh build    # Build only
./run-app-macos.sh test     # Build and test only
./run-app-macos.sh clean    # Clean build artifacts
./run-app-macos.sh rebuild  # Clean and full rebuild
```

#### Manual Maven Command
```bash
# Build and run tests
mvn clean compile test

# Run the application
mvn exec:java -Dexec.mainClass="de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp"
```

## Available Commands

All scripts support the following commands:

| Command | Description |
|---------|-------------|
| `build` | Compile the project without running tests |
| `test` | Build and run all unit tests |
| `run` | Build, run tests, and prepare application (default) |
| `clean` | Remove all build artifacts |
| `rebuild` | Clean and perform a full rebuild with installation |

## Maven Lifecycle Commands

You can also use Maven directly with additional options:

```bash
# Skip tests during build
mvn clean compile -DskipTests

# Run specific test class
mvn test -Dtest=SocialAgentBuyerTest

# Run specific test method
mvn test -Dtest=SocialAgentBuyerTest#testBuyerInitialization

# Generate code coverage report
mvn clean test jacoco:report

# Build with verbose output
mvn clean compile -X

# Update dependencies
mvn dependency:resolve
```

## Troubleshooting

### Maven Not Found
If you get "mvn not found" error:
- **Windows**: Add Maven `bin` directory to system PATH
- **Linux/macOS**: Verify installation with `which mvn`

### Java Version Mismatch
If you get Java version errors:
- Check your Java version: `java -version`
- Update pom.xml if needed to match your Java version
- Current configuration uses Java 17

### Build Fails
1. Clean the project: `mvn clean`
2. Delete `.m2` cache (optional): `rm -rf ~/.m2/repository`
3. Try again: `mvn clean install`

### Test Failures
- Run tests in verbose mode: `mvn test -X`
- Run specific test: `mvn test -Dtest=TestClassName`
- Skip tests: `mvn clean compile -DskipTests`

## Project Structure

```
SocialRbSC-Examples/
├── src/
│   ├── main/java/               # Main source code
│   │   └── de/tudresden/socialrbscdeterministic/
│   │       └── examples/
│   │           ├── agents/      # Social agent implementations
│   │           ├── roles/       # Role implementations
│   │           └── compartments/# Compartment implementations
│   └── test/java/               # Test code
├── generatedsmartcontract/      # Generated Solidity contracts
│   └── contracts/               # Smart contracts
├── run-app.bat                  # Windows runner script
├── run-app.sh                   # Linux runner script
├── run-app-macos.sh             # macOS runner script
├── pom.xml                      # Maven configuration
└── README.md                    # Project documentation
```

## Performance Tips

1. **Use skipping tests for faster builds**: `mvn clean compile -DskipTests`
2. **Use offline mode**: `mvn -o clean compile` (after initial build)
3. **Increase heap memory**: `export MAVEN_OPTS="-Xmx1024m"` (Linux/macOS)
4. **Parallel build**: `mvn -T 1C clean install` (one thread per CPU core)

## IDE Integration

### IntelliJ IDEA / Eclipse
1. Open project as Maven project
2. Right-click project → Maven → Reload Project
3. Run tests via IDE test runner

### VS Code
1. Install Maven extension
2. Use command palette: "Maven: Execute Commands"

## Additional Resources

- [Maven Official Documentation](https://maven.apache.org/guides/)
- [JUnit 5 Documentation](https://junit.org/junit5/)
- [Solidity Documentation](https://docs.soliditylang.org/)
- [Build Troubleshooting Guide](../BUILD_TROUBLESHOOTING.md)
- [SocialRbSC Framework Documentation](../README.md)
- [Developer Setup Guide](../DEVELOPER.md)

## Getting Help

If you encounter issues:
1. Check the error message carefully
2. Run with verbose mode: `mvn -X`
3. Review project README and DEVELOPER.md
4. Check test output in `target/surefire-reports/`
