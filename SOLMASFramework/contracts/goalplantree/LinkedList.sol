// SPDX-License-Identifier: MIT
pragma solidity 0.8.13;

contract LinkedList {
    address public recipient;

    struct Node {
        uint256 data;
        address next;
    }
    mapping(address => Node) public nodes; // adding to the nodes

    uint[] public stateVariables; // state variables are going to be used for controlling

    address public head;

    /// @dev Function to insert a new node at the end of the linked list
    function insert(uint256 _data) public {
        Node memory newNode = Node(_data, address(0));

        // if the address is null of head
        if (head == address(0)) {
            // assign the new node address to head node
            head = address(uint160(uint256(keccak256(abi.encode(newNode)))));
        } else {
            // create the current node for blockchain value with head node
            Node storage current = nodes[head];
            // if the next pointer of current node is empty
            while (current.next != address(0)) {
                // next pointer of the current node should be assigned the current node
                current = nodes[current.next];
            }
            // new node will be assigned to next pointer of current node
            current.next = address(uint160(uint256(keccak256(abi.encode(newNode)))));
        }
        // assign the address of new node into the nodes struct
        nodes[address(uint160(uint256(keccak256(abi.encode(newNode)))))] = newNode;
    }

    /// @dev Function to get the data of the nodes in the linked list
    function getData() public returns (uint256[] memory) {
        uint256 count = 0;
        address current = head;
        while (current != address(0)) {
            count++;
            current = nodes[current].next;
        }

        uint256[] memory result = new uint256[](count);
        current = head;
        for (uint256 i = 0; i < count; i++) {
            result[i] = nodes[current].data;
            stateVariables.push(nodes[current].data);
            current = nodes[current].next;
        }
        return result;
    }


    /// @dev Function should be deleted
    function deleteNode(address _addressToBeDeleted) public {
        require(_addressToBeDeleted != address(0), "Invalid address. Please correct the address");
        // head address check for the linked list in solidity
        require(_addressToBeDeleted != head, "Cannot delete the head node");
        address previous = head;
        // register the value into blockchain
        Node storage current = nodes[head];

        while(current.next != _addressToBeDeleted) {
            require(current.next != address(0), "Cannot delete a non-existent node"); // we have reached without finding the target address
            previous = current.next; // update previous node
            current = nodes[current.next]; // one step forward in the list
        }

        nodes[previous].next = nodes[_addressToBeDeleted].next; // update the previous pointer element called next
        // to skip the node to be deleted
        delete nodes[_addressToBeDeleted]; // delete internal information of the target
    }

}



