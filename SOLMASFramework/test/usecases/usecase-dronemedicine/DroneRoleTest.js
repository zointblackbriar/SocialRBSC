const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("DroneRole", function () {
  let MedicinePlan, DroneRole;
  let plan, drone;
  let owner, other;

  beforeEach(async function () {
    [owner, other] = await ethers.getSigners();

    MedicinePlan = await ethers.getContractFactory("MedicinePlan");
    plan = await MedicinePlan.connect(owner).deploy();
    await plan.deployed();

    DroneRole = await ethers.getContractFactory("DroneRole");
    drone = await DroneRole.connect(owner).deploy(plan.address, owner.address);
    await drone.deployed();

    const Society = await ethers.getContractFactory("Society");
    society = await Society.deploy();
    await society.deployed();
    const ConcreteMediator = await ethers.getContractFactory("ConcreteMediator");
    mediator = await ConcreteMediator.deploy();
    await mediator.deployed();
  });

  it("starts with an empty plan", async function () {
    const status = await plan.currentPlan();
    expect(status.medicineDelivered).to.be.false;
    expect(status.medicineVerified).to.be.false;
    expect(status.patientNotified).to.be.false;
  });

  it("reverts deliverMedicine when already delivered", async function () {
    // first delivery should succeed
    await drone.deliverMedicine();
    // second call fails
    await expect(drone.deliverMedicine()).to.be.revertedWith("Medicine already delivered");
  });

  it("updates plan when delivering medicine", async function () {
    await drone.deliverMedicine();
    const status = await plan.currentPlan();
    expect(status.medicineDelivered).to.be.true;
  });

  it("checkReached returns false before and true after delivery", async function () {
    expect(await drone.checkReached()).to.be.false;
    await drone.deliverMedicine();
    expect(await drone.checkReached()).to.be.true;
  });

  it("inherits naming from SocialAgent", async function () {
    await drone.setName("SkyBot");
    expect(await drone.getSocialAgentName()).to.equal("SkyBot");
  });

  it("inherits Society functionality", async function () {
    await drone.setSocietyName("DroneSociety");
    expect(await drone.getSocietyName()).to.equal("DroneSociety");
    await drone.setSocietyID(7);
    expect(await drone.societyID()).to.equal(7);
  });

  it("can bind and unbind to a society via mediator", async function () {
    const name = "DroneX";
    await drone.socialAgentBindToSociety(society.address, mediator.address, name);
    expect(await mediator.assignedAgentsToSociety(name)).to.equal(society.address);
    expect(await mediator.getAgentFromSociety(name)).to.equal(society.address);
    await drone.socialAgentUnbind(name, mediator.address);
    await expect(mediator.getAgentFromSociety(name)).to.be.revertedWith("Agent not found in the society");
  });

});

