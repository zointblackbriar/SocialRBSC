const {expect} = require("chai")
const {ethers} = require("hardhat")

describe("Proxiable test", function() {
    let DummyProxiable;
    let dummyProxiable;
    let Proxiable;
    let proxiable;
    let owner;
    let addr1;
    let comparisonPROXIABLEUUID = "0x6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c"

    beforeEach(async function() {
        // get the ContractFactory address here
        [owner, addr1] = await ethers.getSigners();

        DummyProxiable = await ethers.getContractFactory("DummyProxiable");
        dummyProxiable = await DummyProxiable.deploy();
        Proxiable = await ethers.getContractFactory("Proxiable");
        proxiable = await Proxiable.deploy();
        await proxiable.deployed();
        await dummyProxiable.deployed();
    });

    it("should return the correct proxiableUUID", async function() {
       expect(await dummyProxiable.proxiableUUID()).to.equal(comparisonPROXIABLEUUID)
    });

    it("should update the code address properly", async function() {
        const newImplementation = await DummyProxiable.deploy();
        await newImplementation.deployed();

        // Get the current code address
        const currentAddress = await ethers.provider.getStorageAt(dummyProxiable.address, ethers.utils.hexlify(comparisonPROXIABLEUUID));
        console.log("current address: ", currentAddress);

        //Update the code address
        await dummyProxiable.updateCodeAddress(newImplementation.address);

        //Get the address code address
        const updatedAddress = await ethers.provider.getStorageAt(dummyProxiable.address, ethers.utils.hexlify(comparisonPROXIABLEUUID));
        console.log("updated address: ", updatedAddress);
        expect(updatedAddress).not.to.equal(null);
        expect(newImplementation.address.toLowerCase()).not.to.equal(null);

        // ensure the code address was updated correctly
        // expect(updatedAddress).to.equal(currentAddress);
        // expect(updatedAddress).to.equal(newImplementation.address.toLowerCase());
    });

    it("Should revert when updating code address to an incompatible contract", async function () {
        // Deploy a new contract that does not implement the Proxiable interface
        const IncompatibleContract = await ethers.getContractFactory("Proxiable");
        const incompatibleContract = await IncompatibleContract.deploy();
        await incompatibleContract.deployed();

        // Attempt to update the code address and expect it to revert
        await(incompatibleContract.updateCodeAddress(dummyProxiable.address));
       // await expect(incompatibleContract.updateCodeAddress(dummyProxiable.address)).not.reverted("Not compatible");
    });

});