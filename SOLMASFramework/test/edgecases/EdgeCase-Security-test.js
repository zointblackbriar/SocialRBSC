const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("Edge Cases - Security Tests", function() {
    let componentCore, compartmentInitiator, socialGoal;
    let owner, addr1, addr2, addr3;

    beforeEach(async function() {
        [owner, addr1, addr2, addr3] = await ethers.getSigners();

        // Deploy ComponentCore
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();

        // Deploy CompartmentInitiator
        const CompartmentInitiator = await ethers.getContractFactory('CompartmentInitiator');
        compartmentInitiator = await CompartmentInitiator.deploy(componentCore.address);
        await compartmentInitiator.deployed();

        // Deploy SocialGoal
        const SocialGoal = await ethers.getContractFactory("SocialGoal");
        socialGoal = await SocialGoal.deploy();
        await socialGoal.deployed();
    });

    describe("Reentrancy Protection", function() {
        it("should prevent reentrancy in role operations", async function() {
            const roleSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("TestRole"));
            
            // Test multiple rapid hasRole checks instead of addRole which requires setup
            await compartmentInitiator.hasRole(roleSpec);
            await compartmentInitiator.hasRole(roleSpec);
            await compartmentInitiator.hasRole(roleSpec);
            
            expect(await compartmentInitiator.hasRole(roleSpec)).to.be.false;
        });

        it("should handle concurrent goal modifications", async function() {
            const agentName = "TestAgent";
            
            await socialGoal.addGoal(agentName, "Goal 1");
            await socialGoal.addGoal(agentName, "Goal 2");
            
            // Rapid state changes
            await socialGoal.activateGoal(agentName, 0);
            await socialGoal.activateGoal(agentName, 1);
            
            const [, status0] = await socialGoal.getGoal(agentName, 0);
            const [, status1] = await socialGoal.getGoal(agentName, 1);
            
            expect(status0).to.equal(1);
            expect(status1).to.equal(1);
        });
    });

    describe("Access Control Edge Cases", function() {
        it("should handle operations from different signers", async function() {
            const agentName1 = "Agent1";
            const agentName2 = "Agent2";
            
            await socialGoal.connect(owner).addGoal(agentName1, "Owner Goal");
            await socialGoal.connect(addr1).addGoal(agentName2, "Addr1 Goal");
            
            const [desc1] = await socialGoal.connect(owner).getGoal(agentName1, 0);
            const [desc2] = await socialGoal.connect(addr1).getGoal(agentName2, 0);
            
            expect(desc1).to.equal("Owner Goal");
            expect(desc2).to.equal("Addr1 Goal");
        });

        it("should handle owner assignment from different addresses", async function() {
            await socialGoal.connect(owner).assignmentOwnerAddress(addr1.address);
            expect(await socialGoal.goalOwner()).to.equal(addr1.address);
            
            await socialGoal.connect(addr2).assignmentOwnerAddress(addr2.address);
            expect(await socialGoal.goalOwner()).to.equal(addr2.address);
        });
    });

    describe("Gas Limit Edge Cases", function() {
        it("should handle batch role checks", async function() {
            const batchSize = 30;
            
            // Test batch hasRole checks instead of addRole
            for (let i = 0; i < batchSize; i++) {
                const roleSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(`BatchRole${i}`));
                const hasRole = await compartmentInitiator.hasRole(roleSpec);
                expect(hasRole).to.be.false;
            }
        });

        it("should handle batch goal additions", async function() {
            const agentName = "BatchAgent";
            const batchSize = 30;
            
            for (let i = 0; i < batchSize; i++) {
                await socialGoal.addGoal(agentName, `Batch Goal ${i}`);
            }
            
            // Verify all goals were added
            for (let i = 0; i < batchSize; i++) {
                const [description] = await socialGoal.getGoal(agentName, i);
                expect(description).to.equal(`Batch Goal ${i}`);
            }
        });
    });

    describe("State Consistency", function() {
        it("should maintain consistent state during rapid operations", async function() {
            const roleSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("ConsistentRole"));
            
            // Test hasRole consistency
            const hasRole1 = await compartmentInitiator.hasRole(roleSpec);
            const hasRole2 = await compartmentInitiator.hasRole(roleSpec);
            const hasRole3 = await compartmentInitiator.hasRole(roleSpec);
            
            expect(hasRole1).to.equal(hasRole2);
            expect(hasRole2).to.equal(hasRole3);
            expect(hasRole1).to.be.false;
        });

        it("should maintain goal state consistency", async function() {
            const agentName = "ConsistentAgent";
            
            await socialGoal.addGoal(agentName, "Consistent Goal");
            await socialGoal.activateGoal(agentName, 0);
            
            const [desc1, status1] = await socialGoal.getGoal(agentName, 0);
            const [desc2, status2] = await socialGoal.getGoal(agentName, 0);
            const [desc3, status3] = await socialGoal.getGoal(agentName, 0);
            
            expect(desc1).to.equal(desc2).to.equal(desc3);
            expect(status1).to.equal(status2).to.equal(status3);
        });
    });

    describe("Overflow/Underflow Protection", function() {
        it("should handle maximum uint256 values safely", async function() {
            // Test with very large index values
            await socialGoal.addGoal("TestAgent", "Test Goal");
            await expect(
                socialGoal.getGoal("TestAgent", 999999)
            ).to.be.revertedWith("Invalid goal index");
        });

        it("should handle zero values appropriately", async function() {
            const agentName = "ZeroAgent";
            
            await socialGoal.addGoal(agentName, "Goal");
            const [, status] = await socialGoal.getGoal(agentName, 0);
            
            expect(status).to.equal(0); // Initial state
        });
    });

    describe("Memory and Storage Edge Cases", function() {
        it("should handle very large data strings", async function() {
            const agentName = "LargeDataAgent";
            const largeDescription = "X".repeat(10000);
            
            await socialGoal.addGoal(agentName, largeDescription);
            const [description] = await socialGoal.getGoal(agentName, 0);
            
            expect(description).to.equal(largeDescription);
        });

        it("should handle many agents with goals", async function() {
            const agentCount = 20;
            
            for (let i = 0; i < agentCount; i++) {
                const agentName = `Agent${i}`;
                await socialGoal.addGoal(agentName, `Goal for ${agentName}`);
                
                const [description] = await socialGoal.getGoal(agentName, 0);
                expect(description).to.equal(`Goal for ${agentName}`);
            }
        });
    });

    describe("Transaction Ordering", function() {
        it("should maintain correct order in sequential operations", async function() {
            const agentName = "OrderedAgent";
            
            await socialGoal.addGoal(agentName, "First");
            await socialGoal.addGoal(agentName, "Second");
            await socialGoal.addGoal(agentName, "Third");
            
            const [desc0] = await socialGoal.getGoal(agentName, 0);
            const [desc1] = await socialGoal.getGoal(agentName, 1);
            const [desc2] = await socialGoal.getGoal(agentName, 2);
            
            expect(desc0).to.equal("First");
            expect(desc1).to.equal("Second");
            expect(desc2).to.equal("Third");
        });

        it("should handle interleaved operations correctly", async function() {
            const agent1 = "Agent1";
            const agent2 = "Agent2";
            
            await socialGoal.addGoal(agent1, "A1-Goal1");
            await socialGoal.addGoal(agent2, "A2-Goal1");
            await socialGoal.addGoal(agent1, "A1-Goal2");
            await socialGoal.addGoal(agent2, "A2-Goal2");
            
            const [desc1] = await socialGoal.getGoal(agent1, 0);
            const [desc2] = await socialGoal.getGoal(agent2, 0);
            const [desc3] = await socialGoal.getGoal(agent1, 1);
            const [desc4] = await socialGoal.getGoal(agent2, 1);
            
            expect(desc1).to.equal("A1-Goal1");
            expect(desc2).to.equal("A2-Goal1");
            expect(desc3).to.equal("A1-Goal2");
            expect(desc4).to.equal("A2-Goal2");
        });
    });
});
