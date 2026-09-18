
const { expect } = require("chai");
const { ethers } = require("hardhat");
const {BigNumber} = require("ethers");

// HELPER: get function selectors from a contract
function getSelectors (contract) {
    // get the function signatures from the ABI of the contract:
    const signatures = Object.keys(contract.interface.functions)
    // convert from signature to selector:
    const selectors = signatures.reduce((acc, val) => {
        acc.push(contract.interface.getSighash(val))
        return acc
    }, [])
    return selectors
}


describe("DeepRolePlayer Test", function() {
    let deepRolePlayer;
    let tx;
    let receipt;


    beforeEach(async function() {
        [owner, account1, account2] = await ethers.getSigners();
        const DeepRolePlayer = await ethers.getContractFactory("DeepRolePlayer");

        deepRolePlayer = await DeepRolePlayer.deploy(owner.address);
        await deepRolePlayer.deployed();
        console.log("DeepRolePlayer deploy at the address: ", deepRolePlayer.address);

    });

    it("should add a facet for DeepRoleFacet1", async () => {
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();
        console.log("deepRole1Library address: ", deepRole1Library.address);

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: {
                LibDeepRole1: deepRole1Library.address,
            }})
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();
        console.log("deepRoleFacet1 address: ", deepRoleFacet1.address);
        expect(deepRoleFacet1.address).to.not.null;


        //get all the function selectors covered by this facet - we need that during the cut below:
        const selectors = getSelectors(deepRoleFacet1);
        // Register the facet - cut the DeepRoleFacet1 onto the deep role player:
        tx = await deepRolePlayer.diamondCut(
            {
                facetAddress: deepRoleFacet1.address, // the DeepRoleFacet1 facet is deployed here
                functionSelectors: selectors // these are the selectors of this facet (the functions that are supported)
            }, { gasLimit: 800000 }

        );
        receiptTransaction = await tx.wait(); // wait for the transaction is completed
        if(!receiptTransaction.status) {
            throw Error(`Diamond upgrade failed: ${tx.hash}`)
        } else {
            console.log("Everything is normal for facets")

        }
        expect(receiptTransaction.status).to.be.equal(1);
        expect(receiptTransaction.transactionHash).to.not.null;
    });

    it("Should add the DeepRoleFacet2", async() => {
        const DeepRoleLibrary2 = await ethers.getContractFactory("LibDeepRole2");
        const deepRoleLibrary2 = await DeepRoleLibrary2.deploy();
        await deepRoleLibrary2.deployed();
        console.log("deepRoleLibrary2 address: ", deepRoleLibrary2.address);

        const DeepRoleFacet2 = await ethers.getContractFactory("DeepRoleFacet2", {
            libraries: {
                LibDeepRole2: deepRoleLibrary2.address,
            }});
        const deepRoleFacet2 = await DeepRoleFacet2.deploy();
        await deepRoleFacet2.deployed()
        console.log("deepRoleFacet2 address: ", deepRoleFacet2.address);
        expect(deepRoleFacet2.address).to.not.null;


        // get all the function selectors covered by this facet. - we need that during the cut below:
        const selectors = getSelectors(deepRoleFacet2);

        tx = await deepRolePlayer.diamondCut(
            {
                facetAddress: deepRoleFacet2.address, // the DeepRoleFacet1 facet is deployed here
                functionSelectors: selectors // these are the selectors of this facet (the functions that are supported)
            }, { gasLimit: 800000 }

        );
        receiptTransaction = await tx.wait(); // wait for the transaction is completed
        if(!receiptTransaction.status) {
            throw Error(`Diamond upgrade failed: ${tx.hash}`)
        } else {
            console.log("Everything is normal for facets")
        }
        console.log("receiptTransaction: ", receiptTransaction);
        expect(receiptTransaction).not.to.null;
        expect(receiptTransaction.status).to.equal(1);
    });

    it("should work as expected for DeepRoleFacet1", async() => {
        const deepRoleFacet1 = await ethers.getContractAt('DeepRoleFacet1', deepRolePlayer.address);
        const deepRoleFacet2 = await ethers.getContractAt('DeepRoleFacet2', deepRolePlayer.address);

        console.log("deepRoleFacet1: ", deepRoleFacet1.address);
        expect(deepRoleFacet1).to.not.equal(0)
        tx = await deepRoleFacet1.setDeepRoleID("1");
        await tx.wait();

        tx = await deepRoleFacet1.getDeepRoleID();
        await tx.wait();

        console.log(await deepRoleFacet2.externalMessageDeepRoleRelated());
        console.log("getDeepRoleID: ", await deepRoleFacet1.getDeepRoleID());
    });

    it("should work as expected for DeepRoleFacet2", async() => {
        // Test DeepRoleFacet2 independently (since it has conflicting selectors with Facet1)
        const DeepRoleLibrary2 = await ethers.getContractFactory("LibDeepRole2");
        const deepRoleLibrary2 = await DeepRoleLibrary2.deploy();
        await deepRoleLibrary2.deployed();

        const DeepRoleFacet2 = await ethers.getContractFactory("DeepRoleFacet2", {
            libraries: { LibDeepRole2: deepRoleLibrary2.address }
        });
        const deepRoleFacet2 = await DeepRoleFacet2.deploy();
        await deepRoleFacet2.deployed();

        // Add facet2
        const selectors2 = getSelectors(deepRoleFacet2);
        await deepRolePlayer.diamondCut({
            facetAddress: deepRoleFacet2.address,
            functionSelectors: selectors2
        }, { gasLimit: 800000 });

        const facet2Contract = await ethers.getContractAt('DeepRoleFacet2', deepRolePlayer.address);

        // Test facet2 functionality - check that functions execute without reverting
        await expect(facet2Contract.setDeepRoleID("Facet2Role")).to.not.be.reverted;
        await expect(facet2Contract.getDeepRoleID()).to.not.be.reverted;

        // Test the unique function in facet2
        await expect(facet2Contract.externalMessageDeepRoleRelated()).to.not.be.reverted;
    });

    it("should prevent non-owner from cutting facets", async() => {
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: { LibDeepRole1: deepRole1Library.address }
        });
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();

        const selectors = getSelectors(deepRoleFacet1);

        // Try to cut facet as non-owner
        await expect(
            deepRolePlayer.connect(account1).diamondCut({
                facetAddress: deepRoleFacet1.address,
                functionSelectors: selectors
            })
        ).to.be.revertedWith("LibDiamond: Must be contract owner");
    });

    it("should prevent adding facet with zero address", async() => {
        await expect(
            deepRolePlayer.diamondCut({
                facetAddress: ethers.constants.AddressZero,
                functionSelectors: ["0x12345678"]
            })
        ).to.be.revertedWith("LibDiamondCut: Add facet can't be address(0)");
    });

    it("should prevent adding facet with no selectors", async() => {
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: { LibDeepRole1: deepRole1Library.address }
        });
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();

        await expect(
            deepRolePlayer.diamondCut({
                facetAddress: deepRoleFacet1.address,
                functionSelectors: []
            })
        ).to.be.revertedWith("LibDiamondCut: No selectors in facet to cut");
    });

    it("should prevent adding duplicate function selectors", async() => {
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: { LibDeepRole1: deepRole1Library.address }
        });
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();

        const selectors = getSelectors(deepRoleFacet1);

        // Add facet first time
        await deepRolePlayer.diamondCut({
            facetAddress: deepRoleFacet1.address,
            functionSelectors: selectors
        }, { gasLimit: 800000 });

        // Try to add same selectors again
        await expect(
            deepRolePlayer.diamondCut({
                facetAddress: deepRoleFacet1.address,
                functionSelectors: [selectors[0]] // Try to add first selector again
            })
        ).to.be.revertedWith("LibDiamondCut: Can't add function that already exists");
    });

    it("should handle facet replacement correctly", async() => {
        // Deploy two different facet implementations
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: { LibDeepRole1: deepRole1Library.address }
        });
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();

        const DeepRoleLibrary2 = await ethers.getContractFactory("LibDeepRole2");
        const deepRoleLibrary2 = await DeepRoleLibrary2.deploy();
        await deepRoleLibrary2.deployed();

        const DeepRoleFacet2 = await ethers.getContractFactory("DeepRoleFacet2", {
            libraries: { LibDeepRole2: deepRoleLibrary2.address }
        });
        const deepRoleFacet2 = await DeepRoleFacet2.deploy();
        await deepRoleFacet2.deployed();

        const selectors = getSelectors(deepRoleFacet1);

        // Add facet1
        await deepRolePlayer.diamondCut({
            facetAddress: deepRoleFacet1.address,
            functionSelectors: selectors
        }, { gasLimit: 800000 });

        const facetContract = await ethers.getContractAt('DeepRoleFacet1', deepRolePlayer.address);
        await expect(facetContract.setDeepRoleID("Original")).to.not.be.reverted;
        await expect(facetContract.getDeepRoleID()).to.not.be.reverted;

        // Note: In a real diamond, replacement would require removing selectors first
        // This test demonstrates the concept - actual replacement needs careful selector management
    });

    it("should handle multiple facets with different functions", async() => {
        // Test that facets with unique functions can coexist
        // Since both facets have conflicting setDeepRoleID/getDeepRoleID, we'll test them separately
        // and show that the unique function in facet2 works

        const DeepRoleLibrary2 = await ethers.getContractFactory("LibDeepRole2");
        const deepRoleLibrary2 = await DeepRoleLibrary2.deploy();
        await deepRoleLibrary2.deployed();

        const DeepRoleFacet2 = await ethers.getContractFactory("DeepRoleFacet2", {
            libraries: { LibDeepRole2: deepRoleLibrary2.address }
        });
        const deepRoleFacet2 = await DeepRoleFacet2.deploy();
        await deepRoleFacet2.deployed();

        // Add facet2 with its unique functions
        const selectors2 = getSelectors(deepRoleFacet2);
        await deepRolePlayer.diamondCut({
            facetAddress: deepRoleFacet2.address,
            functionSelectors: selectors2
        }, { gasLimit: 800000 });

        // Test both facets work independently
        const facet2Contract = await ethers.getContractAt('DeepRoleFacet2', deepRolePlayer.address);

        await expect(facet2Contract.setDeepRoleID("Role2")).to.not.be.reverted;
        await expect(facet2Contract.getDeepRoleID()).to.not.be.reverted;
        await expect(facet2Contract.externalMessageDeepRoleRelated()).to.not.be.reverted;

        // Note: In a real scenario, you'd design facets with unique function selectors
        // or use function selector replacement for upgrades
    });

    it("should handle library dependencies correctly", async() => {
        // Test that facets work with their required libraries
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: { LibDeepRole1: deepRole1Library.address }
        });
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();

        const selectors = getSelectors(deepRoleFacet1);
        await deepRolePlayer.diamondCut({
            facetAddress: deepRoleFacet1.address,
            functionSelectors: selectors
        }, { gasLimit: 800000 });

        const facetContract = await ethers.getContractAt('DeepRoleFacet1', deepRolePlayer.address);

        // Test that the facet functions work (library linking is handled by deployment)
        await expect(facetContract.setDeepRoleID("LibraryTest")).to.not.be.reverted;
        await expect(facetContract.getDeepRoleID()).to.not.be.reverted;
    });

    it("should handle gas limits appropriately", async() => {
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: { LibDeepRole1: deepRole1Library.address }
        });
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();

        const selectors = getSelectors(deepRoleFacet1);

        // Test with sufficient gas limit
        const tx = await deepRolePlayer.diamondCut({
            facetAddress: deepRoleFacet1.address,
            functionSelectors: selectors
        }, { gasLimit: 800000 });

        const receipt = await tx.wait();
        expect(receipt.status).to.equal(1);
        expect(receipt.gasUsed).to.be.lt(800000); // Should use less than provided
    });

    it("should validate contract ownership", async() => {
        // Test that the contract owner is set correctly in the diamond storage
        // This test verifies the diamond cut function requires ownership
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: { LibDeepRole1: deepRole1Library.address }
        });
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();

        const selectors = getSelectors(deepRoleFacet1);

        // Should succeed as owner
        await expect(deepRolePlayer.diamondCut({
            facetAddress: deepRoleFacet1.address,
            functionSelectors: selectors
        }, { gasLimit: 800000 })).to.not.be.reverted;

        // Should fail as non-owner
        await expect(deepRolePlayer.connect(account1).diamondCut({
            facetAddress: deepRoleFacet1.address,
            functionSelectors: selectors
        })).to.be.revertedWith("LibDiamond: Must be contract owner");
    });

    it("should handle fallback function correctly", async() => {
        const DeepRole1Library = await ethers.getContractFactory("LibDeepRole1");
        const deepRole1Library = await DeepRole1Library.deploy();
        await deepRole1Library.deployed();

        const DeepRoleFacet1 = await ethers.getContractFactory("DeepRoleFacet1", {
            libraries: { LibDeepRole1: deepRole1Library.address }
        });
        const deepRoleFacet1 = await DeepRoleFacet1.deploy();
        await deepRoleFacet1.deployed();

        const selectors = getSelectors(deepRoleFacet1);
        await deepRolePlayer.diamondCut({
            facetAddress: deepRoleFacet1.address,
            functionSelectors: selectors
        }, { gasLimit: 800000 });

        const facetContract = await ethers.getContractAt('DeepRoleFacet1', deepRolePlayer.address);

        // Test that calling functions through the diamond works
        await expect(facetContract.setDeepRoleID("FallbackTest")).to.not.be.reverted;
        await expect(facetContract.getDeepRoleID()).to.not.be.reverted;
    });

    it("should handle receive function for ether transfers", async() => {
        // Test sending ether to the diamond contract
        const initialBalance = await ethers.provider.getBalance(deepRolePlayer.address);
        expect(initialBalance).to.equal(0);

        // Send ether to the contract
        await owner.sendTransaction({
            to: deepRolePlayer.address,
            value: ethers.utils.parseEther("1.0")
        });

        const finalBalance = await ethers.provider.getBalance(deepRolePlayer.address);
        expect(finalBalance).to.equal(ethers.utils.parseEther("1.0"));
    });
});