// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";
import "../usecase-dronemedicine/MedicinePlan.sol";
import "../staticroleassignment/Player.sol";
import "../societypattern/ConcreteMediator.sol";
import "../societypattern/SocialAgent.sol";
import "../societypattern/Society.sol";

/// @dev role contract for the pharmacy.  Mirrors the behaviour
/// of the Pharmacy compartment but attaches to a ComponentRole.
contract PharmacyRole is SocialAgent, Society {
    MedicinePlan public plan;
    Player public player;

    event ReceivedForVerification();
    event MedicineCorrect();


    constructor(address _planAddress, address _owner) {
        plan = MedicinePlan(_planAddress);
        player = new Player(_owner);
    }

    function verifyMedicine() external {
        (bool delivered, bool verified, ) = plan.currentPlan();
        require(delivered, "Medicine has not been delivered yet");
        require(!verified, "Medicine already verified");

        emit ReceivedForVerification();
        emit MedicineCorrect();
        plan.markVerified();
    }

    function playRoleContract(address _contract, string calldata _fn) external returns(bool) {
        return player.playDelegateCallRoleContract(_contract, _fn);
    }

} 
