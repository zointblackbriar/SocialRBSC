/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import de.tudresden.codegenerator.autogen.JaccardSimilarity;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class JaccardSimilarityIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------




    /**
     * TODO DOCUMENT ME!
     */
    private static JaccardSimilarity jaccardSimilarity;

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private Credentials credentials;

    /**
     * TODO DOCUMENT ME!
     */
    private Web3j web3j;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * JaccardSimilarity Contract has been eliminated from the Java wrapper.
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
            jaccardSimilarity = JaccardSimilarity.deploy(web3j, txManager, gasProvider).send();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * JaccardSimilarity Contract has been eliminated from the Java wrapper.
     *
     * @throws Exception
     */
    @Test
    public void testCalculateJaccardSimilarity() throws Exception {
        // Test the similarity between two strings
        String str1 = "hello";
        String str2 = "yellow";

        // Execute the similarity function
        TransactionReceipt transactionReceipt = jaccardSimilarity.executeJaccardSimilarity(str1, str2).send();
        Assert.assertNotNull(transactionReceipt);

        // Fetch the result from the contract
        BigInteger similarityResult = jaccardSimilarity.result().sendAsync().get();

        // Verify the result
        BigInteger expectedResult = BigInteger.valueOf(500000000000000000L); // Expected similarity in fixed-point notation
        Assert.assertEquals(expectedResult, similarityResult); // incorrect similarity
    }

    /**
     * JaccardSimilarity Contract has been eliminated from the Java wrapper.
     *
     * @throws Exception
     */
    @Test
    public void testSimilarityWithIdenticalStrings() throws Exception {
        // Test with identical strings
        String str1 = "identical";
        String str2 = "identical";

        // Execute the similarity function
        TransactionReceipt transactionReceipt = jaccardSimilarity.executeJaccardSimilarity(str1, str2).send();
        Assert.assertNotNull(transactionReceipt);

        // Fetch the result from the contract
        BigInteger similarityResult = jaccardSimilarity.result().sendAsync().get();

        // Verify the result
        BigInteger expectedResult = BigInteger.valueOf(1000000000000000000L); // 100% similarity
        Assert.assertEquals("The Jaccard similarity should be 100% for identical strings.", expectedResult, similarityResult);
    }

    /**
     * JaccardSimilarity Contract has been eliminated from the Java wrapper.
     *
     * @throws Exception
     */
    @Test
    public void testSimilarityWithNoIntersection() throws Exception {
        // Test with completely different strings
        String str1 = "abc";
        String str2 = "xyz";

        // Execute the similarity function
        TransactionReceipt transactionReceipt = jaccardSimilarity.executeJaccardSimilarity(str1, str2).send();
        Assert.assertNotNull(transactionReceipt);

        // Fetch the result from the contract
        BigInteger similarityResult = jaccardSimilarity.result().sendAsync().get();

        // Verify the result
        BigInteger expectedResult = BigInteger.ZERO; // No common characters, similarity should be zero

        // "The Jaccard similarity should be zero for disjoint strings."
        Assert.assertEquals(expectedResult, similarityResult);
    }
}
