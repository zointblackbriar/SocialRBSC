const {expect} = require('chai');
const {ethers} = require('hardhat');



describe("MealPreparation System", async function () {
    let plan;
    let mealPreparation;
    let owner;
    let saladMaking;
    let ruleBasedReasoning;
    let goalTreeAddress;
    let goalTree;
    let componentCore;

    // new agent instances
    let cook;
    let helperAgent; // "helper" is reserved in JS environment
    let customer;

    // BDI engine components
    let society;
    let socialAgent;
    let utils;
    let socialAgentStateMachine;
    let socialAgentDesire;
    let socialAgentIntention;
    let socialAgentBelief;
    let deliberation;
    let mealDeliberator;

    beforeEach(async function () {
        [owner] = await ethers.getSigners();

        // Deploy Plan contract
        const Plan = await ethers.getContractFactory("MealPlan");
        plan = await Plan.deploy();
        await plan.deployed();

        // Deploy MealPreparation contract with the address of Plan
        const MealPreparation = await ethers.getContractFactory("MealPreparation");
        mealPreparation = await MealPreparation.deploy(plan.address);
        await mealPreparation.deployed();

        // deploy the three social agents and bind them to the meal preparation instance
        const Cook = await ethers.getContractFactory("Cook");
        cook = await Cook.deploy(mealPreparation.address, "Chef");
        await cook.deployed();

        const Helper = await ethers.getContractFactory("Helper");
        helperAgent = await Helper.deploy(mealPreparation.address, "Assistant");
        await helperAgent.deployed();

        const Customer = await ethers.getContractFactory("Customer");
        customer = await Customer.deploy(mealPreparation.address, "Guest");
        await customer.deployed();

        // register agents in the meal preparation contract
        await mealPreparation.setAgents(cook.address, helperAgent.address, customer.address);

        const SaladMaking = await ethers.getContractFactory("SaladMaking");
        saladMaking = await SaladMaking.deploy(plan.address);
        await saladMaking.deployed();

        const SandwichMaking = await ethers.getContractFactory("SandwichMaking");
        sandwichMaking = await SandwichMaking.deploy(plan.address);
        await sandwichMaking.deployed();

        // deploy component core for role/compartment integration tests
        const ComponentCore = await ethers.getContractFactory("ComponentCore");
        componentCore = await ComponentCore.deploy();
        await componentCore.deployed();

        // Deploy RuleBasedReasiningMealPreparation contract
        const RuleBasedReasoningMealPreparation = await ethers.getContractFactory("RuleBasedReasoningMealPreparation");
        ruleBasedReasoning = await RuleBasedReasoningMealPreparation.deploy(plan.address, mealPreparation.address, saladMaking.address);
        await ruleBasedReasoning.deployed();

        // allow the reasoning contract to drive the meal preparation
        await mealPreparation.setRuleReasoner(ruleBasedReasoning.address);

        // grab the goal tree instance created internally
        goalTreeAddress = await ruleBasedReasoning.goalTree();
        const GoalPlanTree = await ethers.getContractFactory("GoalPlanTree");
        goalTree = GoalPlanTree.attach(goalTreeAddress);

        // ---------------------------------------------------------------------
        // Deploy lightweight BDI engine components and wire into reasoning contract
        const Society = await ethers.getContractFactory("Society");
        society = await Society.deploy();
        await society.deployed();

        const SocialAgent = await ethers.getContractFactory("SocialAgent");
        socialAgent = await SocialAgent.deploy();
        await socialAgent.deployed();

        const Utils = await ethers.getContractFactory("Utils");
        utils = await Utils.deploy();
        await utils.deployed();

        const SocialAgentStateMachine = await ethers.getContractFactory("SocialAgentStateMachine");
        socialAgentStateMachine = await SocialAgentStateMachine.deploy();
        await socialAgentStateMachine.deployed();

        const SocialAgentDesire = await ethers.getContractFactory("SocialAgentDesire");
        socialAgentDesire = await SocialAgentDesire.deploy();
        await socialAgentDesire.deployed();

        const SocialAgentIntention = await ethers.getContractFactory("SocialAgentIntention");
        socialAgentIntention = await SocialAgentIntention.deploy(socialAgentDesire.address, utils.address);
        await socialAgentIntention.deployed();

        const SocialAgentBelief = await ethers.getContractFactory("SocialAgentBelief");
        socialAgentBelief = await SocialAgentBelief.deploy();
        await socialAgentBelief.deployed();

        const SocialAgentDeliberationCycle = await ethers.getContractFactory("SocialAgentDeliberationCycle");
        deliberation = await SocialAgentDeliberationCycle.deploy(
            socialAgentStateMachine.address,
            society.address,
            socialAgent.address,
            socialAgentIntention.address,
            socialAgentBelief.address
        );
        await deliberation.deployed();

        // connect BDI modules to the rule‑based reasoning contract (optional)
        await ruleBasedReasoning.setBDIContracts(
            socialAgentStateMachine.address,
            socialAgentIntention.address,
            socialAgentDesire.address,
            deliberation.address
        );

        // deploy the new wrapper contract that will call deliberation cycle directly
        const MealPreparationDeliberator = await ethers.getContractFactory("MealPreparationDeliberator");
        mealDeliberator = await MealPreparationDeliberator.deploy(deliberation.address);
        await mealDeliberator.deployed();

    });

    describe("Initial State", function () {
        it("should initialize Plan with default values", async function () {
            console.log("Initial State")
            const currentPlan = await plan.currentPlan();

            // Destructure the tuple
            [isBoiled, isCooked, isServed, energyUsage, willingness,
                sandwichAssembled, diet, isSandwichServed, ingredientsInStock, saladAssembled] = currentPlan;

            expect(isBoiled).to.equal(false);
            expect(isCooked).to.equal(false);
            expect(isServed).to.equal(false);
            expect(energyUsage).to.equal(1); // OffPeak
            expect(willingness).to.equal(0); // High
        });
    });

    describe("Boil Water", function () {
        it("Should boil water if not in peak energy usage (cook) ", async function () {
            await cook.boilWater();
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.isBoiled).to.equal(true);
        });

        it("Should allow helper to boil water as well", async function () {
            await helperAgent.assistBoil();
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.isBoiled).to.equal(true);
        });

        it("Unauthorized account cannot boil water", async function () {
            const [, stranger] = await ethers.getSigners();
            await expect(mealPreparation.connect(stranger).boilWater()).to.be.revertedWith("unauthorized: not cook/helper");
        });

        it("Should not boil water during peak energy usage", async function () {
            await plan.setEnergyUsage(0); // Set to Peak

            await expect(cook.boilWater()).to.be.revertedWith("Avoid boiling water during peak electricity hours.");
        });

        it("Should not boil water if already boiled", async function () {
            await cook.boilWater();

            await expect(helperAgent.assistBoil()).to.be.revertedWith("Water is already boiled.");
        });

    });


    describe("Cook Pasta", function () {
        it("Should cook pasta when invoked by cook after boiling", async function () {
            await cook.boilWater();
            await cook.cookPasta();
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.isCooked).to.equal(true);
        });

        it("Helper may also cook pasta", async function () {
            await cook.boilWater();
            await helperAgent.assistCook();
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.isCooked).to.equal(true);
        });

        it("Unauthorized account cannot cook pasta", async function () {
            const [, stranger] = await ethers.getSigners();
            await cook.boilWater();
            await expect(mealPreparation.connect(stranger).cookPasta()).to.be.revertedWith("unauthorized: not cook/helper");
        });

        it("Should not cook pasta if water is not boiled", async function () {
            await expect(cook.cookPasta()).to.be.revertedWith("Water needs to be boiled first.");
        });

        it("Should not cook pasta during peak energy usage", async function () {
            await cook.boilWater();
            await plan.setEnergyUsage(0); // Set to Peak

            await expect(helperAgent.assistCook()).to.be.revertedWith("Avoid cooking during peak energy hours.");
        });

        it("Should not cook pasta if already cooked", async function () {
            await cook.boilWater();
            await cook.cookPasta();

            await expect(helperAgent.assistCook()).to.be.revertedWith("Pasta is already cooked.");
        });
    });

    describe("Reset Plan", function() {
        it("should reset the plan correctly", async function() {
            await cook.boilWater();
            await cook.cookPasta();
            await customer.serveMeal();

            await mealPreparation.resetMeal();

            const currentPlan = await plan.currentPlan();
            const [isBoiled, isCooked, isServed] = currentPlan;
            expect(isBoiled).to.equal(false);
            expect(isCooked).to.equal(false);
            expect(isServed).to.equal(false);
        });
    });

    describe("Set Conditions", function() {
        it("should set energy usage correctly", async function() {
            await plan.setEnergyUsage(0); // Peak
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.energyUsage).to.equal(0);
        });

        it("should set willingness correctly", async function() {
            await plan.setWillingness(1); // Low
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.willingness).to.equal(1);
        })
    });

    describe("Serve Pasta", function() {
        it("customer should serve pasta when conditions met", async function( ) {
            await cook.boilWater();
            await cook.cookPasta();

            await customer.serveMeal();
            // if we try to cook again. Avoidance condition is triggered.
            await expect(cook.cookPasta()).to.be.revertedWith("Pasta is already cooked.");

            const currentPlan = await plan.currentPlan();
            expect(currentPlan.isServed).to.equal(true);
        });

        it("unauthorized users cannot serve the meal", async function() {
            await cook.boilWater();
            await cook.cookPasta();
            const [, stranger] = await ethers.getSigners();
            await expect(mealPreparation.connect(stranger).servePasta()).to.be.revertedWith("unauthorized: not customer");
        });

        it("should not serve pasta if pasta is not cooked", async function() {
            await cook.boilWater();
            await expect(customer.serveMeal()).to.be.revertedWith("Pasta needs to be cooked first.");
        });

        it("should not serve pasta if already served", async function() {
            await cook.boilWater();
            await cook.cookPasta();
            await customer.serveMeal();

            await expect(customer.serveMeal()).to.be.revertedWith("Meal is already served.")
        });
    });


    describe("Salad Making", function () {
        it("should get ingredients for salad if they are in stock", async function () {
            await saladMaking.getIngredientsForSalad();
            const currentPlan = await plan.currentPlan();
            const [isBoiled, isCooked, isServed, energyUsage, willingness, sandwichAssembled, diet, isSandwichServed, ingredientsInStock, saladAssembled] = currentPlan;
            expect(saladAssembled).to.equal(true);
        });

        it("should reject getting ingredients when stock is missing", async function () {
            await plan.setIngredientsAvailability(false); // missing
            await expect(saladMaking.getIngredientsForSalad()).to.be.revertedWith("Some ingredients are missing or expired");
        });

        it("should only allow assembling salad when willingness high", async function () {
            await plan.setWillingness(1); // low
            await expect(saladMaking.assembleSalad()).to.be.revertedWith("The agent is not willing to make a healthy meal right now.");

            await plan.setWillingness(0); // high
            await saladMaking.assembleSalad();
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.saladAssembled).to.equal(true);
        });

        it("should not serve salad unless it is assembled and willingness high", async function () {
            // reset to start fresh
            await mealPreparation.resetMeal();
            await plan.setWillingness(0);
            await expect(saladMaking.serveSalad()).to.be.revertedWith("Salad is already served.");
        });
    });


    describe("Sandwich Making", function() {
        it("should allow obtaining sandwich ingredients when diet is regular", async function () {
            await plan.setDiet(0); // regular
            await sandwichMaking.getIngredients();
        });

        it("should reject sandwich ingredients on low-carb diet", async function () {
            await plan.setDiet(1); // low-carb
            await expect(sandwichMaking.getIngredients()).to.be.revertedWith("Avoid carbs due to dietary restrictions.");
        });

        it("should assemble sandwich when willingness high and diet regular", async function () {
            await plan.setDiet(0);
            await plan.setWillingness(0);
            await sandwichMaking.assembleSandwich();
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.sandwichAssembled).to.equal(true);
        });

        it("should not assemble sandwich if diet is low-carb or unwilling", async function () {
            await plan.setDiet(1);
            await plan.setWillingness(0);
            await expect(sandwichMaking.assembleSandwich()).to.be.revertedWith("Avoid carbs due to dietary restrictions.");

            await plan.setDiet(0);
            await plan.setWillingness(1);
            await expect(sandwichMaking.assembleSandwich()).to.be.revertedWith("the agent does not want a quick meal");
        });

        it("should serve sandwich only when willingness high and not already served", async function () {
            await plan.setWillingness(0);
            await plan.resetPlan();
            // serve when not assembled should just mark served
            await sandwichMaking.serveSandwich();
            const currentPlan = await plan.currentPlan();
            expect(currentPlan.isServed).to.equal(true);

            await expect(sandwichMaking.serveSandwich()).to.be.revertedWith("The sandwich is already served");
        });
    });

    describe("Role/Compartment Integration", function() {
        it("should allow a social agent to set its core and register a role", async function () {
            // cook starts without a core
            await cook.setCore(componentCore.address);
            const spec = ethers.utils.formatBytes32String("cookSpec");
            await cook.addRole(spec, cook.address);
            expect(await componentCore.isPlayingRole(spec)).to.equal(true);
        });

        it("should allow activation and deactivation of a compartment via the agent", async function () {
            await cook.setCore(componentCore.address);
            // MealPlan is the actual compartment contract
            await cook.activateCompartment(plan.address);
            expect(await cook.getActiveCompartment()).to.equal(plan.address);
            await cook.deactivateCompartment();
            expect(await cook.getActiveCompartment()).to.equal(ethers.constants.AddressZero);
        });
    });

    describe("Decision Making or Dynamic Reasoning", function() {
       it("should prepare pasta if willingness is high and energy usage is off-peak", async function () {
           // execute the decision once and verify plan state
           await ruleBasedReasoning.decideMeal();
           const currentPlan = await plan.currentPlan();
           expect(currentPlan.isBoiled).to.equal(true);
           expect(currentPlan.isCooked).to.equal(true);

           // check that goal plan tree executed MakePasta (node should still exist but parent relationship updated)
           const rootPath = ethers.utils.keccak256(ethers.utils.defaultAbiCoder.encode(["string","string"],["","PrepareMeal"]));
           const root = await goalTree.getNode(rootPath);
           expect(root[0]).to.equal("PrepareMeal");
           // executing plan does not remove node immediately, but ensure we can still call executePlan again without revert
           await goalTree.executePlan("MakePasta");
       });
       it("Should not prepare a sandwich if the agent is on a low-carb diet", async function () {
            // Set the agent to a low-carb diet
            await ruleBasedReasoning.setDiet(1); // 1 corresponds to Diet.LowCarb
            await ruleBasedReasoning.setWillingness(1);

            const action = await ruleBasedReasoning.callStatic.decideMeal();
            expect(action).to.equal("No Sandwich due to Low-Carb Diet");
       });

       it("Should prepare a salad if ingredients are available and willingness is high", async function () {
            // Ensure willingness is high and ingredients are available
           await ruleBasedReasoning.setIngredientsAvailability(0);
           await ruleBasedReasoning.setWillingness(0); // 0 == High

           // perform the action and check plan state
           await ruleBasedReasoning.decideMeal();
           const currentPlan = await plan.currentPlan();
           expect(currentPlan.saladAssembled).to.equal(true);

           // after performing salad once, drop the whole tree by calling decideMeal again with no actionable rule
           await ruleBasedReasoning.setWillingness(1); // Low
           await ruleBasedReasoning.setIngredientsAvailability(1); // missing
           await ruleBasedReasoning.decideMeal();
           // tree should be cleared
           const isDropped = await goalTree.isDroppedGoal("PrepareMeal", "");
           expect(isDropped).to.equal(true);
        });

       it("Should not prepare anything if ingredients are unavailable and willingness is low", async function () {
            // Set ingredients availability to unavailable and willingness to low
            await ruleBasedReasoning.setIngredientsAvailability(1); // 1 Missing or not available
            await ruleBasedReasoning.setWillingness(1); // 0 corresponds to Willingness.Low

            const action = await ruleBasedReasoning.callStatic.decideMeal();
            expect(action).to.equal("Nothing to prepare");
       });

    });

    // ---------- new tests exercising the BDI engine components ----------------
    describe("BDI Deliberation Engine", function() {
        it("should allow the reasoning contract to report the current desire", async function() {
            // desire contract initialized to None, change to ExecuteIntention and verify
            await socialAgentDesire.setDesire("PrepareMeal", 1);
            const current = await ruleBasedReasoning.getCurrentDesire();
            expect(current).to.equal("ExecuteIntention");
        });

        it("should run a simple deliberation cycle and return true result", async function() {
            const agentName = "Chef";
            await socialAgent.setName(agentName);
            await deliberation.setIntentionsBeforeDeliberationCycle(ruleBasedReasoning.address, "MakePasta");
            await deliberation.setBeliefsBeforeDeliberationCycle("Willingness", 0);
            await deliberation.setSocialAgentNameForIntentions(agentName);

            await deliberation.deliberationCycle(
                agentName,
                "MealSociety",
                ruleBasedReasoning.address,
                0,
                "MakePasta",
                "PrepareMeal",
                "Willingness"
            );
            expect(await deliberation.deliberationCycleResult()).to.equal(true);
        });

        it("should perform the same cycle through the MealPreparationDeliberator wrapper", async function() {
            const agentName = "Chef";
            // configure the wrapper using its helper
            await mealDeliberator.prepareAgent(
                agentName,
                ruleBasedReasoning.address,
                "MakePasta",
                "Willingness",
                0
            );
            // use callStatic to capture return value
            const result = await mealDeliberator.callStatic.runMealCycle(agentName, ruleBasedReasoning.address);
            expect(result).to.equal(true);

            // actually execute the transaction to populate messages
            await mealDeliberator.runMealCycle(agentName, ruleBasedReasoning.address);
            const msgs = await mealDeliberator.getMessages(agentName);
            expect(msgs.length).to.be.greaterThan(0);
        });
    });

});
