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
public class DeepRole1 extends Contract {
    public static final String BINARY = "60806040526004805460ff1916905534801561001a57600080fd5b50610cab8061002a6000396000f3fe608060405234801561001057600080fd5b50600436106101375760003560e01c806380009630116100b85780639fd20abc1161007c5780639fd20abc14610278578063b3cac0a51461028b578063d09de08a1461029e578063d0bf6d15146102a6578063e959b38a146102b9578063f2f4eb26146102cc57600080fd5b8063800096301461022f5780638129fc1c146102425780638da5cb5b1461024a578063912a98851461025d57806395ec5ce31461027057600080fd5b806346951954116100ff57806346951954146101c6578063496a3c48146101db57806352d1902d146101ee5780636b5a3e39146102145780636e8eb7bc1461021c57600080fd5b806301ffc9a71461013c57806306540f7e14610164578063158ef93e1461017b578063201b631b1461018857806340a9efc81461019b575b600080fd5b61014f61014a366004610a85565b6102df565b60405190151581526020015b60405180910390f35b61016d60035481565b60405190815260200161015b565b60045461014f9060ff1681565b61014f610196366004610acb565b610316565b6001546101ae906001600160a01b031681565b6040516001600160a01b03909116815260200161015b565b6101d96101d4366004610b50565b610396565b005b6101d96101e9366004610b50565b610401565b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c61016d565b6101ae610464565b6101ae61022a366004610b6d565b6104e1565b6101d961023d366004610b50565b610551565b6101d96105f2565b6002546101ae906001600160a01b031681565b6101d961026b366004610b50565b6106ab565b6101d9610790565b6101d9610286366004610b50565b6107eb565b61014f610299366004610b6d565b61088f565b6101d96108fd565b6101d96102b4366004610b6d565b610914565b6101d96102c7366004610b86565b610945565b6000546101ae906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061031057506001600160e01b03198216636f8ae2e560e11b145b92915050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061034b90879087908790600401610bb6565b6020604051808303816000875af115801561036a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061038e9190610bf6565b949350505050565b6002546001600160a01b031633146103f55760405162461bcd60e51b815260206004820152601e60248201527f4f6e6c79206f776e65722063616e207570646174652074686520636f6465000060448201526064015b60405180910390fd5b6103fe816106ab565b50565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561044957600080fd5b505af115801561045d573d6000803e3d6000fd5b5050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156104b8573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104dc9190610c18565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af115801561052d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103109190610c18565b6001600160a01b038116156105d057600061057382637365940f60e01b6109af565b9050806105ce5760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084016103ec565b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b6002546001600160a01b0316156106415760405162461bcd60e51b8152602060048201526013602482015272105b1c9958591e481a5b9a5d1a585b1a5e9959606a1b60448201526064016103ec565b60045460ff161561068a5760405162461bcd60e51b8152602060048201526013602482015272105b1c9958591e481a5b9a5d1a585b1a5e9959606a1b60448201526064016103ec565b600280546001600160a01b031916331790556004805460ff19166001179055565b806001600160a01b03166352d1902d6040518163ffffffff1660e01b8152600401602060405180830381865afa1580156106e9573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061070d9190610c35565b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c1461076c5760405162461bcd60e51b815260206004820152600e60248201526d4e6f7420636f6d70617469626c6560901b60448201526064016103ec565b7f6188f556757f3c51d8424f0d78fa0c802d6d1997bee0b229ab4ebe8f1a1cdd8c55565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b1580156107d157600080fd5b505af11580156107e5573d6000803e3d6000fd5b50505050565b6001600160a01b0381161561086d57600061080d826338cce89560e01b6109af565b90508061086b5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084016103ec565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa1580156108d9573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103109190610bf6565b6003805490600061090d83610c4e565b9190505550565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d159060240161042f565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401600060405180830381600087803b15801561099357600080fd5b505af11580156109a7573d6000803e3d6000fd5b505050505050565b600080806109c4856301ffc9a760e01b610a51565b90925090508115806109d4575080155b156109e457600092505050610310565b6109f6856001600160e01b0319610a51565b9092509050811580610a0757508015155b15610a1757600092505050610310565b610a218585610a51565b9092509050600182148015610a365750806001145b15610a4657600192505050610310565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b600060208284031215610a9757600080fd5b81356001600160e01b031981168114610aaf57600080fd5b9392505050565b6001600160a01b03811681146103fe57600080fd5b600080600060408486031215610ae057600080fd5b8335610aeb81610ab6565b9250602084013567ffffffffffffffff80821115610b0857600080fd5b818601915086601f830112610b1c57600080fd5b813581811115610b2b57600080fd5b876020828501011115610b3d57600080fd5b6020830194508093505050509250925092565b600060208284031215610b6257600080fd5b8135610aaf81610ab6565b600060208284031215610b7f57600080fd5b5035919050565b60008060408385031215610b9957600080fd5b823591506020830135610bab81610ab6565b809150509250929050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610c0857600080fd5b81518015158114610aaf57600080fd5b600060208284031215610c2a57600080fd5b8151610aaf81610ab6565b600060208284031215610c4757600080fd5b5051919050565b600060018201610c6e57634e487b7160e01b600052601160045260246000fd5b506001019056fea264697066735822122095e05c005ed92fda7c79fec97ce183c40f5cfcaeb495f9e025cc5dc6504c81a264736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

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
    protected DeepRole1(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DeepRole1(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DeepRole1(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DeepRole1(String contractAddress, Web3j web3j, TransactionManager transactionManager,
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
    public static DeepRole1 load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRole1(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DeepRole1 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRole1(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DeepRole1 load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DeepRole1(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DeepRole1 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DeepRole1(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DeepRole1> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeepRole1.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeepRole1> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeepRole1.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DeepRole1> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeepRole1.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeepRole1> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeepRole1.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
