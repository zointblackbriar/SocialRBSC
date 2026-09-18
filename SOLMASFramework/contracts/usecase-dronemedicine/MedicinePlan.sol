// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

/**
 * @title MedicinePlan
 * @dev Plan structure tracking the delivery of medicine by a drone, verification by
 * a pharmacy, and confirmation by a patient. Events are emitted for each stage.
 */
contract MedicinePlan {

    // Events emitted when parts of the plan change
    event MedicineDelivered();
    event MedicineVerified();
    event PatientNotified();
    event PlanReset();

    struct PlanStatus {
        bool medicineDelivered;
        bool medicineVerified;
        bool patientNotified;
    }

    PlanStatus public currentPlan;

    constructor() {
        currentPlan.medicineDelivered = false;
        currentPlan.medicineVerified = false;
        currentPlan.patientNotified = false;
    }

    function resetPlan() external {
        currentPlan.medicineDelivered = false;
        currentPlan.medicineVerified = false;
        currentPlan.patientNotified = false;
        emit PlanReset();
    }

    function markDelivered() external {
        require(!currentPlan.medicineDelivered, "Medicine already delivered");
        currentPlan.medicineDelivered = true;
        emit MedicineDelivered();
    }

    function markVerified() external {
        require(currentPlan.medicineDelivered, "Medicine must be delivered first");
        require(!currentPlan.medicineVerified, "Medicine already verified");
        currentPlan.medicineVerified = true;
        emit MedicineVerified();
    }

    function markPatientNotified() external {
        require(currentPlan.medicineVerified, "Medicine must be verified first");
        require(!currentPlan.patientNotified, "Patient already notified");
        currentPlan.patientNotified = true;
        emit PatientNotified();
    }
}
