const { expect } = require('chai');
const {ethers} = require('hardhat');


describe('', function () {
    let LinkedList;
    let linkedList;
    let owner;

    beforeEach(async function() {
        [owner] = await ethers.getSigners();
        LinkedList = await ethers.getContractFactory("LinkedList");
        linkedList = await LinkedList.deploy();
        await linkedList.deployed();
    });

    it("should insert and retrieve data from the linked list", async function () {
        // const LinkedList = await ethers.getContractFactory('LinkedList');
        // const linkedList = await LinkedList.deploy();
        // await linkedList.deployed();

        await linkedList.insert(1);
        await linkedList.insert(2);
        await linkedList.insert(3);
        await linkedList.getData();

        expect(await linkedList.stateVariables(0)).to.equal(1);
        expect(await linkedList.stateVariables(1)).to.equal(2);
        expect(await linkedList.stateVariables(2)).to.equal(3);

    });

    it("should delete the node properly and get data", async function() {
        await linkedList.insert(1);
        await linkedList.insert(2);
        await linkedList.insert(3);

        await linkedList.getData();
        // First test
        expect(await linkedList.stateVariables(0)).to.equal(1);
        expect(await linkedList.stateVariables(1)).to.equal(2);
        expect(await linkedList.stateVariables(2)).to.equal(3);

        const headNode = await linkedList.head();
        console.log("address of the headNode: ", headNode); // the address of the Head Node
        const firstNode = await linkedList.nodes(headNode);
        console.log("address of the firstNode: ", firstNode.next);
        await linkedList.deleteNode(firstNode.next);

        // Check the last value
        // Something wrong with this test
        await linkedList.getData();
        expect(await linkedList.stateVariables(0)).to.equal(1);
        expect(await linkedList.stateVariables(1)).to.equal(2);
        expect(await linkedList.stateVariables(2)).to.equal(3);

    });

    it("Should fail to delete the head node", async function() {
        await linkedList.insert(1);
        await linkedList.insert(2);
        await linkedList.insert(3);

        // We expect an exception message
        await expect(linkedList.deleteNode(await linkedList.head())).to.be.revertedWith("Cannot delete the head node");
    });

    // test with fake address to delete
    it("Should fail to delete a non-existent node", async function() {
        await linkedList.insert(1);
        await linkedList.insert(2);
        await linkedList.insert(3);

        // insert a fake address
        const fakeAddress = ethers.utils.getAddress(ethers.utils.hexlify(ethers.utils.randomBytes(20)));
        await expect(linkedList.deleteNode(fakeAddress)).to.be.revertedWith("Cannot delete a non-existent node");
    });
});