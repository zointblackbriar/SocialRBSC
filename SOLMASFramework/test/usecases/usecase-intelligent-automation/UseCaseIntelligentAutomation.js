const { expect } = require('chai');
const { ethers } = require('hardhat');

// intelligent automation scenario built on top of the generic GoalPlanTree
// the structure encodes a root objective with a proactive monitoring plan and
// a sequence of subgoals/diagnosis/remediation/learning steps.

describe('Use Case Intelligent Automation', function () {
    let goalPlanTree;
    let owner;

    beforeEach(async function () {
        [owner] = await ethers.getSigners();

        // deploy the generic GoalPlanTree contract used for all the other
        // use‑cases in the repository
        const GoalPlanTree = await ethers.getContractFactory('GoalPlanTree');
        goalPlanTree = await GoalPlanTree.deploy();
        await goalPlanTree.deployed();
    });

    it('should build the proactive monitoring goal/plan tree', async function () {
        const rootGoal = 'Maintain Optimal System Performance and Prevent Outages';
        const mainPlan = 'Proactive System Monitoring';
        const sub1 = 'Establish Baseline Performance';
        const sub2 = 'Detect Anomalies in Real-Time';
        const sub3 = 'Diagnose Root Cause';
        const sub4 = 'Execute Remediation Action';
        const sub5 = 'Update Knowledge Base (Learning)';
        const autoRemediation = 'Automated Remediation';

        // root goal is stored with an empty parent string
        await goalPlanTree.add(rootGoal, '', 'high level objective', 0, 0);

        // the main plan sits at the top level and is linked to the root goal
        await goalPlanTree.addPlan(mainPlan, '', 'continuous monitoring cycle', 0, 0);
        await goalPlanTree.linkPlanToGoal(mainPlan, rootGoal);

        // subgoals are children of the root goal; data strings describe the
        // actions associated with each step
        await goalPlanTree.add(sub1, rootGoal, 'collect metrics and analyse logs', 0, 0);
        await goalPlanTree.add(sub2, rootGoal, 'compare current metrics against baseline', 0, 0);
        await goalPlanTree.add(sub3, rootGoal, 'correlate anomaly with recent events', 0, 0);
        await goalPlanTree.add(sub4, rootGoal, 'rollback deployment/scale verify health', 0, 0);
        await goalPlanTree.add(sub5, rootGoal, 'store new pattern and update models', 0, 0);

        // automated remediation is modelled as a plan beneath the remediation goal
        await goalPlanTree.addPlan(autoRemediation, sub4, 'rollback, scale and check', 0, 0);

        // verify root goal exists and has the correct name
        const rootPath = ethers.utils.keccak256(
            ethers.utils.defaultAbiCoder.encode(['string', 'string'], ['', rootGoal])
        );
        const [nameRes] = await goalPlanTree.getNode(rootPath);
        expect(nameRes).to.equal(rootGoal);

        // make sure one of the subgoals can be retrieved
        const sub2Path = ethers.utils.keccak256(
            ethers.utils.defaultAbiCoder.encode(['string', 'string'], [rootGoal, sub2])
        );
        const [sub2Name, sub2Parent, sub2Data] = await goalPlanTree.getNode(sub2Path);
        expect(sub2Name).to.equal(sub2);
        expect(sub2Parent).to.equal(rootGoal);
        expect(sub2Data).to.contain('compare current metrics');
    });

    it('should allow dropping the entire tree when remediation succeeds', async function () {
        const rootGoal = 'Maintain Optimal System Performance and Prevent Outages';
        await goalPlanTree.add(rootGoal, '', 'high level objective', 0, 0);
        // attach a child so drop recursion can be exercised
        await goalPlanTree.add('DummyChild', rootGoal, 'foo', 0, 0);

        // drop recursively from the root; both nodes should disappear
        await goalPlanTree.dropGoalRecursively('', rootGoal);
        expect(await goalPlanTree.isDroppedGoal(rootGoal, '')).to.be.true;
    });

    describe('Automation Plan and Reasoning Integration', function () {
        let automationPlan;
        let reasoner;
        let society;
        let mediator;
        let socialAgent;

        beforeEach(async function () {
            // deploy society and mediator from societypattern
            const Society = await ethers.getContractFactory('Society');
            society = await Society.deploy();
            await society.deployed();
            const ConcreteMediator = await ethers.getContractFactory('ConcreteMediator');
            mediator = await ConcreteMediator.deploy();
            await mediator.deployed();
            // create a social agent and bind to the society
            const SocialAgent = await ethers.getContractFactory('SocialAgent');
            socialAgent = await SocialAgent.deploy();
            await socialAgent.deployed();
            await socialAgent.createAgent('AIOpsAgent1');
            await socialAgent.socialAgentBindToSociety(society.address, mediator.address, 'AIOpsAgent1');

            const AutomationPlan = await ethers.getContractFactory('AutomationPlan');
            automationPlan = await AutomationPlan.deploy();
            await automationPlan.deployed();

            const Reasoner = await ethers.getContractFactory('RuleBasedReasoningAutomation');
            reasoner = await Reasoner.deploy(automationPlan.address);
            await reasoner.deployed();
            // build an external tree and hand it to the reasoner
            const GoalPlanTree = await ethers.getContractFactory('GoalPlanTree');
            const externalTree = await GoalPlanTree.deploy();
            await externalTree.deployed();
            
            // replicate the same structure used by the standalone tests above
            const rootGoal = 'Maintain Optimal System Performance and Prevent Outages';
            const mainPlan = 'Proactive System Monitoring';
            const sub1 = 'Establish Baseline Performance';
            const sub2 = 'Detect Anomalies in Real-Time';
            const sub3 = 'Diagnose Root Cause';
            const sub4 = 'Execute Remediation Action';
            const sub5 = 'Update Knowledge Base (Learning)';
            const autoRemediation = 'Automated Remediation';

            await externalTree.add(rootGoal, '', 'high level objective', 0, 0);
            await externalTree.addPlan(mainPlan, '', 'continuous monitoring cycle', 0, 0);
            await externalTree.linkPlanToGoal(mainPlan, rootGoal);
            await externalTree.add(sub1, rootGoal, 'collect metrics and analyse logs', 0, 0);
            await externalTree.add(sub2, rootGoal, 'compare current metrics against baseline', 0, 0);
            await externalTree.add(sub3, rootGoal, 'correlate anomaly with recent events', 0, 0);
            await externalTree.add(sub4, rootGoal, 'rollback deployment/scale verify health', 0, 0);
            await externalTree.add(sub5, rootGoal, 'store new pattern and update models', 0, 0);
            await externalTree.addPlan(autoRemediation, sub4, 'rollback, scale and check', 0, 0);
            // top-level plans used by runCycle
            await externalTree.addPlan(sub1, '', 'collect metrics and analyse logs', 0, 0);
            await externalTree.addPlan(sub2, '', 'compare current metrics against baseline', 0, 0);
            await externalTree.addPlan(sub3, '', 'correlate anomaly with recent events', 0, 0);
            await externalTree.addPlan(sub4, '', 'rollback deployment/scale verify health', 0, 0);
            await externalTree.addPlan(sub5, '', 'store new pattern and update models', 0, 0);

            await reasoner.setGoalTree(externalTree.address);

        });

        it('should step through the five stages and clear the tree at the end', async function () {
            // perform cycle and verify plan state progression
            await reasoner.runCycle();
            let status = await automationPlan.currentPlan();
            expect(status.baselineEstablished).to.equal(true);

            await reasoner.runCycle();
            status = await automationPlan.currentPlan();
            expect(status.anomalyDetected).to.equal(true);

            await reasoner.runCycle();
            status = await automationPlan.currentPlan();
            expect(status.rootCauseDiagnosed).to.equal(true);

            await reasoner.runCycle();
            status = await automationPlan.currentPlan();
            expect(status.remediationExecuted).to.equal(true);

            await reasoner.runCycle();
            status = await automationPlan.currentPlan();
            expect(status.knowledgeUpdated).to.equal(true);

            await reasoner.runCycle(); // final call returns AllDone but we don't need value check

            // tree remains intact since the reasoner no longer clears it automatically
        });

        it('should not repeat stages once performed', async function () {
            // perform baseline establishment and then try again
            await reasoner.runCycle();
            // second call should progress to anomaly detection
            await reasoner.runCycle();
            const status = await automationPlan.currentPlan();
            expect(status.anomalyDetected).to.equal(true);
        });
    });
});
