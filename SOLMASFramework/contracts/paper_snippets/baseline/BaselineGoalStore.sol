// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/// Minimal hand-written goal store used as a baseline for the SocialRbSC
/// goal--plan tree contract. It stores one goal record per identifier without
/// any tree structure, decomposition, or traversal support.
contract BaselineGoalStore {

    struct Goal {
        bytes32 path;
        string name;
        string parent;
        string data;
    }

    mapping(bytes32 => Goal) public goals;
    uint256 public goalCount;

    event GoalStored(bytes32 indexed path, string name);

    function add(bytes32 path, string memory name, string memory parent, string memory data) external {
        goals[path] = Goal(path, name, parent, data);
        goalCount += 1;
        emit GoalStored(path, name);
    }
}
