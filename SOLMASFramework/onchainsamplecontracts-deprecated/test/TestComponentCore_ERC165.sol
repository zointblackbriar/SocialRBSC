// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0;

import "truffle/Assert.sol";
import "../contracts/staticroleassignment/ComponentCore.sol";
import "../contracts/staticroleassignment/InterfaceIds.sol";

contract TestComponentCore_ERC165 {

    ComponentCore internal component = new ComponentCore();

    function testSupportsErc165() public {
        Assert.isTrue(component.supportsInterface(0x01ffc9a7), "");
    }

    function testSupportsComponentInterface() public {
        Assert.isTrue(component.supportsInterface(InterfaceIds.COMPONENT_ID), "");
    }

    function testDoesNotSupportComponentRoleInterfaceId() public {
        Assert.isFalse(component.supportsInterface(InterfaceIds.COMPONENT_ROLE_ID), "");
    }

    function testDoesNotSupportTeamInterfaceId() public {
        Assert.isFalse(component.supportsInterface(InterfaceIds.COMPARTMENT_ID), "");
    }

    function testDoesNotSupportRandomInterfaceIds() public {
        Assert.isFalse(component.supportsInterface(0x01ffc9a8), "");
    }
}
