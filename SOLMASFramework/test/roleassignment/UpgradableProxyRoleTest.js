const {expect} = require('chai');
const {ethers} = require('hardhat');
const {
    BN,           // Big Number support
    constants,    // Common constants, like the zero address and largest integers
    expectEvent,  // Assertions for emitted events
    expectRevert, // Assertions for transactions that should fail
} = require('@openzeppelin/test-helpers');
const {utils} = require("ethers");
const {web3} = require("@nomiclabs/hardhat-web3");


describe("Upgradable Proxy Deployment", () => {
    let deepRole1;
    let deepRole2;
    let baseRole;
    let compartment;
    let componentCore;
    let proxiable;
    let testSlot;
    let owner
    let address1;
    let address2;

    beforeEach(async function () {
        [owner, address1, address2] = await ethers.getSigners();

        const DeepRole1 = await ethers.getContractFactory("DeepRole1");
        const DeepRole2 = await ethers.getContractFactory("DeepRole2");
        const Proxiable = await ethers.getContractFactory("Proxiable");
        const BaseRole = await ethers.getContractFactory("BaseRole"); //Proxy Contract
        const TestSlot = await ethers.getContractFactory("TestSlot");
        const ComponentCore = await ethers.getContractFactory('contracts/staticroleassignment/ComponentCore.sol:ComponentCore');
        const Compartment = await ethers.getContractFactory('TemporaryCompartment');

        testSlot = await TestSlot.deploy();
        await testSlot.deployed();
        // first contracts
        deepRole1 = await DeepRole1.deploy();
        await deepRole1.deployed();

        deepRole2 = await DeepRole2.deploy();
        await deepRole2.deployed();

        proxiable = await Proxiable.deploy();
        await proxiable.deployed();
        // find the binary code of initialize() function

        componentCore = await ComponentCore.deploy();
        compartment = await Compartment.deploy();
        await componentCore.deployed();
        await compartment.deployed();

        const contractData = await Web3.utils.sha3('initialize()').substring(0, 10);
        console.log(contractData);
        //then the proxied base role
        baseRole = await BaseRole.deploy(contractData, deepRole1.address);
        await baseRole.deployed();
    });

    it("Interacts with contract or child contract in DeepRole", async function () {
        // const ownerAddress = await ethers.getSigner(owner.address);
        await deepRole1.initialize();
        await deepRole1.connect(owner).increment();
        await deepRole1.connect(owner).updateCode(deepRole2.address);
        await componentCore.activateCompartment(compartment.address); // assign an address to an active compartment
        let activatedCompartmentAddress = await componentCore.getActiveCompartment();
        console.log("activation of the compartment:" + activatedCompartmentAddress);
        let deactivatedCompartmentAddress = await componentCore.connect(owner).deactivateCompartment();
        const deepRoleHash1 = utils.keccak256(utils.toUtf8Bytes("SAMPLE ROLE1"));
        const deepRoleHash2 = utils.keccak256(utils.toUtf8Bytes("SAMPLE ROLE2"));
        const baseRoleHash = utils.keccak256(utils.toUtf8Bytes("BASE ROLE"));
        await componentCore.addRole(deepRoleHash1, deepRole1.address);
        await componentCore.addRole(deepRoleHash2, baseRole.address);
        await componentCore.addRole(baseRoleHash, deepRole2.address);
        console.log("deepRoleFirst: " + await componentCore.getRole(deepRoleHash1));
        console.log("deactivation of the compartment:" + deactivatedCompartmentAddress);
        expect(activatedCompartmentAddress).not.to.equal(deactivatedCompartmentAddress);
        const functionSelector = await Web3.utils.sha3('increment()').substring(0, 10);
        console.log(functionSelector)
        const contractTx = {
            to: baseRole.address,
            // data: functionSelector, // can't be just "0x0"
            value: ethers.utils.parseEther('0.1'), // Send some ETH if the fallback function expects it
            gasLimit: 3000000, // Specify an appropriate gas limit
        };

        const txResponse = await owner.sendTransaction(contractTx);
        await txResponse.wait();
        console.log('Transaction Hash:', txResponse.hash);
        console.log('Transaction value:', txResponse.value); // 0 for failed, 1 for successful
        console.log('Transaction data:', txResponse.data);
        console.log('Block Number:', txResponse.blockNumber);
    });

    it("should get the address of the slot", async function() {
        const labelHash = utils.keccak256(utils.toUtf8Bytes("TEST_SLOT"));
        console.log("owner address: " + owner.address);
        await testSlot.writeSlot(owner.address);
        console.log(await testSlot.getSlot());
    });

});

