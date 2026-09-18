// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

import "../staticroleassignment/ComponentRole.sol";

interface RoleCreator {
    /**
     * @dev Creates a concrete instance of a `ComponentRole` from a `spec`.
     */
    function createFor(bytes32 _roleAddress) external returns (ComponentRole);
}



