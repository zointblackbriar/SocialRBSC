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
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import de.tudresden.codegenerator.autogen.Ownable;
import static de.tudresden.multiagentsystem.util.BlockchainUtil.getCredentials;
import static de.tudresden.multiagentsystem.util.BlockchainUtil.printWeb3Version;
import static de.tudresden.multiagentsystem.util.BlockchainUtil.transferEthereum;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class OwnableIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static String PRIVATE_KEY;

    /**
     * TODO DOCUMENT ME!
     */
    private static String CONTRACT_ADDRESS_OWNABLE; // Give an address here

    /**
     * TODO DOCUMENT ME!
     */
    private static Ownable ownable;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testOwnable() throws Exception {
        // Test transferOwnership
        String newOwner = "0x1234567890123456789012345678901234567890";
        TransactionReceipt receipt = ownable.transferOwnership(newOwner).send();
        Assert.assertTrue(receipt.isStatusOK());
        Assert.assertEquals(newOwner, ownable.owner().send());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Before
    public void testOwnableContractConnectionToBlockchain() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(printWeb3Version(web3j));
        Assert.assertNotNull(getCredentials());

        BigInteger gasLimit = BigInteger.valueOf(30000000);
        BigInteger gasPrice = BigInteger.valueOf(2000000000); // 20 Gwei

        transferEthereum(web3j, getCredentials());

        // Deploy the CompartmentInitiator Contract
        ownable = Ownable.deploy(web3j, getCredentials(), gasPrice, gasLimit).send();

        // Get The contract address
        CONTRACT_ADDRESS_OWNABLE = ownable.getContractAddress();

        System.out.println("CONTRACT_ADDRESS_SOCIALAGENTSTATEMACHINE address: " + CONTRACT_ADDRESS_OWNABLE);
    }
}
