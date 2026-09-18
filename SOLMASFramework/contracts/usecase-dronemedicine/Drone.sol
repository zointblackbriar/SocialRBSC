// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../usecase-dronemedicine/MedicinePlan.sol";
import "../staticroleassignment/Compartment.sol";
import "../staticroleassignment/Player.sol";

/**
 * @title Drone
 * @dev Supplier-side compartment representing the drone delivering medicine.
 *      Inherits `Compartment` and creates a `Player` to manage role assignments.
 */
contract Drone is Compartment {
    MedicinePlan public plan;
    Player public player;

    // events describing drone actions
    event Departure();
    event ArrivedAtPatient();

    constructor(address _planAddress, address _owner) {
        plan = MedicinePlan(_planAddress);
        // create a player whose owner can invoke role-related operations
        player = new Player(_owner);
        // assign an identifier to this compartment
        createCompartmentID("Drone");
    }

    /// @notice simulate delivery of medicine
    function deliverMedicine() external {
        (bool delivered, , ) = plan.currentPlan();
        require(!delivered, "Medicine already delivered");

        emit Departure();
        emit ArrivedAtPatient();
        plan.markDelivered();
    }

    /// @notice check whether the medicine has reached the patient
    function checkReached() external view returns (bool) {
        (bool delivered, , ) = plan.currentPlan();
        return delivered;
    }

    /// @dev wrapper around Player to play a role contract by delegatecall
    function playRoleContract(address _contract, string calldata _fn) external returns(bool) {
        return player.playDelegateCallRoleContract(_contract, _fn);
    }

    /// @dev expose compartment identifier
    function getMyCompartmentID() external view returns(bytes32) {
        return getCompartmentID();
    }
}
