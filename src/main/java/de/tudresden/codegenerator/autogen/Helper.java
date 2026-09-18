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
public class Helper extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200122838038062001228833981016040819052620000349162000137565b600380546001600160a01b0319166001600160a01b0384161790556200005a8162000062565b505062000273565b8051620000779060029060208401906200007b565b5050565b828054620000899062000237565b90600052602060002090601f016020900481019282620000ad5760008555620000f8565b82601f10620000c857805160ff1916838001178555620000f8565b82800160010185558215620000f8579182015b82811115620000f8578251825591602001919060010190620000db565b50620001069291506200010a565b5090565b5b808211156200010657600081556001016200010b565b634e487b7160e01b600052604160045260246000fd5b600080604083850312156200014b57600080fd5b82516001600160a01b03811681146200016357600080fd5b602084810151919350906001600160401b03808211156200018357600080fd5b818601915086601f8301126200019857600080fd5b815181811115620001ad57620001ad62000121565b604051601f8201601f19908116603f01168101908382118183101715620001d857620001d862000121565b816040528281528986848701011115620001f157600080fd5b600093505b82841015620002155784840186015181850187015292850192620001f6565b82841115620002275760008684830101525b8096505050505050509250929050565b600181811c908216806200024c57607f821691505b6020821081036200026d57634e487b7160e01b600052602260045260246000fd5b50919050565b610fa580620002836000396000f3fe608060405234801561001057600080fd5b50600436106101425760003560e01c806380009630116100b8578063c47f00271161007c578063c47f002714610271578063d0bf6d1514610284578063e405148414610297578063e51c19fd146102aa578063e959b38a146102bd578063f2f4eb26146102d057600080fd5b8063800096301461022857806395ec5ce31461023b5780639794f5dd146102435780639fd20abc1461024b578063b3cac0a51461025e57600080fd5b806335fb44391161010a57806335fb4439146101d75780633e545edb146101df57806340a9efc8146101e7578063496a3c48146101fa5780636b5a3e391461020d5780636e8eb7bc1461021557600080fd5b806301ffc9a714610147578063102864431461016f57806319b96c3f14610184578063201b631b1461019957806321fc4a6f146101ac575b600080fd5b61015a610155366004610b80565b6102e3565b60405190151581526020015b60405180910390f35b61018261017d366004610c69565b61031a565b005b61018c61037c565b6040516101669190610d08565b61015a6101a7366004610d1b565b61040e565b6003546101bf906001600160a01b031681565b6040516001600160a01b039091168152602001610166565b61018261048e565b6101826104f8565b6001546101bf906001600160a01b031681565b610182610208366004610da0565b610548565b6101bf6105ab565b6101bf610223366004610dbd565b610628565b610182610236366004610da0565b610698565b61018261073e565b61018c61077f565b610182610259366004610da0565b61080d565b61015a61026c366004610dbd565b6108b1565b61018261027f366004610dd6565b61091f565b610182610292366004610dbd565b610936565b6101826102a5366004610dd6565b610967565b6101826102b8366004610e0b565b610973565b6101826102cb366004610e6d565b6109d8565b6000546101bf906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061031457506001600160e01b03198216636f8ae2e560e11b145b92915050565b60405163c352945360e01b81526001600160a01b0382169063c352945390610346908590600401610d08565b600060405180830381600087803b15801561036057600080fd5b505af1158015610374573d6000803e3d6000fd5b505050505050565b60606002805461038b90610e92565b80601f01602080910402602001604051908101604052809291908181526020018280546103b790610e92565b80156104045780601f106103d957610100808354040283529160200191610404565b820191906000526020600020905b8154815290600101906020018083116103e757829003601f168201915b5050505050905090565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061044390879087908790600401610ecc565b6020604051808303816000875af1158015610462573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104869190610f0c565b949350505050565b600360009054906101000a90046001600160a01b03166001600160a01b03166304916f0d6040518163ffffffff1660e01b8152600401600060405180830381600087803b1580156104de57600080fd5b505af11580156104f2573d6000803e3d6000fd5b50505050565b600360009054906101000a90046001600160a01b03166001600160a01b0316632450c0236040518163ffffffff1660e01b8152600401600060405180830381600087803b1580156104de57600080fd5b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561059057600080fd5b505af11580156105a4573d6000803e3d6000fd5b5050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156105ff573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106239190610f2e565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af1158015610674573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103149190610f2e565b6001600160a01b0381161561071c5760006106ba82637365940f60e01b610a11565b90508061071a5760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084015b60405180910390fd5b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b1580156104de57600080fd5b6002805461078c90610e92565b80601f01602080910402602001604051908101604052809291908181526020018280546107b890610e92565b80156108055780601f106107da57610100808354040283529160200191610805565b820191906000526020600020905b8154815290600101906020018083116107e857829003601f168201915b505050505081565b6001600160a01b0381161561088f57600061082f826338cce89560e01b610a11565b90508061088d5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b6064820152608401610711565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa1580156108fb573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103149190610f0c565b8051610932906002906020840190610ae7565b5050565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d1590602401610576565b6109708161091f565b50565b60405163e478371b60e01b81526001600160a01b0383169063e478371b906109a19086908590600401610f4b565b600060405180830381600087803b1580156109bb57600080fd5b505af11580156109cf573d6000803e3d6000fd5b50505050505050565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401610346565b60008080610a26856301ffc9a760e01b610ab3565b9092509050811580610a36575080155b15610a4657600092505050610314565b610a58856001600160e01b0319610ab3565b9092509050811580610a6957508015155b15610a7957600092505050610314565b610a838585610ab3565b9092509050600182148015610a985750806001145b15610aa857600192505050610314565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b828054610af390610e92565b90600052602060002090601f016020900481019282610b155760008555610b5b565b82601f10610b2e57805160ff1916838001178555610b5b565b82800160010185558215610b5b579182015b82811115610b5b578251825591602001919060010190610b40565b50610b67929150610b6b565b5090565b5b80821115610b675760008155600101610b6c565b600060208284031215610b9257600080fd5b81356001600160e01b031981168114610baa57600080fd5b9392505050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610bd857600080fd5b813567ffffffffffffffff80821115610bf357610bf3610bb1565b604051601f8301601f19908116603f01168101908282118183101715610c1b57610c1b610bb1565b81604052838152866020858801011115610c3457600080fd5b836020870160208301376000602085830101528094505050505092915050565b6001600160a01b038116811461097057600080fd5b60008060408385031215610c7c57600080fd5b823567ffffffffffffffff811115610c9357600080fd5b610c9f85828601610bc7565b9250506020830135610cb081610c54565b809150509250929050565b6000815180845260005b81811015610ce157602081850181015186830182015201610cc5565b81811115610cf3576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610baa6020830184610cbb565b600080600060408486031215610d3057600080fd5b8335610d3b81610c54565b9250602084013567ffffffffffffffff80821115610d5857600080fd5b818601915086601f830112610d6c57600080fd5b813581811115610d7b57600080fd5b876020828501011115610d8d57600080fd5b6020830194508093505050509250925092565b600060208284031215610db257600080fd5b8135610baa81610c54565b600060208284031215610dcf57600080fd5b5035919050565b600060208284031215610de857600080fd5b813567ffffffffffffffff811115610dff57600080fd5b61048684828501610bc7565b600080600060608486031215610e2057600080fd5b8335610e2b81610c54565b92506020840135610e3b81610c54565b9150604084013567ffffffffffffffff811115610e5757600080fd5b610e6386828701610bc7565b9150509250925092565b60008060408385031215610e8057600080fd5b823591506020830135610cb081610c54565b600181811c90821680610ea657607f821691505b602082108103610ec657634e487b7160e01b600052602260045260246000fd5b50919050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610f1e57600080fd5b81518015158114610baa57600080fd5b600060208284031215610f4057600080fd5b8151610baa81610c54565b6001600160a01b038316815260406020820181905260009061048690830184610cbb56fea2646970667358221220f25ef6600262bde662f62d93696afb7c1f546620e75eeb9557d6040cc6d8ee7664736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_ASSISTBOIL = "assistBoil";

    public static final String FUNC_ASSISTCOOK = "assistCook";

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

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SETNAME = "setName";

    public static final String FUNC_SOCIALAGENTBINDTOSOCIETY = "socialAgentBindToSociety";

    public static final String FUNC_SOCIALAGENTNAME = "socialAgentName";

    public static final String FUNC_SOCIALAGENTUNBIND = "socialAgentUnbind";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected Helper(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Helper(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Helper(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Helper(String contractAddress, Web3j web3j, TransactionManager transactionManager,
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

    public RemoteFunctionCall<TransactionReceipt> assistBoil() {
        final Function function = new Function(
                FUNC_ASSISTBOIL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> assistCook() {
        final Function function = new Function(
                FUNC_ASSISTCOOK, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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
    public static Helper load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Helper(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Helper load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Helper(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Helper load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Helper(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Helper load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Helper(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Helper> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _mealPreparationAddress, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _mealPreparationAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(Helper.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Helper> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String _mealPreparationAddress, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _mealPreparationAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(Helper.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Helper> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _mealPreparationAddress,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _mealPreparationAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(Helper.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Helper> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String _mealPreparationAddress,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _mealPreparationAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(Helper.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
