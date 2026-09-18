/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.rolemanagement;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import de.tudresden.codegenerator.autogen.CompartmentInitiator;
import de.tudresden.codegenerator.autogen.ComponentCore;
import de.tudresden.codegenerator.autogen.Utils;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class Compartment_ERC165IT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final String INTERFACE_ERC165 = "01ffc9a7";

    /**
     * TODO DOCUMENT ME!
     */
    private static final String INTERFACE_COMPARTMENT_ID = "12345678"; // Replace with actual InterfaceIds.COMPARTMENT_ID

    /**
     * TODO DOCUMENT ME!
     */
    private static final String INTERFACE_COMPONENT_ROLE_ID = "87654321"; // Replace with actual InterfaceIds.COMPONENT_ROLE_ID

    /**
     * TODO DOCUMENT ME!
     */
    private static final String INTERFACE_COMPONENT_ID = "abcdefab"; // Replace with actual InterfaceIds.COMPONENT_ID

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private CompartmentInitiator compartment;

    /**
     * TODO DOCUMENT ME!
     */
    private Credentials credentials;

    /**
     * TODO DOCUMENT ME!
     */
    private Utils utilContract;

    /**
     * TODO DOCUMENT ME!
     */
    private Web3j web3j;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Before
    public void setUpAllFunctions() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // get the transaction manager
        TransactionManager txManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());

        // get gas provider
        StaticGasProvider gasProvider = new StaticGasProvider(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

        Credentials credentials = BlockchainUtil.getCredentials();

        try {
            // Deploy ComponentCore contract
            ComponentCore component = ComponentCore.deploy(web3j, txManager, gasProvider).send();

            // Deploy or load an existing contract
            compartment = CompartmentInitiator.deploy(web3j, credentials, gasProvider, component.getContractAddress()).send();
            utilContract = Utils.deploy(web3j, credentials, gasProvider).send();

            System.out.println("compartment is: " + compartment);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testDoesNotSupportComponentInterfaceId() throws Exception {
        Boolean result = compartment.supportsInterface(utilContract.stringToBytes32Hash(INTERFACE_COMPONENT_ID).send()).send();
        Assert.assertFalse("Contract should not support Component Interface ID", result);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testDoesNotSupportComponentRoleInterfaceId() throws Exception {
        Boolean result = compartment
                    .supportsInterface(utilContract.stringToBytes32Hash(INTERFACE_COMPONENT_ROLE_ID).send())
                    .send();
        Assert.assertFalse("Contract should not support Component Role Interface ID", result);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testDoesNotSupportRandomInterfaceIds() throws Exception {
        Boolean result = compartment.supportsInterface(new BigInteger("01ffc9a8", 16).toByteArray()).send();
        Assert.assertFalse("Contract should not support random interface IDs", result);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testSupportsCompartmentInterface() throws Exception {
        Boolean result = compartment.supportsInterface(new BigInteger(INTERFACE_COMPARTMENT_ID, 16).toByteArray()).send();
        Assert.assertFalse("Contract should not support Compartment Interface", result);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testSupportsErc165Interface() throws Exception {
        // The new BigInteger(hexString, 16) constructor correctly interprets the input string as a hexadecimal number.
        Boolean result = compartment.supportsInterface(new BigInteger(INTERFACE_ERC165, 16).toByteArray()).send();
        Assert.assertTrue("Contract should support ERC165 interface", result);
    }

    // Helper function to convert BigInteger to a 32-byte array
    /**
     * TODO DOCUMENT ME!
     *
     * @param  bi TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    private static byte[] toBytes32(BigInteger bi) {
        byte[] bytes = bi.toByteArray();

        if (bytes.length == 32) {
            return bytes;
        } else if (bytes.length > 32) {
            // If the byte array is longer than 32 bytes, truncate to the last 32 bytes
            return Arrays.copyOfRange(bytes, bytes.length - 32, bytes.length);
        } else {
            // If the byte array is shorter than 32 bytes, pad with leading zeros
            byte[] padded = new byte[32];
            System.arraycopy(bytes, 0, padded, 32 - bytes.length, bytes.length);

            return padded;
        }
    }
}
