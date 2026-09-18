//SPDX-License-Identifier:MIT
pragma solidity 0.8.13;

import "./Mediator.sol";

contract ConcreteMediator is Mediator {
    mapping(string => address) public assignedAgentsToSociety; // address binding for _agentSpec and address _socialAgent
    bool public isMediator;

    function bindAgent(address _societyAddress, string memory _socialAgentName) override public {
        require(_societyAddress != address(0x0), "a social agent should have an address");
        assignedAgentsToSociety[_socialAgentName] = _societyAddress;
    }

    function unbindAgent(string memory _socialAgentName) override public {
        delete assignedAgentsToSociety[_socialAgentName];
    }


    function getAgentFromSociety(string memory _agentName) override public view returns(address){
        // Retrieve the address of the agent from the mapping
        address agentAddress = assignedAgentsToSociety[_agentName];

        // Ensure the agent exists
        require(agentAddress != address(0x0), "Agent not found in the society");

        // Return the agent's address
        return agentAddress;
    }
}


