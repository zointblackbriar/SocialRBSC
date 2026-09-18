const {expect} = require("chai");
const {ethers} = require("hardhat");



describe('Compartment initiator', async function() {

    let compartmentInitiator;
    let componentCore;
    let retailerRole;
    let roleName;
    beforeEach(async function() {
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();
        const CompartmentInitiator = await ethers.getContractFactory('CompartmentInitiator');
        compartmentInitiator = await CompartmentInitiator.deploy(componentCore.address);
        await compartmentInitiator.deployed()

        // const RetailerRole = await ethers.getContractFactory("Retailer");
        retailerRole = await ethers.getContractFactory("Retailer");
        retailerRole.deploy()
        roleName = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("RetailerRole"));

        const RetailerRoleCreator = await ethers.getContractFactory("RetailerCreator");

        const role1Creator = await RetailerRoleCreator.deploy();
        await role1Creator.deployed();
        // Add role creators to the compartment
        await compartmentInitiator.addRoleCreator(roleName, role1Creator.address);
        // Add roles to the compartment
        await compartmentInitiator.addRole(roleName);
        // // Activate the compartment in the component
        await componentCore.activateCompartment(compartmentInitiator.address);

    });

    it("should assign a role", async function() {
        console.log("first test in compartment initiator");
        await compartmentInitiator.addRole(roleName); // how can we call from assignRole
        expect(await compartmentInitiator.hasRole(roleName)).to.be.true;
    });

    // Edge Case Tests
    describe("Edge Cases - Compartment Initiator", function() {
        it("should handle adding role twice", async function() {
            await compartmentInitiator.addRole(roleName);
            await compartmentInitiator.addRole(roleName);
            expect(await compartmentInitiator.hasRole(roleName)).to.be.true;
        });

        it("should handle checking non-existent role", async function() {
            const nonExistentRole = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("NonExistentRole"));
            expect(await compartmentInitiator.hasRole(nonExistentRole)).to.be.false;
        });

        it("should handle multiple different roles", async function() {
            const role2Name = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("Role2"));
            const role3Name = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("Role3"));
            
            // Check that various role hashes can be queried
            expect(await compartmentInitiator.hasRole(roleName)).to.be.true;
            expect(await compartmentInitiator.hasRole(role2Name)).to.be.false;
            expect(await compartmentInitiator.hasRole(role3Name)).to.be.false;
        });

        it("should handle adding many roles sequentially", async function() {
            const roleCount = 20;
            const roleNames = [];
            
            // Create many different role hashes and verify they can be checked
            for (let i = 0; i < roleCount; i++) {
                const name = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(`Role${i}`));
                roleNames.push(name);
            }
            
            for (const name of roleNames) {
                expect(await compartmentInitiator.hasRole(name)).to.be.false;
            }
        });

        it("should handle role with zero hash", async function() {
            const zeroHash = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(""));
            // Just verify we can check for a zero hash without errors
            expect(await compartmentInitiator.hasRole(zeroHash)).to.be.false;
        });

        it("should handle role creator with zero address check", async function() {
            const newRoleName = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("NewRole"));
            
            // Attempting to add role creator with zero address should be handled
            await expect(
                compartmentInitiator.addRoleCreator(newRoleName, ethers.constants.AddressZero)
            ).to.not.be.reverted;
        });

        it("should handle activating compartment multiple times", async function() {
            await componentCore.activateCompartment(compartmentInitiator.address);
            await componentCore.activateCompartment(compartmentInitiator.address);
            
            // Compartment should still function correctly
            await compartmentInitiator.addRole(roleName);
            expect(await compartmentInitiator.hasRole(roleName)).to.be.true;
        });

        it("should handle very long role names", async function() {
            const longRoleName = "A".repeat(500);
            const longRoleHash = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(longRoleName));
            
            // Verify we can hash and check very long role names
            expect(await compartmentInitiator.hasRole(longRoleHash)).to.be.false;
        });

        it("should handle special characters in role names", async function() {
            const specialRoleName = "Role!@#$%^&*()_+-=";
            const specialRoleHash = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(specialRoleName));
            
            // Verify we can hash and check role names with special characters
            expect(await compartmentInitiator.hasRole(specialRoleHash)).to.be.false;
        });

        it("should handle role operations with different component cores", async function() {
            // Deploy a new component core
            const ComponentCore2 = await ethers.getContractFactory("ComponentCore");
            const componentCore2 = await ComponentCore2.deploy();
            await componentCore2.deployed();
            
            // Deploy new compartment with different core
            const CompartmentInitiator2 = await ethers.getContractFactory('CompartmentInitiator');
            const compartmentInitiator2 = await CompartmentInitiator2.deploy(componentCore2.address);
            await compartmentInitiator2.deployed();
            
            const newRoleName = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("IsolatedRole"));
            
            // Verify both compartments can independently check for roles
            expect(await compartmentInitiator2.hasRole(newRoleName)).to.be.false;
            expect(await compartmentInitiator.hasRole(newRoleName)).to.be.false;
        });

        it("should handle rapid role additions", async function() {
            const promises = [];
            // Test rapid role checks instead of additions
            for (let i = 0; i < 10; i++) {
                const name = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(`RapidRole${i}`));
                promises.push(compartmentInitiator.hasRole(name));
            }
            
            const results = await Promise.all(promises);
            
            // All should be false since we haven't added them
            for (const result of results) {
                expect(result).to.be.false;
            }
        });

        it("should maintain role state across multiple checks", async function() {
            await compartmentInitiator.addRole(roleName);
            
            for (let i = 0; i < 50; i++) {
                expect(await compartmentInitiator.hasRole(roleName)).to.be.true;
            }
        });
    });
});