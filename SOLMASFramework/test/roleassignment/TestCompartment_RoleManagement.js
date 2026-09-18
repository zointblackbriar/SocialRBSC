// test/TestCompartment_RoleManagement.test.js
const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("TestCompartment_RoleManagement", function () {
    let component, compartment;

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

    it("should retrieve the compartment address after activating", async function () {
        // Given
        await component.activateCompartment(compartment.address);

        // When
        const currentCompartment = await component.getActiveCompartment();

        // Then
        expect(currentCompartment).to.equal(compartment.address);
    });

    it("should retrieve zero address after deactivating", async function () {
        // Given
        await component.activateCompartment(compartment.address);
        await component.deactivateCompartment();

        // When
        const currentCompartment = await component.getActiveCompartment();

        // Then
        expect(currentCompartment).to.equal(ethers.constants.AddressZero);
    });
});
