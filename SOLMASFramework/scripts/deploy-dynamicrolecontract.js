// const { ethers, upgrades } = require("hardhat");
//
//
// async function main() {
//     const UUPSDynamicRole = await ethers.getContractFactory("UUPSDynamicRole");
//     console.log("Deploying UUPSDynamicRole...");
//
//     const dynamicRole = await upgrades.deployProxy(UUPSDynamicRole, {
//         initializer: "initialize",
//         kind: "uups"
//     });
//     console.log("Dynamic role has been deployed to:", dynamicRole.address);
// }