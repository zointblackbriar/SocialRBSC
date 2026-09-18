const {expect} = require('chai');
const {ethers} = require('hardhat');


describe('SocialAgentIntention', function () {
    let socialAgentIntention;
    let socialAgentDesire;
    let owner;
    let addr1;
    let utils;

    //Deploy the contract before the running tests
    beforeEach(async function () {
        [owner, addr1] = await ethers.getSigners();
        const SocialAgentDesire = await ethers.getContractFactory("SocialAgentDesire");
        socialAgentDesire = await SocialAgentDesire.deploy();
        await socialAgentDesire.deployed();


        const Utils = await ethers.getContractFactory("Utils");
        utils = await Utils.deploy();
        await utils.deployed();

        const SocialAgentIntention = await ethers.getContractFactory("SocialAgentIntention");
        socialAgentIntention = await SocialAgentIntention.deploy(socialAgentDesire.address, utils.address);
        await socialAgentIntention.deployed();
    });

    it("should allow a social agent to push an intention onto their stack", async function () {
        await socialAgentIntention.pushIntention(owner.address, "Intention1");
        await socialAgentIntention.pushIntention(owner.address, "Intention2");
        await socialAgentIntention.pushIntention(owner.address, "Intention3");
        await socialAgentIntention.pushIntention(owner.address, "Intention4");

        const intentions = await socialAgentIntention.getAgentIntentions(owner.address);
        for (let i = 0; i < intentions.length; i++) {
            console.log(intentions[i]);
        }
        expect(intentions.length).to.equal(4);
        expect(intentions[0]).to.equal("Intention1");
        expect(intentions[1]).to.equal("Intention2");
        expect(intentions[2]).to.equal("Intention3");
        expect(intentions[2]).to.equal("Intention3");
        expect(await socialAgentIntention.getAgentIntentionAtIndex(owner.address, 0)).to.be.equal("Intention1");
        expect(await socialAgentIntention.getAgentIntentionAtIndex(owner.address, 1)).to.be.equal("Intention2");
        expect(await socialAgentIntention.getAgentIntentionAtIndex(owner.address, 3)).to.be.equal("Intention4");
        await expect(socialAgentIntention.getAgentIntentionAtIndex(owner.address, 4)).to.be.revertedWith("Intention index out of bounds");

    });

    it("should not execute intention if no match is found", async function () {
        await socialAgentIntention.pushIntention(owner.address, "Intention1");
        await socialAgentIntention.pushIntention(owner.address, "Intention2");
        const socialAgentList = ["SocialAgent1", "SocialAgent2"];
        const metamodelNameOfDesire = "TransferMoney"

        await socialAgentIntention.executeIntention(socialAgentList, metamodelNameOfDesire, await socialAgentDesire.getDesire(), owner.address,);
        console.log("top intention: ", await socialAgentIntention.topIntention());
        expect(await socialAgentIntention.success()).to.be.true;

        // const intentions = await socialAgentIntention.agentIntentionsStack(owner.address);
        const intention1 = await socialAgentIntention.getAgentIntentions(owner.address);
        console.log("intention1: ", intention1);
        expect(intention1.length).to.equal(1);
        await socialAgentIntention.executeIntention(socialAgentList, metamodelNameOfDesire, await socialAgentDesire.getDesire(), owner.address,);
        const intention2 = await socialAgentIntention.getAgentIntentions(owner.address);
        console.log("intention2: ", intention2);
        expect(await socialAgentIntention.success()).to.be.true;

    });

    it("should revert if no intention is set for the agent", async function () {
        const metamodelNameOfDesire = "TransferMoney"
        // Intentions should be set
        await expect(socialAgentIntention.executeIntention([], metamodelNameOfDesire, socialAgentDesire.getDesire(), owner.address,)).to.be.revertedWith("No intention set");
    });

});