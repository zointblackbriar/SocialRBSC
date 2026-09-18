// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";
import "../usecase-dronemedicine/MedicinePlan.sol";
import "../staticroleassignment/Player.sol";
import "../societypattern/ConcreteMediator.sol";
import "../societypattern/SocialAgent.sol";
import "../societypattern/Society.sol";

/// @dev role contract for the patient. Contains the methods to confirm
/// receipt that were in the Patient compartment.
contract PatientRole is SocialAgent, Society {
    MedicinePlan public plan;
    Player public player;
    address public pharmacyAddress;

    event PatientReceived();
    event BenefitAcknowledged();


    constructor(address _planAddress, address _pharmacyAddress, address _owner) {
        plan = MedicinePlan(_planAddress);
        pharmacyAddress = _pharmacyAddress;
        player = new Player(_owner);
    }

    function confirmReceipt() external {
        (, bool verified, bool notified) = plan.currentPlan();
        require(verified, "Medicine must be verified by pharmacy first");
        require(!notified, "Patient has already confirmed");

        emit PatientReceived();
        emit BenefitAcknowledged();
        plan.markPatientNotified();
    }

    function playRoleContract(address _contract, string calldata _fn) external returns(bool) {
        return player.playDelegateCallRoleContract(_contract, _fn);
    }

} 
