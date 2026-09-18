//// SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

/// @title goal assignment for Social Goal
/// @author Orcun Oruc
/// @dev This contract allows the creation, activation, and completion of goals for agents following the BDI model.
contract SocialGoal {


    address public goalOwner;
    // @dev agentGoals hashmap
    mapping(string => Goal[]) public agentGoals;


    modifier goalModifier {
        require(goalOwner == msg.sender, "this contract only can called from beliefowner contract");
        _;
    }
    // @dev is it necessary to put here
    function assignmentOwnerAddress(address ownerAssignment) public {
        goalOwner = ownerAssignment;
    }

    enum GoalStatus {Inactive, Active, Completed, Failed }

    struct Goal {
        string description; // The goal description (e.g., "Clean the house")
        GoalStatus status;  // The current status of the goal (Inactive, Active, Completed, Failed)
    }

    /// @dev This function allows an agent to create a new goal by providing a description.
    /// The goal is initialized as "Inactive".
    /// @param description Description of the goal.
    function addGoal(string memory _nameOfAgent, string memory description) external {
        Goal memory newGoal = Goal(description, GoalStatus.Inactive);
        agentGoals[_nameOfAgent].push(newGoal);
    }

    /// @dev This function activates the goal.
    /// The goal has been turned out to be "Active"
    /// @param _nameOfAgent name of the agent.
    /// @param goalIndex index of the goal that has been saved for the agent name.

    function activateGoal(string memory _nameOfAgent, uint128 goalIndex) external {
        require(goalIndex < agentGoals[_nameOfAgent].length, "Invalid goal index"); // we are assigning goal by index

        // Access goal from storage
        Goal storage goal = agentGoals[_nameOfAgent][goalIndex];
        // Only Inactive goals can be activated
        require(goal.status == GoalStatus.Inactive, "Goal is already active or completed");

        //Set the goal status to Active
        goal.status = GoalStatus.Active;

    }
    // @dev completeGoal function enables an agent ot mark a specific goal as completed. The function takes a goal
    // index as an argument and verifies that the index is within the valid range.
    function completeGoal(string memory _nameOfAgent, uint128 goalIndex) external {
        require(goalIndex < agentGoals[_nameOfAgent].length, "Invalid goal index");
        // storage of the goals in Goal Smart Contract
        Goal storage goal = agentGoals[_nameOfAgent][goalIndex];
        // Goal status should be active, otherwise pop up an error
        require(goal.status == GoalStatus.Active, "Goal is not active"); // TODO: we need to activate goalbase
        // Set the goal status to Completed
        goal.status = GoalStatus.Completed; // Assignment completed
    }


    //    function setGoalStatus(Goal memory sampleGoal, GoalStatus newState) external {
    /// @dev Sets the status of a goal for a specific agent by index. Can be used to set any valid status.
    /// @param _nameOfAgent Name of the agent.
    /// @param goalIndex Index of the goal.
    /// @param newState The new status to assign to the goal.
    function setGoalStatus(string memory _nameOfAgent, uint goalIndex, GoalStatus newState) external {
        require(goalIndex < agentGoals[_nameOfAgent].length, "Invalid goal index");

        Goal storage goal = agentGoals[_nameOfAgent][goalIndex];
        goal.status = newState;
    }


    /// @dev This function allows an agent to retrieve a specific goal by its index.
    /// @param agent The address of the agent.
    /// @param goalIndex The index of the goal to retrieve.
    /// @return description The description of the goal.
    /// @return status The current status of the goal.
    function getGoal(string memory agent, uint128 goalIndex) public view returns (string memory description, GoalStatus status) {
        require(goalIndex < agentGoals[agent].length, "Invalid goal index");
        Goal storage goal = agentGoals[agent][goalIndex];
        return (goal.description, goal.status);
    }


}



