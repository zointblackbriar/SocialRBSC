/**
 * SocialGoalWrapper.java
 *
 * TODO: Add a file description.
 *
 * Auto-added header on 2026-02-03
 */

package de.tudresden.codegenerator.javapoetgenerator;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import javax.lang.model.element.Modifier;
import java.math.BigInteger;

public class SocialGoalWrapper {

    public static boolean generateSocialAgentWrapper() {
        // Define necessary class and method specifications
        ClassName web3j = ClassName.get("org.web3j.protocol", "Web3j");
        ClassName httpService = ClassName.get("org.web3j.protocol.http", "HttpService");
        ClassName transactionManager = ClassName.get("org.web3j.tx", "TransactionManager");
        ClassName rawTransactionManager = ClassName.get("org.web3j.tx", "RawTransactionManager");
        ClassName staticGasProvider = ClassName.get("org.web3j.tx.gas", "StaticGasProvider");
        ClassName defaultGasProvider = ClassName.get("org.web3j.tx.gas", "DefaultGasProvider");
        ClassName socialGoal = ClassName.get("de.tudresden.codegenerator.autogen", "SocialGoal");
        ClassName transactionReceipt = ClassName.get("org.web3j.protocol.core.methods.response", "TransactionReceipt");
        ClassName blockchainUtil = ClassName.get("de.tudresden.multiagentsystem.util", "BlockchainUtil");
        ClassName assertClass = ClassName.get("org.junit", "Assert");
        ClassName testAnnotation = ClassName.get("org.junit", "Test");

        MethodSpec testSocialGoalMethod = MethodSpec.methodBuilder("testSocialGoal")
                .addAnnotation(testAnnotation)
                .addModifiers(Modifier.PUBLIC)
                .addException(Exception.class)
                .addCode(""
                                + "$T web3j = $T.build(new $T(\"http://localhost:8545\"));\n"
                                + "$T.assertTrue($T.printWeb3Version(web3j));\n"
                                + "$T.assertNotNull($T.getCredentials());\n"
                                + "String contractAddressSocialGoal = null;\n"
                                + "\n"
                                + "$T txManager = new $T(web3j, $T.getCredentials());\n"
                                + "$T gasProvider = new $T($T.GAS_PRICE, $T.GAS_LIMIT);\n"
                                + "\n"
                                + "try {\n"
                                + "    $T socialGoal = $T.deploy(web3j, txManager, gasProvider).send();\n"
                                + "    $T.assertNotNull(socialGoal);\n"
                                + "\n"
                                + "    contractAddressSocialGoal = socialGoal.getContractAddress();\n"
                                + "    System.out.println(contractAddressSocialGoal);\n"
                                + "    $T.assertNotNull(contractAddressSocialGoal);\n"
                                + "\n"
                                + "    $T transactionReceipt = socialGoal.assignmentOwnerAddress($T.getCredentials().getAddress()).send();\n"
                                + "    System.out.println(\"Owner address assigned, transaction hash: \" + transactionReceipt.getTransactionHash());\n"
                                + "    $T.assertNotNull(transactionReceipt);\n"
                                + "\n"
                                + "    transactionReceipt = socialGoal.addGoal(\"Test Goal\").send();\n"
                                + "    System.out.println(\"Goal added, transaction hash: \" + transactionReceipt.getTransactionHash());\n"
                                + "    $T.assertNotNull(transactionReceipt);\n"
                                + "\n"
                                + "    transactionReceipt = socialGoal.completeGoal($T.valueOf(0)).send();\n"
                                + "    System.out.println(\"Goal completed, transaction hash: \" + transactionReceipt.getTransactionHash());\n"
                                + "    $T.assertNotNull(transactionReceipt);\n"
                                + "\n"
                                + "} catch (Exception ex) {\n"
                                + "    ex.printStackTrace();\n"
                                + "}\n"
                        , web3j, web3j, httpService,
                        assertClass, blockchainUtil,
                        assertClass, blockchainUtil,
                        transactionManager, rawTransactionManager, blockchainUtil,
                        staticGasProvider, staticGasProvider, defaultGasProvider, defaultGasProvider,
                        socialGoal, socialGoal, assertClass,
                        assertClass,
                        transactionReceipt, blockchainUtil, assertClass,
                        assertClass,
                        BigInteger.class, assertClass)
                .build();

        MethodSpec testFailCompleteGoal = MethodSpec.methodBuilder("testFailCompleteGoal")
                .addAnnotation(testAnnotation)
                .addModifiers(Modifier.PUBLIC)
                .addException(Exception.class)
                .addCode(""
                                + "var web3j = $T.build(new $T(\"http://localhost:8545\"));\n"
                                + "$T.assertTrue($T.printWeb3Version(web3j));\n"
                                + "$T.assertNotNull($T.getCredentials());\n"
                                + "String contractAddressSocialGoal = null;\n"
                                + "\n"
                                + "var txManager = new $T(web3j, $T.getCredentials());\n"
                                + "var gasProvider = new $T($T.GAS_PRICE, $T.GAS_LIMIT);\n"
                                + "\n"
                                + "try {\n"
                                + "    var socialGoal = $T.deploy(web3j, txManager, gasProvider).send();\n"
                                + "    $T.assertNotNull(socialGoal);\n"
                                + "\n"
                                + "    contractAddressSocialGoal = socialGoal.getContractAddress();\n"
                                + "    System.out.println(contractAddressSocialGoal);\n"
                                + "    $T.assertNotNull(contractAddressSocialGoal);\n"
                                + "\n"
                                + "    var transactionReceipt = socialGoal.assignmentOwnerAddress($T.getCredentials().getAddress()).send();\n"
                                + "    System.out.println(\"Owner address assigned, transaction hash: \" + transactionReceipt.getTransactionHash());\n"
                                + "    $T.assertNotNull(transactionReceipt);\n"
                                + "\n"
                                + "    transactionReceipt = socialGoal.addGoal(\"Test Goal\").send();\n"
                                + "    System.out.println(\"Goal added, transaction hash: \" + transactionReceipt.getTransactionHash());\n"
                                + "    $T.assertNotNull(transactionReceipt);\n"
                                + "\n"
                                + "    transactionReceipt = socialGoal.completeGoal($T.valueOf(99)).send();\n"
                                + "    System.out.println(\"Goal completed, transaction hash: \" + transactionReceipt.getTransactionHash());\n"
                                + "    $T.assertNotNull(transactionReceipt);\n"
                                + "\n"
                                + "} catch (Exception ex) {\n"
                                + "    ex.printStackTrace();\n"
                                + "}\n"
                        , web3j, httpService,
                        assertClass, blockchainUtil,
                        assertClass, blockchainUtil,
                        rawTransactionManager, blockchainUtil,
                        staticGasProvider, defaultGasProvider, defaultGasProvider,
                        socialGoal, assertClass,
                        assertClass,
                        blockchainUtil, assertClass,
                        assertClass,
                        BigInteger.class, assertClass)
                .build();

        TypeSpec socialGoalTest = TypeSpec.classBuilder("SocialGoalTest")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(testSocialGoalMethod)
                .addMethod(testFailCompleteGoal)
                .build();

        JavaFile javaFile = JavaFile.builder("de.tudresden.javalanguagewrappertest", socialGoalTest)
                .build();

        System.out.println(javaFile.toString());

        // Print or write the generated code to a file
        try {
            javaFile.writeTo(System.out); // Code will be written to a file not to console
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
