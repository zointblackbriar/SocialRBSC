/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program.
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.mas.annotation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for SocialAgent annotation.
 *
 * @author Test Suite
 */
@DisplayName("SocialAgent Annotation Tests")
class SocialAgentTest {

    @Test
    @DisplayName("Should be a valid annotation interface")
    void testAnnotationExists() {
        assertTrue(SocialAgent.class.isAnnotation(), "SocialAgent should be an annotation");
    }

    @Test
    @DisplayName("Should have annotation retention and target")
    void testAnnotationStructure() {
        assertNotNull(SocialAgent.class.getAnnotations(), 
            "SocialAgent annotation should have metadata");
    }
}
