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
public class RiskManagementAgent extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200119f3803806200119f83398101604081905262000034916200011b565b6200003f8162000046565b5062000233565b80516200005b9060029060208401906200005f565b5050565b8280546200006d90620001f7565b90600052602060002090601f016020900481019282620000915760008555620000dc565b82601f10620000ac57805160ff1916838001178555620000dc565b82800160010185558215620000dc579182015b82811115620000dc578251825591602001919060010190620000bf565b50620000ea929150620000ee565b5090565b5b80821115620000ea5760008155600101620000ef565b634e487b7160e01b600052604160045260246000fd5b600060208083850312156200012f57600080fd5b82516001600160401b03808211156200014757600080fd5b818501915085601f8301126200015c57600080fd5b81518181111562000171576200017162000105565b604051601f8201601f19908116603f011681019083821181831017156200019c576200019c62000105565b816040528281528886848701011115620001b557600080fd5b600093505b82841015620001d95784840186015181850187015292850192620001ba565b82841115620001eb5760008684830101525b98975050505050505050565b600181811c908216806200020c57607f821691505b6020821081036200022d57634e487b7160e01b600052602260045260246000fd5b50919050565b610f5c80620002436000396000f3fe608060405234801561001057600080fd5b50600436106101375760003560e01c806395ec5ce3116100b8578063c47f00271161007c578063c47f00271461026c578063d0bf6d151461027f578063e405148414610292578063e51c19fd146102a5578063e959b38a146102b8578063f2f4eb26146102cb57600080fd5b806395ec5ce3146102205780639794f5dd146102285780639fd20abc14610230578063aa638ed614610243578063b3cac0a51461025957600080fd5b806340a9efc8116100ff57806340a9efc8146101b4578063496a3c48146101df5780636b5a3e39146101f25780636e8eb7bc146101fa578063800096301461020d57600080fd5b806301ffc9a71461013c578063102864431461016457806319b96c3f14610179578063201b631b1461018e5780633e2b4de3146101a1575b600080fd5b61014f61014a366004610b15565b6102de565b60405190151581526020015b60405180910390f35b610177610172366004610bfe565b610315565b005b610181610377565b60405161015b9190610c9d565b61014f61019c366004610cb0565b610409565b6101776101af366004610d35565b610489565b6001546101c7906001600160a01b031681565b6040516001600160a01b03909116815260200161015b565b6101776101ed366004610d6a565b6104c3565b6101c7610526565b6101c7610208366004610d87565b6105a3565b61017761021b366004610d6a565b610613565b6101776106b9565b610181610714565b61017761023e366004610d6a565b6107a2565b61014f610251366004610da0565b600192915050565b61014f610267366004610d87565b610846565b61017761027a366004610d35565b6108b4565b61017761028d366004610d87565b6108cb565b6101776102a0366004610d35565b6108fc565b6101776102b3366004610dc2565b610908565b6101776102c6366004610e24565b61096d565b6000546101c7906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061030f57506001600160e01b03198216636f8ae2e560e11b145b92915050565b60405163c352945360e01b81526001600160a01b0382169063c352945390610341908590600401610c9d565b600060405180830381600087803b15801561035b57600080fd5b505af115801561036f573d6000803e3d6000fd5b505050505050565b60606002805461038690610e49565b80601f01602080910402602001604051908101604052809291908181526020018280546103b290610e49565b80156103ff5780601f106103d4576101008083540402835291602001916103ff565b820191906000526020600020905b8154815290600101906020018083116103e257829003601f168201915b5050505050905090565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061043e90879087908790600401610e83565b6020604051808303816000875af115801561045d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104819190610ec3565b949350505050565b7fca5a37b733cd65bdc31ad52d742ca3d288c5c26e8cf438e1cd2311f06ff8ef9a816040516104b89190610c9d565b60405180910390a150565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561050b57600080fd5b505af115801561051f573d6000803e3d6000fd5b5050505050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa15801561057a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061059e9190610ee5565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af11580156105ef573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061030f9190610ee5565b6001600160a01b0381161561069757600061063582637365940f60e01b6109a6565b9050806106955760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084015b60405180910390fd5b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b1580156106fa57600080fd5b505af115801561070e573d6000803e3d6000fd5b50505050565b6002805461072190610e49565b80601f016020809104026020016040519081016040528092919081815260200182805461074d90610e49565b801561079a5780601f1061076f5761010080835404028352916020019161079a565b820191906000526020600020905b81548152906001019060200180831161077d57829003601f168201915b505050505081565b6001600160a01b038116156108245760006107c4826338cce89560e01b6109a6565b9050806108225760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b606482015260840161068c565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa158015610890573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061030f9190610ec3565b80516108c7906002906020840190610a7c565b5050565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016104f1565b610905816108b4565b50565b60405163e478371b60e01b81526001600160a01b0383169063e478371b906109369086908590600401610f02565b600060405180830381600087803b15801561095057600080fd5b505af1158015610964573d6000803e3d6000fd5b50505050505050565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401610341565b600080806109bb856301ffc9a760e01b610a48565b90925090508115806109cb575080155b156109db5760009250505061030f565b6109ed856001600160e01b0319610a48565b90925090508115806109fe57508015155b15610a0e5760009250505061030f565b610a188585610a48565b9092509050600182148015610a2d5750806001145b15610a3d5760019250505061030f565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b828054610a8890610e49565b90600052602060002090601f016020900481019282610aaa5760008555610af0565b82601f10610ac357805160ff1916838001178555610af0565b82800160010185558215610af0579182015b82811115610af0578251825591602001919060010190610ad5565b50610afc929150610b00565b5090565b5b80821115610afc5760008155600101610b01565b600060208284031215610b2757600080fd5b81356001600160e01b031981168114610b3f57600080fd5b9392505050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610b6d57600080fd5b813567ffffffffffffffff80821115610b8857610b88610b46565b604051601f8301601f19908116603f01168101908282118183101715610bb057610bb0610b46565b81604052838152866020858801011115610bc957600080fd5b836020870160208301376000602085830101528094505050505092915050565b6001600160a01b038116811461090557600080fd5b60008060408385031215610c1157600080fd5b823567ffffffffffffffff811115610c2857600080fd5b610c3485828601610b5c565b9250506020830135610c4581610be9565b809150509250929050565b6000815180845260005b81811015610c7657602081850181015186830182015201610c5a565b81811115610c88576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610b3f6020830184610c50565b600080600060408486031215610cc557600080fd5b8335610cd081610be9565b9250602084013567ffffffffffffffff80821115610ced57600080fd5b818601915086601f830112610d0157600080fd5b813581811115610d1057600080fd5b876020828501011115610d2257600080fd5b6020830194508093505050509250925092565b600060208284031215610d4757600080fd5b813567ffffffffffffffff811115610d5e57600080fd5b61048184828501610b5c565b600060208284031215610d7c57600080fd5b8135610b3f81610be9565b600060208284031215610d9957600080fd5b5035919050565b60008060408385031215610db357600080fd5b50508035926020909101359150565b600080600060608486031215610dd757600080fd5b8335610de281610be9565b92506020840135610df281610be9565b9150604084013567ffffffffffffffff811115610e0e57600080fd5b610e1a86828701610b5c565b9150509250925092565b60008060408385031215610e3757600080fd5b823591506020830135610c4581610be9565b600181811c90821680610e5d57607f821691505b602082108103610e7d57634e487b7160e01b600052602260045260246000fd5b50919050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610ed557600080fd5b81518015158114610b3f57600080fd5b600060208284031215610ef757600080fd5b8151610b3f81610be9565b6001600160a01b038316815260406020820181905260009061048190830184610c5056fea2646970667358221220f5cd9d396f1f7b206ee7aee353e30a2634eee05a66c5243d970fcbb32718913764736f6c634300080d0033";

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

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SETNAME = "setName";

    public static final String FUNC_SOCIALAGENTBINDTOSOCIETY = "socialAgentBindToSociety";

    public static final String FUNC_SOCIALAGENTNAME = "socialAgentName";

    public static final String FUNC_SOCIALAGENTUNBIND = "socialAgentUnbind";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_UPDATERISKPROFILE = "updateRiskProfile";

    public static final String FUNC_VALIDATETRADE = "validateTrade";

    public static final Event RISKPROFILEUPDATED_EVENT = new Event("RiskProfileUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected RiskManagementAgent(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RiskManagementAgent(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RiskManagementAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RiskManagementAgent(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<RiskProfileUpdatedEventResponse> getRiskProfileUpdatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RISKPROFILEUPDATED_EVENT, transactionReceipt);
        ArrayList<RiskProfileUpdatedEventResponse> responses = new ArrayList<RiskProfileUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RiskProfileUpdatedEventResponse typedResponse = new RiskProfileUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.profile = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RiskProfileUpdatedEventResponse getRiskProfileUpdatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RISKPROFILEUPDATED_EVENT, log);
        RiskProfileUpdatedEventResponse typedResponse = new RiskProfileUpdatedEventResponse();
        typedResponse.log = log;
        typedResponse.profile = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<RiskProfileUpdatedEventResponse> riskProfileUpdatedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRiskProfileUpdatedEventFromLog(log));
    }

    public Flowable<RiskProfileUpdatedEventResponse> riskProfileUpdatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RISKPROFILEUPDATED_EVENT));
        return riskProfileUpdatedEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> updateRiskProfile(String profile) {
        final Function function = new Function(
                FUNC_UPDATERISKPROFILE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(profile)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> validateTrade(byte[] symbol, BigInteger size) {
        final Function function = new Function(FUNC_VALIDATETRADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(size)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static RiskManagementAgent load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RiskManagementAgent(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RiskManagementAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RiskManagementAgent(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RiskManagementAgent load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RiskManagementAgent(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RiskManagementAgent load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RiskManagementAgent(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RiskManagementAgent> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(RiskManagementAgent.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<RiskManagementAgent> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(RiskManagementAgent.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RiskManagementAgent> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(RiskManagementAgent.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RiskManagementAgent> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)));
        return deployRemoteCall(RiskManagementAgent.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class RiskProfileUpdatedEventResponse extends BaseEventResponse {
        public String profile;
    }
}
