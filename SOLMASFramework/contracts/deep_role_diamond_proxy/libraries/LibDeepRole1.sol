// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

library LibDeepRole1 {

    bytes32 constant DEEPROLE1_POSITION = keccak256("facet.deeprole1.storage");

    struct Storage{
        string deepRoleID1;
    }

    function getStorage() internal pure returns(Storage storage ds) { // new slot for the storage
        bytes32 position = DEEPROLE1_POSITION;
        assembly {
            ds.slot := position
        }
    }

    function setDeepRoleID(string memory _deepRoleID) public {
        Storage storage ds = getStorage();
        ds.deepRoleID1 = _deepRoleID;
    }

    function getDeepRoleID() public returns(string memory) {
        Storage storage ds = getStorage();
        return ds.deepRoleID1;
    }

}



