// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/Compartment.sol";

/**
 * @title TicketingSystemCompartment
 * @dev Represents the alert/ticketing service the agent uses for escalations.
 */
contract TicketingSystemCompartment is Compartment {
    constructor() {
        createCompartmentID("TicketingSystem");
    }
}
