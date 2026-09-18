#!/bin/bash

################################################################################
# SocialRbSC Examples - Maven Build and Run Script for macOS
################################################################################
#
# This script builds and runs the SocialRbSC Examples project
# on macOS systems using Apache Maven.
#
# Usage: ./run-app-macos.sh [command]
# Commands:
#   build        - Build the project only
#   test         - Build and run tests only
#   run          - Build and run the application (default)
#   clean        - Clean build artifacts
#   rebuild      - Clean and rebuild the project
#
# Note: On macOS, you can also use Homebrew to install Maven:
#       brew install maven
#
################################################################################

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo ""
echo "================================================================================"
echo "SocialRbSC Examples - Maven Application Runner (macOS)"
echo "================================================================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}ERROR: Maven is not installed or not in PATH${NC}"
    echo "Please install Apache Maven and add it to your system PATH"
    echo ""
    echo "Using Homebrew (recommended):"
    echo "  brew install maven"
    echo ""
    echo "Or visit: https://maven.apache.org/download.cgi"
    echo ""
    exit 1
fi

echo -e "${BLUE}Detected Maven version:${NC}"
mvn --version
echo ""

# Check for Java installation
if ! command -v java &> /dev/null; then
    echo -e "${RED}ERROR: Java is not installed${NC}"
    echo "Please install Java Development Kit (JDK)"
    echo ""
    echo "Using Homebrew:"
    echo "  brew install openjdk@17"
    echo ""
    exit 1
fi

echo -e "${BLUE}Detected Java version:${NC}"
java -version
echo ""

# Get the script directory
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

# Set command (default to run)
COMMAND=${1:-run}

echo -e "${BLUE}Project Directory:${NC} $(pwd)"
echo ""

# Execute command
case "$COMMAND" in
    clean)
        echo -e "${YELLOW}[1/1]${NC} Cleaning build artifacts..."
        if mvn clean; then
            echo ""
            echo -e "${GREEN}✓ Clean completed successfully${NC}"
        else
            echo ""
            echo -e "${RED}✗ Clean failed${NC}"
            exit 1
        fi
        ;;
    build)
        echo -e "${YELLOW}[1/1]${NC} Building project..."
        if mvn clean compile; then
            echo ""
            echo -e "${GREEN}✓ Build completed successfully${NC}"
        else
            echo ""
            echo -e "${RED}✗ Build failed${NC}"
            exit 1
        fi
        ;;
    test)
        echo -e "${YELLOW}[1/2]${NC} Building project..."
        if ! mvn clean compile; then
            echo ""
            echo -e "${RED}✗ Build failed${NC}"
            exit 1
        fi
        echo -e "${GREEN}✓ Build completed${NC}"
        echo ""
        echo -e "${YELLOW}[2/2]${NC} Running tests..."
        if mvn test; then
            echo ""
            echo -e "${GREEN}✓ Tests completed successfully${NC}"
        else
            echo ""
            echo -e "${RED}✗ Tests failed${NC}"
            exit 1
        fi
        ;;
    rebuild)
        echo -e "${YELLOW}[1/1]${NC} Cleaning and rebuilding project..."
        if mvn clean install; then
            echo ""
            echo -e "${GREEN}✓ Rebuild completed successfully${NC}"
        else
            echo ""
            echo -e "${RED}✗ Rebuild failed${NC}"
            exit 1
        fi
        ;;
    run)
        echo -e "${YELLOW}[1/3]${NC} Cleaning previous builds..."
        if ! mvn clean; then
            echo ""
            echo -e "${RED}✗ Clean failed${NC}"
            exit 1
        fi
        echo -e "${GREEN}✓ Clean completed${NC}"
        echo ""
        echo -e "${YELLOW}[2/3]${NC} Building project..."
        if ! mvn compile; then
            echo ""
            echo -e "${RED}✗ Build failed${NC}"
            exit 1
        fi
        echo -e "${GREEN}✓ Build completed${NC}"
        echo ""
        echo -e "${YELLOW}[3/3]${NC} Running tests..."
        if mvn test; then
            echo ""
            echo -e "${GREEN}✓ Tests completed successfully${NC}"
            echo ""
            echo "================================================================================"
            echo -e "${GREEN}Application Ready!${NC}"
            echo "================================================================================"
            echo ""
            echo "To run the main application:"
            echo "  mvn exec:java -Dexec.mainClass=\"de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp\""
            echo ""
        else
            echo ""
            echo -e "${RED}✗ Tests failed${NC}"
            echo "Application will not run due to test failures"
            exit 1
        fi
        ;;
    *)
        echo -e "${RED}ERROR: Unknown command: $COMMAND${NC}"
        echo ""
        echo "Valid commands:"
        echo "  build        - Build the project only"
        echo "  test         - Build and run tests only"
        echo "  run          - Build and run the application (default)"
        echo "  clean        - Clean build artifacts"
        echo "  rebuild      - Clean and rebuild the project"
        echo ""
        exit 1
        ;;
esac

echo ""
echo "================================================================================"
echo -e "${GREEN}Script completed successfully!${NC}"
echo "================================================================================"
echo ""

exit 0
