// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "./StorageSlot.sol";

/// @dev this code snippet still under development
contract TestSlot {
    bytes32 public constant slot = keccak256("TEST_SLOT");

    function getSlot() external view returns (address) {
        return StorageSlot.getAddressSlot(slot).value;
    }

    function writeSlot(address _addr) external {
        StorageSlot.getAddressSlot(slot).value = _addr;
    }
}



