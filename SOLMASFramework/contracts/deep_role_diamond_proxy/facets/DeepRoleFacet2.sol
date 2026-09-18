// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;
import {LibDeepRole2} from "../libraries/LibDeepRole2.sol";


contract DeepRoleFacet2 {

    function setDeepRoleID(string memory _deepRoleID) external {
        LibDeepRole2.setDeepRoleID(_deepRoleID);

    }

    function getDeepRoleID() external returns(string memory) {
        return LibDeepRole2.getDeepRoleID();

    }

    function externalMessageDeepRoleRelated() public returns(string memory) {
        return LibDeepRole2.externalMessageDeepRoleRelated();
    }


}



