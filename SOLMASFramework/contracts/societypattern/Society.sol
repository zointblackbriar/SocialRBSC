// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "./ConcreteMediator.sol";

contract Society {
    string public societyName;
    uint public societyID;


    function setSocietyName(string memory _societyName) external {
        societyName = _societyName;
    }

    function getSocietyName() external view returns(string memory){
        return societyName;
    }


    function setSocietyID(uint _societyID) external {
        societyID = _societyID;
    }


}



