const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("SocialInfluence", function () {
    let SocialInfluence;
    let socialInfluence;
    let owner;
    let addr1;
    let addr2;

    beforeEach(async function() {
        SocialInfluence = await ethers.getContractFactory("SocialInfluence");
        [owner, addr1, addr2 ] = await ethers.getSigners();
        socialInfluence = await SocialInfluence.deploy();
        await socialInfluence.deployed();
    });

    it("should register a social agent", async function() {
        await socialInfluence.registerSocialAgent(addr1.address);
        const socialAgent = await socialInfluence.socialAgents(addr1.address);
        expect(socialAgent.exists).to.equal(true);
    });

    it("should be an expected action", async function() {
        const action = "attend_event";
        const actionString = "attend_event"
        console.log("actionHash: " + actionString);
        await socialInfluence.setExpectedAction(action, true);
        expect(await socialInfluence.expectedActions(actionString)).to.equal(true);
    });

    it("should set a norm for a registered agent", async function() {
        const action = "attend_event";
        // if you wanna assign hash value in bytes data structure instead of a string memory value
        // please use the following function:
        // const actionHash = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(action));
        const actionString = "attend_event";
        await socialInfluence.registerSocialAgent(addr1.address);
        await socialInfluence.setNorm(addr1.address, action, true);

        expect(await socialInfluence.norms(addr1.address, actionString)).to.equal(true);
    });

    it("should set influence of one agent on another", async function() {
        const action = "attend_event";
        const actionString = "attend_event";
        await socialInfluence.registerSocialAgent(addr1.address);
        await socialInfluence.registerSocialAgent(addr2.address);
        await socialInfluence.setInfluence(addr1.address ,addr2.address, action, true);
        expect(await socialInfluence.influences(addr1.address, addr2.address, actionString)).to.equal(true);
    });

    it("should set pressure on an agent", async function() {
        const action = "attend_event";
        const actionString = "attend_event";
        await socialInfluence.registerSocialAgent(addr1.address);
        await socialInfluence.setPressure(addr1.address, action, true);
        expect(await socialInfluence.pressures(addr1.address, actionString)).to.equal(true);

    });

    it("should set an action for an agent", async function() {
        const action = "attend_event";
        const actionString = "attend_event";
        await socialInfluence.registerSocialAgent(addr1.address);
        await socialInfluence.setAction(addr1.address, action, true);
        expect(await socialInfluence.actions(addr1.address, actionString)).to.equal(true);
    });

    it("should apply pressure definition correctly", async function() {
       const action = "attend_event";
       const actionString = "attend_event";
       await socialInfluence.registerSocialAgent(addr1.address);
       await socialInfluence.registerSocialAgent(addr2.address);
       await socialInfluence.setInfluence(addr1.address, addr2.address, action, true);
       await socialInfluence.applyPressureDefinition(addr2.address, addr1.address);
       console.log("pressures: ", await socialInfluence.pressures(addr1.address, actionString));
       expect(await socialInfluence.pressures(addr1.address, actionString)).to.equal(false); // first agent does not feel pressure
       expect(await socialInfluence.pressures(addr2.address, actionString)).to.equal(true); // second agent under social pressure
    });

    it("should apply action compliance correctly", async function() {
       const action = "attend_event";
       const actionString = "attend_event";
       await socialInfluence.registerSocialAgent(addr1.address);
       await socialInfluence.setPressure(addr1.address, action, true);
       await socialInfluence.setNorm(addr1.address, action, true);
       await socialInfluence.applyActionCompliance(addr1.address);
       expect(await socialInfluence.actions(addr1.address, actionString)).to.equal(true);
    });
})