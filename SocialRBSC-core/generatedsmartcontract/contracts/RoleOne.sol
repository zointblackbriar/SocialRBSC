// SPDX-License-Identifier: MIT


pragma solidity >=0.8.7;

import "../staticroleassignment/ComponentRole.sol";

contract Retailer is ComponentRole {
uint public itemsInInventoryRetailer;

function pushInventory(uint _item) public {
itemsInInventoryRetailer += _item;
}

function popInventory(uint _item) public {
require(
_item < itemsInInventoryRetailer,
"we need to check boundary for items in inventory"
);
itemsInInventoryRetailer -= _item;
}

function getInventoryStatus() public view returns (uint) {
return itemsInInventoryRetailer;
}

function playContractForIdentity() public pure returns (string memory) {
return "it has been played";
}
}
