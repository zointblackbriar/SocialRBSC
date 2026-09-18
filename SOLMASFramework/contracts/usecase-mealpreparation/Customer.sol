// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../societypattern/SocialAgent.sol";
import "./MealPreparation.sol";

// The customer agent who triggers the serving action
contract Customer is SocialAgent {
    MealPreparation public mealPreparation;

    constructor(address _mealPreparationAddress, string memory _name) {
        mealPreparation = MealPreparation(_mealPreparationAddress);
        setName(_name);
    }


    function serveMeal() external {
        mealPreparation.servePasta();
    }
}
