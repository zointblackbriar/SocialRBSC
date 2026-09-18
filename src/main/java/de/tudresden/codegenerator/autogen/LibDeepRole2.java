package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
public class LibDeepRole2 extends Contract {
    public static final String BINARY = "61044161003a600b82828239805160001a60731461002d57634e487b7160e01b600052600060045260246000fd5b30600052607381538281f3fe730000000000000000000000000000000000000000301460806040526004361061004b5760003560e01c8063b626b65314610050578063d086945d1461007b578063d5c41d031461009d575b600080fd5b81801561005c57600080fd5b506100656100a5565b60405161007291906102b5565b60405180910390f35b81801561008757600080fd5b5061009b610096366004610320565b6101b1565b005b6100656101ea565b60408051808201909152600d8082526c4469616d6f6e642050726f787960981b60209092019182526060917fc4468d7fb3be626474da477d955b42bfcb4000da70f2bee1f5b6d7ccd9de89939161011d917fc4468d7fb3be626474da477d955b42bfcb4000da70f2bee1f5b6d7ccd9de89949161021c565b5080600101805461012d906103d1565b80601f0160208091040260200160405190810160405280929190818152602001828054610159906103d1565b80156101a65780601f1061017b576101008083540402835291602001916101a6565b820191906000526020600020905b81548152906001019060200180831161018957829003601f168201915b505050505091505090565b80517fc4468d7fb3be626474da477d955b42bfcb4000da70f2bee1f5b6d7ccd9de8993906101e5908290602085019061021c565b505050565b7fc4468d7fb3be626474da477d955b42bfcb4000da70f2bee1f5b6d7ccd9de8993805460609190819061012d906103d1565b828054610228906103d1565b90600052602060002090601f01602090048101928261024a5760008555610290565b82601f1061026357805160ff1916838001178555610290565b82800160010185558215610290579182015b82811115610290578251825591602001919060010190610275565b5061029c9291506102a0565b5090565b5b8082111561029c57600081556001016102a1565b600060208083528351808285015260005b818110156102e2578581018301518582016040015282016102c6565b818111156102f4576000604083870101525b50601f01601f1916929092016040019392505050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561033257600080fd5b813567ffffffffffffffff8082111561034a57600080fd5b818401915084601f83011261035e57600080fd5b8135818111156103705761037061030a565b604051601f8201601f19908116603f011681019083821181831017156103985761039861030a565b816040528281528760208487010111156103b157600080fd5b826020860160208301376000928101602001929092525095945050505050565b600181811c908216806103e557607f821691505b60208210810361040557634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212204e6f76d2be5d4af26fd5420bbb53702eef7eac910bb514fa5beb856ff5fdb6d864736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETDEEPROLEID = "getDeepRoleID";

    @Deprecated
    protected LibDeepRole2(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LibDeepRole2(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LibDeepRole2(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LibDeepRole2(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getDeepRoleID() {
        final Function function = new Function(FUNC_GETDEEPROLEID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static LibDeepRole2 load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new LibDeepRole2(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LibDeepRole2 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LibDeepRole2(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LibDeepRole2 load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new LibDeepRole2(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LibDeepRole2 load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LibDeepRole2(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<LibDeepRole2> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LibDeepRole2.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<LibDeepRole2> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LibDeepRole2.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<LibDeepRole2> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LibDeepRole2.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<LibDeepRole2> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LibDeepRole2.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
