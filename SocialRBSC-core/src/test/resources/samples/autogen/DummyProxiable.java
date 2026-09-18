package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class DummyProxiable extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610236806100206000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80633fa4f2451461005157806352d1902d1461006c5780635524107714610092578063912a9885146100a7575b600080fd5b61005a60005481565b60405190815260200160405180910390f35b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c61005a565b6100a56100a036600461019e565b600055565b005b6100a56100b53660046101b7565b806001600160a01b03166352d1902d6040518163ffffffff1660e01b8152600401602060405180830381865afa1580156100f3573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061011791906101e7565b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c1461017a5760405162461bcd60e51b815260206004820152600e60248201526d4e6f7420636f6d70617469626c6560901b604482015260640160405180910390fd5b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c55565b6000602082840312156101b057600080fd5b5035919050565b6000602082840312156101c957600080fd5b81356001600160a01b03811681146101e057600080fd5b9392505050565b6000602082840312156101f957600080fd5b505191905056fea2646970667358221220a83dfd073773402f0e792f059611c849917f128e50bbc06395a040dbc1fc5f4b64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_PROXIABLEUUID = "proxiableUUID";

    public static final String FUNC_SETVALUE = "setValue";

    public static final String FUNC_UPDATECODEADDRESS = "updateCodeAddress";

    public static final String FUNC_VALUE = "value";

    @Deprecated
    protected DummyProxiable(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DummyProxiable(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DummyProxiable(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DummyProxiable(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<byte[]> proxiableUUID() {
        final Function function = new Function(FUNC_PROXIABLEUUID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> setValue(BigInteger _value) {
        final Function function = new Function(
                FUNC_SETVALUE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateCodeAddress(String newAddress) {
        final Function function = new Function(
                FUNC_UPDATECODEADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> value() {
        final Function function = new Function(FUNC_VALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DummyProxiable load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DummyProxiable(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DummyProxiable load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DummyProxiable(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DummyProxiable load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DummyProxiable(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DummyProxiable load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DummyProxiable(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DummyProxiable> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DummyProxiable.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DummyProxiable> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DummyProxiable.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DummyProxiable> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DummyProxiable.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DummyProxiable> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DummyProxiable.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
