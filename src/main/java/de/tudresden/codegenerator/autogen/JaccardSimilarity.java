package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class JaccardSimilarity extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610528806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c806340edeb521461003b5780636537214714610060575b600080fd5b61004e610049366004610408565b610069565b60405190815260200160405180910390f35b61004e60005481565b600082826100778282610087565b6000819055925050505b92915050565b60008080805b85518110156100ed5760018060008884815181106100ad576100ad61046c565b602091018101516001600160f81b0319168252810191909152604001600020805460ff1916911515919091179055806100e581610498565b91505061008d565b5060005b84518110156101db57600160008683815181106101105761011061046c565b016020908101516001600160f81b03191682528101919091526040016000205460ff1615610183578261014281610498565b9350506001600086838151811061015b5761015b61046c565b016020908101516001600160f81b0319168252810191909152604001600020805460ff191690555b60016002600087848151811061019b5761019b61046c565b602091018101516001600160f81b0319168252810191909152604001600020805460ff1916911515919091179055806101d381610498565b9150506100f1565b5060005b855181101561028357600160008783815181106101fe576101fe61046c565b016020908101516001600160f81b03191682528101919091526040016000205460ff1615610271578161023081610498565b925050600160008783815181106102495761024961046c565b016020908101516001600160f81b0319168252810191909152604001600020805460ff191690555b8061027b81610498565b9150506101df565b5060005b845181101561032b57600260008683815181106102a6576102a661046c565b016020908101516001600160f81b03191682528101919091526040016000205460ff161561031957816102d881610498565b925050600260008683815181106102f1576102f161046c565b016020908101516001600160f81b0319168252810191909152604001600020805460ff191690555b8061032381610498565b915050610287565b508060000361033f57600092505050610081565b8061035283670de0b6b3a76400006104b1565b61035c91906104d0565b95945050505050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261038c57600080fd5b813567ffffffffffffffff808211156103a7576103a7610365565b604051601f8301601f19908116603f011681019082821181831017156103cf576103cf610365565b816040528381528660208588010111156103e857600080fd5b836020870160208301376000602085830101528094505050505092915050565b6000806040838503121561041b57600080fd5b823567ffffffffffffffff8082111561043357600080fd5b61043f8683870161037b565b9350602085013591508082111561045557600080fd5b506104628582860161037b565b9150509250929050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b6000600182016104aa576104aa610482565b5060010190565b60008160001904831182151516156104cb576104cb610482565b500290565b6000826104ed57634e487b7160e01b600052601260045260246000fd5b50049056fea2646970667358221220501750d83f118fc70a48caf1ab91c5f7b9e66f5e06eee58ea37ee795b66a8ca664736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_EXECUTEJACCARDSIMILARITY = "executeJaccardSimilarity";

    public static final String FUNC_RESULT = "result";

    @Deprecated
    protected JaccardSimilarity(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected JaccardSimilarity(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected JaccardSimilarity(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected JaccardSimilarity(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> executeJaccardSimilarity(String str1,
            String str2) {
        final Function function = new Function(
                FUNC_EXECUTEJACCARDSIMILARITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(str1), 
                new org.web3j.abi.datatypes.Utf8String(str2)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> result() {
        final Function function = new Function(FUNC_RESULT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static JaccardSimilarity load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new JaccardSimilarity(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static JaccardSimilarity load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new JaccardSimilarity(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static JaccardSimilarity load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new JaccardSimilarity(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static JaccardSimilarity load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new JaccardSimilarity(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<JaccardSimilarity> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(JaccardSimilarity.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<JaccardSimilarity> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(JaccardSimilarity.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<JaccardSimilarity> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(JaccardSimilarity.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<JaccardSimilarity> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(JaccardSimilarity.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
