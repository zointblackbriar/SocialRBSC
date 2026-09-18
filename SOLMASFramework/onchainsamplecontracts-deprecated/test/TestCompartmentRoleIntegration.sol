// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;

import "../contracts/staticroleassignment/ComponentCore.sol";
import "../contracts/roleinitiator/InitiatorRoleCreator.sol";
import "../contracts/roleinitiator/InitiatorCompartment.sol";
import "../../contracts/utils/Utils.sol";

contract TestCompartmentRoleIntegration {
    ComponentCore internal component;
    Compartment compartment;
//    bytes32 firstRole = keccak256("TESTROLE1");
//    bytes32 secondRole = keccak256("TESTROLE2");
    constructor(){
        component = new ComponentCore();
        compartment = new InitiatorCompartment();
        Utils utils = new Utils();
        compartment.addRoleCreator(utils.stringToBytes32RobustVersion("TESTROLE1"), new InitiatorRole1Creator());
        compartment.addRoleCreator(utils.stringToBytes32RobustVersion("TESTROLE2"), new InitiatorRole2Creator());
        compartment.addRole(utils.stringToBytes32RobustVersion("TESTROLE1"));
        compartment.addRole(utils.stringToBytes32RobustVersion("TESTROLE2"));

        component.activateCompartment(address(compartment));
    }

//    function testSupportsInterface() public {
        Tests will be written later on
//    }
}
