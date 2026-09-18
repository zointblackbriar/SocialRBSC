/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program.
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.velocitytemplatetest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Integration tests for Apache Velocity templates with edge cases.
 * Tests ensure templates align with Solidity syntax and Velocity best practices.
 *
 * @author Velocity Template Test Suite
 */
public class VelocityTemplateEdgeCasesIT {

    private static final Logger logger = Logger.getLogger(VelocityTemplateEdgeCasesIT.class.getName());
    private static final String TEMPLATES_PATH = "velocitytemplates";
    private static VelocityEngine velocityEngine;

    /**
     * Initialize the Velocity engine before all tests.
     */
    @BeforeClass
    public static void setUpVelocityEngine() {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        velocityEngine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, TEMPLATES_PATH);
        velocityEngine.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        velocityEngine.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
        velocityEngine.init();
        logger.info("Velocity engine initialized with path: " + TEMPLATES_PATH);
    }

    // ==================== EDGE CASE TESTS ====================

    /**
     * Test RoleCreatorTemplate with null license variable.
     * Edge case: Empty or null license should not break rendering.
     */
    @Test
    public void testRoleCreatorTemplateNullLicense() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("license", null);
        context.put("solidityVersion", "0.8.7");
        context.put("componentRoleContract", "ComponentRole");
        context.put("roleCreatorInterface", "RoleCreator");
        context.put("componentRoleImportPath", "../staticroleassignment/ComponentRole.sol");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("RoleCreatorTemplate.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain SPDX identifier", output, containsString("SPDX-License-Identifier"));
        assertThat("Output should contain pragma", output, containsString("pragma solidity"));
        logger.info("RoleCreatorTemplate with null license rendered successfully");
    }

    /**
     * Test ComponentTemplate with special characters in variable names.
     * Edge case: Variables with underscores and numbers.
     */
    @Test
    public void testComponentTemplateSpecialCharacters() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("license", "AGPL-3.0");
        context.put("solidityVersion", "0.8.7");
        context.put("interfaceName", "Component_v2");
        context.put("baseContract", "ERC165Query");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("ComponentTemplate.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertTrue("Output should contain interface declaration", output.contains("interface"));
        assertThat("Output should contain ERC165Query import", output, containsString("ERC165"));
        logger.info("ComponentTemplate with special characters rendered successfully");
    }

    /**
     * Test Compartment template with empty Compartment name.
     * Edge case: Empty strings should be handled gracefully.
     */
    @Test
    public void testCompartmentTemplateEmptyName() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("Compartment", "");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("Compartment.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain SPDX license", output, containsString("MIT"));
        assertThat("Output should contain pragma solidity", output, containsString("pragma solidity"));
        logger.info("Compartment template with empty name rendered successfully");
    }

    /**
     * Test SocialAgentTemplate variable interpolation fix (typo correction).
     * Edge case: Ensure _societyAddress is correctly interpolated (not _societyAddresss).
     */
    @Test
    public void testSocialAgentTemplateVariableInterpolation() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("SocialAgentContract", "TestSocialAgent");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("SocialAgentTemplate.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        // Should NOT contain the typo
        assertThat("Output should not contain typo _societyAddresss", output, not(containsString("_societyAddresss")));
        // Should contain correct variable
        assertThat("Output should contain correct _societyAddress", output, containsString("_societyAddress"));
        logger.info("SocialAgentTemplate variable interpolation verified");
    }

    /**
     * Test RoleTemplate with version boundaries.
     * Edge case: Test Solidity version ranges.
     */
    @Test
    public void testRoleTemplateSolidityVersions() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("license", "MIT");
        context.put("solidityVersion", "0.7.0");
        context.put("RoleName", "TestRole");
        context.put("stringVar", "testVar");
        context.put("uintVar", "testValue");
        context.put("roleFunction", "executeRole");
        context.put("roleFunction1", "executeRole");
        context.put("roleFunction2", "validateRole");
        context.put("roleNameCreator", "TestRoleCreator");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("Role.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain version range", output, containsString(">=0.7.0 <0.9.0"));
        // The actual template uses $RoleName and $stringVar which may not be expanded if not properly set
        assertTrue("Output should contain contract structure", output.contains("contract"));
        assertThat("Output should contain the role creator contract name", output, containsString("contract TestRoleCreator"));
        assertThat("Template should instantiate correct Role type in creator", output, containsString("return new TestRole()"));
        logger.info("Role template with Solidity versions rendered successfully");
    }

    /**
     * Test ComponentCoreTemplate with undefined context variables.
     * Edge case: Missing variables should not break template rendering.
     */
    @Test
    public void testComponentCoreTemplateUndefinedVariables() throws IOException {
        VelocityContext context = new VelocityContext();
        // Not setting all variables - testing graceful degradation
        context.put("license", "AGPL-3.0");
        context.put("solidityVersion", "0.8.7");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("ComponentCoreTemplate.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain license", output, containsString("AGPL-3.0"));
        assertThat("Output should contain pragma", output, containsString("pragma solidity"));
        logger.info("ComponentCoreTemplate with undefined variables rendered successfully");
    }

    /**
     * Test PlayerTemplate with UNLICENSED license identifier.
     * Edge case: Non-standard license identifiers.
     */
    @Test
    public void testPlayerTemplateNonStandardLicense() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("license", "UNLICENSED");
        context.put("solidityVersion", "0.8.7");
        context.put("contractName", "PlayerTest");
        context.put("ownerVariable", "owner");
        context.put("playFunction", "play");
        context.put("getSelectorFunction", "getSelector");
        context.put("onlyOwnerModifier", "onlyOwner");
        context.put("mappingName", "playedContracts");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("PlayerTemplate.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain UNLICENSED license", output, containsString("UNLICENSED"));
        assertTrue("Output should contain contract keyword", output.contains("contract"));
        logger.info("PlayerTemplate with UNLICENSED license rendered successfully");
    }

    /**
     * Test RoleGeneric with input context objects.
     * Edge case: Complex context objects with nested properties.
     */
    @Test
    public void testRoleGenericComplexContext() throws IOException {
        VelocityContext context = new VelocityContext();
        // Simulating input object
        Map<String, String> input = new HashMap<>();
        input.put("roleOneSpec", "SUPPLIER_ROLE");
        input.put("roleTwoSpec", "BUYER_ROLE");
        context.put("input", input);
        context.put("solidityVersion", "0.8.7");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("RoleGeneric.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain imports", output, containsString("import"));
        assertThat("Output should contain pragma", output, containsString("pragma solidity"));
        logger.info("RoleGeneric with complex context rendered successfully");
    }

    /**
     * Test ConcreteMediatorTemplate with mapping and boolean variables.
     * Edge case: Testing Solidity-specific variable types in templates.
     */
    @Test
    public void testConcreteMediatorTemplateDataTypes() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("pragmaVersion", ">=0.8.7");
        context.put("mediatorContract", "Mediator");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("ConcreteMedaitorTemplate.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain pragma", output, containsString("pragma solidity"));
        assertThat("Output should contain mapping declaration", output, containsString("mapping"));
        logger.info("ConcreteMediatorTemplate with Solidity data types rendered successfully");
    }

    /**
     * Test CompartmentInitiatorTemplate with duplicate imports.
     * Edge case: Ensure imports are properly merged without duplication.
     */
    @Test
    public void testCompartmentInitiatorTemplateImports() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("license", "MIT");
        context.put("solidityVersion", "0.8.7");
        context.put("compartmentContract", "Compartment");
        context.put("compartmentInitiator", "CompartmentInitiator");
        context.put("componentRoleContract", "ComponentRole");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("CompartmentInitiatorTemplate.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        int componentCoreImportCount = countOccurrences(output, "ComponentCore.sol");
        assertTrue("ComponentCore.sol should be imported only once", componentCoreImportCount <= 1);
        logger.info("CompartmentInitiatorTemplate imports verified");
    }

    /**
     * Test SocialAgentAddRole and SocialAgentRemoveRole for correct naming.
     * Edge case: Ensure templates have distinct contract names.
     */
    @Test
    public void testSocialAgentRoleTemplateNaming() throws IOException {
        VelocityContext contextAddRole = new VelocityContext();
        contextAddRole.put("SocialAgentAddRole", "SocialAgentAddRoleTest");

        StringWriter writerAddRole = new StringWriter();
        Template templateAddRole = velocityEngine.getTemplate("SocialAgentAddRole.vm");
        templateAddRole.merge(contextAddRole, writerAddRole);

        String outputAddRole = writerAddRole.toString();
        assertThat("SocialAgentAddRole should contain 'AddRole'", outputAddRole, containsString("AddRole"));

        VelocityContext contextRemoveRole = new VelocityContext();
        contextRemoveRole.put("SocialAgentRemoveRole", "SocialAgentRemoveRoleTest");

        StringWriter writerRemoveRole = new StringWriter();
        Template templateRemoveRole = velocityEngine.getTemplate("SocialAgentRemoveRole.vm");
        templateRemoveRole.merge(contextRemoveRole, writerRemoveRole);

        String outputRemoveRole = writerRemoveRole.toString();
        assertThat("SocialAgentRemoveRole should contain 'RemoveRole'", outputRemoveRole, containsString("RemoveRole"));

        logger.info("SocialAgent role template naming verified");
    }

    /**
     * Test CompartmentActivatorRuntime with dollar-sign variables (Java template).
     * Edge case: Java package variables with $ prefix.
     */
    @Test
    public void testCompartmentActivatorRuntimeJavaVariables() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("yearStart", "2022");
        context.put("yearEnd", "2024");
        context.put("author", "Test Author");
        context.put("packageName", "com.test.compartment");
        context.put("importPath", "com.test");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("CompartmentActivatorRuntime.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain package declaration", output, containsString("package com.test.compartment"));
        assertThat("Output should contain import statements", output, containsString("import"));
        assertThat("Output should contain year range", output, containsString("2022"));
        logger.info("CompartmentActivatorRuntime Java variables rendered successfully");
    }

    /**
     * Test RoleOne template with inventory variable.
     * Edge case: State variable declarations in role contracts.
     */
    @Test
    public void testRoleOneStateVariables() throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("solidityVersion", "0.8.7");
        context.put("genericRole", "SupplierRole");
        context.put("pushFunctionName", "addToInventory");
        context.put("popFunctionName", "removeFromInventory");
        context.put("getInventoryStatusFunctionName", "getInventory");
        context.put("playContractFunctionName", "playRole");
        context.put("inventoryVariable", "inventory");

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("RoleOne.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain inventory variable", output, containsString("inventory"));
        assertThat("Output should contain role contract inheritance", output, containsString("ComponentRole"));
        logger.info("RoleOne state variables rendered successfully");
    }

    /**
     * Test all templates for consistent SPDX license format.
     * Edge case: Validate SPDX license identifier format across all templates.
     */
    @Test
    public void testSPDXLicenseFormat() throws IOException {
        String[] templates = {
            "RoleCreatorTemplate.vm",
            "ComponentTemplate.vm",
            "ComponentCoreTemplate.vm",
            "ComponentRoleTemplate.vm"
        };

        VelocityContext context = createDefaultContext();

        for (String templateName : templates) {
            StringWriter writer = new StringWriter();
            Template template = velocityEngine.getTemplate(templateName);
            template.merge(context, writer);

            String output = writer.toString();
            assertThat(
                "Template " + templateName + " should have valid SPDX format",
                output,
                containsString("// SPDX-License-Identifier:")
            );
            logger.info(() -> "SPDX license format verified for: " + templateName);
        }
    }

    /**
     * Test all templates for consistent pragma solidity format.
     * Edge case: Ensure pragma statements are properly formatted.
     */
    @Test
    public void testPragmaSolidityFormat() throws IOException {
        String[] templates = {
            "Role.vm",
            "Compartment.vm",
            "RoleOne.vm"
        };

        VelocityContext context = createDefaultContext();

        for (String templateName : templates) {
            StringWriter writer = new StringWriter();
            Template template = velocityEngine.getTemplate(templateName);
            template.merge(context, writer);

            String output = writer.toString();
            assertThat(
                "Template " + templateName + " should have pragma solidity",
                output,
                containsString("pragma solidity")
            );
            logger.info(() -> "Pragma format verified for: " + templateName);
        }
    }

    /**
     * Test velocity loop construct in SocialAgent templates.
     * Edge case: Looping through import lists.
     */
    @Test
    public void testVelocityForEachLoop() throws IOException {
        VelocityContext context = new VelocityContext();

        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("SocialAgentAddRole.vm");
        template.merge(context, writer);

        String output = writer.toString();
        assertNotNull("Template output should not be null", output);
        assertThat("Output should contain import statements", output, containsString("import"));
        // Check that foreach loop was processed correctly
        int importCount = countOccurrences(output, "import \"");
        assertTrue("Should have multiple imports processed by foreach", importCount > 0);
        logger.info("Velocity foreach loop rendered successfully");
    }

    // ==================== HELPER METHODS ====================

    /**
     * Create a default context with common template variables.
     */
    private static VelocityContext createDefaultContext() {
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
        context.put("interfaceName", "Component");
        context.put("baseContract", "ERC165");
        return context;
    }

    /**
     * Count occurrences of a substring in a string.
     *
     * @param text the text to search in
     * @param substring the substring to count
     * @return the number of occurrences
     */
    private static int countOccurrences(String text, String substring) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }
        return count;
    }
}
