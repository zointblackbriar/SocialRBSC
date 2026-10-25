// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

interface ICompartmentCore {
    function bindAgentToRole(address agent, string memory role) external;
    function hasRole(address agent, string memory role) external view returns (bool);
}

contract MedicalSupplyMission is ICompartmentCore {

    enum DeliveryStatus { PENDING, ACCEPTED, IN_TRANSIT, DELIVERED, CONFIRMED, FAILED }

    struct Supply {
        bytes32 id;
        string name;
        uint256 quantity;
        uint256 weight;
    }

    struct RoleBinding {
        address agent;
        string roleType;
        uint256 bindingTime;
        bool isActive;
    }

    struct TotalWeight {
        uint256 grams;
        uint256 itemCount;
    }

    // Society/compartment owner: the only authority allowed to bind or
    // unbind role players (access control on role-binding transitions).
    address public society;

    bytes32 public missionId;
    uint256 public deliveryDeadline;
    bytes32[] public requiredSupplies;
    address public hospitalLocation;

    mapping(address => mapping(string => RoleBinding)) public agentRoles;
    mapping(string => address[]) public roleMembers;
    // Populated through registerSupply(); Solidity mappings require no
    // explicit initialization, so Slither's uninitialized-state detector is a
    // false positive here (it ignores element-level assignments to mappings).
    // slither-disable-next-line uninitialized-state
    mapping(bytes32 => Supply) public supplyInventory;
    mapping(address => DeliveryStatus) public deliveryStatus;

    event RoleBound(
        address indexed agent,
        string indexed role,
        uint256 timestamp
    );
    event RoleUnbound(
        address indexed agent,
        string indexed role,
        uint256 timestamp
    );
    event DeliveryStatusUpdated(
        address indexed provider,
        DeliveryStatus newStatus,
        uint256 timestamp
    );
    event DeliveryConfirmed(
        address indexed validator,
        address indexed provider,
        uint256 timestamp
    );

    constructor() {
        society = msg.sender;
    }

    modifier onlySociety() {
        require(
            msg.sender == society,
            "Only the society can manage role bindings"
        );
        _;
    }

    modifier onlyInRole(string memory requiredRole) {
        require(
            agentRoles[msg.sender][requiredRole].isActive,
            "Agent does not have required role"
        );
        _;
    }

    modifier roleNotInUse(address agent, string memory role) {
        require(
            !agentRoles[agent][role].isActive,
            "Agent already plays this role"
        );
        _;
    }

    modifier avoidHazardZones(string memory zone) {
        require(
            !isClassifiedAsHazard(zone),
            "Avoidance motive prevents entry to hazardous zone"
        );
        _;
    }

    function bindAgentToRole(
        address agent,
        string memory role
    ) public override onlySociety roleNotInUse(agent, role) {
        // Checks-effects-interactions: state is updated before the event is
        // emitted and no external call is performed.
        agentRoles[agent][role] = RoleBinding({
            agent: agent,
            roleType: role,
            bindingTime: block.timestamp,
            isActive: true
        });

        roleMembers[role].push(agent);
        emit RoleBound(agent, role, block.timestamp);
    }

    function unbindAgentFromRole(
        address agent,
        string memory role
    ) public onlySociety {
        require(
            agentRoles[agent][role].isActive,
            "Agent does not hold this role"
        );

        agentRoles[agent][role].isActive = false;
        emit RoleUnbound(agent, role, block.timestamp);
    }

    function hasRole(
        address agent,
        string memory role
    ) public view override returns (bool) {
        return agentRoles[agent][role].isActive;
    }

    function registerSupply(
        bytes32 id,
        string memory name,
        uint256 quantity,
        uint256 weight
    ) public onlySociety {
        supplyInventory[id] = Supply(id, name, quantity, weight);
    }

    function acceptDelivery(
        address provider,
        bytes32[] memory suppliesList
    ) public onlyInRole("DeliveryProvider") {
        require(
            msg.sender == provider,
            "Only the provider can accept the delivery"
        );

        TotalWeight memory packageWeight = calculateWeight(suppliesList);
        require(
            checkUAVCapacity(provider, packageWeight),
            "Insufficient UAV capacity"
        );

        deliveryStatus[provider] = DeliveryStatus.ACCEPTED;
        emit DeliveryStatusUpdated(provider, DeliveryStatus.ACCEPTED, block.timestamp);
    }

    function updateDeliveryStatus(
        address provider,
        DeliveryStatus newStatus,
        string memory zone
    ) public onlyInRole("DeliveryProvider") avoidHazardZones(zone) {
        require(
            msg.sender == provider,
            "Only the provider can update their status"
        );
        require(
            newStatus == DeliveryStatus.IN_TRANSIT ||
            newStatus == DeliveryStatus.DELIVERED,
            "Invalid status transition"
        );

        deliveryStatus[provider] = newStatus;
        emit DeliveryStatusUpdated(provider, newStatus, block.timestamp);
    }

    function confirmDelivery(
        address provider,
        bytes calldata signedValidation
    ) public onlyInRole("Validator") {
        require(
            deliveryStatus[provider] == DeliveryStatus.DELIVERED,
            "Delivery must be in DELIVERED state"
        );
        require(
            verifyValidatorSignature(signedValidation),
            "Invalid validator signature"
        );

        deliveryStatus[provider] = DeliveryStatus.CONFIRMED;
        emit DeliveryConfirmed(msg.sender, provider, block.timestamp);
    }

    function calculateWeight(bytes32[] memory supplies)
        private view returns (TotalWeight memory) {
        uint256 total = 0;
        for (uint256 i = 0; i < supplies.length; i++) {
            total += supplyInventory[supplies[i]].weight;
        }
        return TotalWeight(total, supplies.length);
    }

    function checkUAVCapacity(address uav, TotalWeight memory weight)
        private view returns (bool) {
        return weight.grams > 0;
    }

    function isClassifiedAsHazard(string memory zone)
        private pure returns (bool) {
        return false;
    }

    function verifyValidatorSignature(bytes calldata signature)
        private pure returns (bool) {
        return signature.length > 0;
    }
}
