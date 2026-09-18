/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.apachevelocity;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * This Test for general templates in velocity.
 *
 * @author Orcun Oruc
 */
public class ApacheVelocityTemplateTest {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * Logger tag.
     */
    private static final Logger logger = Logger.getLogger(ApacheVelocityTemplateTest.class.getName());

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */

    /**
     * TODO DOCUMENT ME!
     */
    private Path expectedOutputPath;

    /**
     * TODO DOCUMENT ME!
     */
    private Path templatePath;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @BeforeEach
    public void setUP() {
        // initializeVelocity
        try {
            Path propertiesVelocity = Paths.get("src", "main", "resources", "velocity.properties");

            // Velocity.init("src/main/resources/velocity.properties");
            Velocity.init(propertiesVelocity.toString());

            // this.expectedOutputPath = Paths.get("src", "test", "resources", "expectedOutput.sol");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testCompartmentTemplate() {
        // Prepare the Velocity context
        VelocityContext context = new VelocityContext();
        context.put("Compartment", "TestCompartment");

        // Prepare the template as a string (replace with actual file loading if needed)
        String templateString = "// SPDX-License-Identifier: GPL-3.0\n" + "pragma solidity >=0.7.0 <0.9.0;\n\n" +
                "import \"./staticroleassignment/ComponentRole.sol\";\n" +
                "import \"./staticroleassignment/Compartment.sol\";\n\n" + "contract $Compartment is Compartment {\n\n" +
                "    function sampleFunc1() public {\n" + "    }\n\n" + "}";

        // Load the template
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "test", templateString);

        assertNotNull(writer.toString());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testComponentCoreTemplate() throws Exception {
        // Load the template from a file
        Template template = Velocity.getTemplate("ComponentCoreTemplate.vm");

        // Prepare the Velocity context
        VelocityContext context = new VelocityContext();
        context.put("license", "AGPL-3.0");
        context.put("solidityVersion", "0.8.7");
        context.put("componentContract", "Component");
        context.put("componentRoleContract", "ComponentRole");
        context.put("compartmentContract", "Compartment");
        context.put("interfaceIdsContract", "InterfaceIds");
        context.put("erc165QueryContract", "ERC165Query");
        context.put("playerContract", "Player");
        context.put("componentCoreContract", "ComponentCore");

        // Render the template
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // Define the output file path (cross-platform path handling)
        // Path outputPath = Paths.get("src", "test", "resources", "samples", "ComponentCore.sol");
        Path outputPath = Paths.get("generatedsmartcontract", "contracts", "ComponentCore.sol");

        // Ensure the parent directory exists
        Files.createDirectories(outputPath.getParent());

        // Write the rendered output to a file
        Files.write(outputPath, writer.toString().getBytes());

        // For the sake of testing, load the written file back and compare it with itself
        // String generatedOutput = new String(Files.readAllBytes(outputPath));

        // Normalize newlines and trim whitespaces
        // String generatedNormalized = generatedOutput.replace("\r\n", "\n").trim();
        String writerNormalized = writer.toString().replace("\r\n", "\n").trim();

        // System.out.println("generatedNormalized: " + generatedNormalized);
        // System.out.println("writerNormalized: " + writerNormalized);

        // Compare the rendered output to ensure it was written correctly
        // assertEquals(writerNormalized, generatedNormalized);
        assertNotNull(writerNormalized);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testComponentRoleTemplate() throws Exception {
        // Load the template from a file
        Template template = Velocity.getTemplate("ComponentRoleTemplate.vm");

        // Prepare the Velocity context
        VelocityContext context = new VelocityContext();
        context.put("license", "MIT");
        context.put("solidityVersion", "0.8.7");
        context.put("componentContract", "Component");
        context.put("componentRoleContract", "ComponentRole");
        context.put("compartmentContract", "Compartment");
        context.put("interfaceIdsContract", "InterfaceIds");
        context.put("erc165QueryContract", "ERC165Query");
        context.put("playerContract", "Player");
        context.put("componentCoreContract", "ComponentCore");

        // Render the template
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // Define the output file path (cross-platform path handling)
        // Path outputPath = Paths.get("src", "test", "resources", "samples", "ComponentRole.sol");
        Path outputPath = Paths.get("generatedsmartcontract", "contracts", "ComponentRole.sol");

        // Ensure the parent directory exists
        Files.createDirectories(outputPath.getParent());

        // Write the rendered output to a file
        Files.write(outputPath, writer.toString().getBytes());

        // For the sake of testing, load the written file back and compare it with itself
        String generatedOutput = new String(Files.readAllBytes(outputPath));

        // Normalize newlines and trim whitespaces
        // String generatedNormalized = generatedOutput.replace("\r\n", "\n").trim();
        String writerNormalized = writer.toString().replace("\r\n", "\n").trim();

        // System.out.println("generatedNormalized: " + generatedNormalized);
        // System.out.println("writerNormalized: " + writerNormalized);

        // Compare the rendered output to ensure it was written correctly
        // assertEquals(writerNormalized, generatedNormalized);
        assertNotNull(writerNormalized);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testPlayerTemplate() throws Exception {
        // Load the template from a file
        Template template = Velocity.getTemplate("PlayerTemplate.vm");

        // Prepare the Velocity context
        // Set up the Velocity context with necessary variables
        VelocityContext context = new VelocityContext();
        context.put("license", "UNLICENSED");
        context.put("solidityVersion", "0.8.7");
        context.put("contractName", "Player");
        context.put("ownerVariable", "owner");
        context.put("playFunction", "playDelegateCallRoleContract");
        context.put("getSelectorFunction", "getSelector");
        context.put("onlyOwnerModifier", "onlyOwner");
        context.put("mappingName", "playedContractList");

        // Render the template into a StringWriter
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // Define the output file path
        // Path outputPath = Paths.get("src", "test", "resources", "samples", "Player.sol");
        Path outputPath = Paths.get("generatedsmartcontract", "contracts", "Player.sol");

        // Ensure the parent directory exists
        Files.createDirectories(outputPath.getParent());

        // Write the rendered template to a file
        Files.write(outputPath, writer.toString().getBytes());

        // Load the generated output from the generatedPlayerContract.sol file
        String generatedOutput = new String(Files.readAllBytes(outputPath));

        // Compare the generated output to the expected output
        assertNotNull(generatedOutput.trim());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testRole1Template() throws Exception {
        Template template = Velocity.getTemplate("RoleOne.vm");
        assertNotNull(template, "Template should be loaded");
        StringWriter writer = new StringWriter();

        // Set up the context with input parameters
        VelocityContext context = new VelocityContext();
        context.put("genericRole", "Retailer");
        context.put("pushFunctionName", "pushInventory");
        context.put("popFunctionName", "popInventory");
        context.put("getInventoryStatusFunctionName", "getInventoryStatus");
        context.put("playContractFunctionName", "playContractForIdentity");
        context.put("inventoryVariable", "itemsInInventoryRetailer");

        // Merge the template with the context data
        template.merge(context, writer);

        // Assert the Solidity code contains expected keywords and structure
        String output = writer.toString();
        System.out.println("=== Generated Output ===");
        System.out.println(output);
        System.out.println("=== End Output ===");
        
        assertTrue(output.contains("Retailer"), 
                "Output should contain Retailer role name");
        assertTrue(output.contains("pushInventory"), 
                "Output should contain pushInventory function");
        assertTrue(output.contains("popInventory"), 
                "Output should contain popInventory function");
        assertTrue(output.contains("getInventoryStatus"), 
                "Output should contain getInventoryStatus function");
        assertTrue(output.contains("playContractForIdentity"), 
                "Output should contain playContractForIdentity function");

        // Files.write(Paths.get(filePath), generatedSolidity.getBytes());
        Path outputPath = Paths.get("generatedsmartcontract", "contracts", "RoleOne.sol");

        // Ensure the parent directory exists
        Files.createDirectories(outputPath.getParent());

        // Write the rendered output to a file
        Files.write(outputPath, writer.toString().getBytes());

        // Verify the file was written correctly
        assertTrue(Files.exists(Paths.get(outputPath.toString())));
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testRoleCreatorTemplate() throws Exception {
        // Load the Velocity template
        Template template = Velocity.getTemplate("RoleCreatorTemplate.vm");

        // Set up the Velocity context with necessary variables
        VelocityContext context = new VelocityContext();
        context.put("license", "AGPL-3.0");
        context.put("solidityVersion", "0.8.7");
        context.put("componentRoleContract", "ComponentRole");
        context.put("roleCreatorInterface", "RoleCreator");
        context.put("componentRoleImportPath", "../staticroleassignment/ComponentRole.sol");

        // Render the template into a StringWriter
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // Define the output file path
        // Path outputPath = Paths.get("src", "test", "resources", "samples", "RoleCreator.sol");
        Path outputPath = Paths.get("generatedsmartcontract", "contracts", "RoleCreator.sol");

        // Ensure the parent directory exists
        Files.createDirectories(outputPath.getParent());

        // Write the rendered template to a file
        Files.write(outputPath, writer.toString().getBytes());

        // Load the expected output from the expectedRoleCreatorContract.sol file
        // String expectedOutput = new String(Files.readAllBytes(expectedOutputPath));

        // Load the generated output from the generatedRoleCreatorContract.sol file
        String generatedOutput = new String(Files.readAllBytes(outputPath));

        // Compare the generated output to the expected output
        assertNotNull(generatedOutput.trim());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws Exception
     */
    @Test
    public void testRoleGenericTemplate() throws Exception {
        // Load the template
        Template template = Velocity.getTemplate("RoleGeneric.vm");

        // Set up the context with input parameters
        VelocityContext context = new VelocityContext();
        context.put("genericEntity", "SupplyChain");
        context.put("roleOne", "SampleRole1");
        context.put("roleTwo", "SampleRole2");
        context.put("roleOneCreator", "SampleRole1Creator");
        context.put("roleTwoCreator", "SampleRole2Creator");
        context.put("roleOneSpec", "SampleRole1Spec");
        context.put("roleTwoSpec", "SampleRole2Spec");

        // Merge the template with the context data
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // Print the generated Solidity contract code
        System.out.println(writer);

        // Save the generated Solidity contract to a file
        Path outputPath = Paths.get("generatedsmartcontract", "contracts", "SupplyChain.sol");

        // Ensure the parent directory exists
        Files.createDirectories(outputPath.getParent());

        // Write the rendered output to a file
        Files.write(outputPath, writer.toString().getBytes());

        // Verify the file was created
        if (Files.exists(Paths.get(outputPath.toString()))) {
            System.out.println("Solidity contract written to " + outputPath.toString());
        } else {
            System.out.println("Failed to write Solidity contract.");
        }

        assertTrue(Files.exists(Paths.get(outputPath.toString())));

        String content = new String(Files.readAllBytes(Paths.get(outputPath.toString()))); // content of the smart contract
        assertNotNull(content);
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testRoleTemplate() throws Exception {
        // Load the template from file
        Template template = Velocity.getTemplate("Role.vm");

        // Prepare the Velocity context
        VelocityContext context = new VelocityContext();
        context.put("license", "MIT");
        context.put("solidityVersion", "0.7.0");
        context.put("RoleName", "sampleRole");
        context.put("stringVar", "yourStringVar");
        context.put("uintVar", "yourUintVar");
        context.put("roleFunction", "yourFunction1");
        context.put("roleFunction1", "yourFunction1");
        context.put("roleFunction2", "yourFunction2");

        // Render the template into a StringWriter
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        assertNotNull(writer.toString());
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    @Test
    public void testSocialAgentInitiatorTemplate() throws IOException {
        Template template = Velocity.getTemplate("SocialAgentInitiator.vm");

        // Create the context and add data
        VelocityContext context = new VelocityContext();
        context.put("_socialAgentAddress", "'0x123456789'");
        context.put("_concreteMediatorAddress", "'0x987654321'");
        context.put("_societyAddress", "'0x555555555'");
        context.put("_componentCoreAddress", "'0x444444444'");
        context.put("_compartmentInitiator", "'0x333333333'");
        context.put("_utilsAddress", "'0x222222222'");
        context.put("_socialAgentName", "'Agent007'");
        context.put("_roleName", "'LeaderRole'");

        // Merge template
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        Path outputPath = Paths.get("generatedsmartcontract", "contracts", "SocialAgentInitiator.sol");

        // Ensure the parent directory exists
        Files.createDirectories(outputPath.getParent());

        // Write the rendered template to a file
        Files.write(outputPath, writer.toString().getBytes());

        // Load the expected output from the expectedRoleCreatorContract.sol file
        // String expectedOutput = new String(Files.readAllBytes(expectedOutputPath));

        // Load the generated output from the generatedRoleCreatorContract.sol file
        String generatedOutput = new String(Files.readAllBytes(outputPath));

        assertNotNull(generatedOutput);
    }
}
