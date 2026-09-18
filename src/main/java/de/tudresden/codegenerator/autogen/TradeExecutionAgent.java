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
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class TradeExecutionAgent extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620011ec380380620011ec83398101604081905262000034916200011b565b6200003f8162000046565b5062000233565b80516200005b9060029060208401906200005f565b5050565b8280546200006d90620001f7565b90600052602060002090601f016020900481019282620000915760008555620000dc565b82601f10620000ac57805160ff1916838001178555620000dc565b82800160010185558215620000dc579182015b82811115620000dc578251825591602001919060010190620000bf565b50620000ea929150620000ee565b5090565b5b80821115620000ea5760008155600101620000ef565b634e487b7160e01b600052604160045260246000fd5b600060208083850312156200012f57600080fd5b82516001600160401b03808211156200014757600080fd5b818501915085601f8301126200015c57600080fd5b81518181111562000171576200017162000105565b604051601f8201601f19908116603f011681019083821181831017156200019c576200019c62000105565b816040528281528886848701011115620001b557600080fd5b600093505b82841015620001d95784840186015181850187015292850192620001ba565b82841115620001eb5760008684830101525b98975050505050505050565b600181811c908216806200020c57607f821691505b6020821081036200022d57634e487b7160e01b600052602260045260246000fd5b50919050565b610fa980620002436000396000f3fe608060405234801561001057600080fd5b50600436106101375760003560e01c806380009630116100b8578063c47f00271161007c578063c47f002714610269578063d0bf6d151461027c578063e40514841461028f578063e51c19fd146102a2578063e959b38a146102b5578063f2f4eb26146102c857600080fd5b8063800096301461022057806395ec5ce3146102335780639794f5dd1461023b5780639fd20abc14610243578063b3cac0a51461025657600080fd5b806340a9efc8116100ff57806340a9efc8146101b4578063496a3c48146101df578063656788f8146101f25780636b5a3e39146102055780636e8eb7bc1461020d57600080fd5b806301ffc9a71461013c578063102864431461016457806319b96c3f14610179578063201b631b1461018e5780632e3818ed146101a1575b600080fd5b61014f61014a366004610b58565b6102db565b60405190151581526020015b60405180910390f35b610177610172366004610c41565b610312565b005b610181610374565b60405161015b9190610ce0565b61014f61019c366004610cf3565b610406565b6101776101af366004610d78565b610486565b6001546101c7906001600160a01b031681565b6040516001600160a01b03909116815260200161015b565b6101776101ed366004610da4565b6104cc565b610177610200366004610dc1565b61052f565b6101c7610569565b6101c761021b366004610df6565b6105e6565b61017761022e366004610da4565b610656565b6101776106fc565b610181610757565b610177610251366004610da4565b6107e5565b61014f610264366004610df6565b610889565b610177610277366004610dc1565b6108f7565b61017761028a366004610df6565b61090e565b61017761029d366004610dc1565b61093f565b6101776102b0366004610e0f565b61094b565b6101776102c3366004610e71565b6109b0565b6000546101c7906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061030c57506001600160e01b03198216636f8ae2e560e11b145b92915050565b60405163c352945360e01b81526001600160a01b0382169063c35294539061033e908590600401610ce0565b600060405180830381600087803b15801561035857600080fd5b505af115801561036c573d6000803e3d6000fd5b505050505050565b60606002805461038390610e96565b80601f01602080910402602001604051908101604052809291908181526020018280546103af90610e96565b80156103fc5780601f106103d1576101008083540402835291602001916103fc565b820191906000526020600020905b8154815290600101906020018083116103df57829003601f168201915b5050505050905090565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061043b90879087908790600401610ed0565b6020604051808303816000875af115801561045a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061047e9190610f10565b949350505050565b60408051848152602081018490529081018290527f7af936ac70897f07a82f0b3a8b68b1c83d89652a4cbbe826051751460b84fcdb9060600160405180910390a1505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561051457600080fd5b505af1158015610528573d6000803e3d6000fd5b5050505050565b7f48c2818d70695b02ba3decbc745609e1fd738b07a44413ddf1b0f6de3744e5a48160405161055e9190610ce0565b60405180910390a150565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156105bd573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105e19190610f32565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af1158015610632573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061030c9190610f32565b6001600160a01b038116156106da57600061067882637365940f60e01b6109e9565b9050806106d85760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084015b60405180910390fd5b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561073d57600080fd5b505af1158015610751573d6000803e3d6000fd5b50505050565b6002805461076490610e96565b80601f016020809104026020016040519081016040528092919081815260200182805461079090610e96565b80156107dd5780601f106107b2576101008083540402835291602001916107dd565b820191906000526020600020905b8154815290600101906020018083116107c057829003601f168201915b505050505081565b6001600160a01b03811615610867576000610807826338cce89560e01b6109e9565b9050806108655760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084016106cf565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa1580156108d3573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061030c9190610f10565b805161090a906002906020840190610abf565b5050565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016104fa565b610948816108f7565b50565b60405163e478371b60e01b81526001600160a01b0383169063e478371b906109799086908590600401610f4f565b600060405180830381600087803b15801561099357600080fd5b505af11580156109a7573d6000803e3d6000fd5b50505050505050565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a9060440161033e565b600080806109fe856301ffc9a760e01b610a8b565b9092509050811580610a0e575080155b15610a1e5760009250505061030c565b610a30856001600160e01b0319610a8b565b9092509050811580610a4157508015155b15610a515760009250505061030c565b610a5b8585610a8b565b9092509050600182148015610a705750806001145b15610a805760019250505061030c565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b828054610acb90610e96565b90600052602060002090601f016020900481019282610aed5760008555610b33565b82601f10610b0657805160ff1916838001178555610b33565b82800160010185558215610b33579182015b82811115610b33578251825591602001919060010190610b18565b50610b3f929150610b43565b5090565b5b80821115610b3f5760008155600101610b44565b600060208284031215610b6a57600080fd5b81356001600160e01b031981168114610b8257600080fd5b9392505050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610bb057600080fd5b813567ffffffffffffffff80821115610bcb57610bcb610b89565b604051601f8301601f19908116603f01168101908282118183101715610bf357610bf3610b89565b81604052838152866020858801011115610c0c57600080fd5b836020870160208301376000602085830101528094505050505092915050565b6001600160a01b038116811461094857600080fd5b60008060408385031215610c5457600080fd5b823567ffffffffffffffff811115610c6b57600080fd5b610c7785828601610b9f565b9250506020830135610c8881610c2c565b809150509250929050565b6000815180845260005b81811015610cb957602081850181015186830182015201610c9d565b81811115610ccb576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610b826020830184610c93565b600080600060408486031215610d0857600080fd5b8335610d1381610c2c565b9250602084013567ffffffffffffffff80821115610d3057600080fd5b818601915086601f830112610d4457600080fd5b813581811115610d5357600080fd5b876020828501011115610d6557600080fd5b6020830194508093505050509250925092565b600080600060608486031215610d8d57600080fd5b505081359360208301359350604090920135919050565b600060208284031215610db657600080fd5b8135610b8281610c2c565b600060208284031215610dd357600080fd5b813567ffffffffffffffff811115610dea57600080fd5b61047e84828501610b9f565b600060208284031215610e0857600080fd5b5035919050565b600080600060608486031215610e2457600080fd5b8335610e2f81610c2c565b92506020840135610e3f81610c2c565b9150604084013567ffffffffffffffff811115610e5b57600080fd5b610e6786828701610b9f565b9150509250925092565b60008060408385031215610e8457600080fd5b823591506020830135610c8881610c2c565b600181811c90821680610eaa57607f821691505b602082108103610eca57634e487b7160e01b600052602260045260246000fd5b50919050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610f2257600080fd5b81518015158114610b8257600080fd5b600060208284031215610f4457600080fd5b8151610b8281610c2c565b6001600160a01b038316815260406020820181905260009061047e90830184610c9356fea264697066735822122062e0cab118e5509f15b1b7b5176cf3a1e9aa00feef09f87884daa173bdfa785164736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_CREATEAGENT = "createAgent";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_EXECUTETRADE = "executeTrade";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_GETSOCIALAGENTNAME = "getSocialAgentName";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_RECEIVERECOMMENDATION = "receiveRecommendation";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SETNAME = "setName";

    public static final String FUNC_SOCIALAGENTBINDTOSOCIETY = "socialAgentBindToSociety";

    public static final String FUNC_SOCIALAGENTNAME = "socialAgentName";

    public static final String FUNC_SOCIALAGENTUNBIND = "socialAgentUnbind";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final Event RECOMMENDATIONRECEIVED_EVENT = new Event("RecommendationReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event TRADEEXECUTED_EVENT = new Event("TradeExecuted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected TradeExecutionAgent(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TradeExecutionAgent(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TradeExecutionAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TradeExecutionAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<RecommendationReceivedEventResponse> getRecommendationReceivedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RECOMMENDATIONRECEIVED_EVENT, transactionReceipt);
        ArrayList<RecommendationReceivedEventResponse> responses = new ArrayList<RecommendationReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RecommendationReceivedEventResponse typedResponse = new RecommendationReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.rec = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RecommendationReceivedEventResponse getRecommendationReceivedEventFromLog(
            Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RECOMMENDATIONRECEIVED_EVENT, log);
        RecommendationReceivedEventResponse typedResponse = new RecommendationReceivedEventResponse();
        typedResponse.log = log;
        typedResponse.rec = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<RecommendationReceivedEventResponse> recommendationReceivedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRecommendationReceivedEventFromLog(log));
    }

    public Flowable<RecommendationReceivedEventResponse> recommendationReceivedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECOMMENDATIONRECEIVED_EVENT));
        return recommendationReceivedEventFlowable(filter);
    }

    public static List<TradeExecutedEventResponse> getTradeExecutedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRADEEXECUTED_EVENT, transactionReceipt);
        ArrayList<TradeExecutedEventResponse> responses = new ArrayList<TradeExecutedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TradeExecutedEventResponse typedResponse = new TradeExecutedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.symbol = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.size = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TradeExecutedEventResponse getTradeExecutedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRADEEXECUTED_EVENT, log);
        TradeExecutedEventResponse typedResponse = new TradeExecutedEventResponse();
        typedResponse.log = log;
        typedResponse.symbol = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.size = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<TradeExecutedEventResponse> tradeExecutedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTradeExecutedEventFromLog(log));
    }

    public Flowable<TradeExecutedEventResponse> tradeExecutedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRADEEXECUTED_EVENT));
        return tradeExecutedEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> executeTrade(byte[] symbol, BigInteger size,
            BigInteger price) {
        final Function function = new Function(
                FUNC_EXECUTETRADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(size), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
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

    public RemoteFunctionCall<TransactionReceipt> receiveRecommendation(String rec) {
        final Function function = new Function(
                FUNC_RECEIVERECOMMENDATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(rec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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
    public static TradeExecutionAgent load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TradeExecutionAgent(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TradeExecutionAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TradeExecutionAgent(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TradeExecutionAgent load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TradeExecutionAgent(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TradeExecutionAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TradeExecutionAgent(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TradeExecutionAgent> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(TradeExecutionAgent.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<TradeExecutionAgent> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(TradeExecutionAgent.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TradeExecutionAgent> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(TradeExecutionAgent.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TradeExecutionAgent> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(TradeExecutionAgent.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class RecommendationReceivedEventResponse extends BaseEventResponse {
        public String rec;
    }

    public static class TradeExecutedEventResponse extends BaseEventResponse {
        public byte[] symbol;

        public BigInteger size;

        public BigInteger price;
    }
}
