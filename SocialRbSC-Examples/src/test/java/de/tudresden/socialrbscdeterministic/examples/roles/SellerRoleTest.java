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
 * Test cases for SellerRole.
 * Tests role-specific methods and behaviors for seller role.
 *
 * @author SocialRbSC Framework
 */
@DisplayName("SellerRole Tests")
public class SellerRoleTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    private SellerRole sellerRole;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Setup test fixtures before each test.
     */
    @BeforeEach
    public void setUp() {
        sellerRole = new SellerRole();
    }

    /**
     * Test initialization of SellerRole.
     */
    @Test
    @DisplayName("Should initialize SellerRole successfully")
    public void testSellerRoleInitialization() {
        assertNotNull(sellerRole, "SellerRole instance should not be null");
    }

    /**
     * Test sendQuotation method execution.
     */
    @Test
    @DisplayName("Should execute sendQuotation without exceptions")
    public void testSendQuotation() {
        assertDoesNotThrow(() -> {
            sellerRole.sendQuotation();
        }, "sendQuotation should execute without throwing exceptions");
    }

    /**
     * Test acceptOrder method execution.
     */
    @Test
    @DisplayName("Should execute acceptOrder without exceptions")
    public void testAcceptOrder() {
        assertDoesNotThrow(() -> {
            sellerRole.acceptOrder();
        }, "acceptOrder should execute without throwing exceptions");
    }

    /**
     * Test shipGoods method execution.
     */
    @Test
    @DisplayName("Should execute shipGoods without exceptions")
    public void testShipGoods() {
        assertDoesNotThrow(() -> {
            sellerRole.shipGoods();
        }, "shipGoods should execute without throwing exceptions");
    }

    /**
     * Test complete seller workflow sequence.
     */
    @Test
    @DisplayName("Should execute complete seller workflow in correct sequence")
    public void testCompleteSellerWorkflow() {
        // Send quotation
        assertDoesNotThrow(() -> {
            sellerRole.sendQuotation();
        }, "Quotation sending should succeed");
        
        // Accept order
        assertDoesNotThrow(() -> {
            sellerRole.acceptOrder();
        }, "Order acceptance should succeed");
        
        // Ship goods
        assertDoesNotThrow(() -> {
            sellerRole.shipGoods();
        }, "Goods shipment should succeed");
    }

    /**
     * Test multiple quotation sends.
     */
    @Test
    @DisplayName("Should handle multiple quotation sends")
    public void testMultipleQuotationSends() {
        for (int i = 0; i < 3; i++) {
            assertDoesNotThrow(() -> {
                sellerRole.sendQuotation();
            }, "Quotation send " + (i + 1) + " should succeed");
        }
    }

    /**
     * Test repeated sales cycle.
     */
    @Test
    @DisplayName("Should handle repeated sales cycles")
    public void testRepeatedSalesCycle() {
        for (int cycle = 0; cycle < 2; cycle++) {
            assertDoesNotThrow(() -> {
                sellerRole.sendQuotation();
                sellerRole.acceptOrder();
                sellerRole.shipGoods();
            }, "Sales cycle " + (cycle + 1) + " should succeed");
        }
    }

}
