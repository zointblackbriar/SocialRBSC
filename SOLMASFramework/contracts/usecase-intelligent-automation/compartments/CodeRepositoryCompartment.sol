// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/Compartment.sol";

/**
 * @title CodeRepositoryCompartment
 * @dev Represents the CI/CD pipeline or code repository entity.
 */
contract CodeRepositoryCompartment is Compartment {
    constructor() {
        createCompartmentID("CodeRepository");
    }
}
