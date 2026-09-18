package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class Customer extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620011c5380380620011c5833981016040819052620000349162000137565b600380546001600160a01b0319166001600160a01b0384161790556200005a8162000062565b505062000273565b8051620000779060029060208401906200007b565b5050565b828054620000899062000237565b90600052602060002090601f016020900481019282620000ad5760008555620000f8565b82601f10620000c857805160ff1916838001178555620000f8565b82800160010185558215620000f8579182015b82811115620000f8578251825591602001919060010190620000db565b50620001069291506200010a565b5090565b5b808211156200010657600081556001016200010b565b634e487b7160e01b600052604160045260246000fd5b600080604083850312156200014b57600080fd5b82516001600160a01b03811681146200016357600080fd5b602084810151919350906001600160401b03808211156200018357600080fd5b818601915086601f8301126200019857600080fd5b815181811115620001ad57620001ad62000121565b604051601f8201601f19908116603f01168101908382118183101715620001d857620001d862000121565b816040528281528986848701011115620001f157600080fd5b600093505b82841015620002155784840186015181850187015292850192620001f6565b82841115620002275760008684830101525b8096505050505050509250929050565b600181811c908216806200024c57607f821691505b6020821081036200026d57634e487b7160e01b600052602260045260246000fd5b50919050565b610f4280620002836000396000f3fe608060405234801561001057600080fd5b50600436106101375760003560e01c806380009630116100b8578063c47f00271161007c578063c47f00271461025e578063d0bf6d1514610271578063e405148414610284578063e51c19fd14610297578063e959b38a146102aa578063f2f4eb26146102bd57600080fd5b8063800096301461021557806395ec5ce3146102285780639794f5dd146102305780639fd20abc14610238578063b3cac0a51461024b57600080fd5b806340a9efc8116100ff57806340a9efc8146101cc578063496a3c48146101df5780636aa28393146101f25780636b5a3e39146101fa5780636e8eb7bc1461020257600080fd5b806301ffc9a71461013c578063102864431461016457806319b96c3f14610179578063201b631b1461018e57806321fc4a6f146101a1575b600080fd5b61014f61014a366004610b1d565b6102d0565b60405190151581526020015b60405180910390f35b610177610172366004610c06565b610307565b005b610181610369565b60405161015b9190610ca5565b61014f61019c366004610cb8565b6103fb565b6003546101b4906001600160a01b031681565b6040516001600160a01b03909116815260200161015b565b6001546101b4906001600160a01b031681565b6101776101ed366004610d3d565b61047b565b6101776104de565b6101b4610548565b6101b4610210366004610d5a565b6105c5565b610177610223366004610d3d565b610635565b6101776106db565b61018161071c565b610177610246366004610d3d565b6107aa565b61014f610259366004610d5a565b61084e565b61017761026c366004610d73565b6108bc565b61017761027f366004610d5a565b6108d3565b610177610292366004610d73565b610904565b6101776102a5366004610da8565b610910565b6101776102b8366004610e0a565b610975565b6000546101b4906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061030157506001600160e01b03198216636f8ae2e560e11b145b92915050565b60405163c352945360e01b81526001600160a01b0382169063c352945390610333908590600401610ca5565b600060405180830381600087803b15801561034d57600080fd5b505af1158015610361573d6000803e3d6000fd5b505050505050565b60606002805461037890610e2f565b80601f01602080910402602001604051908101604052809291908181526020018280546103a490610e2f565b80156103f15780601f106103c6576101008083540402835291602001916103f1565b820191906000526020600020905b8154815290600101906020018083116103d457829003601f168201915b5050505050905090565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061043090879087908790600401610e69565b6020604051808303816000875af115801561044f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104739190610ea9565b949350505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b1580156104c357600080fd5b505af11580156104d7573d6000803e3d6000fd5b5050505050565b600360009054906101000a90046001600160a01b03166001600160a01b031663bd996a656040518163ffffffff1660e01b8152600401600060405180830381600087803b15801561052e57600080fd5b505af1158015610542573d6000803e3d6000fd5b50505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa15801561059c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105c09190610ecb565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af1158015610611573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103019190610ecb565b6001600160a01b038116156106b957600061065782637365940f60e01b6109ae565b9050806106b75760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084015b60405180910390fd5b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561052e57600080fd5b6002805461072990610e2f565b80601f016020809104026020016040519081016040528092919081815260200182805461075590610e2f565b80156107a25780601f10610777576101008083540402835291602001916107a2565b820191906000526020600020905b81548152906001019060200180831161078557829003601f168201915b505050505081565b6001600160a01b0381161561082c5760006107cc826338cce89560e01b6109ae565b90508061082a5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084016106ae565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa158015610898573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103019190610ea9565b80516108cf906002906020840190610a84565b5050565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016104a9565b61090d816108bc565b50565b60405163e478371b60e01b81526001600160a01b0383169063e478371b9061093e9086908590600401610ee8565b600060405180830381600087803b15801561095857600080fd5b505af115801561096c573d6000803e3d6000fd5b50505050505050565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401610333565b600080806109c3856301ffc9a760e01b610a50565b90925090508115806109d3575080155b156109e357600092505050610301565b6109f5856001600160e01b0319610a50565b9092509050811580610a0657508015155b15610a1657600092505050610301565b610a208585610a50565b9092509050600182148015610a355750806001145b15610a4557600192505050610301565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b828054610a9090610e2f565b90600052602060002090601f016020900481019282610ab25760008555610af8565b82601f10610acb57805160ff1916838001178555610af8565b82800160010185558215610af8579182015b82811115610af8578251825591602001919060010190610add565b50610b04929150610b08565b5090565b5b80821115610b045760008155600101610b09565b600060208284031215610b2f57600080fd5b81356001600160e01b031981168114610b4757600080fd5b9392505050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610b7557600080fd5b813567ffffffffffffffff80821115610b9057610b90610b4e565b604051601f8301601f19908116603f01168101908282118183101715610bb857610bb8610b4e565b81604052838152866020858801011115610bd157600080fd5b836020870160208301376000602085830101528094505050505092915050565b6001600160a01b038116811461090d57600080fd5b60008060408385031215610c1957600080fd5b823567ffffffffffffffff811115610c3057600080fd5b610c3c85828601610b64565b9250506020830135610c4d81610bf1565b809150509250929050565b6000815180845260005b81811015610c7e57602081850181015186830182015201610c62565b81811115610c90576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610b476020830184610c58565b600080600060408486031215610ccd57600080fd5b8335610cd881610bf1565b9250602084013567ffffffffffffffff80821115610cf557600080fd5b818601915086601f830112610d0957600080fd5b813581811115610d1857600080fd5b876020828501011115610d2a57600080fd5b6020830194508093505050509250925092565b600060208284031215610d4f57600080fd5b8135610b4781610bf1565b600060208284031215610d6c57600080fd5b5035919050565b600060208284031215610d8557600080fd5b813567ffffffffffffffff811115610d9c57600080fd5b61047384828501610b64565b600080600060608486031215610dbd57600080fd5b8335610dc881610bf1565b92506020840135610dd881610bf1565b9150604084013567ffffffffffffffff811115610df457600080fd5b610e0086828701610b64565b9150509250925092565b60008060408385031215610e1d57600080fd5b823591506020830135610c4d81610bf1565b600181811c90821680610e4357607f821691505b602082108103610e6357634e487b7160e01b600052602260045260246000fd5b50919050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610ebb57600080fd5b81518015158114610b4757600080fd5b600060208284031215610edd57600080fd5b8151610b4781610bf1565b6001600160a01b038316815260406020820181905260009061047390830184610c5856fea264697066735822122064592ca2222403bb41f089d7f193c62cbf5b03b36c43278fe6fdddb5f62d9bcb64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_CREATEAGENT = "createAgent";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_GETSOCIALAGENTNAME = "getSocialAgentName";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_MEALPREPARATION = "mealPreparation";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SERVEMEAL = "serveMeal";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SETNAME = "setName";

    public static final String FUNC_SOCIALAGENTBINDTOSOCIETY = "socialAgentBindToSociety";

    public static final String FUNC_SOCIALAGENTNAME = "socialAgentName";

    public static final String FUNC_SOCIALAGENTUNBIND = "socialAgentUnbind";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected Customer(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Customer(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Customer(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Customer(String contractAddress, Web3j web3j, TransactionManager transactionManager,
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

    public RemoteFunctionCall<TransactionReceipt> createAgent(String _socialAgentName) {
        final Function function = new Function(
                FUNC_CREATEAGENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_socialAgentName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<String> getSocialAgentName() {
        final Function function = new Function(FUNC_GETSOCIALAGENTNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<String> mealPreparation() {
        final Function function = new Function(FUNC_MEALPREPARATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeRole(byte[] spec) {
        final Function function = new Function(
                FUNC_REMOVEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> serveMeal() {
        final Function function = new Function(
                FUNC_SERVEMEAL, 
                Arrays.<Type>asList(), 
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

    public RemoteFunctionCall<TransactionReceipt> setName(String _specAgent) {
        final Function function = new Function(
                FUNC_SETNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_specAgent)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> socialAgentBindToSociety(String _societyAddress,
            String mediator, String _socialAgentName) {
        final Function function = new Function(
                FUNC_SOCIALAGENTBINDTOSOCIETY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _societyAddress), 
                new org.web3j.abi.datatypes.Address(160, mediator), 
                new org.web3j.abi.datatypes.Utf8String(_socialAgentName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> socialAgentName() {
        final Function function = new Function(FUNC_SOCIALAGENTNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> socialAgentUnbind(String _socialAgentName,
            String mediator) {
        final Function function = new Function(
                FUNC_SOCIALAGENTUNBIND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_socialAgentName), 
                new org.web3j.abi.datatypes.Address(160, mediator)), 
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
    public static Customer load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Customer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Customer load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Customer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Customer load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Customer(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Customer load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Customer(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Customer> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _mealPreparationAddress, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _mealPreparationAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(Customer.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Customer> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String _mealPreparationAddress, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _mealPreparationAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(Customer.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Customer> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _mealPreparationAddress,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _mealPreparationAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(Customer.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Customer> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String _mealPreparationAddress,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _mealPreparationAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(Customer.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
