// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0;

import "truffle/Assert.sol";
import "../contracts/staticroleassignment/InterfaceIds.sol";
import "../contracts/roleinitiator/InitiatorCompartment.sol";

contract TestCompartment_ERC165 {

    InitiatorCompartment internal compartment;

    function beforeEach() public {
        compartment = new InitiatorCompartment();
    }

    function testSupportsErc165() public {
        Assert.isTrue(compartment.supportsInterface(0x01ffc9a7), "");
    }

    function testSupportsTeamInterface() public {
        Assert.isTrue(compartment.supportsInterface(InterfaceIds.COMPARTMENT_ID), "");
    }

    function testDoesNotSupportComponentRoleInterfaceId() public {
        Assert.isFalse(compartment.supportsInterface(InterfaceIds.COMPONENT_ROLE_ID), "");
    }

    function testDoesNotSupportComponentInterfaceId() public {
        Assert.isFalse(compartment.supportsInterface(InterfaceIds.COMPONENT_ID), "");
    }

    function testDoesNotSupportRandomInterfaceIds() public {
        Assert.isFalse(compartment.supportsInterface(0x01ffc9a8), "");
    }
}
