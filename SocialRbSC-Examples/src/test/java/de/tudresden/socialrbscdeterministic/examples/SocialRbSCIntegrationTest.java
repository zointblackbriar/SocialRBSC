/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import de.tudresden.socialrbscdeterministic.examples.agents.SocialAgentBuyer;
import de.tudresden.socialrbscdeterministic.examples.agents.SocialAgentSeller;
import de.tudresden.socialrbscdeterministic.examples.agents.SocialAgentInitiator;
import de.tudresden.socialrbscdeterministic.examples.roles.BuyerRole;
import de.tudresden.socialrbscdeterministic.examples.roles.SellerRole;
import de.tudresden.socialrbscdeterministic.examples.compartments.SupplyChainCompartment;
import de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp;


/**
 * Integration test cases for SocialRbSC Examples project.
 * Tests the interaction and integration between agents, roles, and compartments.
 *
 * @author SocialRbSC Framework
 */
@DisplayName("SocialRbSC Examples Integration Tests")
public class SocialRbSCIntegrationTest {

    //~ Nested Classes -----------------------------------------------------------------------------------------------------------

    /**
     * Test buyer-seller interaction scenarios.
     */
    @Nested
    @DisplayName("Buyer-Seller Interaction Tests")
    public class BuyerSellerInteractionTest {

        private SocialAgentBuyer buyer;
        private SocialAgentSeller seller;
        private BuyerRole buyerRole;
        private SellerRole sellerRole;

        @BeforeEach
        public void setUp() {
            buyer = new SocialAgentBuyer();
            seller = new SocialAgentSeller();
            buyerRole = new BuyerRole();
            sellerRole = new SellerRole();
        }

        /**
         * Test simple buyer-seller transaction.
         */
        @Test
        @DisplayName("Should execute simple buyer-seller transaction")
        public void testSimpleTransaction() {
            assertTrue(buyer.checkBalance(), "Buyer should have sufficient balance");
            assertTrue(seller.verifyInventory(), "Seller should have inventory");
            
            assertDoesNotThrow(() -> {
                buyerRole.requestQuotation();
                sellerRole.sendQuotation();
                buyerRole.placeOrder();
                sellerRole.acceptOrder();
            }, "Transaction flow should complete successfully");
        }

        /**
         * Test complete transaction lifecycle.
         */
        @Test
        @DisplayName("Should execute complete transaction lifecycle")
        public void testCompleteTransactionLifecycle() {
            // Buyer initiates
            assertDoesNotThrow(() -> {
                buyer.initiatePurchase();
            }, "Buyer should initiate purchase");
            
            // Buyer-seller negotiation
            assertDoesNotThrow(() -> {
                buyerRole.requestQuotation();
                sellerRole.sendQuotation();
            }, "Negotiation phase should succeed");
            
            // Order placement
            assertDoesNotThrow(() -> {
                buyerRole.placeOrder();
                sellerRole.acceptOrder();
            }, "Order phase should succeed");
            
            // Shipment
            assertDoesNotThrow(() -> {
                seller.prepareGoodsForShipment();
                sellerRole.shipGoods();
                buyerRole.processDelivery();
            }, "Shipment phase should succeed");
        }

        /**
         * Test multiple parallel transactions.
         */
        @Test
        @DisplayName("Should handle multiple parallel transactions")
        public void testMultipleParallelTransactions() {
            for (int i = 0; i < 3; i++) {
                assertTrue(buyer.checkBalance(), "Buyer balance check " + (i + 1));
                assertTrue(seller.verifyInventory(), "Seller inventory check " + (i + 1));
                
                assertDoesNotThrow(() -> {
                    buyerRole.requestQuotation();
                    sellerRole.sendQuotation();
                    buyerRole.placeOrder();
                    sellerRole.acceptOrder();
                }, "Transaction " + (i + 1) + " should succeed");
            }
        }
    }

    /**
     * Test compartment with full agent ecosystem.
     */
    @Nested
    @DisplayName("Compartment Ecosystem Tests")
    public class CompartmentEcosystemTest {

        private SupplyChainCompartment compartment;
        private SocialAgentBuyer buyer;
        private SocialAgentSeller seller;
        private SocialAgentInitiator initiator;

        @BeforeEach
        public void setUp() {
            compartment = new SupplyChainCompartment();
            buyer = new SocialAgentBuyer();
            seller = new SocialAgentSeller();
            initiator = new SocialAgentInitiator();
        }

        /**
         * Test compartment initialization with agents.
         */
        @Test
        @DisplayName("Should initialize compartment with all agents")
        public void testCompartmentInitializationWithAgents() {
            assertNotNull(compartment, "Compartment should be initialized");
            assertNotNull(buyer, "Buyer agent should be initialized");
            assertNotNull(seller, "Seller agent should be initialized");
            assertNotNull(initiator, "Initiator agent should be initialized");
        }

        /**
         * Test orchestrated transaction via compartment.
         */
        @Test
        @DisplayName("Should orchestrate transaction via compartment")
        public void testOrcheestratedTransaction() {
            // Initialize compartment
            assertDoesNotThrow(() -> {
                compartment.initialize();
            }, "Compartment initialization should succeed");
            
            // Initiator validates parties
            assertTrue(initiator.validateParties(), "All parties should be valid");
            
            // Execute transaction through compartment
            assertDoesNotThrow(() -> {
                initiator.initializeTransaction();
                compartment.executeTransaction();
            }, "Orchestrated transaction should succeed");
        }

        /**
         * Test multi-transaction compartment workflow.
         */
        @Test
        @DisplayName("Should handle multi-transaction workflow")
        public void testMultiTransactionWorkflow() {
            compartment.initialize();
            
            for (int i = 0; i < 2; i++) {
                assertTrue(initiator.validateParties(), "Parties valid for transaction " + (i + 1));
                assertTrue(buyer.checkBalance(), "Buyer has balance for transaction " + (i + 1));
                assertTrue(seller.verifyInventory(), "Seller has inventory for transaction " + (i + 1));
                
                assertDoesNotThrow(() -> {
                    initiator.initializeTransaction();
                    compartment.executeTransaction();
                }, "Transaction " + (i + 1) + " should succeed");
            }
        }
    }

    /**
     * Test application entry point.
     */
    @Nested
    @DisplayName("Application Entry Point Tests")
    public class ApplicationEntryPointTest {

        /**
         * Test application startup.
         */
        @Test
        @DisplayName("Should start application successfully")
        public void testApplicationStartup() {
            assertDoesNotThrow(() -> {
                ExamplesApp.main(new String[]{});
            }, "Application should start successfully");
        }

        /**
         * Test application restart capability.
         */
        @Test
        @DisplayName("Should handle multiple application startups")
        public void testMultipleApplicationStartups() {
            for (int i = 0; i < 2; i++) {
                assertDoesNotThrow(() -> {
                    ExamplesApp.main(new String[]{});
                }, "Application startup " + (i + 1) + " should succeed");
            }
        }
    }

    /**
     * Test full system interaction scenario.
     */
    @Nested
    @DisplayName("Full System Scenario Tests")
    public class FullSystemScenarioTest {

        /**
         * Test end-to-end supply chain scenario.
         */
        @Test
        @DisplayName("Should execute end-to-end supply chain scenario")
        public void testEndToEndSupplyChainScenario() {
            // Setup agents and compartment
            SocialAgentBuyer buyer = new SocialAgentBuyer();
            SocialAgentSeller seller = new SocialAgentSeller();
            SocialAgentInitiator initiator = new SocialAgentInitiator();
            SupplyChainCompartment compartment = new SupplyChainCompartment();
            
            BuyerRole buyerRole = new BuyerRole();
            SellerRole sellerRole = new SellerRole();
            
            // Initialize
            compartment.initialize();
            
            // Verification phase
            assertTrue(buyer.checkBalance(), "Buyer should have balance");
            assertTrue(seller.verifyInventory(), "Seller should have inventory");
            assertTrue(initiator.validateParties(), "All parties should be valid");
            
            // Transaction phase
            assertDoesNotThrow(() -> {
                initiator.initializeTransaction();
                buyerRole.requestQuotation();
                sellerRole.sendQuotation();
                buyerRole.placeOrder();
                sellerRole.acceptOrder();
                seller.prepareGoodsForShipment();
                sellerRole.shipGoods();
                buyerRole.processDelivery();
                compartment.executeTransaction();
            }, "End-to-end scenario should succeed");
        }

        /**
         * Test system resilience with repeated cycles.
         */
        @Test
        @DisplayName("Should maintain system resilience through repeated cycles")
        public void testSystemResilienceThroughCycles() {
            SocialAgentBuyer buyer = new SocialAgentBuyer();
            SocialAgentSeller seller = new SocialAgentSeller();
            SupplyChainCompartment compartment = new SupplyChainCompartment();
            
            compartment.initialize();
            
            // Execute multiple transaction cycles
            for (int cycle = 0; cycle < 5; cycle++) {
                assertTrue(buyer.checkBalance(), "Buyer should be resilient in cycle " + (cycle + 1));
                assertTrue(seller.verifyInventory(), "Seller should be resilient in cycle " + (cycle + 1));
                
                assertDoesNotThrow(() -> {
                    buyer.initiatePurchase();
                    seller.prepareGoodsForShipment();
                    compartment.executeTransaction();
                }, "Cycle " + (cycle + 1) + " should succeed");
            }
        }
    }

}
