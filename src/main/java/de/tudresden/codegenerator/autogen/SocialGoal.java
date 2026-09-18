package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint8;
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
 * <p>Generated with web3j version 5.0.2.
 */
@SuppressWarnings("rawtypes")
@Generated("org.web3j.codegen.SolidityFunctionWrapperGenerator")
public class SocialGoal extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610b40806100206000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c80634f0b97ee1161005b5780634f0b97ee1461012757806358d7f2791461013a5780639298f4241461014d5780639515c6d31461016057600080fd5b80630ab2a8931461008d578063120da4a5146100b757806318d63d9b146100ca578063491be435146100f5575b600080fd5b6100a061009b36600461083e565b610173565b6040516100ae9291906108c9565b60405180910390f35b6100a06100c5366004610921565b61024e565b6000546100dd906001600160a01b031681565b6040516001600160a01b0390911681526020016100ae565b61012561010336600461097f565b600080546001600160a01b0319166001600160a01b0392909216919091179055565b005b610125610135366004610921565b61039a565b610125610148366004610921565b6104a6565b61012561015b3660046109af565b6105bc565b61012561016e366004610a13565b610652565b8151602081840181018051600182529282019185019190912091905280548290811061019e57600080fd5b9060005260206000209060020201600091509150508060000180546101c290610a72565b80601f01602080910402602001604051908101604052809291908181526020018280546101ee90610a72565b801561023b5780601f106102105761010080835404028352916020019161023b565b820191906000526020600020905b81548152906001019060200180831161021e57829003601f168201915b5050506001909301549192505060ff1682565b606060006001846040516102629190610aac565b908152604051908190036020019020546001600160801b038416106102a25760405162461bcd60e51b815260040161029990610ac8565b60405180910390fd5b60006001856040516102b49190610aac565b9081526020016040518091039020846001600160801b0316815481106102dc576102dc610af4565b90600052602060002090600202019050806000018160010160009054906101000a900460ff1681805461030e90610a72565b80601f016020809104026020016040519081016040528092919081815260200182805461033a90610a72565b80156103875780601f1061035c57610100808354040283529160200191610387565b820191906000526020600020905b81548152906001019060200180831161036a57829003601f168201915b5050505050915092509250509250929050565b6001826040516103aa9190610aac565b908152604051908190036020019020546001600160801b038216106103e15760405162461bcd60e51b815260040161029990610ac8565b60006001836040516103f39190610aac565b9081526020016040518091039020826001600160801b03168154811061041b5761041b610af4565b60009182526020909120600290910201905060018082015460ff166003811115610447576104476108b3565b146104895760405162461bcd60e51b8152602060048201526012602482015271476f616c206973206e6f742061637469766560701b6044820152606401610299565b6001808201805460029260ff1990911690835b0217905550505050565b6001826040516104b69190610aac565b908152604051908190036020019020546001600160801b038216106104ed5760405162461bcd60e51b815260040161029990610ac8565b60006001836040516104ff9190610aac565b9081526020016040518091039020826001600160801b03168154811061052757610527610af4565b6000918252602082206002909102019150600182015460ff166003811115610551576105516108b3565b146105aa5760405162461bcd60e51b815260206004820152602360248201527f476f616c20697320616c726561647920616374697665206f7220636f6d706c656044820152621d195960ea1b6064820152608401610299565b6001818101805460ff1916828061049c565b6040805180820182528281526000602082015290516001906105df908590610aac565b90815260405160209181900382019020805460018101825560009182529082902083518051859460029094029092019261061e92849290910190610702565b50602082015160018083018054909160ff1990911690836003811115610646576106466108b3565b02179055505050505050565b6001836040516106629190610aac565b9081526040519081900360200190205482106106905760405162461bcd60e51b815260040161029990610ac8565b60006001846040516106a29190610aac565b908152602001604051809103902083815481106106c1576106c1610af4565b90600052602060002090600202019050818160010160006101000a81548160ff021916908360038111156106f7576106f76108b3565b021790555050505050565b82805461070e90610a72565b90600052602060002090601f0160209004810192826107305760008555610776565b82601f1061074957805160ff1916838001178555610776565b82800160010185558215610776579182015b8281111561077657825182559160200191906001019061075b565b50610782929150610786565b5090565b5b808211156107825760008155600101610787565b634e487b7160e01b600052604160045260246000fd5b600082601f8301126107c257600080fd5b813567ffffffffffffffff808211156107dd576107dd61079b565b604051601f8301601f19908116603f011681019082821181831017156108055761080561079b565b8160405283815286602085880101111561081e57600080fd5b836020870160208301376000602085830101528094505050505092915050565b6000806040838503121561085157600080fd5b823567ffffffffffffffff81111561086857600080fd5b610874858286016107b1565b95602094909401359450505050565b60005b8381101561089e578181015183820152602001610886565b838111156108ad576000848401525b50505050565b634e487b7160e01b600052602160045260246000fd5b60408152600083518060408401526108e8816060850160208801610883565b601f01601f1916820160600190506004831061091457634e487b7160e01b600052602160045260246000fd5b8260208301529392505050565b6000806040838503121561093457600080fd5b823567ffffffffffffffff81111561094b57600080fd5b610957858286016107b1565b92505060208301356001600160801b038116811461097457600080fd5b809150509250929050565b60006020828403121561099157600080fd5b81356001600160a01b03811681146109a857600080fd5b9392505050565b600080604083850312156109c257600080fd5b823567ffffffffffffffff808211156109da57600080fd5b6109e6868387016107b1565b935060208501359150808211156109fc57600080fd5b50610a09858286016107b1565b9150509250929050565b600080600060608486031215610a2857600080fd5b833567ffffffffffffffff811115610a3f57600080fd5b610a4b868287016107b1565b93505060208401359150604084013560048110610a6757600080fd5b809150509250925092565b600181811c90821680610a8657607f821691505b602082108103610aa657634e487b7160e01b600052602260045260246000fd5b50919050565b60008251610abe818460208701610883565b9190910192915050565b602080825260129082015271092dcecc2d8d2c840cedec2d840d2dcc8caf60731b604082015260600190565b634e487b7160e01b600052603260045260246000fdfea26469706673582212208ecea6eac259e1097f5ed8d57930e5a9cce9cee0de8aa79679c4ca9f8278fdd664736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATEGOAL = "activateGoal";

    public static final String FUNC_ADDGOAL = "addGoal";

    public static final String FUNC_AGENTGOALS = "agentGoals";

    public static final String FUNC_ASSIGNMENTOWNERADDRESS = "assignmentOwnerAddress";

    public static final String FUNC_COMPLETEGOAL = "completeGoal";

    public static final String FUNC_GETGOAL = "getGoal";

    public static final String FUNC_GOALOWNER = "goalOwner";

    public static final String FUNC_SETGOALSTATUS = "setGoalStatus";

    @Deprecated
    protected SocialGoal(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialGoal(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialGoal(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialGoal(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> activateGoal(String _nameOfAgent,
            BigInteger goalIndex) {
        final Function function = new Function(
                FUNC_ACTIVATEGOAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nameOfAgent), 
                new org.web3j.abi.datatypes.generated.Uint128(goalIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addGoal(String _nameOfAgent, String description) {
        final Function function = new Function(
                FUNC_ADDGOAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nameOfAgent), 
                new org.web3j.abi.datatypes.Utf8String(description)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> agentGoals(String param0,
            BigInteger param1) {
        final Function function = new Function(FUNC_AGENTGOALS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> assignmentOwnerAddress(String ownerAssignment) {
        final Function function = new Function(
                FUNC_ASSIGNMENTOWNERADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, ownerAssignment)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> completeGoal(String _nameOfAgent,
            BigInteger goalIndex) {
        final Function function = new Function(
                FUNC_COMPLETEGOAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nameOfAgent), 
                new org.web3j.abi.datatypes.generated.Uint128(goalIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> getGoal(String agent,
            BigInteger goalIndex) {
        final Function function = new Function(FUNC_GETGOAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(agent), 
                new org.web3j.abi.datatypes.generated.Uint128(goalIndex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
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

    public RemoteFunctionCall<String> goalOwner() {
        final Function function = new Function(FUNC_GOALOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setGoalStatus(String _nameOfAgent,
            BigInteger goalIndex, BigInteger newState) {
        final Function function = new Function(
                FUNC_SETGOALSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nameOfAgent), 
                new org.web3j.abi.datatypes.generated.Uint256(goalIndex), 
                new org.web3j.abi.datatypes.generated.Uint8(newState)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SocialGoal load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialGoal(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialGoal load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialGoal(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialGoal load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new SocialGoal(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialGoal load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialGoal(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialGoal> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialGoal.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialGoal> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialGoal.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocialGoal> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialGoal.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialGoal> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialGoal.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
