const { ethers } = require("hardhat");

async function deploy(name, args = []) {
  const F = await ethers.getContractFactory(name);
  const c = await F.deploy(...args);
  await c.deployed();
  const r = await c.deployTransaction.wait();
  return { c, gas: r.gasUsed.toNumber() };
}

async function gas(promise) {
  const tx = await promise;
  const r = await tx.wait();
  return r.gasUsed.toNumber();
}

async function main() {
  const signers = await ethers.getSigners();
  const owner = signers[0];

  console.log("\n===== A. BASELINE COMPARISON (hand-written vs SocialRbSC) =====");

  // --- Role binding ---
  const baseReg = await deploy("BaselineRoleRegistry");
  const regBind = await gas(baseReg.c.bind(ethers.utils.formatBytes32String("DeliveryProvider")));

  const mission = await deploy("MedicalSupplyMission");
  const socBind = await gas(mission.c.bindAgentToRole(signers[1].address, "DeliveryProvider"));

  // --- FSM lifecycle ---
  const baseFsm = await deploy("BaselineFSM");
  const fsmGas =
    (await gas(baseFsm.c.accept())) +
    (await gas(baseFsm.c.ship())) +
    (await gas(baseFsm.c.deliver()));

  const role = await deploy("DeliveryProviderRole", [mission.c.address, signers[1].address]);
  const roleGas =
    (await gas(role.c.connect(signers[1]).acceptDelivery([]))) +
    (await gas(role.c.connect(signers[1]).updateStatus("safe_zone"))) +
    (await gas(role.c.connect(signers[1]).completeDelivery()));

  console.log(`Role binding  : baseline=${regBind}  socialrbsc=${socBind}  delta=${socBind - regBind}`);
  console.log(`FSM lifecycle : baseline=${fsmGas}  socialrbsc=${roleGas}  delta=${roleGas - fsmGas}`);
  console.log(`Deploy        : baseline-registry=${baseReg.gas}  baseline-fsm=${baseFsm.gas}  compartment=${mission.gas}  role-behavior=${role.gas}`);

  console.log("\n===== B. END-TO-END MEDICAL SUPPLY LIFECYCLE =====");
  const comp = await deploy("MedicalSupplyMission");
  const mediator = await deploy("contracts/paper_snippets/ConcreteMediator.sol:ConcreteMediator");
  const roleC = await deploy("DeliveryProviderRole", [comp.c.address, signers[1].address]);
  const deployGas = comp.gas + mediator.gas + roleC.gas;

  const steps = [];
  steps.push(["bind UAV (DeliveryProvider)", await gas(comp.c.bindAgentToRole(signers[1].address, "DeliveryProvider"))]);
  steps.push(["bind Validator", await gas(comp.c.bindAgentToRole(signers[2].address, "Validator"))]);
  steps.push(["mediator: bind UAV", await gas(mediator.c.bindAgent(signers[1].address, "UAVDeliveryAgent"))]);
  steps.push(["UAV: status -> IN_TRANSIT", await gas(comp.c.connect(signers[1]).updateDeliveryStatus(signers[1].address, 2, "safe_zone"))]);
  steps.push(["UAV: status -> DELIVERED", await gas(comp.c.connect(signers[1]).updateDeliveryStatus(signers[1].address, 3, "safe_zone"))]);
  steps.push(["Validator: confirm", await gas(comp.c.connect(signers[2]).confirmDelivery(signers[1].address, "0x01"))]);

  let opTotal = 0;
  for (const [n, g] of steps) { console.log(`  ${n}: ${g}`); opTotal += g; }
  console.log(`Deployments total: ${deployGas}`);
  console.log(`Operations total: ${opTotal}`);
  console.log(`E2E total gas: ${deployGas + opTotal}`);

  console.log("\n===== C. SCALABILITY (bind N role players) =====");
  const Ns = [1, 5, 10, 25, 50];
  for (const N of Ns) {
    const m = await deploy("MedicalSupplyMission");
    let total = m.gas;
    let last = 0;
    for (let i = 0; i < N; i++) {
      const w = ethers.Wallet.createRandom().connect(ethers.provider);
      await (await owner.sendTransaction({ to: w.address, value: ethers.utils.parseEther("1") })).wait();
      last = await gas(m.c.bindAgentToRole(w.address, "DeliveryProvider"));
      total += last;
    }
    console.log(`N=${N}: last-binding=${last}  deploy=${m.gas}  total=${total}  avg-per-binding=${Math.round((total - m.gas) / N)}`);
  }

  console.log("\n===== D. MULTI-AGENT / MULTI-ROLE COORDINATION =====");
  for (const A of [1, 5, 10]) {
    const m = await deploy("MedicalSupplyMission");
    const providers = [];
    const validators = [];
    for (let i = 0; i < A; i++) {
      const w = ethers.Wallet.createRandom().connect(ethers.provider);
      await (await owner.sendTransaction({ to: w.address, value: ethers.utils.parseEther("1") })).wait();
      providers.push(w);
    }
    for (let i = 0; i < A; i++) {
      const w = ethers.Wallet.createRandom().connect(ethers.provider);
      await (await owner.sendTransaction({ to: w.address, value: ethers.utils.parseEther("1") })).wait();
      validators.push(w);
    }
    let bindGas = 0;
    for (const p of providers) bindGas += await gas(m.c.bindAgentToRole(p.address, "DeliveryProvider"));
    for (const v of validators) bindGas += await gas(m.c.bindAgentToRole(v.address, "Validator"));
    let cycleGas = 0;
    for (let i = 0; i < A; i++) {
      cycleGas += await gas(m.c.connect(providers[i]).updateDeliveryStatus(providers[i].address, 2, "safe_zone"));
      cycleGas += await gas(m.c.connect(providers[i]).updateDeliveryStatus(providers[i].address, 3, "safe_zone"));
      cycleGas += await gas(m.c.connect(validators[i]).confirmDelivery(providers[i].address, "0x01"));
    }
    const total = m.gas + bindGas + cycleGas;
    console.log(`A=${A}: agents=${2 * A} deploy=${m.gas} bind=${bindGas} cycle=${cycleGas} total=${total} per-agent-cycle=${Math.round((bindGas + cycleGas) / A)}`);
  }

  console.log("\n===== E. GOAL-PLAN-TREE SCALING =====");
  for (const T of [10, 50, 100, 500, 1000]) {
    const gpt = await deploy("GoalPlanTreeBenchmark");
    const w = ethers.utils.formatBytes32String;
    let addGas = 0;
    for (let i = 0; i < T; i++) {
      const parent = i === 0 ? ethers.constants.HashZero : w("g" + ((i - 1) >> 1));
      const leaf = 2 * i + 1 >= T && 2 * i + 2 >= T;
      addGas += await gas(gpt.c.addNode(w("g" + i), parent, leaf, w("a" + i)));
    }
    const travGas = await gas(gpt.c.traverseAll());
    console.log(`T=${T}: addTotal=${addGas} addPerNode=${Math.round(addGas / T)} traverse=${travGas} traversePerNode=${Math.round(travGas / T)}`);
  }
}

main().then(() => process.exit(0)).catch((e) => { console.error(e); process.exit(1); });
