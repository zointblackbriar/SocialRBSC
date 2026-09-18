 
// SPDX-License-Identifier: MIT
pragma solidity >=0.7.0 <0.9.0;
import "../../../SOLMASFramework/contracts/staticroleassignmentinitiator/RoleCreator.sol";
import "../../../SOLMASFramework/contracts/staticroleassignment/ComponentRole.sol";


contract Student is ComponentRole {

    string public yourStringVar;

    function sampleMethod() public {
        // Your function logic here
    }

}
contract StudentCreator is RoleCreator {
    function createFor(bytes32 spec) external override returns(ComponentRole) {
        return new Student();
    }
}
