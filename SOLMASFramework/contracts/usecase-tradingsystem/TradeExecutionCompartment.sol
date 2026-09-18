// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../staticroleassignment/Compartment.sol";

contract TradeExecutionCompartment is Compartment {
    constructor() {
        createCompartmentID("TradeExecutionCompartment");
    }
}
