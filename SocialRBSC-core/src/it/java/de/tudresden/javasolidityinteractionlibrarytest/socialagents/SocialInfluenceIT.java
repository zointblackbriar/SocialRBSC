/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.socialagents;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import de.tudresden.codegenerator.autogen.SocialInfluence;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class SocialInfluenceIT {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private Credentials credentials;

    /**
     * TODO DOCUMENT ME!
     */
    private SocialInfluence socialInfluence;

    /**
     * TODO DOCUMENT ME!
     */
    private Web3j web3j;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Before
    public void setup() {
        web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(BlockchainUtil.printWeb3Version(web3j));
        Assert.assertNotNull(BlockchainUtil.getCredentials());
        credentials = BlockchainUtil.getCredentials();

        TransactionManager txManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());
        StaticGasProvider gasProvider = new StaticGasProvider(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        Assert.assertNotNull(txManager);
        Assert.assertNotNull(gasProvider);

        try {
            socialInfluence = SocialInfluence.deploy(web3j, txManager, gasProvider).send();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testSetAction() throws Exception {
        // Register a social agent
        socialInfluence.registerSocialAgent(credentials.getAddress()).send();

        // Set action
        String action = "attend_event";
        socialInfluence.setAction(credentials.getAddress(), action, true).send();

        // Verify action
        Boolean actionPerformed = socialInfluence.actions(credentials.getAddress(), action).send();
        Assert.assertTrue(actionPerformed);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testSetAndCheckExpectedAction() throws Exception {
        // Set an expected action
        String action = "attend_event";
        TransactionReceipt receipt = socialInfluence.setExpectedAction(action, true).send();

        // Check if the expected action is set correctly
        Boolean isExpected = socialInfluence.expectedActions(action).send();
        Assert.assertTrue(isExpected);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testSetInfluence() throws Exception {
        // Register a social agent
        socialInfluence.registerSocialAgent(credentials.getAddress()).send();

        // Set influence
        String action = "attend_event";
        socialInfluence.setInfluence(credentials.getAddress(), credentials.getAddress(), action, true).send();

        // Verify influence
        Boolean influence = socialInfluence.influences(credentials.getAddress(), credentials.getAddress(), action).send();
        Assert.assertTrue(influence);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testSetNorm() throws Exception {
        // Register a social agent first
        socialInfluence.registerSocialAgent(credentials.getAddress()).send();

        // Set a norm
        String action = "attend_event";
        socialInfluence.setNorm(credentials.getAddress(), action, true).send();

        // Verify the norm
        Boolean norm = socialInfluence.norms(credentials.getAddress(), action).send();
        Assert.assertTrue(norm);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testSetPressure() throws Exception {
        // Register a social agent
        socialInfluence.registerSocialAgent(credentials.getAddress()).send();

        // Set pressure
        String action = "attend_event";
        socialInfluence.setPressure(credentials.getAddress(), action, true).send();

        // Verify pressure
        Boolean pressure = socialInfluence.pressures(credentials.getAddress(), action).send();
        Assert.assertTrue(pressure);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testSocialInfluence() throws Exception {
        // Register a social agent
        TransactionReceipt receipt = socialInfluence.registerSocialAgent(credentials.getAddress()).send();
        Assert.assertNotNull(receipt);

        // Check if the event log confirms registration
        Tuple2 agentFirstValue = socialInfluence.getRegisteredSocialAgent(credentials.getAddress()).send();
        Assert.assertNotNull(agentFirstValue.getValue1());
        System.out.println(agentFirstValue.getValue2().getClass().getName());
        Assert.assertEquals(agentFirstValue.getValue2().getClass().getName(), "java.lang.Boolean");
    }
}
