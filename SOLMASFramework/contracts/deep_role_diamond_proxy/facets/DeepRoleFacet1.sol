// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import {LibDeepRole1} from "../libraries/LibDeepRole1.sol";

contract DeepRoleFacet1 {

    // a read only value, which does not require a transaction. To do this, mark your function as a view:
    function setDeepRoleID(string memory _deepRoleID) external {
        LibDeepRole1.setDeepRoleID(_deepRoleID);
    }
    // a read only value, which does not require a transaction. To do this, mark your function as a view:
    function getDeepRoleID() external returns (string memory) {
        return LibDeepRole1.getDeepRoleID();
    }
}



