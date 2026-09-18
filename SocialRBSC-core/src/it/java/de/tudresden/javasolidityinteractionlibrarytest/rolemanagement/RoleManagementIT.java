/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.rolemanagement;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import de.tudresden.codegenerator.autogen.CompartmentInitiator;
import de.tudresden.codegenerator.autogen.ComponentCore;
import de.tudresden.codegenerator.autogen.SampleRole1;
import de.tudresden.codegenerator.autogen.SampleRole1Creator;
import de.tudresden.codegenerator.autogen.SampleRole2;
import de.tudresden.codegenerator.autogen.SampleRole2Creator;
import de.tudresden.multiagentsystem.util.BlockchainUtil;
import de.tudresden.multiagentsystem.util.PropertiesPrivateKeyandAccounts;


/**
 * Role Management Test.
 *
 * @author Orcun Oruc
 */
public class RoleManagementIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * CONTRACT_ADDRESS_COMPARTMENT.
     */
    private static String PRIVATE_KEY;

    /**
     * CONTRACT_ADDRESS_COMPARTMENT.
     */
    private static String CONTRACT_ADDRESS_COMPARTMENT; // Give an address here

    /**
     * CONTRACT_ADDRESS_COMPONENTCORE.
     */
    private static String CONTRACT_ADDRESS_COMPONENTCORE; // Give an address here

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * String to 64 length HexString (equivalent to 32 Hex lenght).
     *
     * @Source: https://ethereum.stackexchange.com/questions/11898/how-to-convert-java-string-to-bytes32-in-java-i-am-using-web3j-solidity-wrappe
     *
     * @param   asciiValue Ascii value to convert HEX values
     *
     * @return  Hext value in string!
     */
    public static String asciiToHex(String asciiValue) {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString() + "".join("", Collections.nCopies(32 - (hex.length() / 2), "00"));
    }

    /**
     * property file testing for private key.
     */
    @Test
    public void propertyFileTest() {
        PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();

        Map<String, String> sampleCredential = null;

        try {
            sampleCredential = propertiesPrivateKeyandAccounts.getPropertyFromFile();
            PRIVATE_KEY = sampleCredential.get("private_key1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Assert.assertEquals(sampleCredential.get("private_key1"), PRIVATE_KEY);
    }

    /**
     * Compartment Assignment Correction Test.
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testRightCompartmentAssignment() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(BlockchainUtil.printWeb3Version(web3j));
        Assert.assertNotNull(BlockchainUtil.getCredentials());

        BigInteger gasLimit = BigInteger.valueOf(30000000);
        BigInteger gasPrice = BigInteger.valueOf(2000000000); // 20 Gwei

        BlockchainUtil.transferEthereum(web3j, BlockchainUtil.getCredentials());

        ComponentCore componentCore = ComponentCore.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();

        // Deploy the Comparment Contract
        CompartmentInitiator compartmentContractInitiator = CompartmentInitiator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit, componentCore.getContractAddress())
                    .send();

        // Deploy the ComponentCore Contact
        // ComponentCore componentCore = ComponentCore.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();
        SampleRole1Creator fakeRole1Creator = SampleRole1Creator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit)
                    .send();

        // Get The contract address
        CONTRACT_ADDRESS_COMPARTMENT = compartmentContractInitiator.getContractAddress();
        CONTRACT_ADDRESS_COMPONENTCORE = componentCore.getContractAddress();

        System.out.println();
        System.out.println("CONTRACT_ADDRESS_COMPARTMENT address: " + CONTRACT_ADDRESS_COMPARTMENT);
        System.out.println("CONTRACT_ADDRESS_COMPONENTCORE address: " + CONTRACT_ADDRESS_COMPONENTCORE);

        Assert.assertNotNull(CONTRACT_ADDRESS_COMPARTMENT);
        Assert.assertNotNull(CONTRACT_ADDRESS_COMPONENTCORE);

        byte[] myStringInByte1 = Numeric.hexStringToByteArray(asciiToHex("SAMPLEROLE1"));

        compartmentContractInitiator.addRoleCreator(myStringInByte1, fakeRole1Creator.getContractAddress()).send();
        compartmentContractInitiator.addRole(myStringInByte1).send();

        String fakeRoleAddress = String.valueOf(componentCore.getRole(myStringInByte1).send());

        componentCore.activateCompartment(fakeRoleAddress).send(); // Exception should be thrown
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testRoleManagementFunctionsERC165() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(BlockchainUtil.printWeb3Version(web3j));
        Assert.assertNotNull(BlockchainUtil.getCredentials());

        BigInteger gasLimit = BigInteger.valueOf(30000000);
        BigInteger gasPrice = BigInteger.valueOf(2000000000); // 20 Gwei

        BlockchainUtil.transferEthereum(web3j, BlockchainUtil.getCredentials());

        ComponentCore componentCore = ComponentCore.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();

        // Deploy the CompartmentInitiator Contract
        CompartmentInitiator compartmentContractInitiator = CompartmentInitiator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit, componentCore.getContractAddress())
                    .send();

        // Deploy the ComponentCore Contact
        // ComponentCore componentCore = ComponentCore.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();
        SampleRole1Creator fakeRole1Creator = SampleRole1Creator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit)
                    .send();
        SampleRole2Creator fakeRole2Creator = SampleRole2Creator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit)
                    .send();

        // Get The contract address
        CONTRACT_ADDRESS_COMPARTMENT = compartmentContractInitiator.getContractAddress();
        CONTRACT_ADDRESS_COMPONENTCORE = componentCore.getContractAddress();

        System.out.println();
        System.out.println("CONTRACT_ADDRESS_COMPARTMENT address: " + CONTRACT_ADDRESS_COMPARTMENT);
        System.out.println("CONTRACT_ADDRESS_COMPONENTCORE address: " + CONTRACT_ADDRESS_COMPONENTCORE);

        Assert.assertNotNull(CONTRACT_ADDRESS_COMPARTMENT);
        Assert.assertNotNull(CONTRACT_ADDRESS_COMPONENTCORE);

        byte[] myStringInByte1 = Numeric.hexStringToByteArray(asciiToHex("SAMPLEROLE1"));
        byte[] myStringInByte2 = Numeric.hexStringToByteArray(asciiToHex("SAMPLEROLE2"));

        // Sample role byte conversion
        Bytes32 byteObject1 = new Bytes32(myStringInByte1);
        Bytes32 byteObject2 = new Bytes32(myStringInByte2);
        compartmentContractInitiator.addRoleCreator(byteObject1.getValue(), fakeRole1Creator.getContractAddress()).send();
        compartmentContractInitiator.addRoleCreator(byteObject2.getValue(), fakeRole2Creator.getContractAddress()).send();

        // Adding roles for the test purposes
        compartmentContractInitiator.addRole(byteObject1.getValue()).send();
        compartmentContractInitiator.addRole(byteObject2.getValue()).send();
        componentCore.activateCompartment(CONTRACT_ADDRESS_COMPARTMENT).send();

        // Get first address of the role
        String addressGettingRole1 = compartmentContractInitiator.getRole(byteObject1.getValue()).send();
        System.out.println("compartmentContractInitiator.getRole(byteObject1.getValue()).toString() address: " +
                addressGettingRole1);

        String addressGettingRole2 = compartmentContractInitiator.getRole(byteObject2.getValue()).send();
        System.out.println("compartmentContractInitiator.getRole(byteObject2.getValue()).toString() address: " +
                addressGettingRole2);

        Assert.assertNotEquals(addressGettingRole1, "0x0000000000000000000000000000000000000000"); // null address in EVM
        Assert.assertNotEquals(addressGettingRole2, "0x0000000000000000000000000000000000000000"); // null address in EVM
        Assert.assertNotEquals(addressGettingRole1, addressGettingRole2); // Different roles have been added to a different
                                                                          // addresses

        // Has been played - check with isPlayingRole() function
        System.out.println(componentCore.isComponentRole().send());
        componentCore.addRole(byteObject1.getValue(), addressGettingRole1).send();

        boolean resultBoolean = componentCore.isPlayingRole(byteObject1.getValue()).send();
        Assert.assertTrue(resultBoolean);

        componentCore.addRole(byteObject2.getValue(), addressGettingRole2).send();
        resultBoolean = componentCore.isPlayingRole(byteObject2.getValue()).send();
        Assert.assertTrue(resultBoolean);

        SampleRole1 role1Test = SampleRole1.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();
        componentCore.addRole(byteObject1.getValue(), role1Test.getContractAddress());

        role1Test.roleFunction().send();

        String result = String.valueOf(role1Test.message().send());
        System.out.println(result);
        Assert.assertEquals(result, "roleFunction test value");

        // // Remove test for the role
        // // isPlayingRole should throw false assertion
        // // componentCore.removeRole(byteObject1.getValue()).send();
        // // resultBoolean = componentCore.isPlayingRole(byteObject1.getValue()).send();
        // Assert.assertFalse(resultBoolean);
    }

    /**
     * Type safety test for role.
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testTypeSafetyForRoles() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        Assert.assertTrue(BlockchainUtil.printWeb3Version(web3j));
        Assert.assertNotNull(BlockchainUtil.getCredentials());

        BigInteger gasLimit = BigInteger.valueOf(30000000);
        BigInteger gasPrice = BigInteger.valueOf(2000000000); // 20 Gwei

        BlockchainUtil.transferEthereum(web3j, BlockchainUtil.getCredentials());

        ComponentCore componentCore = ComponentCore.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();

        // Deploy the CompartmentInitiator Contract
        CompartmentInitiator compartmentContractInitiator = CompartmentInitiator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit, componentCore.getContractAddress())
                    .send();

        // Deploy the ComponentCore Contact
        // ComponentCore componentCore = ComponentCore.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();
        SampleRole1Creator sampleRole1Creator = SampleRole1Creator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit)
                    .send();
        SampleRole2Creator sampleRole2Creator = SampleRole2Creator
                    .deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit)
                    .send();
        SampleRole1 sampleRole1 = SampleRole1.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();
        SampleRole2 sampleRole2 = SampleRole2.deploy(web3j, BlockchainUtil.getCredentials(), gasPrice, gasLimit).send();

        // Get The contract address
        CONTRACT_ADDRESS_COMPARTMENT = compartmentContractInitiator.getContractAddress();
        CONTRACT_ADDRESS_COMPONENTCORE = componentCore.getContractAddress();

        System.out.println();
        System.out.println("CONTRACT_ADDRESS_COMPARTMENT address: " + CONTRACT_ADDRESS_COMPARTMENT);
        System.out.println("CONTRACT_ADDRESS_COMPONENTCORE address: " + CONTRACT_ADDRESS_COMPONENTCORE);

        Assert.assertNotNull(CONTRACT_ADDRESS_COMPARTMENT);
        Assert.assertNotNull(CONTRACT_ADDRESS_COMPONENTCORE);

        byte[] myStringInByte1 = Numeric.hexStringToByteArray(asciiToHex("SAMPLEROLE1"));
        byte[] myStringInByte2 = Numeric.hexStringToByteArray(asciiToHex("SAMPLEROLE2"));

        Bytes32 byteObject1 = new Bytes32(myStringInByte1);
        Bytes32 byteObject2 = new Bytes32(myStringInByte2);
        compartmentContractInitiator.addRoleCreator(byteObject1.getValue(), sampleRole1Creator.getContractAddress()).send();
        compartmentContractInitiator.addRoleCreator(byteObject2.getValue(), sampleRole2Creator.getContractAddress()).send();

        sampleRole1.setCore(sampleRole2.getContractAddress()).send();
        sampleRole1.setCore(sampleRole1.getContractAddress()).send(); // this gives a correct result

        // Set compartment with null address
        sampleRole1.setCompartment("0x0000000000000000000000000000000000000000").send(); // this gives a correct result
    }
}
