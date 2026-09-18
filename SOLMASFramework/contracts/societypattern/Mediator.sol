// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

interface Mediator {
    // we have three basic agent functions to bind, unbind, and get the agent information
    function bindAgent(address _agentAddress, string memory _agentName) external;

    function unbindAgent(string memory _agentName) external;

    function getAgentFromSociety(string memory _agentName) external view returns(address);
}



