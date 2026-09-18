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
 * Test cases for SocialAgentSeller.
 * Tests preconditions and role methods for seller agent.
 *
 * @author SocialRbSC Framework
 */
@DisplayName("SocialAgentSeller Tests")
public class SocialAgentSellerTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    private SocialAgentSeller seller;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Setup test fixtures before each test.
     */
    @BeforeEach
    public void setUp() {
        seller = new SocialAgentSeller();
    }

    /**
     * Test initialization of SocialAgentSeller.
     */
    @Test
    @DisplayName("Should initialize SocialAgentSeller successfully")
    public void testSellerInitialization() {
        assertNotNull(seller, "Seller instance should not be null");
    }

    /**
     * Test prepareGoodsForShipment method execution.
     */
    @Test
    @DisplayName("Should execute prepareGoodsForShipment without exceptions")
    public void testPrepareGoodsForShipment() {
        assertDoesNotThrow(() -> {
            seller.prepareGoodsForShipment();
        }, "prepareGoodsForShipment should execute without throwing exceptions");
    }

    /**
     * Test verifyInventory method returns true.
     */
    @Test
    @DisplayName("Should verify inventory check returns true")
    public void testVerifyInventoryReturnsTrue() {
        boolean hasInventory = seller.verifyInventory();
        assertTrue(hasInventory, "verifyInventory should return true when goods are in stock");
    }

    /**
     * Test verifyInventory method with multiple calls.
     */
    @Test
    @DisplayName("Should maintain inventory verification consistency across multiple calls")
    public void testVerifyInventoryConsistency() {
        boolean firstCheck = seller.verifyInventory();
        boolean secondCheck = seller.verifyInventory();
        
        assertEquals(firstCheck, secondCheck, 
            "verifyInventory should return consistent results across multiple calls");
    }

    /**
     * Test shipment preparation flow sequence.
     */
    @Test
    @DisplayName("Should execute complete shipment preparation flow")
    public void testCompleteShipmentFlow() {
        // Verify inventory first
        assertTrue(seller.verifyInventory(), "Initial inventory check should pass");
        
        // Prepare goods for shipment
        assertDoesNotThrow(() -> {
            seller.prepareGoodsForShipment();
        }, "Shipment preparation should succeed");
        
        // Verify inventory again
        assertTrue(seller.verifyInventory(), "Final inventory check should pass");
    }

    /**
     * Test multiple shipment attempts.
     */
    @Test
    @DisplayName("Should handle multiple shipment attempts")
    public void testMultipleShipmentAttempts() {
        for (int i = 0; i < 3; i++) {
            assertTrue(seller.verifyInventory(), "Inventory should be available for attempt " + (i + 1));
            assertDoesNotThrow(() -> {
                seller.prepareGoodsForShipment();
            }, "Shipment preparation attempt " + (i + 1) + " should succeed");
        }
    }

}
