// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/Compartment.sol";

/**
 * @title CloudInfrastructureCompartment
 * @dev Represents the target infrastructure being monitored by the agent.
 * In the smart contract model it acts as a passive compartment that can be
 * referenced but does not itself execute business logic.
 */
contract CloudInfrastructureCompartment is Compartment {
    constructor() {
        createCompartmentID("CloudInfrastructure");
    }
}
