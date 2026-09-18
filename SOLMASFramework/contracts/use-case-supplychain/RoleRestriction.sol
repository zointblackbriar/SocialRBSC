// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "./Retailer.sol";

contract RoleRestriction is Retailer {

    address public owner;
    constructor() {
        owner = msg.sender;
    }

    modifier onlyOwner() {
        require(owner == msg.sender, "Otherwise throw an exception regarding onlyOwner is necessary");
        _;
    }
}



