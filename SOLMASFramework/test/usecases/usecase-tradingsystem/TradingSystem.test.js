const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("Trading System Agents", function () {
    let newsAgent;
    let sectorAgent;
    let execAgent;
    let riskAgent;

    beforeEach(async function () {
        const News = await ethers.getContractFactory("NewsSentimentAgent");
        newsAgent = await News.deploy("NewsAgent");
        await newsAgent.deployed();

        const Sector = await ethers.getContractFactory("SectorAnalysisAgent");
        sectorAgent = await Sector.deploy("SectorAgent");
        await sectorAgent.deployed();

        const Exec = await ethers.getContractFactory("TradeExecutionAgent");
        execAgent = await Exec.deploy("ExecAgent");
        await execAgent.deployed();

        const Risk = await ethers.getContractFactory("RiskManagementAgent");
        riskAgent = await Risk.deploy("RiskAgent");
        await riskAgent.deployed();
    });

    it("news agent should ingest and emit event", async function () {
        const tx = await newsAgent.ingestHeadline("Market up");
        const receipt = await tx.wait();
        expect(receipt.events.some(e => e.event === "NewsIngested")).to.be.true;
    });

    it("sector agent makes recommendation and emits", async function () {
        await sectorAgent.receiveSentiment(ethers.utils.formatBytes32String("AAPL"), 1);
        // use callStatic to read return value without creating a transaction
        const rec = await sectorAgent.callStatic.makeRecommendation(ethers.utils.formatBytes32String("AAPL"));
        expect(rec).to.equal("HOLD");
        // send transaction to produce event as well
        const tx = await sectorAgent.makeRecommendation(ethers.utils.formatBytes32String("AAPL"));
        const receipt = await tx.wait();
        expect(receipt.events.some(e => e.event === "Recommendation")).to.be.true;
    });

    it("execution agent executes trade and emits", async function () {
        const tx = await execAgent.executeTrade(ethers.utils.formatBytes32String("AAPL"), 100, 150);
        const receipt = await tx.wait();
        expect(receipt.events.some(e => e.event === "TradeExecuted")).to.be.true;
    });

    it("risk agent updates profile", async function () {
        const tx = await riskAgent.updateRiskProfile("max 5% airline");
        const receipt = await tx.wait();
        expect(receipt.events.some(e => e.event === "RiskProfileUpdated")).to.be.true;
        const valid = await riskAgent.validateTrade(ethers.utils.formatBytes32String("AAPL"), 10);
        expect(valid).to.be.true;
    });

    it("role contracts deploy successfully", async function () {
        const NewsRole = await ethers.getContractFactory("NewsSentimentRole");
        const newsRole = await NewsRole.deploy();
        await newsRole.deployed();
        expect(newsRole.address).to.properAddress;

        const SectorRole = await ethers.getContractFactory("SectorAnalysisRole");
        const sectorRole = await SectorRole.deploy();
        await sectorRole.deployed();
        expect(sectorRole.address).to.properAddress;
    });

    it("compartments deploy successfully", async function () {
        const NewsComp = await ethers.getContractFactory("NewsSentimentCompartment");
        const newsComp = await NewsComp.deploy();
        await newsComp.deployed();
        expect(newsComp.address).to.properAddress;

        const ExecComp = await ethers.getContractFactory("TradeExecutionCompartment");
        const execComp = await ExecComp.deploy();
        await execComp.deployed();
        expect(execComp.address).to.properAddress;
    });

    it("compartment IDs are nonzero", async function () {
        const Comp = await ethers.getContractFactory("RiskManagementCompartment");
        const comp = await Comp.deploy();
        await comp.deployed();
        expect(await comp.getCompartmentID()).to.not.equal(ethers.constants.HashZero);
    });

    it("basic workflow: news->sector->risk->execution", async function () {
        // news agent generates an alert and sector agent receives sentiment
        await newsAgent.ingestHeadline("Test headline");
        await newsAgent.sendAlert("flagged");
        await sectorAgent.receiveSentiment(ethers.utils.formatBytes32String("AAPL"), 1);

        const rec = await sectorAgent.callStatic.makeRecommendation(ethers.utils.formatBytes32String("AAPL"));
        expect(rec).to.equal("HOLD");
        // send transaction to emit event
        const txRec = await sectorAgent.makeRecommendation(ethers.utils.formatBytes32String("AAPL"));
        const receiptRec = await txRec.wait();
        expect(receiptRec.events.some(e => e.event === "Recommendation")).to.be.true;

        // risk check
        const ok = await riskAgent.validateTrade(ethers.utils.formatBytes32String("AAPL"), 123);
        expect(ok).to.be.true;

        // execution
        const execTx = await execAgent.executeTrade(ethers.utils.formatBytes32String("AAPL"), 50, 200);
        const execReceipt = await execTx.wait();
        expect(execReceipt.events.some(e => e.event === "TradeExecuted")).to.be.true;
    });
});