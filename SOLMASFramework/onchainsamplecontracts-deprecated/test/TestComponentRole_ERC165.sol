// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0;

import "truffle/Assert.sol";
import "../contracts/staticroleassignment/ComponentCore.sol";
import "../contracts/staticroleassignment/InterfaceIds.sol";
import "../contracts/roleinitiator/InitiatorRole.sol";

contract TestComponentRole_ERC165 {

    InitiatorRole1 internal role;

    function beforeEach() public {
        role = new InitiatorRole1();
    }

    function testSupportsErc165() public {
        Assert.isTrue(role.supportsInterface(0x01ffc9a7), "");
    }

    function testSupportsComponentRoleInterface() public {
        Assert.isTrue(role.supportsInterface(InterfaceIds.COMPONENT_ROLE_ID), "");
    }

    function testDoesNotSupportComponentInterfaceId() public {
        Assert.isFalse(role.supportsInterface(InterfaceIds.COMPONENT_ID), "");
    }

    function testDoesNotSupportTeamInterfaceId() public {
        Assert.isFalse(role.supportsInterface(InterfaceIds.COMPARTMENT_ID), "");
    }

    function testDoesNotSupportRandomInterfaceIds() public {
        Assert.isFalse(role.supportsInterface(0x01ffc9a8), "");
    }
}
