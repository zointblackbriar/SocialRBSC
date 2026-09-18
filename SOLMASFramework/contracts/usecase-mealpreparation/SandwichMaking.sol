// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "./MealPlan.sol";

contract SandwichMaking {

    constructor(address _planAdress) {
        plan = MealPlan(_planAdress);
    }

    MealPlan public plan;

    // Action: Serve sandwich (Sub-goal for sandwich)
    function serveSandwich() external {
        ( , , bool isServed, , MealPlan.Willingness willingness, , , , , ) = plan.currentPlan();

        // Willingness condition: If high willingness, prioritize serving the sandwich
        require(willingness == MealPlan.Willingness.High, "The agent prefers a heavier meal.");
        require(!isServed, "The sandwich is already served");
        plan.markSandwichServed();

    }

    // Action: Get ingredients for sandwich
    function getIngredients() external {
        // Get the current plan
        ( , , , , , , MealPlan.Diet diet, , , ) = plan.currentPlan();

        // Avoidance condition: If low-carb diet, avoid making a sandwich
        require(diet == MealPlan.Diet.Regular, "Avoid carbs due to dietary restrictions.");
    }

    // Action: Assemble sandwich
    function assembleSandwich() external {
        // Get the current plan
        ( , , , , MealPlan.Willingness willingness, , MealPlan.Diet diet, , , ) = plan.currentPlan();

        // Avoidance condition: Ensure dietary restrictions are respected
        require(diet == MealPlan.Diet.Regular, "Avoid carbs due to dietary restrictions.");

        // Willingness condition: If high willingness, prioritize assembling the sandwich
        require(willingness == MealPlan.Willingness.High, "the agent does not want a quick meal");

        // Assembling the sandwich actions
        plan.markSandwichAssembled();

    }


}



