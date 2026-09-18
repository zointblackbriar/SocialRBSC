// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

/**
 * © 2022-2025 Orcun Oruc
 * Generated from: UAVDeliveryAgentRole.java
 * Generated with: SocialRbSC Annotation Processor
 */

/**
 * UAV (Unmanned Aerial Vehicle) Delivery Agent Contract
 * Generated from @Role annotation on UAVDeliveryAgentRole class
 * 
 * State Variables from @StateVariable:
 * - batteryLevel
 * - payloadCapacity
 * - currentRoute
 * 
 * Agent State from @SocialAgentState:
 * - agentState
 */
contract UAVDeliveryAgent {
    
    //~ Events ------------------------------------------------
    
    event DeliveryStarted(
        address indexed uav,
        string destination,
        uint256 batteryLevel,
        uint256 timestamp
    );
    
    event DeliveryCompleted(
        address indexed uav,
        string destination,
        bool successful,
        uint256 timestamp
    );
    
    event RouteRecalculated(
        address indexed uav,
        string alternateRoute,
        uint256 batteryLevel,
        uint256 timestamp
    );
    
    event EnvironmentalConditionsCheck(
        address indexed uav,
        bool favorable,
        string weatherStatus
    );
    
    event BatteryUpdated(
        address indexed uav,
        uint256 newBatteryLevel,
        uint256 timestamp
    );
    
    //~ State Variables ----------------------------------------
    
    /// @dev Current battery level (StateVariable) - 0-100 %
    uint256 public batteryLevel;
    
    /// @dev Payload capacity in kilograms (StateVariable)
    uint256 public payloadCapacity;
    
    /// @dev Current route designation (StateVariable)
    string public currentRoute;
    
    /// @dev Agent behavioral state (SocialAgentState)
    string public agentState;
    
    /// @dev UAV identifier
    string public uavId;
    
    /// @dev Delivery tracking
    uint256 public deliveriesCompleted;
    uint256 public deliveriesFailed;
    
    /// @dev Environmental conditions
    bool public lastWeatherCheck;
    
    /// @dev Manager address
    address public manager;
    
    //~ Structures ---------------------------------------------
    
    struct DeliveryAssignment {
        string requestId;
        string supplyType;
        string destination;
        string urgencyLevel;
        uint256 timestamp;
    }
    
    struct DeliveryRecord {
        string requestId;
        string destination;
        uint256 startTime;
        uint256 endTime;
        bool successful;
        uint256 batteryUsed;
    }
    
    mapping(uint256 => DeliveryRecord) public deliveryHistory;
    uint256 public deliveryCount;
    
    //~ Constructor -------------------------------------------
    
    constructor(string memory _uavId) {
        uavId = _uavId;
        batteryLevel = 85;
        payloadCapacity = 10;
        currentRoute = "NONE";
        agentState = "READY_FOR_DISPATCH";
        manager = msg.sender;
        deliveriesCompleted = 0;
        deliveriesFailed = 0;
    }
    
    //~ Modifiers---------------------------------------------
    
    /// @dev Precondition: assignment != null && batteryLevel > 20.0f
    modifier requireDeliveryCapable() {
        require(
            batteryLevel > 20,
            "UAVDeliveryAgent: Battery level too low for delivery"
        );
        _;
    }
    
    modifier onlyManager() {
        require(
            msg.sender == manager,
            "UAVDeliveryAgent: Only manager can call this"
        );
        _;
    }
    
    //~ Methods -----------------------------------------------
    
    /**
     * Execute delivery assignment
     * Generated from @RoleMethod executeDelivery
     * 
     * Adaptively handles environmental conditions and reroutes if necessary
     * 
     * Precondition: assignment != null && batteryLevel > 20.0f
     */
    function executeDelivery(DeliveryAssignment calldata assignment)
        external
        requireDeliveryCapable
        onlyManager
        returns (bool success)
    {
        require(
            bytes(assignment.requestId).length > 0,
            "UAVDeliveryAgent: Invalid assignment"
        );
        
        // Set current route
        currentRoute = assignment.destination;
        agentState = "IN_FLIGHT";
        
        // Check environmental conditions
        bool conditionsOk = checkEnvironmentalConditions();
        
        if (!conditionsOk) {
            // Initiate reroute protocol
            emit EnvironmentalConditionsCheck(msg.sender, false, "ADVERSE_WEATHER");
            success = recalculateRoute(assignment);
        } else {
            emit EnvironmentalConditionsCheck(msg.sender, true, "FAVORABLE");
            
            // Consume battery for delivery execution
            uint256 batteryConsumption = 15; // 15% per delivery
            require(
                batteryLevel >= batteryConsumption,
                "UAVDeliveryAgent: Insufficient battery"
            );
            
            batteryLevel -= batteryConsumption;
            
            // Record successful delivery
            recordDelivery(assignment, true, batteryConsumption);
            deliveriesCompleted++;
            
            agentState = "DELIVERY_COMPLETE";
            
            emit DeliveryCompleted(
                msg.sender,
                assignment.destination,
                true,
                block.timestamp
            );
            
            emit BatteryUpdated(msg.sender, batteryLevel, block.timestamp);
            
            success = true;
        }
        
        return success;
    }
    
    /**
     * Recalculate route during deliberation cycle
     * Generated from @RoleMethod recalculateRoute
     * 
     * Checks airspace, battery reserves, identifies alternate landing zones
     */
    function recalculateRoute(DeliveryAssignment calldata assignment)
        public
        onlyManager
        returns (bool success)
    {
        // Deliberation process
        // 1. Check airspace restrictions
        // 2. Calculate battery reserves
        // 3. Identify alternate landing zones
        
        if (batteryLevel > 30) {
            // Update belief about environmental conditions
            currentRoute = string(abi.encodePacked("ALTERNATE_", assignment.destination));
            agentState = "REROUTED";
            
            emit RouteRecalculated(
                msg.sender,
                currentRoute,
                batteryLevel,
                block.timestamp
            );
            
            return true;
        }
        
        agentState = "DELIVERY_FAILED";
        deliveriesFailed++;
        
        emit DeliveryCompleted(
            msg.sender,
            assignment.destination,
            false,
            block.timestamp
        );
        
        return false;
    }
    
    /**
     * Check environmental conditions for flight
     * Returns true if conditions are favorable
     */
    function checkEnvironmentalConditions()
        public
        onlyManager
        returns (bool favorable)
    {
        // Simulate environmental check with 70% success rate
        // In production, this would use oracle for real weather data
        favorable = (block.timestamp % 10) > 3; // 70% favorable
        lastWeatherCheck = favorable;
        return favorable;
    }
    
    /**
     * Update battery status
     */
    function updateBatteryStatus(uint256 consumption)
        external
        onlyManager
        returns (bool)
    {
        require(
            consumption <= batteryLevel,
            "UAVDeliveryAgent: Insufficient battery for consumption"
        );
        
        batteryLevel -= consumption;
        
        emit BatteryUpdated(msg.sender, batteryLevel, block.timestamp);
        
        return batteryLevel > 0;
    }
    
    /**
     * Record delivery attempt
     */
    function recordDelivery(
        DeliveryAssignment calldata assignment,
        bool successful,
        uint256 batteryUsed
    ) internal {
        deliveryHistory[deliveryCount] = DeliveryRecord({
            requestId: assignment.requestId,
            destination: assignment.destination,
            startTime: block.timestamp,
            endTime: block.timestamp,
            successful: successful,
            batteryUsed: batteryUsed
        });
        deliveryCount++;
    }
    
    //~ View Functions ----------------------------------------
    
    /**
     * Get current battery level
     */
    function getBatteryLevel()
        external
        view
        returns (uint256)
    {
        return batteryLevel;
    }
    
    /**
     * Get current state
     */
    function getState()
        external
        view
        returns (string memory)
    {
        return agentState;
    }
    
    /**
     * Get current route
     */
    function getCurrentRoute()
        external
        view
        returns (string memory)
    {
        return currentRoute;
    }
    
    /**
     * Get delivery statistics
     */
    function getStatistics()
        external
        view
        returns (uint256 completed, uint256 failed, uint256 battery)
    {
        return (deliveriesCompleted, deliveriesFailed, batteryLevel);
    }
    
    /**
     * Get delivery record
     */
    function getDeliveryRecord(uint256 index)
        external
        view
        returns (DeliveryRecord memory)
    {
        require(
            index < deliveryCount,
            "UAVDeliveryAgent: Invalid delivery index"
        );
        return deliveryHistory[index];
    }
}

/**
 * Web3j Interaction Example:
 * 
 * // Load contract
 * Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
 * UAVDeliveryAgent contract = UAVDeliveryAgent.load(contractAddress, web3j, ...);
 * 
 * // Execute delivery
 * DeliveryAssignment assignment = new DeliveryAssignment(
 *     "REQ-001",
 *     "Blood Supplies",
 *     "Hospital A",
 *     "CRITICAL",
 *     System.currentTimeMillis()
 * );
 * 
 * TransactionReceipt receipt = contract.executeDelivery(assignment).send();
 * 
 * // Check state
 * BigInteger battery = contract.getBatteryLevel().send();
 * String state = contract.getState().send();
 */
