// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../societypattern/SocialAgent.sol";
import "../staticroleassignment/Compartment.sol";

/// @title Trade Execution Agent Compartment
contract TradeExecutionAgent is SocialAgent {
    constructor(string memory _name) {
        setName(_name);
    }

    /// @notice receive a recommendation
    function receiveRecommendation(string memory rec) external {
        emit RecommendationReceived(rec);
    }

    /// @notice execute trade stub
    function executeTrade(bytes32 symbol, uint256 size, uint256 price) external {
        // stub; in reality would call exchange APIs
        emit TradeExecuted(symbol, size, price);
    }

    event RecommendationReceived(string rec);
    event TradeExecuted(bytes32 symbol, uint256 size, uint256 price);
}
