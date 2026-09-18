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
public class SocialAgent extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610ec1806100206000396000f3fe608060405234801561001057600080fd5b50600436106101215760003560e01c806395ec5ce3116100ad578063d0bf6d1511610071578063d0bf6d1514610240578063e405148414610253578063e51c19fd14610266578063e959b38a14610279578063f2f4eb261461028c57600080fd5b806395ec5ce3146101f75780639794f5dd146101ff5780639fd20abc14610207578063b3cac0a51461021a578063c47f00271461022d57600080fd5b806340a9efc8116100f457806340a9efc81461018b578063496a3c48146101b65780636b5a3e39146101c95780636e8eb7bc146101d157806380009630146101e457600080fd5b806301ffc9a714610126578063102864431461014e57806319b96c3f14610163578063201b631b14610178575b600080fd5b610139610134366004610a9c565b61029f565b60405190151581526020015b60405180910390f35b61016161015c366004610b85565b6102d6565b005b61016b610338565b6040516101459190610c24565b610139610186366004610c37565b6103ca565b60015461019e906001600160a01b031681565b6040516001600160a01b039091168152602001610145565b6101616101c4366004610cbc565b61044a565b61019e6104ad565b61019e6101df366004610cd9565b61052a565b6101616101f2366004610cbc565b61059a565b610161610640565b61016b61069b565b610161610215366004610cbc565b610729565b610139610228366004610cd9565b6107cd565b61016161023b366004610cf2565b61083b565b61016161024e366004610cd9565b610852565b610161610261366004610cf2565b610883565b610161610274366004610d27565b61088f565b610161610287366004610d89565b6108f4565b60005461019e906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b0319831614806102d057506001600160e01b03198216636f8ae2e560e11b145b92915050565b60405163c352945360e01b81526001600160a01b0382169063c352945390610302908590600401610c24565b600060405180830381600087803b15801561031c57600080fd5b505af1158015610330573d6000803e3d6000fd5b505050505050565b60606002805461034790610dae565b80601f016020809104026020016040519081016040528092919081815260200182805461037390610dae565b80156103c05780601f10610395576101008083540402835291602001916103c0565b820191906000526020600020905b8154815290600101906020018083116103a357829003601f168201915b5050505050905090565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b906103ff90879087908790600401610de8565b6020604051808303816000875af115801561041e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104429190610e28565b949350505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561049257600080fd5b505af11580156104a6573d6000803e3d6000fd5b5050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa158015610501573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105259190610e4a565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af1158015610576573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102d09190610e4a565b6001600160a01b0381161561061e5760006105bc82637365940f60e01b61092d565b90508061061c5760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084015b60405180910390fd5b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561068157600080fd5b505af1158015610695573d6000803e3d6000fd5b50505050565b600280546106a890610dae565b80601f01602080910402602001604051908101604052809291908181526020018280546106d490610dae565b80156107215780601f106106f657610100808354040283529160200191610721565b820191906000526020600020905b81548152906001019060200180831161070457829003601f168201915b505050505081565b6001600160a01b038116156107ab57600061074b826338cce89560e01b61092d565b9050806107a95760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b6064820152608401610613565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa158015610817573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102d09190610e28565b805161084e906002906020840190610a03565b5050565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d1590602401610478565b61088c8161083b565b50565b60405163e478371b60e01b81526001600160a01b0383169063e478371b906108bd9086908590600401610e67565b600060405180830381600087803b1580156108d757600080fd5b505af11580156108eb573d6000803e3d6000fd5b50505050505050565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401610302565b60008080610942856301ffc9a760e01b6109cf565b9092509050811580610952575080155b15610962576000925050506102d0565b610974856001600160e01b03196109cf565b909250905081158061098557508015155b15610995576000925050506102d0565b61099f85856109cf565b90925090506001821480156109b45750806001145b156109c4576001925050506102d0565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b828054610a0f90610dae565b90600052602060002090601f016020900481019282610a315760008555610a77565b82601f10610a4a57805160ff1916838001178555610a77565b82800160010185558215610a77579182015b82811115610a77578251825591602001919060010190610a5c565b50610a83929150610a87565b5090565b5b80821115610a835760008155600101610a88565b600060208284031215610aae57600080fd5b81356001600160e01b031981168114610ac657600080fd5b9392505050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610af457600080fd5b813567ffffffffffffffff80821115610b0f57610b0f610acd565b604051601f8301601f19908116603f01168101908282118183101715610b3757610b37610acd565b81604052838152866020858801011115610b5057600080fd5b836020870160208301376000602085830101528094505050505092915050565b6001600160a01b038116811461088c57600080fd5b60008060408385031215610b9857600080fd5b823567ffffffffffffffff811115610baf57600080fd5b610bbb85828601610ae3565b9250506020830135610bcc81610b70565b809150509250929050565b6000815180845260005b81811015610bfd57602081850181015186830182015201610be1565b81811115610c0f576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610ac66020830184610bd7565b600080600060408486031215610c4c57600080fd5b8335610c5781610b70565b9250602084013567ffffffffffffffff80821115610c7457600080fd5b818601915086601f830112610c8857600080fd5b813581811115610c9757600080fd5b876020828501011115610ca957600080fd5b6020830194508093505050509250925092565b600060208284031215610cce57600080fd5b8135610ac681610b70565b600060208284031215610ceb57600080fd5b5035919050565b600060208284031215610d0457600080fd5b813567ffffffffffffffff811115610d1b57600080fd5b61044284828501610ae3565b600080600060608486031215610d3c57600080fd5b8335610d4781610b70565b92506020840135610d5781610b70565b9150604084013567ffffffffffffffff811115610d7357600080fd5b610d7f86828701610ae3565b9150509250925092565b60008060408385031215610d9c57600080fd5b823591506020830135610bcc81610b70565b600181811c90821680610dc257607f821691505b602082108103610de257634e487b7160e01b600052602260045260246000fd5b50919050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610e3a57600080fd5b81518015158114610ac657600080fd5b600060208284031215610e5c57600080fd5b8151610ac681610b70565b6001600160a01b038316815260406020820181905260009061044290830184610bd756fea2646970667358221220ac5b6466366d40e52732bbeb494d65c69a9894816f2fff7be9278d6ad9500a0164736f6c634300080d0033";

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

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SETNAME = "setName";

    public static final String FUNC_SOCIALAGENTBINDTOSOCIETY = "socialAgentBindToSociety";

    public static final String FUNC_SOCIALAGENTNAME = "socialAgentName";

    public static final String FUNC_SOCIALAGENTUNBIND = "socialAgentUnbind";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected SocialAgent(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialAgent(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialAgent(String contractAddress, Web3j web3j,
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
    public static SocialAgent load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgent(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgent(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialAgent load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new SocialAgent(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialAgent(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialAgent> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialAgent.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialAgent> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialAgent.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocialAgent> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialAgent.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialAgent> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialAgent.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
