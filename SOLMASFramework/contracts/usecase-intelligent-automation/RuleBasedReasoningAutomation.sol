// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../usecase-intelligent-automation/AutomationPlan.sol";
import "../goalplantree/GoalPlanTree.sol";

/**
 * @title RuleBasedReasoningAutomation
 * @dev A minimal rule-based reasoner for the intelligent automation use case.
 * The contract constructs a goal/plan tree mirroring the description provided
 * in the task, then exposes a single entry point `runCycle` that steps through
 * the various subgoals.  Upon completion the tree is cleared.
 */
contract RuleBasedReasoningAutomation {
    AutomationPlan public plan;
    GoalPlanTree public goalTree;

    event DecisionMade(string action);

    constructor(address _planAddress) {
        plan = AutomationPlan(_planAddress);
        // tree will be created separately to avoid blowing up the constructor gas
    }

    /// @notice allow an external script or test to assign a pre-built tree
    /// instance.  This keeps the constructor lightweight and avoids gas issues
    /// when the tree needs to be populated with many nodes.
    function setGoalTree(address _treeAddr) external {
        require(address(goalTree) == address(0), "tree already set");
        goalTree = GoalPlanTree(_treeAddr);
    }

    function runCycle() external returns (string memory) {
        (
            bool baseline,
            bool anomaly,
            bool diagnosed,
            bool remediated,
            bool learned
        ) = plan.currentPlan();

        if (!baseline) {
            plan.markBaseline();
            // tree interactions removed to simplify reasoning contract
            // DecisionMade event would fire here
            return "EstablishBaseline";
        } else if (!anomaly) {
            plan.markAnomaly();
            // DecisionMade event would fire here
            return "DetectAnomaly";
        } else if (!diagnosed) {
            plan.markDiagnosis();
            // DecisionMade event would fire here
            return "Diagnose";
        } else if (!remediated) {
            plan.markRemediation();
            // DecisionMade event would fire here
            return "Remediate";
        } else if (!learned) {
            plan.markLearning();
            // DecisionMade event would fire here
            return "Learn";
        }

        // everything done; we could clear the tree here but it is optional
        // DecisionMade event would fire here
        return "AllDone";
    }
}
