const { expect } = require("chai");
const { ethers } = require("hardhat");


describe("Machine learning method", function () {
    let contractJaccard;
    let owner, addr1;

    beforeEach(async function () {
        [owner, addr1] = await ethers.getSigners();
        const JaccardSimilarity = await ethers.getContractFactory("JaccardSimilarity");
        contractJaccard = await JaccardSimilarity.deploy();
        await contractJaccard.deployed();

    });

    it("should accordingly find similarity between two strings false situation", async function () {
        // To be fixed because result is not seen.
        // transactionX = await contract.executeJaccardSimilarity("test", "test1231");
        const str1 = 'hello';
        const str2 = 'world';
        await contractJaccard.executeJaccardSimilarity(str1, str2);
        expect(await contractJaccard.result()).not.to.be.equal(0);
    });

    it("should accordingly find similarity between two strings true situation", async function () {
        // To be fixed because result is not seen.
        // transactionX = await contract.executeJaccardSimilarity("test", "test1231");
        const str1 = 'hello';
        const str2 = 'hello';
        await contractJaccard.executeJaccardSimilarity(str1, str2);
        const result = await contractJaccard.result();
        console.log(result);
        expect(result).to.be.equal(ethers.BigNumber.from(1000000000000000000n));
    });

    it("should accordingly find another test case with the same strings", async function () {
        // To be fixed because result is not seen.
        // transactionX = await contract.executeJaccardSimilarity("test", "test1231");
        const str1 = 'sample';
        const str2 = 'sample';
        await contractJaccard.executeJaccardSimilarity(str1, str2);
        const result = await contractJaccard.result();
        console.log(result);
        expect(result).to.be.equal(ethers.BigNumber.from(1000000000000000000n));
    });


    it('should calculate Jaccard similarity correctly with common characters', async function () {
        const str1 = 'hello';
        const str2 = 'help';
        // 625000000000000000; // (2 / 4) * (10^18) = 0.5 * 10^18

        await contractJaccard.executeJaccardSimilarity(str1, str2);
        console.log(await contractJaccard.result());
        expect(await contractJaccard.result()).to.equal(600000000000000000n);
    });

    it('should calculate Jaccard similarity correctly with empty strings', async function () {
        const str1 = '';
        const str2 = '';

        await contractJaccard.executeJaccardSimilarity(str1, str2);
        console.log(await contractJaccard.result());
        expect(await contractJaccard.result()).to.equal(0n);
    });


});