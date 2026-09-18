#!/usr/bin/env bash
# ================================================================
# SocialRbSC - Run the examples (Linux/macOS)
# ================================================================
#
# Builds the framework, generates Solidity, runs unit/integration
# tests, starts Ganache, and launches the example application.
#
# Usage: ./run-examples.sh [command]
# Commands:
#   setup        - Build parent framework and examples (default)
#   build        - Build the whole project
#   test         - Build and run unit tests (no Ganache required)
#   integration  - Build and run integration tests (needs Ganache)
#   run          - Build and launch the example application
#   ganache      - Start a local Ganache node (deterministic accounts)
#   solidity     - Compile SOLMASFramework Solidity contracts
#   clean        - Remove all build artifacts
#   help         - Show this help
# ================================================================

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT_DIR="$SCRIPT_DIR"
EXAMPLES_DIR="$SCRIPT_DIR/SocialRbSC-Examples"
SOLMAS_DIR="$SCRIPT_DIR/SOLMASFramework"

MAIN_CLASS="de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp"
GANACHE_MNEMONIC="world reopen cute forward vintage okay drink margin piano buffalo autumn awful"

RED='\033[0;31m'; GREEN='\033[0;32m'; YELLOW='\033[1;33m'; BLUE='\033[0;34m'; NC='\033[0m'

command="${1:-help}"

step() { echo ""; echo -e "${BLUE}==>${NC} $*"; }
ok()   { echo -e "${GREEN}✓${NC} $*"; }
fail() { echo -e "${RED}✗${NC} $*"; exit 1; }

require_mvn() {
    command -v mvn >/dev/null 2>&1 || fail "Maven is not installed or not in PATH. Install Apache Maven and retry."
}

run_mvn() {
    ( cd "$2" && echo "[mvn] $1" && mvn $1 ) || fail "Maven command failed: mvn $1"
}

help() {
    cat <<EOF

SocialRbSC - Run Examples

Usage: ./run-examples.sh [command]

Commands:
  setup        Build parent framework and examples (default full build)
  build        Build the whole project (SocialRBSC-core + SocialRbSC-Examples)
  test         Build and run unit tests (no Ganache required)
  integration  Build and run integration tests (requires Ganache on localhost:8545)
  run          Build and launch the example application
  ganache      Start a local Ganache node with the project's deterministic accounts
  solidity     Compile the SOLMASFramework Solidity contracts (Hardhat)
  clean        Remove all build artifacts
  help         Show this help

Typical workflow:
  1. ./run-examples.sh ganache       # in one terminal
  2. ./run-examples.sh integration   # in another terminal
  3. ./run-examples.sh run

EOF
}

case "$command" in
    help)
        help
        ;;
    setup)
        require_mvn
        step "Building parent framework and examples (skip tests)"
        run_mvn "clean install -DskipTests" "$ROOT_DIR"
        ok "Setup complete. Run './run-examples.sh test' or './run-examples.sh run'."
        ;;
    build)
        require_mvn
        step "Building the full project (SocialRBSC-core + SocialRbSC-Examples)"
        run_mvn "clean install -DskipTests" "$ROOT_DIR"
        ok "Build complete."
        ;;
    test)
        require_mvn
        step "Building parent framework"
        run_mvn "clean install -DskipTests" "$ROOT_DIR"
        step "Running unit tests"
        run_mvn "test" "$EXAMPLES_DIR"
        ok "Unit tests complete."
        ;;
    integration)
        require_mvn
        step "Building parent framework"
        run_mvn "clean install -DskipTests" "$ROOT_DIR"
        step "Running integration tests (requires Ganache on localhost:8545)"
        run_mvn "verify" "$EXAMPLES_DIR"
        ok "Integration tests complete."
        ;;
    run)
        require_mvn
        step "Building parent framework"
        run_mvn "clean install -DskipTests" "$ROOT_DIR"
        step "Launching example application: $MAIN_CLASS"
        ( cd "$EXAMPLES_DIR" && mvn org.codehaus.mojo:exec-maven-plugin:3.1.0:java "-Dexec.mainClass=$MAIN_CLASS" ) \
            || fail "exec:java failed"
        ;;
    ganache)
        step "Starting Ganache with deterministic accounts"
        echo "Mnemonic: $GANACHE_MNEMONIC"
        if command -v ganache >/dev/null 2>&1; then
            ganache -m "$GANACHE_MNEMONIC"
        else
            npx ganache -m "$GANACHE_MNEMONIC"
        fi
        ;;
    solidity)
        step "Compiling SOLMASFramework Solidity contracts (Hardhat)"
        ( cd "$SOLMAS_DIR" && npx hardhat compile ) || fail "hardhat compile failed"
        ok "Solidity compile complete."
        ;;
    clean)
        step "Cleaning Maven builds"
        run_mvn "clean" "$ROOT_DIR"
        step "Cleaning Solidity artifacts"
        ( cd "$SOLMAS_DIR" && npx hardhat clean ) || fail "hardhat clean failed"
        ok "Clean complete."
        ;;
    *)
        echo -e "${RED}ERROR: Unknown command: $command${NC}" >&2
        help
        exit 1
        ;;
esac
