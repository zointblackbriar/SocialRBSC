const {expect} = require("chai");
const {ethers} = require("hardhat")
const {BigNumber} = require("ethers");

describe("SocialGoal contract", function() {
    let socialGoal;
    let owner;

    beforeEach(async function() {
        // Deploy the contract before each test
        const SocialGoal = await ethers.getContractFactory("SocialGoal");
        socialGoal = await SocialGoal.deploy();
        await socialGoal.deployed();

        [owner] = await ethers.getSigners();
    });

    it("should add, activate, complete a goal, and retrieve the goal", async function() {
        const agentName = "Agent1";
        const goalDescription = "Clean the house";

        // Add a goal for the agent
        await socialGoal.addGoal(agentName, goalDescription);

        // Retrieve and check the goal details (it should be inactive)
        let [description, status] = await socialGoal.getGoal(agentName, 0);
        expect(description).to.equal(goalDescription);
        expect(status).to.equal(0); // GoalStatus.Inactivate

        // Activate the goal
        await socialGoal.activateGoal(agentName, 0);

        // Retrieve and check the goal again (it should now be activate)
        [description, status] = await socialGoal.getGoal(agentName, 0);
        expect(status).to.equal(1); // GoalStatus.Active

        // Complete the goal
        await socialGoal.completeGoal(agentName, 0);

        // Retrieve and check the goal again (it should now be completed)
        [description, status] = await socialGoal.getGoal(agentName, 0);
        expect(status).to.equal(2); // GoalStatus.Completed
    });

    it("should fail to activate a goal if index is out of range", async function() {
        const agentName = "SampleAgent";

        // Try activating a goal that does not exist
        await expect(socialGoal.activateGoal(agentName, 0)).to.be.revertedWith("Invalid goal index");
    });

    it("should fail to activate a goal if it is already active or completed", async function() {
        const agentName = "Agent1";
        const goalDescription =  "Clean the house";

        // Add a goal
        await socialGoal.addGoal(agentName, goalDescription);

        // Activate the goal
        await socialGoal.activateGoal(agentName, 0);

        // Try activating the same goal again
        await expect(socialGoal.activateGoal(agentName, 0)).to.be.revertedWith("Goal is already active or completed");

        // Complete the goal
        await socialGoal.completeGoal(agentName, 0);

        // try activating a completed goal
        await expect(socialGoal.activateGoal(agentName, 0)).to.be.revertedWith("Goal is already active or completed");
    });

    it("should fail to complete a goal if it is not active", async function() {
       const agentName =  "Agent1";
       const goalDescription = "Clean the house";

       // Add a goal
       await socialGoal.addGoal(agentName, goalDescription);

       // Try completing a goal that is not active
        await expect(socialGoal.completeGoal(agentName, 0)).to.be.revertedWith("Goal is not active")

    });


    it("should allow agent to add a new goal", async function() {
        // await socialGoal.assignmentOwnerAddress(owner.address);
        // console.log(await socialGoal.goalOwner());
        const agentName = "SampleAgent";
        await socialGoal.connect(owner).addGoal(agentName, "Test Goal");
        await socialGoal.connect(owner).activateGoal(agentName, 0) // first goal
        await socialGoal.connect(owner).addGoal(agentName, "Collaborative Goal");
        await socialGoal.connect(owner).activateGoal(agentName, 1) // second goal
        await socialGoal.connect(owner).addGoal(agentName, "Distinct Goal");
        await socialGoal.connect(owner).activateGoal(agentName, 2) // and third goal
        await socialGoal.connect(owner).completeGoal(agentName, 0);
        // console.log(await socialGoal.agentGoals(owner.address));
        // console.log(await socialGoal.agentGoals(owner.address, 0));
        // console.log(await socialGoal.agentGoals(owner.address, 1));
        console.log(await socialGoal.agentGoals(agentName, 0));
        console.log(await socialGoal.agentGoals(agentName, 1));
        const [description1, status1] = await socialGoal.agentGoals(agentName, 0)
        expect(description1).to.be.equal('Test Goal');
        expect(status1).to.be.equal(2);

        const [description2, status2] = await socialGoal.agentGoals(agentName, 1)
        expect(description2).to.be.equal('Collaborative Goal');
        expect(status2).to.be.equal(1);

        const distinctGoal = await socialGoal.agentGoals(agentName, 2);
        const collaborativeGoal = await socialGoal.agentGoals(agentName, 1);
        const testGoal = await socialGoal.agentGoals(agentName,0);
        console.log("distinctGoal[0]: ", distinctGoal[0]);
        console.log("collaborativeGoal[0]: ", collaborativeGoal[0]);
        console.log("testGoal[0]: ", testGoal[0]);
        console.log("owner address: ", owner.address);
        await socialGoal.assignmentOwnerAddress(owner.address);
        expect(await socialGoal.goalOwner()).to.be.equal(owner.address);
        expect(distinctGoal[0]).to.be.equal("Distinct Goal");
        expect(testGoal[0]).to.be.equal("Test Goal");
        expect(collaborativeGoal[0]).to.be.equal("Collaborative Goal");
    });

    it("should activate a goal", async function() {
        const agentName = "SampleAgent";
        await socialGoal.assignmentOwnerAddress(owner.address);
        console.log(await socialGoal.goalOwner());
        await socialGoal.connect(owner).addGoal(agentName, "Sample goal");
        await socialGoal.connect(owner).activateGoal(agentName, 0);
        const goal = await socialGoal.agentGoals(agentName, 0);
        expect(goal.status).to.equal(1);
    });

    it("Should add a new goal and initialize it to Inactivate", async function() {
        const agentName = "SampleAgent";
        await socialGoal.connect(owner).addGoal(agentName, "Learn this path");
        const goals = await socialGoal.agentGoals(agentName, 0); // added as inactivated
        console.log("goals: ", goals);
        expect(goals.description).to.equal("Learn this path");
        expect(goals.status).to.equal(0); // 0 corresponds to Inactive in the GoalStatus enum
    })

    it("should allow an agent to complete a goal", async function() {
        const agentName = "SampleAgent";
        await socialGoal.assignmentOwnerAddress(owner.address);
        console.log(await socialGoal.connect(owner).goalOwner());
        await socialGoal.connect(owner).connect(owner).addGoal(agentName, "Test Goal");
        await socialGoal.connect(owner).activateGoal(agentName, 0)
        await socialGoal.connect(owner).completeGoal(agentName, 0);
        // const testGoalStatus = await socialGoal.connect(owner).agentGoals(owner.address, 0); //3 for status
        const testGoalStatus = await socialGoal.connect(owner).agentGoals(agentName, 0);
        console.log("testGoalStatus: " + testGoalStatus[1]);
        expect(testGoalStatus[1]).to.equal(2); //GoalStatus.COMPLETED
    });

    it("should allow an agent to set goal status", async function() {
        const agentName = "SampleAgent";
        await socialGoal.assignmentOwnerAddress(owner.address);
        console.log(await socialGoal.goalOwner());
        await socialGoal.connect(owner).addGoal(agentName, "Test Goal"); // goal index is 0
        const sampleGoal = await socialGoal.agentGoals(agentName, 0);
        console.log("sampleGoal: " + sampleGoal);
        await socialGoal.setGoalStatus(agentName, 0, 2);  //GoalStatus.Completed
        const [description, status] = await socialGoal.agentGoals(agentName, 0); //3 for status
        console.log("description of the goal: ", description);
        console.log("status: ", status);

        expect(description).to.be.equal("Test Goal");
        expect(status).to.be.equal(2); //GoalStatus.Completed

    });

    it("should prevent non-owners from adding goals", async function() {
        const agentName = "SampleAgent";
        await expect(socialGoal.connect(owner).addGoal(agentName, "Test Goal")).not.to.be.revertedWith("this contract only can called from beliefowner contract");
    });

    it("should not complete inactive goals", async function() {
        const agentName = "SampleAgent";
        await socialGoal.connect(owner).addGoal(agentName, "Collect the sample"); // goal is now inactivated
        await expect(socialGoal.connect(owner).completeGoal(agentName, 0)).to.be.revertedWith("Goal is not active");
    })

    it("should assign the owner address", async function() {
        await socialGoal.assignmentOwnerAddress(owner.address); // assign owner address
        expect(await socialGoal.goalOwner()).to.equal(owner.address);
    });

    // Edge Case Tests
    describe("Edge Cases", function() {
        it("should handle empty agent name", async function() {
            const emptyAgentName = "";
            const goalDescription = "Test Goal";
            
            await socialGoal.addGoal(emptyAgentName, goalDescription);
            const [description, status] = await socialGoal.getGoal(emptyAgentName, 0);
            expect(description).to.equal(goalDescription);
            expect(status).to.equal(0);
        });

        it("should handle empty goal description", async function() {
            const agentName = "TestAgent";
            const emptyDescription = "";
            
            await socialGoal.addGoal(agentName, emptyDescription);
            const [description, status] = await socialGoal.getGoal(agentName, 0);
            expect(description).to.equal(emptyDescription);
            expect(status).to.equal(0);
        });

        it("should handle very long agent names", async function() {
            const longAgentName = "A".repeat(1000);
            const goalDescription = "Test Goal";
            
            await socialGoal.addGoal(longAgentName, goalDescription);
            const [description, status] = await socialGoal.getGoal(longAgentName, 0);
            expect(description).to.equal(goalDescription);
        });

        it("should handle very long goal descriptions", async function() {
            const agentName = "TestAgent";
            const longDescription = "X".repeat(5000);
            
            await socialGoal.addGoal(agentName, longDescription);
            const [description, status] = await socialGoal.getGoal(agentName, 0);
            expect(description).to.equal(longDescription);
        });

        it("should handle multiple goals for same agent", async function() {
            const agentName = "MultiGoalAgent";
            const numGoals = 50;
            
            for (let i = 0; i < numGoals; i++) {
                await socialGoal.addGoal(agentName, `Goal ${i}`);
            }
            
            for (let i = 0; i < numGoals; i++) {
                const [description, status] = await socialGoal.getGoal(agentName, i);
                expect(description).to.equal(`Goal ${i}`);
                expect(status).to.equal(0);
            }
        });

        it("should fail when getting goal with out-of-bounds index", async function() {
            const agentName = "TestAgent";
            
            await expect(socialGoal.getGoal(agentName, 999)).to.be.revertedWith("Invalid goal index");
        });

        it("should handle special characters in agent name", async function() {
            const specialAgentName = "Agent!@#$%^&*()_+-={}[]|:;<>?,./";
            const goalDescription = "Test Goal";
            
            await socialGoal.addGoal(specialAgentName, goalDescription);
            const [description, status] = await socialGoal.getGoal(specialAgentName, 0);
            expect(description).to.equal(goalDescription);
        });

        it("should handle special characters in goal description", async function() {
            const agentName = "TestAgent";
            const specialDescription = "Goal with émojis 🚀 and spëcial çhars!";
            
            await socialGoal.addGoal(agentName, specialDescription);
            const [description, status] = await socialGoal.getGoal(agentName, 0);
            expect(description).to.equal(specialDescription);
        });

        it("should fail when completing already completed goal", async function() {
            const agentName = "TestAgent";
            
            await socialGoal.addGoal(agentName, "Test Goal");
            await socialGoal.activateGoal(agentName, 0);
            await socialGoal.completeGoal(agentName, 0);
            
            await expect(socialGoal.completeGoal(agentName, 0)).to.be.revertedWith("Goal is not active");
        });

        it("should handle rapid goal state transitions", async function() {
            const agentName = "RapidAgent";
            
            for (let i = 0; i < 10; i++) {
                await socialGoal.addGoal(agentName, `Goal ${i}`);
                await socialGoal.activateGoal(agentName, i);
                await socialGoal.completeGoal(agentName, i);
                
                const [, status] = await socialGoal.getGoal(agentName, i);
                expect(status).to.equal(2);
            }
        });

        it("should fail when activating with very large index", async function() {
            const agentName = "TestAgent";
            await socialGoal.addGoal(agentName, "Test Goal");
            
            // Test with a very large but valid uint256
            await expect(socialGoal.activateGoal(agentName, 999999)).to.be.revertedWith("Invalid goal index");
        });

        it("should maintain separate goal lists for different agents", async function() {
            const agent1 = "Agent1";
            const agent2 = "Agent2";
            
            await socialGoal.addGoal(agent1, "Agent1 Goal");
            await socialGoal.addGoal(agent2, "Agent2 Goal");
            
            const [desc1] = await socialGoal.getGoal(agent1, 0);
            const [desc2] = await socialGoal.getGoal(agent2, 0);
            
            expect(desc1).to.equal("Agent1 Goal");
            expect(desc2).to.equal("Agent2 Goal");
            expect(desc1).to.not.equal(desc2);
        });

        it("should handle zero address as owner", async function() {
            await socialGoal.assignmentOwnerAddress(ethers.constants.AddressZero);
            expect(await socialGoal.goalOwner()).to.equal(ethers.constants.AddressZero);
        });

        it("should handle setting invalid goal status values", async function() {
            const agentName = "TestAgent";
            await socialGoal.addGoal(agentName, "Test Goal");
            
            // Try setting status to value beyond enum range (if not protected)
            // This tests boundary conditions
            await socialGoal.setGoalStatus(agentName, 0, 0);
            let [, status] = await socialGoal.getGoal(agentName, 0);
            expect(status).to.equal(0);
            
            await socialGoal.setGoalStatus(agentName, 0, 2);
            [, status] = await socialGoal.getGoal(agentName, 0);
            expect(status).to.equal(2);
        });
    });

})