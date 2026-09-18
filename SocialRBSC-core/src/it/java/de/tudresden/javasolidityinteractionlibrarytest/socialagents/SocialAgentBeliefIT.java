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
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import de.tudresden.codegenerator.autogen.SocialAgentBelief;
import static de.tudresden.multiagentsystem.util.BlockchainUtil.getCredentials;
import static de.tudresden.multiagentsystem.util.BlockchainUtil.printWeb3Version;
import static de.tudresden.multiagentsystem.util.BlockchainUtil.transferEthereum;
import de.tudresden.multiagentsystem.util.PropertiesPrivateKeyandAccounts;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class SocialAgentBeliefIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(SocialAgentBeliefIT.class.getName());

    /**
     * TODO DOCUMENT ME!
     */
    private static String PRIVATE_KEY;

    /**
     * TODO DOCUMENT ME!
     */
    private static String CONTRACT_ADDRESS_SOCIALAGENTBELIEF; // Give an address here

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * String to 64 length HexString (equivalent to 32 Hex lenght).
     *
     * @Source: https://ethereum.stackexchange.com/questions/11898/how-to-convert-java-string-to-bytes32-in-java-i-am-using-web3j-solidity-wrappe
     *
     * @param   asciiValue Ascii value to convert HEX values
     *
     * @return  Hext value in string!
     */
    public static String asciiToHex(String asciiValue) {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString() + "".join("", Collections.nCopies(32 - (hex.length() / 2), "00"));
    }

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
    public void testBeliefBDIConnector() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(printWeb3Version(web3j));
        Assert.assertNotNull(getCredentials());

        BigInteger gasLimit = BigInteger.valueOf(30000000);
        BigInteger gasPrice = BigInteger.valueOf(2000000000); // 20 Gwei

        transferEthereum(web3j, getCredentials());

        // Deploy the CompartmentInitiator Contract
        SocialAgentBelief socialAgentBeliefInitiator = SocialAgentBelief
                    .deploy(web3j, getCredentials(), gasPrice, gasLimit)
                    .send();

        // Get The contract address
        CONTRACT_ADDRESS_SOCIALAGENTBELIEF = socialAgentBeliefInitiator.getContractAddress();

        System.out.println();
        System.out.println("CONTRACT_ADDRESS_SOCIALAGENTBELIEF address: " + CONTRACT_ADDRESS_SOCIALAGENTBELIEF);

        Assert.assertNotNull(CONTRACT_ADDRESS_SOCIALAGENTBELIEF);

        // byte[] myStringInByte1 = Numeric.hexStringToByteArray(asciiToHex("Sample Belief"));
        boolean result = Boolean.valueOf(socialAgentBeliefInitiator.hasBelief("Sample Belief").send());

        // There is no social agent belief yet
        Assert.assertFalse(result);

        // Add a belief
        socialAgentBeliefInitiator.addBelief("Belief1", BigInteger.valueOf(0)).send();
        result = Boolean.valueOf(socialAgentBeliefInitiator.hasBelief("Belief1").send());
        Assert.assertTrue(result);
        result = Boolean.valueOf(socialAgentBeliefInitiator.hasBelief("Belief2").send());
        Assert.assertFalse(result);
        socialAgentBeliefInitiator.updateBelief("Belief1", BigInteger.valueOf(0)).send();
        result = socialAgentBeliefInitiator.beliefUpdated().send();
        Assert.assertTrue(result);

        // byte[] getNameSocialBelief = Numeric.hexStringToByteArray(asciiToHex("Social Belief"));
        String updateNameSocialBelief = "Belief1";
        int updateValueSocialBelief = 0;
        socialAgentBeliefInitiator.updateBelief(updateNameSocialBelief, BigInteger.valueOf(updateValueSocialBelief)).send();
        Assert.assertTrue(socialAgentBeliefInitiator.beliefUpdated().send());

        logger.info("socialAgentBeliefInitiator.getBeliefNames first belief: " +
                socialAgentBeliefInitiator.beliefNames(BigInteger.valueOf(0)).send());
        socialAgentBeliefInitiator.addBelief("Belief2", BigInteger.valueOf(1));
        logger.info("socialAgentBeliefInitiator.getBeliefNames second belief: " +
                socialAgentBeliefInitiator.beliefNames(BigInteger.valueOf(0)).send());
        Assert.assertNotNull(socialAgentBeliefInitiator.beliefNames(BigInteger.valueOf(0)).send());

        // Assert.assertNotNull(socialAgentBeliefInitiator.beliefNames(BigInteger.valueOf(1)).send());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    // private Credentials getCredentials() {
    // PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();
    //
    // try {
    // Map<String, String> sampleCredential = propertiesPrivateKeyandAccounts.getPropertyFromFile();
    //
    // PRIVATE_KEY = sampleCredential.get("private_key1");
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // }
    //
    // return Credentials.create(PRIVATE_KEY);
    // }

    /**
     * Open ganache-cli first.
     *
     * @param  web3j TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    // private boolean printWeb3Version(Web3j web3j) {
    // Web3ClientVersion web3ClientVersion = null;
    //
    // try {
    // web3ClientVersion = web3j.web3ClientVersion().send();
    //
    // String web3ClientVersionString = web3ClientVersion.getWeb3ClientVersion();
    // System.out.println(web3ClientVersionString);
    // } catch (Exception ex) {
    // ex.printStackTrace();
    //
    // return false;
    // }
    //
    // return true;
    // }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  web3j       TODO DOCUMENT ME!
     * @param  credentials TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    // private void transferEthereum(Web3j web3j, Credentials credentials) throws Exception {
    // TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
    //
    // Transfer transfer = new Transfer(web3j, transactionManager);
    // BigInteger gasLimit = BigInteger.valueOf(30000000L);
    // BigInteger gasPrice = BigInteger.valueOf(20000000000L); // 20 Gwei
    //
    // PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();
    // Map<String, String> credentialFromFile = propertiesPrivateKeyandAccounts.getPropertyFromFile();
    // TransactionReceipt transactionReceipt = transfer
    // .sendFunds(credentialFromFile.get("account1"), BigDecimal.ONE, Convert.Unit.ETHER, gasPrice, gasLimit)
    // .send();
    //
    // System.out.print("Transaction = " + transactionReceipt.getTransactionHash());
    // }
}
