public class CompartmentRoleManagementTest {
  private de.tudresden.codegenerator.autogen.CompartmentInitiator compartment;

  private de.tudresden.codegenerator.autogen.ComponentCore component;

  private org.web3j.crypto.Credentials credentials;

  private org.web3j.protocol.Web3j web3j;

  @org.junit.Before
  public void setUp() throws java.lang.Exception {
    web3j = org.web3j.protocol.Web3j.build(new org.web3j.protocol.http.HttpService("http://localhost:8545"));
    org.web3j.tx.TransactionManager txManager = new org.web3j.tx.RawTransactionManager(web3j, de.tudresden.multiagentsystem.util.BlockchainUtil.getCredentials());
    org.web3j.tx.gas.StaticGasProvider gasProvider = new org.web3j.tx.gas.StaticGasProvider(org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE, org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT);
    credentials = de.tudresden.multiagentsystem.util.BlockchainUtil.getCredentials();
    component = de.tudresden.codegenerator.autogen.ComponentCore.deploy(web3j, txManager, gasProvider).send();
    compartment = de.tudresden.codegenerator.autogen.CompartmentInitiator.deploy(web3j, txManager, gasProvider, component.getContractAddress()).send();
  }
}
