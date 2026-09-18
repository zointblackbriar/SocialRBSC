/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program.
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.commandlinetester;

import de.tudresden.codegenerator.commandline.CommandLineActivator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.util.logging.Logger;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class CommandLineTest {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(CommandLineTest.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    @Disabled
    public void runMultipleCommand() throws IOException {
        logger.info("Running multiple command test");

        String[] commandsArray = new String[] {"ls -alFh", "ls -alFh"};
        assertTrue(CommandLineActivator.runMultipleCommands(commandsArray));
    }

    /**
     * Simple echo command test.
     *
     * @throws IOException
     */
    @Test
    public void testCreationOfANewProjectWithMaven() throws IOException {
        logger.info("Simple echo command for the output");

        CommandLineActivator commandLineActivator = new CommandLineActivator();

        // Arrange
        String command = null;

        // Act
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            command = "cmd.exe /c echo This is a command executed by the Command Prompt";
        } else {
            command = "echo hello world";
        }

        boolean result = commandLineActivator.runACommand(command);
        // Assert
        assertTrue(result, "Command should be execute successfully: ");
    }

    /**
     * Test The Ganache Tool Functionality in Windows or other OS.*
     *
     * @throws IOException
     */
    @Test
    public void testGanacheConsistency() throws IOException {
        logger.info("testing for the Ganache CLI with a consistent wallet key");

        CommandLineActivator commandLineActivator = new CommandLineActivator();
        boolean result = false;

        // Arrange
        String command = null;

        // Act
        if (System.getProperty("os.name").startsWith("Windows")) {
            command =
                "cmd.exe /c ganache -l 9000000 -m \\\"world reopen cute forward vintage okay drink margin piano buffalo autumn awful\\";
            result = commandLineActivator.runACommand(command);

            Process commandProcess = new ProcessBuilder("cmd.exe", "/c").start();
            commandProcess.destroy();
        } else {
            command =
                "ganache -l 9000000 -m \\\"world reopen cute forward vintage okay drink margin piano buffalo autumn awful\\";
            result = commandLineActivator.runACommand(command);
        }

        assertTrue(result);
    }

    /**
     * List the files with a test case.
     *
     * @throws IOException
     */
    @Test
    public void testListingAdvancedFiles() throws IOException {
        logger.info("Advanced listing for files");

        CommandLineActivator commandLineActivator = new CommandLineActivator();

        // assertTrue(CommandLineActivator.runACommand("ls -alFh"));
        boolean result = false;

        // Arrange
        String command = null;

        // Act
        if (System.getProperty("os.name").startsWith("Windows")) {
            command = "cmd.exe /c dir /a /l";
            result = commandLineActivator.runACommand(command);
        } else {
            command = "ls -alFh";
            result = commandLineActivator.runACommand(command);
        }

        assertTrue(result);
    }

    /**
     * mvn web3j:generate-sources command test for the Java Wrappers of Solidity packages.
     *
     * @throws IOException
     */
    @Test
    public void testMavenCommandLineForSoliditySources() throws IOException {
        logger.info("Mvn command for generated solidity java wrappers");

        CommandLineActivator commandLineActivator = new CommandLineActivator();

        // assertTrue(CommandLineActivator.runACommand("mvn web3j:generate-sources"));

        boolean result = false;

        // Arrange
        String command = null;

        // Act
        if (System.getProperty("os.name").startsWith("Windows")) {
            command = "cmd.exe /c mvn web3j:generate-sources";
            result = commandLineActivator.runACommand(command);
        } else {
            command = "mvn web3j:generate-sources";
            result = commandLineActivator.runACommand(command);
        }

        assertTrue(result);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    @Disabled
    public void testSimpleCommand() throws IOException {
        logger.info("Simple listing for files");

//        CommandLineActivator commandLineActivator = new CommandLineActivator();

        boolean result = false;

        // Arrange
        String command = null;

        // Act
        if (System.getProperty("os.name").startsWith("Windows")) {
            command = "cmd.exe /c dir";
//            result = commandLineActivator.runACommand(command);
        } else { // in case of Ubuntu or MacOS
            command = "ls -l";
//            result = commandLineActivator.runACommand(command);
        }

        assertTrue(result);
    }
}
