/*
 * Copyright 2022-2023 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.runtime.ethereumconnector;

import java.util.logging.Logger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class EthereumConnection {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(EthereumConnection.class.getName());

    /**
     * TODO DOCUMENT ME!
     */
    private static final String DEFAULT_ADDRESS = "http://127.0.0.1:8545";

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private Web3ClientVersion web3ClientVersion = null;

    /**
     * TODO DOCUMENT ME!
     */
    private Web3j web3j = null;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    public boolean ethereumWeb3jConnection() throws Exception {
        try {
            System.out.println("hello web3j connection");
            web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS)); // RPCServer
            System.out.println("Successfully connected to Ethereum");

            EthAccounts resultAccount = new EthAccounts();
            resultAccount = this.web3j.ethAccounts().sendAsync().get();

            EthGetTransactionCount resultTransactionCount = new EthGetTransactionCount();
            resultTransactionCount = this.web3j
                        .ethGetTransactionCount(DEFAULT_ADDRESS, DefaultBlockParameter.valueOf("latest"))
                        .sendAsync()
                        .get();

            EthGetBalance resultBalance = new EthGetBalance();
            this.web3j.ethGetBalance(DEFAULT_ADDRESS, DefaultBlockParameter.valueOf("latest")).sendAsync().get();

            logger.info("transaction numbers are: " + resultTransactionCount);
            logger.info("accounts are: " + resultAccount);
            logger.info("Account balance is: " + resultBalance);

            // logger.info("Logger info 3: "+ EthereumConnection.addRoles2Contract(roleRegister));
            // EthereumConnection.printRoles(roleRegister);
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    public boolean getExternalNetworkConfiguration() throws Exception {
        try {
            Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/a63f69b8ada5413e8afc2dd41eef444a")); // could be deprecated

            logger.info("Connected to Ethereum client version: {}" + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }
}
