// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;
import "truffle/Assert.sol";
import "../contracts/staticroleassignment/ComponentCore.sol";
import "../contracts/roleinitiator/InitiatorRole.sol";
import "../contracts/roleinitiator/InitiatorRoleCreator.sol";
import "../contracts/roleinitiator/InitiatorCompartment.sol";

contract TestComponentCore_RoleManagement {
    ComponentCore internal component;
    Compartment internal compartment;

    bytes32 f1spec = keccak256("FAKEROLE1");
    bytes32 f2spec = keccak256("FAKEROLE2");

    function beforeEach() public {
        component = new ComponentCore();

        compartment = new InitiatorCompartment();
        compartment.addRoleCreator(f1spec, new InitiatorRole1Creator());
        compartment.addRoleCreator(f2spec, new InitiatorRole2Creator());
        compartment.addRole(f1spec);
        compartment.addRole(f2spec);

        component.activateCompartment(address(compartment));
    }

    function testAddRoleDoesNotAddANewRoleIfItHasAZeroAddress() public {
        // Given
        address role1 = compartment.getRole(f1spec);
        component.addRole(f1spec, role1);

        // When
        component.addRole(f1spec, address(0));

        // Then
        Assert.equal(component.getRole(f1spec), role1, "");
    }

    function testAddRoleOverwritesTheCurrentRoleForTheSameSpec() public {
        // Given
        address role1 = compartment.getRole(f1spec);
        component.addRole(f1spec, role1);

//      The following code snippet has an error
//      compartment.addRoleCreator(f1spec, new FakeRole1UpdatedCreator());
        compartment.addRole(f1spec);
        address role1Updated = compartment.getRole(f1spec);
        component.addRole(f1spec, role1Updated);
        Assert.equal(component.getRole(f1spec), role1Updated, "");
    }

    function testWeCanRetrieveTheRoleAfterItIsAdded() public {
        // Given
        address role1 = compartment.getRole(f1spec);
        component.addRole(f1spec, role1);

        // When
        address receivedRole = component.getRole(f1spec);

        // Then
        Assert.equal(receivedRole, role1, "");
    }

    function testGetRoleReturnsEmptyAddressForAnUnknownRole() public {
        // When
        address receivedRole = component.getRole(f2spec);

        // Then
        Assert.isZero(receivedRole, "");
    }
//
    function testIsPlayingRoleReturnsTrueWhenRoleIsAdded() public {
        // Given
        address role1 = compartment.getRole(f1spec);
        component.addRole(f1spec, role1);

        // When
        bool isPlayingRole1 = component.isPlayingRole(f1spec);

        // Then
        Assert.isTrue(isPlayingRole1, "");
    }
//
    function testIsPlayingRoleReturnsFalseWhenRoleIsNotAdded() public {
        // When
        bool isPlayingRole2 = component.isPlayingRole(f2spec);

        // Then
        Assert.isFalse(isPlayingRole2, "");
    }
//
    function testRemoveRoleRendersRoleUnusable() public {
        // Given
        address role1 = compartment.getRole(f1spec);
        component.addRole(f1spec, role1);

        // When
        component.removeRole(f1spec);

        // Then
        try InitiatorRole1(role1).roleFunction() {
            Assert.fail("");
        } catch Error(string memory /*reason*/) {
            // Failure expected.
        }
    }
    function testIsPlayingRoleReturnsFalseAfterRemoveRole() public {
        // Given
        address role1 = compartment.getRole(f1spec);
        component.addRole(f1spec, role1);

        // When
        component.removeRole(f1spec);
        bool isPlayingRole1 = component.isPlayingRole(f1spec);

        // Then
        Assert.isFalse(isPlayingRole1, "");
    }
}
