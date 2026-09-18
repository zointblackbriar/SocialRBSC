// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/Compartment.sol";

/**
 * @title MonitoringStackCompartment
 * @dev Represents the observability layer (metrics/logs) that feeds the agent.
 */
contract MonitoringStackCompartment is Compartment {
    constructor() {
        createCompartmentID("MonitoringStack");
    }
}
