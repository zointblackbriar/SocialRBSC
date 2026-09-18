/**
 * SocialInfluence.java
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
public class SocialInfluence extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610c42806100206000396000f3fe608060405234801561001057600080fd5b50600436106101205760003560e01c80636ba392aa116100ad5780638c6f955f116100715780638c6f955f1461038e578063ad0da42c146103cd578063cbfe8dc5146103e0578063e384b591146103f3578063ede552a31461018357600080fd5b80636ba392aa146102e85780637884525b146102fb5780637c8a76071461030e578063831f1e581461033c578063841d41ef1461037b57600080fd5b8063479f98de116100f4578063479f98de146101c157806350c3f224146101d4578063582b27db146101e7578063592a426e1461023b578063668425051461028957600080fd5b806257830b146101255780630e476f33146101835780632282e06a146101995780632996c919146101ae575b600080fd5b61016e610133366004610922565b600360209081526000938452604080852082529284529190922082518084018301805192815290830193909201929092209190525460ff1681565b60405190151581526020015b60405180910390f35b61016e610191366004610980565b600192915050565b6101ac6101a73660046109c3565b610432565b005b6101ac6101bc366004610a32565b6104da565b6101ac6101cf366004610980565b61056b565b6101ac6101e2366004610a32565b610603565b61021c6101f5366004610a90565b6000602081905290815260409020546001600160a01b03811690600160a01b900460ff1682565b604080516001600160a01b03909316835290151560208301520161017a565b61021c610249366004610a90565b6001600160a01b0390811660009081526020818152604091829020825180840190935254928316808352600160a01b90930460ff16151591018190529091565b6101ac610297366004610a90565b6040805180820182526001600160a01b039283168082526001602080840191825260009283528290529290209051815492511515600160a01b026001600160a81b0319909316931692909217179055565b6101ac6102f6366004610a32565b61066e565b6101ac610309366004610a90565b6106d9565b61016e61031c366004610ab2565b805160208183018101805160018252928201919093012091525460ff1681565b61016e61034a366004610aef565b6004602090815260009283526040909220815180830184018051928152908401929093019190912091525460ff1681565b6101ac610389366004610a90565b6107a3565b61016e61039c366004610aef565b6005602090815260009283526040909220815180830184018051928152908401929093019190912091525460ff1681565b6101ac6103db366004610b3d565b6107df565b6101ac6103ee366004610980565b6107f0565b61016e610401366004610aef565b6002602090815260009283526040909220815180830184018051928152908401929093019190912091525460ff1681565b6001600160a01b0384166000908152602081905260409020548490600160a01b900460ff1661047c5760405162461bcd60e51b815260040161047390610b82565b60405180910390fd5b6001600160a01b038086166000908152600360209081526040808320938816835292905281902090518391906104b3908690610bb9565b908152604051908190036020019020805491151560ff199092169190911790555050505050565b6001600160a01b0383166000908152602081905260409020548390600160a01b900460ff1661051b5760405162461bcd60e51b815260040161047390610b82565b6001600160a01b038416600090815260046020526040908190209051839190610545908690610bb9565b908152604051908190036020019020805491151560ff1990921691909117905550505050565b6001600160a01b0380821660009081526003602090815260408083209386168352929052819020905161059d90610bf4565b9081526040519081900360200190205460ff16156105ff576001600160a01b038216600090815260046020526040908190209051600191906105de90610bf4565b908152604051908190036020019020805491151560ff199092169190911790555b5050565b6001600160a01b0383166000908152602081905260409020548390600160a01b900460ff166106445760405162461bcd60e51b815260040161047390610b82565b6001600160a01b038416600090815260026020526040908190209051839190610545908690610bb9565b6001600160a01b0383166000908152602081905260409020548390600160a01b900460ff166106af5760405162461bcd60e51b815260040161047390610b82565b6001600160a01b038416600090815260056020526040908190209051839190610545908690610bb9565b6001600160a01b0381166000908152600460205260409081902090516106fe90610bf4565b9081526040519081900360200190205460ff16801561075157506001600160a01b03811660009081526002602052604090819020905161073d90610bf4565b9081526040519081900360200190205460ff165b156107a0576001600160a01b0381166000908152600560205260409081902090516001919061077f90610bf4565b908152604051908190036020019020805491151560ff199092169190911790555b50565b6001600160a01b0381166000908152600260205260409081902090516d185d1d195b99195917d95d995b9d60921b815260019190600e0161077f565b806001836040516105de9190610bb9565b6001600160a01b03821660009081526005602052604090819020905161081590610bf4565b9081526040519081900360200190205460ff16156105ff576001600160a01b03808316600090815260036020908152604080832093851683529290528190209051600191906105de90610bf4565b80356001600160a01b038116811461087a57600080fd5b919050565b634e487b7160e01b600052604160045260246000fd5b600082601f8301126108a657600080fd5b813567ffffffffffffffff808211156108c1576108c161087f565b604051601f8301601f19908116603f011681019082821181831017156108e9576108e961087f565b8160405283815286602085880101111561090257600080fd5b836020870160208301376000602085830101528094505050505092915050565b60008060006060848603121561093757600080fd5b61094084610863565b925061094e60208501610863565b9150604084013567ffffffffffffffff81111561096a57600080fd5b61097686828701610895565b9150509250925092565b6000806040838503121561099357600080fd5b61099c83610863565b91506109aa60208401610863565b90509250929050565b8035801515811461087a57600080fd5b600080600080608085870312156109d957600080fd5b6109e285610863565b93506109f060208601610863565b9250604085013567ffffffffffffffff811115610a0c57600080fd5b610a1887828801610895565b925050610a27606086016109b3565b905092959194509250565b600080600060608486031215610a4757600080fd5b610a5084610863565b9250602084013567ffffffffffffffff811115610a6c57600080fd5b610a7886828701610895565b925050610a87604085016109b3565b90509250925092565b600060208284031215610aa257600080fd5b610aab82610863565b9392505050565b600060208284031215610ac457600080fd5b813567ffffffffffffffff811115610adb57600080fd5b610ae784828501610895565b949350505050565b60008060408385031215610b0257600080fd5b610b0b83610863565b9150602083013567ffffffffffffffff811115610b2757600080fd5b610b3385828601610895565b9150509250929050565b60008060408385031215610b5057600080fd5b823567ffffffffffffffff811115610b6757600080fd5b610b7385828601610895565b9250506109aa602084016109b3565b6020808252601e908201527f4e6f742061207265676973746572656420736f6369616c206167656e742e0000604082015260600190565b6000825160005b81811015610bda5760208186018101518583015201610bc0565b81811115610be9576000828501525b509190910192915050565b6b185d1d195b9917d95d995b9d60a21b8152600c019056fea26469706673582212205e75b192ec4bc804140496a5f676ad78505344dc713fa67caae8f4f35be7cc2864736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIONS = "actions";

    public static final String FUNC_APPLYACTIONCOMPLIANCE = "applyActionCompliance";

    public static final String FUNC_APPLYINFLUENCEDEFINITION = "applyInfluenceDefinition";

    public static final String FUNC_APPLYNORMDEFINITION = "applyNormDefinition";

    public static final String FUNC_APPLYPRESSUREDEFINITION = "applyPressureDefinition";

    public static final String FUNC_EXPECTEDACTIONS = "expectedActions";

    public static final String FUNC_FRIENDSOCIALAGENT = "friendSocialAgent";

    public static final String FUNC_GETREGISTEREDSOCIALAGENT = "getRegisteredSocialAgent";

    public static final String FUNC_GROUPMEMBERSHIP = "groupMembership";

    public static final String FUNC_INFLUENCES = "influences";

    public static final String FUNC_NORMS = "norms";

    public static final String FUNC_PRESSURES = "pressures";

    public static final String FUNC_REGISTERSOCIALAGENT = "registerSocialAgent";

    public static final String FUNC_SETACTION = "setAction";

    public static final String FUNC_SETEXPECTEDACTION = "setExpectedAction";

    public static final String FUNC_SETINFLUENCE = "setInfluence";

    public static final String FUNC_SETNORM = "setNorm";

    public static final String FUNC_SETPRESSURE = "setPressure";

    public static final String FUNC_SOCIALAGENTS = "socialAgents";

    @Deprecated
    protected SocialInfluence(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialInfluence(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialInfluence(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialInfluence(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> actions(String param0, String param1) {
        final Function function = new Function(FUNC_ACTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Utf8String(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> applyActionCompliance(String _socialAgent) {
        final Function function = new Function(
                FUNC_APPLYACTIONCOMPLIANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> applyInfluenceDefinition(String _socialAgent,
            String _friend) {
        final Function function = new Function(
                FUNC_APPLYINFLUENCEDEFINITION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent), 
                new org.web3j.abi.datatypes.Address(160, _friend)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> applyNormDefinition(String _socialAgent) {
        final Function function = new Function(
                FUNC_APPLYNORMDEFINITION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> applyPressureDefinition(String _socialAgent,
            String _friend) {
        final Function function = new Function(
                FUNC_APPLYPRESSUREDEFINITION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent), 
                new org.web3j.abi.datatypes.Address(160, _friend)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> expectedActions(String param0) {
        final Function function = new Function(FUNC_EXPECTEDACTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> friendSocialAgent(String _socialAgent, String _friend) {
        final Function function = new Function(FUNC_FRIENDSOCIALAGENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent), 
                new org.web3j.abi.datatypes.Address(160, _friend)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple2<String, Boolean>> getRegisteredSocialAgent(
            String _socialAgent) {
        final Function function = new Function(FUNC_GETREGISTEREDSOCIALAGENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple2<String, Boolean>>(function,
                new Callable<Tuple2<String, Boolean>>() {
                    @Override
                    public Tuple2<String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> groupMembership(String _group, String _socialAgent) {
        final Function function = new Function(FUNC_GROUPMEMBERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _group), 
                new org.web3j.abi.datatypes.Address(160, _socialAgent)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> influences(String param0, String param1, String param2) {
        final Function function = new Function(FUNC_INFLUENCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Address(160, param1), 
                new org.web3j.abi.datatypes.Utf8String(param2)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> norms(String param0, String param1) {
        final Function function = new Function(FUNC_NORMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Utf8String(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> pressures(String param0, String param1) {
        final Function function = new Function(FUNC_PRESSURES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Utf8String(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> registerSocialAgent(String _socialAgent) {
        final Function function = new Function(
                FUNC_REGISTERSOCIALAGENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAction(String _socialAgent, String _action,
            Boolean _performed) {
        final Function function = new Function(
                FUNC_SETACTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent), 
                new org.web3j.abi.datatypes.Utf8String(_action), 
                new org.web3j.abi.datatypes.Bool(_performed)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setExpectedAction(String _action,
            Boolean _expected) {
        final Function function = new Function(
                FUNC_SETEXPECTEDACTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_action), 
                new org.web3j.abi.datatypes.Bool(_expected)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setInfluence(String _socialAgent,
            String _influencedAgent, String _action, Boolean _influenced) {
        final Function function = new Function(
                FUNC_SETINFLUENCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent), 
                new org.web3j.abi.datatypes.Address(160, _influencedAgent), 
                new org.web3j.abi.datatypes.Utf8String(_action), 
                new org.web3j.abi.datatypes.Bool(_influenced)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setNorm(String _socialAgent, String _action,
            Boolean _expected) {
        final Function function = new Function(
                FUNC_SETNORM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent), 
                new org.web3j.abi.datatypes.Utf8String(_action), 
                new org.web3j.abi.datatypes.Bool(_expected)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPressure(String _socialAgent, String _action,
            Boolean _pressured) {
        final Function function = new Function(
                FUNC_SETPRESSURE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgent), 
                new org.web3j.abi.datatypes.Utf8String(_action), 
                new org.web3j.abi.datatypes.Bool(_pressured)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<String, Boolean>> socialAgents(String param0) {
        final Function function = new Function(FUNC_SOCIALAGENTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple2<String, Boolean>>(function,
                new Callable<Tuple2<String, Boolean>>() {
                    @Override
                    public Tuple2<String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue());
                    }
                });
    }

    @Deprecated
    public static SocialInfluence load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialInfluence(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialInfluence load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialInfluence(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialInfluence load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new SocialInfluence(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialInfluence load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialInfluence(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialInfluence> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialInfluence.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocialInfluence> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialInfluence.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialInfluence> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialInfluence.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialInfluence> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialInfluence.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
