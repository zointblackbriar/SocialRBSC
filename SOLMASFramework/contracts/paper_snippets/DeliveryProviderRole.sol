// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

interface IDeliveryProviderRole {
    function acceptDelivery(bytes32[] memory supplies) external;
    function updateStatus(string memory zone) external;
    function completeDelivery() external;
}

contract DeliveryProviderRole is IDeliveryProviderRole {

    enum DeliveryStatus { PENDING, ACCEPTED, IN_TRANSIT, DELIVERED }

    address public compartment;
    address public uavAgent;
    DeliveryStatus public currentStatus;
    bytes32[] public currentPayload;

    event DeliveryAccepted(
        address indexed agent,
        bytes32[] supplies,
        uint256 timestamp
    );
    event StatusUpdated(
        address indexed agent,
        DeliveryStatus newStatus,
        uint256 timestamp
    );
    event DeliveryCompleted(
        address indexed agent,
        uint256 deliveryTime
    );

    modifier onlyCompartment() {
        require(msg.sender == compartment, "Only compartment can call");
        _;
    }

    modifier onlyAgent() {
        require(msg.sender == uavAgent, "Only assigned agent can call");
        _;
    }

    constructor(address _compartment, address _uavAgent) {
        compartment = _compartment;
        uavAgent = _uavAgent;
        currentStatus = DeliveryStatus.PENDING;
    }

    function acceptDelivery(
        bytes32[] memory supplies
    ) public override onlyAgent {
        require(
            currentStatus == DeliveryStatus.PENDING,
            "Role must be in PENDING state"
        );

        // Precondition check: capacity check
        require(
            checkBatteryCapacity(),
            "Insufficient battery for delivery"
        );

        currentPayload = supplies;
        currentStatus = DeliveryStatus.ACCEPTED;

        emit DeliveryAccepted(msg.sender, supplies, block.timestamp);
    }

    function updateStatus(
        string memory zone
    ) public override onlyAgent {
        require(
            currentStatus == DeliveryStatus.ACCEPTED,
            "Must have accepted delivery first"
        );

        // Motivational constraint: Avoidance of hazardous zones
        require(
            !isHazardZone(zone),
            "Avoidance motive: Cannot enter hazardous zone"
        );

        currentStatus = DeliveryStatus.IN_TRANSIT;
        emit StatusUpdated(msg.sender, DeliveryStatus.IN_TRANSIT, block.timestamp);
    }

    function completeDelivery() public override onlyAgent {
        require(
            currentStatus == DeliveryStatus.IN_TRANSIT,
            "Must be in transit to complete"
        );

        // Postcondition: Record completion timestamp
        currentStatus = DeliveryStatus.DELIVERED;
        emit DeliveryCompleted(msg.sender, block.timestamp);
    }

    // Helper functions for BDI integration (domain-specific logic to be completed per deployment)
    function checkBatteryCapacity() private pure returns (bool) {
        return true;
    }

    function isHazardZone(string memory zone) private pure returns (bool) {
        return false;
    }
}
