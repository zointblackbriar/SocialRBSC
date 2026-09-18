// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../usecase-dronemedicine/MedicinePlan.sol";
import "../staticroleassignment/Compartment.sol";
import "../staticroleassignment/Player.sol";

/**
 * @title Pharmacy
 * @dev Consumer-side compartment where a pharmacy verifies delivery contents.
 */
contract Pharmacy is Compartment {
    MedicinePlan public plan;
    Player public player;

    event ReceivedForVerification();
    event MedicineCorrect();

    constructor(address _planAddress, address _owner) {
        plan = MedicinePlan(_planAddress);
        player = new Player(_owner);
        createCompartmentID("Pharmacy");
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

    function getMyCompartmentID() external view returns(bytes32) {
        return getCompartmentID();
    }
}
