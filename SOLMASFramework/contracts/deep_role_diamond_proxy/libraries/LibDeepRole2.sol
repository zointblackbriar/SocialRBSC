// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

library LibDeepRole2 {
    bytes32 constant DEEPROLE2_POSITION = keccak256("facet.deeprole2.storage");

    struct Storage{
        string deepRoleID2;
        string secondOutput;
    }

    function getStorage() internal pure returns(Storage storage ds) { // new slot for the storage
        bytes32 position = DEEPROLE2_POSITION;
        assembly {
            ds.slot := position
        }
    }

    function setDeepRoleID(string memory _deepRoleID) public {
        Storage storage ds = getStorage();
        ds.deepRoleID2 = _deepRoleID;
    }

    function getDeepRoleID() public view returns(string memory) {
        Storage storage ds = getStorage();
        return ds.deepRoleID2;
    }

    function externalMessageDeepRoleRelated() public returns(string memory) {
        Storage storage ds = getStorage();
        ds.secondOutput = "Diamond Proxy";
        return ds.secondOutput;
    }



}



