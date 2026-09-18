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
public class Patient extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161127238038061127283398101604081905261002f9161012e565b600380546001600160a01b038086166001600160a01b0319928316179092556005805492851692909116919091179055604051819061006d90610105565b6001600160a01b039091168152602001604051809103906000f080158015610099573d6000803e3d6000fd5b50600480546001600160a01b0319166001600160a01b039290921691909117905560408051808201909152600781526614185d1a595b9d60ca1b6020909101527f675bc9802ff0994bbcafe826bffd0f15ec0a3ec17fd0cfbe035a08cbededf8a3600255505050610171565b61047880610dfa83390190565b80516001600160a01b038116811461012957600080fd5b919050565b60008060006060848603121561014357600080fd5b61014c84610112565b925061015a60208501610112565b915061016860408501610112565b90509250925092565b610c7a806101806000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c8063802459da116100975780639ad179be116100665780639ad179be1461024f578063add39d7d14610262578063b5fa94b214610275578063d0bf6d15146102b157600080fd5b8063802459da146101e257806389c15be2146101f5578063953566511461021e5780639ac9d0951461024757600080fd5b80634c4fb805116100d35780634c4fb8051461018857806353e065711461019e5780636e8eb7bc146101b15780637d94ad98146101da57600080fd5b806301ffc9a714610105578063274b02a71461012d5780633e5d1dd51461015857806348db5f8914610175575b600080fd5b610118610113366004610996565b6102c4565b60405190151581526020015b60405180910390f35b61014061013b3660046109c7565b6102fb565b6040516001600160a01b039091168152602001610124565b6101736101663660046109f6565b8051602090910120600255565b005b600454610140906001600160a01b031681565b610190610482565b604051908152602001610124565b600354610140906001600160a01b031681565b6101406101bf3660046109c7565b6000908152602081905260409020546001600160a01b031690565b610173610492565b600554610140906001600160a01b031681565b6101406102033660046109c7565b6001602052600090815260409020546001600160a01b031681565b61014061022c3660046109c7565b6000602081905290815260409020546001600160a01b031681565b600254610190565b61011861025d366004610abf565b61067e565b6101186102703660046109c7565b6106ff565b610173610283366004610b44565b60009182526001602052604090912080546001600160a01b0319166001600160a01b03909216919091179055565b6101736102bf3660046109c7565b610775565b60006301ffc9a760e01b6001600160e01b0319831614806102f557506001600160e01b031982166338cce89560e01b145b92915050565b6000818152600160205260408082205490516213ba5160e61b8152600481018490526001600160a01b0390911690829082906304ee9440906024016020604051808303816000875af1158015610355573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103799190610b74565b9050600061038e82636f8ae2e560e11b6108c0565b9050806103f15760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420436f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084015b60405180910390fd5b6001600160a01b0382161561047a576000858152602081905260409081902080546001600160a01b0319166001600160a01b03851690811790915590516327f482af60e21b8152306004820152639fd20abc90602401600060405180830381600087803b15801561046157600080fd5b505af1158015610475573d6000803e3d6000fd5b505050505b509392505050565b600061048d60025490565b905090565b600080600360009054906101000a90046001600160a01b03166001600160a01b0316630574070c6040518163ffffffff1660e01b8152600401606060405180830381865afa1580156104e8573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061050c9190610ba6565b9250925050816105725760405162461bcd60e51b815260206004820152602b60248201527f4d65646963696e65206d7573742062652076657269666965642062792070686160448201526a1c9b5858de48199a5c9cdd60aa1b60648201526084016103e8565b80156105c05760405162461bcd60e51b815260206004820152601d60248201527f50617469656e742068617320616c726561647920636f6e6669726d656400000060448201526064016103e8565b6040517f205e93e1d0f88dd634f95a7f6f5863b4d84fb8ed441f64e673c703832d91093490600090a16040517f7a331846d947819dcba40782a0187413a987337cf079ba81ce727acb26984ef790600090a1600360009054906101000a90046001600160a01b03166001600160a01b031663f29f67466040518163ffffffff1660e01b8152600401600060405180830381600087803b15801561066257600080fd5b505af1158015610676573d6000803e3d6000fd5b505050505050565b6004805460405163176fe4cf60e31b81526000926001600160a01b039092169163bb7f2678916106b49188918891889101610be9565b6020604051808303816000875af11580156106d3573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106f79190610c29565b949350505050565b604051631ba3adef60e21b81526004810182905260009081903090636e8eb7bc90602401602060405180830381865afa158015610740573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107649190610b74565b6001600160a01b0316141592915050565b60405163add39d7d60e01b815260048101829052309063add39d7d90602401602060405180830381865afa1580156107b1573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107d59190610c29565b6107dc5750565b600081815260208190526040808220549051630800096360e41b815260048101929092526001600160a01b0316908190638000963090602401600060405180830381600087803b15801561082f57600080fd5b505af1158015610843573d6000803e3d6000fd5b50506040516327f482af60e21b8152600060048201526001600160a01b0384169250639fd20abc9150602401600060405180830381600087803b15801561088957600080fd5b505af115801561089d573d6000803e3d6000fd5b5050506000928352505060208190526040902080546001600160a01b0319169055565b600080806108d5856301ffc9a760e01b610962565b90925090508115806108e5575080155b156108f5576000925050506102f5565b610907856001600160e01b0319610962565b909250905081158061091857508015155b15610928576000925050506102f5565b6109328585610962565b90925090506001821480156109475750806001145b15610957576001925050506102f5565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b6000602082840312156109a857600080fd5b81356001600160e01b0319811681146109c057600080fd5b9392505050565b6000602082840312156109d957600080fd5b5035919050565b634e487b7160e01b600052604160045260246000fd5b600060208284031215610a0857600080fd5b813567ffffffffffffffff80821115610a2057600080fd5b818401915084601f830112610a3457600080fd5b813581811115610a4657610a466109e0565b604051601f8201601f19908116603f01168101908382118183101715610a6e57610a6e6109e0565b81604052828152876020848701011115610a8757600080fd5b826020860160208301376000928101602001929092525095945050505050565b6001600160a01b0381168114610abc57600080fd5b50565b600080600060408486031215610ad457600080fd5b8335610adf81610aa7565b9250602084013567ffffffffffffffff80821115610afc57600080fd5b818601915086601f830112610b1057600080fd5b813581811115610b1f57600080fd5b876020828501011115610b3157600080fd5b6020830194508093505050509250925092565b60008060408385031215610b5757600080fd5b823591506020830135610b6981610aa7565b809150509250929050565b600060208284031215610b8657600080fd5b81516109c081610aa7565b80518015158114610ba157600080fd5b919050565b600080600060608486031215610bbb57600080fd5b610bc484610b91565b9250610bd260208501610b91565b9150610be060408501610b91565b90509250925092565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610c3b57600080fd5b6109c082610b9156fea26469706673582212204ce253f55ab2fb2974b03b0cb08f4783e574983413dc5fbf546df70fd8a7264964736f6c634300080d0033608060405234801561001057600080fd5b5060405161047838038061047883398101604081905261002f91610054565b600180546001600160a01b0319166001600160a01b0392909216919091179055610084565b60006020828403121561006657600080fd5b81516001600160a01b038116811461007d57600080fd5b9392505050565b6103e5806100936000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80638da5cb5b14610046578063bb7f267814610076578063c954030614610099575b600080fd5b600154610059906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b6100896100843660046102bf565b6100bc565b604051901515815260200161006d565b6100896100a7366004610342565b60006020819052908152604090205460ff1681565b6001546000906001600160a01b031633146101315760405162461bcd60e51b815260206004820152602a60248201527f7265766572742062656361757365206974206973206e6f7420746865206f776e604482015269195c881858d8dbdd5b9d60b21b60648201526084015b60405180910390fd5b600061013d8484610281565b60408051600481526024810182526020810180516001600160e01b03166001600160e01b03198516179052905191925060009182916001600160a01b038916916101879190610364565b600060405180830381855af49150503d80600081146101c2576040519150601f19603f3d011682016040523d82523d6000602084013e6101c7565b606091505b50909250905081151560000361024f576001600160a01b0387166000908152602081905260409020805460ff191690558051156102075780518082602001fd5b60405162461bcd60e51b815260206004820152601f60248201527f46756e6374696f6e2063616c6c20686173206265656e207265766572746564006044820152606401610128565b5050506001600160a01b0384166000908152602081905260409020805460ff1916600190811790915590509392505050565b6000828260405161029392919061039f565b6040518091039020905092915050565b80356001600160a01b03811681146102ba57600080fd5b919050565b6000806000604084860312156102d457600080fd5b6102dd846102a3565b9250602084013567ffffffffffffffff808211156102fa57600080fd5b818601915086601f83011261030e57600080fd5b81358181111561031d57600080fd5b87602082850101111561032f57600080fd5b6020830194508093505050509250925092565b60006020828403121561035457600080fd5b61035d826102a3565b9392505050565b6000825160005b81811015610385576020818601810151858301520161036b565b81811115610394576000828501525b509190910192915050565b818382376000910190815291905056fea2646970667358221220842ffaa5301c29786eaecffd5c1edae4416e0a105f2221505d20ff0513143d5664736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ADDROLECREATOR = "addRoleCreator";

    public static final String FUNC_CONFIRMRECEIPT = "confirmReceipt";

    public static final String FUNC_CREATECOMPARTMENTID = "createCompartmentID";

    public static final String FUNC_GETCOMPARTMENTID = "getCompartmentID";

    public static final String FUNC_GETMYCOMPARTMENTID = "getMyCompartmentID";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_PHARMACYADDRESS = "pharmacyAddress";

    public static final String FUNC_PLAN = "plan";

    public static final String FUNC_PLAYROLECONTRACT = "playRoleContract";

    public static final String FUNC_PLAYER = "player";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_ROLECREATORS = "roleCreators";

    public static final String FUNC_ROLES = "roles";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final Event BENEFITACKNOWLEDGED_EVENT = new Event("BenefitAcknowledged", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event PATIENTRECEIVED_EVENT = new Event("PatientReceived", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected Patient(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Patient(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Patient(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Patient(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BenefitAcknowledgedEventResponse> getBenefitAcknowledgedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BENEFITACKNOWLEDGED_EVENT, transactionReceipt);
        ArrayList<BenefitAcknowledgedEventResponse> responses = new ArrayList<BenefitAcknowledgedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BenefitAcknowledgedEventResponse typedResponse = new BenefitAcknowledgedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BenefitAcknowledgedEventResponse getBenefitAcknowledgedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BENEFITACKNOWLEDGED_EVENT, log);
        BenefitAcknowledgedEventResponse typedResponse = new BenefitAcknowledgedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<BenefitAcknowledgedEventResponse> benefitAcknowledgedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBenefitAcknowledgedEventFromLog(log));
    }

    public Flowable<BenefitAcknowledgedEventResponse> benefitAcknowledgedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BENEFITACKNOWLEDGED_EVENT));
        return benefitAcknowledgedEventFlowable(filter);
    }

    public static List<PatientReceivedEventResponse> getPatientReceivedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PATIENTRECEIVED_EVENT, transactionReceipt);
        ArrayList<PatientReceivedEventResponse> responses = new ArrayList<PatientReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PatientReceivedEventResponse typedResponse = new PatientReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PatientReceivedEventResponse getPatientReceivedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PATIENTRECEIVED_EVENT, log);
        PatientReceivedEventResponse typedResponse = new PatientReceivedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<PatientReceivedEventResponse> patientReceivedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPatientReceivedEventFromLog(log));
    }

    public Flowable<PatientReceivedEventResponse> patientReceivedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PATIENTRECEIVED_EVENT));
        return patientReceivedEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> confirmReceipt() {
        final Function function = new Function(
                FUNC_CONFIRMRECEIPT, 
                Arrays.<Type>asList(), 
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

    public RemoteFunctionCall<String> pharmacyAddress() {
        final Function function = new Function(FUNC_PHARMACYADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    @Deprecated
    public static Patient load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Patient(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Patient load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Patient(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Patient load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Patient(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Patient load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Patient(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Patient> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _planAddress, String _pharmacyAddress,
            String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _pharmacyAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Patient.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Patient> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String _planAddress, String _pharmacyAddress,
            String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _pharmacyAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Patient.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Patient> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _planAddress, String _pharmacyAddress,
            String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _pharmacyAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Patient.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Patient> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String _planAddress, String _pharmacyAddress,
            String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _pharmacyAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Patient.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class BenefitAcknowledgedEventResponse extends BaseEventResponse {
    }

    public static class PatientReceivedEventResponse extends BaseEventResponse {
    }
}
