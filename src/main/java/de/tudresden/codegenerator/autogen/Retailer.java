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
public class Retailer extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610ca7806100206000396000f3fe608060405234801561001057600080fd5b506004361061012c5760003560e01c806381d12c58116100ad578063ccd3c4bc11610071578063ccd3c4bc1461027b578063d0bf6d15146102af578063e959b38a146102c2578063ee8bb69f146102d5578063f2f4eb26146102de57600080fd5b806381d12c581461021357806395ec5ce31461023b5780639fd20abc14610243578063b2becc2114610256578063b3cac0a51461026857600080fd5b8063496a3c48116100f4578063496a3c48146101bf57806355dc9245146101d25780636b5a3e39146101e55780636e8eb7bc146101ed578063800096301461020057600080fd5b806301ffc9a7146101315780630a05934c14610159578063201b631b1461016e57806340a9efc814610181578063413f7ec8146101ac575b600080fd5b61014461013f366004610a02565b6102f1565b60405190151581526020015b60405180910390f35b61016c610167366004610a33565b610328565b005b61014461017c366004610a6d565b6103ec565b600154610194906001600160a01b031681565b6040516001600160a01b039091168152602001610150565b61016c6101ba366004610af2565b61046c565b61016c6101cd366004610b0b565b610486565b61016c6101e0366004610af2565b6104e9565b610194610568565b6101946101fb366004610af2565b6105e5565b61016c61020e366004610b0b565b610655565b610226610221366004610af2565b6106f6565b60408051928352602083019190915201610150565b61016c610724565b61016c610251366004610b0b565b61077f565b6002545b604051908152602001610150565b610144610276366004610af2565b610823565b60408051808201825260128152711a5d081a185cc81899595b881c1b185e595960721b602082015290516101509190610b28565b61016c6102bd366004610af2565b610891565b61016c6102d0366004610b7d565b6108c2565b61025a60025481565b600054610194906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061032257506001600160e01b03198216636f8ae2e560e11b145b92915050565b60408051808201909152828152602081018281526003805460018082018355600083905293517fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b60029092029182015591517fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85c90920191909155546103ad9190610bc3565b604080518481526020810184905230917f6d99ab0063f349e58d8ec7467db6693c757be1db1b8fea260c12260daf997a7b910160405180910390a35050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061042190879087908790600401610bda565b6020604051808303816000875af1158015610440573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104649190610c1a565b949350505050565b806002600082825461047e9190610c3c565b909155505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b1580156104ce57600080fd5b505af11580156104e2573d6000803e3d6000fd5b5050505050565b60025481106105565760405162461bcd60e51b815260206004820152602e60248201527f7765206e65656420746f20636865636b20626f756e6461727920666f7220697460448201526d656d73496e496e76656e746f727960901b60648201526084015b60405180910390fd5b806002600082825461047e9190610bc3565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156105bc573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105e09190610c54565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af1158015610631573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103229190610c54565b6001600160a01b038116156106d457600061067782637365940f60e01b61092c565b9050806106d25760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b606482015260840161054d565b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b6003818154811061070657600080fd5b60009182526020909120600290910201805460019091015490915082565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561076557600080fd5b505af1158015610779573d6000803e3d6000fd5b50505050565b6001600160a01b038116156108015760006107a1826338cce89560e01b61092c565b9050806107ff5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b606482015260840161054d565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa15801561086d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103229190610c1a565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016104b4565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401600060405180830381600087803b15801561091057600080fd5b505af1158015610924573d6000803e3d6000fd5b505050505050565b60008080610941856301ffc9a760e01b6109ce565b9092509050811580610951575080155b1561096157600092505050610322565b610973856001600160e01b03196109ce565b909250905081158061098457508015155b1561099457600092505050610322565b61099e85856109ce565b90925090506001821480156109b35750806001145b156109c357600192505050610322565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b600060208284031215610a1457600080fd5b81356001600160e01b031981168114610a2c57600080fd5b9392505050565b60008060408385031215610a4657600080fd5b50508035926020909101359150565b6001600160a01b0381168114610a6a57600080fd5b50565b600080600060408486031215610a8257600080fd5b8335610a8d81610a55565b9250602084013567ffffffffffffffff80821115610aaa57600080fd5b818601915086601f830112610abe57600080fd5b813581811115610acd57600080fd5b876020828501011115610adf57600080fd5b6020830194508093505050509250925092565b600060208284031215610b0457600080fd5b5035919050565b600060208284031215610b1d57600080fd5b8135610a2c81610a55565b600060208083528351808285015260005b81811015610b5557858101830151858201604001528201610b39565b81811115610b67576000604083870101525b50601f01601f1916929092016040019392505050565b60008060408385031215610b9057600080fd5b823591506020830135610ba281610a55565b809150509250929050565b634e487b7160e01b600052601160045260246000fd5b600082821015610bd557610bd5610bad565b500390565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610c2c57600080fd5b81518015158114610a2c57600080fd5b60008219821115610c4f57610c4f610bad565b500190565b600060208284031215610c6657600080fd5b8151610a2c81610a5556fea2646970667358221220079619a490fb615df4dfb4f0faf461daba2de4d2d15175d5e1567b7fa84d14d364736f6c634300080d0033";

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
    protected Retailer(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Retailer(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Retailer(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Retailer(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
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
    public static Retailer load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Retailer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Retailer load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Retailer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Retailer load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Retailer(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Retailer load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Retailer(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Retailer> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Retailer.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Retailer> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Retailer.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<Retailer> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Retailer.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Retailer> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Retailer.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
