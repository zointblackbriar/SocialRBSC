# Solidity API

## TestContract

### stateVariable

```solidity
string stateVariable
```

### constructor

```solidity
constructor() public
```

### getString

```solidity
function getString() public view returns (string)
```

### setString

```solidity
function setString(string setStateVariable) public
```

## BaseRole

### hashValueOfConstructor

```solidity
bytes4 hashValueOfConstructor
```

### owner

```solidity
address owner
```

### myUint

```solidity
uint256 myUint
```

### constructor1

```solidity
function constructor1() public
```

### increment

```solidity
function increment() public
```

### calculateConstructorHash

```solidity
function calculateConstructorHash(string constructorFunc) external returns (bytes4)
```

### initialize

```solidity
function initialize() public
```

### doDelegateCall

```solidity
function doDelegateCall() public
```

## DeepRole

### getMessage

```solidity
function getMessage() public pure returns (string)
```

### getAddress

```solidity
function getAddress() public view returns (address)
```

## DeepRoleV1

### updateCode

```solidity
function updateCode(address newCode) public
```

### onlyOwner

```solidity
modifier onlyOwner()
```

## DelegateCallExtend

### setDelegateCallUintParam

```solidity
function setDelegateCallUintParam(address _contract, string functionName, uint256 _numberparam) public payable
```

### setDelegateCallStringParam

```solidity
function setDelegateCallStringParam(address _contract, string functionName, string _message) public payable
```

## Dispatcher

### constructor

```solidity
constructor(address target) public
```

### initialize

```solidity
function initialize() public pure
```

### fallback

```solidity
fallback() external
```

## Proxiable

### updateCodeAddress

```solidity
function updateCodeAddress(address newAddress) internal
```

### proxiableUUID

```solidity
function proxiableUUID() public pure returns (bytes32)
```

## Proxy

### constructor

```solidity
constructor(bytes constructData, address contractLogic) public
```

### fallback

```solidity
fallback() external payable
```

## Upgradeable

### _sizes

```solidity
mapping(bytes4 => uint32) _sizes
```

### _dest

```solidity
address _dest
```

### initialize

```solidity
function initialize() public virtual
```

### replace

```solidity
function replace(address target) public
```

## EventSchema

### Logging

```solidity
event Logging(string definition, uint256 value)
```

### callEventAction

```solidity
function callEventAction(string definition, uint256 value) public
```

### getEventSchemaObject

```solidity
function getEventSchemaObject(address _addr) public returns (contract EventSchema eventSchema)
```

## JaccardSimilarity

### result

```solidity
uint256 result
```

### output

```solidity
string output
```

### testFunction

```solidity
function testFunction() public returns (string)
```

### executeJaccardSimilarity

```solidity
function executeJaccardSimilarity(string str1, string str2) public returns (uint256)
```

## DecisionTreeBasedBDI

### Goal

```solidity
struct Goal {
  string name;
  struct DecisionTreeBasedBDI.Plan[] plans;
}
```

### Plan

```solidity
struct Plan {
  string name;
  uint256 weight;
  struct DecisionTreeBasedBDI.Goal[] subgoals;
}
```

### goals

```solidity
struct DecisionTreeBasedBDI.Goal[] goals
```

### constructor

```solidity
constructor() public
```

## DirectoryFacilitator

### constructor

```solidity
constructor() public
```

### Agent

```solidity
struct Agent {
  address agentAddress;
  string serviceName;
  string capabilities;
}
```

### agents

```solidity
mapping(address => struct DirectoryFacilitator.Agent) agents
```

### registerAgent

```solidity
function registerAgent(string _serviceName, string _capabilities) external
```

### getAgent

```solidity
function getAgent(address _agentAddress) external view returns (string, string)
```

## GoalTree

### Node

```solidity
struct Node {
  string name;
  string parent;
  string data;
  bytes32[] nodes;
}
```

### nodes

```solidity
mapping(bytes32 => struct GoalTree.Node) nodes
```

### get

```solidity
function get(string _name, string _parent) public view returns (string, string, string, bytes32[])
```

### add

```solidity
function add(string _name, string _parent, string _data) public
```

### update

```solidity
function update(string _name, string _parent, string _data) public
```

### getIndex

```solidity
function getIndex(bytes32[] childs, bytes32 _path) internal pure returns (uint256)
```

### remove

```solidity
function remove(string _name, string _parent) public
```

### string2bytes32

```solidity
function string2bytes32(string _source) public pure returns (bytes32 result)
```

## SocialAgentBelief

### beliefowner

```solidity
address beliefowner
```

### beliefModifier

```solidity
modifier beliefModifier()
```

### Belief

```solidity
struct Belief {
  bytes32 name;
  bytes32 value;
}
```

### beliefs

```solidity
mapping(bytes32 => struct SocialAgentBelief.Belief) beliefs
```

### beliefNames

```solidity
bytes32[] beliefNames
```

### capability

```solidity
address capability
```

### constructor

```solidity
constructor() public
```

### onlyCapability

```solidity
modifier onlyCapability()
```

### addBelief

```solidity
function addBelief(bytes32 name, bytes32 value) public
```

### getBelief

```solidity
function getBelief(bytes32 name) public view returns (bytes32)
```

### updateBelief

```solidity
function updateBelief(bytes32 name, bytes32 value) public
```

### hasBelief

```solidity
function hasBelief(bytes32 name) public view returns (bool)
```

### removeBelief

```solidity
function removeBelief(bytes32 name) public returns (bool)
```

### getBeliefNames

```solidity
function getBeliefNames() public view returns (bytes32[])
```

## SocialAgentDefinition

### setName

```solidity
function setName(string _id) public
```

### setID

```solidity
function setID(string _name) public
```

### getName

```solidity
function getName() public view returns (string)
```

### getID

```solidity
function getID() public view returns (string)
```

## SocialAgentEventDoubleEndedQueue

### deque

```solidity
mapping(uint256 => uint256) deque
```

### first

```solidity
uint256 first
```

### last

```solidity
uint256 last
```

### pushLeft

```solidity
function pushLeft(uint256 _data) public
```

### pushRight

```solidity
function pushRight(uint256 _data) public
```

### popLeft

```solidity
function popLeft() public returns (uint256 _data)
```

### popRight

```solidity
function popRight() public returns (uint256 _data)
```

## SocialAgentEventSingleEndedQueue

### queue

```solidity
mapping(uint256 => uint256) queue
```

### eventqueue

```solidity
mapping(uint256 => string) eventqueue
```

### entrance

```solidity
uint256 entrance
```

### rear

```solidity
uint256 rear
```

### dataString

```solidity
string dataString
```

### dataUint

```solidity
uint256 dataUint
```

### testMessage

```solidity
string testMessage
```

### enqueue

```solidity
function enqueue(uint256 _data) public
```

### dequeue

```solidity
function dequeue() public returns (uint256)
```

### enqueueEvent

```solidity
function enqueueEvent(string _data) public
```

### dequeueEvent

```solidity
function dequeueEvent() public returns (string)
```

### testFunction

```solidity
function testFunction(string _sampleMessage) public returns (string)
```

## SocialGoal

### goalowner

```solidity
address goalowner
```

### agentGoals

```solidity
mapping(address => struct SocialGoal.Goal[]) agentGoals
```

### goalModifier

```solidity
modifier goalModifier()
```

### assignmentOwnerAddress

```solidity
function assignmentOwnerAddress(address ownerAssignment) public
```

### GoalStatus

```solidity
enum GoalStatus {
  Active,
  Completed,
  Failed
}
```

### Goal

```solidity
struct Goal {
  string description;
  enum SocialGoal.GoalStatus status;
}
```

### addGoal

```solidity
function addGoal(string description) public
```

### completeGoal

```solidity
function completeGoal(uint128 goalIndex) public
```

### setGoalStatus

```solidity
function setGoalStatus(struct SocialGoal.Goal sampleGoal, enum SocialGoal.GoalStatus newState) public
```

## SocialIntention

### agentIntentions

```solidity
mapping(address => string) agentIntentions
```

### setIntention

```solidity
function setIntention(string intention) public
```

### executeIntention

```solidity
function executeIntention(string[] roleList) public returns (bool success)
```

## SocialPlan

### PlanStatus

```solidity
enum PlanStatus {
  NotExecuted,
  Executed,
  Succeeded,
  Failed
}
```

### SocialPlan

```solidity
struct SocialPlan {
  string name;
  enum SocialPlan.PlanStatus status;
}
```

### socialPlans

```solidity
mapping(address => mapping(string => struct SocialPlan.SocialPlan)) socialPlans
```

### formulas

```solidity
mapping(address => mapping(string => bool)) formulas
```

### executePlan

```solidity
function executePlan(string planName, enum SocialPlan.PlanStatus status) external
```

### isSocialAgentFormulaTrue

```solidity
function isSocialAgentFormulaTrue(string formulaName, string planName) external returns (bool)
```

### evaluateFormula

```solidity
function evaluateFormula(string formulaName, bool value) external
```

## SocialSubGoal

## Statemachine

### AgentStates

```solidity
enum AgentStates {
  NEWAGENT,
  RUNNABLEAGENT,
  BLOCKEDAGENT,
  TERMINATEDAGENT,
  EXPLORING,
  PLANNING
}
```

### owner

```solidity
address owner
```

### constructor

```solidity
constructor() public
```

### agentState

```solidity
enum Statemachine.AgentStates agentState
```

### agentCreationTime

```solidity
uint256 agentCreationTime
```

### atState

```solidity
modifier atState(enum Statemachine.AgentStates _state)
```

### transitionAgentAfter

```solidity
modifier transitionAgentAfter()
```

### timedTransitions

```solidity
modifier timedTransitions()
```

### doPlan

```solidity
function doPlan() public payable returns (uint256)
```

### agentEnding

```solidity
function agentEnding() public payable
```

### nextState

```solidity
function nextState() public
```

## Utils

### resultstringToBytes32

```solidity
bytes32 resultstringToBytes32
```

### compare

```solidity
function compare(string _firstString, string _secondString) public pure returns (bool)
```

### substring

```solidity
function substring(string _firstString, uint256 _startIndex, uint256 _endIndex) public pure returns (string)
```

### stringToBytes32

```solidity
function stringToBytes32(string str) public returns (bytes32)
```

### stringToBytes32RobustVersion

```solidity
function stringToBytes32RobustVersion(string source) public returns (bytes32 result)
```

### stringToBytes32Hash

```solidity
function stringToBytes32Hash(string str) public returns (bytes32)
```

### toBytes

```solidity
function toBytes(uint256 _input) public returns (bytes _result)
```

## ERC165

### supportsInterface

```solidity
function supportsInterface(bytes4 interfaceID) external pure returns (bool)
```

Query if a contract implements an interface

_Interface identification is specified in ERC-165. This function
 uses less than 30,000 gas._

#### Parameters

| Name | Type | Description |
| ---- | ---- | ----------- |
| interfaceID | bytes4 | The interface identifier, as specified in ERC-165 |

#### Return Values

| Name | Type | Description |
| ---- | ---- | ----------- |
| [0] | bool | `true` if the contract implements `interfaceID` and  `interfaceID` is not 0xffffffff, `false` otherwise |

## ERC165Query

### InvalidID

```solidity
bytes4 InvalidID
```

### ERC165ID

```solidity
bytes4 ERC165ID
```

### doesContractImplementInterface

```solidity
function doesContractImplementInterface(address _contract, bytes4 _interfaceId) internal view returns (bool)
```

### noThrowCall

```solidity
function noThrowCall(address _contract, bytes4 _interfaceId) internal view returns (uint256 success, uint256 result)
```

## Compartment

### roles

```solidity
mapping(bytes32 => contract ComponentRole) roles
```

### roleCreators

```solidity
mapping(bytes32 => contract RoleCreator) roleCreators
```

### supportsInterface

```solidity
function supportsInterface(bytes4 interfaceID) external pure returns (bool)
```

Query if a contract implements an interface

_Interface identification is specified in ERC-165. This function
 uses less than 30,000 gas._

#### Parameters

| Name | Type | Description |
| ---- | ---- | ----------- |
| interfaceID | bytes4 | The interface identifier, as specified in ERC-165 |

#### Return Values

| Name | Type | Description |
| ---- | ---- | ----------- |
| [0] | bool | `true` if the contract implements `interfaceID` and  `interfaceID` is not 0xffffffff, `false` otherwise |

### addRole

```solidity
function addRole(bytes32 _spec) external returns (address)
```

_Adds a role that should be created from `spec`.

Returns the `address` of the newly created role instance._

### removeRole

```solidity
function removeRole(bytes32 _spec) external
```

_Removes role with `spec` from `team`.

If `team` does not have the role with `spec`, does nothing._

### hasRole

```solidity
function hasRole(bytes32 _spec) external view returns (bool)
```

_Returns `true` if `team` has role with `spec`._

### getRole

```solidity
function getRole(bytes32 _spec) external view returns (address)
```

_Returns the role address that is associated to `spec`.

If `team` hdoes not have the role with `spec`, returns 0x00 address._

### addRoleCreator

```solidity
function addRoleCreator(bytes32 _spec, contract RoleCreator _roleCreator) external
```

_Add a concrete `roleCreator` which is associated to a `spec`._

## CompartmentInitiator

## Component

### addRole

```solidity
function addRole(bytes32 _rolespec, address _roleaddress) external
```

_Grants `role` to `component` and associates it to `spec`.
interface functions implicity virtual after 0.8.7;_

### removeRole

```solidity
function removeRole(bytes32 _rolespec) external
```

_Revokes role with `spec` from `component`.
interface functions implicity virtual after 0.8.7;

If `component` has not been granted the role with `spec`, does nothing._

### isPlayingRole

```solidity
function isPlayingRole(bytes32 _rolespec) external view returns (bool)
```

_Returns `true` if `component` has been granted role with `spec`._

### getRole

```solidity
function getRole(bytes32 _spec) external returns (address)
```

_Returns the role address that is associated to `spec`.

If `component` has not been granted the role with `spec`, returns 0x00 address._

### activateCompartment

```solidity
function activateCompartment(address _compartment) external
```

_Sets a `compartment` to the `component` to indicate entering a context.

If another `compartment` has already been set, it gets overridden one._

### deactivateCompartment

```solidity
function deactivateCompartment() external
```

_Unsets the `compartment` if there is one activated._

### getActiveCompartment

```solidity
function getActiveCompartment() external view returns (address)
```

_Returns the `address` of the activated `compartment`._

## ComponentCore

### playedRoles

```solidity
mapping(bytes32 => contract ComponentRole) playedRoles
```

### isComponentRole

```solidity
bool isComponentRole
```

### isCompartment

```solidity
bool isCompartment
```

### compartmentInUse

```solidity
contract Compartment compartmentInUse
```

### supportsInterface

```solidity
function supportsInterface(bytes4 interfaceID) external pure returns (bool)
```

Query if a contract implements an interface

_Interface identification is specified in ERC-165. This function
 uses less than 30,000 gas._

#### Parameters

| Name | Type | Description |
| ---- | ---- | ----------- |
| interfaceID | bytes4 | The interface identifier, as specified in ERC-165 |

#### Return Values

| Name | Type | Description |
| ---- | ---- | ----------- |
| [0] | bool | `true` if the contract implements `interfaceID` and  `interfaceID` is not 0xffffffff, `false` otherwise |

### addRole

```solidity
function addRole(bytes32 spec, address role) external
```

_Grants `role` to `component` and associates it to `spec`._

### removeRole

```solidity
function removeRole(bytes32 spec) external
```

_Revokes role with `spec` from `component`.

If `component` has not been granted the role with `spec`, does nothing._

### isPlayingRole

```solidity
function isPlayingRole(bytes32 spec) external view returns (bool)
```

_Returns `true` if `component` has been granted role with `spec`._

### getRole

```solidity
function getRole(bytes32 spec) external returns (address)
```

_Returns the role address that is associated to `spec`.

If `component` has not been granted the role with `spec`, returns 0x00 address._

### activateCompartment

```solidity
function activateCompartment(address _compartmentAddress) external
```

_Sets a `team` to the `component` to indicate entering a context.

If another `team` has already been set, it gets overriden._

### deactivateCompartment

```solidity
function deactivateCompartment() external
```

_Unsets the `team` if there is one activated._

### getActiveCompartment

```solidity
function getActiveCompartment() external view returns (address)
```

_Returns the `address` of the activated `team`._

## ComponentRole

### core

```solidity
contract ComponentCore core
```

### assignedCompartment

```solidity
contract Compartment assignedCompartment
```

### supportsInterface

```solidity
function supportsInterface(bytes4 interfaceID) external pure returns (bool)
```

Query if a contract implements an interface

_Interface identification is specified in ERC-165. This function
 uses less than 30,000 gas._

#### Parameters

| Name | Type | Description |
| ---- | ---- | ----------- |
| interfaceID | bytes4 | The interface identifier, as specified in ERC-165 |

#### Return Values

| Name | Type | Description |
| ---- | ---- | ----------- |
| [0] | bool | `true` if the contract implements `interfaceID` and  `interfaceID` is not 0xffffffff, `false` otherwise |

### setCore

```solidity
function setCore(address coreAddress) external
```

_Sets the `componentCore` to `role`._

### setCompartment

```solidity
function setCompartment(address compartmentAddress) external
```

_Sets the `team` to `role`._

### onlyWhenActive

```solidity
modifier onlyWhenActive()
```

_Modifier that checks  `componentCore` has been set and
 the core's `team` matches this role's `team`.

Reverts with a descriptive message if the core has not been set.

To be used for all role functions that require the core._

### addRole

```solidity
function addRole(bytes32 spec, address role) external
```

_Grants `role` to `component` and associates it to `spec`._

### removeRole

```solidity
function removeRole(bytes32 spec) external
```

_Revokes role with `spec` from `component`.

If `component` has not been granted the role with `spec`, does nothing._

### isPlayingRole

```solidity
function isPlayingRole(bytes32 spec) external view returns (bool)
```

_Returns `true` if `component` has been granted role with `spec`._

### getRole

```solidity
function getRole(bytes32 spec) external returns (address)
```

_Returns the role address that is associated to `spec`.

If `component` has not been granted the role with `spec`, returns 0x00 address._

### activateCompartment

```solidity
function activateCompartment(address team) external
```

_Sets a `team` to the `component` to indicate entering a context.

If another `team` has already been set, it gets overriden._

### deactivateCompartment

```solidity
function deactivateCompartment() external
```

_Unsets the `team` if there is one activated._

### getActiveCompartment

```solidity
function getActiveCompartment() external view returns (address)
```

_Returns the `address` of the activated `team`._

## InterfaceIds

### COMPONENT_ID

```solidity
bytes4 COMPONENT_ID
```

### COMPONENT_ROLE_ID

```solidity
bytes4 COMPONENT_ROLE_ID
```

### COMPARTMENT_ID

```solidity
bytes4 COMPARTMENT_ID
```

## RoleCreator

### createFor

```solidity
function createFor(bytes32 _spec) external returns (contract ComponentRole)
```

_Creates a concrete instance of a `ComponentRole` from a `spec`._

## FakeRole1Creator

### createFor

```solidity
function createFor(bytes32 spec) external returns (contract ComponentRole)
```

## FakeRole1UpdatedCreator

### createFor

```solidity
function createFor(bytes32 spec) external returns (contract ComponentRole)
```

## FakeRole2Creator

### createFor

```solidity
function createFor(bytes32 spec) external returns (contract ComponentRole)
```

## FakeRole1

### message

```solidity
string message
```

### roleFunction

```solidity
function roleFunction() public
```

## FakeRole1Updated

## FakeRole2

## RoleInitiator

## Production

### retailerRole

```solidity
contract Retailer retailerRole
```

### wholesalerRole

```solidity
contract Wholesaler wholesalerRole
```

### utils

```solidity
contract Utils utils
```

### retailerSpec

```solidity
bytes32 retailerSpec
```

### wholesalerSpec

```solidity
bytes32 wholesalerSpec
```

### getRetailerRole

```solidity
function getRetailerRole() public returns (contract Retailer)
```

### getWholesalerRole

```solidity
function getWholesalerRole() public returns (contract Wholesaler)
```

## Retailer

### itemsInInventoryRetailer

```solidity
uint256 itemsInInventoryRetailer
```

### pushInventory

```solidity
function pushInventory(uint256 _item) public
```

### popInventory

```solidity
function popInventory(uint256 _item) public
```

### getInventoryStatus

```solidity
function getInventoryStatus() public view returns (uint256)
```

## RetailerCreator

### createFor

```solidity
function createFor(bytes32 _spec) public returns (contract ComponentRole)
```

_Creates a concrete instance of a `ComponentRole` from a `spec`._

## TemporaryCompartment

### constructor

```solidity
constructor() public
```

## Wholesaler

### itemsInInventoryWholeSaler

```solidity
uint256 itemsInInventoryWholeSaler
```

### pushInventory

```solidity
function pushInventory(uint256 _item) public
```

### popInventory

```solidity
function popInventory(uint256 _item) public
```

### getInventoryStatus

```solidity
function getInventoryStatus() public view returns (uint256)
```

## WholesalerCreator

### createFor

```solidity
function createFor(bytes32 _spec) external returns (contract ComponentRole)
```

_Creates a concrete instance of a `ComponentRole` from a `spec`._

