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
public class TemporaryCompartment extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610823806100206000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c8063953566511161006657806395356651146101655780639ac9d0951461018e578063add39d7d1461019f578063b5fa94b2146101b2578063d0bf6d15146101ee57600080fd5b806301ffc9a7146100a3578063274b02a7146100cb5780633e5d1dd5146100f65780636e8eb7bc1461011357806389c15be21461013c575b600080fd5b6100b66100b1366004610655565b610201565b60405190151581526020015b60405180910390f35b6100de6100d9366004610686565b610238565b6040516001600160a01b0390911681526020016100c2565b6101116101043660046106b5565b8051602090910120600255565b005b6100de610121366004610686565b6000908152602081905260409020546001600160a01b031690565b6100de61014a366004610686565b6001602052600090815260409020546001600160a01b031681565b6100de610173366004610686565b6000602081905290815260409020546001600160a01b031681565b6002546040519081526020016100c2565b6100b66101ad366004610686565b6103be565b6101116101c036600461077e565b60009182526001602052604090912080546001600160a01b0319166001600160a01b03909216919091179055565b6101116101fc366004610686565b610434565b60006301ffc9a760e01b6001600160e01b03198316148061023257506001600160e01b031982166338cce89560e01b145b92915050565b6000818152600160205260408082205490516213ba5160e61b8152600481018490526001600160a01b0390911690829082906304ee9440906024016020604051808303816000875af1158015610292573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102b691906107ae565b905060006102cb82636f8ae2e560e11b61057f565b90508061032d5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420436f6d706172746d656e7420696e7465604482015265393330b1b29760d11b606482015260840160405180910390fd5b6001600160a01b038216156103b6576000858152602081905260409081902080546001600160a01b0319166001600160a01b03851690811790915590516327f482af60e21b8152306004820152639fd20abc90602401600060405180830381600087803b15801561039d57600080fd5b505af11580156103b1573d6000803e3d6000fd5b505050505b509392505050565b604051631ba3adef60e21b81526004810182905260009081903090636e8eb7bc90602401602060405180830381865afa1580156103ff573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061042391906107ae565b6001600160a01b0316141592915050565b60405163add39d7d60e01b815260048101829052309063add39d7d90602401602060405180830381865afa158015610470573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061049491906107cb565b61049b5750565b600081815260208190526040808220549051630800096360e41b815260048101929092526001600160a01b0316908190638000963090602401600060405180830381600087803b1580156104ee57600080fd5b505af1158015610502573d6000803e3d6000fd5b50506040516327f482af60e21b8152600060048201526001600160a01b0384169250639fd20abc9150602401600060405180830381600087803b15801561054857600080fd5b505af115801561055c573d6000803e3d6000fd5b5050506000928352505060208190526040902080546001600160a01b0319169055565b60008080610594856301ffc9a760e01b610621565b90925090508115806105a4575080155b156105b457600092505050610232565b6105c6856001600160e01b0319610621565b90925090508115806105d757508015155b156105e757600092505050610232565b6105f18585610621565b90925090506001821480156106065750806001145b1561061657600192505050610232565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b60006020828403121561066757600080fd5b81356001600160e01b03198116811461067f57600080fd5b9392505050565b60006020828403121561069857600080fd5b5035919050565b634e487b7160e01b600052604160045260246000fd5b6000602082840312156106c757600080fd5b813567ffffffffffffffff808211156106df57600080fd5b818401915084601f8301126106f357600080fd5b8135818111156107055761070561069f565b604051601f8201601f19908116603f0116810190838211818310171561072d5761072d61069f565b8160405282815287602084870101111561074657600080fd5b826020860160208301376000928101602001929092525095945050505050565b6001600160a01b038116811461077b57600080fd5b50565b6000806040838503121561079157600080fd5b8235915060208301356107a381610766565b809150509250929050565b6000602082840312156107c057600080fd5b815161067f81610766565b6000602082840312156107dd57600080fd5b8151801515811461067f57600080fdfea26469706673582212206d6d36616422486bb98ab3b08682799e66e29a859509a6bd69c2b4753eea869b64736f6c634300080d0033";

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

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected TemporaryCompartment(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TemporaryCompartment(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TemporaryCompartment(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TemporaryCompartment(String contractAddress, Web3j web3j,
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

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceID) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static TemporaryCompartment load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TemporaryCompartment(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TemporaryCompartment load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TemporaryCompartment(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TemporaryCompartment load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TemporaryCompartment(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TemporaryCompartment load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TemporaryCompartment(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TemporaryCompartment> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TemporaryCompartment.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<TemporaryCompartment> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TemporaryCompartment.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TemporaryCompartment> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TemporaryCompartment.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TemporaryCompartment> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TemporaryCompartment.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
