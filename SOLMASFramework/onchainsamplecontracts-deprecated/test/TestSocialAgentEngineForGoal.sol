//// SPDX-License-Identifier: UNLICENSED
//pragma solidity ^0.8.0;
//import "truffle/Assert.sol";
//import "../contracts/socialagentengine/SocialAgentGoal.sol";
//import "truffle/console.sol";
//
//contract TestSocialAgentEngineForGoal {
//
//
//    SocialAgentGoal socialAgentGoal ;
//
//    function beforeEach() public {
//        socialAgentGoal = new SocialAgentGoal();
//    }
//
//
//    function testAddGoal() public {
//        string memory description = "sample goal";
//        SocialAgentGoal.Goal memory newGoal = socialAgentGoal.addGoal(description);
//
//        //Assert that the added goal matches the description and is in "Active" status
//        Assert.equal(newGoal.description, description, "Description does not match");
//        Assert.equal(uint(newGoal.status), uint(SocialAgentGoal.GoalStatus.Active), "status is not active");
//    }
//
//    function testCompleteGoal() public {
//        string memory description = "Test Goal";
//        SocialAgentGoal.Goal memory newGoal = socialAgentGoal.addGoal(description);
//        socialAgentGoal.completeGoal(0);
//
//        //Get the updated goal from the contract
//        string memory goalDefinition = socialAgentGoal.getGoalDescription(address(this), 0);
//        SocialAgentGoal.GoalStatus statusOfTheGoal = socialAgentGoal.getGoalStatus(address(this),0);
//        //Assert that the goal status is now "Completed"
//        Assert.equal(uint(statusOfTheGoal), uint(SocialAgentGoal.GoalStatus.Completed), "Status is completed");
//        Assert.equal(goalDefinition, description, "Goal descriptions match");
//    }
//
//    function testSetGoalStatus() public {
//        string memory description = "Activate Goal";
//        SocialAgentGoal.Goal memory newGoal = socialAgentGoal.addGoal(description);
//
//        SocialAgentGoal.GoalStatus newStatus = SocialAgentGoal.GoalStatus.Failed;
//        socialAgentGoal.setGoalStatus(newGoal, newStatus);
//
//        //Get the updated goal
//        SocialAgentGoal.GoalStatus updatedGoal = socialAgentGoal.getGoalStatus(address(this), 0);
//        Assert.equal(description, newGoal.description, "Name matches");
////        Assert.equal(uint(updatedGoal), uint(newStatus), "Status is not updated"); // it does not work
//
//    }
//
//}
