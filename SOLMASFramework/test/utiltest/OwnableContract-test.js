const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("Ownable smart contract test", function () {

    let ownable;
    let owner;
    let addr1;
    let addr2;

    beforeEach(async function() {
        const Ownable = await ethers.getContractFactory("Ownable");
        [owner, addr1, addr2] = await ethers.getSigners();
        ownable = await Ownable.deploy(); // Deploy the smart contract
        await ownable.deployed(); // wait for the be mined
    });

    it("should set the deployer as the owner", async function() {
       expect(await ownable.owner()).to.equal(owner.address);
    });

    it("should allow the owner to transfer ownership",  async function() {
        await ownable.transferOwnership(addr1.address); // transfer ownership to addr1
        expect(await ownable.owner()).to.equal(addr1.address); // check the new owner
    });

    it("Should restrict non owners from transferring ownership", async function() {
        await expect(ownable.connect(addr1).transferOwnership(addr2.address)).to.be.revertedWith("Ownable: caller is not the owner")
    });

    it("should not allow ownership transfer to zero address", async function() {
       await expect(ownable.transferOwnership("0x0000000000000000000000000000000000000000")).to.be.revertedWith("Ownable: new owner is the zero address")
    });

    it("should allow the owner to renounce ownership", async function() {
       await ownable.renounceOwnership();
       expect(await ownable.owner()).to.equal("0x0000000000000000000000000000000000000000");
    });

    it("should restrict non-owners from renouncing ownership", async function() {
       await expect(ownable.connect(addr1).renounceOwnership()).to.be.revertedWith("Ownable: caller is not the owner");
    });
});