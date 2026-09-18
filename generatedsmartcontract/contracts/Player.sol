
// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.7;

/// @dev this contract holds the addresses of played contracts regarding they have been played
contract Player {

// State variables for the address holding
mapping(address => bool) public playedContractList;

// State variable for smart contract controlling
// Owner address
address public owner;

constructor(address _assignedAddress) {
owner = _assignedAddress;
}

function playDelegateCallRoleContract(address _contractAddress, string calldata _functionName) public onlyOwner returns (bool) {
bytes4 functionSelector = getSelector(_functionName);
(bool success, bytes memory returndata) = _contractAddress.delegatecall(abi.encodeWithSelector(functionSelector));

// If the function call reverted
if (!success) {
playedContractList[_contractAddress] = false;
if (returndata.length > 0) {
assembly {
let sizeReturnData := mload(returndata) // Write a specific message regarding the revert process
revert(add(32, returndata), sizeReturnData)
}
} else {
revert("Function call has been reverted");
}
}

playedContractList[_contractAddress] = true; // Played addresses of roles have been stored in this variable
return playedContractList[_contractAddress];
}

/// @dev Get the function selector from the function name
function getSelector(string calldata _function) internal pure returns (bytes4) {
return bytes4(keccak256(bytes(_function))); // 4 bytes of function selector
}

modifier onlyOwner() {
require(msg.sender == owner, "Revert because it is not the owner account");
_;
}
}
