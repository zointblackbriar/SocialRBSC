const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("GoalPlanTree (extended)", function () {
    let tree;

    beforeEach(async function () {
        const Tree = await ethers.getContractFactory("GoalPlanTree");
        tree = await Tree.deploy();
        await tree.deployed();
    });

    it("should emit NodeAdded and allow retrieval", async function () {
        const tx = await tree.add("A", "", "dataA", 1, 2);
        const receipt = await tx.wait();
        const event = receipt.events.find(e => e.event === "NodeAdded");
        expect(event).to.not.be.undefined;
        const path = event.args.path;

        const node = await tree.getNode(path);
        expect(node.name).to.equal("A");
        expect(node.parent).to.equal("");
        expect(node.data).to.equal("dataA");
    });

    it("should update existing node and emit NodeUpdated", async function () {
        await tree.add("B", "", "initial", 0, 0);
        const path = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string","string"],["", "B"]));
        const tx = await tree.update("B", "", "changed", 5, 7);
        const receipt = await tx.wait();
        expect(receipt.events.some(e => e.event === "NodeUpdated")).to.be.true;
        const node = await tree.getNode(path);
        expect(node.data).to.equal("changed");
    });

    it("getChildren should return children paths", async function () {
        await tree.add("parent", "", "", 0, 0);
        await tree.add("child1", "parent", "", 0, 0);
        const children = await tree.getChildren("parent");
        expect(children.length).to.equal(1);
        const childPath = children[0];
        const node = await tree.getNode(childPath);
        expect(node.name).to.equal("child1");
    });

    it("linkPlanToGoal and executePlan trigger events", async function () {
        await tree.add("planX", "", "", 0, 0);
        await tree.add("goalX", "", "", 0, 0);
        const linkTx = await tree.linkPlanToGoal("planX", "goalX");
        const linkReceipt = await linkTx.wait();
        expect(linkReceipt.events.some(e => e.event === "PlanLinked")).to.be.true;

        const execTx = await tree.executePlan("planX");
        const execReceipt = await execTx.wait();
        expect(execReceipt.events.some(e => e.event === "PlanExecuted")).to.be.true;
    });

    it("remove should purge global arrays and emit NodeRemoved", async function () {
        await tree.add("leaf", "", "", 0, 0);
        const removeTx = await tree.remove("leaf", "");
        const removeReceipt = await removeTx.wait();
        expect(removeReceipt.events.some(e => e.event === "NodeRemoved")).to.be.true;
        // global arrays should no longer include any node entries
        const names = await tree.goalTreeNodeNameLength();
        expect(names.toNumber()).to.equal(0);
    });

    it("dropLowPriorityGoals drops and emits GoalPriorityDropped", async function () {
        // insert two nodes with different values
        await tree.add("g1", "", "", 10, 5);
        await tree.add("g2", "", "", 2, 8);
        // inspect linked list addresses for each goal (debugging)
        const path1 = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string","string"],["", "g1"]));
        console.log("path1", path1.toString());
        console.log("addresses g1", await tree.getLinkedListAddresses(path1));
        const path2 = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string","string"],["", "g2"]));
        console.log("path2", path2.toString());
        console.log("addresses g2", await tree.getLinkedListAddresses(path2));
        // drop using thresholds that should drop g1
        try {
            const tx = await tree.dropLowPriorityGoals(6, 5, "");
            const receipt = await tx.wait();
            // event count >=1
            expect(receipt.events.some(e => e.event === "GoalPriorityDropped")).to.be.true;
            // g1 should be removed
            const dropped = await tree.isDroppedGoal("g1", "");
            expect(dropped).to.be.true;
            // g2 should still exist
            const exists = await tree.isDroppedGoal("g2", "");
            expect(exists).to.be.false;
        } catch (err) {
            console.error("dropLowPriorityGoals reverted:", err);
            throw err;
        }
    });
});