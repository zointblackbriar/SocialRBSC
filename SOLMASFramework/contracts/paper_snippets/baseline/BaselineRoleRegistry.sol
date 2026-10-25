// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/// Minimal hand-written role registry used as a baseline for the
/// SocialRbSC compartment contract. It implements the same core operation
/// (binding an agent to a named role) without any framework abstraction.
contract BaselineRoleRegistry {

    mapping(address => mapping(bytes32 => bool)) private roles;
    mapping(bytes32 => address[]) private members;

    event Bound(address indexed agent, bytes32 indexed role);

    function bind(bytes32 role) external {
        require(!roles[msg.sender][role], "already bound");
        roles[msg.sender][role] = true;
        members[role].push(msg.sender);
        emit Bound(msg.sender, role);
    }

    function hasRole(address agent, bytes32 role) external view returns (bool) {
        return roles[agent][role];
    }
}
