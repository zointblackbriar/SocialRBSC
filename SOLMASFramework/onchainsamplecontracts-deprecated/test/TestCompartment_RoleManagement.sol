// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0;

import "truffle/Assert.sol";
import "../contracts/staticroleassignment/ComponentCore.sol";
import "../contracts/staticroleassignment/InterfaceIds.sol";
import "../contracts/roleinitiator/InitiatorCompartment.sol";

contract TestCompartment_RoleManagement {

    ComponentCore internal component;
    Compartment internal compartment;

    function beforeEach() public {
        component = new ComponentCore();
        compartment = new InitiatorCompartment();
    }

    function testRetrieveTeamAfterActivating() public {
        // Given
        component.activateCompartment(address(compartment));

        // When
        address currentCompartment = component.getActiveCompartment();

        // Then
        Assert.equal(currentCompartment, address(compartment), "");
    }

    function testRetrieveTeamAfterDeactivating() public {
        // Given
        component.activateCompartment(address(compartment));
        component.deactivateCompartment();

        // When
        address currentCompartment = component.getActiveCompartment();

        // Then
        Assert.isZero(currentCompartment, "");
    }
}
