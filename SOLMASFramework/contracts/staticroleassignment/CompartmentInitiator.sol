// SPDX-License-Identifier: MIT

pragma solidity 0.8.13;

import "../staticroleassignment/Compartment.sol";
import "../staticroleassignment/ComponentCore.sol";

// @dev Compartment is an abstract smart contract
// @dev Abstract smart contracts cannot be initiated as a regular smart contract. We need a CompartmentInitiator
contract CompartmentInitiator is Compartment {
    ComponentCore componentCore;
    constructor(ComponentCore _componentCoreAddress) {
        componentCore = ComponentCore(_componentCoreAddress);
    }

    function setData(string memory str) public returns(bytes32){
        require(bytes(str).length <= 32, "String exceeds 32 bytes");
        bytes32 data = bytes32(bytes(str));
        return data;
    }
}



