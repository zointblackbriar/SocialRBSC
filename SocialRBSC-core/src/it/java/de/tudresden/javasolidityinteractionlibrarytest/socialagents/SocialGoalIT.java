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

import org.junit.Assert;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import de.tudresden.codegenerator.autogen.SocialGoal;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class SocialGoalIT {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testSocialGoal() {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(BlockchainUtil.printWeb3Version(web3j));
        Assert.assertNotNull(BlockchainUtil.getCredentials());

        String contractAddressSocialGoal = null;

        TransactionManager txManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());
        StaticGasProvider gasProvider = new StaticGasProvider(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        String nameOfString = "testSocialAgent";

        try {
            // Deploy the contract
            SocialGoal socialGoal = SocialGoal.deploy(web3j, txManager, gasProvider).send();
            Assert.assertNotNull(socialGoal);

            contractAddressSocialGoal = socialGoal.getContractAddress();
            System.out.println(contractAddressSocialGoal);
            Assert.assertNotNull(contractAddressSocialGoal);

            TransactionReceipt transactionReceipt = socialGoal
                        .assignmentOwnerAddress(BlockchainUtil.getCredentials().getAddress())
                        .send();
            System.out.println("Owner address assigned, transaction hash: " + transactionReceipt.getTransactionHash());
            Assert.assertNotNull(transactionReceipt);

            // Add a goal
            transactionReceipt = socialGoal.addGoal(nameOfString, "Test Goal").send();
            System.out.println("Goal added, transaction hash: " + transactionReceipt.getTransactionHash());
            Assert.assertNotNull(transactionReceipt);

            transactionReceipt = socialGoal.activateGoal(nameOfString, BigInteger.valueOf(0)).send(); // first goal should be
                                                                                                      // activated
            System.out.println("Goal activated, transaction hash: " + transactionReceipt.getTransactionHash());
            Assert.assertNotNull(transactionReceipt);

            // Test completeGoal
            transactionReceipt = socialGoal.completeGoal(nameOfString, BigInteger.valueOf(0)).send();
            System.out.println("Goal completed, transaction hash: " + transactionReceipt.getTransactionHash());
            Assert.assertNotNull(transactionReceipt);

            // All transactions are completed

            // Verify goal status
//             SocialGoal.Goal goal = socialGoal.agentGoals(getCredentials().getAddress()).send().get(0);
//             System.out.println("Goal status: " + goal.status);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
