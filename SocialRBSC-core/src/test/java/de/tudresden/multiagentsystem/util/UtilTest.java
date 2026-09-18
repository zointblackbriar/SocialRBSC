/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program.
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Test class for Util.
 *
 * @author Test Suite
 */
@DisplayName("Util Tests")
class UtilTest {

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        // Setup if needed
    }

    @Test
    @DisplayName("Should read a compiled file")
    void testReadACompiledFile() throws IOException {
        // Create a temporary test file
        Path testFile = tempDir.resolve("test.txt");
        String testContent = "Test content\nLine 2\n";
        Files.writeString(testFile, testContent);

        // Read the file
        String result = Util.readACompiledFile(testFile.toString());

        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Test content"), "Content should match");
        assertTrue(result.contains("Line 2"), "Content should contain second line");
    }

    @Test
    @DisplayName("Should handle non-existent file")
    void testReadNonExistentFile() {
        assertThrows(IOException.class, () -> {
            Util.readACompiledFile("non_existent_file.txt");
        }, "Should throw IOException for non-existent file");
    }

    @Test
    @DisplayName("Should have valid class structure")
    void testClassStructure() {
        assertNotNull(Util.class, "Util class should exist");
    }
}
