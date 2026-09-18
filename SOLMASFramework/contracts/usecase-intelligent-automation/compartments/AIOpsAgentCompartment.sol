// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/Compartment.sol";

/**
 * @title AIOpsAgentCompartment
 * @dev Compartment representing the autonomous AIOps agent itself.  In a
 * deployed system the agent would occupy this compartment and perform
 * monitoring, diagnostics and remediation activities on the managed
 * environment.
 */
contract AIOpsAgentCompartment is Compartment {
    constructor() {
        // give the compartment an identifier for debugging / lookup
        createCompartmentID("AIOpsAgent");
    }
}
