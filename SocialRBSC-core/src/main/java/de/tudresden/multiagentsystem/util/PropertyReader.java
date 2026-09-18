/*
 * Copyright 2022-2023 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class PropertyReader {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static PropertyReader instance;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    // Singleton pattern
    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static PropertyReader getInstance() {
        if (instance == null) {
            instance = new PropertyReader(); // default constructor
        }

        return instance;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    public Properties readProperty() throws IOException {
        Properties prop = null;

        try(InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();

            return null;
        }

        return prop;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @param  filename TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     *
     * @throws IOException
     */
    public Properties readPropertyFromClasspath(String filename) throws IOException {
        Properties prop = null;

        try(InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(filename)) {
            prop = new Properties();

            if (inputStream == null) {
                System.out.println("Unable to find: " + filename);

                return null;
            }

            prop.load(inputStream);

            // print key and values
            prop.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

            // Get all keys
            prop.keySet().forEach(x -> System.out.println(x));
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }

        return prop;
    }
}
