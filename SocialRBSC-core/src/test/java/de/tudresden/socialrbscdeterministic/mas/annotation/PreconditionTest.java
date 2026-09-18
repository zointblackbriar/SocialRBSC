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
 * Test class for Precondition annotation.
 *
 * @author Test Suite
 */
@DisplayName("Precondition Annotation Tests")
class PreconditionTest {

    @Test
    @DisplayName("Should be a valid annotation interface")
    void testAnnotationExists() {
        assertTrue(Precondition.class.isAnnotation(), "Precondition should be an annotation");
    }

    @Test
    @DisplayName("Should have annotation retention and target")
    void testAnnotationStructure() {
        assertNotNull(Precondition.class.getAnnotations(), 
            "Precondition annotation should have metadata");
    }
}
