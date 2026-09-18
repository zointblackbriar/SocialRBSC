package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class DeepRoleFacet2 extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506103c0806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c8063b626b65314610046578063d086945d14610064578063d5c41d0314610079575b600080fd5b61004e610081565b60405161005b919061020a565b60405180910390f35b610077610072366004610293565b6100f9565b005b61004e610163565b606073__$8c1916e2ae17217625002615b370d33008$__63b626b6536040518163ffffffff1660e01b8152600401600060405180830381865af41580156100cc573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526100f49190810190610313565b905090565b60405163d086945d60e01b815273__$8c1916e2ae17217625002615b370d33008$__9063d086945d9061013090849060040161020a565b60006040518083038186803b15801561014857600080fd5b505af415801561015c573d6000803e3d6000fd5b5050505050565b606073__$8c1916e2ae17217625002615b370d33008$__63d5c41d036040518163ffffffff1660e01b8152600401600060405180830381865af41580156100cc573d6000803e3d6000fd5b60005b838110156101c95781810151838201526020016101b1565b838111156101d8576000848401525b50505050565b600081518084526101f68160208601602086016101ae565b601f01601f19169290920160200192915050565b60208152600061021d60208301846101de565b9392505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff8111828210171561026357610263610224565b604052919050565b600067ffffffffffffffff82111561028557610285610224565b50601f01601f191660200190565b6000602082840312156102a557600080fd5b813567ffffffffffffffff8111156102bc57600080fd5b8201601f810184136102cd57600080fd5b80356102e06102db8261026b565b61023a565b8181528560208385010111156102f557600080fd5b81602084016020830137600091810160200191909152949350505050565b60006020828403121561032557600080fd5b815167ffffffffffffffff81111561033c57600080fd5b8201601f8101841361034d57600080fd5b805161035b6102db8261026b565b81815285602083850101111561037057600080fd5b6103818260208301602086016101ae565b9594505050505056fea2646970667358221220344bac0d0fbeebb5e88d5c89edd1695542a781b314285960bdcc6f33137f218264736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_EXTERNALMESSAGEDEEPROLERELATED = "externalMessageDeepRoleRelated";

    public static final String FUNC_GETDEEPROLEID = "getDeepRoleID";

    public static final String FUNC_SETDEEPROLEID = "setDeepRoleID";

    @Deprecated
    protected DeepRoleFacet2(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DeepRoleFacet2(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DeepRoleFacet2(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DeepRoleFacet2(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> externalMessageDeepRoleRelated() {
        final Function function = new Function(
                FUNC_EXTERNALMESSAGEDEEPROLERELATED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getDeepRoleID() {
        final Function function = new Function(
                FUNC_GETDEEPROLEID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDeepRoleID(String _deepRoleID) {
        final Function function = new Function(
                FUNC_SETDEEPROLEID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_deepRoleID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DeepRoleFacet2 load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRoleFacet2(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DeepRoleFacet2 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRoleFacet2(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DeepRoleFacet2 load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DeepRoleFacet2(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DeepRoleFacet2 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DeepRoleFacet2(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DeepRoleFacet2> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeepRoleFacet2.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeepRoleFacet2> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeepRoleFacet2.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DeepRoleFacet2> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeepRoleFacet2.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeepRoleFacet2> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeepRoleFacet2.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
