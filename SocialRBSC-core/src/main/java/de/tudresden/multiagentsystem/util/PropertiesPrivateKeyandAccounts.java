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
import java.io.InputStream;

import java.util.Map;
import java.util.Properties;


/**
 * Properties with private key in Ganache Test Blockchain.
 *
 * @author Orcun Oruc
 */
public class PropertiesPrivateKeyandAccounts {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    public static synchronized PropertiesPrivateKeyandAccounts getProperties() {
        if (propertiesPrivateKeyandAccounts == null) {
            propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();
        }

        return propertiesPrivateKeyandAccounts;
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return Get property file called newValueMap
     */
    public Map<String, String> getPropertyFromFile() {
        Map<String, String> newValueMap = new java.util.HashMap<String, String>();
        Properties prop = new Properties();

        try(InputStream input = new FileInputStream("src/test/resources/privatekeysaccounts.properties")) {
            prop.load(input);
            newValueMap.put("account1", prop.getProperty("account1"));
            newValueMap.put("account2", prop.getProperty("account2"));
            newValueMap.put("account3", prop.getProperty("account3"));
            newValueMap.put("account4", prop.getProperty("account4"));
            newValueMap.put("account5", prop.getProperty("account5"));
            newValueMap.put("account6", prop.getProperty("account6"));
            newValueMap.put("account7", prop.getProperty("account7"));
            newValueMap.put("account8", prop.getProperty("account8"));
            newValueMap.put("account9", prop.getProperty("account9"));
            newValueMap.put("account10", prop.getProperty("account10"));
            newValueMap.put("private_key1", prop.getProperty("private_key1"));
            newValueMap.put("private_key2", prop.getProperty("private_key2"));
            newValueMap.put("private_key3", prop.getProperty("private_key3"));
            newValueMap.put("private_key4", prop.getProperty("private_key4"));
            newValueMap.put("private_key5", prop.getProperty("private_key5"));
            newValueMap.put("private_key6", prop.getProperty("private_key6"));
            newValueMap.put("private_key7", prop.getProperty("private_key7"));
            newValueMap.put("private_key8", prop.getProperty("private_key8"));
            newValueMap.put("private_key9", prop.getProperty("private_key9"));
            newValueMap.put("private_key10", prop.getProperty("private_key10"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newValueMap;
    }
}
