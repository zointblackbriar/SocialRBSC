// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../staticroleassignment/Compartment.sol";

contract MealPlan is Compartment {

    enum EnergyUsage {
        Peak,
        OffPeak
    }

    enum Willingness {
        High,
        Low
    }

    enum Diet {
        Regular,
        LowCarb
    }

    enum IngredientsAvailability {
        Available,
        Missing
    }

    // Events to emit changes
    event BoilWater();
    event CookedPasta();
    event ServePasta();
    event AssembleSandwich();
    event ServeSandwich();
    event EnergyUsageChanged(EnergyUsage newUsage);
    event WillingnessChanged(Willingness newLevel);
    event DietChanged(Diet newDiet);
    event PlanReset();
    event IngredientsAvailabilityChanged(bool available);


    //Struct for the status of the plan
    struct PlanStatus { // Plan is struct again
        bool isBoiled;  // Track if water is boiled
        bool isCooked; // Track if pasta is cooked
        bool isServed; // Track if meal is served
        EnergyUsage energyUsage; // Energy usage
        Willingness willingness; // Willingness to make meal
        bool sandwichAssembled; // For sandwich plan
        Diet diet; // Dietary restriction
        bool isSandwichServed; // Track if meal is served
        bool ingredientsInStock;  // To check if ingredients for salad are available
        bool saladAssembled;    // For salad plan
    }

    // Define the current plan status
    PlanStatus public currentPlan;

    constructor() {
        currentPlan.isBoiled = false;
        currentPlan.isCooked = false;
        currentPlan.isServed = false;
        currentPlan.energyUsage = EnergyUsage.OffPeak;
        currentPlan.willingness = Willingness.High;
        currentPlan.sandwichAssembled = false;
        currentPlan.diet = Diet.Regular;
        currentPlan.isSandwichServed = false;
        currentPlan.ingredientsInStock = true; // Assume ingredients are available by default
        currentPlan.saladAssembled = true;    // mark whether or not salad is assembled

    }


    function resetPlan() external {
        currentPlan.isBoiled = false;
        currentPlan.isCooked = false;
        currentPlan.isServed = false;
        emit PlanReset();

    }

    // Functions to update plan status
    function markBoiled() external {
        currentPlan.isBoiled = true;
        emit BoilWater();

    }

    function markCooked() external {
        currentPlan.isCooked = true;
        emit CookedPasta();

    }

    function markServed() external {
        currentPlan.isServed = true;
        emit ServePasta();

    }

    function markSandwichAssembled() external {
        currentPlan.sandwichAssembled = true;
        emit AssembleSandwich();

    }

    function markSandwichServed() external {
        currentPlan.isServed = true;
        emit ServeSandwich();

    }

    function markSaladServed() external {
        currentPlan.isServed = true;
    }

    function markSaladAssembled() external {
        currentPlan.saladAssembled = true;
    }
    //Functions to set conditions

    function setIngredientsAvailability(bool available) external {
        currentPlan.ingredientsInStock = available;
        emit IngredientsAvailabilityChanged(available);
    }

    function setDiet(Diet dietType) external {
        currentPlan.diet = dietType;
        emit DietChanged(dietType);
    }

    function setEnergyUsage(EnergyUsage _usage) external {
        currentPlan.energyUsage = _usage;
        emit EnergyUsageChanged(_usage);

    }

    function setWillingness(Willingness level) external {
        currentPlan.willingness = level;
        emit WillingnessChanged(level);

    }

}



