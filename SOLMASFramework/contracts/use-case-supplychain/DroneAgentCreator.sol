// SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

import "../staticroleassignmentinitiator/RoleCreator.sol";
import "./DroneAgent.sol";

contract DroneAgentCreator is RoleCreator {
    function createFor(bytes32 _spec) override public returns (ComponentRole) {
        return new DroneAgent();
    }
}
