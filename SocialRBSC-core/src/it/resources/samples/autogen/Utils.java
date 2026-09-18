/**
 * Utils.java
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
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class Utils extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506106c7806100206000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c8063775a8f5e1161005b578063775a8f5e14610103578063ba35664014610116578063c562405014610129578063cfb519281461013c57600080fd5b80631dcd9b551461008d578063274a5183146100b65780633a96fdd7146100d7578063555e7f15146100fa575b600080fd5b6100a061009b366004610455565b610156565b6040516100ad91906104ff565b60405180910390f35b6100c96100c4366004610519565b61029b565b6040519081526020016100ad565b6100ea6100e5366004610556565b6102ba565b60405190151581526020016100ad565b6100c960005481565b6100a06101113660046105ba565b610325565b6100c9610124366004610556565b61034f565b6100c9610137366004610519565b610382565b6100c961014a366004610519565b60200151600081905590565b825160609084908410801561016b5750805183105b80156101775750828411155b6101c75760405162461bcd60e51b815260206004820152601960248201527f696e76616c696420737562737472696e6720696e646963657300000000000000604482015260640160405180910390fd5b60006101d385856105e9565b6101de906001610600565b67ffffffffffffffff8111156101f6576101f66103b2565b6040519080825280601f01601f191660200182016040528015610220576020820181803683370190505b509050845b8481116102915782818151811061023e5761023e610618565b01602001516001600160f81b0319168261025888846105e9565b8151811061026857610268610618565b60200101906001600160f81b031916908160001a905350806102898161062e565b915050610225565b5095945050505050565b8051600090829082036102b15750600092915050565b50506020015190565b600081518351146102cd5750600061031f565b816040516020016102de9190610647565b60405160208183030381529060405280519060200120836040516020016103059190610647565b604051602081830303815290604052805190602001201490505b92915050565b60408051602080825281830190925260609160208201818036833750505060208101929092525090565b60008282604051602001610364929190610663565b60405160208183030381529060405280519060200120905092915050565b6000816040516020016103959190610647565b604051602081830303815290604052805190602001209050919050565b634e487b7160e01b600052604160045260246000fd5b600082601f8301126103d957600080fd5b813567ffffffffffffffff808211156103f4576103f46103b2565b604051601f8301601f19908116603f0116810190828211818310171561041c5761041c6103b2565b8160405283815286602085880101111561043557600080fd5b836020870160208301376000602085830101528094505050505092915050565b60008060006060848603121561046a57600080fd5b833567ffffffffffffffff81111561048157600080fd5b61048d868287016103c8565b9660208601359650604090950135949350505050565b60005b838110156104be5781810151838201526020016104a6565b838111156104cd576000848401525b50505050565b600081518084526104eb8160208601602086016104a3565b601f01601f19169290920160200192915050565b60208152600061051260208301846104d3565b9392505050565b60006020828403121561052b57600080fd5b813567ffffffffffffffff81111561054257600080fd5b61054e848285016103c8565b949350505050565b6000806040838503121561056957600080fd5b823567ffffffffffffffff8082111561058157600080fd5b61058d868387016103c8565b935060208501359150808211156105a357600080fd5b506105b0858286016103c8565b9150509250929050565b6000602082840312156105cc57600080fd5b5035919050565b634e487b7160e01b600052601160045260246000fd5b6000828210156105fb576105fb6105d3565b500390565b60008219821115610613576106136105d3565b500190565b634e487b7160e01b600052603260045260246000fd5b600060018201610640576106406105d3565b5060010190565b600082516106598184602087016104a3565b9190910192915050565b60408152600061067660408301856104d3565b828103602084015261068881856104d3565b9594505050505056fea2646970667358221220575fffadff7f59b884e06edcb49487ba093c44e1e19c99ecf33706cc3e04374b64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_COMPARE = "compare";

    public static final String FUNC_COMPUTEBYTEPATH = "computeBytePath";

    public static final String FUNC_RESULTSTRINGTOBYTES32 = "resultstringToBytes32";

    public static final String FUNC_STRINGTOBYTES32 = "stringToBytes32";

    public static final String FUNC_STRINGTOBYTES32HASH = "stringToBytes32Hash";

    public static final String FUNC_STRINGTOBYTES32ROBUSTVERSION = "stringToBytes32RobustVersion";

    public static final String FUNC_SUBSTRING = "substring";

    public static final String FUNC_TOBYTES = "toBytes";

    @Deprecated
    protected Utils(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Utils(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Utils(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Utils(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> compare(String _firstString, String _secondString) {
        final Function function = new Function(FUNC_COMPARE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_firstString), 
                new org.web3j.abi.datatypes.Utf8String(_secondString)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<byte[]> computeBytePath(String _parentGoalName, String _goalName) {
        final Function function = new Function(FUNC_COMPUTEBYTEPATH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_parentGoalName), 
                new org.web3j.abi.datatypes.Utf8String(_goalName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> resultstringToBytes32() {
        final Function function = new Function(FUNC_RESULTSTRINGTOBYTES32, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> stringToBytes32(String str) {
        final Function function = new Function(
                FUNC_STRINGTOBYTES32, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(str)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> stringToBytes32Hash(String str) {
        final Function function = new Function(FUNC_STRINGTOBYTES32HASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(str)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> stringToBytes32RobustVersion(String source) {
        final Function function = new Function(FUNC_STRINGTOBYTES32ROBUSTVERSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(source)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> substring(String _firstString, BigInteger _startIndex,
            BigInteger _endIndex) {
        final Function function = new Function(FUNC_SUBSTRING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_firstString), 
                new org.web3j.abi.datatypes.generated.Uint256(_startIndex), 
                new org.web3j.abi.datatypes.generated.Uint256(_endIndex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> toBytes(BigInteger _input) {
        final Function function = new Function(FUNC_TOBYTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_input)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    @Deprecated
    public static Utils load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Utils(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Utils load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Utils(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Utils load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Utils(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Utils load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Utils(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Utils> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Utils.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Utils> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Utils.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<Utils> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Utils.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Utils> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Utils.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
