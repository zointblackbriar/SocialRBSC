/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.socialagents;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import de.tudresden.codegenerator.autogen.SocialAgentStateMachine;
import de.tudresden.multiagentsystem.util.PropertiesPrivateKeyandAccounts;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class SociaAgentStateMachineIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(SociaAgentStateMachineIT.class.getName());

    /**
     * TODO DOCUMENT ME!
     */
    private static String PRIVATE_KEY;

    /**
     * TODO DOCUMENT ME!
     */
    private static String CONTRACT_ADDRESS_SOCIALAGENTSTATEMACHINE; // Give an address here

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testBeliefBDIConnector() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(printWeb3Version(web3j));
        Assert.assertNotNull(getCredentials());

        BigInteger gasLimit = BigInteger.valueOf(30000000);
        BigInteger gasPrice = BigInteger.valueOf(2000000000); // 20 Gwei

        transferEthereum(web3j, getCredentials());

        // Deploy the CompartmentInitiator Contract
        SocialAgentStateMachine socialAgentStateMachine = SocialAgentStateMachine
                    .deploy(web3j, getCredentials(), gasPrice, gasLimit)
                    .send();

        // Get The contract address
        CONTRACT_ADDRESS_SOCIALAGENTSTATEMACHINE = socialAgentStateMachine.getContractAddress();

        System.out.println();
        System.out.println("CONTRACT_ADDRESS_SOCIALAGENTSTATEMACHINE address: " + CONTRACT_ADDRESS_SOCIALAGENTSTATEMACHINE);

        Assert.assertNotNull(CONTRACT_ADDRESS_SOCIALAGENTSTATEMACHINE);
        // NEWAGENT,
        // RUNNABLEAGENT,
        // TERMINATEDAGENT,
        // PLANNING

        socialAgentStateMachine.nextState().send(); // RUNNABLE AGENT
        Assert.assertEquals("Agent state check after nextstate() method", socialAgentStateMachine.agentState().send(),
            BigInteger.valueOf(1));
        socialAgentStateMachine.nextState().send(); // TERMINATEDAGENT
        Assert.assertEquals("Agent state check after nextstate() method", socialAgentStateMachine.agentState().send(),
            BigInteger.valueOf(2));
        socialAgentStateMachine.nextState().send(); // PLANNING
        Assert.assertEquals("Agent state check after nextstate() method", socialAgentStateMachine.agentState().send(),
            BigInteger.valueOf(3));
        // socialAgentStateMachine.nextState().send(); // PLANNING
        // Assert.assertEquals("Agent state check after nextstate() method", socialAgentStateMachine.agentState().send(),
        // BigInteger.valueOf(4));
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    private Credentials getCredentials() {
        PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();

        try {
            Map<String, String> sampleCredential = propertiesPrivateKeyandAccounts.getPropertyFromFile();

            PRIVATE_KEY = sampleCredential.get("private_key1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Credentials.create(PRIVATE_KEY);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  web3j TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    private boolean printWeb3Version(Web3j web3j) {
        Web3ClientVersion web3ClientVersion = null;

        try {
            web3ClientVersion = web3j.web3ClientVersion().send();

            String web3ClientVersionString = web3ClientVersion.getWeb3ClientVersion();
            System.out.println(web3ClientVersionString);
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  web3j       TODO DOCUMENT ME!
     * @param  credentials TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    private void transferEthereum(Web3j web3j, Credentials credentials) throws Exception {
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);

        Transfer transfer = new Transfer(web3j, transactionManager);
        BigInteger gasLimit = BigInteger.valueOf(30000000L);
        BigInteger gasPrice = BigInteger.valueOf(20000000000L); // 20 Gwei

        PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();
        Map<String, String> credentialFromFile = propertiesPrivateKeyandAccounts.getPropertyFromFile();
        TransactionReceipt transactionReceipt = transfer
                    .sendFunds(credentialFromFile.get("account1"), BigDecimal.ONE, Convert.Unit.ETHER, gasPrice, gasLimit)
                    .send();

        System.out.print("Transaction = " + transactionReceipt.getTransactionHash());
    }
}
