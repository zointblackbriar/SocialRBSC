const {expect} = require("chai");
const {ethers} = require("hardhat")

describe("SocialAgentInitiator", function() {
    let socialAgentInitiator, socialAgent, concreteMediator, society, componentCore, utils, compartmentInitiator;

    beforeEach(async() =>  {
        [owner, other] = await ethers.getSigners();
        const socialAgentContract = await ethers.getContractFactory("SocialAgent");
        socialAgent = await socialAgentContract.deploy();
        console.log("socialAgent: ", socialAgent)

        const ConcreteMediator = await ethers.getContractFactory("ConcreteMediator")
        concreteMediator = await ConcreteMediator.deploy();

        const Society = await ethers.getContractFactory("Society");
        society = await Society.deploy();

        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        componentCore = await ComponentCore.deploy();

        const Utils = await ethers.getContractFactory("Utils");
        utils = await Utils.deploy();

        const CompartmentInitiator = await ethers.getContractFactory("CompartmentInitiator");
        compartmentInitiator = await CompartmentInitiator.deploy(componentCore.address);

        const SocialAgentInitiator = await ethers.getContractFactory("SocialAgentInitiator");
        // constructor(address _socialAgentAddress, ConcreteMediator _concreteMediatorAddress, Society _societyAddress,
        //     ComponentCore _componentCoreAddress, CompartmentInitiator _compartmentInitiator, Utils _utilsAddress)
        socialAgentInitiator = await SocialAgentInitiator.deploy(
            socialAgent.address,
            concreteMediator.address,
            society.address,
            componentCore.address,
            compartmentInitiator.address,
            utils.address
        );

    });

    it("should add a role if not already played", async() => {
        const roleName = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("Leader"));
        console.log("owner address: ", owner.address);
        await expect(componentCore.addRole(await utils.stringToBytes32Hash(roleName), owner.address)).to.be.revertedWith("Doesn't support ComponentRole interface.");
        await socialAgentInitiator.addRoleInitiator(roleName, socialAgent.address);
        expect(await componentCore.isPlayingRole(await utils.stringToBytes32Hash(roleName))).to.equal(true);
    });

    it("should create agent and bind to society", async () => {
        await socialAgentInitiator.socialAgentInitiatorForSociety("Alice");
        expect(await socialAgent.getSocialAgentName()).to.equal("Alice");
    });

    it("should delete a role if played", async () => {
        const roleName = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("Leader"));
        await socialAgentInitiator.addRoleInitiator(roleName, socialAgent.address);
        expect(await componentCore.isPlayingRole(await utils.stringToBytes32Hash(roleName))).to.equal(true);
        await socialAgentInitiator.deleteRoleInitiator(roleName);
        expect(await componentCore.getActiveCompartment()).to.equal("0x0000000000000000000000000000000000000000");
    });


});