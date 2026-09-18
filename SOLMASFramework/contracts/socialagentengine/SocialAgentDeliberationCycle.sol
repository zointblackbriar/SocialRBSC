// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "./SocialAgentStateMachine.sol";
import "./SocialGoal.sol";
import "../societypattern/SocialAgent.sol";
import "../societypattern/Society.sol";
import "./SocialAgentIntention.sol";
import "./SocialAgentBelief.sol";
import "./Ownable.sol";

contract SocialAgentDeliberationCycle is Ownable {

    SocialAgentStateMachine socialAgentStateMachine;
    SocialAgentIntention socialAgentIntention; // Reference to the intetion stack contract
    SocialAgentBelief socialAgentBelief; // Refernece to the beliefs
    SocialAgent socialAgent;
    uint societyId = 0;
    uint beliefId = 0;
    bool public deliberationCycleResult = false;
    string[] socialAgentList;


    Society society;
    // 1. Agent State Management

    constructor(address payable _socialAgentStateMachineAddress, address payable _society,
        address _socialAgentAddress, address _socialAgentIntentionAddress, address _socialAgentBeliefAddress)  {
        socialAgentStateMachine = SocialAgentStateMachine(_socialAgentStateMachineAddress);
        society = Society(_society);
        socialAgent = SocialAgent(_socialAgentAddress);
        socialAgentIntention = SocialAgentIntention(_socialAgentIntentionAddress);
        socialAgentBelief = SocialAgentBelief(_socialAgentBeliefAddress);
    }

    // @dev 2. Behavior Mapping for Goals
    mapping(SocialAgentStateMachine.AgentStates => SocialGoal.GoalStatus) public behaviorMapping;

    // @dev 3. Communication Routing - to communicate with other agents - message passing
    struct Message {
        string sender;
        string receiver;
        string messageType;
    }

    mapping(string => Message[]) public communicationRouting;

    // @dev 4. Resource Allocation
    function allocateSocialGoals(SocialGoal.GoalStatus _socialGoal) external {
        // Sample goals as follows:
        require(SocialAgentStateMachine.AgentStates.NEWAGENT == socialAgentStateMachine.agentState(), "Social agent should be newly generated");
//        socialAgentStateMachine.nextState(); // Agent is in runnable form
        behaviorMapping[socialAgentStateMachine.agentState()] = _socialGoal;
    }

    // @dev Function to add a new message to communication routing
    function addMessage(string memory _senderName, string memory _receiverName, string memory _messageType) public {
        communicationRouting[_senderName].push(Message(_senderName, _receiverName, _messageType));
    }

    // @dev Function to get a message from communication routing
    function getMessage(string memory _senderName) external view returns (Message[] memory) {
        return communicationRouting[_senderName];
    }

    // @dev set intentions for the deliberation cycle
    function setIntentionsBeforeDeliberationCycle(address assignmentIntentionStackAddress, string memory _intention) public {
        socialAgentIntention.pushIntention(assignmentIntentionStackAddress, _intention);
    }
    // @dev set beliefs for the deliberation cycle
    function setBeliefsBeforeDeliberationCycle(string memory _beliefName, uint _value) public {
        socialAgentBelief.addBelief(_beliefName, _value);
    }

    // @dev set social agent name
    function setSocialAgentNameForIntentions(string memory _socialAgentName) public {
        socialAgent.setName(_socialAgentName);
    }

    function getAgentIntentionAtIndex(address _addressOfIntentions, uint _index) public view returns (string memory) {
        require(_index < socialAgentIntention.getIntentionStackSize(_addressOfIntentions), "Intention index out of bounds");
        return socialAgentIntention.getAgentIntentionAtIndex(_addressOfIntentions, _index);
    }


    // @dev Main deliberation cycle, now integrated with intentions
    function deliberationCycle(string memory _nameOfAgent, string memory _nameOfSociety,
        address _addressOfIntentions, uint _indexOfAgentIntention, string memory _intentionName,
        string memory _metamodelName, string memory _socialAgentBeliefName) external {
        if (societyId < type(uint).max) {
            societyId++;
        }
        if (beliefId < type(uint).max) {
            beliefId++;
        }
        string memory intention;
        // @dev Create an agent with role and connect with Society
        // @dev Allocate goals based on the current state
        if (socialAgentStateMachine.agentState() == SocialAgentStateMachine.AgentStates.NEWAGENT) {
            // Allocate initial goals for a new agent
            society.setSocietyName(_nameOfSociety);
            society.setSocietyID(societyId);
            socialAgentList.push(socialAgent.getSocialAgentName());
            behaviorMapping[socialAgentStateMachine.agentState()] = SocialGoal.GoalStatus.Active;
            // Push initial intentions onto the intention stack
            socialAgentIntention.pushIntention(_addressOfIntentions, _intentionName);
            intention = socialAgentIntention.getAgentIntentionAtIndex(_addressOfIntentions, _indexOfAgentIntention);
            require(bytes(intention).length > 0, "there should be a intention");
            socialAgentBelief.addBelief(_socialAgentBeliefName, beliefId);
            socialAgentStateMachine.nextState();  // Transition to the next state (e.g., RUNNABLE)

        }

        // Strategy or Plan Selection based on agent's state and goals
        if (socialAgentStateMachine.agentState() == SocialAgentStateMachine.AgentStates.RUNNABLEAGENT) {
            if (socialAgentBelief.hasBelief(_socialAgentBeliefName)) {
                socialAgentIntention.executeIntention(socialAgentList, _metamodelName, "Execute Intention", _addressOfIntentions);
            } else {
                deliberationCycleResult = false;
            }
            socialAgentStateMachine.nextState();  // Transition to ACTIVE state after executing the strategy
        }


        // @dev  Sending a message for goalstatus has been completed
        if (socialAgentStateMachine.agentState() == SocialAgentStateMachine.AgentStates.TERMINATEDAGENT) {
            // Agent has completed its goals, now communicating the results
            behaviorMapping[socialAgentStateMachine.agentState()] = SocialGoal.GoalStatus.Completed;
            addMessage(_nameOfAgent, _nameOfSociety, "Goal Completed");
        }

        // @dev Cycle ends when agent reaches COMPLETED state
        deliberationCycleResult = true; // Control parameter for the deliber4ation cycle result
    }
}



