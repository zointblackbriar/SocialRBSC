// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0;

import "truffle/Assert.sol";
import "../contracts/staticroleassignment/ComponentCore.sol";
import "../contracts/staticroleassignment/InterfaceIds.sol";
import "../contracts/roleinitiator/InitiatorRole.sol";
import "../contracts/roleinitiator/InitiatorCompartment.sol";

contract TestComponentRole_TypeSafety {

    InitiatorRole1 internal role;

    function beforeEach() public {
        role = new InitiatorRole1();
    }

    function testSetCoreAcceptsZeroAddress() public {
        try role.setCore(address(0)) {
            // Should succeed
        } catch Error(string memory /*reason*/) {
            Assert.fail("");
        } 
    }

    function testSetCoreAcceptsCoreAddress() public {
        // Given
        ComponentCore core = new ComponentCore();

        // Then
        try role.setCore(address(core)) {
            // Should succeed
        } catch Error(string memory /*reason*/) {
            Assert.fail("");
        } 
    }

    function testSetCoreDoesNotAcceptRoleAddress() public {
        // Given
        InitiatorRole2 role2 = new InitiatorRole2();

        // Then
        try role.setCore(address(role2)) {
            Assert.fail("");
        } catch Error(string memory /*reason*/) {
            // Failure expected
        } 
    }

    function testSetTeamAcceptsZeroAddress() public {
        try role.setCompartment(address(0)) {
            // Should succeed
        } catch Error(string memory /*reason*/) {
            Assert.fail("");
        } 
    }

    function testSetTeamAcceptsTeamAddress() public {
        // Given
        InitiatorCompartment compartment = new InitiatorCompartment();

        // Then
        try role.setCompartment(address(compartment)) {
            // Should succeed
        } catch Error(string memory /*reason*/) {
            Assert.fail("");
        } 
    }

    function testSetTeamDoesNotAcceptRoleAddress() public {
        // Given
        InitiatorRole2 role2 = new InitiatorRole2();

        // Then
        try role.setCompartment(address(role2)) {
            Assert.fail("");
        } catch Error(string memory /*reason*/) {
            // Failure expected
        } 
    }
}
