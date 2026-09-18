// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;
import "./ConcreteMediator.sol";
import "./SocialAgent.sol";
import "../staticroleassignment/ComponentCore.sol";
import "../utils/Utils.sol";
import "../staticroleassignment/CompartmentInitiator.sol";
import "../staticroleassignment/ComponentRole.sol";

/// @dev social agent initiator contract for society, social agent name, role and compartment assignment
contract SocialAgentInitiator is ComponentRole{
    SocialAgent socialAgent;
    ConcreteMediator concreteMediator;
    Society society;
    ComponentCore componentCore;
    Utils utils;
    CompartmentInitiator compartmentInitiator;
    constructor(address _socialAgentAddress, ConcreteMediator _concreteMediatorAddress, Society _societyAddress,
                ComponentCore _componentCoreAddress, CompartmentInitiator _compartmentInitiator, Utils _utilsAddress){
        socialAgent = SocialAgent(_socialAgentAddress);
        concreteMediator = ConcreteMediator(_concreteMediatorAddress);
        society = Society(_societyAddress);
        componentCore = ComponentCore(_componentCoreAddress);
        utils = Utils(_utilsAddress);
        compartmentInitiator = CompartmentInitiator(_compartmentInitiator);
    }

    function socialAgentInitiatorForSociety(string memory _socialAgentName) public returns (address _societyAddress){
        socialAgent.createAgent(_socialAgentName);
        socialAgent.socialAgentBindToSociety(address(society), concreteMediator, socialAgent.getSocialAgentName());
        // avoid shadowing the named return variable
        _societyAddress = concreteMediator.getAgentFromSociety(socialAgent.getSocialAgentName());
        return _societyAddress;
    }

    function addRoleInitiator(string memory _roleName, address _roleAddress) public {
        require(!componentCore.isPlayingRole(utils.stringToBytes32Hash(_roleName)), "otherwise we cannot add this if it is played");
//        componentCore.addRole(utils.stringToBytes32Hash(_roleName), address(this)); // assign the current contract address as role address
        componentCore.addRole(utils.stringToBytes32Hash(_roleName), _roleAddress);
        componentCore.activateCompartment(address(compartmentInitiator));
    }

    function deleteRoleInitiator(string memory _roleName) public {
        require(componentCore.isPlayingRole(utils.stringToBytes32Hash(_roleName)), "otherwise we cannot add this if it is played");
        componentCore.isPlayingRole(utils.stringToBytes32Hash(_roleName));
        componentCore.deactivateCompartment();
    }

}



