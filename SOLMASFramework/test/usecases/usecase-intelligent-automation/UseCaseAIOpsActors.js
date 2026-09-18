const { expect } = require('chai');
const { ethers } = require('hardhat');

// smoke tests for the compartments/roles created for the AIOps use case
// these verify that the artifacts compile and basic methods behave as expected.

describe('AIOps Actors', function () {
    // share references so sub-tests can inspect the same deployments
    let agent, cloud, repo, mon, ticket;

    it('can deploy compartments and return identifiers', async function () {
        const AgentComp = await ethers.getContractFactory('AIOpsAgentCompartment');
        agent = await AgentComp.deploy();
        await agent.deployed();
        expect(await agent.getCompartmentID()).to.equal(ethers.utils.keccak256(ethers.utils.toUtf8Bytes('AIOpsAgent')));

        const CloudComp = await ethers.getContractFactory('CloudInfrastructureCompartment');
        cloud = await CloudComp.deploy();
        await cloud.deployed();
        expect(await cloud.getCompartmentID()).to.equal(ethers.utils.keccak256(ethers.utils.toUtf8Bytes('CloudInfrastructure')));

        const RepoComp = await ethers.getContractFactory('CodeRepositoryCompartment');
        repo = await RepoComp.deploy();
        await repo.deployed();
        expect(await repo.getCompartmentID()).to.equal(ethers.utils.keccak256(ethers.utils.toUtf8Bytes('CodeRepository')));

        const MonComp = await ethers.getContractFactory('MonitoringStackCompartment');
        mon = await MonComp.deploy();
        await mon.deployed();
        expect(await mon.getCompartmentID()).to.equal(ethers.utils.keccak256(ethers.utils.toUtf8Bytes('MonitoringStack')));

        const TicketComp = await ethers.getContractFactory('TicketingSystemCompartment');
        ticket = await TicketComp.deploy();
        await ticket.deployed();
        expect(await ticket.getCompartmentID()).to.equal(ethers.utils.keccak256(ethers.utils.toUtf8Bytes('TicketingSystem')));
    });

    it('can deploy roles and set/get core/compartment', async function () {
        const Human = await ethers.getContractFactory('HumanOperatorRole');
        const human = await Human.deploy();
        await human.deployed();

        const EndUser = await ethers.getContractFactory('EndUserRole');
        const user = await EndUser.deploy();
        await user.deployed();

        const Stakeholder = await ethers.getContractFactory('BusinessStakeholderRole');
        const owner = await Stakeholder.deploy();
        await owner.deployed();

        // none of the setup functions should revert when given zero addresses
        await human.setCore(ethers.constants.AddressZero);
        await human.setCompartment(ethers.constants.AddressZero);
        await user.setCore(ethers.constants.AddressZero);
        await user.setCompartment(ethers.constants.AddressZero);
        await owner.setCore(ethers.constants.AddressZero);
        await owner.setCompartment(ethers.constants.AddressZero);
    });

    describe("Compartment Deployment", function() {
        it("can deploy compartments and return identifiers", async function() {
            expect(await agent.getCompartmentID()).to.equal(ethers.utils.keccak256(ethers.utils.toUtf8Bytes("AIOpsAgent")));
            expect(await cloud.getCompartmentID()).to.equal(
                ethers.utils.keccak256(ethers.utils.toUtf8Bytes("CloudInfrastructure"))
            );
            expect(await repo.getCompartmentID()).to.equal(
                ethers.utils.keccak256(ethers.utils.toUtf8Bytes("CodeRepository"))
            );
            expect(await mon.getCompartmentID()).to.equal(
                ethers.utils.keccak256(ethers.utils.toUtf8Bytes("MonitoringStack"))
            );
            expect(await ticket.getCompartmentID()).to.equal(
                ethers.utils.keccak256(ethers.utils.toUtf8Bytes("TicketingSystem"))
            );
        });

        it("compartment IDs are unique across all compartments", async function() {
            const agentId = await agent.getCompartmentID();
            const cloudId = await cloud.getCompartmentID();
            const repoId = await repo.getCompartmentID();
            const monId = await mon.getCompartmentID();
            const ticketId = await ticket.getCompartmentID();   
            const ids = [agentId, cloudId, repoId, monId, ticketId];
            const uniqueIds = new Set(ids);
            expect(uniqueIds.size).to.equal(ids.length);
        });
    });
});