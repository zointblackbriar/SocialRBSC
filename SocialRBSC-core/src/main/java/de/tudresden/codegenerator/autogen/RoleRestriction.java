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
import org.web3j.abi.datatypes.Utf8String;
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
public class RoleRestriction extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600380546001600160a01b03191633179055610b60806100326000396000f3fe608060405234801561001057600080fd5b50600436106101215760003560e01c80638da5cb5b116100ad578063ccd3c4bc11610071578063ccd3c4bc14610248578063d0bf6d151461027c578063e959b38a1461028f578063ee8bb69f146102a2578063f2f4eb26146102ab57600080fd5b80638da5cb5b146101f557806395ec5ce3146102085780639fd20abc14610210578063b2becc2114610223578063b3cac0a51461023557600080fd5b8063496a3c48116100f4578063496a3c48146101a157806355dc9245146101b45780636b5a3e39146101c75780636e8eb7bc146101cf57806380009630146101e257600080fd5b806301ffc9a714610126578063201b631b1461014e57806340a9efc814610161578063413f7ec81461018c575b600080fd5b6101396101343660046108dd565b6102be565b60405190151581526020015b60405180910390f35b61013961015c366004610926565b6102f5565b600154610174906001600160a01b031681565b6040516001600160a01b039091168152602001610145565b61019f61019a3660046109ab565b610375565b005b61019f6101af3660046109c4565b61038f565b61019f6101c23660046109ab565b6103f2565b610174610471565b6101746101dd3660046109ab565b6104ee565b61019f6101f03660046109c4565b61055e565b600354610174906001600160a01b031681565b61019f6105ff565b61019f61021e3660046109c4565b61065a565b6002545b604051908152602001610145565b6101396102433660046109ab565b6106fe565b60408051808201825260128152711a5d081a185cc81899595b881c1b185e595960721b6020820152905161014591906109e1565b61019f61028a3660046109ab565b61076c565b61019f61029d366004610a36565b61079d565b61022760025481565b600054610174906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b0319831614806102ef57506001600160e01b03198216636f8ae2e560e11b145b92915050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061032a90879087908790600401610a66565b6020604051808303816000875af1158015610349573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061036d9190610aa6565b949350505050565b80600260008282546103879190610ade565b909155505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b1580156103d757600080fd5b505af11580156103eb573d6000803e3d6000fd5b5050505050565b600254811061045f5760405162461bcd60e51b815260206004820152602e60248201527f7765206e65656420746f20636865636b20626f756e6461727920666f7220697460448201526d656d73496e496e76656e746f727960901b60648201526084015b60405180910390fd5b80600260008282546103879190610af6565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156104c5573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104e99190610b0d565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af115801561053a573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102ef9190610b0d565b6001600160a01b038116156105dd57600061058082637365940f60e01b610807565b9050806105db5760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b6064820152608401610456565b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561064057600080fd5b505af1158015610654573d6000803e3d6000fd5b50505050565b6001600160a01b038116156106dc57600061067c826338cce89560e01b610807565b9050806106da5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b6064820152608401610456565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa158015610748573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102ef9190610aa6565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016103bd565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401600060405180830381600087803b1580156107eb57600080fd5b505af11580156107ff573d6000803e3d6000fd5b505050505050565b6000808061081c856301ffc9a760e01b6108a9565b909250905081158061082c575080155b1561083c576000925050506102ef565b61084e856001600160e01b03196108a9565b909250905081158061085f57508015155b1561086f576000925050506102ef565b61087985856108a9565b909250905060018214801561088e5750806001145b1561089e576001925050506102ef565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b6000602082840312156108ef57600080fd5b81356001600160e01b03198116811461090757600080fd5b9392505050565b6001600160a01b038116811461092357600080fd5b50565b60008060006040848603121561093b57600080fd5b83356109468161090e565b9250602084013567ffffffffffffffff8082111561096357600080fd5b818601915086601f83011261097757600080fd5b81358181111561098657600080fd5b87602082850101111561099857600080fd5b6020830194508093505050509250925092565b6000602082840312156109bd57600080fd5b5035919050565b6000602082840312156109d657600080fd5b81356109078161090e565b600060208083528351808285015260005b81811015610a0e578581018301518582016040015282016109f2565b81811115610a20576000604083870101525b50601f01601f1916929092016040019392505050565b60008060408385031215610a4957600080fd5b823591506020830135610a5b8161090e565b809150509250929050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610ab857600080fd5b8151801515811461090757600080fd5b634e487b7160e01b600052601160045260246000fd5b60008219821115610af157610af1610ac8565b500190565b600082821015610b0857610b08610ac8565b500390565b600060208284031215610b1f57600080fd5b81516109078161090e56fea264697066735822122084ca3a4d22fc446195112af8f2427a8c53690b9692de5f8624fdad629ee5897a64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETINVENTORYSTATUS = "getInventoryStatus";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_ITEMSININVENTORYRETAILER = "itemsInInventoryRetailer";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PLAYCONTRACTFORIDENTITY = "playContractForIdentity";

    public static final String FUNC_POPINVENTORY = "popInventory";

    public static final String FUNC_PUSHINVENTORY = "pushInventory";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected RoleRestriction(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RoleRestriction(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RoleRestriction(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RoleRestriction(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<BigInteger> getInventoryStatus() {
        final Function function = new Function(FUNC_GETINVENTORYSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<Boolean> isPlayingRole(byte[] spec) {
        final Function function = new Function(FUNC_ISPLAYINGROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> itemsInInventoryRetailer() {
        final Function function = new Function(FUNC_ITEMSININVENTORYRETAILER, 
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

    public RemoteFunctionCall<String> playContractForIdentity() {
        final Function function = new Function(FUNC_PLAYCONTRACTFORIDENTITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> popInventory(BigInteger _item) {
        final Function function = new Function(
                FUNC_POPINVENTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_item)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> pushInventory(BigInteger _item) {
        final Function function = new Function(
                FUNC_PUSHINVENTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_item)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    @Deprecated
    public static RoleRestriction load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new RoleRestriction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RoleRestriction load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RoleRestriction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RoleRestriction load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new RoleRestriction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RoleRestriction load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RoleRestriction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RoleRestriction> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RoleRestriction.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<RoleRestriction> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RoleRestriction.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<RoleRestriction> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RoleRestriction.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<RoleRestriction> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RoleRestriction.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
