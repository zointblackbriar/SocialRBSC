package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class LinkedList extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061078e806100206000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806366d003ac1161005b57806366d003ac146100fe5780638f7dcfa31461012957806390b5561d1461013c578063ea9544cf1461014f57600080fd5b8063189a5a17146100825780632d4ede93146100d45780633bc5de30146100e9575b600080fd5b6100b2610090366004610678565b600160208190526000918252604090912080549101546001600160a01b031682565b604080519283526001600160a01b039091166020830152015b60405180910390f35b6100e76100e2366004610678565b610170565b005b6100f1610349565b6040516100cb91906106a8565b600054610111906001600160a01b031681565b6040516001600160a01b0390911681526020016100cb565b600354610111906001600160a01b031681565b6100e761014a3660046106ec565b6104a5565b61016261015d3660046106ec565b610657565b6040519081526020016100cb565b6001600160a01b0381166101df5760405162461bcd60e51b815260206004820152602b60248201527f496e76616c696420616464726573732e20506c6561736520636f72726563742060448201526a746865206164647265737360a81b60648201526084015b60405180910390fd5b6003546001600160a01b039081169082160361023d5760405162461bcd60e51b815260206004820152601b60248201527f43616e6e6f742064656c657465207468652068656164206e6f6465000000000060448201526064016101d6565b6003546001600160a01b031660008181526001602052604090205b60018101546001600160a01b038481169116146102fa5760018101546001600160a01b03166102d35760405162461bcd60e51b815260206004820152602160248201527f43616e6e6f742064656c6574652061206e6f6e2d6578697374656e74206e6f646044820152606560f81b60648201526084016101d6565b6001908101546001600160a01b031660008181526020929092526040909120909150610258565b506001600160a01b039182166000818152600160208190526040808320808301805496881685529184209092018054959096166001600160a01b03199586161790955591815290558154169055565b6003546060906000906001600160a01b03165b6001600160a01b038116156103a0578161037581610705565b6001600160a01b0392831660009081526001602081905260409091200154909350909116905061035c565b60008267ffffffffffffffff8111156103bb576103bb61072c565b6040519080825280602002602001820160405280156103e4578160200160208202803683370190505b506003546001600160a01b03169250905060005b8381101561049d576001600160a01b038316600090815260016020526040902054825183908390811061042d5761042d610742565b6020908102919091018101919091526001600160a01b039384166000908152600191829052604090208054600280548085019091557f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace01550154909216918061049581610705565b9150506103f8565b509392505050565b60408051808201909152818152600060208201526003546001600160a01b031661051b57604080518251602080830191909152808401516001600160a01b039081168385015283518084038501815260609093019093528151910120600380546001600160a01b031916919092161790556105cd565b6003546001600160a01b031660009081526001602052604090205b60018101546001600160a01b03161561056c576001908101546001600160a01b0316600090815260209190915260409020610536565b6040805183516020808301919091528401516001600160a01b03169181019190915260600160408051808303601f19018152919052805160209091012060019190910180546001600160a01b0319166001600160a01b039092169190911790555b8060016000836040516020016105fe9190815181526020918201516001600160a01b03169181019190915260400190565b60408051808303601f1901815291815281516020928301206001600160a01b039081168452838301949094529190910160002083518155920151600190920180546001600160a01b031916929091169190911790555050565b6002818154811061066757600080fd5b600091825260209091200154905081565b60006020828403121561068a57600080fd5b81356001600160a01b03811681146106a157600080fd5b9392505050565b6020808252825182820181905260009190848201906040850190845b818110156106e0578351835292840192918401916001016106c4565b50909695505050505050565b6000602082840312156106fe57600080fd5b5035919050565b60006001820161072557634e487b7160e01b600052601160045260246000fd5b5060010190565b634e487b7160e01b600052604160045260246000fd5b634e487b7160e01b600052603260045260246000fdfea26469706673582212205b63061d8c0e832882bac2c1e5f2328f1bce55f53dba891d58174d152ef2175a64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DELETENODE = "deleteNode";

    public static final String FUNC_GETDATA = "getData";

    public static final String FUNC_HEAD = "head";

    public static final String FUNC_INSERT = "insert";

    public static final String FUNC_NODES = "nodes";

    public static final String FUNC_RECIPIENT = "recipient";

    public static final String FUNC_STATEVARIABLES = "stateVariables";

    @Deprecated
    protected LinkedList(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LinkedList(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LinkedList(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LinkedList(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteNode(String _addressToBeDeleted) {
        final Function function = new Function(
                FUNC_DELETENODE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addressToBeDeleted)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getData() {
        final Function function = new Function(
                FUNC_GETDATA, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> head() {
        final Function function = new Function(FUNC_HEAD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> insert(BigInteger _data) {
        final Function function = new Function(
                FUNC_INSERT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, String>> nodes(String param0) {
        final Function function = new Function(FUNC_NODES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, String>>(function,
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> recipient() {
        final Function function = new Function(FUNC_RECIPIENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> stateVariables(BigInteger param0) {
        final Function function = new Function(FUNC_STATEVARIABLES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static LinkedList load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new LinkedList(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LinkedList load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LinkedList(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LinkedList load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new LinkedList(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LinkedList load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LinkedList(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<LinkedList> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LinkedList.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<LinkedList> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LinkedList.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<LinkedList> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LinkedList.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<LinkedList> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LinkedList.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
