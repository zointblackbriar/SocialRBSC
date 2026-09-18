const {
    time,
    loadFixture,
  } = require("@nomicfoundation/hardhat-network-helpers");
  const { anyValue } = require("@nomicfoundation/hardhat-chai-matchers/withArgs");
  const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("ComponentCore Contract", function() {
    let componentCore;
    let owner, account1, account2;
    let interfaceID;
    let retailerRole;
    let wholesaleRole;
    let retailerCreator;
    let wholesalerCreator;
    let player;
    let utils;

    beforeEach(async function() {
        [owner, account1, account2] = await ethers.getSigners();
        //const ComponentCore = await ethers.getContractFactory("contracts/staticroleassignment/ComponentCore.sol:ComponentCore");
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        // const ComponentCore = await ethers.getContractFactory("contracts/staticroleassignment/ComponentCore");
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();
        const InterfaceIds = await ethers.getContractFactory('InterfaceIds');
        interfaceID = await InterfaceIds.deploy();
        await interfaceID.deployed();

        const RetailerCreator = await ethers.getContractFactory('RetailerCreator');
        const WholesalerCreator = await ethers.getContractFactory('WholesalerCreator');
        retailerCreator = await RetailerCreator.deploy();
        wholesalerCreator = await WholesalerCreator.deploy();
        await retailerCreator.deployed();
        await wholesalerCreator.deployed();
        // we have two different roles here
        const RetailerRole = await ethers.getContractFactory('Retailer');
        const WholesalerRole = await ethers.getContractFactory('Wholesaler');
        retailerRole = await RetailerRole.deploy();
        wholesaleRole = await WholesalerRole.deploy();
        await retailerRole.deployed();
        await wholesaleRole.deployed();

        const Utils = await ethers.getContractFactory('Utils');
        utils = await Utils.deploy();
        await utils.deployed();


        const Player = await ethers.getContractFactory("Player");
        player = await Player.deploy(owner.address);
        player.deployed();


    });

    it("Deployment should be assigned to the ComponentCore contract", async function() {
        const roleName = "sample role";
        const isPlayingRoleBeforeRemoval = await componentCore.isPlayingRole(await utils.stringToBytes32Hash(roleName));
        expect(isPlayingRoleBeforeRemoval).to.equal(false);
        // address of the role should be a smart contract address, not an account address
        await expect(componentCore.addRole(await utils.stringToBytes32Hash(roleName), owner.address)).to.be.revertedWith("Doesn't support ComponentRole interface.");
        // nonpertinent contract - it is a regular contract
        await expect(componentCore.addRole(await utils.stringToBytes32Hash(roleName), utils.address)).to.be.revertedWith("Doesn't support ComponentRole interface.");
        // pertinent contract - it is a role smart contract
        await expect(componentCore.addRole(await utils.stringToBytes32Hash(roleName), retailerRole.address)).not.to.be.revertedWith("Doesn't support ComponentRole interface.");


    });


    it("ERC should answer correctly", async function() {
        expect(await componentCore.supportsInterface(0x01ffc9a7)).not.to.be.revertedWith("ERC165 revert message has been implemented");
        expect(await componentCore.supportsInterface(0xffffffff)).to.be.revertedWith("Null interface ID");
        expect(await componentCore.supportsInterface(0x01ffc9a8)).to.be.revertedWithoutReason();
    });

});


