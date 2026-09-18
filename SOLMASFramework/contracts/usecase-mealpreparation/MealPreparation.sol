// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "./MealPlan.sol";
import "./Cook.sol";
import "./Helper.sol";
import "./Customer.sol";

contract MealPreparation is Society{

    MealPlan public plan;
    Cook public cook;
    Helper public helper;
    Customer public customer;
    address public ruleReasoner; // optional external contract that is trusted to invoke actions

    /// @dev set addresses of the agents who are allowed to perform actions
    function setAgents(address _cook, address _helper, address _customer) external {
        cook = Cook(_cook);
        helper = Helper(_helper);
        customer = Customer(_customer);
    }

    /// @dev register a rule-based controller that may also operate the meal
    function setRuleReasoner(address _reasoner) external {
        ruleReasoner = _reasoner;
    }

    modifier onlyCookOrHelper() {
        require(
            msg.sender == address(cook) ||
            msg.sender == address(helper) ||
            msg.sender == ruleReasoner,
            "unauthorized: not cook/helper"
        );
        _;
    }

    modifier onlyCustomer() {
        require(msg.sender == address(customer), "unauthorized: not customer");
        _;
    }


    // Events for actions
    event FilledPot();
    event PlacedPotOnStove();
    event TurnedOnStove();
    event AddedPasta();
    event StirredPasta();
    event PlanReset();


    // Constructor takes the address of the Plan contract
    constructor(address _planAddress) {
        plan = MealPlan(_planAddress);
    }



    // ActÄ±on: Boil Water (Sub-goal)
    function boilWater() external onlyCookOrHelper {
        (bool isBoiled, , , MealPlan.EnergyUsage energyUsage, , , , , , ) = plan.currentPlan();
        // Avoidance condition: if peak electricity, avoid boiling water
        require(energyUsage == MealPlan.EnergyUsage.OffPeak, "Avoid boiling water during peak electricity hours.");
        require(!isBoiled, "Water is already boiled.");

        // Boiling water actions sequentially
        fillPot();
        placePotOnStove();
        turnOnStove();
        plan.markBoiled(); // water is boiled now
    }

    // Action: Cook pasta (Sub-goal)
    function cookPasta() public onlyCookOrHelper {
        (bool isBoiled, bool isCooked, , MealPlan.EnergyUsage energyUsage, , , , , , ) = plan.currentPlan();

        // Check if water is boiled first
        require(isBoiled, "Water needs to be boiled first.");

        // Avoidance condition: Ensure not leaving the stove unattended
        // condition for
        require(energyUsage == MealPlan.EnergyUsage.OffPeak, "Avoid cooking during peak energy hours.");
        require(!isCooked, "Pasta is already cooked.");

        // Cooking pasta actions
        addPastaToBoilingWater();
        stirOccasionally();
        plan.markCooked();
    }

    // Action: Serve pasta (Sub-goal)
    function servePasta() external onlyCustomer {
        // Willingness condition: If high willingness, prioritize serving the meal
        ( , bool isCooked, bool isServed, , MealPlan.Willingness willingness, , , , , ) = plan.currentPlan();
        require(willingness == MealPlan.Willingness.High, "The agent does not prefer a warm meal at the moment.");
        require(isCooked, "Pasta needs to be cooked first.");
        require(!isServed, "Meal is already served.");

        plan.markServed();
    }




    // Functions representing the individual actions
    function fillPot() internal returns(string memory){
        // Simulate filling pot with water
        // FilledPot event would be emitted here
        return "FilledPot()";
    }

    function placePotOnStove() internal returns(string memory){
        // Simulate placing pot on stove
        // PlacedPotOnStove event would be emitted here
        return "PlacedPotOnStove()";
    }

    function turnOnStove() internal returns(string memory){
        // Simulate turning on stove
        // TurnedOnStove event would be emitted here
        return "TurnedOnStove()";
    }

    function addPastaToBoilingWater() internal returns(string memory){
        // Simulate adding pasta to boiling water
        // AddedPasta event would be emitted here
        return "AddedPasta()";
    }

    function stirOccasionally() internal returns(string memory){
        // Simulate stirring pasta
        // StirredPasta event would be emitted here
        return "StirredPasta()";
    }

    // Function to reset the plan via the Plan contract
    function resetMeal() external {
        plan.resetPlan();
    }

}



