const {expect} = require("chai");
const {ethers} = require("hardhat");

describe("DeepRole1", function() {
    let DeepRole1;
    let deepRole1;
    let DummyRoleForTesting;
    let dummyRoleForTesting;
    let owner;
    let addr1;

    beforeEach(async function() {
        [owner, addr1] = await ethers.getSigners();
        DeepRole1 = await ethers.getContractFactory("DeepRole1");
        deepRole1 = await DeepRole1.deploy();
        await deepRole1.deployed();

        DummyRoleForTesting = await ethers.getContractFactory("DummyRoleForTesting");
        dummyRoleForTesting = await DummyRoleForTesting.deploy();
        await dummyRoleForTesting.deployed();
    });

    it("Should initialize correctly", async function() {
        expect(await deepRole1.initialized()).to.equal(false);

        //Initialize the contract
        await deepRole1.initialize();

        //Check if initialized and owner set correctly
        expect(await deepRole1.initialized()).to.equal(true);
        expect(await deepRole1.owner()).to.equal(owner.address);
    });

    it("Should not allow re-initialization", async function() {
       await deepRole1.initialize();

       await expect(deepRole1.initialize()).to.be.revertedWith("Already initialized");
    });

    it("Should increment my uint correctly", async function() {
        await deepRole1.initialize();
        expect(await deepRole1.myUint()).to.equal(0);

        await deepRole1.increment();
        expect(await deepRole1.myUint()).to.equal(1);

        await deepRole1.increment();
        expect(await deepRole1.myUint()).to.equal(2);
    });

    it("Should only allow owner to update code", async function() {
       await deepRole1.initialize();

       await expect(deepRole1.connect(addr1).updateCode(addr1.address)).to.be.revertedWith("Only owner can update the code");
       console.log("dummyRoleForTesting",  dummyRoleForTesting.address);
       await expect(deepRole1.connect(owner).updateCode(dummyRoleForTesting.address));
       // await deepRole1.dummyFunction();
    });
});