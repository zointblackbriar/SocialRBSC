// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/// Reduced on-chain goal--plan-tree micro-benchmark.
///
/// It mirrors the child-linked-list structure and bounded depth-first search of
/// the SocialRbSC framework's GoalPlanTree contract, without the surrounding
/// framework contracts, so that the cost of storing and searching a
/// goal--plan tree can be measured as a function of its size. It is a
/// benchmark artifact, not part of the language runtime.
contract GoalPlanTreeBenchmark {

    struct Node {
        bytes32 goalId;
        bytes32 firstChild;
        bytes32 nextSibling;
        bool isLeaf;
        bytes32 action;
    }

    mapping(bytes32 => Node) public nodes;
    bytes32 public root;
    uint256 public nodeCount;
    uint256 public lastVisited;

    event NodeAdded(bytes32 indexed id, bytes32 indexed parent, bool isLeaf);

    function addNode(
        bytes32 id,
        bytes32 parent,
        bool isLeaf,
        bytes32 action
    ) external {
        require(nodes[id].goalId == bytes32(0), "node exists");
        Node storage n = nodes[id];
        n.goalId = id;
        n.isLeaf = isLeaf;
        n.action = action;
        if (parent == bytes32(0)) {
            require(root == bytes32(0), "root exists");
            root = id;
        } else {
            n.nextSibling = nodes[parent].firstChild;
            nodes[parent].firstChild = id;
        }
        nodeCount += 1;
        emit NodeAdded(id, parent, isLeaf);
    }

    /// Bounded depth-first search for the first leaf. Realises the paper's
    /// requirement that plan search is bounded so that gas stays predictable.
    function selectPlan(uint256 maxVisits)
        external
        view
        returns (bytes32 action, uint256 visited)
    {
        bytes32[] memory stack = new bytes32[](maxVisits + 1);
        uint256 sp = 0;
        bytes32 current = root;
        while (current != bytes32(0) || sp > 0) {
            while (current != bytes32(0) && visited < maxVisits) {
                Node storage n = nodes[current];
                visited += 1;
                if (n.isLeaf) {
                    return (n.action, visited);
                }
                stack[sp++] = n.nextSibling;
                current = n.firstChild;
            }
            if (sp == 0) {
                break;
            }
            current = stack[--sp];
        }
        return (bytes32(0), visited);
    }

    /// Full traversal (visit every node once) used to measure search cost as a
    /// function of tree size. Non-view so that its gas can be measured.
    function traverseAll() external returns (uint256 visited) {
        bytes32[] memory stack = new bytes32[](nodeCount + 1);
        uint256 sp = 0;
        bytes32 current = root;
        while (current != bytes32(0) || sp > 0) {
            while (current != bytes32(0)) {
                Node storage n = nodes[current];
                visited += 1;
                stack[sp++] = n.nextSibling;
                current = n.firstChild;
            }
            if (sp == 0) {
                break;
            }
            current = stack[--sp];
        }
        lastVisited = visited;
    }
}
