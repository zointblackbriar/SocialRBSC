const {
    time,
    loadFixture,
} = require("@nomicfoundation/hardhat-network-helpers");
const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("SocialAgentStateMachine testing", function () {
    let owner, addr1;
    let socialAgentStateMachine;

    beforeEach(async function () {
        [owner, addr1] = await ethers.getSigners();
        const SocialAgenStateMachine = await ethers.getContractFactory("SocialAgentStateMachine");
        socialAgentStateMachine = await SocialAgenStateMachine.deploy();
        console.log(owner.address);
        await socialAgentStateMachine.deployed();
    });
    it("Owner address check", async function () {
        console.log(owner.address);
        console.log(await socialAgentStateMachine.owner());
        expect(await socialAgentStateMachine.owner()).to.equal(owner.address);
    });

    it("should initialize with NEWAGENT state", async function () {
        // const state = await statemachine.getState();
        const state = await socialAgentStateMachine.agentState();
        expect(state).to.equal(0); //AgentStates.NEWAGENT
        // expect(state).to.equal("NEWAGENT");
    });

    it("should transtition to the next state after state changing", async function () {
        await socialAgentStateMachine.nextState();
        // await statemachine.doPlan();
        const state = await socialAgentStateMachine.agentState();
        expect(state).to.equal(1); //AgentStates.RUNNABLEAGENT
    });

    it("should transaction to the next state after timedTransitions", async function () {
        await network.provider.send("evm_increaseTime", [86400]);
        await network.provider.send("evm_mine");

        await socialAgentStateMachine.nextState();
        await socialAgentStateMachine.nextState();

        const state = await socialAgentStateMachine.agentState();
        expect(state).to.equal(2); //AgentStates.BLOCKEDAGENT
    });

    it("should transition to the next state after agentEnding", async function () {
        await socialAgentStateMachine.nextState();
        await socialAgentStateMachine.nextState();
        await socialAgentStateMachine.nextState();

        const state = await socialAgentStateMachine.agentState();
        console.log("current state of the agent in termination: " + state)
        expect(state).to.equal(3); //AgentStates.TERMINATEDAGENT
    });

    it("should not allow non-owner to transition states", async function () {
        await expect(socialAgentStateMachine.connect(addr1).manualTransition()).to.be.revertedWith("Only the owner can transition states");
    });

    it("should terminate the agent in TERMINATEDAGENT state", async function () {
        //Transition states manually to TERMINATEDAGENT state

        await socialAgentStateMachine.manualTransition(); // RUNNABLEAGENT
        await socialAgentStateMachine.manualTransition(); // TERMINATEDAGENT

        //Check state before termination
        expect(await socialAgentStateMachine.agentState()).to.equal(2); // TERMINATEDAGENT

        // Terminate the contract
        await socialAgentStateMachine.agentEnding({value: ethers.utils.parseEther("0.1")})

        // Test contract is terminated (self-destructed)
        // The following test gives different result under Windows and macOS operating systems
        // await expect(socialAgentStateMachine.agentState()).to.be.reverted // Contract should no longer exist
    });

    it("should revert if agentEnding is called before TERMINATEDAGENT state", async function() {
        await expect(socialAgentStateMachine.agentEnding()).to.be.revertedWith("agent state should be complied with the assigned state");
    });

});
