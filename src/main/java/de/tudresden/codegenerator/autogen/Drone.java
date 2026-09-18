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
public class Drone extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161125338038061125383398101604081905261002f91610116565b600380546001600160a01b0319166001600160a01b0384161790556040518190610058906100ed565b6001600160a01b039091168152602001604051809103906000f080158015610084573d6000803e3d6000fd5b50600480546001600160a01b0319166001600160a01b039290921691909117905560408051808201909152600581526444726f6e6560d81b6020909101527f628490b581bbd4a068f65a9a4a7b69a372169b821a42366b2b7ea884c6e5b8f96002555050610149565b61047880610ddb83390190565b80516001600160a01b038116811461011157600080fd5b919050565b6000806040838503121561012957600080fd5b610132836100fa565b9150610140602084016100fa565b90509250929050565b610c83806101586000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c806389c15be211610097578063add39d7d11610066578063add39d7d1461024f578063b5fa94b214610262578063b8f3b5511461029e578063d0bf6d15146102a657600080fd5b806389c15be2146101e2578063953566511461020b5780639ac9d095146102345780639ad179be1461023c57600080fd5b80634c4fb805116100d35780634c4fb8051461018857806350cc31131461019e57806353e06571146101a65780636e8eb7bc146101b957600080fd5b806301ffc9a714610105578063274b02a71461012d5780633e5d1dd51461015857806348db5f8914610175575b600080fd5b61011861011336600461099f565b6102b9565b60405190151581526020015b60405180910390f35b61014061013b3660046109d0565b6102f0565b6040516001600160a01b039091168152602001610124565b6101736101663660046109ff565b8051602090910120600255565b005b600454610140906001600160a01b031681565b610190610477565b604051908152602001610124565b610118610487565b600354610140906001600160a01b031681565b6101406101c73660046109d0565b6000908152602081905260409020546001600160a01b031690565b6101406101f03660046109d0565b6001602052600090815260409020546001600160a01b031681565b6101406102193660046109d0565b6000602081905290815260409020546001600160a01b031681565b600254610190565b61011861024a366004610ac8565b61050a565b61011861025d3660046109d0565b61058b565b610173610270366004610b4d565b60009182526001602052604090912080546001600160a01b0319166001600160a01b03909216919091179055565b610173610601565b6101736102b43660046109d0565b61077e565b60006301ffc9a760e01b6001600160e01b0319831614806102ea57506001600160e01b031982166338cce89560e01b145b92915050565b6000818152600160205260408082205490516213ba5160e61b8152600481018490526001600160a01b0390911690829082906304ee9440906024016020604051808303816000875af115801561034a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061036e9190610b7d565b9050600061038382636f8ae2e560e11b6108c9565b9050806103e65760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420436f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084015b60405180910390fd5b6001600160a01b0382161561046f576000858152602081905260409081902080546001600160a01b0319166001600160a01b03851690811790915590516327f482af60e21b8152306004820152639fd20abc90602401600060405180830381600087803b15801561045657600080fd5b505af115801561046a573d6000803e3d6000fd5b505050505b509392505050565b600061048260025490565b905090565b600080600360009054906101000a90046001600160a01b03166001600160a01b0316630574070c6040518163ffffffff1660e01b8152600401606060405180830381865afa1580156104dd573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105019190610baf565b50909392505050565b6004805460405163176fe4cf60e31b81526000926001600160a01b039092169163bb7f2678916105409188918891889101610bf2565b6020604051808303816000875af115801561055f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105839190610c32565b949350505050565b604051631ba3adef60e21b81526004810182905260009081903090636e8eb7bc90602401602060405180830381865afa1580156105cc573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105f09190610b7d565b6001600160a01b0316141592915050565b6003546040805163015d01c360e21b815290516000926001600160a01b031691630574070c9160048083019260609291908290030181865afa15801561064b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061066f9190610baf565b5050905080156106c15760405162461bcd60e51b815260206004820152601a60248201527f4d65646963696e6520616c72656164792064656c69766572656400000000000060448201526064016103dd565b6040517fa482f745edba5ff7b0d1f9f226084c550365883fb34909dba9268774a35dc0c590600090a16040517f48b6fa901da23fa93049eb7150f2bbd1b56b979073f187d316195123479e7d3090600090a1600360009054906101000a90046001600160a01b03166001600160a01b031663597825d06040518163ffffffff1660e01b8152600401600060405180830381600087803b15801561076357600080fd5b505af1158015610777573d6000803e3d6000fd5b5050505050565b60405163add39d7d60e01b815260048101829052309063add39d7d90602401602060405180830381865afa1580156107ba573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107de9190610c32565b6107e55750565b600081815260208190526040808220549051630800096360e41b815260048101929092526001600160a01b0316908190638000963090602401600060405180830381600087803b15801561083857600080fd5b505af115801561084c573d6000803e3d6000fd5b50506040516327f482af60e21b8152600060048201526001600160a01b0384169250639fd20abc9150602401600060405180830381600087803b15801561089257600080fd5b505af11580156108a6573d6000803e3d6000fd5b5050506000928352505060208190526040902080546001600160a01b0319169055565b600080806108de856301ffc9a760e01b61096b565b90925090508115806108ee575080155b156108fe576000925050506102ea565b610910856001600160e01b031961096b565b909250905081158061092157508015155b15610931576000925050506102ea565b61093b858561096b565b90925090506001821480156109505750806001145b15610960576001925050506102ea565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b6000602082840312156109b157600080fd5b81356001600160e01b0319811681146109c957600080fd5b9392505050565b6000602082840312156109e257600080fd5b5035919050565b634e487b7160e01b600052604160045260246000fd5b600060208284031215610a1157600080fd5b813567ffffffffffffffff80821115610a2957600080fd5b818401915084601f830112610a3d57600080fd5b813581811115610a4f57610a4f6109e9565b604051601f8201601f19908116603f01168101908382118183101715610a7757610a776109e9565b81604052828152876020848701011115610a9057600080fd5b826020860160208301376000928101602001929092525095945050505050565b6001600160a01b0381168114610ac557600080fd5b50565b600080600060408486031215610add57600080fd5b8335610ae881610ab0565b9250602084013567ffffffffffffffff80821115610b0557600080fd5b818601915086601f830112610b1957600080fd5b813581811115610b2857600080fd5b876020828501011115610b3a57600080fd5b6020830194508093505050509250925092565b60008060408385031215610b6057600080fd5b823591506020830135610b7281610ab0565b809150509250929050565b600060208284031215610b8f57600080fd5b81516109c981610ab0565b80518015158114610baa57600080fd5b919050565b600080600060608486031215610bc457600080fd5b610bcd84610b9a565b9250610bdb60208501610b9a565b9150610be960408501610b9a565b90509250925092565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208284031215610c4457600080fd5b6109c982610b9a56fea2646970667358221220f3cb333f2ecf080c84848c351c7f0883a98db62b07d97152586db2a962a22f7564736f6c634300080d0033608060405234801561001057600080fd5b5060405161047838038061047883398101604081905261002f91610054565b600180546001600160a01b0319166001600160a01b0392909216919091179055610084565b60006020828403121561006657600080fd5b81516001600160a01b038116811461007d57600080fd5b9392505050565b6103e5806100936000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80638da5cb5b14610046578063bb7f267814610076578063c954030614610099575b600080fd5b600154610059906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b6100896100843660046102bf565b6100bc565b604051901515815260200161006d565b6100896100a7366004610342565b60006020819052908152604090205460ff1681565b6001546000906001600160a01b031633146101315760405162461bcd60e51b815260206004820152602a60248201527f7265766572742062656361757365206974206973206e6f7420746865206f776e604482015269195c881858d8dbdd5b9d60b21b60648201526084015b60405180910390fd5b600061013d8484610281565b60408051600481526024810182526020810180516001600160e01b03166001600160e01b03198516179052905191925060009182916001600160a01b038916916101879190610364565b600060405180830381855af49150503d80600081146101c2576040519150601f19603f3d011682016040523d82523d6000602084013e6101c7565b606091505b50909250905081151560000361024f576001600160a01b0387166000908152602081905260409020805460ff191690558051156102075780518082602001fd5b60405162461bcd60e51b815260206004820152601f60248201527f46756e6374696f6e2063616c6c20686173206265656e207265766572746564006044820152606401610128565b5050506001600160a01b0384166000908152602081905260409020805460ff1916600190811790915590509392505050565b6000828260405161029392919061039f565b6040518091039020905092915050565b80356001600160a01b03811681146102ba57600080fd5b919050565b6000806000604084860312156102d457600080fd5b6102dd846102a3565b9250602084013567ffffffffffffffff808211156102fa57600080fd5b818601915086601f83011261030e57600080fd5b81358181111561031d57600080fd5b87602082850101111561032f57600080fd5b6020830194508093505050509250925092565b60006020828403121561035457600080fd5b61035d826102a3565b9392505050565b6000825160005b81811015610385576020818601810151858301520161036b565b81811115610394576000828501525b509190910192915050565b818382376000910190815291905056fea2646970667358221220842ffaa5301c29786eaecffd5c1edae4416e0a105f2221505d20ff0513143d5664736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ADDROLECREATOR = "addRoleCreator";

    public static final String FUNC_CHECKREACHED = "checkReached";

    public static final String FUNC_CREATECOMPARTMENTID = "createCompartmentID";

    public static final String FUNC_DELIVERMEDICINE = "deliverMedicine";

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

    public static final Event ARRIVEDATPATIENT_EVENT = new Event("ArrivedAtPatient", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event DEPARTURE_EVENT = new Event("Departure", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected Drone(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Drone(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Drone(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Drone(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ArrivedAtPatientEventResponse> getArrivedAtPatientEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ARRIVEDATPATIENT_EVENT, transactionReceipt);
        ArrayList<ArrivedAtPatientEventResponse> responses = new ArrayList<ArrivedAtPatientEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ArrivedAtPatientEventResponse typedResponse = new ArrivedAtPatientEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ArrivedAtPatientEventResponse getArrivedAtPatientEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ARRIVEDATPATIENT_EVENT, log);
        ArrivedAtPatientEventResponse typedResponse = new ArrivedAtPatientEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<ArrivedAtPatientEventResponse> arrivedAtPatientEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getArrivedAtPatientEventFromLog(log));
    }

    public Flowable<ArrivedAtPatientEventResponse> arrivedAtPatientEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ARRIVEDATPATIENT_EVENT));
        return arrivedAtPatientEventFlowable(filter);
    }

    public static List<DepartureEventResponse> getDepartureEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEPARTURE_EVENT, transactionReceipt);
        ArrayList<DepartureEventResponse> responses = new ArrayList<DepartureEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepartureEventResponse typedResponse = new DepartureEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DepartureEventResponse getDepartureEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEPARTURE_EVENT, log);
        DepartureEventResponse typedResponse = new DepartureEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<DepartureEventResponse> departureEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDepartureEventFromLog(log));
    }

    public Flowable<DepartureEventResponse> departureEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPARTURE_EVENT));
        return departureEventFlowable(filter);
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

    public RemoteFunctionCall<Boolean> checkReached() {
        final Function function = new Function(FUNC_CHECKREACHED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> createCompartmentID(String _message) {
        final Function function = new Function(
                FUNC_CREATECOMPARTMENTID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_message)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deliverMedicine() {
        final Function function = new Function(
                FUNC_DELIVERMEDICINE, 
                Arrays.<Type>asList(), 
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

    @Deprecated
    public static Drone load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Drone(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Drone load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Drone(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Drone load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Drone(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Drone load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Drone(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Drone> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _planAddress, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Drone.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Drone> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String _planAddress, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Drone.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Drone> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _planAddress, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Drone.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Drone> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String _planAddress, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _planAddress), 
                new org.web3j.abi.datatypes.Address(160, _owner)));
        return deployRemoteCall(Drone.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class ArrivedAtPatientEventResponse extends BaseEventResponse {
    }

    public static class DepartureEventResponse extends BaseEventResponse {
    }
}
