/**
 * SocialAgentBelief.java
 *
 * TODO: Add a file description.
 *
 * Auto-added header on 2026-02-03
 */

package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class SocialAgentBelief extends Contract {
    public static final String BINARY = "60806040526003805460ff60a01b1916905534801561001d57600080fd5b50600380546001600160a01b03191633179055610d778061003f6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c8063a68a6ccf11610071578063a68a6ccf14610168578063e57a15f61461017b578063eb48a7c01461018d578063edf33da8146101a0578063f68db3e2146101b4578063fc2f17f8146101d457600080fd5b80633a96fdd7146100b95780634739cdc6146100e1578063640f8b5c1461010c57806374c444141461012d578063934bfab914610140578063971b93d414610155575b600080fd5b6100cc6100c7366004610ae6565b6101e7565b60405190151581526020015b60405180910390f35b6000546100f4906001600160a01b031681565b6040516001600160a01b0390911681526020016100d8565b61011f61011a366004610b4a565b610240565b6040516100d8929190610bdf565b6100cc61013b366004610b4a565b6102ef565b61015361014e366004610c01565b61057d565b005b6100cc610163366004610b4a565b61061e565b6003546100f4906001600160a01b031681565b6002545b6040519081526020016100d8565b61015361019b366004610c01565b6106d7565b6003546100cc90600160a01b900460ff1681565b6101c76101c2366004610c46565b61081b565b6040516100d89190610c5f565b61017f6101e2366004610b4a565b6108c7565b6000816040516020016101fa9190610c79565b60405160208183030381529060405280519060200120836040516020016102219190610c79565b6040516020818303038152906040528051906020012014905092915050565b805160208183018101805160018252928201919093012091528054819061026690610c95565b80601f016020809104026020016040519081016040528092919081815260200182805461029290610c95565b80156102df5780601f106102b4576101008083540402835291602001916102df565b820191906000526020600020905b8154815290600101906020018083116102c257829003601f168201915b5050505050908060010154905082565b60006103cf6001836040516103049190610c79565b908152604051908190036020019020805461031e90610c95565b80601f016020809104026020016040519081016040528092919081815260200182805461034a90610c95565b80156103975780601f1061036c57610100808354040283529160200191610397565b820191906000526020600020905b81548152906001019060200180831161037a57829003601f168201915b50505050506040518060400160405280601581526020017410995b1a595988191bd95cc81b9bdd08195e1a5cdd605a1b8152506101e7565b156103d957600080fd5b6001826040516103e99190610c79565b908152604051908190036020019020600061040482826108f2565b6001820160009055505060005b600254811015610574576104ca6002828154811061043157610431610ccf565b90600052602060002001805461044690610c95565b80601f016020809104026020016040519081016040528092919081815260200182805461047290610c95565b80156104bf5780601f10610494576101008083540402835291602001916104bf565b820191906000526020600020905b8154815290600101906020018083116104a257829003601f168201915b5050505050846101e7565b1561056257600280546104df90600190610cfb565b815481106104ef576104ef610ccf565b906000526020600020016002828154811061050c5761050c610ccf565b9060005260206000200190805461052290610c95565b61052d92919061092f565b50600280548061053f5761053f610d12565b60019003818190600052602060002001600061055b91906108f2565b9055610574565b8061056c81610d28565b915050610411565b50600192915050565b60408051808201825283815260208101839052905181906001906105a2908690610c79565b908152602001604051809103902060008201518160000190805190602001906105cc9291906109ba565b506020918201516001918201556002805491820181556000528451610618927f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace909201918601906109ba565b50505050565b60006106d16001836040516106339190610c79565b908152604051908190036020019020805461064d90610c95565b80601f016020809104026020016040519081016040528092919081815260200182805461067990610c95565b80156106c65780601f1061069b576101008083540402835291602001916106c6565b820191906000526020600020905b8154815290600101906020018083116106a957829003601f168201915b5050505050836101e7565b92915050565b6107976001836040516106ea9190610c79565b908152604051908190036020019020805461070490610c95565b80601f016020809104026020016040519081016040528092919081815260200182805461073090610c95565b801561077d5780601f106107525761010080835404028352916020019161077d565b820191906000526020600020905b81548152906001019060200180831161076057829003601f168201915b5050505050604051806020016040528060008152506101e7565b156107e05760405162461bcd60e51b815260206004820152601560248201527410995b1a595988191bd95cc81b9bdd08195e1a5cdd605a1b604482015260640160405180910390fd5b806001836040516107f19190610c79565b9081526040519081900360200190206001015550506003805460ff60a01b1916600160a01b179055565b6002818154811061082b57600080fd5b90600052602060002001600091509050805461084690610c95565b80601f016020809104026020016040519081016040528092919081815260200182805461087290610c95565b80156108bf5780601f10610894576101008083540402835291602001916108bf565b820191906000526020600020905b8154815290600101906020018083116108a257829003601f168201915b505050505081565b60006001826040516108d99190610c79565b9081526020016040518091039020600101549050919050565b5080546108fe90610c95565b6000825580601f1061090e575050565b601f01602090049060005260206000209081019061092c9190610a2e565b50565b82805461093b90610c95565b90600052602060002090601f01602090048101928261095d57600085556109aa565b82601f1061096e57805485556109aa565b828001600101855582156109aa57600052602060002091601f016020900482015b828111156109aa57825482559160010191906001019061098f565b506109b6929150610a2e565b5090565b8280546109c690610c95565b90600052602060002090601f0160209004810192826109e857600085556109aa565b82601f10610a0157805160ff19168380011785556109aa565b828001600101855582156109aa579182015b828111156109aa578251825591602001919060010190610a13565b5b808211156109b65760008155600101610a2f565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610a6a57600080fd5b813567ffffffffffffffff80821115610a8557610a85610a43565b604051601f8301601f19908116603f01168101908282118183101715610aad57610aad610a43565b81604052838152866020858801011115610ac657600080fd5b836020870160208301376000602085830101528094505050505092915050565b60008060408385031215610af957600080fd5b823567ffffffffffffffff80821115610b1157600080fd5b610b1d86838701610a59565b93506020850135915080821115610b3357600080fd5b50610b4085828601610a59565b9150509250929050565b600060208284031215610b5c57600080fd5b813567ffffffffffffffff811115610b7357600080fd5b610b7f84828501610a59565b949350505050565b60005b83811015610ba2578181015183820152602001610b8a565b838111156106185750506000910152565b60008151808452610bcb816020860160208601610b87565b601f01601f19169290920160200192915050565b604081526000610bf26040830185610bb3565b90508260208301529392505050565b60008060408385031215610c1457600080fd5b823567ffffffffffffffff811115610c2b57600080fd5b610c3785828601610a59565b95602094909401359450505050565b600060208284031215610c5857600080fd5b5035919050565b602081526000610c726020830184610bb3565b9392505050565b60008251610c8b818460208701610b87565b9190910192915050565b600181811c90821680610ca957607f821691505b602082108103610cc957634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b600082821015610d0d57610d0d610ce5565b500390565b634e487b7160e01b600052603160045260246000fd5b600060018201610d3a57610d3a610ce5565b506001019056fea2646970667358221220fb34205e37141d122e13685816bf29845d96caed366a3489a334d6a4d72f2c9264736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDBELIEF = "addBelief";

    public static final String FUNC_BELIEFNAMES = "beliefNames";

    public static final String FUNC_BELIEFUPDATED = "beliefUpdated";

    public static final String FUNC_BELIEFOWNER = "beliefowner";

    public static final String FUNC_BELIEFS = "beliefs";

    public static final String FUNC_CAPABILITY = "capability";

    public static final String FUNC_COMPARE = "compare";

    public static final String FUNC_GETBELIEF = "getBelief";

    public static final String FUNC_GETBELIEFNAMESLENGTH = "getBeliefNamesLength";

    public static final String FUNC_HASBELIEF = "hasBelief";

    public static final String FUNC_REMOVEBELIEF = "removeBelief";

    public static final String FUNC_UPDATEBELIEF = "updateBelief";

    @Deprecated
    protected SocialAgentBelief(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialAgentBelief(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialAgentBelief(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialAgentBelief(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addBelief(String _name, BigInteger _value) {
        final Function function = new Function(
                FUNC_ADDBELIEF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> beliefNames(BigInteger param0) {
        final Function function = new Function(FUNC_BELIEFNAMES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> beliefUpdated() {
        final Function function = new Function(FUNC_BELIEFUPDATED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> beliefowner() {
        final Function function = new Function(FUNC_BELIEFOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> beliefs(String param0) {
        final Function function = new Function(FUNC_BELIEFS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> capability() {
        final Function function = new Function(FUNC_CAPABILITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> compare(String _str1, String _str2) {
        final Function function = new Function(FUNC_COMPARE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_str1), 
                new org.web3j.abi.datatypes.Utf8String(_str2)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> getBelief(String _name) {
        final Function function = new Function(FUNC_GETBELIEF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getBeliefNamesLength() {
        final Function function = new Function(FUNC_GETBELIEFNAMESLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> hasBelief(String _name) {
        final Function function = new Function(FUNC_HASBELIEF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeBelief(String _name) {
        final Function function = new Function(
                FUNC_REMOVEBELIEF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateBelief(String _name, BigInteger _value) {
        final Function function = new Function(
                FUNC_UPDATEBELIEF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SocialAgentBelief load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentBelief(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialAgentBelief load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentBelief(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialAgentBelief load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SocialAgentBelief(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialAgentBelief load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialAgentBelief(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialAgentBelief> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialAgentBelief.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocialAgentBelief> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialAgentBelief.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialAgentBelief> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialAgentBelief.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialAgentBelief> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialAgentBelief.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
