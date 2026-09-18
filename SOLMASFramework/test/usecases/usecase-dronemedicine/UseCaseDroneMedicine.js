const {expect} = require('chai');
const {ethers} = require('hardhat');

// simulate a simple delivery scenario using the plan/agent structure from SOLMAS

describe("Drone Medicine Delivery Scenario", function () {
    let plan;
    let drone;
    let pharmacy;
    let patient;
    let reasoning;
    let goalPlanTree;
    let owner;

    beforeEach(async function () {
        [owner] = await ethers.getSigners();

        // deploy the core plan
        const MedicinePlan = await ethers.getContractFactory("MedicinePlan");
        plan = await MedicinePlan.deploy();
        await plan.deployed();

        // deploy the specialist contracts
        const Drone = await ethers.getContractFactory("Drone");
        drone = await Drone.deploy(plan.address, owner.address);
        await drone.deployed();

        const Pharmacy = await ethers.getContractFactory("Pharmacy");
        pharmacy = await Pharmacy.deploy(plan.address, owner.address);
        await pharmacy.deployed();

        const Patient = await ethers.getContractFactory("Patient");
        patient = await Patient.deploy(plan.address, pharmacy.address, owner.address);
        await patient.deployed();

        const RuleBasedReasoningMedicine = await ethers.getContractFactory("RuleBasedReasoningMedicine");
        reasoning = await RuleBasedReasoningMedicine.deploy(
            plan.address,
            drone.address,
            pharmacy.address,
            patient.address
        );
        await reasoning.deployed();

        const GoalPlanTree = await ethers.getContractFactory("GoalPlanTree");
        goalPlanTree = await GoalPlanTree.deploy();
        await goalPlanTree.deployed();

        // verify reasoning contract created its own tree inside constructor
        const reasoningTreeAddress = await reasoning.goalTree();
        const reasoningTree = GoalPlanTree.attach(reasoningTreeAddress);
        const rootPath = ethers.utils.keccak256(
            ethers.utils.defaultAbiCoder.encode(["string","string"],["","DeliverMedicine"])
        );
        const [nameRoot] = await reasoningTree.getNode(rootPath);
        expect(nameRoot).to.equal("DeliverMedicine");
    });

    it("should perform the manual delivery-verification-notification flow", async function () {
        // check compartment identifiers are assigned
        const zeroHash = ethers.constants.HashZero;
        expect(await drone.getMyCompartmentID()).to.not.equal(zeroHash);
        expect(await pharmacy.getMyCompartmentID()).to.not.equal(zeroHash);
        expect(await patient.getMyCompartmentID()).to.not.equal(zeroHash);

        // initially nothing done
        let status = await plan.currentPlan();
        expect(status.medicineDelivered).to.equal(false);
        expect(status.medicineVerified).to.equal(false);
        expect(status.patientNotified).to.equal(false);

        // deliver and check reached flag
        expect(await drone.checkReached()).to.equal(false);
        await drone.deliverMedicine();
        expect(await drone.checkReached()).to.equal(true);
        status = await plan.currentPlan();
        expect(status.medicineDelivered).to.equal(true);

        await pharmacy.verifyMedicine();
        status = await plan.currentPlan();
        expect(status.medicineVerified).to.equal(true);

        await patient.confirmReceipt();
        status = await plan.currentPlan();
        expect(status.patientNotified).to.equal(true);
    });

    it("should let the reasoner choose the next action sequentially", async function () {
        const GoalPlanTree = await ethers.getContractFactory("GoalPlanTree");
        const reasoningTree = GoalPlanTree.attach(await reasoning.goalTree());

        // decision should deliver first
        await reasoning.decideNextAction();
        let status = await plan.currentPlan();
        expect(status.medicineDelivered).to.equal(true);
        // verify that the plan node still exists
        let deliverPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string","string"],["","DeliverMedicine"]));
        let [nameD] = await reasoningTree.getNode(deliverPath);
        expect(nameD).to.equal("DeliverMedicine");

        // next decision should verify
        await reasoning.decideNextAction();
        status = await plan.currentPlan();
        expect(status.medicineVerified).to.equal(true);
        // check verify plan (child of DeliverMedicine)
        let verifyPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string","string"],["DeliverMedicine","VerifyMedicine"]));
        let [nameV] = await reasoningTree.getNode(verifyPath);
        expect(nameV).to.equal("VerifyMedicine");
        
        // then notify patient
        await reasoning.decideNextAction();
        status = await plan.currentPlan();
        expect(status.patientNotified).to.equal(true);
        let notifyPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string","string"],["VerifyMedicine","NotifyPatient"]));
        let [nameN] = await reasoningTree.getNode(notifyPath);
        expect(nameN).to.equal("NotifyPatient");

        // further calls signal completion and tree cleared
        await reasoning.decideNextAction();
        expect(await reasoningTree.isDroppedGoal("DeliverMedicine","" )).to.be.true;
    });

    it("player utility should reject invalid delegate call", async function () {
        await expect(drone.playRoleContract(drone.address, "nonexistent")).to.be.reverted;
    });

    it("should be able to build a simple goal plan tree representing the delivery plan", async function () {
        // add some goals and plans, using the same pattern as other tests
        await goalPlanTree.add("DeliverMedicine", "", "deliver to patient", 0, 0);
        await goalPlanTree.addPlan("VerifyMedicine", "DeliverMedicine", "pharmacy checks", 0, 0);
        await goalPlanTree.addPlan("NotifyPatient", "VerifyMedicine", "patient acknowledgement", 0, 0);

        // retrieve the node for DeliverMedicine; children array is not populated by add, it's stored
        // under a separate key in the current implementation so we only verify the node exists.
        const goalPath = ethers.utils.keccak256(
            ethers.utils.defaultAbiCoder.encode(["string", "string"], ["", "DeliverMedicine"])
        );
        const [nameRes, parentRes, dataRes, avoidanceResp, willingnessResp, children] = await goalPlanTree.getNode(goalPath);
        expect(nameRes).to.equal("DeliverMedicine");
        // according to the existing CaseStudy test, the returned child list is zero-length
        // after recent updates the root goal includes its immediate child
        // plans in the node struct; expect a single entry
        expect(children.length).to.equal(1);
    });

    describe("Role Contracts and Social Agent Functions", function () {
        let ConcreteMediator;
        let mediator;
        let droneRole;
        let pharmacyRole;
        let patientRole;

        beforeEach(async function () {
            ConcreteMediator = await ethers.getContractFactory("ConcreteMediator");
            mediator = await ConcreteMediator.deploy();
            await mediator.deployed();

            const DroneRole = await ethers.getContractFactory("DroneRole");
            droneRole = await DroneRole.deploy(ethers.constants.AddressZero, owner.address);
            await droneRole.deployed();

            const PharmacyRole = await ethers.getContractFactory("PharmacyRole");
            pharmacyRole = await PharmacyRole.deploy(ethers.constants.AddressZero, owner.address);
            await pharmacyRole.deployed();

            const PatientRole = await ethers.getContractFactory("PatientRole");
            patientRole = await PatientRole.deploy(ethers.constants.AddressZero, ethers.constants.AddressZero, owner.address);
            await patientRole.deployed();
        });

        it("should set and get social agent name", async function () {
            await droneRole.createAgent("Drone1");
            expect(await droneRole.getSocialAgentName()).to.equal("Drone1");

            await pharmacyRole.createAgent("Pharm1");
            expect(await pharmacyRole.getSocialAgentName()).to.equal("Pharm1");

            await patientRole.createAgent("Patient1");
            expect(await patientRole.getSocialAgentName()).to.equal("Patient1");
        });

        it("should bind and unbind agents to society via mediator", async function () {
            await droneRole.socialAgentBindToSociety(owner.address, mediator.address, "Drone1");
            expect(await mediator.getAgentFromSociety("Drone1")).to.equal(owner.address);

            await droneRole.socialAgentUnbind("Drone1", mediator.address);
            await expect(mediator.getAgentFromSociety("Drone1")).to.be.reverted;
        });
    });
});
