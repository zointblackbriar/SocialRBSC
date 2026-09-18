/**
 * LinkedListWrapperJavaPoet.java
 *
 * TODO: Add a file description.
 *
 * Auto-added header on 2026-02-03
 */

package de.tudresden.codegenerator.javapoetgenerator;
import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public class LinkedListWrapperJavaPoet {

    public static boolean linkedListWrapperJavaPoet() {
        // Define class names for import
        ClassName linkedList = ClassName.get("de.tudresden.codegenerator.autogen", "LinkedList");
        ClassName blockchainUtil = ClassName.get("de.tudresden.multiagentsystem.util", "BlockchainUtil");
        ClassName testAnnotation = ClassName.get("org.junit", "Test");
        ClassName web3j = ClassName.get("org.web3j.protocol", "Web3j");
        ClassName httpService = ClassName.get("org.web3j.protocol.http", "HttpService");
        ClassName transactionManager = ClassName.get("org.web3j.tx", "TransactionManager");
        ClassName rawTransactionManager = ClassName.get("org.web3j.tx", "RawTransactionManager");
        ClassName defaultGasProvider = ClassName.get("org.web3j.tx.gas", "DefaultGasProvider");
        ClassName credentials = ClassName.get("org.web3j.crypto", "Credentials");
        ClassName transactionReceipt = ClassName.get("org.web3j.protocol.core.methods.response", "TransactionReceipt");

        // Define logger field
        TypeSpec linkedListTest = TypeSpec.classBuilder("LinkedListTest")
                .addModifiers(Modifier.PUBLIC)
                // Define the testLinkedListFunctions method
                .addMethod(MethodSpec.methodBuilder("testLinkedListFunctions")
                        .addAnnotation(testAnnotation)
                        .addModifiers(Modifier.PUBLIC)
                        .addException(Exception.class)
                        .addCode(""
                                        + "$T web3j = $T.build(new $T(\"http://localhost:8545\"));\n"
                                        + "$T transactionManager = new $T(web3j, $T.getCredentials());\n"
                                        + "$T gasProvider = new $T();\n"
                                        + "$T credentials = $T.getCredentials();\n\n",
                                web3j, web3j, httpService,
                                transactionManager, rawTransactionManager, blockchainUtil,
                                defaultGasProvider, defaultGasProvider, credentials, blockchainUtil
                        )
                        .build())
                .build();

        // Create JavaFile instance
        JavaFile javaFile = JavaFile.builder("de.tudresden.javalanguagewrappertest", linkedListTest)
                .build();

        // Print or write the generated code to a file
        try {
            javaFile.writeTo(System.out);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
