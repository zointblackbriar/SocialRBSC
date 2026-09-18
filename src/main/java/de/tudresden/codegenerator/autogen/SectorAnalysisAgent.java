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
import org.web3j.abi.datatypes.generated.Int8;
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
public class SectorAnalysisAgent extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620012213803806200122183398101604081905262000034916200011b565b6200003f8162000046565b5062000233565b80516200005b9060029060208401906200005f565b5050565b8280546200006d90620001f7565b90600052602060002090601f016020900481019282620000915760008555620000dc565b82601f10620000ac57805160ff1916838001178555620000dc565b82800160010185558215620000dc579182015b82811115620000dc578251825591602001919060010190620000bf565b50620000ea929150620000ee565b5090565b5b80821115620000ea5760008155600101620000ef565b634e487b7160e01b600052604160045260246000fd5b600060208083850312156200012f57600080fd5b82516001600160401b03808211156200014757600080fd5b818501915085601f8301126200015c57600080fd5b81518181111562000171576200017162000105565b604051601f8201601f19908116603f011681019083821181831017156200019c576200019c62000105565b816040528281528886848701011115620001b557600080fd5b600093505b82841015620001d95784840186015181850187015292850192620001ba565b82841115620001eb5760008684830101525b98975050505050505050565b600181811c908216806200020c57607f821691505b6020821081036200022d57634e487b7160e01b600052602260045260246000fd5b50919050565b610fde80620002436000396000f3fe608060405234801561001057600080fd5b50600436106101375760003560e01c806395ec5ce3116100b8578063c47f00271161007c578063c47f002714610269578063d0bf6d151461027c578063e40514841461028f578063e51c19fd146102a2578063e959b38a146102b5578063f2f4eb26146102c857600080fd5b806395ec5ce3146102205780639794f5dd146102285780639fd20abc14610230578063afe1a19214610243578063b3cac0a51461025657600080fd5b806340a9efc8116100ff57806340a9efc8146101b4578063496a3c48146101df5780636b5a3e39146101f25780636e8eb7bc146101fa578063800096301461020d57600080fd5b806301ffc9a71461013c5780630e4b74b614610164578063102864431461018457806319b96c3f14610199578063201b631b146101a1575b600080fd5b61014f61014a366004610b75565b6102db565b60405190151581526020015b60405180910390f35b610177610172366004610ba6565b610312565b60405161015b9190610c0c565b610197610192366004610cd7565b61036f565b005b6101776103d1565b61014f6101af366004610d29565b610463565b6001546101c7906001600160a01b031681565b6040516001600160a01b03909116815260200161015b565b6101976101ed366004610dae565b6104e3565b6101c7610546565b6101c7610208366004610ba6565b6105c3565b61019761021b366004610dae565b610633565b6101976106d9565b610177610734565b61019761023e366004610dae565b6107c2565b610197610251366004610dcb565b610866565b61014f610264366004610ba6565b6108a6565b610197610277366004610df6565b610914565b61019761028a366004610ba6565b61092b565b61019761029d366004610df6565b61095c565b6101976102b0366004610e2b565b610968565b6101976102c3366004610e8d565b6109cd565b6000546101c7906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061030c57506001600160e01b03198216636f8ae2e560e11b145b92915050565b60408051808201825260048152631213d31160e21b60208201529051606091907f51fff8a4c0f365b61a4a8949e8fdca02b4bd1c35b72e6b19fb525abf0d0223bd906103619085908490610eb2565b60405180910390a192915050565b60405163c352945360e01b81526001600160a01b0382169063c35294539061039b908590600401610c0c565b600060405180830381600087803b1580156103b557600080fd5b505af11580156103c9573d6000803e3d6000fd5b505050505050565b6060600280546103e090610ecb565b80601f016020809104026020016040519081016040528092919081815260200182805461040c90610ecb565b80156104595780601f1061042e57610100808354040283529160200191610459565b820191906000526020600020905b81548152906001019060200180831161043c57829003601f168201915b5050505050905090565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061049890879087908790600401610f05565b6020604051808303816000875af11580156104b7573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104db9190610f45565b949350505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561052b57600080fd5b505af115801561053f573d6000803e3d6000fd5b5050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa15801561059a573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105be9190610f67565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af115801561060f573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061030c9190610f67565b6001600160a01b038116156106b757600061065582637365940f60e01b610a06565b9050806106b55760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084015b60405180910390fd5b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561071a57600080fd5b505af115801561072e573d6000803e3d6000fd5b50505050565b6002805461074190610ecb565b80601f016020809104026020016040519081016040528092919081815260200182805461076d90610ecb565b80156107ba5780601f1061078f576101008083540402835291602001916107ba565b820191906000526020600020905b81548152906001019060200180831161079d57829003601f168201915b505050505081565b6001600160a01b038116156108445760006107e4826338cce89560e01b610a06565b9050806108425760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084016106ac565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b60408051838152600083900b60208201527fb4df68864c5a955dde3c34a26ed09cc8b5a182beaf9bd218d6906bd6d72009c2910160405180910390a15050565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa1580156108f0573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061030c9190610f45565b8051610927906002906020840190610adc565b5050565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d1590602401610511565b61096581610914565b50565b60405163e478371b60e01b81526001600160a01b0383169063e478371b906109969086908590600401610f84565b600060405180830381600087803b1580156109b057600080fd5b505af11580156109c4573d6000803e3d6000fd5b50505050505050565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a9060440161039b565b60008080610a1b856301ffc9a760e01b610aa8565b9092509050811580610a2b575080155b15610a3b5760009250505061030c565b610a4d856001600160e01b0319610aa8565b9092509050811580610a5e57508015155b15610a6e5760009250505061030c565b610a788585610aa8565b9092509050600182148015610a8d5750806001145b15610a9d5760019250505061030c565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b828054610ae890610ecb565b90600052602060002090601f016020900481019282610b0a5760008555610b50565b82601f10610b2357805160ff1916838001178555610b50565b82800160010185558215610b50579182015b82811115610b50578251825591602001919060010190610b35565b50610b5c929150610b60565b5090565b5b80821115610b5c5760008155600101610b61565b600060208284031215610b8757600080fd5b81356001600160e01b031981168114610b9f57600080fd5b9392505050565b600060208284031215610bb857600080fd5b5035919050565b6000815180845260005b81811015610be557602081850181015186830182015201610bc9565b81811115610bf7576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610b9f6020830184610bbf565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610c4657600080fd5b813567ffffffffffffffff80821115610c6157610c61610c1f565b604051601f8301601f19908116603f01168101908282118183101715610c8957610c89610c1f565b81604052838152866020858801011115610ca257600080fd5b836020870160208301376000602085830101528094505050505092915050565b6001600160a01b038116811461096557600080fd5b60008060408385031215610cea57600080fd5b823567ffffffffffffffff811115610d0157600080fd5b610d0d85828601610c35565b9250506020830135610d1e81610cc2565b809150509250929050565b600080600060408486031215610d3e57600080fd5b8335610d4981610cc2565b9250602084013567ffffffffffffffff80821115610d6657600080fd5b818601915086601f830112610d7a57600080fd5b813581811115610d8957600080fd5b876020828501011115610d9b57600080fd5b6020830194508093505050509250925092565b600060208284031215610dc057600080fd5b8135610b9f81610cc2565b60008060408385031215610dde57600080fd5b8235915060208301358060000b8114610d1e57600080fd5b600060208284031215610e0857600080fd5b813567ffffffffffffffff811115610e1f57600080fd5b6104db84828501610c35565b600080600060608486031215610e4057600080fd5b8335610e4b81610cc2565b92506020840135610e5b81610cc2565b9150604084013567ffffffffffffffff811115610e7757600080fd5b610e8386828701610c35565b9150509250925092565b60008060408385031215610ea057600080fd5b823591506020830135610d1e81610cc2565b8281526040602082015260006104db6040830184610bbf565b600181811c90821680610edf57607f821691505b602082108103610eff57634e487b7160e01b600052602260045260246000fd5b50919050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610f5757600080fd5b81518015158114610b9f57600080fd5b600060208284031215610f7957600080fd5b8151610b9f81610cc2565b6001600160a01b03831681526040602082018190526000906104db90830184610bbf56fea26469706673582212206373c4062fc6eff449351a870ccc5bf1a3e562a24f28a62a15cfa738e44de3a264736f6c634300080d0033";

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

    public static final String FUNC_MAKERECOMMENDATION = "makeRecommendation";

    public static final String FUNC_RECEIVESENTIMENT = "receiveSentiment";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SETNAME = "setName";

    public static final String FUNC_SOCIALAGENTBINDTOSOCIETY = "socialAgentBindToSociety";

    public static final String FUNC_SOCIALAGENTNAME = "socialAgentName";

    public static final String FUNC_SOCIALAGENTUNBIND = "socialAgentUnbind";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final Event RECOMMENDATION_EVENT = new Event("Recommendation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event SENTIMENTRECEIVED_EVENT = new Event("SentimentReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Int8>() {}));
    ;

    @Deprecated
    protected SectorAnalysisAgent(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SectorAnalysisAgent(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SectorAnalysisAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SectorAnalysisAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<RecommendationEventResponse> getRecommendationEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RECOMMENDATION_EVENT, transactionReceipt);
        ArrayList<RecommendationEventResponse> responses = new ArrayList<RecommendationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RecommendationEventResponse typedResponse = new RecommendationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.symbol = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.rec = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RecommendationEventResponse getRecommendationEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RECOMMENDATION_EVENT, log);
        RecommendationEventResponse typedResponse = new RecommendationEventResponse();
        typedResponse.log = log;
        typedResponse.symbol = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.rec = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<RecommendationEventResponse> recommendationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRecommendationEventFromLog(log));
    }

    public Flowable<RecommendationEventResponse> recommendationEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECOMMENDATION_EVENT));
        return recommendationEventFlowable(filter);
    }

    public static List<SentimentReceivedEventResponse> getSentimentReceivedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SENTIMENTRECEIVED_EVENT, transactionReceipt);
        ArrayList<SentimentReceivedEventResponse> responses = new ArrayList<SentimentReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SentimentReceivedEventResponse typedResponse = new SentimentReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.symbol = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.score = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SentimentReceivedEventResponse getSentimentReceivedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SENTIMENTRECEIVED_EVENT, log);
        SentimentReceivedEventResponse typedResponse = new SentimentReceivedEventResponse();
        typedResponse.log = log;
        typedResponse.symbol = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.score = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<SentimentReceivedEventResponse> sentimentReceivedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSentimentReceivedEventFromLog(log));
    }

    public Flowable<SentimentReceivedEventResponse> sentimentReceivedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SENTIMENTRECEIVED_EVENT));
        return sentimentReceivedEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> makeRecommendation(byte[] symbol) {
        final Function function = new Function(
                FUNC_MAKERECOMMENDATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(symbol)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> receiveSentiment(byte[] symbol,
            BigInteger score) {
        final Function function = new Function(
                FUNC_RECEIVESENTIMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(symbol), 
                new org.web3j.abi.datatypes.generated.Int8(score)), 
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
    public static SectorAnalysisAgent load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SectorAnalysisAgent(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SectorAnalysisAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SectorAnalysisAgent(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SectorAnalysisAgent load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SectorAnalysisAgent(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SectorAnalysisAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SectorAnalysisAgent(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SectorAnalysisAgent> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(SectorAnalysisAgent.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<SectorAnalysisAgent> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(SectorAnalysisAgent.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SectorAnalysisAgent> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(SectorAnalysisAgent.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SectorAnalysisAgent> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(SectorAnalysisAgent.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class RecommendationEventResponse extends BaseEventResponse {
        public byte[] symbol;

        public String rec;
    }

    public static class SentimentReceivedEventResponse extends BaseEventResponse {
        public byte[] symbol;

        public BigInteger score;
    }
}
