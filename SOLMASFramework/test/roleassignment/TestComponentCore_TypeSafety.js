// test/TestComponentCore_TypeSafety.test.js
const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("TestComponentCore_TypeSafety", function () {
    let componentCore;
    let compartment;
    let firstRole;

    beforeEach(async function () {
        // Deploy ComponentCore contract
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();
        console.log("ComponentCore address is: ", componentCore.address);

        // Deploy InitiatorCompartment contract
        const Compartment = await ethers.getContractFactory("CompartmentInitiator");
        compartment = await Compartment.deploy(componentCore.address);
        await compartment.deployed();
        console.log("Compartment address is: ", compartment.address);

        // Define role specifications
        firstRole = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("TESTROLE1"));

        // Deploy role creators
        const InitiatorRole1Creator = await ethers.getContractFactory("RoleCreatorInitiator");
        const roleCreator = await InitiatorRole1Creator.deploy();
        await roleCreator.deployed();
        console.log("role creator address is: ", roleCreator.address);
        // Add role creators to the compartment
        await compartment.addRoleCreator(firstRole, roleCreator.address);

        // Add roles to the compartment
        await compartment.addRole(firstRole);
    });

    it("should fail when activating a non-compartment contract", async function () {
        // Given
        const randomRoleContract = await compartment.getRole(firstRole);

        // Then
        await expect(componentCore.activateCompartment(randomRoleContract)).to.be.reverted;
    });
    //
    it("should succeed when activating a compartment contract", async function () {
        // Should succeed
        await componentCore.activateCompartment(compartment.address);
        await expect(componentCore.activateCompartment(compartment.address)).to.not.be.reverted;
    });
    //
    it("should fail when adding a component as a role", async function () {
        // Given
        const ComponentCore2 = await ethers.getContractFactory("ComponentCore");
        const core2 = await ComponentCore2.deploy();
        await core2.deployed();

        // Then
        await expect(core2.addRole(firstRole, core2.address)).to.be.reverted;
    });
});
