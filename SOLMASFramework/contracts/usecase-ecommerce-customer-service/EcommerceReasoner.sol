// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../goalplantree/GoalPlanTree.sol";
import "../socialagentengine/SocialAgentDeliberationCycle.sol";

/// @title Simple helper combining a goal tree with the deliberation cycle
///        for the e-commerce customer service use case.  The idea is that the
///        shopping workflow can be modelled as a tree of goals and then an agent
///        can deliberate over them; compartments and roles are passed in during
///        execution to demonstrate the cross-module interaction.
contract EcommerceReasoner {
    GoalPlanTree public tree;
    SocialAgentDeliberationCycle public deliberation;

    constructor(address _deliberation) {
        tree = new GoalPlanTree();
        deliberation = SocialAgentDeliberationCycle(_deliberation);
    }

    /// @notice add a goal/step to the shopping workflow
    function addShoppingGoal(string memory name, string memory parent) external {
        tree.add(name, parent, "", 0, 0);
    }

    /// @notice link a plan goal under another
    function linkShoppingPlan(string memory plan, string memory goal) external {
        tree.linkPlanToGoal(plan, goal);
    }

    /// @notice run a deliberation cycle on behalf of the shopping assistant
    function runDeliberation(
        string memory agentName,
        string memory societyName,
        address intentionsAddress,
        uint indexOfIntent,
        string memory intention,
        string memory metamodelName,
        string memory beliefName
    ) external returns (bool) {
        deliberation.setSocialAgentNameForIntentions(agentName);
        deliberation.setIntentionsBeforeDeliberationCycle(intentionsAddress, intention);
        deliberation.setBeliefsBeforeDeliberationCycle(beliefName, 1);
        deliberation.deliberationCycle(
            agentName,
            societyName,
            intentionsAddress,
            indexOfIntent,
            intention,
            metamodelName,
            beliefName
        );
        return deliberation.deliberationCycleResult();
    }
}
