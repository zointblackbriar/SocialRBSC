// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../usecase-dronemedicine/MedicinePlan.sol";
import "../staticroleassignment/Compartment.sol";
import "../staticroleassignment/Player.sol";

/**
 * @title Patient
 * @dev Represents the patient confirming receipt and benefit from medicine.
 */
contract Patient is Compartment {
    MedicinePlan public plan;
    Player public player;
    address public pharmacyAddress;

    event PatientReceived();
    event BenefitAcknowledged();

    constructor(address _planAddress, address _pharmacyAddress, address _owner) {
        plan = MedicinePlan(_planAddress);
        pharmacyAddress = _pharmacyAddress;
        player = new Player(_owner);
        createCompartmentID("Patient");
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

    function getMyCompartmentID() external view returns(bytes32) {
        return getCompartmentID();
    }
}
