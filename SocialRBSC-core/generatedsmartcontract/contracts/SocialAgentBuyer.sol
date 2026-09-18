// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "./ConcreteMediator.sol";
import "./SocialAgent.sol";
import "../staticroleassignment/ComponentCore.sol";
import "../utils/Utils.sol";
import "../staticroleassignment/CompartmentInitiator.sol";

contract SocialAgentBuyer is Compartment, ConcreteMediator {
SocialAgent socialAgent;
ConcreteMediator concreteMediator;
Society society;
ComponentCore componentCore;
Utils utils;
CompartmentInitiator compartmentInitiator;

constructor(address _socialAgentAddress, ConcreteMediator _concreteMediatorAddress, Society _societyAddress,
ComponentCore _componentCoreAddress, CompartmentInitiator _compartmentInitiator, Utils _utilsAddress) {
socialAgent = SocialAgent(_socialAgentAddress);
concreteMediator = ConcreteMediator(_concreteMediatorAddress);
society = Society(_societyAddress);
componentCore = ComponentCore(_componentCoreAddress);
utils = Utils(_utilsAddress);
compartmentInitiator = CompartmentInitiator(_compartmentInitiator);
}

function socialAgentInitiatorForSociety(string memory _socialAgentName) public returns (address _societyAddress) {
socialAgent.createAgent(_socialAgentName);
socialAgent.socialAgentBindToSociety(address(society), concreteMediator, socialAgent.getSocialAgentName());
address _societyAddress = concreteMediator.getAgentFromSociety(socialAgent.getSocialAgentName());
return _societyAddress;
}


}
