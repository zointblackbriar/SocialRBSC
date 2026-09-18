// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../socialagentengine/SocialAgentDeliberationCycle.sol";
import "../socialagentengine/SocialAgentIntention.sol";
import "../socialagentengine/SocialAgentBelief.sol";
import "../societypattern/SocialAgent.sol";

/// @title A simple wrapper demonstrating use of the deliberation cycle
///        from within a meal-preparation use case contract.
contract MealPreparationDeliberator {
    SocialAgentDeliberationCycle public deliberation;

    constructor(address _deliberation) {
        deliberation = SocialAgentDeliberationCycle(_deliberation);
    }

    /// @dev helper to configure the agent name and an initial intention/belief
    function prepareAgent(
        string memory _agentName,
        address _intentionsAddress,
        string memory _intention,
        string memory _beliefName,
        uint _beliefValue
    ) external {
        deliberation.setSocialAgentNameForIntentions(_agentName);
        deliberation.setIntentionsBeforeDeliberationCycle(_intentionsAddress, _intention);
        deliberation.setBeliefsBeforeDeliberationCycle(_beliefName, _beliefValue);
    }

    /// @dev run a deliberation cycle using fixed meal-specific parameters
    function runMealCycle(
        string memory _agentName,
        address _intentionsAddress
    ) external returns (bool) {
        deliberation.deliberationCycle(
            _agentName,
            "MealSociety",
            _intentionsAddress,
            0,
            "MakePasta",
            "PrepareMeal",
            "Willingness"
        );
        return deliberation.deliberationCycleResult();
    }

    /// @dev proxy to get messages for testing
    function getMessages(string memory _sender) external view returns (SocialAgentDeliberationCycle.Message[] memory) {
        return deliberation.getMessage(_sender);
    }
}
