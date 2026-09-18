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
import org.web3j.abi.datatypes.Array;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
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
public class Wholesaler extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50611003806100206000396000f3fe608060405234801561001057600080fd5b506004361061012c5760003560e01c80638b04e10b116100ad578063ccd3c4bc11610071578063ccd3c4bc14610260578063d0bf6d1514610294578063dfa9ae61146102a7578063e959b38a146102c8578063f2f4eb26146102db57600080fd5b80638b04e10b1461021357806395ec5ce31461022a5780639fd20abc14610232578063b2becc2114610245578063b3cac0a51461024d57600080fd5b806355dc9245116100f457806355dc9245146101bf5780636a4701fc146101d25780636b5a3e39146101e55780636e8eb7bc146101ed578063800096301461020057600080fd5b806301ffc9a714610131578063201b631b1461015957806340a9efc81461016c578063413f7ec814610197578063496a3c48146101ac575b600080fd5b61014461013f366004610b60565b6102ee565b60405190151581526020015b60405180910390f35b610144610167366004610ba9565b610325565b60015461017f906001600160a01b031681565b6040516001600160a01b039091168152602001610150565b6101aa6101a5366004610c2e565b6103a5565b005b6101aa6101ba366004610c47565b6103bf565b6101aa6101cd366004610c2e565b610422565b6101aa6101e0366004610d3a565b6104a1565b61017f6105b6565b61017f6101fb366004610c2e565b610633565b6101aa61020e366004610c47565b6106a3565b61021c60025481565b604051908152602001610150565b6101aa610744565b6101aa610240366004610c47565b61079f565b60025461021c565b61014461025b366004610c2e565b610843565b60408051808201825260128152711a5d081a185cc81899595b881c1b185e595960721b602082015290516101509190610df3565b6101aa6102a2366004610c2e565b6108b1565b6102ba6102b5366004610c2e565b6108e2565b604051610150929190610e48565b6101aa6102d6366004610ec3565b6109c0565b60005461017f906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061031f57506001600160e01b03198216636f8ae2e560e11b145b92915050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b9061035a90879087908790600401610ef3565b6020604051808303816000875af1158015610379573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061039d9190610f33565b949350505050565b80600260008282546103b79190610f6b565b909155505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561040757600080fd5b505af115801561041b573d6000803e3d6000fd5b5050505050565b600254811061048f5760405162461bcd60e51b815260206004820152602e60248201527f7765206e65656420746f20636865636b20626f756e6461727920666f7220697460448201526d656d73496e496e76656e746f727960901b60648201526084015b60405180910390fd5b80600260008282546103b79190610f83565b80518251146104e45760405162461bcd60e51b815260206004820152600f60248201526e0d8cadccee8d040dad2e6dac2e8c6d608b1b6044820152606401610486565b604080518082019091528281526020808201839052600380546001810182556000919091528251805160029092027fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b019261054492849290910190610b00565b50602082810151805161055d9260018501920190610b00565b505060035461056f9150600190610f83565b306001600160a01b03167f5ec783f73d2b3477059d9e042ba4c563e926851caa69ff4e237244a43433754684846040516105aa929190610e48565b60405180910390a35050565b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa15801561060a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061062e9190610f9a565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af115801561067f573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061031f9190610f9a565b6001600160a01b038116156107225760006106c582637365940f60e01b610a2a565b9050806107205760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b6064820152608401610486565b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561078557600080fd5b505af1158015610799573d6000803e3d6000fd5b50505050565b6001600160a01b038116156108215760006107c1826338cce89560e01b610a2a565b90508061081f5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b6064820152608401610486565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa15801561088d573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061031f9190610f33565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d15906024016103ed565b6060806000600384815481106108fa576108fa610fb7565b9060005260206000209060020201905080600001816001018180548060200260200160405190810160405280929190818152602001828054801561095d57602002820191906000526020600020905b815481526020019060010190808311610949575b50505050509150808054806020026020016040519081016040528092919081815260200182805480156109af57602002820191906000526020600020905b81548152602001906001019080831161099b575b505050505090509250925050915091565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401600060405180830381600087803b158015610a0e57600080fd5b505af1158015610a22573d6000803e3d6000fd5b505050505050565b60008080610a3f856301ffc9a760e01b610acc565b9092509050811580610a4f575080155b15610a5f5760009250505061031f565b610a71856001600160e01b0319610acc565b9092509050811580610a8257508015155b15610a925760009250505061031f565b610a9c8585610acc565b9092509050600182148015610ab15750806001145b15610ac15760019250505061031f565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b828054828255906000526020600020908101928215610b3b579160200282015b82811115610b3b578251825591602001919060010190610b20565b50610b47929150610b4b565b5090565b5b80821115610b475760008155600101610b4c565b600060208284031215610b7257600080fd5b81356001600160e01b031981168114610b8a57600080fd5b9392505050565b6001600160a01b0381168114610ba657600080fd5b50565b600080600060408486031215610bbe57600080fd5b8335610bc981610b91565b9250602084013567ffffffffffffffff80821115610be657600080fd5b818601915086601f830112610bfa57600080fd5b813581811115610c0957600080fd5b876020828501011115610c1b57600080fd5b6020830194508093505050509250925092565b600060208284031215610c4057600080fd5b5035919050565b600060208284031215610c5957600080fd5b8135610b8a81610b91565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715610ca357610ca3610c64565b604052919050565b600067ffffffffffffffff821115610cc557610cc5610c64565b5060051b60200190565b600082601f830112610ce057600080fd5b81356020610cf5610cf083610cab565b610c7a565b82815260059290921b84018101918181019086841115610d1457600080fd5b8286015b84811015610d2f5780358352918301918301610d18565b509695505050505050565b60008060408385031215610d4d57600080fd5b823567ffffffffffffffff80821115610d6557600080fd5b818501915085601f830112610d7957600080fd5b81356020610d89610cf083610cab565b82815260059290921b84018101918181019089841115610da857600080fd5b948201945b83861015610dc657853582529482019490820190610dad565b96505086013592505080821115610ddc57600080fd5b50610de985828601610ccf565b9150509250929050565b600060208083528351808285015260005b81811015610e2057858101830151858201604001528201610e04565b81811115610e32576000604083870101525b50601f01601f1916929092016040019392505050565b604080825283519082018190526000906020906060840190828701845b82811015610e8157815184529284019290840190600101610e65565b5050508381038285015284518082528583019183019060005b81811015610eb657835183529284019291840191600101610e9a565b5090979650505050505050565b60008060408385031215610ed657600080fd5b823591506020830135610ee881610b91565b809150509250929050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610f4557600080fd5b81518015158114610b8a57600080fd5b634e487b7160e01b600052601160045260246000fd5b60008219821115610f7e57610f7e610f55565b500190565b600082821015610f9557610f95610f55565b500390565b600060208284031215610fac57600080fd5b8151610b8a81610b91565b634e487b7160e01b600052603260045260246000fdfea2646970667358221220a1ab94dbdec77affd7ded2b7572492fcb20f79c15b873dfc25f82fbfdadc233d64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETINVENTORYSTATUS = "getInventoryStatus";

    public static final String FUNC_GETMANIFEST = "getManifest";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_ITEMSININVENTORYWHOLESALER = "itemsInInventoryWholeSaler";

    public static final String FUNC_PLAYCONTRACTFORIDENTITY = "playContractForIdentity";

    public static final String FUNC_POPINVENTORY = "popInventory";

    public static final String FUNC_PUSHINVENTORY = "pushInventory";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SUBMITBULKMANIFEST = "submitBulkManifest";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final Event BULKMANIFESTSUBMITTED_EVENT = new Event("BulkManifestSubmitted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected Wholesaler(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Wholesaler(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Wholesaler(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Wholesaler(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BulkManifestSubmittedEventResponse> getBulkManifestSubmittedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BULKMANIFESTSUBMITTED_EVENT, transactionReceipt);
        ArrayList<BulkManifestSubmittedEventResponse> responses = new ArrayList<BulkManifestSubmittedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BulkManifestSubmittedEventResponse typedResponse = new BulkManifestSubmittedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.wholesaler = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.manifestId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.stops = (List<byte[]>) ((Array) eventValues.getNonIndexedValues().get(0)).getNativeValueCopy();
            typedResponse.timeWindows = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(1)).getNativeValueCopy();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BulkManifestSubmittedEventResponse getBulkManifestSubmittedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BULKMANIFESTSUBMITTED_EVENT, log);
        BulkManifestSubmittedEventResponse typedResponse = new BulkManifestSubmittedEventResponse();
        typedResponse.log = log;
        typedResponse.wholesaler = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.manifestId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.stops = (List<byte[]>) ((Array) eventValues.getNonIndexedValues().get(0)).getNativeValueCopy();
        typedResponse.timeWindows = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(1)).getNativeValueCopy();
        return typedResponse;
    }

    public Flowable<BulkManifestSubmittedEventResponse> bulkManifestSubmittedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBulkManifestSubmittedEventFromLog(log));
    }

    public Flowable<BulkManifestSubmittedEventResponse> bulkManifestSubmittedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BULKMANIFESTSUBMITTED_EVENT));
        return bulkManifestSubmittedEventFlowable(filter);
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

    public RemoteFunctionCall<Tuple2<List<byte[]>, List<BigInteger>>> getManifest(BigInteger idx) {
        final Function function = new Function(FUNC_GETMANIFEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple2<List<byte[]>, List<BigInteger>>>(function,
                new Callable<Tuple2<List<byte[]>, List<BigInteger>>>() {
                    @Override
                    public Tuple2<List<byte[]>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<byte[]>, List<BigInteger>>(
                                convertToNative((List<Bytes32>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()));
                    }
                });
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

    public RemoteFunctionCall<BigInteger> itemsInInventoryWholeSaler() {
        final Function function = new Function(FUNC_ITEMSININVENTORYWHOLESALER, 
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

    public RemoteFunctionCall<TransactionReceipt> submitBulkManifest(List<byte[]> stops,
            List<BigInteger> timeWindows) {
        final Function function = new Function(
                FUNC_SUBMITBULKMANIFEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(stops, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(timeWindows, org.web3j.abi.datatypes.generated.Uint256.class))), 
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
    public static Wholesaler load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Wholesaler(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Wholesaler load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Wholesaler(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Wholesaler load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Wholesaler(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Wholesaler load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Wholesaler(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Wholesaler> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Wholesaler.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Wholesaler> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Wholesaler.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<Wholesaler> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Wholesaler.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Wholesaler> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Wholesaler.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class BulkManifestSubmittedEventResponse extends BaseEventResponse {
        public String wholesaler;

        public BigInteger manifestId;

        public List<byte[]> stops;

        public List<BigInteger> timeWindows;
    }
}
