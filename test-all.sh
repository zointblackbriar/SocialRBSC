#!/bin/bash

################################################################################
# Full Test and Verify Script for Linux/macOS
################################################################################
#
# This script:
# 1. Checks if Docker is running
# 2. Starts Ganache via Docker if available
# 3. Runs unit tests (mvn test) on all projects
# 4. Runs integration tests (mvn verify) on all projects
#
# Usage: ./test-all.sh [skip-docker] [test-type]
#   skip-docker - Skip Docker and Ganache setup (optional)
#   test-type   - Specify which tests to run: unit, integration, or all (optional)
#              Default: all
#
################################################################################

set -e

# Resolve script directory and ensure we run from repository root (script lives in repo root)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo ""
echo "Full Maven Test and Verify Script (Linux/macOS)"
echo ""

# Get command line parameters
SKIP_DOCKER=${1:-""}
TEST_TYPE=${2:-"all"}
echo "Test Type: $TEST_TYPE (unit/integration/all)"
echo ""

if [[ "$SKIP_DOCKER" == "skip-docker" ]]; then
    echo -e "${YELLOW}Docker check skipped by user${NC}"
    echo ""
else
    # Check if Docker is installed
    if ! command -v docker &> /dev/null; then
        echo -e "${YELLOW}[WARNING] Docker is not installed${NC}"
        echo -e "${YELLOW}[INFO] Skipping Docker and Ganache setup${NC}"
        echo ""
    else
        # Check if Docker daemon is running
        echo "Checking Docker status..."
        if ! docker info &> /dev/null; then
            echo -e "${YELLOW}[WARNING] Docker daemon is not running${NC}"
            echo -e "${BLUE}[INFO] Please start Docker Desktop or Docker daemon manually${NC}"
            echo -e "${YELLOW}To skip Docker setup, use: ./test-all.sh skip-docker${NC}"
            echo ""
            read -p "Continue without Docker? (y/n) " -n 1 -r
            echo ""
            if [[ ! $REPLY =~ ^[Yy]$ ]]; then
                exit 1
            fi
        else
            echo -e "${GREEN}✓ Docker is running${NC}"
            echo ""
            
            # Check if ganache-docker.sh exists
            if [[ -f "ganache-docker.sh" ]]; then
                echo "Starting Ganache via Docker..."
                echo ""
                bash ganache-docker.sh
                echo ""
                echo -e "${GREEN}✓ Ganache started${NC}"
                echo ""
                sleep 3
            else
                echo -e "${YELLOW}[WARNING] ganache-docker.sh not found${NC}"
                echo -e "${YELLOW}[WARNING] Skipping Ganache startup${NC}"
                echo ""
            fi
        fi
    fi
fi

echo "================================================================================"
echo "Starting Maven Tests and Verification"
echo "================================================================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}ERROR: Maven is not installed${NC}"
    echo "Please install Apache Maven: https://maven.apache.org/download.cgi"
    echo ""
    echo "On Ubuntu/Debian: sudo apt-get install maven"
    echo "On CentOS/RHEL: sudo yum install maven"
    echo "On macOS: brew install maven"
    exit 1
fi

echo -e "${BLUE}Detected Maven version:${NC}"
mvn --version
echo ""

# Get list of directories with pom.xml files
PROJECT_DIRS=()
while IFS= read -r -d '' dir; do
    PROJECT_DIRS+=("$dir")
done < <(find . -name "pom.xml" -type f -not -path "*/generatedsmartcontract/*" -print0)

if [[ ${#PROJECT_DIRS[@]} -eq 0 ]]; then
    echo -e "${RED}ERROR: No pom.xml files found in project${NC}"
    exit 1
fi

# Filter out unwanted projects (e.g., SOLMASFramework and hidden VCS dirs) and
# build a sanitized list that we'll actually run tests against. Print skip
# messages for clarity.
FILTERED_PROJECTS=()
for project_pom in "${PROJECT_DIRS[@]}"; do
    project_dir=$(dirname "$project_pom")
    # Skip SOLMASFramework module
    if [[ "$project_dir" == *"SOLMASFramework"* ]]; then
        echo -e "${YELLOW}Skipping $project_dir (SOLMASFramework - not a Maven module)${NC}"
        continue
    fi
    # Skip any project under a hidden directory like .git or .github
    if [[ "$project_dir" == *"/."* ]] || [[ "$project_dir" == *"\\."* ]]; then
        echo -e "${YELLOW}Skipping $project_dir (inside hidden directory)${NC}"
        continue
    fi
    # Skip any generated smart contract output directories (these aren't Maven projects)
    if [[ "$project_dir" == *"generatedsmartcontract"* ]]; then
        echo -e "${YELLOW}Skipping $project_dir (generated smart contract output)${NC}"
        continue
    fi
    # Double-check the pom.xml exists (should be true) and add to list
    if [[ -f "$project_dir/pom.xml" ]]; then
        FILTERED_PROJECTS+=("$project_pom")
    else
        echo -e "${YELLOW}Skipping $project_dir (no pom.xml)${NC}"
    fi
done

if [[ ${#FILTERED_PROJECTS[@]} -eq 0 ]]; then
    echo -e "${RED}ERROR: No Maven projects to process after filtering${NC}"
    exit 1
fi

TOTAL_PROJECTS=${#FILTERED_PROJECTS[@]}
echo -e "${BLUE}Found $TOTAL_PROJECTS Maven project(s) (after filtering)${NC}"
echo ""

# Store repository root (script directory)
ROOT_DIR="$SCRIPT_DIR"

# Phase 1: Run unit tests
if [[ "$TEST_TYPE" == "all" ]] || [[ "$TEST_TYPE" == "unit" ]]; then
    echo "================================================================================"
    echo "[Phase 1/2] Running mvn clean test (Unit Tests) on all projects"
    echo "================================================================================"
    echo ""

    PROJECT_NUM=0
    for project_pom in "${FILTERED_PROJECTS[@]}"; do
        ((PROJECT_NUM+=1))
        PROJECT_DIR=$(dirname "$project_pom")
        
        echo -e "${BLUE}[$PROJECT_NUM/$TOTAL_PROJECTS] Unit Tests: $PROJECT_DIR${NC}"
        # double-check pom exists
        if [[ ! -f "$PROJECT_DIR/pom.xml" ]]; then
            echo -e "${YELLOW}Skipping $PROJECT_DIR (no pom.xml)${NC}"
            continue
        fi
        cd "$PROJECT_DIR"
        
        if ! mvn clean test -T 1C -Dorg.slf4j.simpleLogger.defaultLogLevel=warn -Dskip.solidity.codegen=true; then
            echo -e "${RED}[ERROR] Unit tests failed in $PROJECT_DIR${NC}"
            cd "$ROOT_DIR"
            echo ""
            echo "================================================================================"
            echo -e "${RED}Unit Tests FAILED${NC}"
            echo "================================================================================"
            exit 1
        fi
        
        cd "$ROOT_DIR"
    done

    echo ""
fi

# Phase 2: Run integration tests
if [[ "$TEST_TYPE" == "all" ]] || [[ "$TEST_TYPE" == "integration" ]]; then
    echo "================================================================================"
    echo "[Phase 2/2] Running mvn verify (Integration Tests) on all projects"
    echo "================================================================================"
    echo ""

    PROJECT_NUM=0
    for project_pom in "${FILTERED_PROJECTS[@]}"; do
        ((PROJECT_NUM+=1))
        PROJECT_DIR=$(dirname "$project_pom")
        
        echo -e "${BLUE}[$PROJECT_NUM/$TOTAL_PROJECTS] Integration Tests: $PROJECT_DIR${NC}"
        # double-check pom exists
        if [[ ! -f "$PROJECT_DIR/pom.xml" ]]; then
            echo -e "${YELLOW}Skipping $PROJECT_DIR (no pom.xml)${NC}"
            continue
        fi
        cd "$PROJECT_DIR"
        
        if ! mvn verify -T 1C -Dorg.slf4j.simpleLogger.defaultLogLevel=warn -Dskip.solidity.codegen=true; then
            echo -e "${RED}[ERROR] Integration tests failed in $PROJECT_DIR${NC}"
            cd "$ROOT_DIR"
            echo ""
            echo "================================================================================"
            echo -e "${RED}Integration Tests FAILED${NC}"
            echo "================================================================================"
            exit 1
        fi
        
        cd "$ROOT_DIR"
    done
fi

echo ""
echo "================================================================================"
echo -e "${GREEN}✓ All requested tests completed successfully!${NC}"
echo "================================================================================"
echo ""

exit 0
