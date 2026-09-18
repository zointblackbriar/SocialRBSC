
// SPDX-License-Identifier: AGPL-3.0
pragma solidity >=0.8.7;

import "./Component.sol";
import "./ComponentRole.sol";
import "./Compartment.sol";
import "../interfaceseparator/InterfaceIds.sol";
import "../interfaceseparator/ERC165Query.sol";
import "./Player.sol";

contract ComponentCore is Component, ERC165Query {
mapping(bytes32 => ComponentRole) public playedRoles;
bool public isSuitableForDeepRoles;
bool public isComponentRole;
bool public isCompartment;
address public stateVariableRole;
Compartment public compartmentInUse;

/// @notice Query if a contract implements an interface
/// @param interfaceID The interface identifier, as specified in ERC-165
/// @dev Interface identification is specified in ERC-165. This function
///  uses less than 30,000 gas.
/// @return `true` if the contract implements `interfaceID` and
///  `interfaceID` is not 0xffffffff, `false` otherwise
function supportsInterface(
bytes4 interfaceID
) external pure override returns (bool) {
return
interfaceID == 0x01ffc9a7 || // ERC165
interfaceID == InterfaceIds.COMPONENT_ID; // Component
}

/// @dev Grants `role` to `component` and associates it to `spec`.
function addRole(bytes32 spec, address role) external override {
if (role == address(0)) return;
stateVariableRole = role;
// role should be registered to a state variable
isComponentRole = doesContractImplementInterface(
role,
InterfaceIds.COMPONENT_ROLE_ID
);
require(isComponentRole, "Doesn't support ComponentRole interface.");

ComponentRole componentRole = ComponentRole(role);
playedRoles[spec] = componentRole;
componentRole.setCore(address(this));
}

/// @dev Revokes role with `spec` from `component`.
/// @notice If `component` has not been granted the role with `spec`, does nothing.
function removeRole(bytes32 spec) external override {
if (!this.isPlayingRole(spec)) return;
ComponentRole componentRole = ComponentRole(playedRoles[spec]);
componentRole.setCore(address(0));
delete (playedRoles[spec]);
}

/// @dev Returns `true` if `component` has been granted role with `spec`.
function isPlayingRole(bytes32 spec) external view override returns (bool) {
return address(playedRoles[spec]) != address(0); // it does not check the address again for deep roles.
}

function hasBeenPlayedRuntime(address _contractAddress, string calldata _functionName) external override returns(bool){
Player player = Player(_contractAddress);
return player.playDelegateCallRoleContract(_contractAddress, _functionName);
}

/// @dev Returns the role address that is associated to `spec`.
/// @notice If `component` has not been granted the role with `spec`, returns 0x00 address.
function getRole(bytes32 spec) external override returns (address) {
ComponentRole role = playedRoles[spec];
if (this.isPlayingRole(spec)) {
role.setCore(address(this));
}
return address(role);
}

/// @dev Sets a `compartment` to the `component` to indicate entering a context.
/// @notice If another `compartment` has already been set, it gets overridden.
function activateCompartment(address _compartmentAddress) external override {
isCompartment = doesContractImplementInterface(
_compartmentAddress,
InterfaceIds.COMPARTMENT_ID
);
require(isCompartment, "Doesn't support Compartment interface.");
compartmentInUse = Compartment(_compartmentAddress);
}

/// @dev Unsets the `compartment` if there is one activated.
function deactivateCompartment() external override {
delete (compartmentInUse);
}

/// @dev Returns the `address` of the activated `compartment`.
function getActiveCompartment() external view override returns (address) {
return address(compartmentInUse);
}
}
