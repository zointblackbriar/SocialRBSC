package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
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
public class SocialAgentDeliberationCycle extends Contract {
    public static final String BINARY = "6080604052600060058190556006556007805460ff191690553480156200002557600080fd5b5060405162001f0138038062001f018339810160408190526200004891620000ce565b60008054336001600160a01b0319918216179091556001805482166001600160a01b0397881617905560098054821695871695909517909455600480548516938616939093179092556002805484169185169190911790556003805490921692169190911790556200014e565b6001600160a01b0381168114620000cb57600080fd5b50565b600080600080600060a08688031215620000e757600080fd5b8551620000f481620000b5565b60208701519095506200010781620000b5565b60408701519094506200011a81620000b5565b60608701519093506200012d81620000b5565b60808701519092506200014081620000b5565b809150509295509295909350565b611da3806200015e6000396000f3fe608060405234801561001057600080fd5b50600436106100ea5760003560e01c80638da5cb5b1161008c578063dc191fab11610066578063dc191fab146101d9578063f2fde38b14610209578063f39d40ba1461021c578063f76909d81461023e57600080fd5b80638da5cb5b14610198578063b98464b3146101b3578063bc31e980146101c657600080fd5b80634601ecf3116100c85780634601ecf3146101405780634e4c2bfe146101605780635fb230d414610173578063715018a61461019057600080fd5b806301c8cbf9146100ef57806301d67406146101045780630cc4e8d814610117575b600080fd5b6101026100fd366004611634565b610251565b005b61010261011236600461168d565b6102b5565b61012a610125366004611634565b61031d565b6040516101379190611737565b60405180910390f35b61015361014e3660046117d8565b610550565b6040516101379190611802565b61010261016e366004611815565b610692565b6007546101809060ff1681565b6040519015158152602001610137565b6101026106c4565b6000546040516001600160a01b039091168152602001610137565b6101026101c136600461185a565b610730565b6101026101d43660046118f2565b6107c7565b6101fc6101e73660046118f2565b600a6020526000908152604090205460ff1681565b6040516101379190611925565b61010261021736600461194d565b61097b565b61022f61022a366004611815565b610a5c565b60405161013793929190611968565b61010261024c3660046119ab565b610c4a565b6004805460405163c47f002760e01b81526001600160a01b039091169163c47f00279161028091859101611802565b600060405180830381600087803b15801561029a57600080fd5b505af11580156102ae573d6000803e3d6000fd5b5050505050565b60025460405163a3bdb54d60e01b81526001600160a01b039091169063a3bdb54d906102e79085908590600401611a97565b600060405180830381600087803b15801561030157600080fd5b505af1158015610315573d6000803e3d6000fd5b505050505050565b6060600b8260405161032f9190611abb565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b82821015610545578382906000526020600020906003020160405180606001604052908160008201805461039090611ad7565b80601f01602080910402602001604051908101604052809291908181526020018280546103bc90611ad7565b80156104095780601f106103de57610100808354040283529160200191610409565b820191906000526020600020905b8154815290600101906020018083116103ec57829003601f168201915b5050505050815260200160018201805461042290611ad7565b80601f016020809104026020016040519081016040528092919081815260200182805461044e90611ad7565b801561049b5780601f106104705761010080835404028352916020019161049b565b820191906000526020600020905b81548152906001019060200180831161047e57829003601f168201915b505050505081526020016002820180546104b490611ad7565b80601f01602080910402602001604051908101604052809291908181526020018280546104e090611ad7565b801561052d5780601f106105025761010080835404028352916020019161052d565b820191906000526020600020905b81548152906001019060200180831161051057829003601f168201915b5050505050815250508152602001906001019061035d565b505050509050919050565b60025460405163d2c97a1760e01b81526001600160a01b038481166004830152606092169063d2c97a1790602401602060405180830381865afa15801561059b573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105bf9190611b11565b82106106125760405162461bcd60e51b815260206004820152601d60248201527f496e74656e74696f6e20696e646578206f7574206f6620626f756e647300000060448201526064015b60405180910390fd5b600254604051634601ecf360e01b81526001600160a01b0385811660048301526024820185905290911690634601ecf390604401600060405180830381865afa158015610663573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261068b9190810190611b2a565b9392505050565b60035460405163934bfab960e01b81526001600160a01b039091169063934bfab9906102e79085908590600401611ba1565b6000546001600160a01b0316331461071e5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65726044820152606401610609565b600080546001600160a01b0319169055565b600b836040516107409190611abb565b90815260408051918290036020908101832060608401835286845283820186905291830184905281546001810183556000928352918190208351805160039094029091019261079292849201906114d6565b5060208281015180516107ab92600185019201906114d6565b50604082015180516103159160028401916020909101906114d6565b600160009054906101000a90046001600160a01b03166001600160a01b0316632618c7f66040518163ffffffff1660e01b8152600401602060405180830381865afa15801561081a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061083e9190611bc3565b600381111561084f5761084f61190f565b156108ab5760405162461bcd60e51b815260206004820152602660248201527f536f6369616c206167656e742073686f756c64206265206e65776c792067656e604482015265195c985d195960d21b6064820152608401610609565b80600a6000600160009054906101000a90046001600160a01b03166001600160a01b0316632618c7f66040518163ffffffff1660e01b8152600401602060405180830381865afa158015610903573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109279190611bc3565b60038111156109385761093861190f565b60038111156109495761094961190f565b81526020810191909152604001600020805460ff191660018360038111156109735761097361190f565b021790555050565b6000546001600160a01b031633146109d55760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65726044820152606401610609565b6001600160a01b038116610a3a5760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b6064820152608401610609565b600080546001600160a01b0319166001600160a01b0392909216919091179055565b8151602081840181018051600b825292820191850191909120919052805482908110610a8757600080fd5b906000526020600020906003020160009150915050806000018054610aab90611ad7565b80601f0160208091040260200160405190810160405280929190818152602001828054610ad790611ad7565b8015610b245780601f10610af957610100808354040283529160200191610b24565b820191906000526020600020905b815481529060010190602001808311610b0757829003601f168201915b505050505090806001018054610b3990611ad7565b80601f0160208091040260200160405190810160405280929190818152602001828054610b6590611ad7565b8015610bb25780601f10610b8757610100808354040283529160200191610bb2565b820191906000526020600020905b815481529060010190602001808311610b9557829003601f168201915b505050505090806002018054610bc790611ad7565b80601f0160208091040260200160405190810160405280929190818152602001828054610bf390611ad7565b8015610c405780601f10610c1557610100808354040283529160200191610c40565b820191906000526020600020905b815481529060010190602001808311610c2357829003601f168201915b5050505050905083565b6000196005541015610c6c5760058054906000610c6683611be0565b91905055505b6000196006541015610c8e5760068054906000610c8883611be0565b91905055505b60606000600160009054906101000a90046001600160a01b03166001600160a01b0316632618c7f66040518163ffffffff1660e01b8152600401602060405180830381865afa158015610ce5573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610d099190611bc3565b6003811115610d1a57610d1a61190f565b0361115857600954604051639079abd360e01b81526001600160a01b0390911690639079abd390610d4f908a90600401611802565b600060405180830381600087803b158015610d6957600080fd5b505af1158015610d7d573d6000803e3d6000fd5b505060095460055460405163d85fcbd560e01b81526001600160a01b03909216935063d85fcbd59250610db69160040190815260200190565b600060405180830381600087803b158015610dd057600080fd5b505af1158015610de4573d6000803e3d6000fd5b505050506008600460009054906101000a90046001600160a01b03166001600160a01b03166319b96c3f6040518163ffffffff1660e01b8152600401600060405180830381865afa158015610e3d573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052610e659190810190611b2a565b815460018101835560009283526020928390208251610e8a94919092019201906114d6565b506001600a6000600160009054906101000a90046001600160a01b03166001600160a01b0316632618c7f66040518163ffffffff1660e01b8152600401602060405180830381865afa158015610ee4573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610f089190611bc3565b6003811115610f1957610f1961190f565b6003811115610f2a57610f2a61190f565b81526020810191909152604001600020805460ff19166001836003811115610f5457610f5461190f565b021790555060025460405163a3bdb54d60e01b81526001600160a01b039091169063a3bdb54d90610f8b9089908890600401611a97565b600060405180830381600087803b158015610fa557600080fd5b505af1158015610fb9573d6000803e3d6000fd5b5050600254604051634601ecf360e01b81526001600160a01b038a81166004830152602482018a90529091169250634601ecf39150604401600060405180830381865afa15801561100e573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526110369190810190611b2a565b905060008151116110895760405162461bcd60e51b815260206004820152601b60248201527f74686572652073686f756c64206265206120696e74656e74696f6e00000000006044820152606401610609565b60035460065460405163934bfab960e01b81526001600160a01b039092169163934bfab9916110bd91869190600401611ba1565b600060405180830381600087803b1580156110d757600080fd5b505af11580156110eb573d6000803e3d6000fd5b50505050600160009054906101000a90046001600160a01b03166001600160a01b03166314007c6d6040518163ffffffff1660e01b8152600401600060405180830381600087803b15801561113f57600080fd5b505af1158015611153573d6000803e3d6000fd5b505050505b60018060009054906101000a90046001600160a01b03166001600160a01b0316632618c7f66040518163ffffffff1660e01b8152600401602060405180830381865afa1580156111ac573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906111d09190611bc3565b60038111156111e1576111e161190f565b0361133c576003546040516325c6e4f560e21b81526001600160a01b039091169063971b93d490611216908590600401611802565b602060405180830381865afa158015611233573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906112579190611c07565b156112c857600254604051630aaefe7360e21b81526001600160a01b0390911690632abbf9cc906112919060089087908b90600401611c29565b600060405180830381600087803b1580156112ab57600080fd5b505af11580156112bf573d6000803e3d6000fd5b505050506112d3565b6007805460ff191690555b600160009054906101000a90046001600160a01b03166001600160a01b03166314007c6d6040518163ffffffff1660e01b8152600401600060405180830381600087803b15801561132357600080fd5b505af1158015611337573d6000803e3d6000fd5b505050505b6001546040805163130c63fb60e11b815290516002926001600160a01b031691632618c7f69160048083019260209291908290030181865afa158015611386573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906113aa9190611bc3565b60038111156113bb576113bb61190f565b036114bf576002600a6000600160009054906101000a90046001600160a01b03166001600160a01b0316632618c7f66040518163ffffffff1660e01b8152600401602060405180830381865afa158015611419573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061143d9190611bc3565b600381111561144e5761144e61190f565b600381111561145f5761145f61190f565b81526020810191909152604001600020805460ff191660018360038111156114895761148961190f565b02179055506114bf88886040518060400160405280600e81526020016d11dbd85b0810dbdb5c1b195d195960921b815250610730565b50506007805460ff19166001179055505050505050565b8280546114e290611ad7565b90600052602060002090601f016020900481019282611504576000855561154a565b82601f1061151d57805160ff191683800117855561154a565b8280016001018555821561154a579182015b8281111561154a57825182559160200191906001019061152f565b5061155692915061155a565b5090565b5b80821115611556576000815560010161155b565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156115ae576115ae61156f565b604052919050565b600067ffffffffffffffff8211156115d0576115d061156f565b50601f01601f191660200190565b600082601f8301126115ef57600080fd5b81356116026115fd826115b6565b611585565b81815284602083860101111561161757600080fd5b816020850160208301376000918101602001919091529392505050565b60006020828403121561164657600080fd5b813567ffffffffffffffff81111561165d57600080fd5b611669848285016115de565b949350505050565b80356001600160a01b038116811461168857600080fd5b919050565b600080604083850312156116a057600080fd5b6116a983611671565b9150602083013567ffffffffffffffff8111156116c557600080fd5b6116d1858286016115de565b9150509250929050565b60005b838110156116f65781810151838201526020016116de565b83811115611705576000848401525b50505050565b600081518084526117238160208601602086016116db565b601f01601f19169290920160200192915050565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b838110156117ca57603f198984030185528151606081518186526117848287018261170b565b915050888201518582038a87015261179c828261170b565b915050878201519150848103888601526117b6818361170b565b96890196945050509086019060010161175e565b509098975050505050505050565b600080604083850312156117eb57600080fd5b6117f483611671565b946020939093013593505050565b60208152600061068b602083018461170b565b6000806040838503121561182857600080fd5b823567ffffffffffffffff81111561183f57600080fd5b61184b858286016115de565b95602094909401359450505050565b60008060006060848603121561186f57600080fd5b833567ffffffffffffffff8082111561188757600080fd5b611893878388016115de565b945060208601359150808211156118a957600080fd5b6118b5878388016115de565b935060408601359150808211156118cb57600080fd5b506118d8868287016115de565b9150509250925092565b600481106118ef57600080fd5b50565b60006020828403121561190457600080fd5b813561068b816118e2565b634e487b7160e01b600052602160045260246000fd5b602081016004831061194757634e487b7160e01b600052602160045260246000fd5b91905290565b60006020828403121561195f57600080fd5b61068b82611671565b60608152600061197b606083018661170b565b828103602084015261198d818661170b565b905082810360408401526119a1818561170b565b9695505050505050565b600080600080600080600060e0888a0312156119c657600080fd5b873567ffffffffffffffff808211156119de57600080fd5b6119ea8b838c016115de565b985060208a0135915080821115611a0057600080fd5b611a0c8b838c016115de565b9750611a1a60408b01611671565b965060608a0135955060808a0135915080821115611a3757600080fd5b611a438b838c016115de565b945060a08a0135915080821115611a5957600080fd5b611a658b838c016115de565b935060c08a0135915080821115611a7b57600080fd5b50611a888a828b016115de565b91505092959891949750929550565b6001600160a01b03831681526040602082018190526000906116699083018461170b565b60008251611acd8184602087016116db565b9190910192915050565b600181811c90821680611aeb57607f821691505b602082108103611b0b57634e487b7160e01b600052602260045260246000fd5b50919050565b600060208284031215611b2357600080fd5b5051919050565b600060208284031215611b3c57600080fd5b815167ffffffffffffffff811115611b5357600080fd5b8201601f81018413611b6457600080fd5b8051611b726115fd826115b6565b818152856020838501011115611b8757600080fd5b611b988260208301602086016116db565b95945050505050565b604081526000611bb4604083018561170b565b90508260208301529392505050565b600060208284031215611bd557600080fd5b815161068b816118e2565b600060018201611c0057634e487b7160e01b600052601160045260246000fd5b5060010190565b600060208284031215611c1957600080fd5b8151801515811461068b57600080fd5b6000608082016080835280865480835260a08501915060a08160051b860101925060008881526020808220825b84811015611d0c57888703609f1901865281548490600181811c9080831680611c8057607f831692505b8783108103611c9d57634e487b7160e01b89526022600452602489fd5b828c5260208c01818015611cb85760018114611cc957611cf3565b60ff19861682528982019650611cf3565b6000898152602090208b5b86811015611ced57815484820152908501908b01611cd4565b83019750505b50949b5050988601989490940193505050600101611c56565b50508685038188015250505050611d23818661170b565b90508281036040840152611d5581601181527022bc32b1baba329024b73a32b73a34b7b760791b602082015260400190565b91505061166960608301846001600160a01b0316905256fea26469706673582212207a55188231d124cd4199f6f64776f48489fc4080072e1fb5402789d94657391e64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDMESSAGE = "addMessage";

    public static final String FUNC_ALLOCATESOCIALGOALS = "allocateSocialGoals";

    public static final String FUNC_BEHAVIORMAPPING = "behaviorMapping";

    public static final String FUNC_COMMUNICATIONROUTING = "communicationRouting";

    public static final String FUNC_DELIBERATIONCYCLE = "deliberationCycle";

    public static final String FUNC_DELIBERATIONCYCLERESULT = "deliberationCycleResult";

    public static final String FUNC_GETAGENTINTENTIONATINDEX = "getAgentIntentionAtIndex";

    public static final String FUNC_GETMESSAGE = "getMessage";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETBELIEFSBEFOREDELIBERATIONCYCLE = "setBeliefsBeforeDeliberationCycle";

    public static final String FUNC_SETINTENTIONSBEFOREDELIBERATIONCYCLE = "setIntentionsBeforeDeliberationCycle";

    public static final String FUNC_SETSOCIALAGENTNAMEFORINTENTIONS = "setSocialAgentNameForIntentions";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    @Deprecated
    protected SocialAgentDeliberationCycle(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialAgentDeliberationCycle(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialAgentDeliberationCycle(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialAgentDeliberationCycle(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addMessage(String _senderName,
            String _receiverName, String _messageType) {
        final Function function = new Function(
                FUNC_ADDMESSAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_senderName), 
                new org.web3j.abi.datatypes.Utf8String(_receiverName), 
                new org.web3j.abi.datatypes.Utf8String(_messageType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> allocateSocialGoals(BigInteger _socialGoal) {
        final Function function = new Function(
                FUNC_ALLOCATESOCIALGOALS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_socialGoal)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> behaviorMapping(BigInteger param0) {
        final Function function = new Function(FUNC_BEHAVIORMAPPING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple3<String, String, String>> communicationRouting(String param0,
            BigInteger param1) {
        final Function function = new Function(FUNC_COMMUNICATIONROUTING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, String>>(function,
                new Callable<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> deliberationCycle(String _nameOfAgent,
            String _nameOfSociety, String _addressOfIntentions, BigInteger _indexOfAgentIntention,
            String _intentionName, String _metamodelName, String _socialAgentBeliefName) {
        final Function function = new Function(
                FUNC_DELIBERATIONCYCLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nameOfAgent), 
                new org.web3j.abi.datatypes.Utf8String(_nameOfSociety), 
                new org.web3j.abi.datatypes.Address(160, _addressOfIntentions), 
                new org.web3j.abi.datatypes.generated.Uint256(_indexOfAgentIntention), 
                new org.web3j.abi.datatypes.Utf8String(_intentionName), 
                new org.web3j.abi.datatypes.Utf8String(_metamodelName), 
                new org.web3j.abi.datatypes.Utf8String(_socialAgentBeliefName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> deliberationCycleResult() {
        final Function function = new Function(FUNC_DELIBERATIONCYCLERESULT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> getAgentIntentionAtIndex(String _addressOfIntentions,
            BigInteger _index) {
        final Function function = new Function(FUNC_GETAGENTINTENTIONATINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addressOfIntentions), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getMessage(String _senderName) {
        final Function function = new Function(FUNC_GETMESSAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_senderName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Message>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setBeliefsBeforeDeliberationCycle(
            String _beliefName, BigInteger _value) {
        final Function function = new Function(
                FUNC_SETBELIEFSBEFOREDELIBERATIONCYCLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_beliefName), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setIntentionsBeforeDeliberationCycle(
            String assignmentIntentionStackAddress, String _intention) {
        final Function function = new Function(
                FUNC_SETINTENTIONSBEFOREDELIBERATIONCYCLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, assignmentIntentionStackAddress), 
                new org.web3j.abi.datatypes.Utf8String(_intention)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setSocialAgentNameForIntentions(
            String _socialAgentName) {
        final Function function = new Function(
                FUNC_SETSOCIALAGENTNAMEFORINTENTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_socialAgentName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SocialAgentDeliberationCycle load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentDeliberationCycle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialAgentDeliberationCycle load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentDeliberationCycle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialAgentDeliberationCycle load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SocialAgentDeliberationCycle(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialAgentDeliberationCycle load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialAgentDeliberationCycle(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialAgentDeliberationCycle> deploy(Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider,
            String _socialAgentStateMachineAddress, String _society, String _socialAgentAddress,
            String _socialAgentIntentionAddress, String _socialAgentBeliefAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentStateMachineAddress), 
                new org.web3j.abi.datatypes.Address(160, _society), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentAddress), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentIntentionAddress), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentBeliefAddress)));
        return deployRemoteCall(SocialAgentDeliberationCycle.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<SocialAgentDeliberationCycle> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _socialAgentStateMachineAddress, String _society, String _socialAgentAddress,
            String _socialAgentIntentionAddress, String _socialAgentBeliefAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentStateMachineAddress), 
                new org.web3j.abi.datatypes.Address(160, _society), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentAddress), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentIntentionAddress), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentBeliefAddress)));
        return deployRemoteCall(SocialAgentDeliberationCycle.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SocialAgentDeliberationCycle> deploy(Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit,
            String _socialAgentStateMachineAddress, String _society, String _socialAgentAddress,
            String _socialAgentIntentionAddress, String _socialAgentBeliefAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentStateMachineAddress), 
                new org.web3j.abi.datatypes.Address(160, _society), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentAddress), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentIntentionAddress), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentBeliefAddress)));
        return deployRemoteCall(SocialAgentDeliberationCycle.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SocialAgentDeliberationCycle> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _socialAgentStateMachineAddress, String _society, String _socialAgentAddress,
            String _socialAgentIntentionAddress, String _socialAgentBeliefAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentStateMachineAddress), 
                new org.web3j.abi.datatypes.Address(160, _society), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentAddress), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentIntentionAddress), 
                new org.web3j.abi.datatypes.Address(160, _socialAgentBeliefAddress)));
        return deployRemoteCall(SocialAgentDeliberationCycle.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class Message extends DynamicStruct {
        public String sender;

        public String receiver;

        public String messageType;

        public Message(String sender, String receiver, String messageType) {
            super(new org.web3j.abi.datatypes.Utf8String(sender), 
                    new org.web3j.abi.datatypes.Utf8String(receiver), 
                    new org.web3j.abi.datatypes.Utf8String(messageType));
            this.sender = sender;
            this.receiver = receiver;
            this.messageType = messageType;
        }

        public Message(Utf8String sender, Utf8String receiver, Utf8String messageType) {
            super(sender, receiver, messageType);
            this.sender = sender.getValue();
            this.receiver = receiver.getValue();
            this.messageType = messageType.getValue();
        }
    }
}
