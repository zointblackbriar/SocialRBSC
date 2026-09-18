package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
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
 * <p>Generated with web3j version 5.0.2.
 */
@SuppressWarnings("rawtypes")
@Generated("org.web3j.codegen.SolidityFunctionWrapperGenerator")
public class DeepRoleFacet1 extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610362806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063d086945d1461003b578063d5c41d0314610050575b600080fd5b61004e6100493660046101bf565b61006e565b005b6100586100d8565b604051610065919061029b565b60405180910390f35b60405163d086945d60e01b815273__$630c51825201dab47bf5102cb4acb07a37$__9063d086945d906100a590849060040161029b565b60006040518083038186803b1580156100bd57600080fd5b505af41580156100d1573d6000803e3d6000fd5b5050505050565b606073__$630c51825201dab47bf5102cb4acb07a37$__63d5c41d036040518163ffffffff1660e01b8152600401600060405180830381865af4158015610123573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261014b91908101906102b5565b905090565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff8111828210171561018f5761018f610150565b604052919050565b600067ffffffffffffffff8211156101b1576101b1610150565b50601f01601f191660200190565b6000602082840312156101d157600080fd5b813567ffffffffffffffff8111156101e857600080fd5b8201601f810184136101f957600080fd5b803561020c61020782610197565b610166565b81815285602083850101111561022157600080fd5b81602084016020830137600091810160200191909152949350505050565b60005b8381101561025a578181015183820152602001610242565b83811115610269576000848401525b50505050565b6000815180845261028781602086016020860161023f565b601f01601f19169290920160200192915050565b6020815260006102ae602083018461026f565b9392505050565b6000602082840312156102c757600080fd5b815167ffffffffffffffff8111156102de57600080fd5b8201601f810184136102ef57600080fd5b80516102fd61020782610197565b81815285602083850101111561031257600080fd5b61032382602083016020860161023f565b9594505050505056fea2646970667358221220c4e7448a36d29fd92e2d063f85fab36c4c1c1fcfd7ae76f0d037bf3746f9fa1264736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETDEEPROLEID = "getDeepRoleID";

    public static final String FUNC_SETDEEPROLEID = "setDeepRoleID";

    @Deprecated
    protected DeepRoleFacet1(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DeepRoleFacet1(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DeepRoleFacet1(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DeepRoleFacet1(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> getDeepRoleID() {
        final Function function = new Function(
                FUNC_GETDEEPROLEID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDeepRoleID(String _deepRoleID) {
        final Function function = new Function(
                FUNC_SETDEEPROLEID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_deepRoleID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DeepRoleFacet1 load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRoleFacet1(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DeepRoleFacet1 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRoleFacet1(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DeepRoleFacet1 load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DeepRoleFacet1(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DeepRoleFacet1 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DeepRoleFacet1(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DeepRoleFacet1> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeepRoleFacet1.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeepRoleFacet1> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeepRoleFacet1.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DeepRoleFacet1> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeepRoleFacet1.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeepRoleFacet1> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeepRoleFacet1.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
