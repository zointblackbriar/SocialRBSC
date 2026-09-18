// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";
import "../usecase-dronemedicine/MedicinePlan.sol";
import "../staticroleassignment/Player.sol";
import "../societypattern/ConcreteMediator.sol";
import "../societypattern/SocialAgent.sol";
import "../societypattern/Society.sol";

/// @dev role contract representing the drone. Contains identical
/// behaviour to the Drone compartment; the role is used when the agent
/// is acting in the drone capacity.
contract DroneRole is SocialAgent, Society {
    MedicinePlan public plan;
    Player public player;

    // events describing drone actions
    event Departure();
    event ArrivedAtPatient();


    constructor(address _planAddress, address _owner) {
        plan = MedicinePlan(_planAddress);
        player = new Player(_owner);
        // optional: compartment id may not be strictly needed
    }

    function deliverMedicine() external {
        (bool delivered, , ) = plan.currentPlan();
        require(!delivered, "Medicine already delivered");

        // event Departure would be emitted here
        // event ArrivedAtPatient would be emitted here
        plan.markDelivered();
    }

    function checkReached() external view returns (bool) {
        (bool delivered, , ) = plan.currentPlan();
        return delivered;
    }

    function playRoleContract(address _contract, string calldata _fn) external returns(bool) {
        return player.playDelegateCallRoleContract(_contract, _fn);
    }

} 
