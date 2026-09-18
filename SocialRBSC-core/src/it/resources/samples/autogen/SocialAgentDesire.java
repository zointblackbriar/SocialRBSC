/**
 * SocialAgentDesire.java
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
import org.web3j.abi.datatypes.generated.Uint8;
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
public class SocialAgentDesire extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506000805460ff191690556103188061002a6000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80633dd3c9e614610046578063c370214114610069578063e21cc2d51461007e575b600080fd5b6000546100539060ff1681565b6040516100609190610179565b60405180910390f35b61007c6100773660046101cb565b610093565b005b6100866100ba565b604051610060919061028d565b6000805482919060ff1916600183818111156100b1576100b1610163565b02179055505050565b60606000805460ff1660018111156100d4576100d4610163565b036100f857506040805180820190915260048152634e6f6e6560e01b602082015290565b600160005460ff16600181111561011157610111610163565b03610141575060408051808201909152601081526f22bc32b1baba32a4b73a32b73a34b7b760811b602082015290565b506040805180820190915260078152662ab735b737bbb760c91b602082015290565b634e487b7160e01b600052602160045260246000fd5b602081016002831061019b57634e487b7160e01b600052602160045260246000fd5b91905290565b634e487b7160e01b600052604160045260246000fd5b8035600281106101c657600080fd5b919050565b600080604083850312156101de57600080fd5b823567ffffffffffffffff808211156101f657600080fd5b818501915085601f83011261020a57600080fd5b81358181111561021c5761021c6101a1565b604051601f8201601f19908116603f01168101908382118183101715610244576102446101a1565b8160405282815288602084870101111561025d57600080fd5b826020860160208301376000602084830101528096505050505050610284602084016101b7565b90509250929050565b600060208083528351808285015260005b818110156102ba5785810183015185820160400152820161029e565b818111156102cc576000604083870101525b50601f01601f191692909201604001939250505056fea26469706673582212208f6c6430bf703c4beb02bc8953193be142614c1e539bbe842dfd22c0f1472ea664736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DESIRESTATUS = "desireStatus";

    public static final String FUNC_GETDESIRE = "getDesire";

    public static final String FUNC_SETDESIRE = "setDesire";

    @Deprecated
    protected SocialAgentDesire(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialAgentDesire(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialAgentDesire(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialAgentDesire(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> desireStatus() {
        final Function function = new Function(FUNC_DESIRESTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getDesire() {
        final Function function = new Function(FUNC_GETDESIRE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setDesire(String _metamodeledDesire,
            BigInteger _statusOfDesire) {
        final Function function = new Function(
                FUNC_SETDESIRE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_metamodeledDesire), 
                new org.web3j.abi.datatypes.generated.Uint8(_statusOfDesire)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SocialAgentDesire load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentDesire(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialAgentDesire load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentDesire(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialAgentDesire load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SocialAgentDesire(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialAgentDesire load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialAgentDesire(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialAgentDesire> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialAgentDesire.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocialAgentDesire> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialAgentDesire.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialAgentDesire> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialAgentDesire.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialAgentDesire> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialAgentDesire.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
