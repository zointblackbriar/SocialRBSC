const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("LinkedList — basic operations", function () {
  let linkedList;

  beforeEach(async function () {
    const LinkedList = await ethers.getContractFactory("LinkedList");
    linkedList = await LinkedList.deploy();
    await linkedList.deployed();
  });

  it("inserts nodes and returns data in order", async function () {
    await linkedList.insert(10);
    await linkedList.insert(20);

    const head = await linkedList.head();
    expect(head).to.not.equal(ethers.constants.AddressZero);

    // getData is a non-view function (it mutates stateVariables), so use callStatic to obtain the returned array
    const data = await linkedList.callStatic.getData();
    expect(data.length).to.equal(2);
    expect(data[0].toNumber()).to.equal(10);
    expect(data[1].toNumber()).to.equal(20);

    // ensure nodes mapping contains the second node
    const headNode = await linkedList.nodes(head);
    const secondAddr = headNode.next;
    expect(secondAddr).to.not.equal(ethers.constants.AddressZero);

    // delete second node
    await linkedList.deleteNode(secondAddr);

    // after deletion, use callStatic.getData to read current list (callStatic doesn't mutate state)
    const dataAfter = await linkedList.callStatic.getData();
    expect(dataAfter.length).to.equal(1);
    expect(dataAfter[0].toNumber()).to.equal(10);

    // the deleted node's stored data should be zero
    const deletedNode = await linkedList.nodes(secondAddr);
    expect(deletedNode.data.toNumber()).to.equal(0);
  });

  it("reverts when attempting to delete head or non-existent node", async function () {
    await linkedList.insert(1);

    const head = await linkedList.head();

    // deleting head should revert with specific message
    await expect(linkedList.deleteNode(head)).to.be.revertedWith("Cannot delete the head node");

    // deleting a random address should revert with non-existent message
    const randomAddr = ethers.Wallet.createRandom().address;
    await expect(linkedList.deleteNode(randomAddr)).to.be.revertedWith("Cannot delete a non-existent node");
  });
});
