// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/Compartment.sol";

/**
 * @title ShoppingAssistantCompartment
 * @dev Compartment representing the autonomous shopping assistant agent.
 */
contract ShoppingAssistantCompartment is Compartment {
    constructor() {
        createCompartmentID("ShoppingAssistantAgent");
    }
}
