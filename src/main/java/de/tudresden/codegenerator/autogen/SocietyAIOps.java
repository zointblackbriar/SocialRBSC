package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
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
 * <p>Generated with web3j version 5.0.2.
 */
@SuppressWarnings("rawtypes")
@Generated("org.web3j.codegen.SolidityFunctionWrapperGenerator")
public class SocietyAIOps extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610436806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80633d5315b51461005c5780638a8981b11461007a5780639079abd314610091578063d85fcbd5146100a6578063e80c8a66146100b9575b600080fd5b6100646100c1565b6040516100719190610291565b60405180910390f35b61008360015481565b604051908152602001610071565b6100a461009f3660046102fc565b610153565b005b6100a46100b43660046103ad565b600155565b61006461016a565b6060600080546100d0906103c6565b80601f01602080910402602001604051908101604052809291908181526020018280546100fc906103c6565b80156101495780601f1061011e57610100808354040283529160200191610149565b820191906000526020600020905b81548152906001019060200180831161012c57829003601f168201915b5050505050905090565b80516101669060009060208401906101f8565b5050565b60008054610177906103c6565b80601f01602080910402602001604051908101604052809291908181526020018280546101a3906103c6565b80156101f05780601f106101c5576101008083540402835291602001916101f0565b820191906000526020600020905b8154815290600101906020018083116101d357829003601f168201915b505050505081565b828054610204906103c6565b90600052602060002090601f016020900481019282610226576000855561026c565b82601f1061023f57805160ff191683800117855561026c565b8280016001018555821561026c579182015b8281111561026c578251825591602001919060010190610251565b5061027892915061027c565b5090565b5b80821115610278576000815560010161027d565b600060208083528351808285015260005b818110156102be578581018301518582016040015282016102a2565b818111156102d0576000604083870101525b50601f01601f1916929092016040019392505050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561030e57600080fd5b813567ffffffffffffffff8082111561032657600080fd5b818401915084601f83011261033a57600080fd5b81358181111561034c5761034c6102e6565b604051601f8201601f19908116603f01168101908382118183101715610374576103746102e6565b8160405282815287602084870101111561038d57600080fd5b826020860160208301376000928101602001929092525095945050505050565b6000602082840312156103bf57600080fd5b5035919050565b600181811c908216806103da57607f821691505b6020821081036103fa57634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212205d711dfa2b23b46ffb5a5ed91e86f200193b534d7412eb55bb4e20cdbc030ed564736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETSOCIETYNAME = "getSocietyName";

    public static final String FUNC_SETSOCIETYID = "setSocietyID";

    public static final String FUNC_SETSOCIETYNAME = "setSocietyName";

    public static final String FUNC_SOCIETYID = "societyID";

    public static final String FUNC_SOCIETYNAME = "societyName";

    @Deprecated
    protected SocietyAIOps(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocietyAIOps(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocietyAIOps(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocietyAIOps(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getSocietyName() {
        final Function function = new Function(FUNC_GETSOCIETYNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setSocietyID(BigInteger _societyID) {
        final Function function = new Function(
                FUNC_SETSOCIETYID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_societyID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setSocietyName(String _societyName) {
        final Function function = new Function(
                FUNC_SETSOCIETYNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_societyName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> societyID() {
        final Function function = new Function(FUNC_SOCIETYID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> societyName() {
        final Function function = new Function(FUNC_SOCIETYNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static SocietyAIOps load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new SocietyAIOps(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocietyAIOps load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocietyAIOps(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocietyAIOps load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new SocietyAIOps(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocietyAIOps load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocietyAIOps(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocietyAIOps> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocietyAIOps.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocietyAIOps> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocietyAIOps.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocietyAIOps> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocietyAIOps.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocietyAIOps> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocietyAIOps.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
