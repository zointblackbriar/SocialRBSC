/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.runtime.ethereumconnector;

// Logger library

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Numeric;

import java.io.IOException;

import java.math.BigInteger;

import java.util.Optional;
import java.util.concurrent.ExecutionException;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class ConnectionWeb3 {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = LoggerFactory.getLogger(ConnectionWeb3.class.getName());

    /**
     * TODO DOCUMENT ME!
     */
    private static Web3j web3_instance = null;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static boolean connectionWeb3js() throws InterruptedException, ExecutionException {
        boolean result = false;

        try {
            Web3j web3 = ConnectionWeb3.getInstance();

            if (web3 == null) {
                throw new IllegalArgumentException();
            }

            System.out.println("Successfuly connected to Ethereum local node");

            // web3_clientVersion returns the current client version.
            Web3ClientVersion clientVersion = web3.web3ClientVersion().send();

            // for async call, you can use the following
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            logger.info("web3ClientVersion: " + web3ClientVersion);

            // eth_blockNumber returns the number of most recent block.
            EthBlockNumber blockNumber = web3.ethBlockNumber().send();

            // eth_gasPrice, returns the current price per gas in wei.
            EthGasPrice gasPrice = web3.ethGasPrice().send();

            logger.info("Client version: " + clientVersion.getWeb3ClientVersion());
            logger.info("Block number: " + blockNumber.getBlockNumber());
            logger.info("Gas price: " + gasPrice.getGasPrice());
        } catch (IOException ex) {
            result = false;

            // throw new RuntimeException("Json-rpc request has a problem", ex);
            logger.error("Json-rpc request has a problem", ex);
        }

        return result;
    }

    /**
     * Get the web3 instance.
     *
     * @return web3 instance
     */
    public static Web3j getInstance() {
        if (web3_instance == null) {
            web3_instance = Web3j.build(new HttpService("http://localhost:8545"));
        }

        return web3_instance;
    }

    /**
     * From a static private key, we need to do a transaction to a particular address.
     *
     * @return Either true or false for transaction with web3
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean makeTransaction() throws IOException, InterruptedException {
        try {
            Web3j web3 = ConnectionWeb3.getInstance();

            String privateKey = "0xdd10a09af47e5ec05c9e2c7073393e1372177960d4a38266ffcd5fed2e1afb06";
            Credentials credentials = Credentials.create(privateKey);
            logger.info("Account address: " + credentials.getAddress());
            logger.info("Balance: " +
                    Convert.fromWei(
                        web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                            .send()
                            .getBalance()
                            .toString(), Unit.ETHER));

            // Get the latest nonce
            EthGetTransactionCount ethGetTransactionCount = web3
                        .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                        .send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            // Recipient address
            // Find a way to convert automatically
            // String recipientAddress = "0x010765d1541A6dB273ef8Ee0f6351e1a9530d04C";
            String recipientAddress = "0x4E64B8cE0Ff7238677899f100b02BC8f1e0191F4";

            // Value to transfer (in wei)
            BigInteger value = Convert.toWei("1", Unit.ETHER).toBigInteger();

            // Gas Parameters
            BigInteger gasLimit = BigInteger.valueOf(21000);
            BigInteger gasPrice = Convert.toWei("1", Unit.GWEI).toBigInteger();

            // Use whole big integer values
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, recipientAddress,
                    value);
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);

            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
            String transactionHash = ethSendTransaction.getTransactionHash();
            logger.info("transactionHash: " + transactionHash);

            // Waiting for a transaction has been mined
            Optional<TransactionReceipt> transactionReceipt = null;

            do {
                logger.info("This transaction hash has been mined: " + transactionHash);

                EthGetTransactionReceipt ethGetTransactionReceiptResp = web3.ethGetTransactionReceipt(transactionHash).send();
                transactionReceipt = ethGetTransactionReceiptResp.getTransactionReceipt();
                Thread.sleep(3000); // Put a sleeping thread
            } while (!transactionReceipt.isPresent());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }
}
