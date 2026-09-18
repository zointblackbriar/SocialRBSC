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
import org.web3j.abi.datatypes.generated.Bytes32;
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
public class CompartmentInitiator extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161096038038061096083398101604081905261002f91610054565b600380546001600160a01b0319166001600160a01b0392909216919091179055610084565b60006020828403121561006657600080fd5b81516001600160a01b038116811461007d57600080fd5b9392505050565b6108cd806100936000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806389c15be21161007157806389c15be21461016857806395356651146101915780639ac9d095146101ba578063add39d7d146101c2578063b5fa94b2146101d5578063d0bf6d151461021157600080fd5b806301ffc9a7146100ae578063274b02a7146100d65780633e5d1dd51461010157806347064d6a1461011e5780636e8eb7bc1461013f575b600080fd5b6100c16100bc3660046106df565b610224565b60405190151581526020015b60405180910390f35b6100e96100e4366004610709565b61025b565b6040516001600160a01b0390911681526020016100cd565b61011c61010f366004610738565b8051602090910120600255565b005b61013161012c366004610738565b6103e2565b6040519081526020016100cd565b6100e961014d366004610709565b6000908152602081905260409020546001600160a01b031690565b6100e9610176366004610709565b6001602052600090815260409020546001600160a01b031681565b6100e961019f366004610709565b6000602081905290815260409020546001600160a01b031681565b600254610131565b6100c16101d0366004610709565b610448565b61011c6101e3366004610801565b60009182526001602052604090912080546001600160a01b0319166001600160a01b03909216919091179055565b61011c61021f366004610709565b6104be565b60006301ffc9a760e01b6001600160e01b03198316148061025557506001600160e01b031982166338cce89560e01b145b92915050565b6000818152600160205260408082205490516213ba5160e61b8152600481018490526001600160a01b0390911690829082906304ee9440906024016020604051808303816000875af11580156102b5573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102d99190610831565b905060006102ee82636f8ae2e560e11b610609565b9050806103515760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420436f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084015b60405180910390fd5b6001600160a01b038216156103da576000858152602081905260409081902080546001600160a01b0319166001600160a01b03851690811790915590516327f482af60e21b8152306004820152639fd20abc90602401600060405180830381600087803b1580156103c157600080fd5b505af11580156103d5573d6000803e3d6000fd5b505050505b509392505050565b60006020825111156104365760405162461bcd60e51b815260206004820152601760248201527f537472696e6720657863656564732033322062797465730000000000000000006044820152606401610348565b60006104418361084e565b9392505050565b604051631ba3adef60e21b81526004810182905260009081903090636e8eb7bc90602401602060405180830381865afa158015610489573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104ad9190610831565b6001600160a01b0316141592915050565b60405163add39d7d60e01b815260048101829052309063add39d7d90602401602060405180830381865afa1580156104fa573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061051e9190610875565b6105255750565b600081815260208190526040808220549051630800096360e41b815260048101929092526001600160a01b0316908190638000963090602401600060405180830381600087803b15801561057857600080fd5b505af115801561058c573d6000803e3d6000fd5b50506040516327f482af60e21b8152600060048201526001600160a01b0384169250639fd20abc9150602401600060405180830381600087803b1580156105d257600080fd5b505af11580156105e6573d6000803e3d6000fd5b5050506000928352505060208190526040902080546001600160a01b0319169055565b6000808061061e856301ffc9a760e01b6106ab565b909250905081158061062e575080155b1561063e57600092505050610255565b610650856001600160e01b03196106ab565b909250905081158061066157508015155b1561067157600092505050610255565b61067b85856106ab565b90925090506001821480156106905750806001145b156106a057600192505050610255565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b6000602082840312156106f157600080fd5b81356001600160e01b03198116811461044157600080fd5b60006020828403121561071b57600080fd5b5035919050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561074a57600080fd5b813567ffffffffffffffff8082111561076257600080fd5b818401915084601f83011261077657600080fd5b81358181111561078857610788610722565b604051601f8201601f19908116603f011681019083821181831017156107b0576107b0610722565b816040528281528760208487010111156107c957600080fd5b826020860160208301376000928101602001929092525095945050505050565b6001600160a01b03811681146107fe57600080fd5b50565b6000806040838503121561081457600080fd5b823591506020830135610826816107e9565b809150509250929050565b60006020828403121561084357600080fd5b8151610441816107e9565b8051602080830151919081101561086f576000198160200360031b1b821691505b50919050565b60006020828403121561088757600080fd5b8151801515811461044157600080fdfea26469706673582212205196c52ef91e94ad20b50ad54cbb76b7dd1334f839e16bfb107141e0a08e46e764736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ADDROLECREATOR = "addRoleCreator";

    public static final String FUNC_CREATECOMPARTMENTID = "createCompartmentID";

    public static final String FUNC_GETCOMPARTMENTID = "getCompartmentID";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_ROLECREATORS = "roleCreators";

    public static final String FUNC_ROLES = "roles";

    public static final String FUNC_SETDATA = "setData";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected CompartmentInitiator(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CompartmentInitiator(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CompartmentInitiator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CompartmentInitiator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addRole(byte[] _spec) {
        final Function function = new Function(
                FUNC_ADDROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRoleCreator(byte[] _roleAddress,
            String _roleCreator) {
        final Function function = new Function(
                FUNC_ADDROLECREATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress), 
                new org.web3j.abi.datatypes.Address(160, _roleCreator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createCompartmentID(String _message) {
        final Function function = new Function(
                FUNC_CREATECOMPARTMENTID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_message)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> getCompartmentID() {
        final Function function = new Function(FUNC_GETCOMPARTMENTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> getRole(byte[] _roleAddress) {
        final Function function = new Function(FUNC_GETROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> hasRole(byte[] _roleAddress) {
        final Function function = new Function(FUNC_HASROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeRole(byte[] _roleAddress) {
        final Function function = new Function(
                FUNC_REMOVEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> roleCreators(byte[] param0) {
        final Function function = new Function(FUNC_ROLECREATORS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> roles(byte[] param0) {
        final Function function = new Function(FUNC_ROLES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setData(String str) {
        final Function function = new Function(
                FUNC_SETDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(str)), 
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
    public static CompartmentInitiator load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CompartmentInitiator(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CompartmentInitiator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CompartmentInitiator(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CompartmentInitiator load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CompartmentInitiator(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CompartmentInitiator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CompartmentInitiator(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CompartmentInitiator> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _componentCoreAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _componentCoreAddress)));
        return deployRemoteCall(CompartmentInitiator.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<CompartmentInitiator> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _componentCoreAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _componentCoreAddress)));
        return deployRemoteCall(CompartmentInitiator.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CompartmentInitiator> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _componentCoreAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _componentCoreAddress)));
        return deployRemoteCall(CompartmentInitiator.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CompartmentInitiator> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _componentCoreAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _componentCoreAddress)));
        return deployRemoteCall(CompartmentInitiator.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
