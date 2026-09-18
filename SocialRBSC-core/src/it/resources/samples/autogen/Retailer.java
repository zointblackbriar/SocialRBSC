/**
 * Retailer.java
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
public class Retailer extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610b42806100206000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c806395ec5ce3116100a2578063ccd3c4bc11610071578063ccd3c4bc1461022a578063d0bf6d151461025e578063e959b38a14610271578063ee8bb69f14610284578063f2f4eb261461028d57600080fd5b806395ec5ce3146101ea5780639fd20abc146101f2578063b2becc2114610205578063b3cac0a51461021757600080fd5b8063496a3c48116100e9578063496a3c481461019657806355dc9245146101a95780636b5a3e39146101bc5780636e8eb7bc146101c457806380009630146101d757600080fd5b806301ffc9a71461011b578063201b631b1461014357806340a9efc814610156578063413f7ec814610181575b600080fd5b61012e6101293660046108bf565b6102a0565b60405190151581526020015b60405180910390f35b61012e610151366004610908565b6102d7565b600154610169906001600160a01b031681565b6040516001600160a01b03909116815260200161013a565b61019461018f36600461098d565b610357565b005b6101946101a43660046109a6565b610371565b6101946101b736600461098d565b6103d4565b610169610453565b6101696101d236600461098d565b6104d0565b6101946101e53660046109a6565b610540565b6101946105e1565b6101946102003660046109a6565b61063c565b6002545b60405190815260200161013a565b61012e61022536600461098d565b6106e0565b60408051808201825260128152711a5d081a185cc81899595b881c1b185e595960721b6020820152905161013a91906109c3565b61019461026c36600461098d565b61074e565b61019461027f366004610a18565b61077f565b61020960025481565b600054610169906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b0319831614806102d157506001600160e01b03198216636f8ae2e560e11b145b92915050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061030c90879087908790600401610a48565b6020604051808303816000875af115801561032b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061034f9190610a88565b949350505050565b80600260008282546103699190610ac0565b909155505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b1580156103b957600080fd5b505af11580156103cd573d6000803e3d6000fd5b5050505050565b60025481106104415760405162461bcd60e51b815260206004820152602e60248201527f7765206e65656420746f20636865636b20626f756e6461727920666f7220697460448201526d656d73496e496e76656e746f727960901b60648201526084015b60405180910390fd5b80600260008282546103699190610ad8565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156104a7573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104cb9190610aef565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af115801561051c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102d19190610aef565b6001600160a01b038116156105bf57600061056282637365940f60e01b6107e9565b9050806105bd5760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b6064820152608401610438565b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561062257600080fd5b505af1158015610636573d6000803e3d6000fd5b50505050565b6001600160a01b038116156106be57600061065e826338cce89560e01b6107e9565b9050806106bc5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b6064820152608401610438565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa15801561072a573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102d19190610a88565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d159060240161039f565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401600060405180830381600087803b1580156107cd57600080fd5b505af11580156107e1573d6000803e3d6000fd5b505050505050565b600080806107fe856301ffc9a760e01b61088b565b909250905081158061080e575080155b1561081e576000925050506102d1565b610830856001600160e01b031961088b565b909250905081158061084157508015155b15610851576000925050506102d1565b61085b858561088b565b90925090506001821480156108705750806001145b15610880576001925050506102d1565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b6000602082840312156108d157600080fd5b81356001600160e01b0319811681146108e957600080fd5b9392505050565b6001600160a01b038116811461090557600080fd5b50565b60008060006040848603121561091d57600080fd5b8335610928816108f0565b9250602084013567ffffffffffffffff8082111561094557600080fd5b818601915086601f83011261095957600080fd5b81358181111561096857600080fd5b87602082850101111561097a57600080fd5b6020830194508093505050509250925092565b60006020828403121561099f57600080fd5b5035919050565b6000602082840312156109b857600080fd5b81356108e9816108f0565b600060208083528351808285015260005b818110156109f0578581018301518582016040015282016109d4565b81811115610a02576000604083870101525b50601f01601f1916929092016040019392505050565b60008060408385031215610a2b57600080fd5b823591506020830135610a3d816108f0565b809150509250929050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610a9a57600080fd5b815180151581146108e957600080fd5b634e487b7160e01b600052601160045260246000fd5b60008219821115610ad357610ad3610aaa565b500190565b600082821015610aea57610aea610aaa565b500390565b600060208284031215610b0157600080fd5b81516108e9816108f056fea264697066735822122063137666227c284289f56158256f217bd570319169b4da7e568fd4c2a93e4e9c64736f6c634300080d0033";

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

    public static final String FUNC_PLAYCONTRACTFORIDENTITY = "playContractForIdentity";

    public static final String FUNC_POPINVENTORY = "popInventory";

    public static final String FUNC_PUSHINVENTORY = "pushInventory";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected Retailer(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Retailer(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Retailer(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Retailer(String contractAddress, Web3j web3j, TransactionManager transactionManager,
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
    public static Retailer load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Retailer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Retailer load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Retailer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Retailer load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Retailer(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Retailer load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Retailer(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Retailer> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Retailer.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Retailer> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Retailer.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<Retailer> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Retailer.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Retailer> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Retailer.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
