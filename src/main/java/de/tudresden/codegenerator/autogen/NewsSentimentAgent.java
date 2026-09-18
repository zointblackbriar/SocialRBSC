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
public class NewsSentimentAgent extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620011db380380620011db83398101604081905262000034916200011b565b6200003f8162000046565b5062000233565b80516200005b9060029060208401906200005f565b5050565b8280546200006d90620001f7565b90600052602060002090601f016020900481019282620000915760008555620000dc565b82601f10620000ac57805160ff1916838001178555620000dc565b82800160010185558215620000dc579182015b82811115620000dc578251825591602001919060010190620000bf565b50620000ea929150620000ee565b5090565b5b80821115620000ea5760008155600101620000ef565b634e487b7160e01b600052604160045260246000fd5b600060208083850312156200012f57600080fd5b82516001600160401b03808211156200014757600080fd5b818501915085601f8301126200015c57600080fd5b81518181111562000171576200017162000105565b604051601f8201601f19908116603f011681019083821181831017156200019c576200019c62000105565b816040528281528886848701011115620001b557600080fd5b600093505b82841015620001d95784840186015181850187015292850192620001ba565b82841115620001eb5760008684830101525b98975050505050505050565b600181811c908216806200020c57607f821691505b6020821081036200022d57634e487b7160e01b600052602260045260246000fd5b50919050565b610f9880620002436000396000f3fe608060405234801561001057600080fd5b50600436106101425760003560e01c806395ec5ce3116100b8578063c47f00271161007c578063c47f00271461029b578063d0bf6d15146102ae578063e4051484146102c1578063e51c19fd146102d4578063e959b38a146102e7578063f2f4eb26146102fa57600080fd5b806395ec5ce3146102525780639794f5dd1461025a5780639fd20abc14610262578063b3cac0a514610275578063c1bd4f991461028857600080fd5b806340a9efc81161010a57806340a9efc8146101d3578063496a3c48146101fe5780636b5a3e39146102115780636e8eb7bc1461021957806378088d5f1461022c578063800096301461023f57600080fd5b806301ffc9a71461014757806303a5ffc71461016f578063102864431461019657806319b96c3f146101ab578063201b631b146101c0575b600080fd5b61015a610155366004610b73565b61030d565b60405190151581526020015b60405180910390f35b61018361017d366004610c47565b50600090565b60405160009190910b8152602001610166565b6101a96101a4366004610c91565b610344565b005b6101b36103a6565b6040516101669190610d30565b61015a6101ce366004610d43565b610438565b6001546101e6906001600160a01b031681565b6040516001600160a01b039091168152602001610166565b6101a961020c366004610dc8565b6104b8565b6101e661051b565b6101e6610227366004610de5565b610598565b6101a961023a366004610c47565b610608565b6101a961024d366004610dc8565b610642565b6101a96106e8565b6101b3610743565b6101a9610270366004610dc8565b6107d1565b61015a610283366004610de5565b610875565b6101a9610296366004610c47565b6108e3565b6101a96102a9366004610c47565b610912565b6101a96102bc366004610de5565b610929565b6101a96102cf366004610c47565b61095a565b6101a96102e2366004610dfe565b610966565b6101a96102f5366004610e60565b6109cb565b6000546101e6906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061033e57506001600160e01b03198216636f8ae2e560e11b145b92915050565b60405163c352945360e01b81526001600160a01b0382169063c352945390610370908590600401610d30565b600060405180830381600087803b15801561038a57600080fd5b505af115801561039e573d6000803e3d6000fd5b505050505050565b6060600280546103b590610e85565b80601f01602080910402602001604051908101604052809291908181526020018280546103e190610e85565b801561042e5780601f106104035761010080835404028352916020019161042e565b820191906000526020600020905b81548152906001019060200180831161041157829003601f168201915b5050505050905090565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061046d90879087908790600401610ebf565b6020604051808303816000875af115801561048c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104b09190610eff565b949350505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561050057600080fd5b505af1158015610514573d6000803e3d6000fd5b5050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa15801561056f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105939190610f21565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af11580156105e4573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061033e9190610f21565b7f331b00b839198c2c3bf6925162c32051e9b33ef66dfde12fc545206c059ad28a816040516106379190610d30565b60405180910390a150565b6001600160a01b038116156106c657600061066482637365940f60e01b610a04565b9050806106c45760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084015b60405180910390fd5b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561072957600080fd5b505af115801561073d573d6000803e3d6000fd5b50505050565b6002805461075090610e85565b80601f016020809104026020016040519081016040528092919081815260200182805461077c90610e85565b80156107c95780601f1061079e576101008083540402835291602001916107c9565b820191906000526020600020905b8154815290600101906020018083116107ac57829003601f168201915b505050505081565b6001600160a01b038116156108535760006107f3826338cce89560e01b610a04565b9050806108515760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084016106bb565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa1580156108bf573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061033e9190610eff565b7fab25b88d4fe94bf8555f5fcd0552b38d71166f98b870b596b47abe3f40d68d2a816040516106379190610d30565b8051610925906002906020840190610ada565b5050565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016104e6565b61096381610912565b50565b60405163e478371b60e01b81526001600160a01b0383169063e478371b906109949086908590600401610f3e565b600060405180830381600087803b1580156109ae57600080fd5b505af11580156109c2573d6000803e3d6000fd5b50505050505050565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401610370565b60008080610a19856301ffc9a760e01b610aa6565b9092509050811580610a29575080155b15610a395760009250505061033e565b610a4b856001600160e01b0319610aa6565b9092509050811580610a5c57508015155b15610a6c5760009250505061033e565b610a768585610aa6565b9092509050600182148015610a8b5750806001145b15610a9b5760019250505061033e565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b828054610ae690610e85565b90600052602060002090601f016020900481019282610b085760008555610b4e565b82601f10610b2157805160ff1916838001178555610b4e565b82800160010185558215610b4e579182015b82811115610b4e578251825591602001919060010190610b33565b50610b5a929150610b5e565b5090565b5b80821115610b5a5760008155600101610b5f565b600060208284031215610b8557600080fd5b81356001600160e01b031981168114610b9d57600080fd5b9392505050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610bcb57600080fd5b813567ffffffffffffffff80821115610be657610be6610ba4565b604051601f8301601f19908116603f01168101908282118183101715610c0e57610c0e610ba4565b81604052838152866020858801011115610c2757600080fd5b836020870160208301376000602085830101528094505050505092915050565b600060208284031215610c5957600080fd5b813567ffffffffffffffff811115610c7057600080fd5b6104b084828501610bba565b6001600160a01b038116811461096357600080fd5b60008060408385031215610ca457600080fd5b823567ffffffffffffffff811115610cbb57600080fd5b610cc785828601610bba565b9250506020830135610cd881610c7c565b809150509250929050565b6000815180845260005b81811015610d0957602081850181015186830182015201610ced565b81811115610d1b576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610b9d6020830184610ce3565b600080600060408486031215610d5857600080fd5b8335610d6381610c7c565b9250602084013567ffffffffffffffff80821115610d8057600080fd5b818601915086601f830112610d9457600080fd5b813581811115610da357600080fd5b876020828501011115610db557600080fd5b6020830194508093505050509250925092565b600060208284031215610dda57600080fd5b8135610b9d81610c7c565b600060208284031215610df757600080fd5b5035919050565b600080600060608486031215610e1357600080fd5b8335610e1e81610c7c565b92506020840135610e2e81610c7c565b9150604084013567ffffffffffffffff811115610e4a57600080fd5b610e5686828701610bba565b9150509250925092565b60008060408385031215610e7357600080fd5b823591506020830135610cd881610c7c565b600181811c90821680610e9957607f821691505b602082108103610eb957634e487b7160e01b600052602260045260246000fd5b50919050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610f1157600080fd5b81518015158114610b9d57600080fd5b600060208284031215610f3357600080fd5b8151610b9d81610c7c565b6001600160a01b03831681526040602082018190526000906104b090830184610ce356fea2646970667358221220ddcf1f89833f74d5edf9c68da3f18e8c59bbe2bad0ba466787a06bbb63ffc72964736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ANALYZESENTIMENT = "analyzeSentiment";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_CREATEAGENT = "createAgent";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_GETSOCIALAGENTNAME = "getSocialAgentName";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_INGESTHEADLINE = "ingestHeadline";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SENDALERT = "sendAlert";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SETNAME = "setName";

    public static final String FUNC_SOCIALAGENTBINDTOSOCIETY = "socialAgentBindToSociety";

    public static final String FUNC_SOCIALAGENTNAME = "socialAgentName";

    public static final String FUNC_SOCIALAGENTUNBIND = "socialAgentUnbind";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final Event ALERTSENT_EVENT = new Event("AlertSent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event NEWSINGESTED_EVENT = new Event("NewsIngested", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected NewsSentimentAgent(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NewsSentimentAgent(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NewsSentimentAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NewsSentimentAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<AlertSentEventResponse> getAlertSentEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ALERTSENT_EVENT, transactionReceipt);
        ArrayList<AlertSentEventResponse> responses = new ArrayList<AlertSentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AlertSentEventResponse typedResponse = new AlertSentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AlertSentEventResponse getAlertSentEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ALERTSENT_EVENT, log);
        AlertSentEventResponse typedResponse = new AlertSentEventResponse();
        typedResponse.log = log;
        typedResponse.message = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<AlertSentEventResponse> alertSentEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAlertSentEventFromLog(log));
    }

    public Flowable<AlertSentEventResponse> alertSentEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ALERTSENT_EVENT));
        return alertSentEventFlowable(filter);
    }

    public static List<NewsIngestedEventResponse> getNewsIngestedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWSINGESTED_EVENT, transactionReceipt);
        ArrayList<NewsIngestedEventResponse> responses = new ArrayList<NewsIngestedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewsIngestedEventResponse typedResponse = new NewsIngestedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.headline = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NewsIngestedEventResponse getNewsIngestedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NEWSINGESTED_EVENT, log);
        NewsIngestedEventResponse typedResponse = new NewsIngestedEventResponse();
        typedResponse.log = log;
        typedResponse.headline = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<NewsIngestedEventResponse> newsIngestedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNewsIngestedEventFromLog(log));
    }

    public Flowable<NewsIngestedEventResponse> newsIngestedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWSINGESTED_EVENT));
        return newsIngestedEventFlowable(filter);
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

    public RemoteFunctionCall<BigInteger> analyzeSentiment(String text) {
        final Function function = new Function(FUNC_ANALYZESENTIMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(text)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<TransactionReceipt> ingestHeadline(String headline) {
        final Function function = new Function(
                FUNC_INGESTHEADLINE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(headline)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isPlayingRole(byte[] spec) {
        final Function function = new Function(FUNC_ISPLAYINGROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeRole(byte[] spec) {
        final Function function = new Function(
                FUNC_REMOVEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> sendAlert(String message) {
        final Function function = new Function(
                FUNC_SENDALERT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(message)), 
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
    public static NewsSentimentAgent load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NewsSentimentAgent(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NewsSentimentAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NewsSentimentAgent(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NewsSentimentAgent load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NewsSentimentAgent(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NewsSentimentAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NewsSentimentAgent(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NewsSentimentAgent> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(NewsSentimentAgent.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<NewsSentimentAgent> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(NewsSentimentAgent.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NewsSentimentAgent> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(NewsSentimentAgent.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NewsSentimentAgent> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(NewsSentimentAgent.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class AlertSentEventResponse extends BaseEventResponse {
        public String message;
    }

    public static class NewsIngestedEventResponse extends BaseEventResponse {
        public String headline;
    }
}
