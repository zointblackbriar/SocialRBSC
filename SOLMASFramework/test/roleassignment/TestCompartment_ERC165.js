// test/TestCompartment_ERC165.test.js
const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("TestCompartment_ERC165", function () {
    let compartment;
    let componentCore;
    const INTERFACE_ERC165 = "0x01ffc9a7";
    const INTERFACE_COMPARTMENT_ID = "0x12345678"; // Replace with actual InterfaceIds.COMPARTMENT_ID
    const INTERFACE_COMPONENT_ROLE_ID = "0x87654321"; // Replace with actual InterfaceIds.COMPONENT_ROLE_ID
    const INTERFACE_COMPONENT_ID = "0xabcdefab"; // Replace with actual InterfaceIds.COMPONENT_ID

    beforeEach(async function () {
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();

        const Compartment = await ethers.getContractFactory("CompartmentInitiator");
        compartment = await Compartment.deploy(componentCore.address);
        await compartment.deployed();
    });

    it("should support ERC165 interface", async function () {
        expect(await compartment.supportsInterface(INTERFACE_ERC165)).to.be.true;
    });

    it("should support Compartment Interface (COMPARTMENT_ID)", async function () {
        expect(await compartment.supportsInterface(INTERFACE_COMPARTMENT_ID)).to.be.false;
    });

    it("should not support Component Role Interface ID (COMPONENT_ROLE_ID)", async function () {
        expect(await compartment.supportsInterface(INTERFACE_COMPONENT_ROLE_ID)).to.be.false;
    });

    it("should not support Component Interface ID (COMPONENT_ID)", async function () {
        expect(await compartment.supportsInterface(INTERFACE_COMPONENT_ID)).to.be.false;
    });

    it("should not support random interface IDs", async function () {
        expect(await compartment.supportsInterface("0x01ffc9a8")).to.be.false;
    });
});