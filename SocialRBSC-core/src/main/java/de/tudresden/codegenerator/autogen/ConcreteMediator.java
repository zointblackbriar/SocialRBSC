package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class ConcreteMediator extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610410806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c806338935f131461005c57806358b13ac51461007e5780636e2bbc15146100ca578063c3529453146100dd578063e478371b146100f2575b600080fd5b6001546100699060ff1681565b60405190151581526020015b60405180910390f35b6100b261008c366004610306565b80516020818301810180516000825292820191909301209152546001600160a01b031681565b6040516001600160a01b039091168152602001610075565b6100b26100d8366004610306565b610105565b6100f06100eb366004610306565b61018b565b005b6100f0610100366004610343565b6101bb565b600080600083604051610118919061039f565b908152604051908190036020019020546001600160a01b03169050806101855760405162461bcd60e51b815260206004820152601e60248201527f4167656e74206e6f7420666f756e6420696e2074686520736f6369657479000060448201526064015b60405180910390fd5b92915050565b60008160405161019b919061039f565b90815260405190819003602001902080546001600160a01b031916905550565b6001600160a01b03821661021f5760405162461bcd60e51b815260206004820152602560248201527f6120736f6369616c206167656e742073686f756c64206861766520616e206164604482015264647265737360d81b606482015260840161017c565b81600082604051610230919061039f565b90815260405190819003602001902080546001600160a01b03929092166001600160a01b03199092169190911790555050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261028a57600080fd5b813567ffffffffffffffff808211156102a5576102a5610263565b604051601f8301601f19908116603f011681019082821181831017156102cd576102cd610263565b816040528381528660208588010111156102e657600080fd5b836020870160208301376000602085830101528094505050505092915050565b60006020828403121561031857600080fd5b813567ffffffffffffffff81111561032f57600080fd5b61033b84828501610279565b949350505050565b6000806040838503121561035657600080fd5b82356001600160a01b038116811461036d57600080fd5b9150602083013567ffffffffffffffff81111561038957600080fd5b61039585828601610279565b9150509250929050565b6000825160005b818110156103c057602081860181015185830152016103a6565b818111156103cf576000828501525b50919091019291505056fea26469706673582212201957453d828043f21142a51f26ed000bd96a4b88945ea2edc2018abdccc7555864736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ASSIGNEDAGENTSTOSOCIETY = "assignedAgentsToSociety";

    public static final String FUNC_BINDAGENT = "bindAgent";

    public static final String FUNC_GETAGENTFROMSOCIETY = "getAgentFromSociety";

    public static final String FUNC_ISMEDIATOR = "isMediator";

    public static final String FUNC_UNBINDAGENT = "unbindAgent";

    @Deprecated
    protected ConcreteMediator(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ConcreteMediator(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ConcreteMediator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ConcreteMediator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> assignedAgentsToSociety(String param0) {
        final Function function = new Function(FUNC_ASSIGNEDAGENTSTOSOCIETY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> bindAgent(String _societyAddress,
            String _socialAgentName) {
        final Function function = new Function(
                FUNC_BINDAGENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _societyAddress), 
                new org.web3j.abi.datatypes.Utf8String(_socialAgentName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getAgentFromSociety(String _agentName) {
        final Function function = new Function(FUNC_GETAGENTFROMSOCIETY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_agentName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isMediator() {
        final Function function = new Function(FUNC_ISMEDIATOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> unbindAgent(String _socialAgentName) {
        final Function function = new Function(
                FUNC_UNBINDAGENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_socialAgentName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ConcreteMediator load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ConcreteMediator(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ConcreteMediator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ConcreteMediator(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ConcreteMediator load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ConcreteMediator(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ConcreteMediator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ConcreteMediator(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ConcreteMediator> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ConcreteMediator.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ConcreteMediator> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ConcreteMediator.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ConcreteMediator> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ConcreteMediator.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ConcreteMediator> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ConcreteMediator.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
