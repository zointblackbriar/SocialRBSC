 
// SPDX-License-Identifier: MIT
pragma solidity >=0.7.0 <0.9.0;
import "../../../SOLMASFramework/contracts/staticroleassignmentinitiator/RoleCreator.sol";
import "../../../SOLMASFramework/contracts/staticroleassignment/ComponentRole.sol";


contract Role is ComponentRole {

    string public yourStringVar;

    function ${roleFunction}() public {
        // Your function logic here
    }

}
contract RoleCreator is RoleCreator {
    function createFor(bytes32 spec) external override returns(ComponentRole) {
        return new Role();
    }
}
