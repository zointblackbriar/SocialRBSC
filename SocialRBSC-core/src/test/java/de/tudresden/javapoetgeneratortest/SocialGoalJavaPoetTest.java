/**
 * SocialGoalJavaPoetTest.java
 *
 * TODO: Add a file description.
 *
 * Auto-added header on 2026-02-03
 */

package de.tudresden.javapoetgeneratortest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import de.tudresden.codegenerator.javapoetgenerator.SocialGoalWrapper;

public class SocialGoalJavaPoetTest {

    @Test
    public void testSocialAgentWrapper() {
        assertTrue(SocialGoalWrapper.generateSocialAgentWrapper());
    }
}
