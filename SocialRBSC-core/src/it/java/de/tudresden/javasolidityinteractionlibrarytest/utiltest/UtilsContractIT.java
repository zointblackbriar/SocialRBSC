/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.utiltest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;

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

import de.tudresden.codegenerator.autogen.Utils;
import de.tudresden.multiagentsystem.util.PropertiesPrivateKeyandAccounts;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class UtilsContractIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static String PRIVATE_KEY;

    /**
     * TODO DOCUMENT ME!
     */
    private static String CONTRACT_ADDRESS; // Give an address here

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void propertyFileTest() {
        PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();

        Map<String, String> sampleCredential = null;

        try {
            sampleCredential = propertiesPrivateKeyandAccounts.getPropertyFromFile();
            PRIVATE_KEY = sampleCredential.get("private_key1");

            // Iterator iter = sampleCredential.entrySet().iterator();

            // while (iter.hasNext()) {
            // System.out.println(iter.next());
            // }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Assert.assertEquals(sampleCredential.get("private_key1"), PRIVATE_KEY);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testUtilsContract() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(printWeb3Version(web3j));
        Assert.assertNotNull(getCredentials());

        BigInteger gasLimit = BigInteger.valueOf(30000000);
        BigInteger gasPrice = BigInteger.valueOf(2000000000); // 20 Gwei

        transferEthereum(web3j, getCredentials());

        // Deploy the contract
        Utils utilsContract = Utils.deploy(web3j, getCredentials(), gasPrice, gasLimit).send();

        // Get The contract address
        CONTRACT_ADDRESS = utilsContract.getContractAddress();
        System.out.println("Contract address: " + CONTRACT_ADDRESS);
        Assert.assertNotNull(CONTRACT_ADDRESS);

        // Real operation for getString and setString

        Assert.assertTrue(compareDifferentValues(utilsContract, "sample value", "sample value"));
        Assert.assertFalse(compareDifferentValues(utilsContract, "sample val", "sample value"));

        System.out.println("result of the byte conversion in blockchain network: " +
                convertBigInteger2Bytes(utilsContract, BigInteger.valueOf(25)));

        Assert.assertNotNull(convertBigInteger2Bytes(utilsContract, BigInteger.valueOf(25)));
    }

    // Helper function to convert byte array to a 32-byte array
    /**
     * TODO DOCUMENT ME!
     *
     * @param  input TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    private static byte[] toBytes32(BigInteger input) {
        // Convert BigInteger to byte array
        byte[] byteArray = input.toByteArray();

        return Arrays.toString(byteArray).getBytes();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  utilsContract TODO DOCUMENT ME!
     * @param  value1        TODO DOCUMENT ME!
     * @param  value2        TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    private boolean compareDifferentValues(Utils utilsContract, String value1, String value2) throws Exception {
        return utilsContract.compare(value1, value2).send();
    }

    /**
     * Helper Function.
     *
     * @param  utilsContract TODO DOCUMENT ME!
     * @param  value         TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    private byte[] convertBigInteger2Bytes(Utils utilsContract, BigInteger value) throws Exception {
        return toBytes32(value);
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
    // private TestContract loadContract(String contractAddress, Web3j web3j, Credentials credentials) {
    // return TestContract.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    // }

    /**
     * Open ganache-cli first.
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
