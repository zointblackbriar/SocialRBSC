# Sample Hardhat Project

This project demonstrates a basic Hardhat use case. It comes with a sample contract, a test for that contract, and a script that deploys that contract.

Try running some of the following tasks:

```shell
npx hardhat help
npx hardhat compile
npx hardhat test
REPORT_GAS=true npx hardhat test
npx hardhat node
npx hardhat run scripts/deploy-retailercontract.js
```

### APPLICATION REQUIREMENTS

> npm version: 8.5.0
> node version: v16.14.2

### INFORMATION

Deploy the retailer contract

```bash
npx hardhat node
npx hardhat run --network localhost scripts/deploy-retailercontract.js
```

## Configuration Solhint

```bash
npm install --save-dev solhint
```

And then find the module with the following command: 

```bash
./node_modules/.bin/solhint init-config
``` 


Add the following script file to the package.json file: 

```bash
"solhint": "./node_modules/.bin/solhint -f table contracts/**/*.sol"
```

Run the following script for solidity linter

```bash
npm run solhint
```

## Formatting the solidity-mas-framework 

Install prettier plugin for solidity

```bash
npm install --save-dev solhint-plugin-prettier
```

Adding the configuration of script to the package.json 

```bash
  "scripts": {
    "solhint": "./node_modules/.bin/solhint -f table contracts/**/*.sol",
    "prettier:solidity": "./node_modules/.bin/prettier --write contracts/**/*.sol"
  }

```

Run the prettier-plugin as follows: 

```bash
npm run prettier:solidity
```

You can use 

Basic BDI Logic for the implementation: 

```bash 
Abstract BDI Interpreter: 
  initialize_state():
  do 
    options := option-generator(event-queue, B, G, I); 
    selected-options := deliberate(options, B, G, I); 
    update-intentions(selected-options, I); 
    execute(); 
    get-new-external-events();
    drop-successful-attitudes(B, G, I); 
    drop-impossible-attituted(B, G, I); 
  until
  quit
```

(@Source: An Operational Semantics for a PRS-Like Agent Architecture - Wayne Wobcke)


Detailed explanation of the algorithm

The Abstract BDI interpreter is a high-level description of how a Belief-Desire-Intention
(BDI) agent can be implemented. It consists of the following steps: 

1. Initialize the state of the agent: This includes setting the agent's beliefs, desires and intentions.
2. Deliberate over the possible actions: This involves evaluating the options and selecting the one that is most likely to achieve the agent's goals.
3. Update the agent's intentions: The agent's intentions are updated to reflect the option that was selected in the previous step. 
4. Execute the selected option: This involves taking the actions that are necessary to achieve the option.
5. Get new external events: The agent may receive new information from the environment, such as sensor data or message from other agents.
6. Drop successful attitudes: If the agent has achieved one of its goals, it can drop the corresponding belief, desire, or intention.
7. Drop impossible attitudes: If the agent has determined that it is impossible to achieve one of its goals, it can drop the corresponding belief, desire, or intention.
8. Quit: The agent terminates when it has no more goals to achieve. 

Adopted Goals can be implemented through BDI System (Inspired by JADEX Agent System)

Adopted goals are goals that an agent has decided to pursue. They are represented as objects 
in the Jadex System, and have several attributes, including: 

- Goal ID: A unique identifier for the goal. 
- Goal Type: The current state of the goal, which can be one of "active", "suspended", "failed". 
- Goal State: The current state of the goal, which is the state that the agent is trying to achieve. 
- Goal Plan: The plan that the agent is using to achieve the goal. 
- Goal Constraints: Any constraints on the goal, such as time limits or resources. 


