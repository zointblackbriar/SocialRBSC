/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.codegenerator.soliditygenerator;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import de.tudresden.multiagentsystem.util.Util;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class SolidityGenerator {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(SolidityGenerator.class.getName());

    private static VelocityEngine createVelocityEngine() {
        Properties properties = new Properties();
        properties.setProperty("input.encoding", "utf-8");
        properties.setProperty("resource.loader", "class,file");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty("class.resource.loader.cache", "true");
        properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        properties.setProperty("file.resource.loader.path", resolveVelocityTemplatePaths());
        properties.setProperty("file.resource.loader.cache", "true");
        properties.setProperty("file.resource.loader.modificationCheckInterval", "2");
        properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogChute");

        VelocityEngine engine = new VelocityEngine();
        engine.init(properties);
        return engine;
    }

    private static String resolveVelocityTemplatePaths() {
        LinkedHashSet<String> paths = new LinkedHashSet<>();
        paths.add(Paths.get("velocitytemplates").toAbsolutePath().normalize().toString());
        paths.add(Paths.get("SocialRBSC-core", "velocitytemplates").toAbsolutePath().normalize().toString());
        paths.add(Paths.get("..", "SocialRBSC-core", "velocitytemplates").toAbsolutePath().normalize().toString());
        return String.join(",", paths);
    }

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @param  compartmentName   TODO DOCUMENT ME!
     * @param  smartContractName TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static boolean solidityForCompartmentCreation(String compartmentName, String smartContractName) {
        try {
            Path generatedContractPath = Paths.get("generatedsmartcontract", "contracts", smartContractName);
            VelocityEngine velocityEngine = createVelocityEngine();

            Util util = new Util();
            Template template = velocityEngine.getTemplate("Compartment.vm");
            final Context context = new VelocityContext();
            context.put("Compartment", compartmentName);

            StringWriter writer = new StringWriter();
            template.merge(context, writer);

            // util.saveSourceCode2File("smartcontracts/contracts/StaticRole.sol", writer.toString());
            // util.saveSourceCode2File("generatedsmartcontract/contracts/" + smartContractName + ".sol", writer.toString());
            util.saveSourceCode2File(generatedContractPath + ".sol", writer.toString());
            logger.info("solidity RoleDeterministic contract is ready");
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * Creates a new {@link SolidityGenerator} object.
     *
     * @param  roleMethodName TODO DOCUMENT ME!
     * @param  methodName     assign method name
     * @param  contractName   roleName       particular role name
     *
     * @return True or false
     */
    public static boolean solidityForRoleMethod(String roleMethodName, String methodName, String contractName) {
        try {
            VelocityEngine velocityEngine = createVelocityEngine();

            // Use a sanitized simple role name for file naming
            String simpleName = contractName.contains(".") ? contractName.substring(contractName.lastIndexOf('.') + 1) : contractName;
            String roleCreatorName = simpleName + "Creator";
            Path generatedContractPath = Paths.get("generatedsmartcontract", "contracts", simpleName);

            Util util = new Util();
            Template template = velocityEngine.getTemplate("Role.vm");
            final Context solidityContext = new VelocityContext();
            // Provide both roleFunction and function to support different template expectations
            solidityContext.put("roleFunction", methodName);
            solidityContext.put("function", methodName);
            // Provide role name and creator name
            solidityContext.put("RoleName", simpleName);
            solidityContext.put("roleNameCreator", roleCreatorName);

            StringWriter writer = new StringWriter();
            template.merge(solidityContext, writer);
            System.out.println("roleMethod name: " + roleMethodName);

            util.saveSourceCode2File(generatedContractPath + ".sol", writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  roleName TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static boolean solidityForSocialRbSCRoleGenerateOnlyRole(String roleName) {
        try {
            VelocityEngine velocityEngine = createVelocityEngine();

            // Derive a simple role name (strip package if provided) and the creator name
            String simpleName = roleName.contains(".") ? roleName.substring(roleName.lastIndexOf('.') + 1) : roleName;
            String roleCreatorName = simpleName + "Creator";

            // Generate the base Role contract as a generic Role.sol
            Path generatedContractPath = Paths.get("generatedsmartcontract", "contracts", "Role");

            Util util = new Util();
            Template template = velocityEngine.getTemplate("Role.vm");
            final Context context = new VelocityContext();
            // Base contract should be named 'Role'
            context.put("RoleName", "Role");
            // Provide the derived role creator name to the template
            context.put("roleNameCreator", roleCreatorName);

            StringWriter writer = new StringWriter();
            template.merge(context, writer);

            Path folderPath = Paths.get("generatedsmartcontract", "contracts");
            File contractsFolder = new File(folderPath.toString());

            if (!contractsFolder.exists()) {
                boolean folderCreated = contractsFolder.mkdirs();

                if (folderCreated) {
                    logger.info("Contracts folder created successfully");
                } else {
                    logger.severe("Failed to create contracts folder");

                    return false;
                }
            }

            util.saveSourceCode2File(generatedContractPath + ".sol", writer.toString());
            logger.info("final file name: " + generatedContractPath + ".sol");
            logger.info("solidity RoleDeterministic contract is ready");
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  nameOfContract TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static boolean soliditySocialAddRole(String nameOfContract) {
        try {
            Path generatedContractPath = Paths.get("generatedsmartcontract", "contracts", nameOfContract);
            VelocityEngine velocityEngine = createVelocityEngine();

            Util util = new Util();

            Template template = velocityEngine.getTemplate("SocialAgentAddRole.vm");
            final Context context = new VelocityContext();
            StringWriter writer = new StringWriter();

            // Set up variables for the Solidity contract within the Velocity context
            // context.put("version", "pragma solidity >=0.7.0 <0.8.0;");

            // context.put("SocialAgentContract", socialAgentName);
            // context.put("socialAgentName", socialAgentName); // Social agent's name variable
            context.put("SocialAgentInitiator", nameOfContract); // Social agent's name variable

            // Merge context data into the template and write to String
            template.merge(context, writer);
            logger.info("Generated contract content: " + writer.toString());

            // Save the generated Solidity contract to a file
            util.saveSourceCode2File(generatedContractPath + ".sol", writer.toString());

            logger.info("final file name: " + generatedContractPath + ".sol");
            logger.info("solidity RoleDeterministic contract is ready");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  socialAgentName contextName    TODO DOCUMENT ME!
     * @param  nameOfContract  TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static boolean soliditySocialAgentGeneration(String socialAgentName, String nameOfContract) {
        try {
            Path generatedContractPath = Paths.get("generatedsmartcontract", "contracts", nameOfContract);
            VelocityEngine velocityEngine = createVelocityEngine();

            Util util = new Util();

            Template template = velocityEngine.getTemplate("SocialAgentInitiator.vm");
            final Context context = new VelocityContext();
            StringWriter writer = new StringWriter();

            context.put("SocialAgentInitiator", nameOfContract); // Social agent's name variable
            logger.info("the name of the agent: " + socialAgentName);

            // Merge context data into the template and write to String
            template.merge(context, writer);
            logger.info("Generated contract content: " + writer.toString());

            // Save the generated Solidity contract to a file
            util.saveSourceCode2File(generatedContractPath + ".sol", writer.toString());

            logger.info("final file name: " + generatedContractPath + ".sol");
            logger.info("solidity RoleDeterministic contract is ready");
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  nameOfContract TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static boolean soliditySocialAgentRemoveRole(String nameOfContract) {
        try {
            Path generatedContractPath = Paths.get("generatedsmartcontract", "contracts", nameOfContract);
            VelocityEngine velocityEngine = createVelocityEngine();

            Util util = new Util();

            Template template = velocityEngine.getTemplate("SocialAgentRemoveRole.vm");
            final Context context = new VelocityContext();
            StringWriter writer = new StringWriter();
            context.put("SocialAgentInitiator", nameOfContract); // Contract name

            // Merge context data into the template and write to String
            template.merge(context, writer);

            // Save the generated Solidity contract to a file
            util.saveSourceCode2File(generatedContractPath + ".sol", writer.toString());

            logger.info("final file name: " + generatedContractPath + ".sol");
            logger.info("solidity RoleDeterministic remove role");
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * Generate a solidity contract from a goal plan model using a velocity template
     *
     * @param contractName the name to use for the generated contract file
     * @param plans        list of plans (each plan is a map with keys: name, avoidance, willingness, subGoals)
     * @return true on success
     */
    public static boolean solidityForGoalPlan(String contractName, java.util.List<java.util.Map<String, Object>> plans) {
        try {
            Path generatedContractPath = Paths.get("generatedsmartcontract", "contracts", contractName);
            VelocityEngine velocityEngine = createVelocityEngine();

            Util util = new Util();

            Template template = velocityEngine.getTemplate("GoalPlan.vm");
            final Context context = new VelocityContext();
            StringWriter writer = new StringWriter();
            context.put("contractName", contractName);
            context.put("plans", plans);

            template.merge(context, writer);

            util.saveSourceCode2File(generatedContractPath + ".sol", writer.toString());

            logger.info("final file name: " + generatedContractPath + ".sol");
            logger.info("solidity GoalPlan contract is ready");
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }
}
