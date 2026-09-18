// SPDX-License-Identifier: MIT
// pragma solidity 0.8.13;
pragma solidity 0.8.13;

import "../interfaceseparator/ERC165.sol";

interface Component is ERC165 {

     /// @dev Grants `role` to `component` and associates it to `spec`.
     /// @dev interface functions implicity virtual after 0.8.7;
    function addRole(bytes32 _rolespec, address _roleaddress) external;

     /// @dev Revokes role with `spec` from `component`.
     /// @dev interface functions implicity virtual after 0.8.7;
     /// @notice If `component` has not been granted the role with `spec`, does nothing.
    function removeRole(bytes32 _rolespec) external;

     // @dev Returns `true` if `component` has been granted role with `spec`.
    function isPlayingRole(bytes32 _rolespec) external view returns (bool);

     /// @dev Returns the role address that is associated to `spec`. If `component` has not been granted the role with `spec`, returns 0x00 address.
    function getRole(bytes32 _spec) external returns (address);

     /// @dev Sets a `compartment` to the `component` to indicate entering a context. If another `compartment` has already been set, it gets overridden one.
    function activateCompartment(address _compartment) external;

     /// @dev Unsets the `compartment` if there is one activated.
    function deactivateCompartment() external;

     /// @dev Returns the `address` of the activated `compartment`.
    function getActiveCompartment() external view returns (address);

    /// @dev Deep Role Checking
    function hasBeenPlayedRuntime(address _contractAddress, string calldata _functionName) external returns(bool);

}



