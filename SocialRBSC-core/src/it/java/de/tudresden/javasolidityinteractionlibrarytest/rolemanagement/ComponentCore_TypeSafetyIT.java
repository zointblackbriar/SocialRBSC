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
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import de.tudresden.codegenerator.autogen.CompartmentInitiator;
import de.tudresden.codegenerator.autogen.ComponentCore;
import de.tudresden.codegenerator.autogen.RoleCreatorInitiator;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class ComponentCore_TypeSafetyIT {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    Credentials credentials;

    /**
     * TODO DOCUMENT ME!
     */
    private CompartmentInitiator compartment;

    /**
     * TODO DOCUMENT ME!
     */
    private ComponentCore componentCore;

    /**
     * TODO DOCUMENT ME!
     */
    private BigInteger firstRole;

    /**
     * TODO DOCUMENT ME!
     */
    private RoleCreatorInitiator roleCreator;

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
    public void setUp() throws Exception {
        web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // get the transaction manager
        TransactionManager txManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());

        // get gas provider
        StaticGasProvider gasProvider = new StaticGasProvider(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

        // Credentials
        credentials = BlockchainUtil.getCredentials();

        // Deploy ComponentCore contract
        componentCore = ComponentCore.deploy(web3j, txManager, gasProvider).send();
        System.out.println("ComponentCore address is: " + componentCore.getContractAddress());

        // Deploy CompartmentInitiator contract
        compartment = CompartmentInitiator.deploy(web3j, txManager, gasProvider, componentCore.getContractAddress()).send();
        System.out.println("Compartment address is: " + compartment.getContractAddress());

        // Define role specifications
        firstRole = new BigInteger(1,
                org.web3j.utils.Numeric.hexStringToByteArray(
                    org.web3j.utils.Numeric.toHexStringNoPrefix(Hash.sha3String("TESTROLE1").getBytes())));

        // Deploy RoleCreatorInitiator contract
        roleCreator = RoleCreatorInitiator.deploy(web3j, txManager, gasProvider).send();
        System.out.println("Role creator address is: " + roleCreator.getContractAddress());

        // Add role creators to the compartment
        compartment.addRoleCreator(toBytes32(firstRole.toByteArray()), roleCreator.getContractAddress()).send();

        // Add roles to the compartment
        compartment.addRole(toBytes32(firstRole.toByteArray())).send();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws RuntimeException
     */
    @Test
    public void shouldFailWhenActivatingNonCompartmentContract() {
        // Given
        String randomRoleContractAddress;

        try {
            randomRoleContractAddress = compartment.getRole(toBytes32(firstRole.toByteArray())).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Then
        Exception exception = Assert.assertThrows(Exception.class,
                () -> {componentCore.activateCompartment(randomRoleContractAddress).send();});

        // Ensure the exception message indicates a revert
        Assert.assertTrue("Expected transaction to be reverted", exception.getMessage().contains("revert"));
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws RuntimeException
     */
    @Test
    public void shouldFailWhenAddingComponentAsRole() {
        // Given
        ComponentCore core2;

        try {
            core2 = ComponentCore.deploy(web3j, credentials, new DefaultGasProvider()).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Then
        Exception exception = Assert.assertThrows(Exception.class,
                () -> {core2.addRole(toBytes32(firstRole.toByteArray()), core2.getContractAddress()).send();});

        // Ensure the exception message indicates a revert
        Assert.assertTrue("Expected transaction to be reverted", exception.getMessage().contains("revert"));
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void shouldSucceedWhenActivatingCompartmentContract() throws Exception {
        // Should succeed
        componentCore.activateCompartment(compartment.getContractAddress()).send();
    }

    // Helper function to convert byte array to a 32-byte array
    /**
     * TODO DOCUMENT ME!
     *
     * @param  input TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    private static byte[] toBytes32(byte[] input) {
        if (input.length == 32) {
            return input;
        } else if (input.length > 32) {
            // If the byte array is longer than 32 bytes, truncate to the last 32 bytes
            return Arrays.copyOfRange(input, input.length - 32, input.length);
        } else {
            // If the byte array is shorter than 32 bytes, pad with leading zeros
            byte[] padded = new byte[32];
            System.arraycopy(input, 0, padded, 32 - input.length, input.length);

            return padded;
        }
    }
}
