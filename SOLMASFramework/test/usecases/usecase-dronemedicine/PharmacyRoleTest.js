const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("PharmacyRole", function () {
  let MedicinePlan, PharmacyRole;
  let plan, pharmacy;
  let owner, other;

  beforeEach(async function () {
    [owner, other] = await ethers.getSigners();

    MedicinePlan = await ethers.getContractFactory("MedicinePlan");
    plan = await MedicinePlan.connect(owner).deploy();
    await plan.deployed();

    PharmacyRole = await ethers.getContractFactory("PharmacyRole");
    pharmacy = await PharmacyRole.connect(owner).deploy(plan.address, owner.address);
    await pharmacy.deployed();

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

  it("reverts verifyMedicine when nothing has been delivered", async function () {
    await expect(pharmacy.verifyMedicine()).to.be.revertedWith("Medicine has not been delivered yet");
  });

  it("allows verification once delivery has been marked", async function () {
    await plan.markDelivered();

    await pharmacy.verifyMedicine();
  });

  it("cannot verify a second time", async function () {
    await plan.markDelivered();
    await pharmacy.verifyMedicine();
    await expect(pharmacy.verifyMedicine()).to.be.revertedWith("Medicine already verified");
  });

  it("inherits basic SocialAgent naming", async function () {
    await pharmacy.setName("Pharma");
    expect(await pharmacy.getSocialAgentName()).to.equal("Pharma");
  });

  it("inherits Society functionality", async function () {
    await pharmacy.setSocietyName("PharmSociety");
    expect(await pharmacy.getSocietyName()).to.equal("PharmSociety");
    await pharmacy.setSocietyID(99);
    expect(await pharmacy.societyID()).to.equal(99);
  });

  it("can bind and unbind to a society via mediator", async function () {
    const name = "PharmaAgent";
    await pharmacy.socialAgentBindToSociety(society.address, mediator.address, name);
    expect(await mediator.assignedAgentsToSociety(name)).to.equal(society.address);
    expect(await mediator.getAgentFromSociety(name)).to.equal(society.address);
    await pharmacy.socialAgentUnbind(name, mediator.address);
    await expect(mediator.getAgentFromSociety(name)).to.be.revertedWith("Agent not found in the society");
  });
});
