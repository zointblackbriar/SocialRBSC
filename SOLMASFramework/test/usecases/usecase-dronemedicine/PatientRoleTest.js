const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("PatientRole", function () {
  let MedicinePlan, PatientRole;
  let plan, patient;
  let owner, other;

  beforeEach(async function () {
    [owner, other] = await ethers.getSigners();

    MedicinePlan = await ethers.getContractFactory("MedicinePlan");
    plan = await MedicinePlan.connect(owner).deploy();
    await plan.deployed();

    PatientRole = await ethers.getContractFactory("PatientRole");
    patient = await PatientRole.connect(owner).deploy(plan.address, other.address, owner.address);
    await patient.deployed();

    // deploy society and mediator to test binding
    const Society = await ethers.getContractFactory("Society");
    society = await Society.deploy();
    await society.deployed();

    const ConcreteMediator = await ethers.getContractFactory("ConcreteMediator");
    mediator = await ConcreteMediator.deploy();
    await mediator.deployed();
  });

  it("starts with an empty plan and correct pharmacy address", async function () {
    const status = await plan.currentPlan();
    expect(status.medicineDelivered).to.be.false;
    expect(status.medicineVerified).to.be.false;
    expect(status.patientNotified).to.be.false;
    expect(await patient.pharmacyAddress()).to.equal(other.address);
  });

  it("reverts confirmReceipt when not verified", async function () {
    await expect(patient.confirmReceipt()).to.be.revertedWith("Medicine must be verified by pharmacy first");
  });

  it("reverts confirmReceipt when already notified", async function () {
    // simulate full path: deliver + verify + first confirm
    await plan.markDelivered();
    await plan.markVerified();
    await patient.confirmReceipt();
    await expect(patient.confirmReceipt()).to.be.revertedWith("Patient has already confirmed");
  });

  it("allows confirming after verification and updates plan", async function () {
    await plan.markDelivered();
    await plan.markVerified();

    await patient.confirmReceipt();

    const status = await plan.currentPlan();
    expect(status.patientNotified).to.be.true;
  });

  it("inherits naming from SocialAgent", async function () {
    await patient.setName("Patty");
    expect(await patient.getSocialAgentName()).to.equal("Patty");
  });

  it("inherits Society functionality", async function () {
    await patient.setSocietyName("HealthSociety");
    expect(await patient.getSocietyName()).to.equal("HealthSociety");

    await patient.setSocietyID(42);
    expect(await patient.societyID()).to.equal(42);
  });

  it("can bind and unbind to a society via mediator", async function () {
    const name = "PatientAlice";
    await patient.socialAgentBindToSociety(society.address, mediator.address, name);
    expect(await mediator.assignedAgentsToSociety(name)).to.equal(society.address);
    expect(await mediator.getAgentFromSociety(name)).to.equal(society.address);

    await patient.socialAgentUnbind(name, mediator.address);
    await expect(mediator.getAgentFromSociety(name)).to.be.revertedWith("Agent not found in the society");
  });
});
