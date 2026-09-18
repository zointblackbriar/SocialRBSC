/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.web3jconnectiontester;

import de.tudresden.multiagentsystem.runtime.ethereumconnector.ConnectionWeb3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

import java.util.logging.Logger;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class Web3jConnectionTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    Logger logger = Logger.getLogger(Web3jConnectionTest.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Disabled
    public void testWeb3jConnection() throws Exception {
        ConnectionWeb3 connectionWeb3 = new ConnectionWeb3();
        logger.info("show the web3 object: %s" + connectionWeb3);
        assertTrue(connectionWeb3.connectionWeb3js());
    }
}
