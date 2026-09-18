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
import org.web3j.abi.datatypes.generated.Bytes32;
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
public class Pharmacy extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161122738038061122783398101604081905261002f91610119565b600380546001600160a01b0319166001600160a01b0384161790556040518190610058906100f0565b6001600160a01b039091168152602001604051809103906000f080158015610084573d6000803e3d6000fd5b50600480546001600160a01b0319166001600160a01b0392909216919091179055604080518082019091526008815267506861726d61637960c01b6020909101527f9e2d0d7197b7c2c8505831f18b6f769f5c1955bd89c99584c92bb75c3fa865cf600255505061014c565b61047880610daf83390190565b80516001600160a01b038116811461011457600080fd5b919050565b6000806040838503121561012c57600080fd5b610135836100fd565b9150610143602084016100fd565b90509250929050565b610c548061015b6000396000f3fe608060405234801561001057600080fd5b50600436106100f55760003560e01c806389c15be211610097578063add39d7d11610066578063add39d7d1461023c578063b5fa94b21461024f578063d0bf6d151461028b578063ee3027341461029e57600080fd5b806389c15be2146101cf57806395356651146101f85780639ac9d095146102215780639ad179be1461022957600080fd5b806348db5f89116100d357806348db5f891461016a5780634c4fb8051461017d57806353e06571146101935780636e8eb7bc146101a657600080fd5b806301ffc9a7146100fa578063274b02a7146101225780633e5d1dd51461014d575b600080fd5b61010d610108366004610970565b6102a6565b60405190151581526020015b60405180910390f35b6101356101303660046109a1565b6102dd565b6040516001600160a01b039091168152602001610119565b61016861015b3660046109d0565b8051602090910120600255565b005b600454610135906001600160a01b031681565b610185610464565b604051908152602001610119565b600354610135906001600160a01b031681565b6101356101b43660046109a1565b6000908152602081905260409020546001600160a01b031690565b6101356101dd3660046109a1565b6001602052600090815260409020546001600160a01b031681565b6101356102063660046109a1565b6000602081905290815260409020546001600160a01b031681565b600254610185565b61010d610237366004610a99565b610474565b61010d61024a3660046109a1565b6104f5565b61016861025d366004610b1e565b60009182526001602052604090912080546001600160a01b0319166001600160a01b03909216919091179055565b6101686102993660046109a1565b61056b565b6101686106b6565b60006301ffc9a760e01b6001600160e01b0319831614806102d757506001600160e01b031982166338cce89560e01b145b92915050565b6000818152600160205260408082205490516213ba5160e61b8152600481018490526001600160a01b0390911690829082906304ee9440906024016020604051808303816000875af1158015610337573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061035b9190610b4e565b9050600061037082636f8ae2e560e11b61089a565b9050806103d35760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420436f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084015b60405180910390fd5b6001600160a01b0382161561045c576000858152602081905260409081902080546001600160a01b0319166001600160a01b03851690811790915590516327f482af60e21b8152306004820152639fd20abc90602401600060405180830381600087803b15801561044357600080fd5b505af1158015610457573d6000803e3d6000fd5b505050505b509392505050565b600061046f60025490565b905090565b6004805460405163176fe4cf60e31b81526000926001600160a01b039092169163bb7f2678916104aa9188918891889101610b6b565b6020604051808303816000875af11580156104c9573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104ed9190610bc0565b949350505050565b604051631ba3adef60e21b81526004810182905260009081903090636e8eb7bc90602401602060405180830381865afa158015610536573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061055a9190610b4e565b6001600160a01b0316141592915050565b60405163add39d7d60e01b815260048101829052309063add39d7d90602401602060405180830381865afa1580156105a7573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105cb9190610bc0565b6105d25750565b600081815260208190526040808220549051630800096360e41b815260048101929092526001600160a01b0316908190638000963090602401600060405180830381600087803b15801561062557600080fd5b505af1158015610639573d6000803e3d6000fd5b50506040516327f482af60e21b8152600060048201526001600160a01b0384169250639fd20abc9150602401600060405180830381600087803b15801561067f57600080fd5b505af1158015610693573d6000803e3d6000fd5b5050506000928352505060208190526040902080546001600160a01b0319169055565b600080600360009054906101000a90046001600160a01b03166001600160a01b0316630574070c6040518163ffffffff1660e01b8152600401606060405180830381865afa15801561070c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107309190610bdb565b50915091508161078e5760405162461bcd60e51b815260206004820152602360248201527f4d65646963696e6520686173206e6f74206265656e2064656c697665726564206044820152621e595d60ea1b60648201526084016103ca565b80156107dc5760405162461bcd60e51b815260206004820152601960248201527f4d65646963696e6520616c72656164792076657269666965640000000000000060448201526064016103ca565b6040517f0aa2afc0c995677f4763c489682e3335932de0afa3b721c3bf41910edbae8e5690600090a16040517fcc88cac3d005802690761a789ced9fe1ac6030163b628e1d0f0050a3d4261d1d90600090a1600360009054906101000a90046001600160a01b03166001600160a01b0316638d29e2cb6040518163ffffffff1660e01b8152600401600060405180830381600087803b15801561087e57600080fd5b505af1158015610892573d6000803e3d6000fd5b505050505050565b600080806108af856301ffc9a760e01b61093c565b90925090508115806108bf575080155b156108cf576000925050506102d7565b6108e1856001600160e01b031961093c565b90925090508115806108f257508015155b15610902576000925050506102d7565b61090c858561093c565b90925090506001821480156109215750806001145b15610931576001925050506102d7565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b60006020828403121561098257600080fd5b81356001600160e01b03198116811461099a57600080fd5b9392505050565b6000602082840312156109b357600080fd5b5035919050565b634e487b7160e01b600052604160045260246000fd5b6000602082840312156109e257600080fd5b813567ffffffffffffffff808211156109fa57600080fd5b818401915084601f830112610a0e57600080fd5b813581811115610a2057610a206109ba565b604051601f8201601f19908116603f01168101908382118183101715610a4857610a486109ba565b81604052828152876020848701011115610a6157600080fd5b826020860160208301376000928101602001929092525095945050505050565b6001600160a01b0381168114610a9657600080fd5b50565b600080600060408486031215610aae57600080fd5b8335610ab981610a81565b9250602084013567ffffffffffffffff80821115610ad657600080fd5b818601915086601f830112610aea57600080fd5b813581811115610af957600080fd5b876020828501011115610b0b57600080fd5b6020830194508093505050509250925092565b60008060408385031215610b3157600080fd5b823591506020830135610b4381610a81565b809150509250929050565b600060208284031215610b6057600080fd5b815161099a81610a81565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b80518015158114610bbb57600080fd5b919050565b600060208284031215610bd257600080fd5b61099a82610bab565b600080600060608486031215610bf057600080fd5b610bf984610bab565b9250610c0760208501610bab565b9150610c1560408501610bab565b9050925092509256fea2646970667358221220ee836b7930536a80b1ce8d34434f3eb32a82ae07f9280b0c6b9a3dbe6b4f03f164736f6c634300080d0033608060405234801561001057600080fd5b5060405161047838038061047883398101604081905261002f91610054565b600180546001600160a01b0319166001600160a01b0392909216919091179055610084565b60006020828403121561006657600080fd5b81516001600160a01b038116811461007d57600080fd5b9392505050565b6103e5806100936000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80638da5cb5b14610046578063bb7f267814610076578063c954030614610099575b600080fd5b600154610059906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b6100896100843660046102bf565b6100bc565b604051901515815260200161006d565b6100896100a7366004610342565b60006020819052908152604090205460ff1681565b6001546000906001600160a01b031633146101315760405162461bcd60e51b815260206004820152602a60248201527f7265766572742062656361757365206974206973206e6f7420746865206f776e604482015269195c881858d8dbdd5b9d60b21b60648201526084015b60405180910390fd5b600061013d8484610281565b60408051600481526024810182526020810180516001600160e01b03166001600160e01b03198516179052905191925060009182916001600160a01b038916916101879190610364565b600060405180830381855af49150503d80600081146101c2576040519150601f19603f3d011682016040523d82523d6000602084013e6101c7565b606091505b50909250905081151560000361024f576001600160a01b0387166000908152602081905260409020805460ff191690558051156102075780518082602001fd5b60405162461bcd60e51b815260206004820152601f60248201527f46756e6374696f6e2063616c6c20686173206265656e207265766572746564006044820152606401610128565b5050506001600160a01b0384166000908152602081905260409020805460ff1916600190811790915590509392505050565b6000828260405161029392919061039f565b6040518091039020905092915050565b80356001600160a01b03811681146102ba57600080fd5b919050565b6000806000604084860312156102d457600080fd5b6102dd846102a3565b9250602084013567ffffffffffffffff808211156102fa57600080fd5b818601915086601f83011261030e57600080fd5b81358181111561031d57600080fd5b87602082850101111561032f57600080fd5b6020830194508093505050509250925092565b60006020828403121561035457600080fd5b61035d826102a3565b9392505050565b6000825160005b81811015610385576020818601810151858301520161036b565b81811115610394576000828501525b509190910192915050565b818382376000910190815291905056fea2646970667358221220842ffaa5301c29786eaecffd5c1edae4416e0a105f2221505d20ff0513143d5664736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ADDROLECREATOR = "addRoleCreator";

    public static final String FUNC_CREATECOMPARTMENTID = "createCompartmentID";

    public static final String FUNC_GETCOMPARTMENTID = "getCompartmentID";

    public static final String FUNC_GETMYCOMPARTMENTID = "getMyCompartmentID";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_PLAN = "plan";

    public static final String FUNC_PLAYROLECONTRACT = "playRoleContract";

    public static final String FUNC_PLAYER = "player";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_ROLECREATORS = "roleCreators";

    public static final String FUNC_ROLES = "roles";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_VERIFYMEDICINE = "verifyMedicine";

    public static final Event MEDICINECORRECT_EVENT = new Event("MedicineCorrect", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event RECEIVEDFORVERIFICATION_EVENT = new Event("ReceivedForVerification", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected Pharmacy(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Pharmacy(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Pharmacy(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Pharmacy(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<MedicineCorrectEventResponse> getMedicineCorrectEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MEDICINECORRECT_EVENT, transactionReceipt);
        ArrayList<MedicineCorrectEventResponse> responses = new ArrayList<MedicineCorrectEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MedicineCorrectEventResponse typedResponse = new MedicineCorrectEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MedicineCorrectEventResponse getMedicineCorrectEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MEDICINECORRECT_EVENT, log);
        MedicineCorrectEventResponse typedResponse = new MedicineCorrectEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<MedicineCorrectEventResponse> medicineCorrectEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMedicineCorrectEventFromLog(log));
    }

    public Flowable<MedicineCorrectEventResponse> medicineCorrectEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MEDICINECORRECT_EVENT));
        return medicineCorrectEventFlowable(filter);
    }

    public static List<ReceivedForVerificationEventResponse> getReceivedForVerificationEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RECEIVEDFORVERIFICATION_EVENT, transactionReceipt);
        ArrayList<ReceivedForVerificationEventResponse> responses = new ArrayList<ReceivedForVerificationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceivedForVerificationEventResponse typedResponse = new ReceivedForVerificationEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ReceivedForVerificationEventResponse getReceivedForVerificationEventFromLog(
            Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RECEIVEDFORVERIFICATION_EVENT, log);
        ReceivedForVerificationEventResponse typedResponse = new ReceivedForVerificationEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<ReceivedForVerificationEventResponse> receivedForVerificationEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getReceivedForVerificationEventFromLog(log));
    }

    public Flowable<ReceivedForVerificationEventResponse> receivedForVerificationEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIVEDFORVERIFICATION_EVENT));
        return receivedForVerificationEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addRole(byte[] _spec) {
        final Function function = new Function(
                FUNC_ADDROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRoleCreator(byte[] _roleAddress,
            String _roleCreator) {
        final Function function = new Function(
                FUNC_ADDROLECREATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress), 
                new org.web3j.abi.datatypes.Address(160, _roleCreator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createCompartmentID(String _message) {
        final Function function = new Function(
                FUNC_CREATECOMPARTMENTID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_message)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> getCompartmentID() {
        final Function function = new Function(FUNC_GETCOMPARTMENTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> getMyCompartmentID() {
        final Function function = new Function(FUNC_GETMYCOMPARTMENTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> getRole(byte[] _roleAddress) {
        final Function function = new Function(FUNC_GETROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> hasRole(byte[] _roleAddress) {
        final Function function = new Function(FUNC_HASROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> plan() {
        final Function function = new Function(FUNC_PLAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> playRoleContract(String _contract, String _fn) {
        final Function function = new Function(
                FUNC_PLAYROLECONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contract), 
                new org.web3j.abi.datatypes.Utf8String(_fn)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> player() {
        final Function function = new Function(FUNC_PLAYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeRole(byte[] _roleAddress) {
        final Function function = new Function(
                FUNC_REMOVEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_roleAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> roleCreators(byte[] param0) {
        final Function function = new Function(FUNC_ROLECREATORS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> roles(byte[] param0) {
        final Function function = new Function(FUNC_ROLES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceID) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> verifyMedicine() {
        final Function function = new Function(
                FUNC_VERIFYMEDICINE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Pharmacy load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Pharmacy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Pharmacy load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Pharmacy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Pharmacy load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Pharmacy(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Pharmacy load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Pharmacy(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Pharmacy> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _planAddress, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Pharmacy.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Pharmacy> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String _planAddress, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Pharmacy.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Pharmacy> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _planAddress, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Pharmacy.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Pharmacy> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String _planAddress, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Pharmacy.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class MedicineCorrectEventResponse extends BaseEventResponse {
    }

    public static class ReceivedForVerificationEventResponse extends BaseEventResponse {
    }
}
