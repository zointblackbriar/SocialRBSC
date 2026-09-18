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
import org.web3j.tuples.generated.Tuple2;
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
public class RoleRestriction extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600480546001600160a01b03191633179055610cc5806100326000396000f3fe608060405234801561001057600080fd5b50600436106101375760003560e01c806381d12c58116100b8578063b3cac0a51161007c578063b3cac0a514610286578063ccd3c4bc14610299578063d0bf6d15146102cd578063e959b38a146102e0578063ee8bb69f146102f3578063f2f4eb26146102fc57600080fd5b806381d12c581461021e5780638da5cb5b1461024657806395ec5ce3146102595780639fd20abc14610261578063b2becc211461027457600080fd5b8063496a3c48116100ff578063496a3c48146101ca57806355dc9245146101dd5780636b5a3e39146101f05780636e8eb7bc146101f8578063800096301461020b57600080fd5b806301ffc9a71461013c5780630a05934c14610164578063201b631b1461017957806340a9efc81461018c578063413f7ec8146101b7575b600080fd5b61014f61014a366004610a20565b61030f565b60405190151581526020015b60405180910390f35b610177610172366004610a51565b610346565b005b61014f610187366004610a8b565b61040a565b60015461019f906001600160a01b031681565b6040516001600160a01b03909116815260200161015b565b6101776101c5366004610b10565b61048a565b6101776101d8366004610b29565b6104a4565b6101776101eb366004610b10565b610507565b61019f610586565b61019f610206366004610b10565b610603565b610177610219366004610b29565b610673565b61023161022c366004610b10565b610714565b6040805192835260208301919091520161015b565b60045461019f906001600160a01b031681565b610177610742565b61017761026f366004610b29565b61079d565b6002545b60405190815260200161015b565b61014f610294366004610b10565b610841565b60408051808201825260128152711a5d081a185cc81899595b881c1b185e595960721b6020820152905161015b9190610b46565b6101776102db366004610b10565b6108af565b6101776102ee366004610b9b565b6108e0565b61027860025481565b60005461019f906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061034057506001600160e01b03198216636f8ae2e560e11b145b92915050565b60408051808201909152828152602081018281526003805460018082018355600083905293517fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b60029092029182015591517fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85c90920191909155546103cb9190610be1565b604080518481526020810184905230917f6d99ab0063f349e58d8ec7467db6693c757be1db1b8fea260c12260daf997a7b910160405180910390a35050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061043f90879087908790600401610bf8565b6020604051808303816000875af115801561045e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104829190610c38565b949350505050565b806002600082825461049c9190610c5a565b909155505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b1580156104ec57600080fd5b505af1158015610500573d6000803e3d6000fd5b5050505050565b60025481106105745760405162461bcd60e51b815260206004820152602e60248201527f7765206e65656420746f20636865636b20626f756e6461727920666f7220697460448201526d656d73496e496e76656e746f727960901b60648201526084015b60405180910390fd5b806002600082825461049c9190610be1565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156105da573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105fe9190610c72565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af115801561064f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103409190610c72565b6001600160a01b038116156106f257600061069582637365940f60e01b61094a565b9050806106f05760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b606482015260840161056b565b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b6003818154811061072457600080fd5b60009182526020909120600290910201805460019091015490915082565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561078357600080fd5b505af1158015610797573d6000803e3d6000fd5b50505050565b6001600160a01b0381161561081f5760006107bf826338cce89560e01b61094a565b90508061081d5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b606482015260840161056b565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa15801561088b573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103409190610c38565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016104d2565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401600060405180830381600087803b15801561092e57600080fd5b505af1158015610942573d6000803e3d6000fd5b505050505050565b6000808061095f856301ffc9a760e01b6109ec565b909250905081158061096f575080155b1561097f57600092505050610340565b610991856001600160e01b03196109ec565b90925090508115806109a257508015155b156109b257600092505050610340565b6109bc85856109ec565b90925090506001821480156109d15750806001145b156109e157600192505050610340565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b600060208284031215610a3257600080fd5b81356001600160e01b031981168114610a4a57600080fd5b9392505050565b60008060408385031215610a6457600080fd5b50508035926020909101359150565b6001600160a01b0381168114610a8857600080fd5b50565b600080600060408486031215610aa057600080fd5b8335610aab81610a73565b9250602084013567ffffffffffffffff80821115610ac857600080fd5b818601915086601f830112610adc57600080fd5b813581811115610aeb57600080fd5b876020828501011115610afd57600080fd5b6020830194508093505050509250925092565b600060208284031215610b2257600080fd5b5035919050565b600060208284031215610b3b57600080fd5b8135610a4a81610a73565b600060208083528351808285015260005b81811015610b7357858101830151858201604001528201610b57565b81811115610b85576000604083870101525b50601f01601f1916929092016040019392505050565b60008060408385031215610bae57600080fd5b823591506020830135610bc081610a73565b809150509250929050565b634e487b7160e01b600052601160045260246000fd5b600082821015610bf357610bf3610bcb565b500390565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610c4a57600080fd5b81518015158114610a4a57600080fd5b60008219821115610c6d57610c6d610bcb565b500190565b600060208284031215610c8457600080fd5b8151610a4a81610a7356fea26469706673582212202c522dafbbad8d663bf4f292e11013a0155591490071156409bbebdabe3e8adf64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETINVENTORYSTATUS = "getInventoryStatus";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_ITEMSININVENTORYRETAILER = "itemsInInventoryRetailer";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PLAYCONTRACTFORIDENTITY = "playContractForIdentity";

    public static final String FUNC_POPINVENTORY = "popInventory";

    public static final String FUNC_PUSHINVENTORY = "pushInventory";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_REQUESTS = "requests";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SUBMITDELIVERYREQUEST = "submitDeliveryRequest";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final Event DELIVERYREQUESTED_EVENT = new Event("DeliveryRequested", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected RoleRestriction(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RoleRestriction(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RoleRestriction(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RoleRestriction(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DeliveryRequestedEventResponse> getDeliveryRequestedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DELIVERYREQUESTED_EVENT, transactionReceipt);
        ArrayList<DeliveryRequestedEventResponse> responses = new ArrayList<DeliveryRequestedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeliveryRequestedEventResponse typedResponse = new DeliveryRequestedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.retailer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.requestId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.destination = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.timeWindow = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DeliveryRequestedEventResponse getDeliveryRequestedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DELIVERYREQUESTED_EVENT, log);
        DeliveryRequestedEventResponse typedResponse = new DeliveryRequestedEventResponse();
        typedResponse.log = log;
        typedResponse.retailer = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.requestId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.destination = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.timeWindow = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DeliveryRequestedEventResponse> deliveryRequestedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDeliveryRequestedEventFromLog(log));
    }

    public Flowable<DeliveryRequestedEventResponse> deliveryRequestedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELIVERYREQUESTED_EVENT));
        return deliveryRequestedEventFlowable(filter);
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

    public RemoteFunctionCall<BigInteger> getInventoryStatus() {
        final Function function = new Function(FUNC_GETINVENTORYSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getRole(byte[] spec) {
        final Function function = new Function(
                FUNC_GETROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<BigInteger> itemsInInventoryRetailer() {
        final Function function = new Function(FUNC_ITEMSININVENTORYRETAILER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> playContractForIdentity() {
        final Function function = new Function(FUNC_PLAYCONTRACTFORIDENTITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> popInventory(BigInteger _item) {
        final Function function = new Function(
                FUNC_POPINVENTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_item)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> pushInventory(BigInteger _item) {
        final Function function = new Function(
                FUNC_PUSHINVENTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_item)), 
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

    public RemoteFunctionCall<Tuple2<byte[], BigInteger>> requests(BigInteger param0) {
        final Function function = new Function(FUNC_REQUESTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<byte[], BigInteger>>(function,
                new Callable<Tuple2<byte[], BigInteger>>() {
                    @Override
                    public Tuple2<byte[], BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<byte[], BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
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

    public RemoteFunctionCall<TransactionReceipt> submitDeliveryRequest(byte[] destination,
            BigInteger timeWindow) {
        final Function function = new Function(
                FUNC_SUBMITDELIVERYREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(destination), 
                new org.web3j.abi.datatypes.generated.Uint256(timeWindow)), 
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
    public static RoleRestriction load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new RoleRestriction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RoleRestriction load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RoleRestriction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RoleRestriction load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new RoleRestriction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RoleRestriction load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RoleRestriction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RoleRestriction> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RoleRestriction.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<RoleRestriction> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RoleRestriction.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<RoleRestriction> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RoleRestriction.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<RoleRestriction> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RoleRestriction.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class DeliveryRequestedEventResponse extends BaseEventResponse {
        public String retailer;

        public BigInteger requestId;

        public byte[] destination;

        public BigInteger timeWindow;
    }
}
