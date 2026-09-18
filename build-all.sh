#!/bin/bash

################################################################################
# Build and Install SocialRbSC Framework and Examples
# For Linux and macOS Systems
################################################################################
#
# This script builds the parent SocialRbSC framework first, then the Examples
#
# Usage: ./build-all.sh [command]
# Commands:
#   setup        - Build parent and examples (default)
#   parent       - Build parent framework only
#   examples     - Build examples only (requires parent built)
#   clean        - Clean all builds
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
echo "SocialRbSC Framework and Examples - Build Script (Linux/macOS)"
echo "================================================================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}ERROR: Maven is not installed or not in PATH${NC}"
    exit 1
fi

# Get the script directory
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PARENT_DIR="$SCRIPT_DIR"
EXAMPLES_DIR="$SCRIPT_DIR/SocialRbSC-Examples"

# Set command (default to setup)
COMMAND=${1:-setup}

case "$COMMAND" in
    setup)
        echo -e "${YELLOW}[1/2]${NC} Building parent SocialRbSC framework..."
        cd "$PARENT_DIR"
        if mvn clean install -DskipTests; then
            echo -e "${GREEN}✓ Parent framework built successfully${NC}"
        else
            echo -e "${RED}✗ Parent framework build failed${NC}"
            exit 1
        fi
        echo ""
        echo -e "${YELLOW}[2/2]${NC} Building SocialRbSC Examples..."
        cd "$EXAMPLES_DIR"
        if mvn clean install; then
            echo -e "${GREEN}✓ Examples built successfully${NC}"
            echo ""
            echo "================================================================================"
            echo -e "${GREEN}Build Complete!${NC}"
            echo "================================================================================"
            echo ""
            echo "To run the application:"
            echo "  cd SocialRbSC-Examples"
            echo "  mvn exec:java -Dexec.mainClass=\"de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp\""
            echo ""
        else
            echo -e "${RED}✗ Examples build failed${NC}"
            exit 1
        fi
        ;;
    parent)
        echo -e "${YELLOW}Building parent SocialRbSC framework...${NC}"
        cd "$PARENT_DIR"
        if mvn clean install -DskipTests; then
            echo -e "${GREEN}✓ Parent framework built successfully${NC}"
        else
            echo -e "${RED}✗ Parent framework build failed${NC}"
            exit 1
        fi
        ;;
    examples)
        echo -e "${YELLOW}Building SocialRbSC Examples...${NC}"
        cd "$EXAMPLES_DIR"
        if mvn clean install; then
            echo -e "${GREEN}✓ Examples built successfully${NC}"
        else
            echo -e "${RED}✗ Examples build failed${NC}"
            exit 1
        fi
        ;;
    clean)
        echo -e "${YELLOW}Cleaning all builds...${NC}"
        cd "$PARENT_DIR"
        mvn clean
        cd "$EXAMPLES_DIR"
        mvn clean
        echo -e "${GREEN}✓ Clean completed${NC}"
        ;;
    *)
        echo -e "${RED}ERROR: Unknown command: $COMMAND${NC}"
        echo ""
        echo "Valid commands:"
        echo "  setup        - Build parent and examples (default)"
        echo "  parent       - Build parent framework only"
        echo "  examples     - Build examples only"
        echo "  clean        - Clean all builds"
        exit 1
        ;;
esac

exit 0
