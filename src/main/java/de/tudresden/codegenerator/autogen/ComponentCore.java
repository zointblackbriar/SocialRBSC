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
public class ComponentCore extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061098c806100206000396000f3fe608060405234801561001057600080fd5b50600436106100f55760003560e01c80637ca4158b11610097578063e959b38a11610066578063e959b38a14610219578063ec32a2531461022c578063ef01b2611461023e578063f63547441461024b57600080fd5b80637ca4158b146101b357806395ec5ce3146101c6578063b3cac0a5146101db578063d0bf6d151461020657600080fd5b8063496a3c48116100d3578063496a3c4814610160578063523132d2146101755780636b5a3e391461018f5780636e8eb7bc146101a057600080fd5b806301ffc9a7146100fa578063201b631b146101225780632a2dc87a14610135575b600080fd5b61010d6101083660046107c4565b610274565b60405190151581526020015b60405180910390f35b61010d610130366004610811565b6102ab565b600254610148906001600160a01b031681565b6040516001600160a01b039091168152602001610119565b61017361016e366004610894565b61032c565b005b60015461014890630100000090046001600160a01b031681565b6002546001600160a01b0316610148565b6101486101ae3660046108af565b6103dd565b60015461010d9062010000900460ff1681565b610173600280546001600160a01b0319169055565b61010d6101e93660046108af565b6000908152602081905260409020546001600160a01b0316151590565b6101736102143660046108af565b6104bc565b6101736102273660046108c8565b6105ad565b60015461010d90610100900460ff1681565b60015461010d9060ff1681565b6101486102593660046108af565b6000602081905290815260409020546001600160a01b031681565b60006301ffc9a760e01b6001600160e01b0319831614806102a557506001600160e01b03198216637365940f60e01b145b92915050565b60405163176fe4cf60e31b815260009084906001600160a01b0382169063bb7f2678906102e0908490889088906004016108f4565b6020604051808303816000875af11580156102ff573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103239190610934565b95945050505050565b61033d816338cce89560e01b6106ee565b6001805462ff00001916620100009215158302179081905560ff919004166103bb5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084015b60405180910390fd5b600280546001600160a01b0319166001600160a01b0392909216919091179055565b60008181526020819052604080822054905163b3cac0a560e01b8152600481018490526001600160a01b0390911690309063b3cac0a590602401602060405180830381865afa158015610434573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104589190610934565b156102a557604051630800096360e41b81523060048201526001600160a01b03821690638000963090602401600060405180830381600087803b15801561049e57600080fd5b505af11580156104b2573d6000803e3d6000fd5b5050505092915050565b60405163b3cac0a560e01b815260048101829052309063b3cac0a590602401602060405180830381865afa1580156104f8573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061051c9190610934565b6105235750565b600081815260208190526040808220549051630800096360e41b815260048101929092526001600160a01b0316908190638000963090602401600060405180830381600087803b15801561057657600080fd5b505af115801561058a573d6000803e3d6000fd5b5050506000928352505060208190526040902080546001600160a01b0319169055565b6001600160a01b0381166105bf575050565b600180546301000000600160b81b03191663010000006001600160a01b038416021790556105f481636f8ae2e560e11b6106ee565b6001805461ff0019166101009215158302179081905560ff9190041661066d5760405162461bcd60e51b815260206004820152602860248201527f446f65736e277420737570706f727420436f6d706f6e656e74526f6c6520696e6044820152673a32b93330b1b29760c11b60648201526084016103b2565b6000828152602081905260409081902080546001600160a01b0319166001600160a01b0384169081179091559051630800096360e41b8152306004820152829190638000963090602401600060405180830381600087803b1580156106d157600080fd5b505af11580156106e5573d6000803e3d6000fd5b50505050505050565b60008080610703856301ffc9a760e01b610790565b9092509050811580610713575080155b15610723576000925050506102a5565b610735856001600160e01b0319610790565b909250905081158061074657508015155b15610756576000925050506102a5565b6107608585610790565b90925090506001821480156107755750806001145b15610785576001925050506102a5565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b6000602082840312156107d657600080fd5b81356001600160e01b0319811681146107ee57600080fd5b9392505050565b80356001600160a01b038116811461080c57600080fd5b919050565b60008060006040848603121561082657600080fd5b61082f846107f5565b9250602084013567ffffffffffffffff8082111561084c57600080fd5b818601915086601f83011261086057600080fd5b81358181111561086f57600080fd5b87602082850101111561088157600080fd5b6020830194508093505050509250925092565b6000602082840312156108a657600080fd5b6107ee826107f5565b6000602082840312156108c157600080fd5b5035919050565b600080604083850312156108db57600080fd5b823591506108eb602084016107f5565b90509250929050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b60006020828403121561094657600080fd5b815180151581146107ee57600080fdfea2646970667358221220d30af69ce328d02dccb592cd21d327a9a0f950f33424d8d9ca5d39fb9a84fdcb64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_COMPARTMENTINUSE = "compartmentInUse";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_ISCOMPARTMENT = "isCompartment";

    public static final String FUNC_ISCOMPONENTROLE = "isComponentRole";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_ISSUITABLEFORDEEPROLES = "isSuitableForDeepRoles";

    public static final String FUNC_PLAYEDROLES = "playedRoles";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_STATEVARIABLEROLE = "stateVariableRole";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected ComponentCore(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ComponentCore(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ComponentCore(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ComponentCore(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> activateCompartment(String _compartmentAddress) {
        final Function function = new Function(
                FUNC_ACTIVATECOMPARTMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _compartmentAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRole(byte[] _playedRoleName, String _role) {
        final Function function = new Function(
                FUNC_ADDROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_playedRoleName), 
                new org.web3j.abi.datatypes.Address(160, _role)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> compartmentInUse() {
        final Function function = new Function(FUNC_COMPARTMENTINUSE, 
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

    public RemoteFunctionCall<TransactionReceipt> getRole(byte[] _roleName) {
        final Function function = new Function(
                FUNC_GETROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleName)), 
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

    public RemoteFunctionCall<Boolean> isCompartment() {
        final Function function = new Function(FUNC_ISCOMPARTMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isComponentRole() {
        final Function function = new Function(FUNC_ISCOMPONENTROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isPlayingRole(byte[] _playedRoleName) {
        final Function function = new Function(FUNC_ISPLAYINGROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_playedRoleName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isSuitableForDeepRoles() {
        final Function function = new Function(FUNC_ISSUITABLEFORDEEPROLES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> playedRoles(byte[] param0) {
        final Function function = new Function(FUNC_PLAYEDROLES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeRole(byte[] _playedRoleName) {
        final Function function = new Function(
                FUNC_REMOVEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_playedRoleName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> stateVariableRole() {
        final Function function = new Function(FUNC_STATEVARIABLEROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceID) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static ComponentCore load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new ComponentCore(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ComponentCore load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ComponentCore(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ComponentCore load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new ComponentCore(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ComponentCore load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ComponentCore(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ComponentCore> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ComponentCore.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ComponentCore> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ComponentCore.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ComponentCore> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ComponentCore.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ComponentCore> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ComponentCore.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
