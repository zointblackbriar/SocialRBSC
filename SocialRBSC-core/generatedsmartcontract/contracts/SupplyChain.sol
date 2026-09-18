// SPDX-License-Identifier: MIT


pragma solidity >=0.8.7;

import "../staticroleassignment/Compartment.sol";
import "./RoleOne.sol";
import "./RoleTwo.sol";
import "./RoleOneCreator.sol";
import "./RoleTwoCreator.sol";
import "../socialagentengine/Utils.sol";

// @title Generic Supply Chain Network implementation

contract GenericEntity is Compartment {
RoleOne roleOneInstance;
RoleTwo roleTwoInstance;
Utils utils;

bytes32 public roleOneSpec = "SampleRole1Spec";
bytes32 public roleTwoSpec = "SampleRole2Spec";

function getRoleOne() public returns (RoleOne) {
if(address(roleOneInstance) != address(0)) return roleOneInstance;
//else
this.addRoleCreator(roleOneSpec , new RoleOneCreator());
roleOneInstance = RoleOne(this.addRole(roleOneSpec));
return roleOneInstance;
}

function getRoleTwo() public returns (RoleTwo) {
if(address(roleTwoInstance) != address(0)) return roleTwoInstance;
//else
this.addRoleCreator(roleTwoSpec, new RoleTwoCreator());
roleTwoInstance = RoleTwo(this.addRole(roleTwoSpec));
return roleTwoInstance;
}
}
