/**
 * Player.java
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
import org.web3j.abi.FunctionEncoder;
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
public class Player extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161047838038061047883398101604081905261002f91610054565b600180546001600160a01b0319166001600160a01b0392909216919091179055610084565b60006020828403121561006657600080fd5b81516001600160a01b038116811461007d57600080fd5b9392505050565b6103e5806100936000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80638da5cb5b14610046578063bb7f267814610076578063c954030614610099575b600080fd5b600154610059906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b6100896100843660046102bf565b6100bc565b604051901515815260200161006d565b6100896100a7366004610342565b60006020819052908152604090205460ff1681565b6001546000906001600160a01b031633146101315760405162461bcd60e51b815260206004820152602a60248201527f7265766572742062656361757365206974206973206e6f7420746865206f776e604482015269195c881858d8dbdd5b9d60b21b60648201526084015b60405180910390fd5b600061013d8484610281565b60408051600481526024810182526020810180516001600160e01b03166001600160e01b03198516179052905191925060009182916001600160a01b038916916101879190610364565b600060405180830381855af49150503d80600081146101c2576040519150601f19603f3d011682016040523d82523d6000602084013e6101c7565b606091505b50909250905081151560000361024f576001600160a01b0387166000908152602081905260409020805460ff191690558051156102075780518082602001fd5b60405162461bcd60e51b815260206004820152601f60248201527f46756e6374696f6e2063616c6c20686173206265656e207265766572746564006044820152606401610128565b5050506001600160a01b0384166000908152602081905260409020805460ff1916600190811790915590509392505050565b6000828260405161029392919061039f565b6040518091039020905092915050565b80356001600160a01b03811681146102ba57600080fd5b919050565b6000806000604084860312156102d457600080fd5b6102dd846102a3565b9250602084013567ffffffffffffffff808211156102fa57600080fd5b818601915086601f83011261030e57600080fd5b81358181111561031d57600080fd5b87602082850101111561032f57600080fd5b6020830194508093505050509250925092565b60006020828403121561035457600080fd5b61035d826102a3565b9392505050565b6000825160005b81811015610385576020818601810151858301520161036b565b81811115610394576000828501525b509190910192915050565b818382376000910190815291905056fea264697066735822122063e00a994c423cbc916e21af1aa28362e33a02887a3b2cd34c44479aad2b93c164736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PLAYDELEGATECALLROLECONTRACT = "playDelegateCallRoleContract";

    public static final String FUNC_PLAYEDCONTRACTLIST = "playedContractList";

    @Deprecated
    protected Player(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Player(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Player(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Player(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> playDelegateCallRoleContract(
            String _contractAddress, String _functionName) {
        final Function function = new Function(
                FUNC_PLAYDELEGATECALLROLECONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contractAddress), 
                new org.web3j.abi.datatypes.Utf8String(_functionName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> playedContractList(String param0) {
        final Function function = new Function(FUNC_PLAYEDCONTRACTLIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Player load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Player(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Player load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Player(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Player load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Player(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Player load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Player(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Player> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _assignedAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _assignedAddress)));
        return deployRemoteCall(Player.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Player> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String _assignedAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _assignedAddress)));
        return deployRemoteCall(Player.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Player> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _assignedAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _assignedAddress)));
        return deployRemoteCall(Player.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Player> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String _assignedAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _assignedAddress)));
        return deployRemoteCall(Player.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
