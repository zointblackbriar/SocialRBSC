/**
 * SocialPlan.java
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
import org.web3j.abi.datatypes.Bool;
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
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class SocialPlan extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506106fd806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c806328705a2c1461005c578063861a4ed914610071578063cfb3cfd51461009b578063d6e4be32146100ea578063f819eb2e146100fd575b600080fd5b61006f61006a36600461047b565b610110565b005b61008461007f3660046104d2565b610155565b604051610092929190610574565b60405180910390f35b6100da6100a93660046104d2565b6001602090815260009283526040909220815180830184018051928152908401929093019190912091525460ff1681565b6040519015158152602001610092565b6100da6100f83660046105cc565b610215565b61006f61010b366004610626565b61029f565b33600090815260016020526040908190209051829190610131908590610671565b908152604051908190036020019020805491151560ff199092169190911790555050565b600060208181529281526040902081518083018401805192815290840192909301919091209152805481906101899061068d565b80601f01602080910402602001604051908101604052809291908181526020018280546101b59061068d565b80156102025780601f106101d757610100808354040283529160200191610202565b820191906000526020600020905b8154815290600101906020018083116101e557829003601f168201915b5050506001909301549192505060ff1682565b6000600133600090815260208190526040908190209051610237908590610671565b9081526040519081900360200190206001015460ff16600381111561025e5761025e61055e565b148015610298575033600090815260016020526040908190209051610284908590610671565b9081526040519081900360200190205460ff165b9392505050565b60405180604001604052808381526020018260038111156102c2576102c261055e565b9052336000908152602081905260409081902090516102e2908590610671565b9081526020016040518091039020600082015181600001908051906020019061030c92919061033f565b50602082015160018083018054909160ff19909116908360038111156103345761033461055e565b021790555050505050565b82805461034b9061068d565b90600052602060002090601f01602090048101928261036d57600085556103b3565b82601f1061038657805160ff19168380011785556103b3565b828001600101855582156103b3579182015b828111156103b3578251825591602001919060010190610398565b506103bf9291506103c3565b5090565b5b808211156103bf57600081556001016103c4565b634e487b7160e01b600052604160045260246000fd5b600082601f8301126103ff57600080fd5b813567ffffffffffffffff8082111561041a5761041a6103d8565b604051601f8301601f19908116603f01168101908282118183101715610442576104426103d8565b8160405283815286602085880101111561045b57600080fd5b836020870160208301376000602085830101528094505050505092915050565b6000806040838503121561048e57600080fd5b823567ffffffffffffffff8111156104a557600080fd5b6104b1858286016103ee565b925050602083013580151581146104c757600080fd5b809150509250929050565b600080604083850312156104e557600080fd5b82356001600160a01b03811681146104fc57600080fd5b9150602083013567ffffffffffffffff81111561051857600080fd5b610524858286016103ee565b9150509250929050565b60005b83811015610549578181015183820152602001610531565b83811115610558576000848401525b50505050565b634e487b7160e01b600052602160045260246000fd5b604081526000835180604084015261059381606085016020880161052e565b601f01601f191682016060019050600483106105bf57634e487b7160e01b600052602160045260246000fd5b8260208301529392505050565b600080604083850312156105df57600080fd5b823567ffffffffffffffff808211156105f757600080fd5b610603868387016103ee565b9350602085013591508082111561061957600080fd5b50610524858286016103ee565b6000806040838503121561063957600080fd5b823567ffffffffffffffff81111561065057600080fd5b61065c858286016103ee565b9250506020830135600481106104c757600080fd5b6000825161068381846020870161052e565b9190910192915050565b600181811c908216806106a157607f821691505b6020821081036106c157634e487b7160e01b600052602260045260246000fd5b5091905056fea2646970667358221220a30fce864ed7879e8388eeadb5f91fcc7f3e35d7ad7223682189031be592070164736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_EVALUATEFORMULA = "evaluateFormula";

    public static final String FUNC_EXECUTEPLAN = "executePlan";

    public static final String FUNC_FORMULAS = "formulas";

    public static final String FUNC_ISSOCIALAGENTFORMULATRUE = "isSocialAgentFormulaTrue";

    public static final String FUNC_SOCIALPLANS = "socialPlans";

    @Deprecated
    protected SocialPlan(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialPlan(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialPlan(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialPlan(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> evaluateFormula(String formulaName,
            Boolean value) {
        final Function function = new Function(
                FUNC_EVALUATEFORMULA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(formulaName), 
                new org.web3j.abi.datatypes.Bool(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> executePlan(String planName, BigInteger status) {
        final Function function = new Function(
                FUNC_EXECUTEPLAN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(planName), 
                new org.web3j.abi.datatypes.generated.Uint8(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> formulas(String param0, String param1) {
        final Function function = new Function(FUNC_FORMULAS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Utf8String(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isSocialAgentFormulaTrue(String formulaName,
            String planName) {
        final Function function = new Function(FUNC_ISSOCIALAGENTFORMULATRUE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(formulaName), 
                new org.web3j.abi.datatypes.Utf8String(planName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> socialPlans(String param0,
            String param1) {
        final Function function = new Function(FUNC_SOCIALPLANS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.Utf8String(param1)), 
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

    @Deprecated
    public static SocialPlan load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialPlan(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialPlan load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialPlan(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialPlan load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new SocialPlan(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialPlan load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialPlan(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialPlan> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialPlan.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialPlan> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialPlan.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocialPlan> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialPlan.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialPlan> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialPlan.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
