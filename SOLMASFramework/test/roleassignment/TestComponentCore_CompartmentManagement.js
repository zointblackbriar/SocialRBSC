// test/TestComponentCore_CompartmentManagement.test.js
const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("TestComponentCore_CompartmentManagement", function () {
    let component;
    let compartment;

    beforeEach(async function () {
        // Deploy ComponentCore contract
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        component = await ComponentCore.deploy();
        await component.deployed();

        // Deploy InitiatorCompartment contract
        const Compartment = await ethers.getContractFactory("CompartmentInitiator");
        compartment = await Compartment.deploy(component.address);
        await compartment.deployed();
    });

    it("should retrieve the compartment address after activation", async function () {
        // Activate the compartment
        await component.activateCompartment(compartment.address);

        // Retrieve the currently active compartment
        const currentCompartment = await component.getActiveCompartment();

        console.log("current compartment:", currentCompartment);

        // Assert that the retrieved address is equal to the activated compartment's address
        expect(currentCompartment).to.equal(compartment.address);
    });

    it("should retrieve the zero address after deactivation", async function () {
        // Activate and then deactivate the compartment
        await component.activateCompartment(compartment.address);
        await component.deactivateCompartment();

        // Retrieve the currently active compartment (which should be deactivated)
        const currentCompartment = await component.getActiveCompartment();

        console.log("current compartment:", currentCompartment);

        // Assert that the retrieved address is the zero address
        expect(currentCompartment).to.equal(ethers.constants.AddressZero);
    });
});
