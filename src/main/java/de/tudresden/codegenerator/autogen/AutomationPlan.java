package de.tudresden.codegenerator.autogen;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
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
public class AutomationPlan extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506000805464ffffffffff191690556104d38061002e6000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806367c4987a1161005b57806367c4987a146100fa5780636c15052c14610102578063a30b24881461010a578063bd4631c91461011d57600080fd5b80630574070c1461008257806328bfc79b146100e85780634b44d151146100f2575b600080fd5b6000546100b79060ff808216916101008104821691620100008204811691630100000081048216916401000000009091041685565b6040805195151586529315156020860152911515848401521515606084015215156080830152519081900360a00190f35b6100f0610125565b005b6100f06101e1565b6100f06102a8565b6100f061036e565b6100f06000805464ffffffffff19169055565b6100f0610444565b600054610100900460ff166101815760405162461bcd60e51b815260206004820152601e60248201527f416e6f6d616c79206d757374206265206465746563746564206669727374000060448201526064015b60405180910390fd5b60005462010000900460ff16156101ce5760405162461bcd60e51b8152602060048201526011602482015270105b1c9958591e48191a5859db9bdcd959607a1b6044820152606401610178565b6000805462ff0000191662010000179055565b60005462010000900460ff166102395760405162461bcd60e51b815260206004820152601b60248201527f446961676e6f736973206d7573742068617070656e20666972737400000000006044820152606401610178565b6000546301000000900460ff16156102935760405162461bcd60e51b815260206004820152601c60248201527f52656d6564696174696f6e20616c7265616479206578656375746564000000006044820152606401610178565b6000805463ff00000019166301000000179055565b60005460ff166103055760405162461bcd60e51b815260206004820152602260248201527f426173656c696e65206d7573742062652065737461626c6973686564206669726044820152611cdd60f21b6064820152608401610178565b600054610100900460ff161561035d5760405162461bcd60e51b815260206004820152601860248201527f416e6f6d616c7920616c726561647920646574656374656400000000000000006044820152606401610178565b6000805461ff001916610100179055565b6000546301000000900460ff166103d25760405162461bcd60e51b815260206004820152602260248201527f52656d6564696174696f6e206d757374206265206578656375746564206669726044820152611cdd60f21b6064820152608401610178565b600054640100000000900460ff161561042d5760405162461bcd60e51b815260206004820152601960248201527f4b6e6f776c6564676520616c72656164792075706461746564000000000000006044820152606401610178565b6000805464ff000000001916640100000000179055565b60005460ff161561048e5760405162461bcd60e51b815260206004820152601460248201527310985cd95b1a5b9948185b1c9958591e481cd95d60621b6044820152606401610178565b6000805460ff1916600117905556fea2646970667358221220eb30c0c360f6ae7c7a465b42486e195c82ab0f5e63b4997fad90b4fb6cecebee64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CURRENTPLAN = "currentPlan";

    public static final String FUNC_MARKANOMALY = "markAnomaly";

    public static final String FUNC_MARKBASELINE = "markBaseline";

    public static final String FUNC_MARKDIAGNOSIS = "markDiagnosis";

    public static final String FUNC_MARKLEARNING = "markLearning";

    public static final String FUNC_MARKREMEDIATION = "markRemediation";

    public static final String FUNC_RESETPLAN = "resetPlan";

    public static final Event ANOMALYDETECTED_EVENT = new Event("AnomalyDetected", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event BASELINEESTABLISHED_EVENT = new Event("BaselineEstablished", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event KNOWLEDGEUPDATED_EVENT = new Event("KnowledgeUpdated", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event PLANRESET_EVENT = new Event("PlanReset", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event REMEDIATIONEXECUTED_EVENT = new Event("RemediationExecuted", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event ROOTCAUSEDIAGNOSED_EVENT = new Event("RootCauseDiagnosed", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected AutomationPlan(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AutomationPlan(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AutomationPlan(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AutomationPlan(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<AnomalyDetectedEventResponse> getAnomalyDetectedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ANOMALYDETECTED_EVENT, transactionReceipt);
        ArrayList<AnomalyDetectedEventResponse> responses = new ArrayList<AnomalyDetectedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AnomalyDetectedEventResponse typedResponse = new AnomalyDetectedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AnomalyDetectedEventResponse getAnomalyDetectedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ANOMALYDETECTED_EVENT, log);
        AnomalyDetectedEventResponse typedResponse = new AnomalyDetectedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<AnomalyDetectedEventResponse> anomalyDetectedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAnomalyDetectedEventFromLog(log));
    }

    public Flowable<AnomalyDetectedEventResponse> anomalyDetectedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ANOMALYDETECTED_EVENT));
        return anomalyDetectedEventFlowable(filter);
    }

    public static List<BaselineEstablishedEventResponse> getBaselineEstablishedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BASELINEESTABLISHED_EVENT, transactionReceipt);
        ArrayList<BaselineEstablishedEventResponse> responses = new ArrayList<BaselineEstablishedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BaselineEstablishedEventResponse typedResponse = new BaselineEstablishedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BaselineEstablishedEventResponse getBaselineEstablishedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BASELINEESTABLISHED_EVENT, log);
        BaselineEstablishedEventResponse typedResponse = new BaselineEstablishedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<BaselineEstablishedEventResponse> baselineEstablishedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBaselineEstablishedEventFromLog(log));
    }

    public Flowable<BaselineEstablishedEventResponse> baselineEstablishedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BASELINEESTABLISHED_EVENT));
        return baselineEstablishedEventFlowable(filter);
    }

    public static List<KnowledgeUpdatedEventResponse> getKnowledgeUpdatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(KNOWLEDGEUPDATED_EVENT, transactionReceipt);
        ArrayList<KnowledgeUpdatedEventResponse> responses = new ArrayList<KnowledgeUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            KnowledgeUpdatedEventResponse typedResponse = new KnowledgeUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static KnowledgeUpdatedEventResponse getKnowledgeUpdatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(KNOWLEDGEUPDATED_EVENT, log);
        KnowledgeUpdatedEventResponse typedResponse = new KnowledgeUpdatedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<KnowledgeUpdatedEventResponse> knowledgeUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getKnowledgeUpdatedEventFromLog(log));
    }

    public Flowable<KnowledgeUpdatedEventResponse> knowledgeUpdatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(KNOWLEDGEUPDATED_EVENT));
        return knowledgeUpdatedEventFlowable(filter);
    }

    public static List<PlanResetEventResponse> getPlanResetEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PLANRESET_EVENT, transactionReceipt);
        ArrayList<PlanResetEventResponse> responses = new ArrayList<PlanResetEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PlanResetEventResponse typedResponse = new PlanResetEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PlanResetEventResponse getPlanResetEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PLANRESET_EVENT, log);
        PlanResetEventResponse typedResponse = new PlanResetEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<PlanResetEventResponse> planResetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPlanResetEventFromLog(log));
    }

    public Flowable<PlanResetEventResponse> planResetEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLANRESET_EVENT));
        return planResetEventFlowable(filter);
    }

    public static List<RemediationExecutedEventResponse> getRemediationExecutedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REMEDIATIONEXECUTED_EVENT, transactionReceipt);
        ArrayList<RemediationExecutedEventResponse> responses = new ArrayList<RemediationExecutedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RemediationExecutedEventResponse typedResponse = new RemediationExecutedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RemediationExecutedEventResponse getRemediationExecutedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(REMEDIATIONEXECUTED_EVENT, log);
        RemediationExecutedEventResponse typedResponse = new RemediationExecutedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<RemediationExecutedEventResponse> remediationExecutedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRemediationExecutedEventFromLog(log));
    }

    public Flowable<RemediationExecutedEventResponse> remediationExecutedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REMEDIATIONEXECUTED_EVENT));
        return remediationExecutedEventFlowable(filter);
    }

    public static List<RootCauseDiagnosedEventResponse> getRootCauseDiagnosedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ROOTCAUSEDIAGNOSED_EVENT, transactionReceipt);
        ArrayList<RootCauseDiagnosedEventResponse> responses = new ArrayList<RootCauseDiagnosedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RootCauseDiagnosedEventResponse typedResponse = new RootCauseDiagnosedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RootCauseDiagnosedEventResponse getRootCauseDiagnosedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROOTCAUSEDIAGNOSED_EVENT, log);
        RootCauseDiagnosedEventResponse typedResponse = new RootCauseDiagnosedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<RootCauseDiagnosedEventResponse> rootCauseDiagnosedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRootCauseDiagnosedEventFromLog(log));
    }

    public Flowable<RootCauseDiagnosedEventResponse> rootCauseDiagnosedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROOTCAUSEDIAGNOSED_EVENT));
        return rootCauseDiagnosedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple5<Boolean, Boolean, Boolean, Boolean, Boolean>> currentPlan() {
        final Function function = new Function(FUNC_CURRENTPLAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple5<Boolean, Boolean, Boolean, Boolean, Boolean>>(function,
                new Callable<Tuple5<Boolean, Boolean, Boolean, Boolean, Boolean>>() {
                    @Override
                    public Tuple5<Boolean, Boolean, Boolean, Boolean, Boolean> call() throws
                            Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<Boolean, Boolean, Boolean, Boolean, Boolean>(
                                (Boolean) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> markAnomaly() {
        final Function function = new Function(
                FUNC_MARKANOMALY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> markBaseline() {
        final Function function = new Function(
                FUNC_MARKBASELINE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> markDiagnosis() {
        final Function function = new Function(
                FUNC_MARKDIAGNOSIS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> markLearning() {
        final Function function = new Function(
                FUNC_MARKLEARNING, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> markRemediation() {
        final Function function = new Function(
                FUNC_MARKREMEDIATION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> resetPlan() {
        final Function function = new Function(
                FUNC_RESETPLAN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static AutomationPlan load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new AutomationPlan(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AutomationPlan load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AutomationPlan(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AutomationPlan load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new AutomationPlan(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AutomationPlan load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AutomationPlan(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AutomationPlan> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AutomationPlan.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<AutomationPlan> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AutomationPlan.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<AutomationPlan> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AutomationPlan.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<AutomationPlan> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AutomationPlan.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class AnomalyDetectedEventResponse extends BaseEventResponse {
    }

    public static class BaselineEstablishedEventResponse extends BaseEventResponse {
    }

    public static class KnowledgeUpdatedEventResponse extends BaseEventResponse {
    }

    public static class PlanResetEventResponse extends BaseEventResponse {
    }

    public static class RemediationExecutedEventResponse extends BaseEventResponse {
    }

    public static class RootCauseDiagnosedEventResponse extends BaseEventResponse {
    }
}
