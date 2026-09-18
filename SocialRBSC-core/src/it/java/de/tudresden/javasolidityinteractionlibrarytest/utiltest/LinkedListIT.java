/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.utiltest;

import java.math.BigInteger;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Ignore;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import de.tudresden.codegenerator.autogen.LinkedList;
import de.tudresden.codegenerator.commandline.CommandLineActivator;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class LinkedListIT {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    Logger logger = Logger.getLogger(CommandLineActivator.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Ignore
    public void testLinkedListFunctions() {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // get the transaction manager
        TransactionManager transactionManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());

        // get gas provider
        DefaultGasProvider gasProvider = new DefaultGasProvider();

        Credentials credentials = BlockchainUtil.getCredentials();

        try {
            // Deploy the contract
            LinkedList contract = LinkedList.deploy(web3j, transactionManager, gasProvider).send();
            String contractAddress = contract.getContractAddress();
            Assert.assertNotNull(contractAddress);
            logger.info("contractAddress: " + contractAddress);

            // Test insert function
            logger.info("Testing insert function...");
            contract.insert(BigInteger.valueOf(100)).send();
            contract.insert(BigInteger.valueOf(200)).send();
            contract.insert(BigInteger.valueOf(300)).send();

            logger.info("Testing testdata function...");

            TransactionReceipt data = contract.getData().send();
            logger.info("data is: " + data);
            Assert.assertNotNull(data.getTransactionHash());

            // Get all string addresses of the state variables
            BigInteger valueItself1 = contract.stateVariables(BigInteger.valueOf(0)).send();
            BigInteger valueItself2 = contract.stateVariables(BigInteger.valueOf(1)).send();
            BigInteger valueItself3 = contract.stateVariables(BigInteger.valueOf(2)).send();
            BigInteger valueItself4 = contract.stateVariables(BigInteger.valueOf(3)).send();
            logger.info("value of state variables 1: " + valueItself1);
            logger.info("value of state variables 2: " + valueItself2);
            logger.info("value of state variables 3: " + valueItself3);
            logger.info("value of state variables 4: " + valueItself4); // this is an exception as reverted by the EVM
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
