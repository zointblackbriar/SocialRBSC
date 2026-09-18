/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class BlockchainUtil {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * Private key used for creating Credentials object.
     */
    private static String PRIVATE_KEY;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Create Credentials object from the private key stored in the properties file.
     *
     * @return returns Credentials object created from the private key
     */
    public static Credentials getCredentials() {
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
     * Open ganache-cli first.
     *
     * @param  web3j web3j is an object for connection between web3j and java virtual machine
     *
     * @return returns true if the version is printed successfully, false otherwise
     */
    public static boolean printWeb3Version(Web3j web3j) {
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
     * @param  web3j       web3j is an object for connection between web3j and java virtual machine
     * @param  credentials credentials is an object that holds the private key and other information for signing transactions
     *
     * @throws Exception
     */
    public static void transferEthereum(Web3j web3j, Credentials credentials) throws Exception {
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
