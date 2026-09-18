// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;
//import "hardhat/console.sol";

contract BaseRole {
    // Code position in storage is keccak256("PROXIABLE") = "0x6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c"

    bytes32 private constant PROXIABLE_SLOT = 0x6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c;

    constructor(bytes memory constructData, address contractLogic) {
        // save the code address
        assembly { // solium-disable-line
            sstore(PROXIABLE_SLOT, contractLogic)
        }
        (bool success, bytes memory result ) = contractLogic.delegatecall(constructData); // solium-disable-line
        require(success, "Construction failed");
    }

    fallback() external payable {
//        console.log("----- fallback:", msg.value);
        assembly { // solium-disable-line
                let contractLogic := sload(PROXIABLE_SLOT)
                calldatacopy(0x0, 0x0, calldatasize())
                let success := delegatecall(sub(gas(), 10000), contractLogic, 0x0, calldatasize(), 0, 0)
                let retSz := returndatasize()
                returndatacopy(0, 0, retSz)
                switch success
                case 0 {
                    revert(0, retSz)
                }
                default {
                    return(0, retSz)
                }
            }
    }

    receive() external payable {
//        console.log("----- receive:", msg.value);
    }


}



