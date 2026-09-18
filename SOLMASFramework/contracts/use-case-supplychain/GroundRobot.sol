// SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";

// @title Ground Robot Agent role

/// @dev represents an autonomous ground vehicle.  Stub contract for the use
/// case; further logic can be added for navigation state, traffic alerts, etc.
contract GroundRobot is ComponentRole {
    uint public tripsMade;

    function recordTrip() public {
        tripsMade += 1;
    }

    function getTrips() public view returns (uint) {
        return tripsMade;
    }

    function playContractForIdentity() public pure returns (string memory) {
        return "ground robot";
    }
}
