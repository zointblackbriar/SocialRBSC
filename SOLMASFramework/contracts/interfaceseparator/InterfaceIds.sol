// SPDX-License-Identifier: AGPL-3.0
// pragma solidity 0.8.13;
pragma solidity 0.8.13;

import "../staticroleassignment/Component.sol";
import "../staticroleassignment/ComponentRole.sol";
import "../staticroleassignment/Compartment.sol";
import "../societypattern/Mediator.sol";

/// @dev InterfaceIds has three parts
library InterfaceIds {
    bytes4 constant COMPONENT_ID = type(Component).interfaceId; // A bytes4 value containing the EIP-165 interface identifier of the given interface i.
    bytes4 constant COMPONENT_ROLE_ID = type(ComponentRole).interfaceId;
    bytes4 constant COMPARTMENT_ID = type(Compartment).interfaceId;
    bytes4 constant MEDIATOR_ID = type(Mediator).interfaceId;
}



