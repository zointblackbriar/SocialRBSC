// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";
import "./Proxiable.sol";

// @dev EIP-1822 UUPS
contract DeepRole1 is ComponentRole, Proxiable {

    address public owner;
    uint public myUint;
    bool public initialized = false;

    function initialize() public {
        require(owner == address(0), "Already initialized");
        require(!initialized, "Already initialized");
        owner = msg.sender;
        initialized = true;
    }

    function increment() public {
        myUint++;
    }

    function updateCode(address newCode) public {
        require(msg.sender == owner, "Only owner can update the code");
        updateCodeAddress(newCode); // This will refer the new updated Role contract
    }
}



