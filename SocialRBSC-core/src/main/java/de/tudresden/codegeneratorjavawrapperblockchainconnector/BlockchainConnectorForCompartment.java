/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.codegeneratorjavawrapperblockchainconnector;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;


/**
 * Blockchain Connector for Compartments.
 *
 * @author Orcun Oruc
 */
public class BlockchainConnectorForCompartment {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static TypeSpec generateCompartmentRoleManagementTest() {
        ClassName compartmentInitiator = ClassName.get("de.tudresden.codegenerator.autogen", "CompartmentInitiator");
        ClassName componentCore = ClassName.get("de.tudresden.codegenerator.autogen", "ComponentCore");
        ClassName blockchainUtil = ClassName.get("de.tudresden.multiagentsystem.util", "BlockchainUtil");
        ClassName credentials = ClassName.get("org.web3j.crypto", "Credentials");
        ClassName web3j = ClassName.get("org.web3j.protocol", "Web3j");
        ClassName httpService = ClassName.get("org.web3j.protocol.http", "HttpService");
        ClassName rawTransactionManager = ClassName.get("org.web3j.tx", "RawTransactionManager");
        ClassName transactionManager = ClassName.get("org.web3j.tx", "TransactionManager");
        ClassName defaultGasProvider = ClassName.get("org.web3j.tx.gas", "DefaultGasProvider");
        ClassName staticGasProvider = ClassName.get("org.web3j.tx.gas", "StaticGasProvider");
        ClassName before = ClassName.get("org.junit", "Before");
        ClassName test = ClassName.get("org.junit", "Test");
        ClassName assertClass = ClassName.get("org.junit", "Assert");

        // Fields
        FieldSpec compartmentField = FieldSpec.builder(compartmentInitiator, "compartment", Modifier.PRIVATE).build();

        FieldSpec componentField = FieldSpec.builder(componentCore, "component", Modifier.PRIVATE).build();

        FieldSpec credentialsField = FieldSpec.builder(credentials, "credentials", Modifier.PRIVATE).build();

        FieldSpec web3jField = FieldSpec.builder(web3j, "web3j", Modifier.PRIVATE).build();

        // Setup method
        MethodSpec setUpMethod = MethodSpec
                    .methodBuilder("setUp")
                    .addAnnotation(before)
                    .addModifiers(Modifier.PUBLIC)
                    .addException(Exception.class)
                    .addStatement("web3j = $T.build(new $T(\"http://localhost:8545\"))", web3j, httpService)
                    .addStatement("$T txManager = new $T(web3j, $T.getCredentials())", transactionManager, rawTransactionManager,
                        blockchainUtil)
                    .addStatement("$T gasProvider = new $T($T.GAS_PRICE, $T.GAS_LIMIT)", staticGasProvider, staticGasProvider,
                        defaultGasProvider, defaultGasProvider)
                    .addStatement("credentials = $T.getCredentials()", blockchainUtil)
                    .addStatement("component = $T.deploy(web3j, txManager, gasProvider).send()", componentCore)
                    .addStatement("compartment = $T.deploy(web3j, txManager, gasProvider, component.getContractAddress()).send()",
                        compartmentInitiator)
                    .build();

        // Test class
        TypeSpec compartmentRoleManagementTest = TypeSpec
                    .classBuilder("CompartmentRoleManagementTest")
                    .addModifiers(Modifier.PUBLIC)
                    .addField(compartmentField)
                    .addField(componentField)
                    .addField(credentialsField)
                    .addField(web3jField)
                    .addMethod(setUpMethod)
                    .build();

        return compartmentRoleManagementTest;
    }
}
