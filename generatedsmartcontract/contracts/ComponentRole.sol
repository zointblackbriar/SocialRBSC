
// SPDX-License-Identifier: MIT
pragma solidity >=0.8.7;

import "./Component.sol";
import "./ComponentCore.sol";
import "./Compartment.sol";
import "../interfaceseparator/InterfaceIds.sol";
import "../interfaceseparator/ERC165Query.sol";

abstract contract ComponentRole is Component, ERC165Query {
ComponentCore public core;

Compartment public assignedCompartment;

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
interfaceID == InterfaceIds.COMPONENT_ROLE_ID; // ComponentRole
}

// MARK: specific for the ComponentRole

/**
* @dev Sets the `componentCore` to `role`.
*/
function setCore(address coreAddress) external {
if (coreAddress != address(0)) {
bool isComponent = doesContractImplementInterface(
coreAddress,
InterfaceIds.COMPONENT_ID
);
require(isComponent, "Doesn't support Component interface.");
}
core = ComponentCore(coreAddress);
}

/// @dev Sets the `team` to `role`.
function setCompartment(address compartmentAddress) external {
if (compartmentAddress != address(0)) {
bool isTeam = doesContractImplementInterface(
compartmentAddress,
InterfaceIds.COMPARTMENT_ID
);
require(isTeam, "Doesn't support Team interface.");
}
assignedCompartment = Compartment(compartmentAddress);
}

/// @dev Modifier that checks `componentCore` has been set and
/// the core's `team` matches this role's `team`.
/// @notice Reverts with a descriptive message if the core has not been set.
/// To be used for all role functions that require the core.
modifier onlyActivateCompartment() {
require(
address(core) != address(0x0),
"This function requires the core to be set."
);
require(
core.getActiveCompartment() == address(assignedCompartment),
"The context for this role is not currently active."
);
_;
}

/// @dev Grants `role` to `component` and associates it to `spec`.
/// @notice Operations forwarded to the ComponentCore object.
function addRole(bytes32 spec, address role) external override {
core.addRole(spec, role);
}

/// @dev Revokes role with `spec` from `component`.
/// @notice If `component` has not been granted the role with `spec`, does nothing.
function removeRole(bytes32 spec) external override {
core.removeRole(spec);
}

/// @dev Returns `true` if `component` has been granted role with `spec`.
function isPlayingRole(bytes32 spec) external view override returns (bool) {
return core.isPlayingRole(spec);
}

/// @dev Returns the role address that is associated to `spec`.
/// @notice If `component` has not been granted the role with `spec`, returns 0x00 address.
function getRole(bytes32 spec) external override returns (address) {
return core.getRole(spec);
}

/// @dev Sets a `team` to the `component` to indicate entering a context.
/// @notice If another `team` has already been set, it gets overridden.
function activateCompartment(address team) external override {
core.activateCompartment(team);
}

/// @dev Unsets the `team` if there is one activated.
function deactivateCompartment() external override {
core.deactivateCompartment();
}

/// @dev Returns the `address` of the activated `team`.
function getActiveCompartment() external view override returns (address) {
return core.getActiveCompartment();
}

function hasBeenPlayedRuntime(address _contractAddress, string calldata _functionName) external override returns (bool) {
return core.hasBeenPlayedRuntime(_contractAddress, _functionName);
}
}
