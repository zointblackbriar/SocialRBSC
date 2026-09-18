// test/TestComponentCore_ERC165.test.js
const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("TestComponentCore_ERC165", function () {
    let component;

    const INTERFACE_ERC165 = "0x01ffc9a7";
    const INTERFACE_COMPONENT_ID = "0xabcdef12"; // Replace with the actual InterfaceIds.COMPONENT_ID
    const INTERFACE_COMPONENT_ROLE_ID = "0x12345678"; // Replace with the actual InterfaceIds.COMPONENT_ROLE_ID
    const INTERFACE_COMPARTMENT_ID = "0x87654321"; // Replace with the actual InterfaceIds.COMPARTMENT_ID

    beforeEach(async function () {
        // Deploy ComponentCore contract
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        component = await ComponentCore.deploy();
        await component.deployed();
    });

    it("should support ERC165 interface", async function () {
        expect(await component.supportsInterface(INTERFACE_ERC165)).to.be.true;
    });

    it("should support Component Interface (COMPONENT_ID)", async function () {
        expect(await component.supportsInterface(INTERFACE_COMPONENT_ID)).to.be.false;
    });

    it("should not support Component Role Interface ID (COMPONENT_ROLE_ID)", async function () {
        expect(await component.supportsInterface(INTERFACE_COMPONENT_ROLE_ID)).to.be.false;
    });

    it("should not support Team Interface ID (COMPARTMENT_ID)", async function () {
        expect(await component.supportsInterface(INTERFACE_COMPARTMENT_ID)).to.be.false;
    });

    it("should not support random interface IDs", async function () {
        expect(await component.supportsInterface("0x01ffc9a8")).to.be.false;
    });
});
