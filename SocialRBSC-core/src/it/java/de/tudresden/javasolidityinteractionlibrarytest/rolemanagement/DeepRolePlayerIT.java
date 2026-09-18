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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import de.tudresden.codegenerator.autogen.ComponentCore;
import de.tudresden.codegenerator.autogen.Player;
import de.tudresden.codegenerator.autogen.Retailer;
import de.tudresden.codegenerator.autogen.Wholesaler;
import de.tudresden.multiagentsystem.util.BlockchainUtil;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class DeepRolePlayerIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static Web3j web3j;

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private ComponentCore componentCore;

    /**
     * TODO DOCUMENT ME!
     */
    private Player player;

    /**
     * TODO DOCUMENT ME!
     */
    private Retailer retailer;

    /**
     * TODO DOCUMENT ME!
     */
    private Wholesaler wholesaler;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        // Connect to a local Ethereum node (e.g., Ganache, Infura)
        web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // get the transaction manager
        TransactionManager transactionManager = new RawTransactionManager(web3j, BlockchainUtil.getCredentials());
        Credentials ownerAddress = BlockchainUtil.getCredentials();

        // get gas provider
        DefaultGasProvider gasProvider = new DefaultGasProvider();

        // Deploy the SocialAgentDeliberationCycle contract
        componentCore = ComponentCore.deploy(web3j, transactionManager, gasProvider).send();
        player = Player.deploy(web3j, transactionManager, gasProvider, ownerAddress.getAddress()).send();
        retailer = Retailer.deploy(web3j, transactionManager, gasProvider).send();
        wholesaler = Wholesaler.deploy(web3j, transactionManager, gasProvider).send();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testForPlayerContract() throws Exception {
        Assert.assertEquals("Retailer inventory status should match", retailer.getInventoryStatus().send(),
            BigInteger.valueOf(0));

        player.playDelegateCallRoleContract(retailer.getContractAddress(), "playContractForIdentity()").send();
        Assert.assertTrue(player.playedContractList(retailer.getContractAddress()).send());
        Assert.assertFalse(player.playedContractList(wholesaler.getContractAddress()).send());
        player.playDelegateCallRoleContract(wholesaler.getContractAddress(), "playContractForIdentity()").send();
        Assert.assertTrue(player.playedContractList(wholesaler.getContractAddress()).send());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testShouldInteractWithPlayerContractAfterContractWithoutIdentityFunction() throws Exception {
        // Call the playDelegateCallRoleContract function

        TransactionReceipt txReceipt = player
                    .playDelegateCallRoleContract(retailer.getContractAddress(), "nonIdentityFunction()")
                    .send();

        // Verify the transaction was successful
        assert txReceipt.isStatusOK();

        // Try to call getInventoryStatus and expect it to revert
        Exception exception = Assert.assertThrows(Exception.class, () -> {retailer.getInventoryStatus().send();});
        System.out.println(exception.getMessage());
    }
}
