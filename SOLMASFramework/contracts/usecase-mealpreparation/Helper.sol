// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../societypattern/SocialAgent.sol";
import "./MealPreparation.sol";

// An assistant agent that can help the cook with the meal preparation
contract Helper is SocialAgent {
    MealPreparation public mealPreparation;

    constructor(address _mealPreparationAddress, string memory _name) {
        mealPreparation = MealPreparation(_mealPreparationAddress);
        setName(_name);
    }


    function assistBoil() external {
        mealPreparation.boilWater();
    }

    function assistCook() external {
        mealPreparation.cookPasta();
    }
}
