package de.tudresden.codegenerator.autogen;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes4;
import org.web3j.abi.datatypes.reflection.Parameterized;
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
public class DeepRolePlayer extends Contract {
    public static final String BINARY = "60806040526040516107b03803806107b08339810160408190526100229161007c565b6100358161003b60201b6100c91760201c565b506100ac565b7fc8fcad8db84d3cc18b4c41d551ea0ee66dd599cde068d998e57d5e09332c131f80546001600160a01b0319166001600160a01b0392909216919091179055565b60006020828403121561008e57600080fd5b81516001600160a01b03811681146100a557600080fd5b9392505050565b6106f5806100bb6000396000f3fe6080604052600436106100225760003560e01c8063659078311461009a57610029565b3661002957005b60007fc8fcad8db84d3cc18b4c41d551ea0ee66dd599cde068d998e57d5e09332c131c600080356001600160e01b0319168152602082905260408120549192506001600160a01b039091169036908037600080366000845af43d6000803e808015610093573d6000f35b3d6000fd5b005b3480156100a657600080fd5b506100986100b536600461051f565b6100bd61010a565b6100c681610195565b50565b7fc8fcad8db84d3cc18b4c41d551ea0ee66dd599cde068d998e57d5e09332c131f80546001600160a01b0319166001600160a01b0392909216919091179055565b7fc8fcad8db84d3cc18b4c41d551ea0ee66dd599cde068d998e57d5e09332c131f546001600160a01b031633146101935760405162461bcd60e51b815260206004820152602260248201527f4c69624469616d6f6e643a204d75737420626520636f6e7472616374206f776e60448201526132b960f11b60648201526084015b60405180910390fd5b565b60006101a46020830183610561565b905060006101b5602084018461058a565b80806020026020016040519081016040528093929190818152602001838360200280828437600092019190915250508251929350505061024b5760405162461bcd60e51b815260206004820152602b60248201527f4c69624469616d6f6e644375743a204e6f2073656c6563746f727320696e206660448201526a1858d95d081d1bc818dd5d60aa1b606482015260840161018a565b6001600160a01b0382166102b65760405162461bcd60e51b815260206004820152602c60248201527f4c69624469616d6f6e644375743a204164642066616365742063616e2774206260448201526b65206164647265737328302960a01b606482015260840161018a565b6001600160a01b03821660009081527fc8fcad8db84d3cc18b4c41d551ea0ee66dd599cde068d998e57d5e09332c131d60205260408120547fc8fcad8db84d3cc18b4c41d551ea0ee66dd599cde068d998e57d5e09332c131c916001600160601b0382169003610389576103428460405180606001604052806024815260200161069c602491396104f8565b6002820180546001600160a01b0386166000818152600180870160209081526040832082018590559084018555938152929092200180546001600160a01b03191690911790555b60005b83518110156104f05760008482815181106103a9576103a96105db565b6020908102919091018101516001600160e01b031981166000908152918690526040909120549091506001600160a01b031680156104475760405162461bcd60e51b815260206004820152603560248201527f4c69624469616d6f6e644375743a2043616e2774206164642066756e6374696f6044820152746e207468617420616c72656164792065786973747360581b606482015260840161018a565b6001600160e01b031982166000908152602086815260408083206001600160a01b0319600160a01b6001600160601b038a1602166001600160a01b038c1690811790915583526001808901835290832080549182018155835291206008820401805463ffffffff60079093166004026101000a928302191660e085901c92909202919091179055836104d881610607565b945050505080806104e89061062d565b91505061038c565b505050505050565b813b81816105195760405162461bcd60e51b815260040161018a9190610646565b50505050565b60006020828403121561053157600080fd5b813567ffffffffffffffff81111561054857600080fd5b82016040818503121561055a57600080fd5b9392505050565b60006020828403121561057357600080fd5b81356001600160a01b038116811461055a57600080fd5b6000808335601e198436030181126105a157600080fd5b83018035915067ffffffffffffffff8211156105bc57600080fd5b6020019150600581901b36038213156105d457600080fd5b9250929050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b60006001600160601b03808316818103610623576106236105f1565b6001019392505050565b60006001820161063f5761063f6105f1565b5060010190565b600060208083528351808285015260005b8181101561067357858101830151858201604001528201610657565b81811115610685576000604083870101525b50601f01601f191692909201604001939250505056fe4c69624469616d6f6e644375743a204e657720666163657420686173206e6f20636f6465a2646970667358221220e34f5b642a39d15d4300d5c8d82699602a9ee92511717fd74571b8ff5d009b4664736f6c634300080d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DIAMONDCUT = "diamondCut";

    @Deprecated
    protected DeepRolePlayer(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DeepRolePlayer(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DeepRolePlayer(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DeepRolePlayer(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> diamondCut(FacetCut _diamondCut) {
        final Function function = new Function(
                FUNC_DIAMONDCUT, 
                Arrays.<Type>asList(_diamondCut), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DeepRolePlayer load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRolePlayer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DeepRolePlayer load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DeepRolePlayer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DeepRolePlayer load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DeepRolePlayer(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DeepRolePlayer load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DeepRolePlayer(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DeepRolePlayer> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, BigInteger initialWeiValue,
            String _contractOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contractOwner)));
        return deployRemoteCall(DeepRolePlayer.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<DeepRolePlayer> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            BigInteger initialWeiValue, String _contractOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contractOwner)));
        return deployRemoteCall(DeepRolePlayer.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<DeepRolePlayer> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue,
            String _contractOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contractOwner)));
        return deployRemoteCall(DeepRolePlayer.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<DeepRolePlayer> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            BigInteger initialWeiValue, String _contractOwner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contractOwner)));
        return deployRemoteCall(DeepRolePlayer.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor, initialWeiValue);
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

    public static class FacetCut extends DynamicStruct {
        public String facetAddress;

        public List<byte[]> functionSelectors;

        public FacetCut(String facetAddress, List<byte[]> functionSelectors) {
            super(new org.web3j.abi.datatypes.Address(160, facetAddress), 
                    new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes4>(
                            org.web3j.abi.datatypes.generated.Bytes4.class,
                            org.web3j.abi.Utils.typeMap(functionSelectors, org.web3j.abi.datatypes.generated.Bytes4.class)));
            this.facetAddress = facetAddress;
            this.functionSelectors = functionSelectors;
        }

        public FacetCut(Address facetAddress,
                @Parameterized(type = Bytes4.class) DynamicArray<Bytes4> functionSelectors) {
            super(facetAddress, functionSelectors);
            this.facetAddress = facetAddress.getValue();
            this.functionSelectors = functionSelectors.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
        }
    }
}
