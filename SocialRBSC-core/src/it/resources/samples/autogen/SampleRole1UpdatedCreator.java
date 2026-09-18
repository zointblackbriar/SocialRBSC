/**
 * SampleRole1UpdatedCreator.java
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
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class SampleRole1UpdatedCreator extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610a64806100206000396000f3fe608060405234801561001057600080fd5b506004361061002b5760003560e01c806304ee944014610030575b600080fd5b61004361003e36600461009d565b61005f565b6040516001600160a01b03909116815260200160405180910390f35b600060405161006d90610090565b604051809103906000f080158015610089573d6000803e3d6000fd5b5092915050565b610978806100b783390190565b6000602082840312156100af57600080fd5b503591905056fe608060405234801561001057600080fd5b50610958806100206000396000f3fe608060405234801561001057600080fd5b50600436106100cf5760003560e01c8063800096301161008c578063b3cac0a511610066578063b3cac0a514610198578063d0bf6d15146101ab578063e959b38a146101be578063f2f4eb26146101d157600080fd5b8063800096301461016a57806395ec5ce31461017d5780639fd20abc1461018557600080fd5b806301ffc9a7146100d4578063201b631b146100fc57806340a9efc81461010f578063496a3c481461013a5780636b5a3e391461014f5780636e8eb7bc14610157575b600080fd5b6100e76100e236600461076f565b6101e4565b60405190151581526020015b60405180910390f35b6100e761010a3660046107b8565b61021b565b600154610122906001600160a01b031681565b6040516001600160a01b0390911681526020016100f3565b61014d61014836600461083d565b61029b565b005b6101226102fe565b61012261016536600461085a565b61037b565b61014d61017836600461083d565b6103eb565b61014d610491565b61014d61019336600461083d565b6104ec565b6100e76101a636600461085a565b610590565b61014d6101b936600461085a565b6105fe565b61014d6101cc366004610873565b61062f565b600054610122906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061021557506001600160e01b03198216636f8ae2e560e11b145b92915050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b90610250908790879087906004016108a3565b6020604051808303816000875af115801561026f573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061029391906108e3565b949350505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b1580156102e357600080fd5b505af11580156102f7573d6000803e3d6000fd5b5050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa158015610352573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103769190610905565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af11580156103c7573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102159190610905565b6001600160a01b0381161561046f57600061040d82637365940f60e01b610699565b90508061046d5760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084015b60405180910390fd5b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b1580156104d257600080fd5b505af11580156104e6573d6000803e3d6000fd5b50505050565b6001600160a01b0381161561056e57600061050e826338cce89560e01b610699565b90508061056c5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b6064820152608401610464565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa1580156105da573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061021591906108e3565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016102c9565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401600060405180830381600087803b15801561067d57600080fd5b505af1158015610691573d6000803e3d6000fd5b505050505050565b600080806106ae856301ffc9a760e01b61073b565b90925090508115806106be575080155b156106ce57600092505050610215565b6106e0856001600160e01b031961073b565b90925090508115806106f157508015155b1561070157600092505050610215565b61070b858561073b565b90925090506001821480156107205750806001145b1561073057600192505050610215565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b60006020828403121561078157600080fd5b81356001600160e01b03198116811461079957600080fd5b9392505050565b6001600160a01b03811681146107b557600080fd5b50565b6000806000604084860312156107cd57600080fd5b83356107d8816107a0565b9250602084013567ffffffffffffffff808211156107f557600080fd5b818601915086601f83011261080957600080fd5b81358181111561081857600080fd5b87602082850101111561082a57600080fd5b6020830194508093505050509250925092565b60006020828403121561084f57600080fd5b8135610799816107a0565b60006020828403121561086c57600080fd5b5035919050565b6000806040838503121561088657600080fd5b823591506020830135610898816107a0565b809150509250929050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b6000602082840312156108f557600080fd5b8151801515811461079957600080fd5b60006020828403121561091757600080fd5b8151610799816107a056fea2646970667358221220aaa9b1eca9fb7c2c7e89260b0b0a59c67ffed4c0b97aebf32ead02e8018945df64736f6c634300080d0033a2646970667358221220849909a7a64a9742938b04f3f58d5430acd45fd313896d4961b0b9f9586415ce64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATEFOR = "createFor";

    @Deprecated
    protected SampleRole1UpdatedCreator(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SampleRole1UpdatedCreator(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SampleRole1UpdatedCreator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SampleRole1UpdatedCreator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> createFor(byte[] _roleAddress) {
        final Function function = new Function(
                FUNC_CREATEFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SampleRole1UpdatedCreator load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SampleRole1UpdatedCreator(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SampleRole1UpdatedCreator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SampleRole1UpdatedCreator(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SampleRole1UpdatedCreator load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SampleRole1UpdatedCreator(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SampleRole1UpdatedCreator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SampleRole1UpdatedCreator(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SampleRole1UpdatedCreator> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SampleRole1UpdatedCreator.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SampleRole1UpdatedCreator> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SampleRole1UpdatedCreator.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SampleRole1UpdatedCreator> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SampleRole1UpdatedCreator.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SampleRole1UpdatedCreator> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SampleRole1UpdatedCreator.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
