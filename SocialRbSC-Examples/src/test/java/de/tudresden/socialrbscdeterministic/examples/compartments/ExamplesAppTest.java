/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.compartments;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


/**
 * Test cases for ExamplesApp.
 * Tests application initialization and main entry point.
 *
 * @author SocialRbSC Framework
 */
@DisplayName("ExamplesApp Tests")
public class ExamplesAppTest {

    /**
     * Test main method execution with empty arguments.
     */
    @Test
    @DisplayName("Should execute main method with empty arguments")
    public void testMainMethodExecution() {
        assertDoesNotThrow(() -> {
            ExamplesApp.main(new String[]{});
        }, "Main method should execute without throwing exceptions");
    }

    /**
     * Test main method execution with null arguments array.
     */
    @Test
    @DisplayName("Should execute main method with empty string array")
    public void testMainMethodWithEmptyArray() {
        String[] args = new String[0];
        assertDoesNotThrow(() -> {
            ExamplesApp.main(args);
        }, "Main method should handle empty string array");
    }

    /**
     * Test main method multiple invocations.
     */
    @Test
    @DisplayName("Should handle multiple main method invocations")
    public void testMultipleMainInvocations() {
        for (int i = 0; i < 3; i++) {
            assertDoesNotThrow(() -> {
                ExamplesApp.main(new String[]{});
            }, "Main method invocation " + (i + 1) + " should succeed");
        }
    }

}
