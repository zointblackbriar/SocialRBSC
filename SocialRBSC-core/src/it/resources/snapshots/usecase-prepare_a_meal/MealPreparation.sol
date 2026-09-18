// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "./MealPlan.sol";

contract MealPreparation {

    MealPlan public plan;

                // Event placeholder for action: Turn on stove
        
    constructor(address _planAddress) {
        plan = MealPlan(_planAddress);
    }

            // SubGoal: Boil water
            function turnOnStove() public {
        // TODO: implement action: Turn on stove
        // Avoidance: check peak
        // Willingness: 
        // Example: Read only the needed fields (partial destructuring):
        // ( , , , MealPlan.EnergyUsage energyUsage, MealPlan.Willingness willingness, , , , , ) = plan.currentPlan();
    }
            
}
