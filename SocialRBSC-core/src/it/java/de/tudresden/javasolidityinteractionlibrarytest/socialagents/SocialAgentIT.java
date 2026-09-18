/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.socialagents;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.DefaultGasProvider;

import de.tudresden.codegenerator.autogen.ConcreteMediator;
import de.tudresden.codegenerator.autogen.SocialAgent;
import de.tudresden.codegenerator.autogen.Society;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class SocialAgentIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * web3j object.
     */
    private static Web3j web3j;

    /**
     * SocialAgent contract.
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
     * setup before the method invocation in test cases.
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

        concreteMediator = de.tudresden.codegenerator.autogen.ConcreteMediator
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        society = de.tudresden.codegenerator.autogen.Society
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();

        socialAgent = de.tudresden.codegenerator.autogen.SocialAgent
                    .deploy(web3j, transactionManager, gasProvider)
                    .send();
    }

    /**
     * Tear down from the web3j object.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        web3j.shutdown();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testAgentBindingToSociety() throws Exception {
        String socialAgentName = "SocialAgentBuyer";
        socialAgent
                .socialAgentBindToSociety(society.getContractAddress(), concreteMediator.getContractAddress(), socialAgentName)
                .send();

        String societyAddressThatHasBeenRegistered = concreteMediator.getAgentFromSociety(socialAgentName).send();
        Assert.assertEquals(society.getContractAddress(), societyAddressThatHasBeenRegistered);
    }

    /**
     * Social agent creation with name.
     *
     * @throws Exception
     */
    @Test
    public void testSocialAgentCreateMethod() throws Exception {
        String socialAgentName = "SocialAgentBuyerAgent1";
        socialAgent.createAgent(socialAgentName).send();

        Assert.assertEquals(socialAgent.getSocialAgentName().send(), socialAgentName);
    }

    /**
     * Binding and unbinding agent with method invocation.
     *
     * @throws Exception
     */
    @Test
    public void testSocialAgentUnbindFromSociety() throws Exception {
        String socialAgentName = "SocialAgentBuyerAgent1";
        socialAgent.createAgent(socialAgentName).send();
        concreteMediator.bindAgent(society.getContractAddress(), socialAgentName).send();

        String societyAddressThatHasBeenRegistered = concreteMediator.getAgentFromSociety(socialAgentName).send();
        Assert.assertEquals(society.getContractAddress(), societyAddressThatHasBeenRegistered);
        concreteMediator.unbindAgent(socialAgentName).send();

        ContractCallException exception = Assert.assertThrows(ContractCallException.class,
                () -> {concreteMediator.getAgentFromSociety(socialAgentName).send();}); // You cannot reach an unbinded agent from
                                                                                        // a
                                                                                        // society

        System.out.println(exception.getMessage());

        String expectedReason = "VM Exception while processing transaction: revert Agent not found in the society";
        Assert.assertTrue(exception.getMessage().contains(expectedReason));
    }
}
