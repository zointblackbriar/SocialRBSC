const {expect} = require("chai");
describe("SocialAgent Belief General Functions test ", function() {
    let SocialAgentBelief;
    let socialAgentBelief;
    let owner;
    let socialAgent1;
    let socialAgent2;

    beforeEach(async function() {
        SocialAgentBelief = await ethers.getContractFactory("SocialAgentBelief");
        [owner, socialAgent1, socialAgent2] = await ethers.getSigners();
        socialAgentBelief = await SocialAgentBelief.deploy();
        await socialAgentBelief.deployed(); // contract deployed
    });

    it("should allow agent to add a new belief to and remove beliefs from the social agent system", async function() {
        console.log(await socialAgentBelief.beliefowner());
        await socialAgentBelief.addBelief("Sample Belief1", 1);
        await socialAgentBelief.addBelief("Sample Belief2", 2);
        await socialAgentBelief.addBelief("Sample Belief3", 3);
        console.log("length of the social agent belief names: " + await socialAgentBelief.getBeliefNamesLength());
        expect(await socialAgentBelief.getBeliefNamesLength()).to.be.equal(3);
        expect(await socialAgentBelief.getBelief("Sample Belief1")).to.be.equal(1);
        expect(await socialAgentBelief.getBelief("Sample Belief2")).to.be.equal(2);
        expect(await socialAgentBelief.getBelief("Sample Belief3")).to.be.equal(3);
        expect(await socialAgentBelief.getBelief("Sample Belief1")).not.to.equal(3);
        for(let i = 0; i < await socialAgentBelief.getBeliefNamesLength(); i++) {
            console.log(await socialAgentBelief.beliefNames(0));
            console.log(await socialAgentBelief.beliefNames(1));
            console.log(await socialAgentBelief.beliefNames(2));
        }
        // control the belief names
        expect(await socialAgentBelief.beliefNames(0)).to.be.equal("Sample Belief1");
        expect(await socialAgentBelief.beliefNames(1)).to.be.equal("Sample Belief2");
        expect(await socialAgentBelief.beliefNames(2)).to.be.equal("Sample Belief3");

        // check whether we have the belief or not.
        expect(await socialAgentBelief.hasBelief("Sample Belief1")).to.be.true;
        expect(await socialAgentBelief.hasBelief("Sample Belief2")).to.be.true;
        expect(await socialAgentBelief.hasBelief("Sample Belief3")).to.be.true;
        expect(await socialAgentBelief.hasBelief("Sample Belief4")).to.be.false;

        await socialAgentBelief.removeBelief("Sample Belief1");
        expect(await socialAgentBelief.hasBelief("Sample Belief1")).to.be.false;
        await socialAgentBelief.removeBelief("Sample Belief2");
        expect(await socialAgentBelief.hasBelief("Sample Belief2")).to.be.false;
        expect(await socialAgentBelief.hasBelief("Sample Belief3")).to.be.true;
        expect(await socialAgentBelief.hasBelief("Sample Belief4")).to.be.false;
        await socialAgentBelief.removeBelief("Sample Belief3");
        await socialAgentBelief.removeBelief("Sample Belief4");
        // Sample Belief3 and Belief4 are deleted and they should not be true
        expect(await socialAgentBelief.hasBelief("Sample Belief3")).to.be.false;
        expect(await socialAgentBelief.hasBelief("Sample Belief4")).to.be.false;
    });

    it("should test a use case", async function() {
        //Belief has two different agents: security agent and energy agent,
        await socialAgentBelief.addBelief("Security Agent Belief", 0);
        await socialAgentBelief.addBelief("Energy Agent Belief", 1);
        expect(await socialAgentBelief.hasBelief("Security Agent Belief")).to.be.true;
        expect(await socialAgentBelief.hasBelief("Energy Agent Belief")).to.be.true;

    });

    it("should remove a belief", async function() {
       await socialAgentBelief.addBelief("SampleSocialBelief", 0);
       await socialAgentBelief.removeBelief("SampleSocialBelief"); // this belief should be deleted
       expect(await socialAgentBelief.hasBelief("SampleSocialBelief")).to.be.false;
    });

    it("should test belief name length", async function()  {
        await socialAgentBelief.addBelief("FirstBelief", 0);
        await socialAgentBelief.addBelief("SecondBelief", 1);
        await socialAgentBelief.removeBelief("FirstBelief")
        expect(await socialAgentBelief.getBeliefNamesLength()).to.be.equal(1); // there should be one belief
    });
});