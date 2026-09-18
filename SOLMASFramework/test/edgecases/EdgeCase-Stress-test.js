const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("Edge Cases - Stress and Performance Tests", function() {
    let componentCore, compartmentInitiator, socialGoal;
    let owner, signers;

    beforeEach(async function() {
        signers = await ethers.getSigners();
        owner = signers[0];

        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();

        const CompartmentInitiator = await ethers.getContractFactory('CompartmentInitiator');
        compartmentInitiator = await CompartmentInitiator.deploy(componentCore.address);
        await compartmentInitiator.deployed();

        const SocialGoal = await ethers.getContractFactory("SocialGoal");
        socialGoal = await SocialGoal.deploy();
        await socialGoal.deployed();
    });

    describe("High Volume Operations", function() {
        it("should handle 100 sequential goal additions", async function() {
            this.timeout(60000); // Increase timeout for stress test
            
            const agentName = "StressAgent";
            const goalCount = 100;
            
            for (let i = 0; i < goalCount; i++) {
                await socialGoal.addGoal(agentName, `Stress Goal ${i}`);
            }
            
            // Verify random samples
            const [first] = await socialGoal.getGoal(agentName, 0);
            const [middle] = await socialGoal.getGoal(agentName, 50);
            const [last] = await socialGoal.getGoal(agentName, goalCount - 1);
            
            expect(first).to.equal("Stress Goal 0");
            expect(middle).to.equal("Stress Goal 50");
            expect(last).to.equal(`Stress Goal ${goalCount - 1}`);
        });

        it("should handle 50 role checks", async function() {
            this.timeout(60000);
            
            const roleCount = 50;
            
            // Test batch hasRole checks
            for (let i = 0; i < roleCount; i++) {
                const roleSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(`StressRole${i}`));
                const hasRole = await compartmentInitiator.hasRole(roleSpec);
                expect(hasRole).to.be.false;
            }
        });

        it("should handle multiple agents with multiple goals each", async function() {
            this.timeout(60000);
            
            const agentCount = 20;
            const goalsPerAgent = 10;
            
            for (let i = 0; i < agentCount; i++) {
                const agentName = `Agent${i}`;
                
                for (let j = 0; j < goalsPerAgent; j++) {
                    await socialGoal.addGoal(agentName, `Agent${i} Goal${j}`);
                }
            }
            
            // Verify first and last agent
            const [firstGoal] = await socialGoal.getGoal("Agent0", 0);
            const [lastGoal] = await socialGoal.getGoal(`Agent${agentCount-1}`, goalsPerAgent-1);
            
            expect(firstGoal).to.equal("Agent0 Goal0");
            expect(lastGoal).to.equal(`Agent${agentCount-1} Goal${goalsPerAgent-1}`);
        });
    });

    describe("Complex State Transitions", function() {
        it("should handle rapid state changes for multiple goals", async function() {
            this.timeout(60000);
            
            const agentName = "RapidAgent";
            const goalCount = 30;
            
            // Add goals
            for (let i = 0; i < goalCount; i++) {
                await socialGoal.addGoal(agentName, `Rapid Goal ${i}`);
            }
            
            // Activate all
            for (let i = 0; i < goalCount; i++) {
                await socialGoal.activateGoal(agentName, i);
            }
            
            // Complete all
            for (let i = 0; i < goalCount; i++) {
                await socialGoal.completeGoal(agentName, i);
            }
            
            // Verify all completed
            for (let i = 0; i < goalCount; i += 5) { // Check every 5th
                const [, status] = await socialGoal.getGoal(agentName, i);
                expect(status).to.equal(2); // Completed
            }
        });

        it("should handle interleaved state transitions", async function() {
            const agentName = "InterleavedAgent";
            
            // Add 10 goals
            for (let i = 0; i < 10; i++) {
                await socialGoal.addGoal(agentName, `Goal ${i}`);
            }
            
            // Activate even-indexed goals
            for (let i = 0; i < 10; i += 2) {
                await socialGoal.activateGoal(agentName, i);
            }
            
            // Complete some activated goals
            await socialGoal.completeGoal(agentName, 0);
            await socialGoal.completeGoal(agentName, 4);
            
            // Verify mixed states
            let [, status0] = await socialGoal.getGoal(agentName, 0);
            let [, status1] = await socialGoal.getGoal(agentName, 1);
            let [, status2] = await socialGoal.getGoal(agentName, 2);
            let [, status4] = await socialGoal.getGoal(agentName, 4);
            
            expect(status0).to.equal(2); // Completed
            expect(status1).to.equal(0); // Inactive
            expect(status2).to.equal(1); // Active
            expect(status4).to.equal(2); // Completed
        });
    });

    describe("Memory Intensive Operations", function() {
        it("should handle long goal descriptions", async function() {
            const agentName = "LongDescAgent";
            // Reduce size to avoid gas limit issues
            const longDescription = "X".repeat(5000);
            
            await socialGoal.addGoal(agentName, longDescription);
            const [description] = await socialGoal.getGoal(agentName, 0);
            
            expect(description.length).to.equal(5000);
            expect(description).to.equal(longDescription);
        });

        it("should handle many unique agent names", async function() {
            this.timeout(60000);
            
            const uniqueAgentCount = 50;
            
            for (let i = 0; i < uniqueAgentCount; i++) {
                const uniqueName = `UniqueAgent${i}_${Math.random().toString(36).substring(7)}`;
                await socialGoal.addGoal(uniqueName, `Goal for ${uniqueName}`);
                
                const [description] = await socialGoal.getGoal(uniqueName, 0);
                expect(description).to.equal(`Goal for ${uniqueName}`);
            }
        });

        it("should handle goals with varied description lengths", async function() {
            const agentName = "VariedAgent";
            
            const lengths = [1, 10, 100, 1000, 10000];
            
            for (const length of lengths) {
                const description = "A".repeat(length);
                await socialGoal.addGoal(agentName, description);
            }
            
            // Verify each
            for (let i = 0; i < lengths.length; i++) {
                const [description] = await socialGoal.getGoal(agentName, i);
                expect(description.length).to.equal(lengths[i]);
            }
        });
    });

    describe("Concurrent-like Operations", function() {
        it("should handle operations from multiple accounts", async function() {
            const accountCount = Math.min(10, signers.length);
            
            for (let i = 0; i < accountCount; i++) {
                const agentName = `Agent${i}`;
                await socialGoal.connect(signers[i]).addGoal(agentName, `Goal from account ${i}`);
                
                const [description] = await socialGoal.getGoal(agentName, 0);
                expect(description).to.equal(`Goal from account ${i}`);
            }
        });

        it("should maintain isolation between agents under load", async function() {
            this.timeout(60000);
            
            const agents = ["Alice", "Bob", "Charlie", "Dave", "Eve"];
            
            // Each agent gets 20 goals
            for (const agent of agents) {
                for (let i = 0; i < 20; i++) {
                    await socialGoal.addGoal(agent, `${agent} Goal ${i}`);
                }
            }
            
            // Verify isolation
            for (const agent of agents) {
                for (let i = 0; i < 20; i++) {
                    const [description] = await socialGoal.getGoal(agent, i);
                    expect(description).to.equal(`${agent} Goal ${i}`);
                    expect(description).to.include(agent);
                }
            }
        });
    });

    describe("Repetitive Operations", function() {
        it("should handle repeated owner address changes", async function() {
            const changeCount = 50;
            
            for (let i = 0; i < changeCount; i++) {
                const address = i % 2 === 0 ? owner.address : signers[1].address;
                await socialGoal.assignmentOwnerAddress(address);
                expect(await socialGoal.goalOwner()).to.equal(address);
            }
        });

        it("should handle repeated compartment activations", async function() {
            const activationCount = 30;
            
            for (let i = 0; i < activationCount; i++) {
                await componentCore.activateCompartment(compartmentInitiator.address);
            }
            
            // Verify compartment is still accessible
            const roleSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("TestRole"));
            const hasRole = await compartmentInitiator.hasRole(roleSpec);
            expect(hasRole).to.be.false;
        });

        it("should handle repeated role checks", async function() {
            const roleSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("CheckedRole"));
            
            // Check 100 times
            for (let i = 0; i < 100; i++) {
                expect(await compartmentInitiator.hasRole(roleSpec)).to.be.false;
            }
        });

        it("should handle repeated goal status checks", async function() {
            const agentName = "CheckAgent";
            await socialGoal.addGoal(agentName, "Checked Goal");
            await socialGoal.activateGoal(agentName, 0);
            
            // Check 100 times
            for (let i = 0; i < 100; i++) {
                const [, status] = await socialGoal.getGoal(agentName, 0);
                expect(status).to.equal(1); // Active
            }
        });
    });

    describe("Pattern-based Stress Tests", function() {
        it("should handle pyramid pattern of goals", async function() {
            this.timeout(60000);
            
            // Create pyramid: Agent0 has 1 goal, Agent1 has 2, ..., Agent9 has 10
            for (let i = 0; i < 10; i++) {
                const agentName = `PyramidAgent${i}`;
                const goalCount = i + 1;
                
                for (let j = 0; j < goalCount; j++) {
                    await socialGoal.addGoal(agentName, `Agent${i} Goal${j}`);
                }
            }
            
            // Verify pyramid structure
            for (let i = 0; i < 10; i++) {
                const agentName = `PyramidAgent${i}`;
                const expectedCount = i + 1;
                
                // Check last goal exists
                const [description] = await socialGoal.getGoal(agentName, expectedCount - 1);
                expect(description).to.equal(`Agent${i} Goal${expectedCount - 1}`);
                
                // Check next goal doesn't exist
                await expect(
                    socialGoal.getGoal(agentName, expectedCount)
                ).to.be.revertedWith("Invalid goal index");
            }
        });

        it("should handle alternating state pattern", async function() {
            const agentName = "AlternatingAgent";
            const count = 20;
            
            // Add goals
            for (let i = 0; i < count; i++) {
                await socialGoal.addGoal(agentName, `Goal ${i}`);
            }
            
            // Activate odd indices
            for (let i = 1; i < count; i += 2) {
                await socialGoal.activateGoal(agentName, i);
            }
            
            // Verify pattern
            for (let i = 0; i < count; i++) {
                const [, status] = await socialGoal.getGoal(agentName, i);
                if (i % 2 === 1) {
                    expect(status).to.equal(1); // Active
                } else {
                    expect(status).to.equal(0); // Inactive
                }
            }
        });

        it("should handle sequential batch pattern", async function() {
            this.timeout(60000);
            
            const batchSize = 10;
            const batchCount = 5;
            
            for (let batch = 0; batch < batchCount; batch++) {
                const agentName = `BatchAgent${batch}`;
                
                // Add batch
                for (let i = 0; i < batchSize; i++) {
                    await socialGoal.addGoal(agentName, `Batch${batch} Goal${i}`);
                }
                
                // Process batch
                for (let i = 0; i < batchSize; i++) {
                    await socialGoal.activateGoal(agentName, i);
                    await socialGoal.completeGoal(agentName, i);
                }
            }
            
            // Verify all batches processed
            for (let batch = 0; batch < batchCount; batch++) {
                const agentName = `BatchAgent${batch}`;
                const [, status] = await socialGoal.getGoal(agentName, 0);
                expect(status).to.equal(2); // Completed
            }
        });
    });

    describe("Resource Exhaustion Resistance", function() {
        it("should not fail with maximum reasonable role count", async function() {
            this.timeout(60000);
            
            const maxRoles = 100;
            
            // Test hasRole checks for many different specs
            for (let i = 0; i < maxRoles; i++) {
                const roleSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(`MaxRole${i}`));
                const hasRole = await compartmentInitiator.hasRole(roleSpec);
                expect(hasRole).to.be.false;
            }
        });

        it("should handle deep goal nesting per agent", async function() {
            this.timeout(60000);
            
            const agentName = "DeepAgent";
            const depth = 200;
            
            for (let i = 0; i < depth; i++) {
                await socialGoal.addGoal(agentName, `Deep Goal ${i}`);
            }
            
            // Sample verification
            const [first] = await socialGoal.getGoal(agentName, 0);
            const [middle] = await socialGoal.getGoal(agentName, depth / 2);
            const [last] = await socialGoal.getGoal(agentName, depth - 1);
            
            expect(first).to.equal("Deep Goal 0");
            expect(middle).to.equal(`Deep Goal ${depth / 2}`);
            expect(last).to.equal(`Deep Goal ${depth - 1}`);
        });
    });
});
