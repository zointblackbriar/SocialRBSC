package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
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
 * <p>Generated with web3j version 5.0.2.
 */
@SuppressWarnings("rawtypes")
@Generated("org.web3j.codegen.SolidityFunctionWrapperGenerator")
public class DeepRole2 extends Contract {
    public static final String BINARY = "60806040526004805460ff1916905534801561001a57600080fd5b50610cec8061002a6000396000f3fe608060405234801561001057600080fd5b50600436106101425760003560e01c806380009630116100b85780639fd20abc1161007c5780639fd20abc1461028b578063b3cac0a51461029e578063d09de08a146102b1578063d0bf6d15146102b9578063e959b38a146102cc578063f2f4eb26146102df57600080fd5b806380009630146102425780638129fc1c146102555780638da5cb5b1461025d578063912a98851461027057806395ec5ce31461028357600080fd5b806340a9efc81161010a57806340a9efc8146101b057806346951954146101db578063496a3c48146101ee57806352d1902d146102015780636b5a3e39146102275780636e8eb7bc1461022f57600080fd5b806301ffc9a71461014757806306540f7e1461016f578063158ef93e14610186578063201b631b146101935780632baeceb7146101a6575b600080fd5b61015a610155366004610aa7565b6102f2565b60405190151581526020015b60405180910390f35b61017860035481565b604051908152602001610166565b60045461015a9060ff1681565b61015a6101a1366004610aed565b610329565b6101ae6103a9565b005b6001546101c3906001600160a01b031681565b6040516001600160a01b039091168152602001610166565b6101ae6101e9366004610b72565b6103c0565b6101ae6101fc366004610b72565b61042b565b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c610178565b6101c361048e565b6101c361023d366004610b8f565b61050b565b6101ae610250366004610b72565b61057b565b6101ae61061c565b6002546101c3906001600160a01b031681565b6101ae61027e366004610b72565b6106d4565b6101ae6107b9565b6101ae610299366004610b72565b610814565b61015a6102ac366004610b8f565b6108b8565b6101ae610926565b6101ae6102c7366004610b8f565b610936565b6101ae6102da366004610ba8565b610967565b6000546101c3906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061032357506001600160e01b03198216636f8ae2e560e11b145b92915050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061035e90879087908790600401610bd8565b6020604051808303816000875af115801561037d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103a19190610c18565b949350505050565b600380549060006103b983610c50565b9190505550565b6002546001600160a01b0316331461041f5760405162461bcd60e51b815260206004820152601e60248201527f4f6e6c79206f776e65722063616e207570646174652074686520636f6465000060448201526064015b60405180910390fd5b610428816106d4565b50565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561047357600080fd5b505af1158015610487573d6000803e3d6000fd5b5050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156104e2573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105069190610c67565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af1158015610557573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103239190610c67565b6001600160a01b038116156105fa57600061059d82637365940f60e01b6109d1565b9050806105f85760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b6064820152608401610416565b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b6002546001600160a01b03161561066a5760405162461bcd60e51b8152602060048201526012602482015271105b1c9958591e481a5b9a5d185b1a5e995960721b6044820152606401610416565b60045460ff16156106b35760405162461bcd60e51b8152602060048201526013602482015272105b1c9958591e481a5b9a5d1a585b1a5e9959606a1b6044820152606401610416565b600280546001600160a01b031916331790556004805460ff19166001179055565b806001600160a01b03166352d1902d6040518163ffffffff1660e01b8152600401602060405180830381865afa158015610712573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107369190610c84565b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c146107955760405162461bcd60e51b815260206004820152600e60248201526d4e6f7420636f6d70617469626c6560901b6044820152606401610416565b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c55565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b1580156107fa57600080fd5b505af115801561080e573d6000803e3d6000fd5b50505050565b6001600160a01b03811615610896576000610836826338cce89560e01b6109d1565b9050806108945760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b6064820152608401610416565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa158015610902573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103239190610c18565b600380549060006103b983610c9d565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d1590602401610459565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401600060405180830381600087803b1580156109b557600080fd5b505af11580156109c9573d6000803e3d6000fd5b505050505050565b600080806109e6856301ffc9a760e01b610a73565b90925090508115806109f6575080155b15610a0657600092505050610323565b610a18856001600160e01b0319610a73565b9092509050811580610a2957508015155b15610a3957600092505050610323565b610a438585610a73565b9092509050600182148015610a585750806001145b15610a6857600192505050610323565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b600060208284031215610ab957600080fd5b81356001600160e01b031981168114610ad157600080fd5b9392505050565b6001600160a01b038116811461042857600080fd5b600080600060408486031215610b0257600080fd5b8335610b0d81610ad8565b9250602084013567ffffffffffffffff80821115610b2a57600080fd5b818601915086601f830112610b3e57600080fd5b813581811115610b4d57600080fd5b876020828501011115610b5f57600080fd5b6020830194508093505050509250925092565b600060208284031215610b8457600080fd5b8135610ad181610ad8565b600060208284031215610ba157600080fd5b5035919050565b60008060408385031215610bbb57600080fd5b823591506020830135610bcd81610ad8565b809150509250929050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610c2a57600080fd5b81518015158114610ad157600080fd5b634e487b7160e01b600052601160045260246000fd5b600081610c5f57610c5f610c3a565b506000190190565b600060208284031215610c7957600080fd5b8151610ad181610ad8565b600060208284031215610c9657600080fd5b5051919050565b600060018201610caf57610caf610c3a565b506001019056fea264697066735822122002d44c8c1b4e1ffc3c741192c61c5f62715660d887410d47effcab0486192d2864736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_DECREMENT = "decrement";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_INCREMENT = "increment";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_INITIALIZED = "initialized";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_MYUINT = "myUint";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PROXIABLEUUID = "proxiableUUID";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_UPDATECODE = "updateCode";

    public static final String FUNC_UPDATECODEADDRESS = "updateCodeAddress";

    @Deprecated
    protected DeepRole2(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DeepRole2(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DeepRole2(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DeepRole2(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> activateCompartment(String _compartment) {
        final Function function = new Function(
                FUNC_ACTIVATECOMPARTMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _compartment)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRole(byte[] spec, String role) {
        final Function function = new Function(
                FUNC_ADDROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec), 
                new org.web3j.abi.datatypes.Address(160, role)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> assignedCompartment() {
        final Function function = new Function(FUNC_ASSIGNEDCOMPARTMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> core() {
        final Function function = new Function(FUNC_CORE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> deactivateCompartment() {
        final Function function = new Function(
                FUNC_DEACTIVATECOMPARTMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> decrement() {
        final Function function = new Function(
                FUNC_DECREMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getActiveCompartment() {
        final Function function = new Function(FUNC_GETACTIVECOMPARTMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getRole(byte[] spec) {
        final Function function = new Function(
                FUNC_GETROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> hasBeenPlayedRuntime(String _contractAddress,
            String _functionName) {
        final Function function = new Function(
                FUNC_HASBEENPLAYEDRUNTIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contractAddress), 
                new org.web3j.abi.datatypes.Utf8String(_functionName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> increment() {
        final Function function = new Function(
                FUNC_INCREMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize() {
        final Function function = new Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        final Function function = new Function(FUNC_INITIALIZED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isPlayingRole(byte[] spec) {
        final Function function = new Function(FUNC_ISPLAYINGROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> myUint() {
        final Function function = new Function(FUNC_MYUINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> proxiableUUID() {
        final Function function = new Function(FUNC_PROXIABLEUUID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeRole(byte[] spec) {
        final Function function = new Function(
                FUNC_REMOVEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setCompartment(String _compartmentAddresss) {
        final Function function = new Function(
                FUNC_SETCOMPARTMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _compartmentAddresss)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setCore(String _coreAddress) {
        final Function function = new Function(
                FUNC_SETCORE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _coreAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceID) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateCode(String newCode) {
        final Function function = new Function(
                FUNC_UPDATECODE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newCode)), 
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

    @Deprecated
    public static DeepRole2 load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRole2(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DeepRole2 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRole2(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DeepRole2 load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DeepRole2(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DeepRole2 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DeepRole2(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DeepRole2> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeepRole2.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeepRole2> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeepRole2.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DeepRole2> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeepRole2.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeepRole2> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeepRole2.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
