// SPDX-License-Identifier: UNLICENSED
pragma solidity 0.8.13;

import "../../staticroleassignment/ComponentRole.sol";

contract BrandRole is ComponentRole {
    // activate a compartment (i.e. set the team/context) through the core
    function activateCompartmentContract(address compartment) external {
        core.activateCompartment(compartment);
    }

    function deactivateCompartmentContract() external {
        core.deactivateCompartment();
    }
}
