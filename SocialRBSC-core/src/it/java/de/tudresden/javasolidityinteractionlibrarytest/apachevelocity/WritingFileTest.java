/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.javasolidityinteractionlibrarytest.apachevelocity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;

import de.tudresden.multiagentsystem.util.Util;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class WritingFileTest {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Writing Files generally for the output of templates.
     *
     * @throws java.io.IOException
     */
    @Disabled
    public void testWritingFile() throws java.io.IOException {
        Util util = new Util();
        String textToBeWritten = "public static void main(String[] args) {" + "System.out.println(\"Hello Java\");" + "}";
        assertTrue(util.saveSourceCode2File("generatedsmartcontract/output1.java", textToBeWritten));
    }

    /**
     * File Print Writer with Buffer.
     *
     * @throws java.io.IOException
     */
    @Disabled
    public void testWritingFileBufferedWriter() throws java.io.IOException {
        Util util = new Util();
        String textToBeWritten = "public static void main(String[] args) {" + "System.out.println(\"Hello Java\");" + "}";
        assertTrue(util.saveSourceCode2FilePrintWriter("generatedsmartcontract/output3.java", textToBeWritten));
    }

    /**
     * Writing File Test.
     *
     * @throws java.io.IOException
     */
    @Disabled
    public void testWritingFilePrintWriter() throws java.io.IOException {
        Util util = new Util();
        String textToBeWritten = "public static void main(String[] args) {" + "System.out.println(\"Hello Java\");" + "}";
        assertTrue(util.saveSourceCode2FilePrintWriter("generatedsmartcontract/output2.java", textToBeWritten));
    }
}
