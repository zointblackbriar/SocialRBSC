/**
 * EventDoubleEndedQueue.java
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
public class EventDoubleEndedQueue extends Contract {
    public static final String BINARY = "6080604052600160ff1b60018181556100179161002c565b60025534801561002657600080fd5b50610051565b60008282101561004c57634e487b7160e01b600052601160045260246000fd5b500390565b610247806100606000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80630ddc164a1461005157806317e669c21461006b57806394e7083f14610080578063f982f9af14610093575b600080fd5b61005961009b565b60405190815260200160405180910390f35b61007e6100793660046101b3565b6100e5565b005b61007e61008e3660046101b3565b610110565b61005961013c565b600060015460025410156100ae57600080fd5b506002805460009081526020819052604081208054908290558254909260019290916100db9084906101e2565b9250508190555090565b60018060008282546100f791906101e2565b9091555050600154600090815260208190526040902055565b60016002600082825461012391906101f9565b9091555050600254600090815260208190526040902055565b600060015460025410156101885760405162461bcd60e51b815260206004820152600f60248201526e6e6f6e2d656d70747920717565756560881b604482015260640160405180910390fd5b50600180546000908152602081905260408120805490829055825490929182916100db9083906101f9565b6000602082840312156101c557600080fd5b5035919050565b634e487b7160e01b600052601160045260246000fd5b6000828210156101f4576101f46101cc565b500390565b6000821982111561020c5761020c6101cc565b50019056fea26469706673582212208110ca2e7d935bdfe91f1ef84ed79bde51ab8e06fe9b2d982329763575e2d13364736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_POPLEFT = "popLeft";

    public static final String FUNC_POPRIGHT = "popRight";

    public static final String FUNC_PUSHLEFT = "pushLeft";

    public static final String FUNC_PUSHRIGHT = "pushRight";

    @Deprecated
    protected EventDoubleEndedQueue(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EventDoubleEndedQueue(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EventDoubleEndedQueue(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EventDoubleEndedQueue(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> popLeft() {
        final Function function = new Function(
                FUNC_POPLEFT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> popRight() {
        final Function function = new Function(
                FUNC_POPRIGHT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> pushLeft(BigInteger _data) {
        final Function function = new Function(
                FUNC_PUSHLEFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> pushRight(BigInteger _data) {
        final Function function = new Function(
                FUNC_PUSHRIGHT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static EventDoubleEndedQueue load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EventDoubleEndedQueue(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EventDoubleEndedQueue load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EventDoubleEndedQueue(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EventDoubleEndedQueue load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EventDoubleEndedQueue(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EventDoubleEndedQueue load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EventDoubleEndedQueue(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EventDoubleEndedQueue> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EventDoubleEndedQueue.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<EventDoubleEndedQueue> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EventDoubleEndedQueue.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<EventDoubleEndedQueue> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EventDoubleEndedQueue.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<EventDoubleEndedQueue> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EventDoubleEndedQueue.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
