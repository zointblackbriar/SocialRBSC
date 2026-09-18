//SPDX-License-Identifier: AGPL-3.0
pragma solidity 0.8.13;

// @title Collection of utils functions of the SOLMAS Framework
// @author Orcun Oruc
// @dev We have two different utils yet. Compare and
contract Utils {
    bytes32 public resultstringToBytes32;
    // @dev compare two different strings whether or not the length of them is equal.
    function compare(
        string memory _firstString,
        string memory _secondString
    ) public pure returns (bool) {
        if (bytes(_firstString).length != bytes(_secondString).length) {
            return false;
        }
        return
            keccak256(abi.encodePacked(_firstString)) ==
            keccak256(abi.encodePacked(_secondString));
    }

    // @dev substring function for a string with starting and ending parameters
    function substring(string memory _firstString, uint _startIndex, uint _endIndex) public view returns(string memory) {
        bytes memory strBytes = bytes(_firstString);
        require(_startIndex < strBytes.length && _endIndex < strBytes.length && _startIndex <= _endIndex, "invalid substring indices");
        bytes memory result = new bytes(_endIndex - _startIndex + 1);

        for(uint i = _startIndex; i  <= _endIndex; i++) {
            result[i - _startIndex] = strBytes[i]; //assign the result to the parameter
        }

        return string(result);
    }

    /// @dev string to bytes32 conversion for smart contracts
    /// @dev in order to protect from the error
    function stringToBytes32(string memory str) public returns(bytes32) {
        bytes32 localResult; //local variable for inline assembly
        assembly {
            localResult := mload(add(str, 32))
        }
        resultstringToBytes32 = localResult; // global variable return for test purposes
        return resultstringToBytes32;
    }

    // @dev string to bytes32 conversion for smart contracts for the sake of brevity with low level call
    // Input byte array must be in range 0 < M <= 32 and length must match type-web3j
    function stringToBytes32RobustVersion(string memory source) public view returns (bytes32 result) {
        bytes memory tempEmptyStringTest = bytes(source);
        if (tempEmptyStringTest.length == 0) {
            return 0x0;
        }

        assembly {
            result := mload(add(source, 32))
        }
    }

    // @dev string to bytes32 hashing conversion
    function stringToBytes32Hash(string memory str) public pure returns(bytes32) {
        return keccak256(abi.encodePacked(str));
    }

    // @dev conversion to bytes from uint256 values.
    function toBytes(uint256 _input) public pure returns (bytes memory _result) {
        _result = new bytes(32);
        // @dev inline assembly to add byte result from _input
        assembly {
            mstore(add(_result, 0x20), _input) // The mstore instruction places the _input at the given memory location (_result + 0x20).
        }

        return _result;
    }

    /// @dev this is only for the calculation of the path in bytes in terms of Goal Plan Tree.
    function computeBytePath(string memory _parentGoalName, string memory _goalName) public pure returns (bytes32) {
        // Return the keccak256 hash of the encoded empty string and the parent goal name
        return keccak256(abi.encode(_parentGoalName, _goalName));
    }

}



