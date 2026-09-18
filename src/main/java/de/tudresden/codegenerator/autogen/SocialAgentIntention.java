package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
public class SocialAgentIntention extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50604051610f3a380380610f3a83398101604081905261002f91610081565b60028054600180546001600160a01b0319166001600160a01b039586161790556001600160a81b031916919092161790556100b4565b80516001600160a01b038116811461007c57600080fd5b919050565b6000806040838503121561009457600080fd5b61009d83610065565b91506100ab60208401610065565b90509250929050565b610e77806100c36000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c806389bccf701161006657806389bccf7014610109578063a3bdb54d14610111578063d2c97a1714610124578063df19f18b1461015b578063f03747df1461016e57600080fd5b80630b93381b14610098578063264b3820146100c15780632abbf9cc146100e15780634601ecf3146100f6575b600080fd5b6002546100ac90600160a01b900460ff1681565b60405190151581526020015b60405180910390f35b6100d46100cf36600461098f565b61018e565b6040516100b89190610a19565b6100f46100ef366004610a4f565b61028c565b005b6100d4610104366004610b6a565b610466565b6100d4610595565b6100f461011f366004610b94565b610623565b61014d610132366004610be2565b6001600160a01b031660009081526020819052604090205490565b6040519081526020016100b8565b6100d4610169366004610b6a565b610662565b61018161017c366004610be2565b61069a565b6040516100b89190610bfd565b600254604051633a96fdd760e01b81526060916001600160a01b031690633a96fdd7906101bf908590600401610c5f565b602060405180830381865afa1580156101dc573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102009190610c98565b1561025c5760405162461bcd60e51b815260206004820152602160248201527f63757272656e74206465736972652073686f756c64206e6f74206265204e6f6e6044820152606560f81b60648201526084015b60405180910390fd5b50506040805180820190915260148152731858dd125b9d195b9d1a5bdb881a5b9d9bdad95960621b602082015290565b6001600160a01b0381166000908152602081905260409020546102e45760405162461bcd60e51b815260206004820152601060248201526f139bc81a5b9d195b9d1a5bdb881cd95d60821b6044820152606401610253565b6002805460ff60a01b1916600160a01b1790556001600160a01b0381166000908152602081905260409020805461031d90600190610cd0565b8154811061032d5761032d610ce7565b90600052602060002001600390805461034590610cfd565b610350929190610787565b5060005b845181101561041a5784818151811061036f5761036f610ce7565b602002602001015180519060200120600360405161038d9190610d37565b604051809103902003610408576001805460405163c370214160e01b81526001600160a01b039091169163c3702141916103cb918891600401610dd2565b600060405180830381600087803b1580156103e557600080fd5b505af11580156103f9573d6000803e3d6000fd5b505050506104068361018e565b505b8061041281610e12565b915050610354565b506001600160a01b038116600090815260208190526040902080548061044257610442610e2b565b60019003818190600052602060002001600061045e9190610812565b905550505050565b6001600160a01b03821660009081526020819052604090205460609082106104d05760405162461bcd60e51b815260206004820152601d60248201527f496e74656e74696f6e20696e646578206f7574206f6620626f756e64730000006044820152606401610253565b6001600160a01b03831660009081526020819052604090208054839081106104fa576104fa610ce7565b90600052602060002001805461050f90610cfd565b80601f016020809104026020016040519081016040528092919081815260200182805461053b90610cfd565b80156105885780601f1061055d57610100808354040283529160200191610588565b820191906000526020600020905b81548152906001019060200180831161056b57829003601f168201915b5050505050905092915050565b600380546105a290610cfd565b80601f01602080910402602001604051908101604052809291908181526020018280546105ce90610cfd565b801561061b5780601f106105f05761010080835404028352916020019161061b565b820191906000526020600020905b8154815290600101906020018083116105fe57829003601f168201915b505050505081565b6001600160a01b0382166000908152602081815260408220805460018101825590835291819020835161065d93919091019184019061084f565b505050565b6000602052816000526040600020818154811061067e57600080fd5b906000526020600020016000915091505080546105a290610cfd565b6001600160a01b038116600090815260208181526040808320805482518185028101850190935280835260609492939192909184015b8282101561077c5783829060005260206000200180546106ef90610cfd565b80601f016020809104026020016040519081016040528092919081815260200182805461071b90610cfd565b80156107685780601f1061073d57610100808354040283529160200191610768565b820191906000526020600020905b81548152906001019060200180831161074b57829003601f168201915b5050505050815260200190600101906106d0565b505050509050919050565b82805461079390610cfd565b90600052602060002090601f0160209004810192826107b55760008555610802565b82601f106107c65780548555610802565b8280016001018555821561080257600052602060002091601f016020900482015b828111156108025782548255916001019190600101906107e7565b5061080e9291506108c3565b5090565b50805461081e90610cfd565b6000825580601f1061082e575050565b601f01602090049060005260206000209081019061084c91906108c3565b50565b82805461085b90610cfd565b90600052602060002090601f01602090048101928261087d5760008555610802565b82601f1061089657805160ff1916838001178555610802565b82800160010185558215610802579182015b828111156108025782518255916020019190600101906108a8565b5b8082111561080e57600081556001016108c4565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715610917576109176108d8565b604052919050565b600082601f83011261093057600080fd5b813567ffffffffffffffff81111561094a5761094a6108d8565b61095d601f8201601f19166020016108ee565b81815284602083860101111561097257600080fd5b816020850160208301376000918101602001919091529392505050565b6000602082840312156109a157600080fd5b813567ffffffffffffffff8111156109b857600080fd5b6109c48482850161091f565b949350505050565b6000815180845260005b818110156109f2576020818501810151868301820152016109d6565b81811115610a04576000602083870101525b50601f01601f19169290920160200192915050565b602081526000610a2c60208301846109cc565b9392505050565b80356001600160a01b0381168114610a4a57600080fd5b919050565b60008060008060808587031215610a6557600080fd5b843567ffffffffffffffff80821115610a7d57600080fd5b818701915087601f830112610a9157600080fd5b8135602082821115610aa557610aa56108d8565b8160051b610ab48282016108ee565b928352848101820192828101908c851115610ace57600080fd5b83870192505b84831015610b0a57823586811115610aec5760008081fd5b610afa8e86838b010161091f565b8352509183019190830190610ad4565b9950505088013592505080821115610b2157600080fd5b610b2d8883890161091f565b94506040870135915080821115610b4357600080fd5b50610b508782880161091f565b925050610b5f60608601610a33565b905092959194509250565b60008060408385031215610b7d57600080fd5b610b8683610a33565b946020939093013593505050565b60008060408385031215610ba757600080fd5b610bb083610a33565b9150602083013567ffffffffffffffff811115610bcc57600080fd5b610bd88582860161091f565b9150509250929050565b600060208284031215610bf457600080fd5b610a2c82610a33565b6000602080830181845280855180835260408601915060408160051b870101925083870160005b82811015610c5257603f19888603018452610c408583516109cc565b94509285019290850190600101610c24565b5092979650505050505050565b604081526000610c7260408301846109cc565b828103602084015260048152634e6f6e6560e01b60208201526040810191505092915050565b600060208284031215610caa57600080fd5b81518015158114610a2c57600080fd5b634e487b7160e01b600052601160045260246000fd5b600082821015610ce257610ce2610cba565b500390565b634e487b7160e01b600052603260045260246000fd5b600181811c90821680610d1157607f821691505b602082108103610d3157634e487b7160e01b600052602260045260246000fd5b50919050565b600080835481600182811c915080831680610d5357607f831692505b60208084108203610d7257634e487b7160e01b86526022600452602486fd5b818015610d865760018114610d9757610dc4565b60ff19861689528489019650610dc4565b60008a81526020902060005b86811015610dbc5781548b820152908501908301610da3565b505084890196505b509498975050505050505050565b604081526000610de560408301856109cc565b905060028310610e0557634e487b7160e01b600052602160045260246000fd5b8260208301529392505050565b600060018201610e2457610e24610cba565b5060010190565b634e487b7160e01b600052603160045260246000fdfea2646970667358221220f4911022d1a77de194dd2a6923d2e5e1223b22b94199a372f0976bf686aec3dd64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTINTENTION = "actIntention";

    public static final String FUNC_AGENTINTENTIONSSTACK = "agentIntentionsStack";

    public static final String FUNC_EXECUTEINTENTION = "executeIntention";

    public static final String FUNC_GETAGENTINTENTIONATINDEX = "getAgentIntentionAtIndex";

    public static final String FUNC_GETAGENTINTENTIONS = "getAgentIntentions";

    public static final String FUNC_GETINTENTIONSTACKSIZE = "getIntentionStackSize";

    public static final String FUNC_PUSHINTENTION = "pushIntention";

    public static final String FUNC_SUCCESS = "success";

    public static final String FUNC_TOPINTENTION = "topIntention";

    @Deprecated
    protected SocialAgentIntention(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialAgentIntention(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialAgentIntention(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialAgentIntention(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> actIntention(String _currentDesire) {
        final Function function = new Function(FUNC_ACTINTENTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_currentDesire)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> agentIntentionsStack(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_AGENTINTENTIONSSTACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> executeIntention(List<String> _socialAgentList,
            String _metamodelNameDesire, String _currentDesire, String addressOfIntention) {
        final Function function = new Function(
                FUNC_EXECUTEINTENTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_socialAgentList, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.Utf8String(_metamodelNameDesire), 
                new org.web3j.abi.datatypes.Utf8String(_currentDesire), 
                new org.web3j.abi.datatypes.Address(160, addressOfIntention)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getAgentIntentionAtIndex(String _intentionAssignmentAddress,
            BigInteger _index) {
        final Function function = new Function(FUNC_GETAGENTINTENTIONATINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _intentionAssignmentAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getAgentIntentions(String _intentionAssignmentAddress) {
        final Function function = new Function(FUNC_GETAGENTINTENTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _intentionAssignmentAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
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

    public RemoteFunctionCall<BigInteger> getIntentionStackSize(String _agentAddress) {
        final Function function = new Function(FUNC_GETINTENTIONSTACKSIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _agentAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pushIntention(String _intentionAssignmentAddress,
            String _intention) {
        final Function function = new Function(
                FUNC_PUSHINTENTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _intentionAssignmentAddress), 
                new org.web3j.abi.datatypes.Utf8String(_intention)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> success() {
        final Function function = new Function(FUNC_SUCCESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> topIntention() {
        final Function function = new Function(FUNC_TOPINTENTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static SocialAgentIntention load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentIntention(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialAgentIntention load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentIntention(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialAgentIntention load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SocialAgentIntention(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialAgentIntention load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialAgentIntention(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialAgentIntention> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _socialAgentDesireAddress,
            String utilsAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentDesireAddress), 
                new org.web3j.abi.datatypes.Address(160, utilsAddress)));
        return deployRemoteCall(SocialAgentIntention.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<SocialAgentIntention> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _socialAgentDesireAddress, String utilsAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentDesireAddress), 
                new org.web3j.abi.datatypes.Address(160, utilsAddress)));
        return deployRemoteCall(SocialAgentIntention.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SocialAgentIntention> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _socialAgentDesireAddress,
            String utilsAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentDesireAddress), 
                new org.web3j.abi.datatypes.Address(160, utilsAddress)));
        return deployRemoteCall(SocialAgentIntention.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SocialAgentIntention> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _socialAgentDesireAddress, String utilsAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentDesireAddress), 
                new org.web3j.abi.datatypes.Address(160, utilsAddress)));
        return deployRemoteCall(SocialAgentIntention.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
}
