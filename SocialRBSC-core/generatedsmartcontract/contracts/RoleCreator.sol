
// SPDX-License-Identifier: AGPL-3.0
pragma solidity >=0.8.7;

import "../staticroleassignment/ComponentRole.sol";

interface RoleCreator {
/**
* @dev Creates a concrete instance of a `ComponentRole` from a `spec`.
*/
function createFor(bytes32 _spec) external returns (ComponentRole);
}
