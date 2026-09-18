require("@nomicfoundation/hardhat-toolbox");
require("@nomiclabs/hardhat-web3");
require("hardhat-gas-reporter");
require("hardhat-docgen")

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: {
    version: "0.8.13",
    settings: {
      optimizer: {
        enabled: true,
        runs: 200
      }
    },
  },
  // docgen: {
  //   path: './reportsSOLMAS',
  //   clear: true,
  //   runOnCompile: true,
  // },
  mocha: {
    timeout: 100000000
  },

  gasReporter: {
    enabled: true, // gas reporter has been disabled
    currency: "USD",
    outputFile: "gas-report-testcases.txt",
    noColors:true,
    token: "ETH",
  },


  networks: {
    localhost: {
      gasPrice: 470000000000,
      chainId: 43112,
      url: "http://localhost:8545"
    },
    hardhat:
      {
      },
    rinkeby: {
      url: "https://eth-rinkeby.alchemyapi.io/v2/123abc123abc123abc123abc123abcde"
      // accounts: [privateKey1, privateKey2]
    }
  },


  paths: {
    sources: "./contracts",
    tests: "./test",
    cache: "./cache",
    artifacts: "./artifacts"
  },
  
};


