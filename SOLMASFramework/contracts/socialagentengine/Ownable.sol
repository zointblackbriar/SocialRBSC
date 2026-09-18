// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

contract Ownable {
    address private _owner;

    // Modifier to restrict access to only the owner
    modifier onlyOwner() {
        require(msg.sender == _owner, "Ownable: caller is not the owner");
        _;
    }

    // Constructor sets the original owner of the contract to the sender
    constructor() {
        _owner = msg.sender;
    }

    // Function to get the address of the owner
    function owner() public view returns (address) {
        return _owner;
    }

    // Function for the owner to transfer ownership to a new address
    function transferOwnership(address newOwner) public onlyOwner {
        require(newOwner != address(0), "Ownable: new owner is the zero address");
        _owner = newOwner;
    }

    // Function to renounce ownership (leave the contract without an owner)
    function renounceOwnership() public onlyOwner {
        _owner = address(0);
    }
}



