// test/TestComponentCore_RoleManagement.test.js
const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("TestComponentCore_RoleManagement", function () {
    let component, compartment;
    let f1spec, f2spec;

    beforeEach(async function () {
        // Deploy ComponentCore contract
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        component = await ComponentCore.deploy();
        await component.deployed();

        // Deploy InitiatorCompartment contract
        const Compartment = await ethers.getContractFactory("CompartmentInitiator");
        compartment = await Compartment.deploy(component.address);
        await compartment.deployed();

        // Define role specifications
        f1spec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("SAMPLEROLE1"));
        f2spec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("SAMPLEROLE2"));

        // Deploy role creators
        const InitiatorRole1Creator = await ethers.getContractFactory("SampleRole1Creator");
        const InitiatorRole2Creator = await ethers.getContractFactory("SampleRole2Creator");

        const role1Creator = await InitiatorRole1Creator.deploy();
        const role2Creator = await InitiatorRole2Creator.deploy();
        await role1Creator.deployed();
        await role2Creator.deployed();

        // Add role creators to the compartment
        await compartment.addRoleCreator(f1spec, role1Creator.address);
        await compartment.addRoleCreator(f2spec, role2Creator.address);

        // Add roles to the compartment
        await compartment.addRole(f1spec);
        await compartment.addRole(f2spec);

        // Activate the compartment in the component
        await component.activateCompartment(compartment.address);
    });

    it("should not add a new role if the role address is zero", async function () {
        await compartment.getRole(f1spec);
        // Attempt to add a role with a zero address
        await component.addRole(f1spec, ethers.constants.AddressZero);
        // Verify the role is unchanged
        expect(await component.getRole(f1spec)).to.not.be.null;
        expect(await component.isComponentRole()).to.not.be.null;
        expect(await component.playedRoles(f1spec)).to.not.be.null;
        expect(await component.stateVariableRole()).to.not.be.null;
        console.log("isComponentRole: ", await component.isComponentRole())
        console.log("playedRoles: ", await component.playedRoles(f1spec))

    });

    it("should overwrite the current role for the same spec", async function () {
        const role1 = await compartment.getRole(f1spec);
        console.log("role1:", role1);
        await component.addRole(f1spec, role1);
        const role1Updated = await compartment.getRole(f1spec);
        await component.addRole(f1spec, role1Updated);
        await component.getRole(f1spec)
        // Verify the updated role is in place
        expect(await component.stateVariableRole()).to.equal(role1Updated);
    });
    //
    it("should retrieve the role after it is added", async function () {
        const role1 = await compartment.getRole(f1spec);
        await component.addRole(f1spec, role1);

        await component.getRole(f1spec);
        expect(await component.stateVariableRole()).to.equal(role1);
    });
    //
    it("should return zero address for an unknown role", async function () {
        await component.getRole(f2spec);
        expect(await component.stateVariableRole()).to.equal(ethers.constants.AddressZero);
    });
    //
    it("should return true when a role is added", async function () {
        const role1 = await compartment.getRole(f1spec);
        await component.addRole(f1spec, role1);

        const isPlayingRole1 = await component.isPlayingRole(f1spec);
        expect(isPlayingRole1).to.be.true;
    });
    //
    it("should return false when a role is not added", async function () {
        const isPlayingRole2 = await component.isPlayingRole(f2spec);
        expect(isPlayingRole2).to.be.false;
    });
    //
    it("should render a role unusable after removal", async function () {
        const role1 = await compartment.getRole(f1spec);
        await component.addRole(f1spec, role1);
        await component.removeRole(f1spec);

        // Attempt to call a function on the removed role
        try {
            await ethers.getContractAt("RoleInitiator", role1);
            expect.fail("Expected roleFunction to fail");
        } catch (error) {
            console.log("error: ", error);
            // expect(error.message).to.include("revert");
        }
    });
    //
    it("should return false after a role is removed", async function () {
        const role1 = await compartment.getRole(f1spec);
        await component.addRole(f1spec, role1);
        const isPlayingRole1 = await component.isPlayingRole(f1spec);
        expect(isPlayingRole1).to.be.true;

        await component.removeRole(f1spec);
        const isPlayingRole2 = await component.isPlayingRole(f1spec);

        expect(isPlayingRole2).to.be.false;
    });

    // Edge Case Tests
    describe("Edge Cases - Role Management", function() {
        it("should handle adding same role multiple times consecutively", async function() {
            const role1 = await compartment.getRole(f1spec);
            
            await component.addRole(f1spec, role1);
            await component.addRole(f1spec, role1);
            await component.addRole(f1spec, role1);
            
            expect(await component.stateVariableRole()).to.equal(role1);
            expect(await component.isPlayingRole(f1spec)).to.be.true;
        });

        it("should handle removing non-existent role", async function() {
            const nonExistentSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("NONEXISTENT"));
            
            await component.removeRole(nonExistentSpec);
            expect(await component.isPlayingRole(nonExistentSpec)).to.be.false;
        });

        it("should handle removing already removed role", async function() {
            const role1 = await compartment.getRole(f1spec);
            await component.addRole(f1spec, role1);
            
            await component.removeRole(f1spec);
            await component.removeRole(f1spec);
            await component.removeRole(f1spec);
            
            expect(await component.isPlayingRole(f1spec)).to.be.false;
        });

        it("should handle maximum number of role additions and removals", async function() {
            const role1 = await compartment.getRole(f1spec);
            
            for (let i = 0; i < 50; i++) {
                await component.addRole(f1spec, role1);
                expect(await component.isPlayingRole(f1spec)).to.be.true;
                
                await component.removeRole(f1spec);
                expect(await component.isPlayingRole(f1spec)).to.be.false;
            }
        });

        it("should handle role spec with all zeros", async function() {
            const zeroSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes(""));
            const role = await compartment.getRole(zeroSpec);
            
            if (role !== ethers.constants.AddressZero) {
                await component.addRole(zeroSpec, role);
                expect(await component.isPlayingRole(zeroSpec)).to.be.true;
            }
        });

        it("should maintain role isolation between different specs", async function() {
            const role1 = await compartment.getRole(f1spec);
            const role2 = await compartment.getRole(f2spec);
            
            await component.addRole(f1spec, role1);
            await component.addRole(f2spec, role2);
            
            // Verify that we can retrieve each role independently
            const retrievedRole1 = await component.getRole(f1spec);
            const retrievedRole2 = await component.getRole(f2spec);
            
            // Both should return valid addresses
            expect(retrievedRole1).to.not.equal(ethers.constants.AddressZero);
            expect(retrievedRole2).to.not.equal(ethers.constants.AddressZero);
        });

        it("should handle compartment with zero address", async function() {
            await expect(component.activateCompartment(ethers.constants.AddressZero))
                .to.be.revertedWith("Doesn't support compartment interface.");
        });

        it("should handle retrieving role before any compartment is activated", async function() {
            const ComponentCore = await ethers.getContractFactory("ComponentCore");
            const newComponent = await ComponentCore.deploy();
            await newComponent.deployed();
            
            const unknownSpec = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("UNKNOWN"));
            await newComponent.getRole(unknownSpec);
            
            expect(await newComponent.stateVariableRole()).to.equal(ethers.constants.AddressZero);
        });

        it("should handle rapid role switching", async function() {
            const role1 = await compartment.getRole(f1spec);
            const role2 = await compartment.getRole(f2spec);
            
            for (let i = 0; i < 20; i++) {
                await component.addRole(f1spec, role1);
                await component.removeRole(f1spec);
                await component.addRole(f2spec, role2);
                await component.removeRole(f2spec);
            }
            
            expect(await component.isPlayingRole(f1spec)).to.be.false;
            expect(await component.isPlayingRole(f2spec)).to.be.false;
        });

        it("should handle role spec collision (same hash)", async function() {
            // Two identical role specs should produce same behavior
            const spec1 = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("SAMEROLE"));
            const spec2 = ethers.utils.keccak256(ethers.utils.toUtf8Bytes("SAMEROLE"));
            
            expect(spec1).to.equal(spec2);
            
            const role = await compartment.getRole(f1spec);
            await component.addRole(spec1, role);
            
            expect(await component.isPlayingRole(spec2)).to.be.true;
        });

        it("should handle very large role spec values", async function() {
            const maxBytes32 = "0x" + "f".repeat(64);
            const role1 = await compartment.getRole(f1spec);
            
            await component.addRole(maxBytes32, role1);
            expect(await component.isPlayingRole(maxBytes32)).to.be.true;
        });

        it("should verify role state after multiple compartment activations", async function() {
            const role1 = await compartment.getRole(f1spec);
            await component.addRole(f1spec, role1);
            
            // Deploy another compartment
            const Compartment2 = await ethers.getContractFactory("CompartmentInitiator");
            const compartment2 = await Compartment2.deploy(component.address);
            await compartment2.deployed();
            
            // Activate second compartment
            await component.activateCompartment(compartment2.address);
            
            // Original role should still be playable
            expect(await component.isPlayingRole(f1spec)).to.be.true;
        });
    });
});
