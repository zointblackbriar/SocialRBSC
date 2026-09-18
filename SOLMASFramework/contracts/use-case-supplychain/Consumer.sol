// SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";

// @title Consumer role

/// @dev represents the end recipient in the network.  While the actor is a
/// human, the contract provides a placeholder for recorded feedback or
/// delivery preferences.
contract Consumer is ComponentRole {
    uint public feedbackScore;

    function submitFeedback(uint _score) public {
        feedbackScore = _score;
    }

    function getFeedback() public view returns (uint) {
        return feedbackScore;
    }

    function playContractForIdentity() public pure returns (string memory) {
        return "consumer";
    }
}
