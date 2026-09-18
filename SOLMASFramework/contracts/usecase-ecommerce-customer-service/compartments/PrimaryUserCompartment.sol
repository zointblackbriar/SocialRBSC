// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/Compartment.sol";

/**
 * @title PrimaryUserCompartment
 * @dev Compartment representing the primary human customer.
 */
contract PrimaryUserCompartment is Compartment {
    constructor() {
        createCompartmentID("PrimaryUser");
    }
}
