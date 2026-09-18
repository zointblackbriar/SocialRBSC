// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;
import "./Society.sol";
import "./ConcreteMediator.sol";
import "../staticroleassignment/ComponentRole.sol";

contract SocialAgent is ComponentRole{

    string public socialAgentName;

    function socialAgentBindToSociety(address _societyAddress, ConcreteMediator mediator, string memory _socialAgentName) public {
        mediator.bindAgent(_societyAddress, _socialAgentName);
    }

    function socialAgentUnbind(string memory _socialAgentName, ConcreteMediator mediator) public {
        mediator.unbindAgent(_socialAgentName);
    }


    function createAgent(string memory _socialAgentName) external {
        setName(_socialAgentName);
    }

    function setName(string memory _specAgent) public {
        socialAgentName = _specAgent;
    }

    function getSocialAgentName() public view returns(string memory) {
        return socialAgentName;
    }

}



