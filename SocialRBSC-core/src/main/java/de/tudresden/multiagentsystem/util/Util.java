/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.util;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.time.Duration;
import java.time.Instant;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class Util // This util class for the time calculation
{

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @param  fileName TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    public static String readACompiledFile(String fileName) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        String content = null;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }

        content = contentBuilder.toString();

        return content;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static Instant start() // Time has been started
    {
        return Instant.now();
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  start TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static Duration stop(Instant start) {
        Instant end = Instant.now();

        return Duration.between(start, end);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  fileName        TODO DOCUMENT ME!
     * @param  textToBeWritten TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public boolean saveSourceCode2File(String fileName, String textToBeWritten) {
        Path outputPath = Path.of(fileName);

        try {
            // Create the parent directories if they don't exist
            Files.createDirectories(outputPath.getParent());

            // Write the text to the file
            Files.writeString(outputPath, textToBeWritten, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  fileName        TODO DOCUMENT ME!
     * @param  textToBeWritten TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public boolean saveSourceCode2FilePrintWriter(String fileName, String textToBeWritten) {
        File output = new File(fileName);

        try(PrintWriter writer = new PrintWriter(output)) {
            writer.write(textToBeWritten);
            writer.flush();

            // writer.close();
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
