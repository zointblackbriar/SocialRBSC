/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program.
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.codegenerator.commandline;

import org.junit.Ignore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class CommandLineActivator {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * ExecutorService
     */
    static ExecutorService executor = Executors.newSingleThreadExecutor();

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * Logger
     */
    Logger logger = Logger.getLogger(CommandLineActivator.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Multiple Command Running for OS
     *
     * @param  commands TODO DOCUMENT ME!
     *
     * @return Result of value of the processing commands
     *
     * @throws IOException
     */
    @Ignore
    public static boolean runMultipleCommands(String[] commands) throws IOException {
        boolean result = false;
        CommandLineActivator commandLineActivator = new CommandLineActivator();
        executor.execute(new Runnable() {
                public boolean result = false;

                @Override
                public void run() {
                    try {
                        for (int i = 0; i < commands.length; i++) {
                            commandLineActivator.runACommand(commands[i]);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        result = false;
                    }
                }
            });

        return result;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  command TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    public boolean runACommand(String command) throws IOException {
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String commandline;

            while (true) {
                commandline = r.readLine();

                if (commandline == null) {
                    break;
                }

                logger.info("Command is: " + commandline);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }
    
}
