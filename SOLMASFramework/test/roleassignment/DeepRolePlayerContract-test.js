const {expect} = require("chai");
const {ethers} = require("hardhat");


describe("Player contract", async function() {
    let player;
    let owner;
    let retailer;
    let wholesaler;
    let otherAddresses;
    let componentCore;
    let compartment;

    before(async function() {
        [owner, otherAddress] = await ethers.getSigners();
        // Deploy ComponentCore and Compartment contracts (assuming they are provided)
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        const Player = await ethers.getContractFactory("Player");
        const Retailer = await ethers.getContractFactory("Retailer"); // role contract
        const Wholesaler = await ethers.getContractFactory("Wholesaler"); // role contract
        componentCore = await ComponentCore.deploy();
        player = await Player.deploy(owner.address);
        retailer = await Retailer.deploy();
        wholesaler = await Retailer.deploy();

        await componentCore.deployed();
        await player.deployed();
        await retailer.deployed();
        await wholesaler.deployed();
    });

    it("should interact with player contract", async function() {
        expect(await retailer.getInventoryStatus()).to.be.equal(0);
        await player.connect(owner).playDelegateCallRoleContract(retailer.address, "playContractForIdentity()") // contract address and hasbeenplayed
        expect(await player.connect(owner).playedContractList(retailer.address)).to.be.true;
        expect(await player.connect(owner).playedContractList(wholesaler.address)).to.be.false;
        await player.connect(owner).playDelegateCallRoleContract(wholesaler.address, "playContractForIdentity()")
        expect(await player.connect(owner).playedContractList(wholesaler.address)).to.be.true;
    });

    it("should interact with player contract after a contract has been called", async function() {
        await  player.connect(owner).playDelegateCallRoleContract(retailer.address, "playContractForIdentity()");
        expect(await retailer.getInventoryStatus()).to.be.revertedWith("contract should not be played again");
    });
});