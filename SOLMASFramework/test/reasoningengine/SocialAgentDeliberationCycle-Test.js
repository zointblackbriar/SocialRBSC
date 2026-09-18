const {expect} = require("chai");
const {ethers} = require("hardhat");
const { BN, constants, expectEvent, expectRevert } = require('@openzeppelin/test-helpers');

describe("SocialAgentDeliberationCycle", async function() {
    let socialAgentDeliberationCycle;
    let socialAgentStateMachine;
    let socialGoal;
    let society;
    let socialAgent;
    let socialAgentIntention;
    let socialAgentBelief;
    let socialAgentDesire;
    let owner;
    let addr1;
    let addr2;
    let concreteMediator;
    let utils;

    beforeEach(async function() {
        [owner, addr1, addr2] = await ethers.getSigners();


        const SocialAgentStateMachine = await ethers.getContractFactory("SocialAgentStateMachine");
        socialAgentStateMachine = await SocialAgentStateMachine.deploy();
        await socialAgentStateMachine.deployed();

        const SocialGoal = await ethers.getContractFactory("SocialGoal");
        socialGoal = await SocialGoal.deploy();
        await socialGoal.deployed();

        const Society = await ethers.getContractFactory("Society");
        society = await Society.deploy();
        await society.deployed();

        const SocialAgent = await ethers.getContractFactory("SocialAgent");
        socialAgent = await SocialAgent.deploy();
        await socialAgent.deployed();

        const SocialAgentDesire = await ethers.getContractFactory("SocialAgentDesire");
        socialAgentDesire = await SocialAgentDesire.deploy();
        await socialAgentDesire.deployed();

        const Utils = await ethers.getContractFactory("Utils");
        utils = await Utils.deploy();
        await utils.deployed();


        const SocialAgentIntention = await ethers.getContractFactory("SocialAgentIntention");
        socialAgentIntention = await SocialAgentIntention.deploy(socialAgentDesire.address, utils.address);
        await socialAgentIntention.deployed();


        const SocialAgentBelief = await ethers.getContractFactory("SocialAgentBelief");
        socialAgentBelief = await SocialAgentBelief.deploy();
        await socialAgentBelief.deployed();

        const ConcreteMediator = await ethers.getContractFactory("ConcreteMediator");
        concreteMediator = await ConcreteMediator.deploy();
        await concreteMediator.deployed();



        // address payable _socialAgentStateMachineAddress, address payable _society,
        //     address _socialAgentAddress, address _socialAgentIntentionAddress, address _socialAgentBeliefAddress
        const SocialAgentDeliberationCycle = await ethers.getContractFactory("SocialAgentDeliberationCycle");
        socialAgentDeliberationCycle = await SocialAgentDeliberationCycle.deploy(socialAgentStateMachine.address,
            society.address, socialAgent.address, socialAgentIntention.address, socialAgentBelief.address);
        await socialAgentDeliberationCycle.deployed();


    });

    it("should allocate social goals for different agent states", async function() {
        const nameOfAgent = "sampleAgent";
        console.log(await socialAgentStateMachine.agentState());
        // await socialAgentStateMachine.nextState(); // Don't use this because it changes the state of the social agent
        // console.log( await  socialAgentStateMachine.agentState()); // Don't use this because it changes the state of the social agent

        // Add a new social goal
        await socialGoal.addGoal(nameOfAgent, "Rest") // Social agent adds a goal called "Rest"
        // await socialAgentDeliberationCycle.allocateSocialGoals("Rest", 1) # "Rest" will be the abstracted usage of metamodel of Java annotations
        await socialGoal.addGoal(nameOfAgent, "Evade") // Social agent adds a goal called "Rest"
        // await socialAgentDeliberationCycle.allocateSocialGoals("Evade", 2) # "Evade" will be the abstracted usage of metamodel of Java annotations

        // Activate the added goal (it will be at index 0)
        await socialGoal.activateGoal(nameOfAgent, 0);
        await socialGoal.activateGoal(nameOfAgent, 1);

        // Log the goal details (fetch the goals for the agent and print the first goal's status
        const [description1, status1] = await socialGoal.getGoal(nameOfAgent, 0);
        const [description2, status2] = await socialGoal.getGoal(nameOfAgent, 0);

        console.log("description: " + description1);
        console.log("status: " + status1);
        console.log("description: " + description2);
        console.log("status: " + status2);

        expect(status1).not.to.be.equal(null);
        expect(status2).not.to.be.equal(null);
        await socialAgentDeliberationCycle.allocateSocialGoals(status1);
        await socialAgentDeliberationCycle.allocateSocialGoals(status2);
    });

    it("should add a message to communication routing correctly", async function () {
        await socialAgentDeliberationCycle.addMessage(owner.address, addr1.address, "TestMessageType");
        const messages = await socialAgentDeliberationCycle.getMessage(owner.address);
        console.log("messages: ", messages);
        expect(messages.length).to.equal(1);
        expect(messages[0].sender).to.equal(owner.address);
        expect(messages[0].receiver).to.equal(addr1.address);
        expect(messages[0].messageType).to.equal("TestMessageType");
    });

    it('should not revert when intentions have been pushed', async () => {
        const metamodelNameOfDesire = "TransferMoney"
        const socialAgentNameList = ["SocialAgent1"]

        await socialAgent.createAgent("sampleagent1");
        await society.setSocietyName("sample society name");
        await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address, await socialAgent.getSocialAgentName());

        const addressOfAgent1 = await concreteMediator.getAgentFromSociety(await socialAgent.getSocialAgentName());

        // socialAgentNameList.push(agentName);
        // const addressOfAgent1 = await concreteMediator.getAgentFromSociety(agentName);
        console.log("addressOfAgent1: " + addressOfAgent1); // address of the agent in the society
        expect(addressOfAgent1).not.to.equal(null);

        // Set up beliefs but do not add any intention
        // await socialAgentBelief.addBelief('Initial Belief', 1, { from: owner }); // if you have onlyCapability with owner address constraint, you need to use it.
        await socialAgentIntention.pushIntention(addressOfAgent1, "Initial Intention")
        await socialAgentIntention.pushIntention(addressOfAgent1, "Intention1");
        await socialAgentIntention.pushIntention(addressOfAgent1, "Intention2");

        console.log("top intention: ", await socialAgentIntention.topIntention());
        console.log("index 0 intention: ", await socialAgentIntention.getAgentIntentionAtIndex(addressOfAgent1, 0));
        console.log("index 0 intention: ", await socialAgentIntention.getAgentIntentionAtIndex(addressOfAgent1, 1));

        // expect(await socialAgentIntention.success()).to.be.true;
        await socialAgentBelief.addBelief('Initial Belief', 1);
        await socialAgentDesire.setDesire('ExecuteTask', 1);
        await socialAgentIntention.executeIntention(socialAgentNameList, metamodelNameOfDesire, await socialAgentDesire.getDesire(), addressOfAgent1);
        let result = await socialAgentDesire.getDesire()
        expect(result).not.to.equal(null);
        console.log("result: " + result);
        await socialAgentIntention.actIntention(result);


        // Try to execute deliberation cycle with an invalid intention index
        // await expectRevert(
        //     socialAgentDeliberationCycle.deliberationCycle(
        //         await socialAgent.getSocialAgentName(),
        //         await society.getSocietyName(),
        //         addressOfAgent1,
        //         0, // Invalid intention index, we don't have index yet
        //         "Intention Name",
        //         "MetaModelAnnotation",
        //         "Initial Belief"
        //     ),
        //     'Intention index out of bounds'
        // );

            socialAgentDeliberationCycle.deliberationCycle(
                await socialAgent.getSocialAgentName(),
                await society.getSocietyName(),
                addressOfAgent1,
                0, // Invalid intention index, we don't have index yet
                "Intention Name",
                "MetaModelAnnotation",
                "Initial Belief"
            );

        // Verify the deliberation result
        expect(await socialAgentDeliberationCycle.deliberationCycleResult()).to.be.true;

    });

    // integration test with DroneRole from usecase-dronemedicine
    it('integrates DroneRole with the deliberation cycle', async () => {
        // deploy usecase contract
        const MedicinePlan = await ethers.getContractFactory("MedicinePlan");
        const plan = await MedicinePlan.deploy();
        await plan.deployed();
        const DroneRole = await ethers.getContractFactory("DroneRole");
        const drone = await DroneRole.deploy(plan.address, owner.address);
        await drone.deployed();

        // create and bind drone agent
        await drone.createAgent("drone1");
        await drone.socialAgentBindToSociety(society.address, concreteMediator.address, await drone.getSocialAgentName());
        const droneAddr = await concreteMediator.getAgentFromSociety(await drone.getSocialAgentName());
        // mediator currently maps name->society address, so expect society address here
        expect(droneAddr).to.equal(society.address);

        // push intentions and beliefs using the returned society address
        await socialAgentIntention.pushIntention(droneAddr, 'Fly');
        await socialAgentBelief.addBelief('CanFly', 1);
        await socialAgentDesire.setDesire('Deliver', 1);
        await socialAgentIntention.executeIntention([await drone.getSocialAgentName()], "MetaModel", await socialAgentDesire.getDesire(), droneAddr);

        // run deliberation cycle with drone info
        await socialAgentDeliberationCycle.deliberationCycle(
            await drone.getSocialAgentName(),
            await society.getSocietyName(),
            droneAddr,
            0,
            'Fly',
            'MetaModel',
            'CanFly'
        );
        expect(await socialAgentDeliberationCycle.deliberationCycleResult()).to.be.true;
    });

    it('should run the deliberationCycle successfully', async () => {
        // Setup an agent
        await socialAgent.createAgent("sampleagent1");
        await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address,
            await socialAgent.getSocialAgentName());

        const addressOfAgent1 = await concreteMediator.getAgentFromSociety(await socialAgent.getSocialAgentName());
        console.log("addressOfAgent1: " + addressOfAgent1); // address of the agent in the society
        expect(addressOfAgent1).not.to.equal(null);

        // Set up initial data (beliefs, intentions, etc.)
        await socialAgentIntention.pushIntention(addressOfAgent1, 'Initial Intention');
        await socialAgentIntention.pushIntention(addressOfAgent1, 'Initial Intention2');
        await socialAgentIntention.pushIntention(addressOfAgent1, 'Initial Intention3');

        await socialAgentBelief.addBelief('Initial Belief', 1);
        const resultValue = await socialAgentBelief.getBelief("Initial Belief");
        expect(resultValue).to.equal(1);

        // Execute deliberation cycle
        const tx = await socialAgentDeliberationCycle.deliberationCycle(
            "Agent1",
            "Society",
            addressOfAgent1,
            0,  // Index of intention in the stack
            "Intention Name",
            "MetaModelAnnotation",
            "Initial Belief"
        );
        tx.wait();

        // Verify the deliberation result
        expect(await socialAgentDeliberationCycle.deliberationCycleResult()).to.be.true;
    });


    it('should revert when because there is no intention', async () => {
        const metamodelNameOfDesire = "TransferMoney"
        const socialAgentNameList = ["SocialAgent1"]

        await socialAgent.createAgent("sampleagent1");
        await society.setSocietyName("sample society name");
        await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address, await socialAgent.getSocialAgentName());

        const addressOfAgent1 = await concreteMediator.getAgentFromSociety(await socialAgent.getSocialAgentName());

        console.log("addressOfAgent1: " + addressOfAgent1); // address of the agent in the society
        expect(addressOfAgent1).not.to.equal(null);


        // expect(await socialAgentIntention.success()).to.be.true;
        await socialAgentBelief.addBelief('Initial Belief', 1);
        await socialAgentDesire.setDesire('ExecuteTask', 1);
        await expectRevert(
            socialAgentIntention.executeIntention(socialAgentNameList, metamodelNameOfDesire, await socialAgentDesire.getDesire(), addressOfAgent1),
            "No intention set"
        );
        let result = await socialAgentDesire.getDesire()
        expect(result).not.to.equal(null);
        console.log("result: " + result);
        await socialAgentIntention.actIntention(result);


        // Try to execute deliberation cycle with an invalid intention index
        await expectRevert(
            socialAgentDeliberationCycle.deliberationCycle(
                await socialAgent.getSocialAgentName(),
                await society.getSocietyName(),
                addressOfAgent1,
                5, // Invalid intention index, we don't have index yet
                "Intention Name",
                "MetaModelAnnotation",
                "Initial Belief"
            ),
            'Intention index out of bounds'
        );
    });



})