// SPDX-License-Identifier: MIT
// pragma solidity 0.8.13;
pragma solidity 0.8.13;

import "./ComponentRole.sol";
import "../staticroleassignmentinitiator/RoleCreator.sol";
import "../interfaceseparator/InterfaceIds.sol";
import "../interfaceseparator/ERC165.sol";
import "../interfaceseparator/ERC165Query.sol";

abstract contract Compartment is ERC165, ERC165Query {
    /// @dev hashtable for roles in ComponentRole addresses
    mapping(bytes32 => ComponentRole) public roles;
    /// @dev hashtable for role creators in RoleCreator smart contract addresses
    mapping(bytes32 => RoleCreator) public roleCreators;
    /// @dev id of the compartment to be activated
    bytes32 private CompartmentID;

    // MARK: ERC165

    /// @notice Query if a contract implements an interface
    /// @param interfaceID The interface identifier, as specified in ERC-165
    /// @dev Interface identification is specified in ERC-165. This function
    ///  uses less than 30,000 gas.
    /// @return `true` if the contract implements `interfaceID` and
    ///  `interfaceID` is not 0xffffffff, `false` otherwise
    function supportsInterface(
        bytes4 interfaceID
    ) external pure override virtual returns (bool) {
        return
            interfaceID == 0x01ffc9a7 || // ERC165
            interfaceID == InterfaceIds.COMPARTMENT_ID; // Compartment.sol
    }

    /// @dev Adds a role that should be created from `spec`.
    /// Returns the `address` of the newly created role instance.
    function addRole(bytes32 _spec) external returns (address) {
        RoleCreator rc = roleCreators[_spec];
        ComponentRole role = rc.createFor(_spec);
        bool isComponentRole = doesContractImplementInterface(
            address(role),
            InterfaceIds.COMPONENT_ROLE_ID
        );
        require(isComponentRole, "Doesn't support Compartment interface.");

        if (role != ComponentRole(address(0))) {
            roles[_spec] = role;
            role.setCompartment(address(this));
        }
        return address(role);
    }

    /// @dev Removes role with `_roleAddress` from `compartment`. If `compartment` does not have the role with `_roleAddress`, do nothing.
    function removeRole(bytes32 _roleAddress) external virtual {
        if (!this.hasRole(_roleAddress)) return;
        ComponentRole componentRole = roles[_roleAddress];
        componentRole.setCore(address(0));
        componentRole.setCompartment(address(0));
        delete (roles[_roleAddress]);
    }

    /**
     * @dev Returns `true` if `compartment` has role with `_roleAddress`.
     */
    function hasRole(bytes32 _roleAddress) external view returns (bool) {
        return this.getRole(_roleAddress) != address(0);
    }

    /**
     * @dev Returns the role address that is associated to `_roleAddress`.
     *
     * If `compartment` does not have the role with `_roleAddress`, returns 0x00 address.
     */
    function getRole(bytes32 _roleAddress) external view virtual returns (address) {
        return address(roles[_roleAddress]);
    }

    /**
     * @dev Add a concrete `roleCreator` which is associated to a `spec`.
     */
    function addRoleCreator(bytes32 _roleAddress, RoleCreator _roleCreator) external {
        roleCreators[_roleAddress] = _roleCreator;
    }
    /// @dev getCompartmentID that returns the unique id of Compartment.
    function getCompartmentID() public view returns(bytes32){
        return CompartmentID;
    }
    /// @dev create a compartment ID with a string _message
    function createCompartmentID(string memory _message) public {
        CompartmentID = keccak256(bytes(_message));
    }

}



