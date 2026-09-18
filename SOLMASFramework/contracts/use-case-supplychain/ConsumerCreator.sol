// SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

import "../staticroleassignmentinitiator/RoleCreator.sol";
import "./Consumer.sol";

contract ConsumerCreator is RoleCreator {
    function createFor(bytes32 _spec) override public returns (ComponentRole) {
        return new Consumer();
    }
}
