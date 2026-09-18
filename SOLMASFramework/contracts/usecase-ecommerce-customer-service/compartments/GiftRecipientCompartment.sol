// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/Compartment.sol";

/**
 * @title GiftRecipientCompartment
 * @dev Compartment representing the gift recipient (secondary human actor).
 */
contract GiftRecipientCompartment is Compartment {
    constructor() {
        createCompartmentID("GiftRecipient");
    }
}
