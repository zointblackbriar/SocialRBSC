const { expect } = require('chai');
const { ethers } = require('hardhat');

// smoke tests for the e-commerce customer service scenario.  The narrative
// involves a Shopping Assistant agent and a Primary User as primary actors
// (modeled as compartments).  Secondary human actors such as a Gift Recipient
// and supporting systems (product catalogs, reviews, etc.) are represented as
// roles in the framework.  The agent is responsible for conducting a multi-
// step shopping workflow: asking clarifying questions, researching products,
// selecting an item, executing the purchase, and scheduling delivery.  The
// user supplies initial requests and approvals.  The deliberation cycle is
// exercised below in both a basic form and a more detailed, stepwise fashion
// to reflect this interaction model.

describe('E-commerce Customer Service', function () {
    let society;
    let mediator;
    let socialAgent;
    let shoppingComp;
    let primaryUserComp;
    let giftRecipientComp;
    let humanOpRole;

    let socialAgentStateMachine;
    let socialAgentIntention;
    let socialAgentBelief;
    let deliberation;
    let ecommerceReasoner;

    beforeEach(async function () {
        [owner] = await ethers.getSigners();

        // deploy society and mediator components
        const Society = await ethers.getContractFactory('Society');
        society = await Society.deploy();
        await society.deployed();

        const ConcreteMediator = await ethers.getContractFactory('ConcreteMediator');
        mediator = await ConcreteMediator.deploy();
        await mediator.deployed();

        // create and register a social agent representing the shopping assistant
        const SocialAgent = await ethers.getContractFactory('SocialAgent');
        socialAgent = await SocialAgent.deploy();
        await socialAgent.deployed();
        await socialAgent.createAgent('ShoppingAssistant1');
        await socialAgent.socialAgentBindToSociety(society.address, mediator.address, 'ShoppingAssistant1');

        // deploy primary actor compartments
        const ShopComp = await ethers.getContractFactory('ShoppingAssistantCompartment');
        shoppingComp = await ShopComp.deploy();
        await shoppingComp.deployed();

        const UserComp = await ethers.getContractFactory('PrimaryUserCompartment');
        primaryUserComp = await UserComp.deploy();
        await primaryUserComp.deployed();

        const GiftComp = await ethers.getContractFactory('GiftRecipientCompartment');
        giftRecipientComp = await GiftComp.deploy();
        await giftRecipientComp.deployed();

        // deploy a sample role to show role contract presence
        const HumanOp = await ethers.getContractFactory('HumanOperatorRole');
        humanOpRole = await HumanOp.deploy();
        await humanOpRole.deployed();

        // bdi engine pieces for deliberation
        const SAMachine = await ethers.getContractFactory('SocialAgentStateMachine');
        socialAgentStateMachine = await SAMachine.deploy();
        await socialAgentStateMachine.deployed();

        const SAIntention = await ethers.getContractFactory('SocialAgentIntention');
        socialAgentIntention = await SAIntention.deploy(owner.address, ethers.constants.AddressZero);
        await socialAgentIntention.deployed();

        const SABelief = await ethers.getContractFactory('SocialAgentBelief');
        socialAgentBelief = await SABelief.deploy();
        await socialAgentBelief.deployed();

        const SACycle = await ethers.getContractFactory('SocialAgentDeliberationCycle');
        deliberation = await SACycle.deploy(
            socialAgentStateMachine.address,
            society.address,
            socialAgent.address,
            socialAgentIntention.address,
            socialAgentBelief.address
        );
        await deliberation.deployed();

        // helper combining goal tree and deliberation
        const Reasoner = await ethers.getContractFactory('EcommerceReasoner');
        ecommerceReasoner = await Reasoner.deploy(deliberation.address);
        await ecommerceReasoner.deployed();
    });

    it('can deploy compartments and roles', async function () {
        const expectedShopId = ethers.utils.keccak256(ethers.utils.toUtf8Bytes('ShoppingAssistantAgent'));
        expect(await shoppingComp.getCompartmentID()).to.equal(expectedShopId);
        const expectedUserId = ethers.utils.keccak256(ethers.utils.toUtf8Bytes('PrimaryUser'));
        expect(await primaryUserComp.getCompartmentID()).to.equal(expectedUserId);
        const expectedGiftId = ethers.utils.keccak256(ethers.utils.toUtf8Bytes('GiftRecipient'));
        expect(await giftRecipientComp.getCompartmentID()).to.equal(expectedGiftId);

        // role setters should not revert
        await humanOpRole.setCore(ethers.constants.AddressZero);
        await humanOpRole.setCompartment(ethers.constants.AddressZero);
    });

    it('should run a basic deliberation cycle as the shopping assistant', async function () {
        // set a dummy intention and belief
        await deliberation.setIntentionsBeforeDeliberationCycle(owner.address, 'Shop');
        await deliberation.setBeliefsBeforeDeliberationCycle('HasPreference', 1);
        await deliberation.setSocialAgentNameForIntentions('ShoppingAssistant1');

        await deliberation.deliberationCycle(
            'ShoppingAssistant1',
            'EcommerceSociety',
            owner.address,
            0,
            'Shop',
            'Meta',
            'HasPreference'
        );
        expect(await deliberation.deliberationCycleResult()).to.equal(true);
    });

    it('should run a detailed deliberation cycle representing a gift-shopping workflow', async function () {
        // define a sequence of intentions that mirror the user story
        const steps = [
            'ClarifyNeed',
            'ResearchProducts',
            'SelectProduct',
            'Purchase',
            'ScheduleDelivery'
        ];
        const beliefs = [
            'NeedUndefined',
            'HasNeed',
            'ProductsFound',
            'SelectionMade',
            'PaymentProcessed'
        ];

        await deliberation.setSocialAgentNameForIntentions('ShoppingAssistant1');

        // iterate through the workflow, driving the deliberation cycle each time
        for (let i = 0; i < steps.length; i++) {
            const step = steps[i];
            const belief = beliefs[i];

            // push the next intention and belief
            await deliberation.setIntentionsBeforeDeliberationCycle(owner.address, step);
            await deliberation.setBeliefsBeforeDeliberationCycle(belief, 1);

            // execute a cycle, verifying that it completes successfully
            await deliberation.deliberationCycle(
                'ShoppingAssistant1',
                'EcommerceSociety',
                owner.address,
                i,
                step,
                'Meta',
                belief
            );
            expect(await deliberation.deliberationCycleResult()).to.equal(true);

            // confirm the intention stack contains the expected value at this index
            const actualIntention = await deliberation.getAgentIntentionAtIndex(owner.address, i);
            expect(actualIntention).to.equal(step);
        }

        // after the workflow all intentions should be present on the stack
        const stackSize = await socialAgentIntention.getIntentionStackSize(owner.address);
        expect(stackSize).to.equal(steps.length);
    });

    it('brand role can activate and deactivate a compartment via core', async function () {
        // deploy component core and role contracts
        const Core = await ethers.getContractFactory('ComponentCore');
        const core = await Core.deploy();
        await core.deployed();

        const Brand = await ethers.getContractFactory('BrandRole');
        const brand = await Brand.deploy();
        await brand.deployed();

        // set the core address on the role
        await brand.setCore(core.address);

        // activate using one of the compartments already deployed
        await brand.activateCompartmentContract(shoppingComp.address);
        expect(await core.getActiveCompartment()).to.equal(shoppingComp.address);

        // deactivate and verify
        await brand.deactivateCompartmentContract();
        expect(await core.getActiveCompartment()).to.equal(ethers.constants.AddressZero);

        // ensure hasBeenPlayedRuntime proxies to core/player (we can call with dummy)
        // deploy a simple Player contract or reuse existing sample
        const Player = await ethers.getContractFactory('Player');
        // Player constructor expects an address for ownership; since the core contract
        // will be the msg.sender when hasBeenPlayedRuntime ultimately calls playDelegateCallRoleContract,
        // we deploy the player with the core address as the owner.
        const player = await Player.deploy(core.address);
        await player.deployed();

        // the Player contract has no 'dummy' function, so the delegated call will revert
        // from playDelegateCallRoleContract; we just want to make sure the proxy chain
        // is executed, so assert that the transaction indeed reverts with the expected reason.
        await expect(
            brand.hasBeenPlayedRuntime(player.address, 'dummy')
        ).to.be.revertedWith('Function call has been reverted');
    });

    describe('Goal tree and deliberation integration', function () {
        it('should record shopping goals and run a cycle via reasoner', async function () {
            // add goals representing the high‑level workflow
            await ecommerceReasoner.addShoppingGoal('Browse', '');
            await ecommerceReasoner.addShoppingGoal('Checkout', 'Browse');
            // verify tree stored child correctly
            const children = await ecommerceReasoner.tree().then(addr => ethers.getContractAt('GoalPlanTree', addr)).then(tree => tree.getChildren('Browse'));
            expect(children.length).to.equal(1);

            // run deliberation cycle through wrapper
            const result = await ecommerceReasoner.callStatic.runDeliberation(
                'ShoppingAssistant1',
                'EcommerceSociety',
                owner.address,
                0,
                'Browse',
                'Meta',
                'HasPreference'
            );
            expect(result).to.equal(true);
        });
    });

});// closing outer describe
