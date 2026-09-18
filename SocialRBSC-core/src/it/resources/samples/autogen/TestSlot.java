/**
 * TestSlot.java
 *
 * TODO: Add a file description.
 *
 * Auto-added header on 2026-02-03
 */

package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
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
public class TestSlot extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610171806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80631a88bc66146100465780636c877c841461008057806380dee782146100ba575b600080fd5b61006d7fa7cb26ea17989bb9c5eb391c94c40892dcdc94bb4c381353450910ba80883e1c81565b6040519081526020015b60405180910390f35b7fa7cb26ea17989bb9c5eb391c94c40892dcdc94bb4c381353450910ba80883e1c546040516001600160a01b039091168152602001610077565b6101096100c836600461010b565b7fa7cb26ea17989bb9c5eb391c94c40892dcdc94bb4c381353450910ba80883e1c80546001600160a01b0319166001600160a01b0392909216919091179055565b005b60006020828403121561011d57600080fd5b81356001600160a01b038116811461013457600080fd5b939250505056fea2646970667358221220dbcbee08c17a83fa49518642ae90715037db6da29307a8f88608645afbcacd6764736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETSLOT = "getSlot";

    public static final String FUNC_SLOT = "slot";

    public static final String FUNC_WRITESLOT = "writeSlot";

    @Deprecated
    protected TestSlot(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TestSlot(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TestSlot(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TestSlot(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getSlot() {
        final Function function = new Function(FUNC_GETSLOT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> slot() {
        final Function function = new Function(FUNC_SLOT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> writeSlot(String _addr) {
        final Function function = new Function(
                FUNC_WRITESLOT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TestSlot load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new TestSlot(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TestSlot load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestSlot(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TestSlot load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new TestSlot(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TestSlot load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TestSlot(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TestSlot> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TestSlot.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TestSlot> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TestSlot.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<TestSlot> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TestSlot.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TestSlot> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TestSlot.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
