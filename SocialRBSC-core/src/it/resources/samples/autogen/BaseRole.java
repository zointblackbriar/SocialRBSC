/**
 * BaseRole.java
 *
 * TODO: Add a file description.
 *
 * Auto-added header on 2026-02-03
 */

package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
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
public class BaseRole extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516102e33803806102e383398101604081905261002f9161016e565b807f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c55600080826001600160a01b03168460405161006d919061022d565b600060405180830381855af49150503d80600081146100a8576040519150601f19603f3d011682016040523d82523d6000602084013e6100ad565b606091505b5091509150816101035760405162461bcd60e51b815260206004820152601360248201527f436f6e737472756374696f6e206661696c656400000000000000000000000000604482015260640160405180910390fd5b50505050610249565b634e487b7160e01b600052604160045260246000fd5b60005b8381101561013d578181015183820152602001610125565b8381111561014c576000848401525b50505050565b80516001600160a01b038116811461016957600080fd5b919050565b6000806040838503121561018157600080fd5b82516001600160401b038082111561019857600080fd5b818501915085601f8301126101ac57600080fd5b8151818111156101be576101be61010c565b604051601f8201601f19908116603f011681019083821181831017156101e6576101e661010c565b816040528281528860208487010111156101ff57600080fd5b610210836020830160208801610122565b809650505050505061022460208401610152565b90509250929050565b6000825161023f818460208701610122565b9190910192915050565b608c806102576000396000f3fe608060405236600a57005b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c543660008037600080366000846127105a03f490503d806000803e818015605157816000f35b816000fdfea26469706673582212208ab15436d294b5abd8b9e7b9c91b039b40df016ea08be3432d8722aab712aa6d64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    @Deprecated
    protected BaseRole(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BaseRole(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BaseRole(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BaseRole(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    @Deprecated
    public static BaseRole load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new BaseRole(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BaseRole load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BaseRole(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BaseRole load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new BaseRole(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BaseRole load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BaseRole(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BaseRole> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, byte[] constructData, String contractLogic) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(constructData), 
                new org.web3j.abi.datatypes.Address(160, contractLogic)));
        return deployRemoteCall(BaseRole.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<BaseRole> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, byte[] constructData, String contractLogic) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(constructData), 
                new org.web3j.abi.datatypes.Address(160, contractLogic)));
        return deployRemoteCall(BaseRole.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<BaseRole> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, byte[] constructData, String contractLogic) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(constructData), 
                new org.web3j.abi.datatypes.Address(160, contractLogic)));
        return deployRemoteCall(BaseRole.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<BaseRole> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, byte[] constructData, String contractLogic) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(constructData), 
                new org.web3j.abi.datatypes.Address(160, contractLogic)));
        return deployRemoteCall(BaseRole.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
