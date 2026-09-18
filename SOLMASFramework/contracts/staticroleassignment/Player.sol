// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

/// @dev this contract holds the addresses of played contracts regarding they have been played
contract Player{

    //state variables for the address holding
    mapping(address => bool) public playedContractList;
    //state variable for smart contract controlling
    // owner address
    address public owner;

    constructor(address _assignedAddress) {
        owner = _assignedAddress;
    }

    function playDelegateCallRoleContract(address _contractAddress, string calldata _functionName) public onlyOwner returns(bool){
//        bytes4 functionSelector = bytes4(keccak256(_functionName));
//        string storage functionName = "playContractForIdentity()";
        bytes4 functionSelector = getSelector(_functionName);
        (bool success, bytes memory returndata) = _contractAddress.delegatecall(abi.encodeWithSelector(functionSelector));

        //if the function call reverted
        if(success == false) {
            playedContractList[_contractAddress] = false;
            if(returndata.length > 0) {
                assembly {
                    let sizeReturnData := mload(returndata) //write a specific message regarding revert process.
                    revert(add(32, returndata), sizeReturnData)
                }
            } else {
                revert("Function call has been reverted");
            }
        }

        playedContractList[_contractAddress] = true; // played addresses of roles have been stored in this variable.
        return playedContractList[_contractAddress];
    }

    /// @dev if you want to use:
    function getSelector(string calldata _function) internal pure returns (bytes4) {
        return bytes4(keccak256(bytes(_function))); // 4 bytes of function selector
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "revert because it is not the owner account");
        _;
    }


}



