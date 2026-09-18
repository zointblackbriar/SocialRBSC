// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;

import "../contracts/roleinitiator/InitiatorCompartment.sol";
import "truffle/Assert.sol";
import "truffle/Console.sol";
contract TestComponentCore_CompartmentManagement {
    constructor(){

    }

    ComponentCore public component;
    Compartment public compartment;

    function beforeEach() public {
        component = new ComponentCore();
        compartment = new InitiatorCompartment();
    }


    function testRetrieveCompartmentAfterActivation() public {
        component.activateCompartment(address(compartment));
        address currentCompartment = component.getActiveCompartment();
        console.log("current compartment: ", currentCompartment);
        Assert.equal(currentCompartment, address(compartment), "");
    }

    function testRetrieveTeamAfterDeactivating() public {
        component.activateCompartment(address(compartment));
        component.deactivateCompartment();

        address currentCompartment = component.getActiveCompartment();
        console.log("current compartment: ", currentCompartment);

        Assert.isZero(currentCompartment, "");
    }
}
