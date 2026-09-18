// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

contract MockRoleContract {
    constructor(){

    }

    bool public played = false;

    function playContractForIdentity() public {
        played = true;
    }
}



