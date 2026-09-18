/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.socialagentengine;

import java.math.BigInteger;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import de.tudresden.codegenerator.autogen.ConcreteMediator;
import de.tudresden.codegenerator.autogen.SocialAgent;
import de.tudresden.codegenerator.autogen.SocialAgentBelief;
import de.tudresden.codegenerator.autogen.SocialAgentDeliberationCycle;
import de.tudresden.codegenerator.autogen.SocialAgentDesire;
import de.tudresden.codegenerator.autogen.SocialAgentIntention;
import de.tudresden.codegenerator.autogen.SocialAgentStateMachine;
import de.tudresden.codegenerator.autogen.Society;
import de.tudresden.codegenerator.autogen.Utils;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class DeliberationCycleIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static Web3j web3j;

    /**
     * TODO DOCUMENT ME!
     */
    private static SocialAgentIntention socialAgentIntention;

    /**
     * TODO DOCUMENT ME!
     */
    private static SocialAgent socialAgent;

    /**
     * TODO DOCUMENT ME!
     */
    private static SocialAgentDeliberationCycle socialAgentDeliberationCycle;

    /**
     * TODO DOCUMENT ME!
     */
    private static Utils utils;

    /**
     * TODO DOCUMENT ME!
     */
    private static ConcreteMediator concreteMediator;

    /**
     * TODO DOCUMENT ME!
     */
    private static Society society1;

    /**
     * TODO DOCUMENT ME!
     */
    private static Society society2;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        // Connect to a local Ethereum node (e.g., Ganache, Infura)
        web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // get the transaction manager
        TransactionManager transactionManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());

        // get gas provider
        DefaultGasProvider gasProvider = new DefaultGasProvider();

        // Deploy the SocialAgentDeliberationCycle contract
        SocialAgentStateMachine socialAgentStateMachine = SocialAgentStateMachine
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();
        society1 = Society.deploy(web3j, transactionManager, gasProvider).send();
        society2 = Society.deploy(web3j, transactionManager, gasProvider).send();

        socialAgent = SocialAgent.deploy(web3j, transactionManager, gasProvider).send();
        concreteMediator = ConcreteMediator.deploy(web3j, transactionManager, gasProvider).send();
        utils = Utils.deploy(web3j, transactionManager, gasProvider).send();

        SocialAgentDesire socialAgentDesire = SocialAgentDesire.deploy(web3j, transactionManager, gasProvider).send();
        socialAgentIntention = SocialAgentIntention
                    .deploy(web3j, transactionManager, gasProvider, socialAgentDesire.getContractAddress(),
                    utils.getContractAddress())
                    .send();

        SocialAgentBelief socialAgentBelief = SocialAgentBelief.deploy(web3j, transactionManager, gasProvider).send();

        // address payable _socialAgentStateMachineAddress, address payable _society,
        // address _socialAgentAddress, address _socialAgentIntentionAddress, address _socialAgentBeliefAddress
        socialAgentDeliberationCycle = de.tudresden.codegenerator.autogen.SocialAgentDeliberationCycle
                    .deploy(web3j, transactionManager, gasProvider, socialAgentStateMachine.getContractAddress(),
                        society1.getContractAddress(), socialAgent.getContractAddress(),
                        socialAgentIntention.getContractAddress(), socialAgentBelief.getContractAddress())
                    .send();

        // TransactionReceipt receipt = deliberationCycle
        // .add("nodeName", "parentName", "data", BigInteger.valueOf(1), BigInteger.valueOf(5))
        // .send();

    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testAddMessage() throws Exception {
        // Test the communication routing function
        socialAgentDeliberationCycle.addMessage("Agent1", "Agent2", "TestMessage").send();

        // Retrieve the message and handle the List<Object> type that Web3j returns
        List<SocialAgentDeliberationCycle.Message> messages = socialAgentDeliberationCycle.getMessage("Agent1").send();
        System.out.println("messageType:" + messages.get(0).messageType.toString());
        System.out.println("messages sender:" + messages.get(0).sender.toString());
        System.out.println("messages receiver:" + messages.get(0).receiver.toString());

        // Output or assert the values for testing
        Assert.assertEquals("Agent1", messages.get(0).sender);
        Assert.assertEquals("Agent2", messages.get(0).receiver);
        Assert.assertEquals("TestMessage", messages.get(0).messageType);
        // }
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testAllocateSocialGoals() throws Exception {
        // Call the allocateSocialGoals function with a specific goal
        TransactionReceipt receipt = socialAgentDeliberationCycle.allocateSocialGoals(BigInteger.valueOf(1)).send();
        Assert.assertTrue(receipt.isStatusOK());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testDeliberationCycle() throws Exception {
        socialAgent.createAgent("sampleagent1").send();
        socialAgent
                .socialAgentBindToSociety(society1.getContractAddress(), concreteMediator.getContractAddress(),
                socialAgent.getSocialAgentName().send())
                .send();

        String addressOfAgent1 = concreteMediator.getAgentFromSociety(socialAgent.getSocialAgentName().send()).send();
        System.out.println("addressOfAgent1: " + addressOfAgent1); // address of the agent in the society
        Assert.assertNotNull(addressOfAgent1);

        socialAgent.createAgent("sampleagent2").send();
        socialAgent
                .socialAgentBindToSociety(society2.getContractAddress(), concreteMediator.getContractAddress(),
                socialAgent.getSocialAgentName().send())
                .send();

        String addressOfAgent2 = concreteMediator.getAgentFromSociety(socialAgent.getSocialAgentName().send()).send();
        System.out.println("addressOfAgent2: " + addressOfAgent2); // address of the agent in the society
        Assert.assertNotNull(addressOfAgent2);

        Assert.assertNotEquals(society1.getContractAddress(), concreteMediator.getContractAddress());
        Assert.assertNotEquals(concreteMediator.getContractAddress(), addressOfAgent1);
        Assert.assertNotEquals(addressOfAgent2, addressOfAgent1); // These agents in different societies

        // Before calling deliberationCycle, add an intention to the stack
        socialAgentDeliberationCycle
                .setIntentionsBeforeDeliberationCycle(socialAgentIntention.getContractAddress(), "SampleIntention")
                .send();

        // Add a sample intention before calling deliberationCycle
        socialAgentDeliberationCycle
                .setIntentionsBeforeDeliberationCycle(socialAgentIntention.getContractAddress(), "TestIntention")
                .send();

        // Check the size of the intention stack to ensure it has at least one element
        BigInteger stackSize = socialAgentIntention.getIntentionStackSize(addressOfAgent1).send();
        System.out.println("stack size: " + stackSize);
        Assert.assertEquals(stackSize, BigInteger.valueOf(0));

        socialAgentDeliberationCycle.setIntentionsBeforeDeliberationCycle(addressOfAgent1, "MyIntention1Agent1").send();
        socialAgentDeliberationCycle.setIntentionsBeforeDeliberationCycle(addressOfAgent1, "MyIntention1Agent1").send();
        socialAgentDeliberationCycle.setIntentionsBeforeDeliberationCycle(addressOfAgent2, "MyIntention2Agent2").send();

        BigInteger stackSizeAgent1 = socialAgentIntention.getIntentionStackSize(addressOfAgent1).send();
        System.out.println("stack size stackSizeAgent1: " + stackSizeAgent1);
        Assert.assertEquals(stackSizeAgent1, BigInteger.valueOf(2));

        BigInteger stackSizeAgent2 = socialAgentIntention.getIntentionStackSize(addressOfAgent2).send();
        System.out.println("stack size stackSizeAgent2: " + stackSizeAgent2);
        Assert.assertEquals(stackSizeAgent2, BigInteger.valueOf(1));

        List<?> agentIntentions = socialAgentIntention.getAgentIntentions(addressOfAgent2).send();

        for (int i = 0; i < agentIntentions.size(); i++) {
            System.out.println("agent2 intentions:" + agentIntentions.get(i));
        }
        // function deliberationCycle(string memory _nameOfAgent, string memory _nameOfSociety,
        // address _addressOfIntentions, uint _indexOfAgentIntention, string memory _intentionName,
        // string memory _metamodelName, string memory _socialAgentBeliefName) external {

        String intentionName = "SampleIntention";
        String metamodelName = "NameofClassOrInterfaceorType";
        String agentName = "Agent1";
        String societyName = "Society1";
        String beliefName = "Belief1";

        // Call the deliberationCycle function and verify the results
        socialAgentDeliberationCycle
                .deliberationCycle(agentName, societyName, addressOfAgent1, BigInteger.valueOf(0), intentionName, metamodelName,
                beliefName)
                .send();

        // Verify if deliberationCycleResult is set to true
        Boolean result = socialAgentDeliberationCycle.deliberationCycleResult().send(); // statevariable check
        Assert.assertTrue(result);
    }
}
