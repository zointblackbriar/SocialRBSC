/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.rolemanagement;

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
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class CompartmentRoleManagementIT {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private CompartmentInitiator compartment;

    /**
     * TODO DOCUMENT ME!
     */
    private ComponentCore component;

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
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // get the transaction manager
        TransactionManager txManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());

        // get gas provider
        StaticGasProvider gasProvider = new StaticGasProvider(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

        Credentials credentials = BlockchainUtil.getCredentials();

        // Deploy ComponentCore contract
        component = ComponentCore.deploy(web3j, txManager, gasProvider).send();

        // Deploy CompartmentInitiator contract
        compartment = CompartmentInitiator.deploy(web3j, txManager, gasProvider, component.getContractAddress()).send();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void shouldRetrieveCompartmentAddressAfterActivating() throws Exception {
        // Given
        component.activateCompartment(compartment.getContractAddress()).send();

        // When
        String currentCompartment = component.getActiveCompartment().send();
        System.out.println("CurrentCompartment: " + currentCompartment);
        System.out.println("compartment.getContractAddress()" + compartment.getContractAddress());

        // Then
        Assert.assertEquals("The compartment address should match the activated compartment address.",
            compartment.getContractAddress(), currentCompartment);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void shouldRetrieveZeroAddressAfterDeactivating() throws Exception {
        // Given
        component.activateCompartment(compartment.getContractAddress()).send();
        component.deactivateCompartment().send();

        // When
        String currentCompartment = component.getActiveCompartment().send();
        System.out.println("CurrentCompartment: " + currentCompartment);

        // Then
        Assert.assertEquals("The compartment address should be zero after deactivation.",
            "0x0000000000000000000000000000000000000000", currentCompartment);
    }
}
