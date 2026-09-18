// const { ethers } = require("hardhat");
// const hre = require("hardhat");
//
// async function main() {
//     console.log("Retailer main deploy function");
//
//     const Retailer = await ethers.getContractFactory("Retailer");
//     // console.log(Retailer);
//     const retailer = await Retailer.deploy();
//     await retailer.deployed();
//     console.log("deployed to retailer address: " + retailer.address);
// }
//
// main().catch((error) => {
//     console.error(error);
//     process.exitCode = 1;
// });
//
//