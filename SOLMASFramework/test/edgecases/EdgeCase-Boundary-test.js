const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("Edge Cases - Boundary Tests", function() {
    let componentCore, compartmentInitiator, socialGoal, utils;
    let owner, addr1;

    beforeEach(async function() {
        [owner, addr1] = await ethers.getSigners();

        // Deploy contracts
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();

        const CompartmentInitiator = await ethers.getContractFactory('CompartmentInitiator');
        compartmentInitiator = await CompartmentInitiator.deploy(componentCore.address);
        await compartmentInitiator.deployed();

        const SocialGoal = await ethers.getContractFactory("SocialGoal");
        socialGoal = await SocialGoal.deploy();
        await socialGoal.deployed();

        const Utils = await ethers.getContractFactory("Utils");
        utils = await Utils.deploy();
        await utils.deployed();
    });

    describe("String Length Boundaries", function() {
        it("should handle minimum length string (1 char)", async function() {
            const minStr = "A";
            const result = await utils.substring(minStr, 0, 0);
            expect(result).to.equal("A");
        });

        it("should handle 32-byte boundary string", async function() {
            const str32 = "A".repeat(32);
            await utils.stringToBytes32(str32);
            const result = await utils.resultstringToBytes32();
            expect(result.length).to.equal(66); // 0x + 64 hex chars
        });

        it("should handle 31-byte string", async function() {
            const str31 = "B".repeat(31);
            await utils.stringToBytes32(str31);
            const result = await utils.resultstringToBytes32();
            expect(result).to.not.equal('0x' + '0'.repeat(64));
        });

        it("should handle 33-byte string overflow", async function() {
            const str33 = "C".repeat(33);
            // Should truncate or handle gracefully
            await utils.stringToBytes32(str33);
            const result = await utils.resultstringToBytes32();
            expect(result.length).to.equal(66);
        });

        it("should handle maximum practical string length", async function() {
            const maxStr = "D".repeat(1000);
            const agentName = "MaxAgent";
            
            await socialGoal.addGoal(agentName, maxStr);
            const [description] = await socialGoal.getGoal(agentName, 0);
            expect(description).to.equal(maxStr);
        });
    });

    describe("Index Boundaries", function() {
        it("should handle index 0", async function() {
            const agentName = "Agent";
            await socialGoal.addGoal(agentName, "Goal 0");
            
            const [description] = await socialGoal.getGoal(agentName, 0);
            expect(description).to.equal("Goal 0");
        });

        it("should handle maximum valid index", async function() {
            const agentName = "Agent";
            const count = 10;
            
            for (let i = 0; i < count; i++) {
                await socialGoal.addGoal(agentName, `Goal ${i}`);
            }
            
            const [description] = await socialGoal.getGoal(agentName, count - 1);
            expect(description).to.equal(`Goal ${count - 1}`);
        });

        it("should fail on index just beyond array length", async function() {
            const agentName = "Agent";
            await socialGoal.addGoal(agentName, "Single Goal");
            
            await expect(
                socialGoal.getGoal(agentName, 1)
            ).to.be.revertedWith("Invalid goal index");
        });

        it("should fail on very large invalid index", async function() {
            const agentName = "Agent";
            await socialGoal.addGoal(agentName, "Goal");
            
            await expect(
                socialGoal.getGoal(agentName, 9999999)
            ).to.be.revertedWith("Invalid goal index");
        });
    });

    describe("Substring Index Boundaries", function() {
        it("should handle substring at string start", async function() {
            const str = "StartMiddleEnd";
            const result = await utils.substring(str, 0, 4);
            expect(result).to.equal("Start");
        });

        it("should handle substring at string end", async function() {
            const str = "StartMiddleEnd";
            const result = await utils.substring(str, 11, 13);
            expect(result).to.equal("End");
        });

        it("should handle substring in middle", async function() {
            const str = "StartMiddleEnd";
            const result = await utils.substring(str, 5, 10);
            expect(result).to.equal("Middle");
        });

        it("should handle single character substring", async function() {
            const str = "ABCDE";
            const result = await utils.substring(str, 2, 2);
            expect(result).to.equal("C");
        });

        it("should handle full string as substring", async function() {
            const str = "FullString";
            const result = await utils.substring(str, 0, str.length - 1);
            expect(result).to.equal(str);
        });
    });

    describe("Numeric Boundaries", function() {
        it("should handle goal status enum boundaries", async function() {
            const agentName = "StatusAgent";
            await socialGoal.addGoal(agentName, "Test");
            
            // Status 0 - Inactive
            let [, status] = await socialGoal.getGoal(agentName, 0);
            expect(status).to.equal(0);
            
            // Status 1 - Active
            await socialGoal.activateGoal(agentName, 0);
            [, status] = await socialGoal.getGoal(agentName, 0);
            expect(status).to.equal(1);
            
            // Status 2 - Completed
            await socialGoal.completeGoal(agentName, 0);
            [, status] = await socialGoal.getGoal(agentName, 0);
            expect(status).to.equal(2);
        });

        it("should handle role spec hash boundaries", async function() {
            // Test with various valid role specs (hashed strings)
            const specs = [
                ethers.utils.keccak256(ethers.utils.toUtf8Bytes("")),
                ethers.utils.keccak256(ethers.utils.toUtf8Bytes("Role1")),
                ethers.utils.keccak256(ethers.utils.toUtf8Bytes("A".repeat(100)))
            ];
            
            // Note: addRole may require role creators to be set up first
            // Testing that the contract can handle various hash values
            for (const spec of specs) {
                const hasRoleBefore = await compartmentInitiator.hasRole(spec);
                expect(hasRoleBefore).to.be.false;
            }
        });

        it("should handle bytes32 conversion boundaries", async function() {
            // Single character conversions
            await utils.stringToBytes32("A");
            let result = await utils.resultstringToBytes32();
            expect(result.length).to.equal(66); // 0x + 64 hex chars
            expect(result).to.match(/^0x41/); // 'A' is 0x41 in ASCII
            
            // Max ASCII character
            await utils.stringToBytes32("~");
            result = await utils.resultstringToBytes32();
            expect(result.length).to.equal(66);
            expect(result).to.not.equal('0x' + '0'.repeat(64));
        });
    });

    describe("Address Boundaries", function() {
        it("should handle zero address", async function() {
            await socialGoal.assignmentOwnerAddress(ethers.constants.AddressZero);
            expect(await socialGoal.goalOwner()).to.equal(ethers.constants.AddressZero);
        });

        it("should handle valid address formats", async function() {
            // Use a valid address instead of raw hex string
            const validAddress = addr1.address;
            await socialGoal.assignmentOwnerAddress(validAddress);
            expect(await socialGoal.goalOwner()).to.equal(validAddress);
        });

        it("should handle contract address as owner", async function() {
            await socialGoal.assignmentOwnerAddress(socialGoal.address);
            expect(await socialGoal.goalOwner()).to.equal(socialGoal.address);
        });

        it("should handle compartment activation with own address", async function() {
            // Activating with own address should revert because ComponentCore doesn't implement compartment interface
            await expect(
                componentCore.activateCompartment(componentCore.address)
            ).to.be.revertedWith("Doesn't support compartment interface.");
        });
    });

    describe("Array Length Boundaries", function() {
        it("should handle empty array state", async function() {
            const agentName = "NewAgent";
            
            await expect(
                socialGoal.getGoal(agentName, 0)
            ).to.be.revertedWith("Invalid goal index");
        });

        it("should handle single element array", async function() {
            const agentName = "SingleAgent";
            await socialGoal.addGoal(agentName, "Only Goal");
            
            const [description] = await socialGoal.getGoal(agentName, 0);
            expect(description).to.equal("Only Goal");
            
            await expect(
                socialGoal.getGoal(agentName, 1)
            ).to.be.revertedWith("Invalid goal index");
        });

        it("should handle large array", async function() {
            const agentName = "ManyGoalsAgent";
            const goalCount = 100;
            
            for (let i = 0; i < goalCount; i++) {
                await socialGoal.addGoal(agentName, `Goal ${i}`);
            }
            
            // Check first
            const [first] = await socialGoal.getGoal(agentName, 0);
            expect(first).to.equal("Goal 0");
            
            // Check last
            const [last] = await socialGoal.getGoal(agentName, goalCount - 1);
            expect(last).to.equal(`Goal ${goalCount - 1}`);
            
            // Check beyond
            await expect(
                socialGoal.getGoal(agentName, goalCount)
            ).to.be.revertedWith("Invalid goal index");
        });
    });

    describe("State Transition Boundaries", function() {
        it("should not allow skipping states", async function() {
            const agentName = "TransitionAgent";
            await socialGoal.addGoal(agentName, "Test");
            
            // Cannot complete without activating
            await expect(
                socialGoal.completeGoal(agentName, 0)
            ).to.be.revertedWith("Goal is not active");
        });

        it("should not allow reverse state transitions", async function() {
            const agentName = "ReverseAgent";
            await socialGoal.addGoal(agentName, "Test");
            await socialGoal.activateGoal(agentName, 0);
            await socialGoal.completeGoal(agentName, 0);
            
            // Cannot activate completed goal
            await expect(
                socialGoal.activateGoal(agentName, 0)
            ).to.be.revertedWith("Goal is already active or completed");
        });

        it("should prevent double activation", async function() {
            const agentName = "DoubleAgent";
            await socialGoal.addGoal(agentName, "Test");
            await socialGoal.activateGoal(agentName, 0);
            
            await expect(
                socialGoal.activateGoal(agentName, 0)
            ).to.be.revertedWith("Goal is already active or completed");
        });
    });

    describe("Character Encoding Boundaries", function() {
        it("should handle ASCII characters", async function() {
            const ascii = "ASCII Test 123!@#";
            await utils.stringToBytes32(ascii);
            const result = await utils.resultstringToBytes32();
            expect(result).to.not.equal('0x' + '0'.repeat(64));
        });

        it("should handle UTF-8 characters", async function() {
            const utf8 = "Hello 世界 🌍";
            const agentName = "UTF8Agent";
            
            await socialGoal.addGoal(agentName, utf8);
            const [description] = await socialGoal.getGoal(agentName, 0);
            expect(description).to.equal(utf8);
        });

        it("should handle control characters", async function() {
            const control = "Line1\nLine2\tTab\r";
            const agentName = "ControlAgent";
            
            await socialGoal.addGoal(agentName, control);
            const [description] = await socialGoal.getGoal(agentName, 0);
            expect(description).to.equal(control);
        });
    });
});
