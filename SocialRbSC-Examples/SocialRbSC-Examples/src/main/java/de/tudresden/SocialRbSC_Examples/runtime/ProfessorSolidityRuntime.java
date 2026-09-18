// Generated at 2026-04-19T13:42:09.418197200
package de.tudresden.SocialRbSC_Examples.runtime;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import java.util.Map;
import java.util.HashMap;

/**
 * Java-to-Solidity Runtime Environment for Professor
 * Provides bridge between Java objects and deployed smart contracts.
 * Auto-generated from @Deploy annotation scanning.
 */
public class ProfessorSolidityRuntime {

    private Web3j web3j;
    private String nodeUrl = "http://localhost:8545";
    private Map<String, String> contractAddresses = new HashMap<>();

    public ProfessorSolidityRuntime(String nodeUrl) {
        this.nodeUrl = nodeUrl;
        this.web3j = Web3j.build(new HttpService(nodeUrl));
    }

    /**
     * Deploys all contracts to the blockchain.
     */
    public void deployAllContracts() throws Exception {
        deployBuyerRole();
        deployBuyItem();
        deployNotifySeller();
        deployProfessor();
        deployPerformRole();
        deployCheckPreconditions();
        deployApp();
        deployPlanMethod();
        deployAgentPrecondition();
    }

    private void deployBuyerRole() throws Exception {
        // Deploy BuyerRole contract (original elements: BuyerRole) -- detected annotations: Role
    }

    private void deployBuyItem() throws Exception {
        // Deploy buyItem contract (original elements: buyItem) -- detected annotations: Action, WillingnessCondition, Plan, AvoidanceCondition
    }

    private void deployNotifySeller() throws Exception {
        // Deploy notifySeller contract (original elements: notifySeller) -- detected annotations: Action
    }

    private void deployProfessor() throws Exception {
        // Deploy Professor contract (original elements: Professor) -- detected annotations: SocialAgent, Deploy, Society
    }

    private void deployPerformRole() throws Exception {
        // Deploy performRole contract (original elements: performRole) -- detected annotations: RoleMethod
    }

    private void deployCheckPreconditions() throws Exception {
        // Deploy checkPreconditions contract (original elements: checkPreconditions) -- detected annotations: Precondition, PreconditionSocialAgent
    }

    private void deployApp() throws Exception {
        // Deploy App contract (original elements: App) -- detected annotations: SocialAgentPlan
    }

    private void deployPlanMethod() throws Exception {
        // Deploy planMethod contract (original elements: planMethod) -- detected annotations: Plan
    }

    private void deployAgentPrecondition() throws Exception {
        // Deploy agentPrecondition contract (original elements: agentPrecondition) -- detected annotations: PreconditionSocialAgent
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public String getContractAddress(String contractName) {
        return contractAddresses.get(contractName);
    }
}
