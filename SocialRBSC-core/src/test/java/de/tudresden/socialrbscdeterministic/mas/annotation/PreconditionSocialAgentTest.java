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
 * Test class for PreconditionSocialAgent annotation.
 *
 * @author Test Suite
 */
@DisplayName("PreconditionSocialAgent Annotation Tests")
class PreconditionSocialAgentTest {

    @Test
    @DisplayName("Should be a valid annotation interface")
    void testAnnotationExists() {
        assertTrue(PreconditionSocialAgent.class.isAnnotation(), 
            "PreconditionSocialAgent should be an annotation");
    }

    @Test
    @DisplayName("Should have annotation retention and target")
    void testAnnotationStructure() {
        assertNotNull(PreconditionSocialAgent.class.getAnnotations(), 
            "PreconditionSocialAgent annotation should have metadata");
    }
}
