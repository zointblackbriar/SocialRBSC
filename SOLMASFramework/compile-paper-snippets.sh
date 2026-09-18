#!/usr/bin/env bash
# ================================================================
# Compile the Solidity contracts referenced by the paper's appendix
# (ConcreteMediator, MedicalSupplyMission, DeliveryProviderRole)
# and fail on any compilation error.
# ================================================================
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT"

echo
echo "Compiling SOLMASFramework contracts (including paper snippets)..."
npx hardhat compile

echo
echo "Verifying paper snippet artifacts..."
test -f "artifacts/contracts/societypattern/ConcreteMediator.sol/ConcreteMediator.json" \
  || { echo "Missing artifact: societypattern/ConcreteMediator.sol"; exit 1; }
test -f "artifacts/contracts/paper_snippets/MedicalSupplyMission.sol/MedicalSupplyMission.json" \
  || { echo "Missing artifact: paper_snippets/MedicalSupplyMission.sol"; exit 1; }
test -f "artifacts/contracts/paper_snippets/DeliveryProviderRole.sol/DeliveryProviderRole.json" \
  || { echo "Missing artifact: paper_snippets/DeliveryProviderRole.sol"; exit 1; }

echo
echo "SUCCESS: all paper snippet contracts compiled without error."
echo "  - societypattern/ConcreteMediator.sol"
echo "  - paper_snippets/MedicalSupplyMission.sol"
echo "  - paper_snippets/DeliveryProviderRole.sol"
