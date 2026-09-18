package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
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
public class SocialAgentStateMachine extends Contract {
    public static final String BINARY = "60806040526000805460ff60a01b191690554260015534801561002157600080fd5b50600080546001600160a01b03191633179055610488806100436000396000f3fe6080604052600436106100595760003560e01c806314007c6d146100be5780632618c7f6146100d557806328648ec51461010c5780633939b59c146101215780634c067ba7146101295780638da5cb5b1461014d57610060565b3661006057005b60405162461bcd60e51b815260206004820152602860248201527f436f6e747261637420646f6573206e6f742061636365707420646972656374206044820152677061796d656e747360c01b60648201526084015b60405180910390fd5b3480156100ca57600080fd5b506100d3610185565b005b3480156100e157600080fd5b506000546100f690600160a01b900460ff1681565b6040516101039190610404565b60405180910390f35b34801561011857600080fd5b506100d36101e8565b6100d3610258565b34801561013557600080fd5b5061013f60015481565b604051908152602001610103565b34801561015957600080fd5b5060005461016d906001600160a01b031681565b6040516001600160a01b039091168152602001610103565b600054600160a01b900460ff1660038111156101a3576101a36103ee565b6101ae90600161042c565b60038111156101bf576101bf6103ee565b6000805460ff60a01b1916600160a01b8360038111156101e1576101e16103ee565b0217905550565b6000546001600160a01b0316331461024e5760405162461bcd60e51b8152602060048201526024808201527f4f6e6c7920746865206f776e65722063616e207472616e736974696f6e2073746044820152636174657360e01b60648201526084016100b5565b610256610185565b565b6001600054600160a01b900460ff166003811115610278576102786103ee565b1480156102945750600154610290906201518061042c565b4210155b156102a1576102a1610185565b6003600054600160a01b900460ff1660038111156102c1576102c16103ee565b1480156102dd57506001546102d9906202a30061042c565b4210155b156102ea576102ea610185565b600280600054600160a01b900460ff16600381111561030b5761030b6103ee565b146103775760405162461bcd60e51b815260206004820152603660248201527f6167656e742073746174652073686f756c6420626520636f6d706c6965642077604482015275697468207468652061737369676e656420737461746560501b60648201526084016100b5565b6000546001600160a01b031633146103e05760405162461bcd60e51b815260206004820152602660248201527f4f6e6c7920746865206f776e65722063616e207465726d696e61746520746865604482015265081859d95b9d60d21b60648201526084016100b5565b6000546001600160a01b0316ff5b634e487b7160e01b600052602160045260246000fd5b602081016004831061042657634e487b7160e01b600052602160045260246000fd5b91905290565b6000821982111561044d57634e487b7160e01b600052601160045260246000fd5b50019056fea2646970667358221220b5f66298fe6b3f6c9cb8fe447b0a7529a0a75f0f3af6fa58034b896ac4e36d5964736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_AGENTCREATIONTIME = "agentCreationTime";

    public static final String FUNC_AGENTENDING = "agentEnding";

    public static final String FUNC_AGENTSTATE = "agentState";

    public static final String FUNC_MANUALTRANSITION = "manualTransition";

    public static final String FUNC_NEXTSTATE = "nextState";

    public static final String FUNC_OWNER = "owner";

    @Deprecated
    protected SocialAgentStateMachine(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialAgentStateMachine(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialAgentStateMachine(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialAgentStateMachine(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> agentCreationTime() {
        final Function function = new Function(FUNC_AGENTCREATIONTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> agentEnding(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_AGENTENDING, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> agentState() {
        final Function function = new Function(FUNC_AGENTSTATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> manualTransition() {
        final Function function = new Function(
                FUNC_MANUALTRANSITION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> nextState() {
        final Function function = new Function(
                FUNC_NEXTSTATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static SocialAgentStateMachine load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentStateMachine(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialAgentStateMachine load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentStateMachine(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialAgentStateMachine load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SocialAgentStateMachine(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialAgentStateMachine load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialAgentStateMachine(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialAgentStateMachine> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialAgentStateMachine.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocialAgentStateMachine> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialAgentStateMachine.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialAgentStateMachine> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialAgentStateMachine.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialAgentStateMachine> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialAgentStateMachine.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
