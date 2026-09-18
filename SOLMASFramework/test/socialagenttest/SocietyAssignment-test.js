// const {ConcreteMediator} = require("ConcreteMediator");
// const {SocialAgent} = require("SocialAgent");
// const {InterfaceIds} = require("InterfaceIds");
const {ethers} =  require('hardhat');
const {expect} = require('chai');

describe("SocietyMediator", function () {
    let SocialAgent, socialAgent, concreteMediator, ConcreteMediator, Society, society, owner, addr1;
    const zeroAddress = '0x0000000000000000000000000000000000000000';

    beforeEach(async function () {
        [owner, addr1] = await ethers.getSigners();

        // Deploy Society contract
        Society = await ethers.getContractFactory("Society");
        society = await Society.deploy();
        await society.deployed();

        // Deploy SocialAgent contract
        SocialAgent = await ethers.getContractFactory("SocialAgent");
        socialAgent = await SocialAgent.deploy();
        await socialAgent.deployed();

        // Deploy ConcreteMediator contract
        ConcreteMediator = await ethers.getContractFactory("ConcreteMediator");
        concreteMediator = await ConcreteMediator.deploy();
        await concreteMediator.deployed();

    });

    it("should bind agent to society", async function () {
        const socialAgentName = "SocialAgent1";
        console.log("society address: ", society.address);
        // Call the bind function
        await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address, socialAgentName);
        const theAddressOfAgent = await concreteMediator.getAgentFromSociety(socialAgentName);
        console.log("get agent from a society: ", theAddressOfAgent);
        expect(theAddressOfAgent).to.not.equal(zeroAddress);

    });

    it("should unbing agent to society", async function () {
        const socialAgentName = "SocialAgent1";
        console.log("society address: ", society.address);
        // Call the bind function
        await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address, socialAgentName);
        await socialAgent.socialAgentUnbind(socialAgentName, concreteMediator.address);
        // it should be reverted
        await expect(concreteMediator.getAgentFromSociety(socialAgentName)).to.be.revertedWith("Agent not found in the society");
    });

    it("should create social agent and set name", async function () {
        const agentName = "Social Agent 1";
        await socialAgent.createAgent(agentName);

        // Check if the returned agent address matches the caller address
        expect(await socialAgent.socialAgentName()).to.equal(agentName);

    });
});
