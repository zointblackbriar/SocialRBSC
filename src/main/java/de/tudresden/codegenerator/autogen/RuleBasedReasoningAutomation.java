package de.tudresden.codegenerator.autogen;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
public class RuleBasedReasoningAutomation extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161066538038061066583398101604081905261002f91610054565b600080546001600160a01b0319166001600160a01b0392909216919091179055610084565b60006020828403121561006657600080fd5b81516001600160a01b038116811461007d57600080fd5b9392505050565b6105d2806100936000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806353e06571146100515780639ceb425f14610081578063d433272514610096578063f4dd8c36146100a9575b600080fd5b600054610064906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b61009461008f36600461049d565b6100be565b005b600154610064906001600160a01b031681565b6100b1610130565b60405161007891906104cd565b6001546001600160a01b03161561010e5760405162461bcd60e51b815260206004820152601060248201526f1d1c995948185b1c9958591e481cd95d60821b604482015260640160405180910390fd5b600180546001600160a01b0319166001600160a01b0392909216919091179055565b606060008060008060008060009054906101000a90046001600160a01b03166001600160a01b0316630574070c6040518163ffffffff1660e01b815260040160a060405180830381865afa15801561018c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906101b09190610537565b945094509450945094508461024c57600080546040805163bd4631c960e01b815290516001600160a01b039092169263bd4631c99260048084019382900301818387803b15801561020057600080fd5b505af1158015610214573d6000803e3d6000fd5b505050506040518060400160405280601181526020017045737461626c697368426173656c696e6560781b8152509550505050505090565b836102da5760008054604080516333e24c3d60e11b815290516001600160a01b03909216926367c4987a9260048084019382900301818387803b15801561029257600080fd5b505af11580156102a6573d6000803e3d6000fd5b505050506040518060400160405280600d81526020016c446574656374416e6f6d616c7960981b8152509550505050505090565b826103635760008054604080516328bfc79b60e01b815290516001600160a01b03909216926328bfc79b9260048084019382900301818387803b15801561032057600080fd5b505af1158015610334573d6000803e3d6000fd5b5050505060405180604001604052806008815260200167446961676e6f736560c01b8152509550505050505090565b816103ed576000805460408051634b44d15160e01b815290516001600160a01b0390921692634b44d1519260048084019382900301818387803b1580156103a957600080fd5b505af11580156103bd573d6000803e3d6000fd5b505050506040518060400160405280600981526020016852656d65646961746560b81b8152509550505050505090565b80610473576000805460408051631b05414b60e21b815290516001600160a01b0390921692636c15052c9260048084019382900301818387803b15801561043357600080fd5b505af1158015610447573d6000803e3d6000fd5b50505050604051806040016040528060058152602001642632b0b93760d91b8152509550505050505090565b60405180604001604052806007815260200166416c6c446f6e6560c81b8152509550505050505090565b6000602082840312156104af57600080fd5b81356001600160a01b03811681146104c657600080fd5b9392505050565b600060208083528351808285015260005b818110156104fa578581018301518582016040015282016104de565b8181111561050c576000604083870101525b50601f01601f1916929092016040019392505050565b8051801515811461053257600080fd5b919050565b600080600080600060a0868803121561054f57600080fd5b61055886610522565b945061056660208701610522565b935061057460408701610522565b925061058260608701610522565b915061059060808701610522565b9050929550929590935056fea2646970667358221220d24789b50aaa95645c3e48cd12ce05f3be925b9cf906036beb8a9a843f02a97864736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GOALTREE = "goalTree";

    public static final String FUNC_PLAN = "plan";

    public static final String FUNC_RUNCYCLE = "runCycle";

    public static final String FUNC_SETGOALTREE = "setGoalTree";

    public static final Event DECISIONMADE_EVENT = new Event("DecisionMade", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected RuleBasedReasoningAutomation(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RuleBasedReasoningAutomation(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RuleBasedReasoningAutomation(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RuleBasedReasoningAutomation(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DecisionMadeEventResponse> getDecisionMadeEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DECISIONMADE_EVENT, transactionReceipt);
        ArrayList<DecisionMadeEventResponse> responses = new ArrayList<DecisionMadeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DecisionMadeEventResponse typedResponse = new DecisionMadeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DecisionMadeEventResponse getDecisionMadeEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DECISIONMADE_EVENT, log);
        DecisionMadeEventResponse typedResponse = new DecisionMadeEventResponse();
        typedResponse.log = log;
        typedResponse.action = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<DecisionMadeEventResponse> decisionMadeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDecisionMadeEventFromLog(log));
    }

    public Flowable<DecisionMadeEventResponse> decisionMadeEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DECISIONMADE_EVENT));
        return decisionMadeEventFlowable(filter);
    }

    public RemoteFunctionCall<String> goalTree() {
        final Function function = new Function(FUNC_GOALTREE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> plan() {
        final Function function = new Function(FUNC_PLAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> runCycle() {
        final Function function = new Function(
                FUNC_RUNCYCLE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setGoalTree(String _treeAddr) {
        final Function function = new Function(
                FUNC_SETGOALTREE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _treeAddr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static RuleBasedReasoningAutomation load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RuleBasedReasoningAutomation(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RuleBasedReasoningAutomation load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RuleBasedReasoningAutomation(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RuleBasedReasoningAutomation load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RuleBasedReasoningAutomation(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RuleBasedReasoningAutomation load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RuleBasedReasoningAutomation(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RuleBasedReasoningAutomation> deploy(Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider, String _planAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress)));
        return deployRemoteCall(RuleBasedReasoningAutomation.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<RuleBasedReasoningAutomation> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _planAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress)));
        return deployRemoteCall(RuleBasedReasoningAutomation.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RuleBasedReasoningAutomation> deploy(Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit,
            String _planAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress)));
        return deployRemoteCall(RuleBasedReasoningAutomation.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RuleBasedReasoningAutomation> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _planAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress)));
        return deployRemoteCall(RuleBasedReasoningAutomation.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class DecisionMadeEventResponse extends BaseEventResponse {
        public String action;
    }
}
