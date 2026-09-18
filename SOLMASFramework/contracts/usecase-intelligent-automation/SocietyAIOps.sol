// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../societypattern/Society.sol";

/**
 * @title SocietyAIOps
 * @dev Represents the overall society encompassing all compartments/actors
 * in the automated IT operations use case.
 */
contract SocietyAIOps is Society {
    constructor() Society() {
        // nothing special
    }
}
