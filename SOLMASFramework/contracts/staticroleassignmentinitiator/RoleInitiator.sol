// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";

contract SampleRole1 is ComponentRole {
    string public message;
    function roleFunction() public {
        message = "roleFunction test value";
    }
}

contract SampleRole1Updated is ComponentRole { }

contract SampleRole2 is ComponentRole { }

contract RoleInitiator {}


