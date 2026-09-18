# Use Case: Supply Chain

This directory contains a simple role-based implementation modelling elements of
an autonomous delivery supply chain.  Contracts are organised around actors
(roles) and their corresponding creators.

## Contents

- `Retailer.sol`, `Wholesaler.sol` – existing commercial actors.
- `Coordinator.sol`, `DroneAgent.sol`, `GroundRobot.sol`, `Consumer.sol`
  – added stubs for primary agents described in the actor model.
- Creator contracts for each role (`*Creator.sol`) used by `Production.sol`.
- `Production.sol` – top‑level compartment that can instantiate any of the
  available roles via a spec string.
- `USE_CASE_SUPPLYCHAIN.md` – comprehensive documentation of the actors,
  responsibilities and interaction patterns.

The design follows the Component/Compartment abstraction defined in the
`staticroleassignment` library and is intended for extension as the
use‑case grows.
