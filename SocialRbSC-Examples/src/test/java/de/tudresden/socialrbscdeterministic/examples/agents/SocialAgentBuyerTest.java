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
 * Test cases for SocialAgentBuyer.
 * Tests preconditions and role methods for buyer agent.
 *
 * @author SocialRbSC Framework
 */
@DisplayName("SocialAgentBuyer Tests")
public class SocialAgentBuyerTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    private SocialAgentBuyer buyer;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Setup test fixtures before each test.
     */
    @BeforeEach
    public void setUp() {
        buyer = new SocialAgentBuyer();
    }

    /**
     * Test initialization of SocialAgentBuyer.
     */
    @Test
    @DisplayName("Should initialize SocialAgentBuyer successfully")
    public void testBuyerInitialization() {
        assertNotNull(buyer, "Buyer instance should not be null");
    }

    /**
     * Test initiatePurchase method execution.
     */
    @Test
    @DisplayName("Should execute initiatePurchase without exceptions")
    public void testInitiatePurchase() {
        assertDoesNotThrow(() -> {
            buyer.initiatePurchase();
        }, "initiatePurchase should execute without throwing exceptions");
    }

    /**
     * Test checkBalance method returns true.
     */
    @Test
    @DisplayName("Should verify balance check returns true")
    public void testCheckBalanceReturnsTrue() {
        boolean hasBalance = buyer.checkBalance();
        assertTrue(hasBalance, "checkBalance should return true when balance is available");
    }

    /**
     * Test checkBalance method with multiple calls.
     */
    @Test
    @DisplayName("Should maintain balance check consistency across multiple calls")
    public void testCheckBalanceConsistency() {
        boolean firstCheck = buyer.checkBalance();
        boolean secondCheck = buyer.checkBalance();
        
        assertEquals(firstCheck, secondCheck, 
            "checkBalance should return consistent results across multiple calls");
    }

    /**
     * Test purchase flow sequence.
     */
    @Test
    @DisplayName("Should execute complete purchase flow")
    public void testCompletePurchaseFlow() {
        // Verify balance first
        assertTrue(buyer.checkBalance(), "Initial balance check should pass");
        
        // Initiate purchase
        assertDoesNotThrow(() -> {
            buyer.initiatePurchase();
        }, "Purchase initiation should succeed");
        
        // Verify balance again
        assertTrue(buyer.checkBalance(), "Final balance check should pass");
    }

    /**
     * Test multiple purchase attempts.
     */
    @Test
    @DisplayName("Should handle multiple purchase attempts")
    public void testMultiplePurchaseAttempts() {
        for (int i = 0; i < 3; i++) {
            assertTrue(buyer.checkBalance(), "Balance should be available for attempt " + (i + 1));
            assertDoesNotThrow(() -> {
                buyer.initiatePurchase();
            }, "Purchase attempt " + (i + 1) + " should succeed");
        }
    }

}
