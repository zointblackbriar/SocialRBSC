// SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";

// @title Drone Agent role

/// @dev represents an aerial delivery unit.  The contract is intentionally
/// minimal and may be extended with sensor data and status reports.
contract DroneAgent is ComponentRole {
    uint public deliveriesCompleted;

    function recordDelivery() public {
        deliveriesCompleted += 1;
    }

    function getDeliveries() public view returns (uint) {
        return deliveriesCompleted;
    }

    function playContractForIdentity() public pure returns (string memory) {
        return "drone agent";
    }
}
