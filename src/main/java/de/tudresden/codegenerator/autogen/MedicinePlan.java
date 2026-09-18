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
import org.web3j.tuples.generated.Tuple3;
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
public class MedicinePlan extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506000805462ffffff191690556103868061002c6000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80630574070c1461005c578063597825d01461009f5780638d29e2cb146100a9578063a30b2488146100b1578063f29f6746146100b9575b600080fd5b60005461007c9060ff808216916101008104821691620100009091041683565b604080519315158452911515602084015215159082015260600160405180910390f35b6100a76100c1565b005b6100a761014f565b6100a7610231565b6100a7610266565b60005460ff16156101195760405162461bcd60e51b815260206004820152601a60248201527f4d65646963696e6520616c72656164792064656c69766572656400000000000060448201526064015b60405180910390fd5b6000805460ff191660011781556040517ff8078f7e2fa45dd44092b82fcd7fe2abbb80fb718fd29cee23b83257ed205c7f9190a1565b60005460ff166101a15760405162461bcd60e51b815260206004820181905260248201527f4d65646963696e65206d7573742062652064656c6976657265642066697273746044820152606401610110565b600054610100900460ff16156101f95760405162461bcd60e51b815260206004820152601960248201527f4d65646963696e6520616c7265616479207665726966696564000000000000006044820152606401610110565b6000805461ff0019166101001781556040517f53551af7062eca2e4dab4ff69748637b2f3db6102a256d2619ee9bc57e87ed6c9190a1565b6000805462ffffff191681556040517f4f1d823f88bc0f895679b8c2365479a7171288a8426d00b74aa9353a44d8c0c29190a1565b600054610100900460ff166102bd5760405162461bcd60e51b815260206004820152601f60248201527f4d65646963696e65206d757374206265207665726966696564206669727374006044820152606401610110565b60005462010000900460ff16156103165760405162461bcd60e51b815260206004820152601860248201527f50617469656e7420616c7265616479206e6f74696669656400000000000000006044820152606401610110565b6000805462ff00001916620100001781556040517f6426d2e4cdceff877cc8d8428c84fb2ab093a1a59364c51a76dd807275b92d4e9190a156fea264697066735822122089ffb5d26848ed61fd5c2554dc4371b88a17f251183eadceaa7bc114be1487fd64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CURRENTPLAN = "currentPlan";

    public static final String FUNC_MARKDELIVERED = "markDelivered";

    public static final String FUNC_MARKPATIENTNOTIFIED = "markPatientNotified";

    public static final String FUNC_MARKVERIFIED = "markVerified";

    public static final String FUNC_RESETPLAN = "resetPlan";

    public static final Event MEDICINEDELIVERED_EVENT = new Event("MedicineDelivered", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event MEDICINEVERIFIED_EVENT = new Event("MedicineVerified", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event PATIENTNOTIFIED_EVENT = new Event("PatientNotified", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event PLANRESET_EVENT = new Event("PlanReset", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected MedicinePlan(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MedicinePlan(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MedicinePlan(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MedicinePlan(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<MedicineDeliveredEventResponse> getMedicineDeliveredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MEDICINEDELIVERED_EVENT, transactionReceipt);
        ArrayList<MedicineDeliveredEventResponse> responses = new ArrayList<MedicineDeliveredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MedicineDeliveredEventResponse typedResponse = new MedicineDeliveredEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MedicineDeliveredEventResponse getMedicineDeliveredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MEDICINEDELIVERED_EVENT, log);
        MedicineDeliveredEventResponse typedResponse = new MedicineDeliveredEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<MedicineDeliveredEventResponse> medicineDeliveredEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMedicineDeliveredEventFromLog(log));
    }

    public Flowable<MedicineDeliveredEventResponse> medicineDeliveredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MEDICINEDELIVERED_EVENT));
        return medicineDeliveredEventFlowable(filter);
    }

    public static List<MedicineVerifiedEventResponse> getMedicineVerifiedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MEDICINEVERIFIED_EVENT, transactionReceipt);
        ArrayList<MedicineVerifiedEventResponse> responses = new ArrayList<MedicineVerifiedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MedicineVerifiedEventResponse typedResponse = new MedicineVerifiedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MedicineVerifiedEventResponse getMedicineVerifiedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MEDICINEVERIFIED_EVENT, log);
        MedicineVerifiedEventResponse typedResponse = new MedicineVerifiedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<MedicineVerifiedEventResponse> medicineVerifiedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMedicineVerifiedEventFromLog(log));
    }

    public Flowable<MedicineVerifiedEventResponse> medicineVerifiedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MEDICINEVERIFIED_EVENT));
        return medicineVerifiedEventFlowable(filter);
    }

    public static List<PatientNotifiedEventResponse> getPatientNotifiedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PATIENTNOTIFIED_EVENT, transactionReceipt);
        ArrayList<PatientNotifiedEventResponse> responses = new ArrayList<PatientNotifiedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PatientNotifiedEventResponse typedResponse = new PatientNotifiedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PatientNotifiedEventResponse getPatientNotifiedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PATIENTNOTIFIED_EVENT, log);
        PatientNotifiedEventResponse typedResponse = new PatientNotifiedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<PatientNotifiedEventResponse> patientNotifiedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPatientNotifiedEventFromLog(log));
    }

    public Flowable<PatientNotifiedEventResponse> patientNotifiedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PATIENTNOTIFIED_EVENT));
        return patientNotifiedEventFlowable(filter);
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

    public RemoteFunctionCall<Tuple3<Boolean, Boolean, Boolean>> currentPlan() {
        final Function function = new Function(FUNC_CURRENTPLAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple3<Boolean, Boolean, Boolean>>(function,
                new Callable<Tuple3<Boolean, Boolean, Boolean>>() {
                    @Override
                    public Tuple3<Boolean, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<Boolean, Boolean, Boolean>(
                                (Boolean) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> markDelivered() {
        final Function function = new Function(
                FUNC_MARKDELIVERED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> markPatientNotified() {
        final Function function = new Function(
                FUNC_MARKPATIENTNOTIFIED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> markVerified() {
        final Function function = new Function(
                FUNC_MARKVERIFIED, 
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
    public static MedicinePlan load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new MedicinePlan(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MedicinePlan load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedicinePlan(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MedicinePlan load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new MedicinePlan(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MedicinePlan load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MedicinePlan(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MedicinePlan> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MedicinePlan.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<MedicinePlan> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MedicinePlan.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<MedicinePlan> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MedicinePlan.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<MedicinePlan> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MedicinePlan.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class MedicineDeliveredEventResponse extends BaseEventResponse {
    }

    public static class MedicineVerifiedEventResponse extends BaseEventResponse {
    }

    public static class PatientNotifiedEventResponse extends BaseEventResponse {
    }

    public static class PlanResetEventResponse extends BaseEventResponse {
    }
}
