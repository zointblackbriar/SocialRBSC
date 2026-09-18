// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";
import "./DroneRole.sol";
import "./MedicineRole.sol";
import "./PatientRole.sol";
import "./PharmacyRole.sol";
import "../staticroleassignmentinitiator/RoleCreator.sol";

// The following creator contracts are specific to the drone‑medicine use case.
// They are placed in the usecase folder so that domain code and related
// factory logic live together.

contract DroneRoleCreator is RoleCreator {
    function createFor(bytes32 _roleAddress) override public returns (ComponentRole) {
        return new DroneRole(address(0), address(0));
    }
}

contract PharmacyRoleCreator is RoleCreator {
    function createFor(bytes32 _roleAddress) override public returns (ComponentRole) {
        return new PharmacyRole(address(0), address(0));
    }
}

contract PatientRoleCreator is RoleCreator {
    function createFor(bytes32 _roleAddress) override public returns (ComponentRole) {
        return new PatientRole(address(0), address(0), address(0));
    }
}

