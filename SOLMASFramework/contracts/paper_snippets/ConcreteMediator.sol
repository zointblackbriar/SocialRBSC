// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

interface Mediator {
    function bindAgent(address _agentAddress, string memory _agentName) external;
    function unbindAgent(string memory _agentName) external;
    function getAgentFromSociety(string memory _agentName) external view returns(address);
}

contract ConcreteMediator is Mediator {
    mapping(string => address) public assignedAgentsToSociety;
    bool public isMediator;

    // Society owner: the only authority allowed to register or remove agents.
    address public society;

    constructor() {
        society = msg.sender;
    }

    modifier onlySociety() {
        require(
            msg.sender == society,
            "Only the society can manage agent bindings"
        );
        _;
    }

    function bindAgent(
        address _societyAddress,
        string memory _socialAgentName
    ) public override onlySociety {
        require(
            _societyAddress != address(0x0),
            "a social agent should have an address"
        );
        assignedAgentsToSociety[_socialAgentName] = _societyAddress;
    }

    function unbindAgent(string memory _socialAgentName) public override onlySociety {
        delete assignedAgentsToSociety[_socialAgentName];
    }

    function getAgentFromSociety(
        string memory _agentName
    ) public view override returns(address) {
        address agentAddress = assignedAgentsToSociety[_agentName];
        require(agentAddress != address(0x0), "Agent not found in the society");
        return agentAddress;
    }
}
