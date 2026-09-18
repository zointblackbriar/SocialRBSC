/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.roles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


/**
 * Test cases for BuyerRole.
 * Tests role-specific methods and behaviors for buyer role.
 *
 * @author SocialRbSC Framework
 */
@DisplayName("BuyerRole Tests")
public class BuyerRoleTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    private BuyerRole buyerRole;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Setup test fixtures before each test.
     */
    @BeforeEach
    public void setUp() {
        buyerRole = new BuyerRole();
    }

    /**
     * Test initialization of BuyerRole.
     */
    @Test
    @DisplayName("Should initialize BuyerRole successfully")
    public void testBuyerRoleInitialization() {
        assertNotNull(buyerRole, "BuyerRole instance should not be null");
    }

    /**
     * Test requestQuotation method execution.
     */
    @Test
    @DisplayName("Should execute requestQuotation without exceptions")
    public void testRequestQuotation() {
        assertDoesNotThrow(() -> {
            buyerRole.requestQuotation();
        }, "requestQuotation should execute without throwing exceptions");
    }

    /**
     * Test placeOrder method execution.
     */
    @Test
    @DisplayName("Should execute placeOrder without exceptions")
    public void testPlaceOrder() {
        assertDoesNotThrow(() -> {
            buyerRole.placeOrder();
        }, "placeOrder should execute without throwing exceptions");
    }

    /**
     * Test processDelivery method execution.
     */
    @Test
    @DisplayName("Should execute processDelivery without exceptions")
    public void testProcessDelivery() {
        assertDoesNotThrow(() -> {
            buyerRole.processDelivery();
        }, "processDelivery should execute without throwing exceptions");
    }

    /**
     * Test complete buyer workflow sequence.
     */
    @Test
    @DisplayName("Should execute complete buyer workflow in correct sequence")
    public void testCompleteBuyerWorkflow() {
        // Request quotation
        assertDoesNotThrow(() -> {
            buyerRole.requestQuotation();
        }, "Quotation request should succeed");
        
        // Place order
        assertDoesNotThrow(() -> {
            buyerRole.placeOrder();
        }, "Order placement should succeed");
        
        // Process delivery
        assertDoesNotThrow(() -> {
            buyerRole.processDelivery();
        }, "Delivery processing should succeed");
    }

    /**
     * Test multiple quotation requests.
     */
    @Test
    @DisplayName("Should handle multiple quotation requests")
    public void testMultipleQuotationRequests() {
        for (int i = 0; i < 3; i++) {
            assertDoesNotThrow(() -> {
                buyerRole.requestQuotation();
            }, "Quotation request " + (i + 1) + " should succeed");
        }
    }

    /**
     * Test repeated purchase cycle.
     */
    @Test
    @DisplayName("Should handle repeated purchase cycles")
    public void testRepeatedPurchaseCycle() {
        for (int cycle = 0; cycle < 2; cycle++) {
            assertDoesNotThrow(() -> {
                buyerRole.requestQuotation();
                buyerRole.placeOrder();
                buyerRole.processDelivery();
            }, "Purchase cycle " + (cycle + 1) + " should succeed");
        }
    }

}
