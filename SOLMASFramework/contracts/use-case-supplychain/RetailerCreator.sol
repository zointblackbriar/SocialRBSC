// SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

import "../staticroleassignmentinitiator/RoleCreator.sol";
import "./Retailer.sol";

// @title Supply Chain Network implementation

contract RetailerCreator is RoleCreator {
    function createFor(bytes32 _spec) public override returns (ComponentRole) {
        return new Retailer();
    }
}



