// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";
import "../usecase-dronemedicine/DroneRole.sol";
import "../usecase-dronemedicine/MedicineRole.sol";
import "../usecase-dronemedicine/PatientRole.sol";
import "./RoleCreator.sol";
import "./RoleInitiator.sol";

/// @dev provides a simple role creator entry point; can choose concrete roles.
contract RoleCreatorInitiator is RoleCreator {
    function createFor(bytes32 _roleAddress) override public returns (ComponentRole) {
        // default example return
        return new DroneRole(address(0), address(0));
    }
}

// legacy examples left for reference
contract SampleRole1Creator is RoleCreator {
    function createFor(bytes32 _roleAddress) override public returns (ComponentRole) {
        return new SampleRole1();
    }
}

contract SampleRole1UpdatedCreator is RoleCreator {
    function createFor(bytes32 _roleAddress) override public returns (ComponentRole) {
        return new SampleRole1Updated();
    }
}

contract SampleRole2Creator is RoleCreator {
    function createFor(bytes32 _roleAddress) override public returns (ComponentRole) {
        return new SampleRole2();
    }
}



