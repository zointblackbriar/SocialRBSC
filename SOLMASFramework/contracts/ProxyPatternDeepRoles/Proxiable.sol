// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

contract Proxiable {
    bytes32 private constant PROXIABLE_SLOT = 0x6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c;

    function updateCodeAddress(address newAddress) public {
        require(
            bytes32(PROXIABLE_SLOT) == Proxiable(newAddress).proxiableUUID(),
            "Not compatible"
        );
        assembly { // solium-disable-line
            sstore(PROXIABLE_SLOT, newAddress)
        }
    }

    function proxiableUUID() public pure returns (bytes32) {
        return PROXIABLE_SLOT;
    }

}



