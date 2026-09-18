const { expect } = require("chai");

describe("SocialAgentDesire contract", function () {
    let SocialAgentDesire;
    let socialAgentDesire;
    let owner;

    beforeEach(async function () {
        // Get the ContractFactory and Signers
        SocialAgentDesire = await ethers.getContractFactory("SocialAgentDesire");
        [owner] = await ethers.getSigners();

        // Deploy the contract before each test
        socialAgentDesire = await SocialAgentDesire.deploy();
        await socialAgentDesire.deployed();
    });

    it("should initialize with 'None' as the default desire", async function () {
        // Call getDesire to check the default value
        const currentDesire = await socialAgentDesire.getDesire();
        expect(currentDesire).to.equal("None");
    });

    it("should set and return 'ExecuteIntention' as the desire", async function () {
        // Set desire to ExecuteIntention (Desire enum value 1)
        await socialAgentDesire.setDesire("ExecuteTask", 1);

        // Verify that the desire was updated
        const currentDesire = await socialAgentDesire.getDesire();
        expect(currentDesire).to.equal("ExecuteIntention");
    });

});
