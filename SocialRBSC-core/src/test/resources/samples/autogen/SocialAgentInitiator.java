package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
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
public class SocialAgentInitiator extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162001421380380620014218339810160408190526200003491620000c1565b600280546001600160a01b03199081166001600160a01b03988916179091556003805482169688169690961790955560048054861694871694909417909355600580548516928616929092179091556006805484169285169290921790915560078054909216921691909117905562000155565b6001600160a01b0381168114620000be57600080fd5b50565b60008060008060008060c08789031215620000db57600080fd5b8651620000e881620000a8565b6020880151909650620000fb81620000a8565b60408801519095506200010e81620000a8565b60608801519094506200012181620000a8565b60808801519093506200013481620000a8565b60a08801519092506200014781620000a8565b809150509295509295509295565b6112bc80620001656000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c806395ec5ce311610097578063d0bf6d1511610066578063d0bf6d1514610202578063e15a6c5d14610215578063e959b38a14610228578063f2f4eb261461023b57600080fd5b806395ec5ce3146101c15780639fd20abc146101c9578063b3cac0a5146101dc578063c88ed960146101ef57600080fd5b8063496a3c48116100d3578063496a3c48146101805780636b5a3e39146101935780636e8eb7bc1461019b57806380009630146101ae57600080fd5b806301ffc9a714610105578063201b631b1461012d578063264062471461014057806340a9efc814610155575b600080fd5b610118610113366004610e26565b61024e565b60405190151581526020015b60405180910390f35b61011861013b366004610e68565b610285565b61015361014e366004610fb2565b610305565b005b600154610168906001600160a01b031681565b6040516001600160a01b039091168152602001610124565b61015361018e366004610fe7565b61054c565b610168610594565b6101686101a9366004611004565b610611565b6101536101bc366004610fe7565b610681565b610153610722565b6101536101d7366004610fe7565b61077d565b6101186101ea366004611004565b610821565b6101536101fd36600461101d565b61088f565b610153610210366004611004565b610ac9565b610168610223366004610fb2565b610afa565b61015361023636600461106f565b610d17565b600054610168906001600160a01b031681565b60006301ffc9a760e01b6001600160e01b03198316148061027f57506001600160e01b03198216636f8ae2e560e11b145b92915050565b6000805460405163201b631b60e01b81526001600160a01b039091169063201b631b906102ba90879087908790600401611094565b6020604051808303816000875af11580156102d9573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102fd91906110d4565b949350505050565b600554600654604051630c56240560e41b81526001600160a01b039283169263b3cac0a592169063c56240509061034090869060040161114e565b602060405180830381865afa15801561035d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103819190611161565b6040518263ffffffff1660e01b815260040161039f91815260200190565b602060405180830381865afa1580156103bc573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103e091906110d4565b6104055760405162461bcd60e51b81526004016103fc9061117a565b60405180910390fd5b600554600654604051630c56240560e41b81526001600160a01b039283169263b3cac0a592169063c56240509061044090869060040161114e565b602060405180830381865afa15801561045d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104819190611161565b6040518263ffffffff1660e01b815260040161049f91815260200190565b602060405180830381865afa1580156104bc573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104e091906110d4565b50600560009054906101000a90046001600160a01b03166001600160a01b03166395ec5ce36040518163ffffffff1660e01b8152600401600060405180830381600087803b15801561053157600080fd5b505af1158015610545573d6000803e3d6000fd5b5050505050565b60005460405163092d478960e31b81526001600160a01b0383811660048301529091169063496a3c48906024015b600060405180830381600087803b15801561053157600080fd5b60008060009054906101000a90046001600160a01b03166001600160a01b0316636b5a3e396040518163ffffffff1660e01b8152600401602060405180830381865afa1580156105e8573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061060c91906111c6565b905090565b60008054604051631ba3adef60e21b8152600481018490526001600160a01b0390911690636e8eb7bc906024016020604051808303816000875af115801561065d573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061027f91906111c6565b6001600160a01b038116156107005760006106a382637365940f60e01b610d50565b9050806106fe5760405162461bcd60e51b8152602060048201526024808201527f446f65736e277420737570706f727420436f6d706f6e656e7420696e7465726660448201526330b1b29760e11b60648201526084016103fc565b505b600080546001600160a01b0319166001600160a01b0392909216919091179055565b60008054604080516395ec5ce360e01b815290516001600160a01b03909216926395ec5ce39260048084019382900301818387803b15801561076357600080fd5b505af1158015610777573d6000803e3d6000fd5b50505050565b6001600160a01b038116156107ff57600061079f826338cce89560e01b610d50565b9050806107fd5760405162461bcd60e51b815260206004820152602660248201527f446f65736e277420737570706f727420636f6d706172746d656e7420696e7465604482015265393330b1b29760d11b60648201526084016103fc565b505b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000805460405163b3cac0a560e01b8152600481018490526001600160a01b039091169063b3cac0a590602401602060405180830381865afa15801561086b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061027f91906110d4565b600554600654604051630c56240560e41b81526001600160a01b039283169263b3cac0a592169063c5624050906108ca90879060040161114e565b602060405180830381865afa1580156108e7573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061090b9190611161565b6040518263ffffffff1660e01b815260040161092991815260200190565b602060405180830381865afa158015610946573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061096a91906110d4565b156109875760405162461bcd60e51b81526004016103fc9061117a565b600554600654604051630c56240560e41b81526001600160a01b039283169263e959b38a92169063c5624050906109c290879060040161114e565b602060405180830381865afa1580156109df573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a039190611161565b6040516001600160e01b031960e084901b16815260048101919091526001600160a01b0384166024820152604401600060405180830381600087803b158015610a4b57600080fd5b505af1158015610a5f573d6000803e3d6000fd5b505060055460075460405163092d478960e31b81526001600160a01b0391821660048201529116925063496a3c4891506024015b600060405180830381600087803b158015610aad57600080fd5b505af1158015610ac1573d6000803e3d6000fd5b505050505050565b60005460405163d0bf6d1560e01b8152600481018390526001600160a01b039091169063d0bf6d159060240161057a565b600254604051633901452160e21b81526000916001600160a01b03169063e405148490610b2b90859060040161114e565b600060405180830381600087803b158015610b4557600080fd5b505af1158015610b59573d6000803e3d6000fd5b505060025460048054600354604080516319b96c3f60e01b815290516001600160a01b03958616975063e51c19fd9650928516949091169286926319b96c3f9280830192600092918290030181865afa158015610bba573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052610be291908101906111e3565b6040518463ffffffff1660e01b8152600401610c009392919061125a565b600060405180830381600087803b158015610c1a57600080fd5b505af1158015610c2e573d6000803e3d6000fd5b5050600354600254604080516319b96c3f60e01b81529051600095506001600160a01b039384169450636e2bbc1593909216916319b96c3f9160048082019288929091908290030181865afa158015610c8b573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052610cb391908101906111e3565b6040518263ffffffff1660e01b8152600401610ccf919061114e565b602060405180830381865afa158015610cec573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610d1091906111c6565b9392505050565b6000546040516374acd9c560e11b8152600481018490526001600160a01b0383811660248301529091169063e959b38a90604401610a93565b60008080610d65856301ffc9a760e01b610df2565b9092509050811580610d75575080155b15610d855760009250505061027f565b610d97856001600160e01b0319610df2565b9092509050811580610da857508015155b15610db85760009250505061027f565b610dc28585610df2565b9092509050600182148015610dd75750806001145b15610de75760019250505061027f565b506000949350505050565b6040516301ffc9a760e01b8082526004820183905260009182919060208160248189617530fa905190969095509350505050565b600060208284031215610e3857600080fd5b81356001600160e01b031981168114610d1057600080fd5b6001600160a01b0381168114610e6557600080fd5b50565b600080600060408486031215610e7d57600080fd5b8335610e8881610e50565b9250602084013567ffffffffffffffff80821115610ea557600080fd5b818601915086601f830112610eb957600080fd5b813581811115610ec857600080fd5b876020828501011115610eda57600080fd5b6020830194508093505050509250925092565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715610f2c57610f2c610eed565b604052919050565b600067ffffffffffffffff821115610f4e57610f4e610eed565b50601f01601f191660200190565b600082601f830112610f6d57600080fd5b8135610f80610f7b82610f34565b610f03565b818152846020838601011115610f9557600080fd5b816020850160208301376000918101602001919091529392505050565b600060208284031215610fc457600080fd5b813567ffffffffffffffff811115610fdb57600080fd5b6102fd84828501610f5c565b600060208284031215610ff957600080fd5b8135610d1081610e50565b60006020828403121561101657600080fd5b5035919050565b6000806040838503121561103057600080fd5b823567ffffffffffffffff81111561104757600080fd5b61105385828601610f5c565b925050602083013561106481610e50565b809150509250929050565b6000806040838503121561108257600080fd5b82359150602083013561106481610e50565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b6000602082840312156110e657600080fd5b81518015158114610d1057600080fd5b60005b838110156111115781810151838201526020016110f9565b838111156107775750506000910152565b6000815180845261113a8160208601602086016110f6565b601f01601f19169290920160200192915050565b602081526000610d106020830184611122565b60006020828403121561117357600080fd5b5051919050565b6020808252602c908201527f6f74686572776973652077652063616e6e6f742061646420746869732069662060408201526b1a5d081a5cc81c1b185e595960a21b606082015260800190565b6000602082840312156111d857600080fd5b8151610d1081610e50565b6000602082840312156111f557600080fd5b815167ffffffffffffffff81111561120c57600080fd5b8201601f8101841361121d57600080fd5b805161122b610f7b82610f34565b81815285602083850101111561124057600080fd5b6112518260208301602086016110f6565b95945050505050565b6001600160a01b038481168252831660208201526060604082018190526000906112519083018461112256fea264697066735822122027ef5c06d57a100987c5c53efe6b6e2311206f5285ba784864bd24500def6fa364736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATECOMPARTMENT = "activateCompartment";

    public static final String FUNC_ADDROLE = "addRole";

    public static final String FUNC_ADDROLEINITIATOR = "addRoleInitiator";

    public static final String FUNC_ASSIGNEDCOMPARTMENT = "assignedCompartment";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_DEACTIVATECOMPARTMENT = "deactivateCompartment";

    public static final String FUNC_DELETEROLEINITIATOR = "deleteRoleInitiator";

    public static final String FUNC_GETACTIVECOMPARTMENT = "getActiveCompartment";

    public static final String FUNC_GETROLE = "getRole";

    public static final String FUNC_HASBEENPLAYEDRUNTIME = "hasBeenPlayedRuntime";

    public static final String FUNC_ISPLAYINGROLE = "isPlayingRole";

    public static final String FUNC_REMOVEROLE = "removeRole";

    public static final String FUNC_SETCOMPARTMENT = "setCompartment";

    public static final String FUNC_SETCORE = "setCore";

    public static final String FUNC_SOCIALAGENTINITIATORFORSOCIETY = "socialAgentInitiatorForSociety";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    @Deprecated
    protected SocialAgentInitiator(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialAgentInitiator(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialAgentInitiator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialAgentInitiator(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> activateCompartment(String _compartment) {
        final Function function = new Function(
                FUNC_ACTIVATECOMPARTMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _compartment)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRole(byte[] spec, String role) {
        final Function function = new Function(
                FUNC_ADDROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec), 
                new org.web3j.abi.datatypes.Address(160, role)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRoleInitiator(String _roleName,
            String _roleAddress) {
        final Function function = new Function(
                FUNC_ADDROLEINITIATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_roleName), 
                new org.web3j.abi.datatypes.Address(160, _roleAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> assignedCompartment() {
        final Function function = new Function(FUNC_ASSIGNEDCOMPARTMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> core() {
        final Function function = new Function(FUNC_CORE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> deactivateCompartment() {
        final Function function = new Function(
                FUNC_DEACTIVATECOMPARTMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteRoleInitiator(String _roleName) {
        final Function function = new Function(
                FUNC_DELETEROLEINITIATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_roleName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getActiveCompartment() {
        final Function function = new Function(FUNC_GETACTIVECOMPARTMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getRole(byte[] spec) {
        final Function function = new Function(
                FUNC_GETROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> hasBeenPlayedRuntime(String _contractAddress,
            String _functionName) {
        final Function function = new Function(
                FUNC_HASBEENPLAYEDRUNTIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contractAddress), 
                new org.web3j.abi.datatypes.Utf8String(_functionName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isPlayingRole(byte[] spec) {
        final Function function = new Function(FUNC_ISPLAYINGROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeRole(byte[] spec) {
        final Function function = new Function(
                FUNC_REMOVEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(spec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setCompartment(String _compartmentAddresss) {
        final Function function = new Function(
                FUNC_SETCOMPARTMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _compartmentAddresss)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setCore(String _coreAddress) {
        final Function function = new Function(
                FUNC_SETCORE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _coreAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> socialAgentInitiatorForSociety(
            String _socialAgentName) {
        final Function function = new Function(
                FUNC_SOCIALAGENTINITIATORFORSOCIETY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_socialAgentName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceID) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static SocialAgentInitiator load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentInitiator(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialAgentInitiator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialAgentInitiator(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialAgentInitiator load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SocialAgentInitiator(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialAgentInitiator load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialAgentInitiator(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialAgentInitiator> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _socialAgentAddress,
            String _concreteMediatorAddress, String _societyAddress, String _componentCoreAddress,
            String _compartmentInitiator, String _utilsAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentAddress), 
                new org.web3j.abi.datatypes.Address(160, _concreteMediatorAddress), 
                new org.web3j.abi.datatypes.Address(160, _societyAddress), 
                new org.web3j.abi.datatypes.Address(160, _componentCoreAddress), 
                new org.web3j.abi.datatypes.Address(160, _compartmentInitiator), 
                new org.web3j.abi.datatypes.Address(160, _utilsAddress)));
        return deployRemoteCall(SocialAgentInitiator.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<SocialAgentInitiator> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String _socialAgentAddress, String _concreteMediatorAddress, String _societyAddress,
            String _componentCoreAddress, String _compartmentInitiator, String _utilsAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentAddress), 
                new org.web3j.abi.datatypes.Address(160, _concreteMediatorAddress), 
                new org.web3j.abi.datatypes.Address(160, _societyAddress), 
                new org.web3j.abi.datatypes.Address(160, _componentCoreAddress), 
                new org.web3j.abi.datatypes.Address(160, _compartmentInitiator), 
                new org.web3j.abi.datatypes.Address(160, _utilsAddress)));
        return deployRemoteCall(SocialAgentInitiator.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SocialAgentInitiator> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _socialAgentAddress,
            String _concreteMediatorAddress, String _societyAddress, String _componentCoreAddress,
            String _compartmentInitiator, String _utilsAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentAddress), 
                new org.web3j.abi.datatypes.Address(160, _concreteMediatorAddress), 
                new org.web3j.abi.datatypes.Address(160, _societyAddress), 
                new org.web3j.abi.datatypes.Address(160, _componentCoreAddress), 
                new org.web3j.abi.datatypes.Address(160, _compartmentInitiator), 
                new org.web3j.abi.datatypes.Address(160, _utilsAddress)));
        return deployRemoteCall(SocialAgentInitiator.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SocialAgentInitiator> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String _socialAgentAddress, String _concreteMediatorAddress, String _societyAddress,
            String _componentCoreAddress, String _compartmentInitiator, String _utilsAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _socialAgentAddress), 
                new org.web3j.abi.datatypes.Address(160, _concreteMediatorAddress), 
                new org.web3j.abi.datatypes.Address(160, _societyAddress), 
                new org.web3j.abi.datatypes.Address(160, _componentCoreAddress), 
                new org.web3j.abi.datatypes.Address(160, _compartmentInitiator), 
                new org.web3j.abi.datatypes.Address(160, _utilsAddress)));
        return deployRemoteCall(SocialAgentInitiator.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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
