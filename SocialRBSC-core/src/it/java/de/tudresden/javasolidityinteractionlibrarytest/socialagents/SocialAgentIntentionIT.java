/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.socialagents;

import java.math.BigInteger;
import java.util.ArrayList;
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
import de.tudresden.codegenerator.autogen.SocialAgentDesire;
import de.tudresden.codegenerator.autogen.SocialAgentIntention;
import de.tudresden.codegenerator.autogen.Society;
import de.tudresden.codegenerator.autogen.Utils;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author Orcun Oruc
 */
public class SocialAgentIntentionIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static Web3j web3j;

    /**
     * Contract object.
     */
    private static SocialAgentIntention socialAgentIntention;

    /**
     * TODO DOCUMENT ME!
     */
    private static Utils utils;

    /**
     * TODO DOCUMENT ME!
     */
    private static SocialAgentDesire socialAgentDesire;

    /**
     * TODO DOCUMENT ME!
     */
    private static TransactionManager transactionManager;

    /**
     * TODO DOCUMENT ME!
     */
    private static DefaultGasProvider gasProvider;

    /**
     * TODO DOCUMENT ME!
     */
    private static SocialAgent socialAgent;

    /**
     * Mediator pattern.
     */
    private static ConcreteMediator concreteMediator;

    /**
     * Society contract object.
     */
    private static Society society;

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
        transactionManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());

        // get gas provider
        gasProvider = new DefaultGasProvider();

        utils = de.tudresden.codegenerator.autogen.Utils
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        concreteMediator = de.tudresden.codegenerator.autogen.ConcreteMediator
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        society = de.tudresden.codegenerator.autogen.Society
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        socialAgent = de.tudresden.codegenerator.autogen.SocialAgent
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        socialAgentDesire = de.tudresden.codegenerator.autogen.SocialAgentDesire
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        socialAgentIntention = de.tudresden.codegenerator.autogen.SocialAgentIntention
                    .deploy(web3j, transactionManager, gasProvider, socialAgentDesire.getContractAddress(),
                    utils.getContractAddress())
                    .send();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testActIntention() throws Exception {
        String currentDesire = "ActiveDesire";
        String response = socialAgentIntention.actIntention(currentDesire).send();
        System.out.println("response from actIntention:  " + response);
        Assert.assertEquals("actIntention should return expected string", "actIntention invoked", response);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testExecuteIntention() throws Exception {
        String intention = "Execute Task";
        String socialAgentName1 = "sampleSocialAgent1";
        String socialAgentName2 = "sampleSocialAgent2";

        socialAgent.createAgent(socialAgentName1).send();
        Assert.assertEquals(socialAgent.getSocialAgentName().send(), "sampleSocialAgent1");

        socialAgent
                .socialAgentBindToSociety(society.getContractAddress(), concreteMediator.getContractAddress(), socialAgentName1)
                .send();

        String adddressOfTheAgent = concreteMediator.getAgentFromSociety(socialAgentName1).send();
        Assert.assertEquals(adddressOfTheAgent, society.getContractAddress());

        String[] socialAgentList = {socialAgentName1, socialAgentName2};

        String metamodelNameDesire = "TestDesire";
        String currentDesire = "ExecuteIntention";

        // Push intention
        socialAgentIntention.pushIntention(adddressOfTheAgent, intention).send();

        // Execute intention
        TransactionReceipt executeReceipt = socialAgentIntention
                    .executeIntention(List.of(socialAgentList), metamodelNameDesire, currentDesire, adddressOfTheAgent)
                    .send();
        Assert.assertNotNull(executeReceipt);
        System.out.println("Intention executed: " + intention);

        // Verify stack is empty after execution
        BigInteger stackSize = socialAgentIntention.getIntentionStackSize(adddressOfTheAgent).send();
        Assert.assertEquals("Stack size should be 0 after execution", BigInteger.ZERO, stackSize);

        // Verify success flag
        boolean success = socialAgentIntention.success().send();
        Assert.assertTrue("Execution should be successful", success);

        // Verify topIntention
        String topIntention = socialAgentIntention.topIntention().send();
        Assert.assertEquals("Top intention should match the executed intention", intention, topIntention);

        String resultDesire = socialAgentDesire.getDesire().send();
        System.out.println("resultDesire: " + resultDesire);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testGetAgentIntentionAtIndex_OutOfBounds() throws Exception {
        // Ensure stack is empty
        String intention = "Execute Task";
        String socialAgentName1 = "sampleSocialAgent1";
        List<String> sampleStringArray = new ArrayList<>();

        socialAgent.createAgent(socialAgentName1).send();
        Assert.assertEquals(socialAgent.getSocialAgentName().send(), "sampleSocialAgent1");

        socialAgent
                .socialAgentBindToSociety(society.getContractAddress(), concreteMediator.getContractAddress(), socialAgentName1)
                .send();

        String adddressOfTheAgent = concreteMediator.getAgentFromSociety(socialAgentName1).send();

        while (socialAgentIntention.getIntentionStackSize(adddressOfTheAgent).send().compareTo(BigInteger.ZERO) > 0) {
            socialAgentIntention.executeIntention(sampleStringArray, "Desire", "CurrentDesire", adddressOfTheAgent).send();
        }

        // Attempt to fetch intention at index 0, should fail
        Exception exception = Assert.assertThrows(Exception.class,
                () -> {socialAgentIntention.getAgentIntentionAtIndex(adddressOfTheAgent, BigInteger.ZERO).send();});

        String expectedMessage = "Intention index out of bounds";
        String actualMessage = exception.getMessage();

        Assert.assertTrue("Exception message should indicate out of bounds", actualMessage.contains(expectedMessage));
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testGetIntentionStackSize() throws Exception {
        String socialAgentName1 = "sampleSocialAgent1";

        socialAgent.createAgent(socialAgentName1).send();
        Assert.assertEquals(socialAgent.getSocialAgentName().send(), "sampleSocialAgent1");
        socialAgent
                .socialAgentBindToSociety(society.getContractAddress(), concreteMediator.getContractAddress(), socialAgentName1)
                .send();

        String adddressOfTheAgent = concreteMediator.getAgentFromSociety(socialAgentName1).send();

        BigInteger initialSize = socialAgentIntention.getIntentionStackSize(adddressOfTheAgent).send();

        // Push two intentions
        socialAgentIntention.pushIntention(adddressOfTheAgent, "Intention1").send();
        socialAgentIntention.pushIntention(adddressOfTheAgent, "Intention2").send();

        BigInteger newSize = socialAgentIntention.getIntentionStackSize(adddressOfTheAgent).send();
        Assert.assertEquals("Stack size should increase by 2", initialSize.add(BigInteger.valueOf(2)), newSize);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testPushAndGetIntention() throws Exception {
        String intention1 = "Learn Web3j";
        String intention2 = "Learn Solidity";

        // String agentAddress = credentials.getAddress();
        String socialAgentName = "sampleSocialAgent";
        socialAgent.createAgent(socialAgentName).send();
        Assert.assertEquals(socialAgent.getSocialAgentName().send(), "sampleSocialAgent");

        socialAgent
                .socialAgentBindToSociety(society.getContractAddress(), concreteMediator.getContractAddress(), socialAgentName)
                .send();

        String adddressOfTheAgent = concreteMediator.getAgentFromSociety(socialAgentName).send();

        // Push intention
        TransactionReceipt pushReceipt1 = socialAgentIntention.pushIntention(adddressOfTheAgent, intention1).send();
        Assert.assertNotNull(pushReceipt1);

        TransactionReceipt pushReceipt2 = socialAgentIntention.pushIntention(adddressOfTheAgent, intention2).send();
        Assert.assertNotNull(pushReceipt2);

        Assert.assertEquals("Stack size should be 1", BigInteger.TWO,
            socialAgentIntention.getIntentionStackSize(adddressOfTheAgent).send());

        // Get all intentions
        List<?> intentions = socialAgentIntention.getAgentIntentions(adddressOfTheAgent).send();
        Assert.assertEquals("There should be one intention", 2, intentions.size());

        for (int i = 0; i < intentions.size(); i++) {
            System.out.println(intentions.get(i));
        }

        Assert.assertEquals("Intention should match", intention1, intentions.get(0));
        Assert.assertEquals("Intention should match", intention2, intentions.get(1));

        // Get intention at index
        String fetchedIntention1 = socialAgentIntention.getAgentIntentionAtIndex(adddressOfTheAgent, BigInteger.ZERO).send();
        Assert.assertEquals("fetchedIntention1 should match", intention1, fetchedIntention1);

        String fetchedIntention2 = socialAgentIntention.getAgentIntentionAtIndex(adddressOfTheAgent, BigInteger.ONE).send();
        Assert.assertEquals("fetchedIntention2 should match", intention2, fetchedIntention2);
    }
}
