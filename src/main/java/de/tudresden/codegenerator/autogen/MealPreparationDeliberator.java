package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class MealPreparationDeliberator extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50604051610a30380380610a3083398101604081905261002f91610054565b600080546001600160a01b0319166001600160a01b0392909216919091179055610084565b60006020828403121561006657600080fd5b81516001600160a01b038116811461007d57600080fd5b9392505050565b61099d806100936000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806338e5659a146100515780634960599a146100665780637ffa207f14610096578063df9084eb146100b9575b600080fd5b61006461005f366004610472565b6100d9565b005b600054610079906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b6100a96100a4366004610513565b610209565b604051901515815260200161008d565b6100cc6100c7366004610561565b6102ec565b60405161008d91906105fa565b6000546040516301c8cbf960e01b81526001600160a01b03909116906301c8cbf99061010990889060040161069b565b600060405180830381600087803b15801561012357600080fd5b505af1158015610137573d6000803e3d6000fd5b505060005460405162eb3a0360e11b81526001600160a01b0390911692506301d67406915061016c90879087906004016106ae565b600060405180830381600087803b15801561018657600080fd5b505af115801561019a573d6000803e3d6000fd5b505060005460405163272615ff60e11b81526001600160a01b039091169250634e4c2bfe91506101d090859085906004016106d2565b600060405180830381600087803b1580156101ea57600080fd5b505af11580156101fe573d6000803e3d6000fd5b505050505050505050565b60008054604051631eed213b60e31b81526001600160a01b039091169063f76909d89061023e908690869086906004016106f4565b600060405180830381600087803b15801561025857600080fd5b505af115801561026c573d6000803e3d6000fd5b5050505060008054906101000a90046001600160a01b03166001600160a01b0316635fb230d46040518163ffffffff1660e01b8152600401602060405180830381865afa1580156102c1573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102e591906107bb565b9392505050565b6000546040516301989d1b60e31b81526060916001600160a01b031690630cc4e8d89061031d90859060040161069b565b600060405180830381865afa15801561033a573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526103629190810190610822565b92915050565b634e487b7160e01b600052604160045260246000fd5b6040516060810167ffffffffffffffff811182821017156103a1576103a1610368565b60405290565b604051601f8201601f1916810167ffffffffffffffff811182821017156103d0576103d0610368565b604052919050565b600067ffffffffffffffff8211156103f2576103f2610368565b50601f01601f191660200190565b600082601f83011261041157600080fd5b813561042461041f826103d8565b6103a7565b81815284602083860101111561043957600080fd5b816020850160208301376000918101602001919091529392505050565b80356001600160a01b038116811461046d57600080fd5b919050565b600080600080600060a0868803121561048a57600080fd5b853567ffffffffffffffff808211156104a257600080fd5b6104ae89838a01610400565b96506104bc60208901610456565b955060408801359150808211156104d257600080fd5b6104de89838a01610400565b945060608801359150808211156104f457600080fd5b5061050188828901610400565b95989497509295608001359392505050565b6000806040838503121561052657600080fd5b823567ffffffffffffffff81111561053d57600080fd5b61054985828601610400565b92505061055860208401610456565b90509250929050565b60006020828403121561057357600080fd5b813567ffffffffffffffff81111561058a57600080fd5b61059684828501610400565b949350505050565b60005b838110156105b95781810151838201526020016105a1565b838111156105c8576000848401525b50505050565b600081518084526105e681602086016020860161059e565b601f01601f19169290920160200192915050565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b8381101561068d57603f19898403018552815160608151818652610647828701826105ce565b915050888201518582038a87015261065f82826105ce565b9150508782015191508481038886015261067981836105ce565b968901969450505090860190600101610621565b509098975050505050505050565b6020815260006102e560208301846105ce565b6001600160a01b0383168152604060208201819052600090610596908301846105ce565b6040815260006106e560408301856105ce565b90508260208301529392505050565b60e08152600061070760e08301866105ce565b828103806020850152600b82526a4d65616c536f636965747960a81b602083015260018060a01b038616604085015284606085015260408101608085015260096040830152684d616b65506173746160b81b60608301526080810160a0850152600b60808301526a141c995c185c995359585b60aa1b60a083015260c0810160c0850152506107b160c08201600b81526a57696c6c696e676e65737360a81b602082015260400190565b9695505050505050565b6000602082840312156107cd57600080fd5b815180151581146102e557600080fd5b600082601f8301126107ee57600080fd5b81516107fc61041f826103d8565b81815284602083860101111561081157600080fd5b61059682602083016020870161059e565b6000602080838503121561083557600080fd5b825167ffffffffffffffff8082111561084d57600080fd5b818501915085601f83011261086157600080fd5b81518181111561087357610873610368565b8060051b6108828582016103a7565b918252838101850191858101908984111561089c57600080fd5b86860192505b8383101561095a578251858111156108ba5760008081fd5b86016060818c03601f19018113156108d25760008081fd5b6108da61037e565b89830151888111156108ec5760008081fd5b6108fa8e8c838701016107dd565b825250604080840151898111156109115760008081fd5b61091f8f8d838801016107dd565b838d0152509183015191888311156109375760008081fd5b6109458e8c858701016107dd565b908201528452505091860191908601906108a2565b999850505050505050505056fea2646970667358221220e1e55117bf7e57cb248e8b1e0b996cf2f292974008b662a583ebcab3b1c1fc9464736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DELIBERATION = "deliberation";

    public static final String FUNC_GETMESSAGES = "getMessages";

    public static final String FUNC_PREPAREAGENT = "prepareAgent";

    public static final String FUNC_RUNMEALCYCLE = "runMealCycle";

    @Deprecated
    protected MealPreparationDeliberator(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MealPreparationDeliberator(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MealPreparationDeliberator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MealPreparationDeliberator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> deliberation() {
        final Function function = new Function(FUNC_DELIBERATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getMessages(String _sender) {
        final Function function = new Function(FUNC_GETMESSAGES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_sender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Message>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> prepareAgent(String _agentName,
            String _intentionsAddress, String _intention, String _beliefName,
            BigInteger _beliefValue) {
        final Function function = new Function(
                FUNC_PREPAREAGENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_agentName), 
                new org.web3j.abi.datatypes.Address(160, _intentionsAddress), 
                new org.web3j.abi.datatypes.Utf8String(_intention), 
                new org.web3j.abi.datatypes.Utf8String(_beliefName), 
                new org.web3j.abi.datatypes.generated.Uint256(_beliefValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> runMealCycle(String _agentName,
            String _intentionsAddress) {
        final Function function = new Function(
                FUNC_RUNMEALCYCLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_agentName), 
                new org.web3j.abi.datatypes.Address(160, _intentionsAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static MealPreparationDeliberator load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MealPreparationDeliberator(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MealPreparationDeliberator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MealPreparationDeliberator(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MealPreparationDeliberator load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MealPreparationDeliberator(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MealPreparationDeliberator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MealPreparationDeliberator(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MealPreparationDeliberator> deploy(Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider,
            String _deliberation) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _deliberation)));
        return deployRemoteCall(MealPreparationDeliberator.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<MealPreparationDeliberator> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _deliberation) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _deliberation)));
        return deployRemoteCall(MealPreparationDeliberator.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MealPreparationDeliberator> deploy(Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit,
            String _deliberation) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _deliberation)));
        return deployRemoteCall(MealPreparationDeliberator.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MealPreparationDeliberator> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _deliberation) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _deliberation)));
        return deployRemoteCall(MealPreparationDeliberator.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class Message extends DynamicStruct {
        public String sender;

        public String receiver;

        public String messageType;

        public Message(String sender, String receiver, String messageType) {
            super(new org.web3j.abi.datatypes.Utf8String(sender), 
                    new org.web3j.abi.datatypes.Utf8String(receiver), 
                    new org.web3j.abi.datatypes.Utf8String(messageType));
            this.sender = sender;
            this.receiver = receiver;
            this.messageType = messageType;
        }

        public Message(Utf8String sender, Utf8String receiver, Utf8String messageType) {
            super(sender, receiver, messageType);
            this.sender = sender.getValue();
            this.receiver = receiver.getValue();
            this.messageType = messageType.getValue();
        }
    }
}
