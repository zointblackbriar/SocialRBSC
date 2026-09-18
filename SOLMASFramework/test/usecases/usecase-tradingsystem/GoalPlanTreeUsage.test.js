const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("GoalPlanTree in trading use case", function () {
    let manager;
    let tree;

    beforeEach(async function () {
        const Manager = await ethers.getContractFactory("TradingGoalManager");
        manager = await Manager.deploy();
        await manager.deployed();
        // for convenience, get underlying tree instance too
        const treeAddr = await manager.tree();
        tree = await ethers.getContractAt("GoalPlanTree", treeAddr);
    });

    it("should add a root goal and compute correct hash", async function () {
        await manager.addGoal("Goal1", "", "root data", 5, 10);
        // compute hash via external call
        await manager.computeHash("", "Goal1");
        const stored = await tree.resultKeccak();
        const expected = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string","string"],["","Goal1"]));
        expect(stored).to.equal(expected);

        // goal should not be considered dropped
        expect(await manager.isDropped("Goal1", "")).to.equal(false);
    });

    it("should update and then remove a goal", async function () {
        await manager.addGoal("GoalA", "", "data", 1, 1);
        await manager.updateGoal("GoalA", "", "newdata", 2, 2);
        // after update still not dropped
        expect(await manager.isDropped("GoalA", "")).to.equal(false);
        // remove it
        await manager.removeGoal("GoalA", "");
        expect(await manager.isDropped("GoalA", "")).to.equal(true);
    });

    it("should handle recursive drop of children", async function () {
        await manager.addGoal("Parent", "", "p", 0,0);
        await manager.addGoal("Child", "Parent", "c", 0,0);
        // now drop parent recursively (parentName="", goalName="Parent")
        await manager.dropRecursively("", "Parent");
        expect(await manager.isDropped("Child", "Parent")).to.equal(true);
        expect(await manager.isDropped("Parent", "")).to.equal(true);
    });

    it("should link a plan to a goal and execute it", async function () {
        // add plan and goal at root
        await manager.addGoal("MyPlan", "", "plan data", 0,0);
        await manager.addGoal("MyGoal", "", "goal data", 0,0);
        // listen for events on the tree contract
        await expect(manager.linkPlan("MyPlan", "MyGoal"))
            .to.emit(tree, "PlanLinked");
        await expect(manager.executePlanEntry("MyPlan"))
            .to.emit(tree, "PlanExecuted");
    });
});