// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../societypattern/SocialAgent.sol";
import "../staticroleassignment/Compartment.sol";

/// @title Risk Management Agent Compartment
contract RiskManagementAgent is SocialAgent {
    constructor(string memory _name) {
        setName(_name);
    }

    /// @notice update risk parameters
    function updateRiskProfile(string memory profile) external {
        emit RiskProfileUpdated(profile);
    }

    /// @notice check a proposed trade
    function validateTrade(bytes32 symbol, uint256 size) external view returns (bool) {
        // always ok for now
        return true;
    }

    event RiskProfileUpdated(string profile);
}
