const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("GoalPlanTree — add & getNode (happy path)", function () {
  let goalPlanTree;

  beforeEach(async function () {
    const GoalPlanTree = await ethers.getContractFactory("GoalPlanTree");
    goalPlanTree = await GoalPlanTree.deploy();
    await goalPlanTree.deployed();
  });

  it("adds a goal and getNode returns correct fields", async function () {
    const name = "Goal A";
    const parent = ""; // root
    const data = "my-data";
    const avoidance = 7;
    const willingness = 12;

    // add a goal
    await goalPlanTree.add(name, parent, data, avoidance, willingness);

    // compute path key same as contract: keccak256(abi.encode(parent, name))
    const path = ethers.utils.keccak256(
      ethers.utils.defaultAbiCoder.encode(["string", "string"], [parent, name])
    );

    // fetch node via getNode
    const node = await goalPlanTree.getNode(path);

    const resName = node[0];
    const resParent = node[1];
    const resData = node[2];
    const avoidanceListAddress = node[3];
    const willingnessListAddress = node[4];
    const childNodes = node[5];

    expect(resName).to.equal(name);
    expect(resParent).to.equal(parent);
    expect(resData).to.equal(data);

    // linked-list addresses should not be zero
    expect(avoidanceListAddress).to.not.equal(ethers.constants.AddressZero);
    expect(willingnessListAddress).to.not.equal(ethers.constants.AddressZero);

    // childNodes should be empty for a new leaf
    expect(childNodes.length).to.equal(0);
  });
});

