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
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import de.tudresden.codegenerator.autogen.CompartmentInitiator;
import de.tudresden.codegenerator.autogen.ComponentCore;
import de.tudresden.multiagentsystem.util.BlockchainUtil;
import de.tudresden.multiagentsystem.util.PropertiesPrivateKeyandAccounts;


/**
 * Comportment Activation Runtime for SocialRbSC Framework.
 *
 * @author $author$
 */
public class RoleCompartmentActivatorIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static String PRIVATE_KEY;

    /**
     * TODO DOCUMENT ME!
     */
    private static String CONTRACT_ADDRESS_COMPARTMENT; // Give an address here

    /**
     * TODO DOCUMENT ME!
     */
    private static String CONTRACT_ADDRESS_COMPONENTCORE; // Give an address here

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
    public void testCompartmentActivation() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(BlockchainUtil.printWeb3Version(web3j));
        Assert.assertNotNull(BlockchainUtil.getCredentials());

        BigInteger gasLimit = BigInteger.valueOf(30000000);
        BigInteger gasPrice = BigInteger.valueOf(2000000000); // 20 Gwei

        BlockchainUtil.transferEthereum(web3j, BlockchainUtil.getCredentials());

        // Deploy the ComponentCore Contact
        ComponentCore componentCore = ComponentCore.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();

        // Deploy the CompartmentInitiator Contract
        CompartmentInitiator compartmentContractInitiator = CompartmentInitiator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit, componentCore.getContractAddress())
                    .send();

        // Get The contract address
        CONTRACT_ADDRESS_COMPARTMENT = compartmentContractInitiator.getContractAddress();
        CONTRACT_ADDRESS_COMPONENTCORE = componentCore.getContractAddress();

        System.out.println();
        System.out.println("CONTRACT_ADDRESS_COMPARTMENT address: " + CONTRACT_ADDRESS_COMPARTMENT);
        System.out.println("CONTRACT_ADDRESS_COMPONENTCORE address: " + CONTRACT_ADDRESS_COMPONENTCORE);

        Assert.assertNotNull(CONTRACT_ADDRESS_COMPARTMENT);
        Assert.assertNotNull(CONTRACT_ADDRESS_COMPONENTCORE);

        // Test for the Compartment and ComponentCore Contract
        componentCore.activateCompartment(CONTRACT_ADDRESS_COMPARTMENT).send();

        String activeCompartmentAddress = String.valueOf(componentCore.getActiveCompartment().send());
        Assert.assertNotNull(activeCompartmentAddress);
        Assert.assertEquals(CONTRACT_ADDRESS_COMPARTMENT, activeCompartmentAddress);

        // Deactivation of the Compartment
        componentCore.deactivateCompartment().send();
        activeCompartmentAddress = String.valueOf(componentCore.getActiveCompartment().send());
        Assert.assertEquals("0x0000000000000000000000000000000000000000", activeCompartmentAddress);
    }
}
