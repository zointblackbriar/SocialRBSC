const {expect} = require("chai");
const {ethers} = require('hardhat');
const {BigNumber} = require("ethers");

describe("Goal Tree Test Cases", () => {
    let goalPlanTree;
    let linkedList;
    let owner;
    let utils;

    beforeEach(async function () {
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
        [owner, sampleRole1, sampleRole2] = await ethers.getSigners();

    });

    it("should configure correctly the contracts", async function () {
        expect(goalPlanTree.address).to.not.null;
        expect(linkedList.address).to.not.null;
    });

    it("should add a goal to the GoalPlanTree", async function () {
        const name = "Goal 1";
        const parent = "";
        const data = "Data for Goal 1";
        const avoidance = 10;
        const willingness = 20;

        await goalPlanTree.add(name, parent, data, avoidance, willingness);

        const node = await goalPlanTree.nodes(
            ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], [parent, name]))
        );
        expect(node.name).to.equal(name);
        expect(node.parent).to.equal(parent);
        expect(node.data).to.equal(data);
    });

    it("should create a plan linked to a goal", async function () {
        const goalName = "Goal 1";
        const planName = "Plan 1";
        const planName2 = "Plan 2";
        const parent = "";
        const goalData = "Data for Goal 1";
        const planData = "Data for Plan 1";
        const avoidance = 10;
        const willingness = 20;

        await goalPlanTree.add(goalName, parent, goalData, avoidance, willingness); // add a goal
        await goalPlanTree.addPlan(planName, parent, planData, avoidance, willingness); // add a plan
        await goalPlanTree.addPlan(planName2, planName, planData, avoidance, willingness); // add a plan

        // await goalPlanTree.createPlan(planName, goalName, planData, avoidance, willingness);

        const goalPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], [parent, goalName]));

        const planPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], [parent, planName]));

        // const goalNode = await goalPlanTree.nodes(goalPath);
        const [nameRes, parentRes, dataRes, avoidanceParametersRes, willingnessParametersRes, childNodesRes] = await goalPlanTree.getNode(goalPath);

        expect(childNodesRes.length).to.equal(0);
        console.log("name result: ", nameRes);
        console.log("parent result: ", parentRes);
        console.log("data result: ", dataRes);
        console.log("avoidance parameter result: ", avoidanceParametersRes); // linked list address
        console.log("willingness param result: ", willingnessParametersRes); // linked list address
        console.log("child node result: ", childNodesRes);


        // const planPath = goalNode.nodes[0];
        const [nameNodePlan, parentNodePlan, dataPlan, avoidancePlanAddress, willingnessPlanAddress, childNodePlan] = await goalPlanTree.getNode(planPath);
        console.log("nameNodePlan: ", nameNodePlan);
        expect(nameNodePlan).to.equal(planName);
        expect(parentNodePlan).to.equal(parent);
        expect(dataPlan).to.equal(planData);
        expect(avoidancePlanAddress).to.not.equal(ethers.constants.AddressZero);
        expect(willingnessPlanAddress).to.not.equal(ethers.constants.AddressZero);
        console.log("childNodePlan: " + childNodePlan);
    });

    /// @dev Add Plan: Adds a plan to the goal tree using the add function.
    /// @dev Link Plan to Goal: Calls the linkPlanToGoal function, which links the previously added plan to the goal as a sub-node.
    /// @dev Verify Linkage: Retrieves the goal node using getNode and checks that the plan has been correctly linked as a child node.
    /// @dev It verifies that the linked plan’s path matches the plan that was added.
    it("should link a plan to a goal", async function () {
        // Add a goal
        const goalName = "Goal 1";
        const goalData = "Data for Goal 1";
        const goalAvoidance = 10;
        const goalWillingness = 15;
        await goalPlanTree.add(goalName, "", goalData, goalAvoidance, goalWillingness);

        // Add a plan
        const planName = "Plan 1";
        const planData = "Data for Plan 1";
        const planAvoidance = 5;
        const planWillingness = 20;
        await goalPlanTree.add(planName, "", planData, planAvoidance, planWillingness);

        // Link the plan to the goal
        await goalPlanTree.linkPlanToGoal(planName, goalName);

        // Check if the plan is linked as a sub-node of the goal
        const goalPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], ["", goalName]));
        const goalNode = await goalPlanTree.getNode(goalPath);

        // Verify the plan has been linked as a sub-node
        expect(goalNode.childNodes.length).to.equal(1); // Ensure the goal has one child node
        const linkedPlanPath = goalNode.childNodes[0]; // Get the path of the linked plan

        // Get the plan node and verify it matches the plan name
        const planPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], ["", planName]));
        expect(linkedPlanPath).to.equal(planPath); // Check if the linked plan matches the plan added
    });


    it("should remove a goal and its subgoals", async function () {
        // Add a parent goal
        const parentGoalName = "Parent Goal";
        const parentGoalData = "Parent goal data";
        const avoidance = 5;
        const willingness = 8;
        await goalPlanTree.add(parentGoalName, "", parentGoalData, avoidance, willingness);

        // Add subgoals
        const subGoal1Name = "Sub Goal 1";
        const subGoal1Data = "Sub goal 1 data";
        await goalPlanTree.add(subGoal1Name, parentGoalName, subGoal1Data, avoidance, willingness);

        const subGoal2Name = "Sub Goal 2";
        const subGoal2Data = "Sub goal 2 data";
        await goalPlanTree.add(subGoal2Name, parentGoalName, subGoal2Data, avoidance, willingness);

        // Check that the goals and subgoals exist
        const parentGoalExists = await goalPlanTree.isDroppedGoal(parentGoalName, "");
        const subGoal1Exists = await goalPlanTree.isDroppedGoal(subGoal1Name, parentGoalName);
        const subGoal2Exists = await goalPlanTree.isDroppedGoal(subGoal2Name, parentGoalName);
        console.log("parentGoalExists: " + parentGoalExists);
        console.log("subGoal1Exists: " + subGoal1Exists);
        console.log("subGoal2Exists: " + subGoal2Exists);
        expect(parentGoalExists).to.be.false; // is not dropped
        expect(subGoal1Exists).to.be.false;
        expect(subGoal2Exists).to.be.false;

        const parentGoalPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], ["", parentGoalName]));
        console.log("parentGoalPath", parentGoalPath);
        // Call the removeGoalAndSubgoals function on the parent goal
        await goalPlanTree.removeGoalAndSubgoals(parentGoalPath);


        // Now check if the parent goal and subgoals have been removed
        const isParentGoalDropped = await goalPlanTree.isDroppedGoal(parentGoalName, "");
        expect(isParentGoalDropped).to.be.true; // parent goal should be dropped

        await goalPlanTree.dropGoalRecursively(parentGoalName, subGoal1Name);
        const subGoal1Check = await goalPlanTree.isDroppedGoal(subGoal1Name, parentGoalName);
        expect(subGoal1Check).to.be.true; // sub goal 1 should be dropped
        //
        await goalPlanTree.dropGoalRecursively(parentGoalName, subGoal2Name);
        const subGoal2Check = await goalPlanTree.isDroppedGoal(subGoal2Name, parentGoalName);
        expect(subGoal2Check).to.be.true; // sub goal 2 should be dropped
    });


    it("should update elements to the tree", async function () {
        console.log("owner address: " + owner.address);
        // Get the struct instance from the contract
        await goalPlanTree.add("samplename", "firstparent", "sampledata", 1, 2);
        await goalPlanTree.update("samplenamenew", "firstparent", "newdata", 1, 2);
        // console.log(await goalTree.goalTreeNodeName(0));
        const lengthOfTheArray = await goalPlanTree.goalTreeNodeNameLength();
        // await goalTree.remove("samplename", "firstparent");
        for (let i = 0; i < lengthOfTheArray; i++) {
            console.log("result: " + await goalPlanTree.goalTreeNodeName(i));
            console.log("result: " + await goalPlanTree.goalTreeNodeParent(i));
            console.log("result: " + await goalPlanTree.goalTreeNodeData(i));
        }
        const goalTreeNodeAvoidance = await goalPlanTree.goalTreeNodeAvoidance(0)
        const goalTreeNodeWillingness = await goalPlanTree.goalTreeNodeWillingness(0)
        expect(goalTreeNodeAvoidance.toNumber()).to.be.equal(1);
        expect(goalTreeNodeWillingness.toNumber()).to.be.equal(2);

    });

    it("should return the LinkedList contract addresses for a given node", async function () {
        // Add a goal node
        const goalName = "Goal 1";
        const goalData = "Data for Goal 1";
        const avoidance = 10;
        const willingness = 15;
        await goalPlanTree.add(goalName, "", goalData, avoidance, willingness);

        // Get the goal's node path
        const goalPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], ["", goalName]));

        // Get the linked list addresses
        const [avoidanceAddress, willingnessAddress] = await goalPlanTree.getLinkedListAddresses(goalPath);

        // Check that the addresses are not zero
        expect(avoidanceAddress).to.not.equal(ethers.constants.AddressZero);
        expect(willingnessAddress).to.not.equal(ethers.constants.AddressZero);
    });


    it("should return correct avoidance and willingness values", async function () {
        const parentGoalName = "Goal1";
        const subGoalName = "Goal2";
        const data = "Data for Goal1";
        const avoidance1 = 2;
        const willingness1 = 3;
        const avoidance2 = 0;
        const willingness2 = 4;

        await goalPlanTree.add(parentGoalName, "", data, avoidance1, willingness1);
        await goalPlanTree.add(subGoalName, parentGoalName, data, avoidance2, willingness2);


        // Calculate the node path (keccak256 hash of the parent and name)
        const nodePath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string", "string"], ["", parentGoalName]));

        // Fetch the linked list values for avoidance and willingness
        await goalPlanTree.getLinkedListValues(nodePath);


        // Verify the values
        expect(await goalPlanTree.goalTreeNodeAvoidance(0)).to.equal(avoidance1);
        expect(await goalPlanTree.goalTreeNodeWillingness(0)).to.equal(willingness1);


        // Calculate the sub-goal path (keccak256 hash of the parentGoalName and subGoalName)
        const subNodePath = ethers.utils.keccak256(
            ethers.utils.defaultAbiCoder.encode(["string", "string"], [parentGoalName, subGoalName ])
        );

        // Fetch the linked list values for avoidance and willingness
        await goalPlanTree.getLinkedListValues(subNodePath);


        expect(await goalPlanTree.goalTreeNodeAvoidance(0)).to.equal(avoidance2);
        expect(await goalPlanTree.goalTreeNodeWillingness(0)).to.equal(willingness2);

    });

});