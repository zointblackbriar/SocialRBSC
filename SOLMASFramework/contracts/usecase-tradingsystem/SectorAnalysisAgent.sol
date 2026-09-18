// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../societypattern/SocialAgent.sol";
import "../staticroleassignment/Compartment.sol";

/// @title Sector Analysis Agent Compartment
contract SectorAnalysisAgent is SocialAgent {
    constructor(string memory _name) {
        setName(_name);
    }

    /// @notice receive sentiment input
    function receiveSentiment(bytes32 symbol, int8 score) external {
        emit SentimentReceived(symbol, score);
    }

    /// @notice generate recommendation stub
    function makeRecommendation(bytes32 symbol) external returns (string memory) {
        string memory rec = "HOLD";
        emit Recommendation(symbol, rec);
        return rec;
    }

    event SentimentReceived(bytes32 symbol, int8 score);
    event Recommendation(bytes32 symbol, string rec);
}
