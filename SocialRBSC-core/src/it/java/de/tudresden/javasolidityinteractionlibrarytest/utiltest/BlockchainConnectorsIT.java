/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.utiltest;

import org.junit.Assert;
import org.junit.Test;

import de.tudresden.multiagentsystem.runtime.ethereumconnector.ConnectionWeb3;
import de.tudresden.multiagentsystem.runtime.ethereumconnector.EthereumConnection;


/**
 * Activate your blockchain connector first.
 *
 * @author Orcun Oruc
 */
public class BlockchainConnectorsIT {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Blockchain connection in a basic way.
     *
     * @throws Exception
     */
    @Test
    public void blockchainConnectionTest() throws Exception {
        EthereumConnection ec = new EthereumConnection();
        Assert.assertTrue(ec.ethereumWeb3jConnection());
    }

    /**
     * Transaction tryout from a particular private account.
     *
     * @throws Exception
     */
    @Test
    public void testMakeTransaction() throws Exception {
        Assert.assertTrue(ConnectionWeb3.makeTransaction());
    }
}
