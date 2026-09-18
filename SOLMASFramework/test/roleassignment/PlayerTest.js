const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("Player Contract", function () {
    let Player;
    let playerContract;
    let owner;
    let addr1;
    let roleContract;

    beforeEach(async function () {
        // Get the ContractFactory and Signers here.
        [owner, addr1] = await ethers.getSigners();

        // Deploy a mock role contract to test delegateCall
        const RoleContract = await ethers.getContractFactory("MockRoleContract");
        roleContract = await RoleContract.deploy();
        await roleContract.deployed();

        // Deploy the Player contract
        Player = await ethers.getContractFactory("Player");
        playerContract = await Player.deploy(owner.address);
        await playerContract.deployed();
    });

    describe("Deployment", function () {
        it("Should set the correct owner", async function () {
            expect(await playerContract.owner()).to.equal(owner.address);
        });
    });

    describe("playDelegateCallRoleContract", function () {
        it("Should successfully delegate call and store the contract address", async function () {
            const tx = await playerContract.playDelegateCallRoleContract(
                roleContract.address,
                "playContractForIdentity()"
            );
            await tx.wait();

            expect(await playerContract.playedContractList(roleContract.address)).to.equal(true);
        });

        it("Should revert if the called function does not exist", async function () {
            await expect(
                playerContract.playDelegateCallRoleContract(roleContract.address, "nonExistentFunction()")
            ).to.be.revertedWith("Function call has been reverted");
        });

        it("Should revert if called by non-owner", async function () {
            await expect(
                playerContract.connect(addr1).playDelegateCallRoleContract(roleContract.address, "playContractForIdentity()")
            ).to.be.revertedWith("revert because it is not the owner account");
        });

    });
});

// Mock Role Contract for testing delegatecall
describe("MockRoleContract", function () {
    let MockRoleContract;
    let mockRoleContract;

    beforeEach(async function () {
        MockRoleContract = await ethers.getContractFactory("MockRoleContract");
        mockRoleContract = await MockRoleContract.deploy();
        await mockRoleContract.deployed();
    });

    it("Should have a function playContractForIdentity", async function () {
        await mockRoleContract.playContractForIdentity()
        expect(await mockRoleContract.played()).to.equal(true);
    });
});
