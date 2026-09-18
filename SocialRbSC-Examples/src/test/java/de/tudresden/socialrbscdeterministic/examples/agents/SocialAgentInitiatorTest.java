/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.agents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


/**
 * Test cases for SocialAgentInitiator.
 * Tests preconditions and role methods for initiator agent.
 *
 * @author SocialRbSC Framework
 */
@DisplayName("SocialAgentInitiator Tests")
public class SocialAgentInitiatorTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    private SocialAgentInitiator initiator;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Setup test fixtures before each test.
     */
    @BeforeEach
    public void setUp() {
        initiator = new SocialAgentInitiator();
    }

    /**
     * Test initialization of SocialAgentInitiator.
     */
    @Test
    @DisplayName("Should initialize SocialAgentInitiator successfully")
    public void testInitiatorInitialization() {
        assertNotNull(initiator, "Initiator instance should not be null");
    }

    /**
     * Test initializeTransaction method execution.
     */
    @Test
    @DisplayName("Should execute initializeTransaction without exceptions")
    public void testInitializeTransaction() {
        assertDoesNotThrow(() -> {
            initiator.initializeTransaction();
        }, "initializeTransaction should execute without throwing exceptions");
    }

    /**
     * Test validateParties method returns true.
     */
    @Test
    @DisplayName("Should verify validateParties returns true")
    public void testValidatePartiesReturnsTrue() {
        boolean partiesReady = initiator.validateParties();
        assertTrue(partiesReady, "validateParties should return true when all parties are ready");
    }

    /**
     * Test validateParties method with multiple calls.
     */
    @Test
    @DisplayName("Should maintain party validation consistency across multiple calls")
    public void testValidatePartiesConsistency() {
        boolean firstCheck = initiator.validateParties();
        boolean secondCheck = initiator.validateParties();
        
        assertEquals(firstCheck, secondCheck, 
            "validateParties should return consistent results across multiple calls");
    }

    /**
     * Test transaction initialization flow sequence.
     */
    @Test
    @DisplayName("Should execute complete transaction initialization flow")
    public void testCompleteTransactionFlow() {
        // Validate parties first
        assertTrue(initiator.validateParties(), "Initial party validation should pass");
        
        // Initialize transaction
        assertDoesNotThrow(() -> {
            initiator.initializeTransaction();
        }, "Transaction initialization should succeed");
        
        // Validate parties again
        assertTrue(initiator.validateParties(), "Final party validation should pass");
    }

    /**
     * Test multiple transaction initialization attempts.
     */
    @Test
    @DisplayName("Should handle multiple transaction initialization attempts")
    public void testMultipleTransactionAttempts() {
        for (int i = 0; i < 3; i++) {
            assertTrue(initiator.validateParties(), "Parties should be valid for attempt " + (i + 1));
            assertDoesNotThrow(() -> {
                initiator.initializeTransaction();
            }, "Transaction initialization attempt " + (i + 1) + " should succeed");
        }
    }

}
