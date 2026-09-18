// const {ethers} = require('hardhat');
//
// async function main() {
//     console.log('Deploying contracts with the account:', process.env.DEPLOYER_ADDRESS);
//     console.log('Account balance:', (await ethers.provider.getBalance(process.env.DEPLOYER_ADDRESS)).toString());
//     console.log('Account nonce:', (await ethers.provider.getTransactionCount(process.env.DEPLOYER_ADDRESS)).toString());
//     console.log('Account gas limit:', (await ethers.provider.getGasLimit()).toString());
//     console.log('Account gas price:', (await ethers.provider.getGasPrice()).toString());
//     const TestContract = await ethers.getContractFactory("TestContract");
//     const testContract = await TestContract.deploy();
//     await testContract.deployed();
//     console.log('TestContract deployed to:', testContract.address);
// }
//
//
// main().catch((error) => {
//     console.error(error);
//     process.exitCode = 1;
// });
