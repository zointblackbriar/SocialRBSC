const {expect} = require('chai');
const {ethers} = require('hardhat');

describe('Production', function () {
    let production;
    let retailerRole;
    let wholesaleRole;
    let coordinatorRole;
    let droneRole;
    let groundRobotRole;
    let consumerRole;
    let retailerCreator;
    let wholesalerCreator;
    // creators for new actors (not strictly needed but deploy for completeness)
    let coordinatorCreator;
    let droneCreator;
    let groundRobotCreator;
    let consumerCreator;
    let compartment;
    let componentCore;

    beforeEach(async() => {
        const Production = await ethers.getContractFactory('Production');
        production = await Production.deploy();
        await production.deployed(); //contract has been deployed
        const RetailerCreator = await ethers.getContractFactory('RetailerCreator');
        const WholesalerCreator = await ethers.getContractFactory('WholesalerCreator');
        retailerCreator = await RetailerCreator.deploy();
        wholesalerCreator = await WholesalerCreator.deploy();
        await retailerCreator.deployed();
        await wholesalerCreator.deployed();

        const RetailerRole = await ethers.getContractFactory('Retailer');
        const WholesalerRole = await ethers.getContractFactory('Wholesaler');
        retailerRole = await RetailerRole.deploy();
        wholesaleRole = await WholesalerRole.deploy();
        await retailerRole.deployed();
        await wholesaleRole.deployed();

        //Compartment and Component Core
        const ComponentCore = await ethers.getContractFactory('contracts/staticroleassignment/ComponentCore.sol:ComponentCore');
        const Compartment = await ethers.getContractFactory('TemporaryCompartment');
        componentCore = await ComponentCore.deploy();
        compartment = await Compartment.deploy();
        await componentCore.deployed();
        await compartment.deployed();

        // deploy new actor contracts so we can call methods directly
        const CoordinatorRole = await ethers.getContractFactory('Coordinator');
        coordinatorRole = await CoordinatorRole.deploy();
        await coordinatorRole.deployed();

        const DroneAgentRole = await ethers.getContractFactory('DroneAgent');
        droneRole = await DroneAgentRole.deploy();
        await droneRole.deployed();

        const GroundRobotRole = await ethers.getContractFactory('GroundRobot');
        groundRobotRole = await GroundRobotRole.deploy();
        await groundRobotRole.deployed();

        const ConsumerRole = await ethers.getContractFactory('Consumer');
        consumerRole = await ConsumerRole.deploy();
        await consumerRole.deployed();

        // creators for completeness
        const CoordinatorCreator = await ethers.getContractFactory('CoordinatorCreator');
        coordinatorCreator = await CoordinatorCreator.deploy();
        await coordinatorCreator.deployed();

        const DroneCreator = await ethers.getContractFactory('DroneAgentCreator');
        droneCreator = await DroneCreator.deploy();
        await droneCreator.deployed();

        const GroundRobotCreator = await ethers.getContractFactory('GroundRobotCreator');
        groundRobotCreator = await GroundRobotCreator.deploy();
        await groundRobotCreator.deployed();

        const ConsumerCreator = await ethers.getContractFactory('ConsumerCreator');
        consumerCreator = await ConsumerCreator.deploy();
        await consumerCreator.deployed();
    });

    it('should return retailer, wholesaler and new actor roles from production', async function () {
        await componentCore.activateCompartment(compartment.address);
        let resultActiveCompartment = await componentCore.getActiveCompartment();
        expect(resultActiveCompartment).to.not.equal(ethers.constants.AddressZero);
        await componentCore.deactivateCompartment();
        resultActiveCompartment = await componentCore.getActiveCompartment();
        expect(resultActiveCompartment).to.equal(ethers.constants.AddressZero);
        await componentCore.activateCompartment(compartment.address);
        await retailerRole.setCompartment(compartment.address);
        await retailerRole.setCore(componentCore.address);

        // getters should return valid addresses
        let rAddr = await production.getRetailerRole();
        let wAddr = await production.getWholesalerRole();
        let cAddr = await production.getCoordinatorRole();
        let dAddr = await production.getDroneAgentRole();
        let gAddr = await production.getGroundRobotRole();
        let uAddr = await production.getConsumerRole();

        expect(rAddr).to.not.equal(ethers.constants.AddressZero);
        expect(wAddr).to.not.equal(ethers.constants.AddressZero);
        expect(cAddr).to.not.equal(ethers.constants.AddressZero);
        expect(dAddr).to.not.equal(ethers.constants.AddressZero);
        expect(gAddr).to.not.equal(ethers.constants.AddressZero);
        expect(uAddr).to.not.equal(ethers.constants.AddressZero);

        expect(retailerRole).to.exist;
        expect(wholesaleRole).to.exist;
    });

    it('should add items to inventory for retailer', async function () {
        const itemToAdd = 10;
        await retailerRole.getInventoryStatus();
        await retailerRole.pushInventory(itemToAdd);
        const inventoryStatus = await retailerRole.getInventoryStatus();
        expect(inventoryStatus).to.equal(itemToAdd);
    });

    it('should remove items from inventory for retailer', async function () {
       const initialInventory = 10;
       const itemToRemove = 5;
       const expectedInventory = initialInventory - itemToRemove;

       await retailerRole.pushInventory(initialInventory);
       await retailerRole.popInventory(itemToRemove);
       const inventoryStatus = await retailerRole.getInventoryStatus();
       expect(inventoryStatus).to.equal(expectedInventory);

    });


    //Wholesaler tests
    it('should add items to inventory for wholesaler', async function () {
        const itemToAdd = 10;
        await wholesaleRole.pushInventory(itemToAdd);
        await wholesaleRole.itemsInInventoryWholeSaler(); // state variable check
        const nextItemAdd = 20;
        await wholesaleRole.pushInventory(nextItemAdd);
        expect(30).to.equal(await wholesaleRole.itemsInInventoryWholeSaler());
    });

    // new actor functionality tests
    it('coordinator can schedule jobs', async function () {
        expect(await coordinatorRole.getStatus()).to.equal(0);
        await coordinatorRole.scheduleJob();
        expect(await coordinatorRole.getStatus()).to.equal(1);
    });

    it('drone agent records deliveries', async function () {
        expect(await droneRole.getDeliveries()).to.equal(0);
        await droneRole.recordDelivery();
        expect(await droneRole.getDeliveries()).to.equal(1);
    });

    it('ground robot records trips', async function () {
        expect(await groundRobotRole.getTrips()).to.equal(0);
        await groundRobotRole.recordTrip();
        expect(await groundRobotRole.getTrips()).to.equal(1);
    });

    it('consumer can submit feedback', async function () {
        expect(await consumerRole.getFeedback()).to.equal(0);
        await consumerRole.submitFeedback(5);
        expect(await consumerRole.getFeedback()).to.equal(5);
    });

    it('should remove items from inventory for wholesaler', async function () {
       const initialInventory = 10;
       const itemToRemove = 5;
       const expectedInventory = initialInventory - itemToRemove;

       await wholesaleRole.pushInventory(initialInventory);
       await wholesaleRole.popInventory(itemToRemove);
       const inventoryStatus = await wholesaleRole.itemsInInventoryWholeSaler(); // state variable check
       expect(inventoryStatus).to.equal(expectedInventory);

    });

    // integration tests for use-case features
    it('retailer can submit a delivery request', async function () {
        const dest = ethers.utils.formatBytes32String('LOC1');
        const window = 123456;
        await retailerRole.submitDeliveryRequest(dest, window);
        const stored = await retailerRole.requests(0);
        expect(stored.destination).to.equal(dest);
        expect(stored.timeWindow).to.equal(window);
    });

    it('wholesaler can submit a bulk manifest', async function () {
        const stops = [ethers.utils.formatBytes32String('S1'), ethers.utils.formatBytes32String('S2')];
        const windows = [111, 222];
        await wholesaleRole.submitBulkManifest(stops, windows);
        const manifest = await wholesaleRole.getManifest(0);
        expect(manifest[0].length).to.equal(2);
        expect(manifest[1][0]).to.equal(windows[0]);
    });
});