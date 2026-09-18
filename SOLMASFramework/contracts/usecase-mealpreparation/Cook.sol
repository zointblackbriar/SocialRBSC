// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../societypattern/SocialAgent.sol";
import "./MealPreparation.sol";

// A specialized agent responsible for cooking tasks
contract Cook is SocialAgent {
    MealPreparation public mealPreparation;

    constructor(address _mealPreparationAddress, string memory _name) {
        mealPreparation = MealPreparation(_mealPreparationAddress);
        // set the social agent's name during creation
        setName(_name);
    }


    /// @dev Convenience wrappers that forward calls to the MealPreparation contract
    function boilWater() external {
        mealPreparation.boilWater();
    }

    function cookPasta() external {
        mealPreparation.cookPasta();
    }
}
