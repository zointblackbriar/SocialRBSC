const { expect } = require("chai");

describe("Util Contract", async  function () {
    let utilscontract;

    beforeEach(async() => {
        UtilsContract = await ethers.getContractFactory("Utils");
        utilscontract = await UtilsContract.deploy();
    });

    it("should produce correct substring when prompted substring function", async() => {
        const str = 'Hello, World';
        const startIndex = 0;
        const endIndex = 4;
        const expectedSubstring = "Hello";
        const secondStr = "Sample Message";
        const startIndexMes = 0;
        const endIndexMes = 5;
        const expectedMessage = "Sample";
        expect(await utilscontract.substring(str, startIndex, endIndex)).to.equal(expectedSubstring);
        expect(await utilscontract.substring(secondStr, startIndexMes, endIndexMes)).to.equal(expectedMessage);
    });

    it("should produce correct byte result", async() => {
        const str = "Hello, World!";
        const expectedBytes32 = '0x48656c6c6f2c20576f726c642100000000000000000000000000000000000000';
        await utilscontract.stringToBytes32(str);
        expect(await utilscontract.resultstringToBytes32()).to.equal(expectedBytes32);
    });

    // Edge Case Tests
    describe("Edge Cases - Utils", function() {
        it("should handle empty string in substring", async() => {
            const emptyStr = "";
            // Empty string with any indices should revert
            await expect(utilscontract.substring(emptyStr, 0, 0))
                .to.be.revertedWith("invalid substring indices");
        });

        it("should handle substring with same start and end index", async() => {
            const str = "Hello";
            const result = await utilscontract.substring(str, 2, 2);
            expect(result).to.equal("l");
        });

        it("should handle substring of entire string", async() => {
            const str = "Complete String";
            const result = await utilscontract.substring(str, 0, str.length - 1);
            expect(result).to.equal(str);
        });

        it("should handle very long strings in substring", async() => {
            const longStr = "A".repeat(1000);
            const result = await utilscontract.substring(longStr, 0, 99);
            expect(result).to.equal("A".repeat(100));
        });

        it("should handle special characters in substring", async() => {
            const specialStr = "Hello!@#$%^&*()";
            const result = await utilscontract.substring(specialStr, 0, 4);
            expect(result).to.equal("Hello");
        });

        it("should handle unicode characters in substring", async() => {
            const unicodeStr = "Hello 世界";
            const result = await utilscontract.substring(unicodeStr, 0, 4);
            expect(result).to.equal("Hello");
        });

        it("should handle substring with zero start index", async() => {
            const str = "Test String";
            const result = await utilscontract.substring(str, 0, 3);
            expect(result).to.equal("Test");
        });

        it("should handle empty string to bytes32", async() => {
            const emptyStr = "";
            await utilscontract.stringToBytes32(emptyStr);
            const result = await utilscontract.resultstringToBytes32();
            expect(result).to.equal('0x' + '0'.repeat(64));
        });

        it("should handle single character to bytes32", async() => {
            const singleChar = "A";
            await utilscontract.stringToBytes32(singleChar);
            const result = await utilscontract.resultstringToBytes32();
            expect(result).to.match(/^0x41/); // 'A' is 0x41
        });

        it("should handle max length string (32 bytes) to bytes32", async() => {
            const maxStr = "A".repeat(32);
            await utilscontract.stringToBytes32(maxStr);
            const result = await utilscontract.resultstringToBytes32();
            expect(result.length).to.equal(66); // 0x + 64 hex characters
        });

        it("should handle numbers in string to bytes32", async() => {
            const numStr = "12345";
            await utilscontract.stringToBytes32(numStr);
            const result = await utilscontract.resultstringToBytes32();
            expect(result).to.not.equal('0x' + '0'.repeat(64));
        });

        it("should handle special characters to bytes32", async() => {
            const specialStr = "!@#$%";
            await utilscontract.stringToBytes32(specialStr);
            const result = await utilscontract.resultstringToBytes32();
            expect(result).to.not.equal('0x' + '0'.repeat(64));
        });

        it("should handle whitespace in substring", async() => {
            const str = "   spaces   ";
            const result = await utilscontract.substring(str, 0, 2);
            expect(result).to.equal("   ");
        });

        it("should handle newlines and tabs in string", async() => {
            const str = "Hello\nWorld\tTest";
            const result = await utilscontract.substring(str, 0, 4);
            expect(result).to.equal("Hello");
        });

        it("should handle consecutive substring calls", async() => {
            const str = "Consecutive Test";
            const result1 = await utilscontract.substring(str, 0, 10);
            const result2 = await utilscontract.substring(str, 12, 15);
            
            expect(result1).to.equal("Consecutive");
            expect(result2).to.equal("Test");
        });

        it("should handle mixed case strings", async() => {
            const mixedStr = "HeLLo WoRLd";
            const result = await utilscontract.substring(mixedStr, 0, 4);
            expect(result).to.equal("HeLLo");
        });

        it("should handle numeric strings", async() => {
            const numericStr = "1234567890";
            const result = await utilscontract.substring(numericStr, 0, 4);
            expect(result).to.equal("12345");
        });

        it("should handle alphanumeric strings", async() => {
            const alphaNumStr = "Test123ABC";
            const result = await utilscontract.substring(alphaNumStr, 4, 9);
            expect(result).to.equal("123ABC");
        });
    });
});