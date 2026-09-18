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
public class ComponentCore_CompartmentManagementIT {

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
        // Initialize Web3j instance and credentials
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // get the transaction manager
        TransactionManager txManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());

        // get gas provider
        StaticGasProvider gasProvider = new StaticGasProvider(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

        // Deploy ComponentCore contract
        component = ComponentCore.deploy(web3j, txManager, gasProvider).send();
        System.out.println("ComponentCore address is: " + component.getContractAddress());

        // Deploy CompartmentInitiator contract
        compartment = CompartmentInitiator.deploy(web3j, txManager, gasProvider, component.getContractAddress()).send();
        System.out.println("Compartment address is: " + compartment.getContractAddress());
    }

    /**
     * retrieving compartment after activation.
     *
     * @throws Exception
     */
    @Test
    public void shouldRetrieveCompartmentAddressAfterActivation() throws Exception {
        /**
         *  *
         *  */
        /* Activate the compartment */
        component.activateCompartment(compartment.getContractAddress()).send();

        // Retrieve the currently active compartment
        String currentCompartment = component.getActiveCompartment().send();

        System.out.println("Current compartment: " + currentCompartment);

        // Assert that the retrieved address is equal to the activated compartment's address
        Assert.assertEquals("The compartment address should match the activated compartment address.",
            compartment.getContractAddress(), currentCompartment);
    }

    /**
     * Deactivation test for compartments.
     *
     * @throws Exception
     */
    @Test
    public void shouldRetrieveZeroAddressAfterDeactivation() throws Exception {

        component.activateCompartment(compartment.getContractAddress()).send();
        component.deactivateCompartment().send();

        // Retrieve the currently active compartment (which should be deactivated)
        String currentCompartment = component.getActiveCompartment().send();

        System.out.println("Current compartment: " + currentCompartment);

        // Assert that the retrieved address is the zero address
        Assert.assertEquals("The compartment address should be zero after deactivation.",
            "0x0000000000000000000000000000000000000000", currentCompartment);
    }
}
