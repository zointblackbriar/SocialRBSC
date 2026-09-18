// SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

import "../staticroleassignmentinitiator/RoleCreator.sol";
import "./Wholesaler.sol";

// @title Supply Chain Network implementation

contract WholesalerCreator is RoleCreator {
//    function createFor() external override returns (ComponentRole)
    function createFor(bytes32 _spec) override public returns (ComponentRole) {
        return new Wholesaler();
    }
}



