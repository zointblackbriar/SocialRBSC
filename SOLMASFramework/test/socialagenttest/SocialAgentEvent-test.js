const {BigNumber} = require( "ethers");

const {expect} = require('chai');
const {ethers} = require('hardhat');


describe("EventQueue test for dynamic deployment", function () {
    let owner, addr1;
    let eventContract;
    beforeEach(async function() {
        [owner, addr1] = await ethers.getSigners();
        const EventContract = await ethers.getContractFactory("EventSingleEndedQueue");
        eventContract = await EventContract.deploy();
        await eventContract.deployed();
    });

    it("contract call revert test", async function () {
        await expect(eventContract.connect(owner)).not.to.be.reverted;
    });

    it("should enqueue and dequeue uint data correctly", async function () {
        await eventContract.enqueue(42);
        await eventContract.enqueue(43);
        await eventContract.dequeue();
        expect(await eventContract.dataUint()).to.equal(42, "Dequeued data should match");
        await eventContract.dequeue();
        expect(await eventContract.dataUint()).to.equal(43, "Dequeued data should match");
    });

    it("should enqueue events and uint ", async function () {
        await eventContract.enqueueEvent("sample goal1");
        await eventContract.enqueueEvent("sample goal2");
        await eventContract.dequeueEvent();
        expect(await eventContract.dataString()).to.equal("sample goal1", "Dequeued data should match");
        await eventContract.dequeueEvent();
        expect(await eventContract.dataString()).to.equal("sample goal2", "Dequeued another data should match");
    });

    it("should equal rear and entrance values", async function() {
        await eventContract.enqueue(42);
        const rear = await eventContract.rear();
        expect(rear).to.equal(1, "Rear value should match");
        await eventContract.dequeue();
        const entrance = await eventContract.entrance();
        expect(entrance).to.equal(2, "Entrance value should match");
    });

    it("should enqueue and dequeue string data correctly", async function () {
        await eventContract.enqueueEvent("Message1");
        await eventContract.enqueueEvent("Message2");
        const dequeuedData = await eventContract.dequeueEvent();
        expect(await eventContract.dataString()).to.equal("Message1", "Dequeued data should match");
        const dequeuedData2 = await eventContract.dequeueEvent();
        expect(await eventContract.dataString()).to.equal("Message2", "Dequeued another data should match");
    });

    it("should run with the test function", async function () {
        // console.log("owner address: " + owner.address);
        console.log(await eventContract.testMessage());
        await eventContract.testFunction("sample message");
        expect(await eventContract.testMessage()).to.equal("sample message", "state variable should  hold the message");
    });

});
