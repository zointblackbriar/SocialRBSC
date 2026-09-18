/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.utiltest;

// import de.tudresden.codegenerator.autogen.TestContract;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.web3j.crypto.Credentials;

import de.tudresden.multiagentsystem.util.PropertiesPrivateKeyandAccounts;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class DifferentParametersContractIT {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static String PRIVATE_KEY;

    /**
     * TODO DOCUMENT ME!
     */
    private static String CONTRACT_ADDRESS; // Give an address here

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void propertyFileTest() {
        PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();

        Map<String, String> sampleCredential = null;

        try {
            sampleCredential = propertiesPrivateKeyandAccounts.getPropertyFromFile();
            PRIVATE_KEY = sampleCredential.get("private_key1");

            // Iterator iter = sampleCredential.entrySet().iterator();

            // while (iter.hasNext()) {
            // System.out.println(iter.next());
            // }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Assert.assertEquals(sampleCredential.get("private_key1"), PRIVATE_KEY);
    }

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    private Credentials getCredentials() {
        PropertiesPrivateKeyandAccounts propertiesPrivateKeyandAccounts = new PropertiesPrivateKeyandAccounts();

        try {
            Map<String, String> sampleCredential = propertiesPrivateKeyandAccounts.getPropertyFromFile();

            PRIVATE_KEY = sampleCredential.get("private_key1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Credentials.create(PRIVATE_KEY);
    }
}
