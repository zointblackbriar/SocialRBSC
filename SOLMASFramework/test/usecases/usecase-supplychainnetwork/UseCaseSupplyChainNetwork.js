const {expect} = require('chai');
const {ethers} = require('hardhat');


describe("Case Study Supply Chain Network", function() {

    let society;
    let socialAgent;
    let concreteMediator;
    let owner;
    let linkedList;
    let utils;
    let goalPlanTree;
    let socialAgentInitiator;
    let componentCore;
    let compartmentInitiator;
    const zeroAddress = '0x0000000000000000000000000000000000000000';

    beforeEach(async function() {
       [owner] = await ethers.getSigners();

        // Deploy Society contract
        const Society = await ethers.getContractFactory("Society");
        society = await Society.deploy();
        await society.deployed();

        // Deploy SocialAgent contract
        const SocialAgent = await ethers.getContractFactory("SocialAgent");
        socialAgent = await SocialAgent.deploy();
        await socialAgent.deployed();

        // Deploy ConcreteMediator contract
        const ConcreteMediator = await ethers.getContractFactory("ConcreteMediator");
        concreteMediator = await ConcreteMediator.deploy();
        await concreteMediator.deployed();

        const LinkedList = await ethers.getContractFactory("LinkedList");
        linkedList = await LinkedList.deploy();
        await linkedList.deployed();

        // Deploying the Utils contract
        const Utils = await ethers.getContractFactory("Utils");
        utils = await Utils.deploy();
        await utils.deployed();

        // Deploying the GoalPlanTree contract
        const GoalPlanTree = await ethers.getContractFactory("GoalPlanTree")
        goalPlanTree = await GoalPlanTree.deploy();
        await goalPlanTree.deployed();

        // Deploying the ComponentCore Address
        const ComponentCore = await ethers.getContractFactory("ComponentCore")
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();

        const CompartmentInitiator = await ethers.getContractFactory("CompartmentInitiator");
        compartmentInitiator = await CompartmentInitiator.deploy(componentCore.address);
        await compartmentInitiator.deployed();

        const SocialAgentInitiator = await ethers.getContractFactory("SocialAgentInitiator")
        socialAgentInitiator = await SocialAgentInitiator.deploy(socialAgent.address, concreteMediator.address, society.address, componentCore.address, compartmentInitiator.address, utils.address);
        await socialAgentInitiator.deployed();
        // deploy BDI engine components for deliberation cycle
        const SocialAgentStateMachine = await ethers.getContractFactory("SocialAgentStateMachine");
        const socialAgentStateMachine = await SocialAgentStateMachine.deploy();
        await socialAgentStateMachine.deployed();

        const SocialAgentIntention = await ethers.getContractFactory("SocialAgentIntention");
        const socialAgentIntention = await SocialAgentIntention.deploy("0x0000000000000000000000000000000000000000", utils.address);
        await socialAgentIntention.deployed();

        const SocialAgentBelief = await ethers.getContractFactory("SocialAgentBelief");
        const socialAgentBelief = await SocialAgentBelief.deploy();
        await socialAgentBelief.deployed();

        const SocialAgentDeliberationCycle = await ethers.getContractFactory("SocialAgentDeliberationCycle");
        deliberation = await SocialAgentDeliberationCycle.deploy(
            socialAgentStateMachine.address,
            society.address,
            socialAgent.address,
            socialAgentIntention.address,
            socialAgentBelief.address
        );
        await deliberation.deployed();
        [owner, sampleRole1, sampleRole2] = await ethers.getSigners();


    });

    it("initial test for binding to a society for the Case Study", async function () {
        console.log("initial test for binding to a society")
        const socialAgentName1 = "Retailer";
        const socialAgentName2 = "Wholesaler";
        const socialAgentName3 = "Producer";

        await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address, socialAgentName1);
        await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address, socialAgentName2);
        await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address, socialAgentName3);

        const theAddressOfAgent1 = await concreteMediator.getAgentFromSociety(socialAgentName1);
        const theAddressOfAgent2 = await concreteMediator.getAgentFromSociety(socialAgentName2);
        const theAddressOfAgent3 = await concreteMediator.getAgentFromSociety(socialAgentName3);

        console.log("get agent from a society theAddressOfAgent1: ", theAddressOfAgent1);
        console.log("get agent from a society theAddressOfAgent2: ", theAddressOfAgent2);
        console.log("get agent from a society theAddressOfAgent3: ", theAddressOfAgent3);

        expect(theAddressOfAgent1).to.not.equal(zeroAddress);
        expect(theAddressOfAgent2).to.not.equal(zeroAddress);
        expect(theAddressOfAgent3).to.not.equal(zeroAddress);

    });


    it("Goal Plan tree initial check for the Case Study", async function() {
        const goalName = "Goal 1";
        const planName = "Plan 1";
        const planName2 = "Plan 2";
        const parent = "";
        const goalData = "Data for Goal 1";
        const planData = "Data for Plan 1";
        const avoidance = 10;
        const willingness = 20;

        await goalPlanTree.add(goalName, parent, goalData, avoidance, willingness); // add a goal
        // add a subgoal beneath goalName so we can drop recursively safely
        const subGoal = "SubGoal";
        await goalPlanTree.add(subGoal, goalName, "sub data", avoidance, willingness);

        await goalPlanTree.addPlan(planName, parent, planData, avoidance, willingness); // add a plan
        await goalPlanTree.addPlan(planName2, planName, planData, avoidance, willingness); // add a plan

        const goalPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], [parent, goalName]));
        const planPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], [parent, planName]));
        const [nameRes, parentRes, dataRes, avoidanceParametersRes, willingnessParametersRes, childNodesRes] = await goalPlanTree.getNode(goalPath);

        // retrieve linked list values for the goal before dropping
        await goalPlanTree.getLinkedListValues(goalPath);
        const avList = await goalPlanTree.goalTreeNodeAvoidance;
        const wilList = await goalPlanTree.goalTreeNodeWillingness;
        console.log("avo list:", avList);
        console.log("will list:", wilList);

        // drop recursively the root goal (contains subgoal but not plan links yet)
        await goalPlanTree.dropGoalRecursively(parent, goalName);
        expect(await goalPlanTree.isDroppedGoal(goalName, parent)).to.be.true;

        // link plan to goal and subplan to plan after drop does not matter as tree is cleared
        await goalPlanTree.linkPlanToGoal(planName, goalName);
        await goalPlanTree.linkPlanToGoal(planName2, planName);

    });

    it("should execute a deliberation cycle using supply‑chain agent names", async function() {
        const names = ["Retailer","Wholesaler","Producer"];
        for (let n of names) {
            await socialAgent.socialAgentBindToSociety(society.address, concreteMediator.address, n);
        }
        const addr = await concreteMediator.getAgentFromSociety(names[0]);
        await deliberation.setIntentionsBeforeDeliberationCycle(addr, "SupplyGoods");
        await deliberation.setBeliefsBeforeDeliberationCycle("HasStock", 1);
        await deliberation.setSocialAgentNameForIntentions(names[0]);
        await deliberation.deliberationCycle(names[0], "SupplySociety", addr, 0, "SupplyGoods", "Meta", "HasStock");
        expect(await deliberation.deliberationCycleResult()).to.equal(true);
        const msgs = await deliberation.getMessage(names[0]);
        expect(msgs.length).to.be.greaterThan(0);
    });

});