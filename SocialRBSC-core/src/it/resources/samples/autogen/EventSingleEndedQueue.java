/**
 * EventSingleEndedQueue.java
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
import org.web3j.abi.TypeReference;
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
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class EventSingleEndedQueue extends Contract {
    public static final String BINARY = "60806040526001600255600060035534801561001a57600080fd5b506107b68061002a6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c8063694a173911610071578063694a1739146101325780636c43f6611461013a57806382ef004a14610143578063957908d114610156578063b3eaa3d71461015e578063ddf0b0091461016657600080fd5b806308076601146100b95780631ffc735d146100d75780632823c9f2146100ec57806344281692146100ff578063575ec03b1461011657806369462c1f14610129575b600080fd5b6100c1610186565b6040516100ce91906105eb565b60405180910390f35b6100ea6100e5366004610640565b610214565b005b6100ea6100fa36600461066f565b610240565b61010860025481565b6040519081526020016100ce565b6100c161012436600461066f565b61027e565b61010860035481565b6100c1610327565b61010860055481565b6100c1610151366004610640565b610334565b61010861034d565b6100c1610399565b610108610174366004610640565b60006020819052908152604090205481565b6006805461019390610720565b80601f01602080910402602001604051908101604052809291908181526020018280546101bf90610720565b801561020c5780601f106101e15761010080835404028352916020019161020c565b820191906000526020600020905b8154815290600101906020018083116101ef57829003601f168201915b505050505081565b600160036000828254610227919061075a565b9091555050600354600090815260208190526040902055565b600160036000828254610253919061075a565b90915550506003546000908152600160209081526040909120825161027a9284019061049a565b5050565b805160609061029490600690602085019061049a565b50600680546102a290610720565b80601f01602080910402602001604051908101604052809291908181526020018280546102ce90610720565b801561031b5780601f106102f05761010080835404028352916020019161031b565b820191906000526020600020905b8154815290600101906020018083116102fe57829003601f168201915b50505050509050919050565b6004805461019390610720565b6001602052600090815260409020805461019390610720565b6000600254600354101561036057600080fd5b60028054600090815260208190526040812080546005558190558154600192919061038c90849061075a565b9091555050600554919050565b606060025460035410156103ac57600080fd5b60025460009081526001602052604090208054600491906103cc90610720565b6103d792919061051e565b5060025460009081526001602052604081206103f291610599565b600160026000828254610405919061075a565b90915550506004805461041790610720565b80601f016020809104026020016040519081016040528092919081815260200182805461044390610720565b80156104905780601f1061046557610100808354040283529160200191610490565b820191906000526020600020905b81548152906001019060200180831161047357829003601f168201915b5050505050905090565b8280546104a690610720565b90600052602060002090601f0160209004810192826104c8576000855561050e565b82601f106104e157805160ff191683800117855561050e565b8280016001018555821561050e579182015b8281111561050e5782518255916020019190600101906104f3565b5061051a9291506105d6565b5090565b82805461052a90610720565b90600052602060002090601f01602090048101928261054c576000855561050e565b82601f1061055d578054855561050e565b8280016001018555821561050e57600052602060002091601f016020900482015b8281111561050e57825482559160010191906001019061057e565b5080546105a590610720565b6000825580601f106105b5575050565b601f0160209004906000526020600020908101906105d391906105d6565b50565b5b8082111561051a57600081556001016105d7565b600060208083528351808285015260005b81811015610618578581018301518582016040015282016105fc565b8181111561062a576000604083870101525b50601f01601f1916929092016040019392505050565b60006020828403121561065257600080fd5b5035919050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561068157600080fd5b813567ffffffffffffffff8082111561069957600080fd5b818401915084601f8301126106ad57600080fd5b8135818111156106bf576106bf610659565b604051601f8201601f19908116603f011681019083821181831017156106e7576106e7610659565b8160405282815287602084870101111561070057600080fd5b826020860160208301376000928101602001929092525095945050505050565b600181811c9082168061073457607f821691505b60208210810361075457634e487b7160e01b600052602260045260246000fd5b50919050565b6000821982111561077b57634e487b7160e01b600052601160045260246000fd5b50019056fea26469706673582212207cce5f0e37b234974eef897f5026ed91cadf327eca8436704b305908b02dc48a64736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DATASTRING = "dataString";

    public static final String FUNC_DATAUINT = "dataUint";

    public static final String FUNC_DEQUEUE = "dequeue";

    public static final String FUNC_DEQUEUEEVENT = "dequeueEvent";

    public static final String FUNC_ENQUEUE = "enqueue";

    public static final String FUNC_ENQUEUEEVENT = "enqueueEvent";

    public static final String FUNC_ENTRANCE = "entrance";

    public static final String FUNC_EVENTQUEUE = "eventqueue";

    public static final String FUNC_QUEUE = "queue";

    public static final String FUNC_REAR = "rear";

    public static final String FUNC_TESTFUNCTION = "testFunction";

    public static final String FUNC_TESTMESSAGE = "testMessage";

    @Deprecated
    protected EventSingleEndedQueue(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EventSingleEndedQueue(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EventSingleEndedQueue(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EventSingleEndedQueue(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> dataString() {
        final Function function = new Function(FUNC_DATASTRING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> dataUint() {
        final Function function = new Function(FUNC_DATAUINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> dequeue() {
        final Function function = new Function(
                FUNC_DEQUEUE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> dequeueEvent() {
        final Function function = new Function(
                FUNC_DEQUEUEEVENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> enqueue(BigInteger _data) {
        final Function function = new Function(
                FUNC_ENQUEUE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> enqueueEvent(String _data) {
        final Function function = new Function(
                FUNC_ENQUEUEEVENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> entrance() {
        final Function function = new Function(FUNC_ENTRANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> eventqueue(BigInteger param0) {
        final Function function = new Function(FUNC_EVENTQUEUE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> queue(BigInteger param0) {
        final Function function = new Function(FUNC_QUEUE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> rear() {
        final Function function = new Function(FUNC_REAR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> testFunction(String _sampleMessage) {
        final Function function = new Function(
                FUNC_TESTFUNCTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_sampleMessage)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> testMessage() {
        final Function function = new Function(FUNC_TESTMESSAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static EventSingleEndedQueue load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EventSingleEndedQueue(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EventSingleEndedQueue load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EventSingleEndedQueue(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EventSingleEndedQueue load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EventSingleEndedQueue(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EventSingleEndedQueue load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EventSingleEndedQueue(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EventSingleEndedQueue> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EventSingleEndedQueue.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<EventSingleEndedQueue> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EventSingleEndedQueue.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<EventSingleEndedQueue> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EventSingleEndedQueue.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<EventSingleEndedQueue> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EventSingleEndedQueue.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
