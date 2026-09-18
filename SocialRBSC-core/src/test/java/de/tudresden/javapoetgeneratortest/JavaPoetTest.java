/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javapoetgeneratortest;

import com.squareup.javapoet.TypeSpec;

import de.tudresden.codegeneratorjavawrapperblockchainconnector.BlockchainConnectorForCompartment;

import de.tudresden.multiagentsystem.util.Util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class JavaPoetTest {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    @Test
    public void testGenerateJavaWrapperBlockchainConnection() throws IOException {
        Path generatedJavaPath = Paths.get("generatedsmartcontract", "javafiles", "CompartmentJavaConnection");
        TypeSpec blockchainConnectorTypeSpec = BlockchainConnectorForCompartment.generateCompartmentRoleManagementTest();
        assertNotNull(blockchainConnectorTypeSpec);
        System.out.println(blockchainConnectorTypeSpec);

        Util util = new Util();
        util.saveSourceCode2File(generatedJavaPath + ".java", blockchainConnectorTypeSpec.toString());

        String content = Util.readACompiledFile(generatedJavaPath + ".java");
        assertNotNull(content);
    }
}
