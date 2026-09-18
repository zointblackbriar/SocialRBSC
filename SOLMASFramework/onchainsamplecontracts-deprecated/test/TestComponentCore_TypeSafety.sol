// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0;

import "truffle/Assert.sol";
import "../contracts/staticroleassignment/ComponentCore.sol";
import "../contracts/roleinitiator/InitiatorRole.sol";
import "../contracts/roleinitiator/InitiatorRoleCreator.sol";
import "../contracts/roleinitiator/InitiatorCompartment.sol";

contract TestComponentCore_TypeSafety {

    ComponentCore internal component;
    Compartment internal compartment;

    bytes32 firstRole = keccak256("TESTROLE1");

    function beforeEach() public {
        component = new ComponentCore();

        compartment = new InitiatorCompartment();
        compartment.addRoleCreator(firstRole, new InitiatorRole1Creator());
        compartment.addRole(firstRole);
    }

    function testActivatingNonCompartmentContractFails() public {
        // Given
        address randomRoleContract = compartment.getRole(firstRole);

        // Then
        try component.activateCompartment(randomRoleContract) {
            Assert.fail("");
        } catch Error(string memory /*reason*/) {
            // Failure expected.
        } 
    }

    function testActivatingCompartmentContractSucceeds() public {
        try component.activateCompartment(address(compartment)) {
            // Should succeed.
        } catch Error(string memory /*reason*/) {
            Assert.fail("");
        } 
    }

    function testAddingComponentAsRoleFails() public {
        // Given
        Component core2 = new ComponentCore();

        // Then
        try component.addRole(firstRole, address(core2)) {
            Assert.fail("");
        } catch Error(string memory /*reason*/) {
            // Failure expected.
        } 
    }
}
