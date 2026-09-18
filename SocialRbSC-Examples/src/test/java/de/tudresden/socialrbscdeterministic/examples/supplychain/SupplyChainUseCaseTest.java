/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.supplychain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.tudresden.socialrbscdeterministic.examples.supplychain.compartments.Factory;
import de.tudresden.socialrbscdeterministic.examples.supplychain.intentions.ProductReady;
import de.tudresden.socialrbscdeterministic.examples.supplychain.intentions.UserExist;
import de.tudresden.socialrbscdeterministic.examples.supplychain.intentions.UserRegistration;
import de.tudresden.socialrbscdeterministic.examples.supplychain.roles.Producer;
import de.tudresden.socialrbscdeterministic.examples.supplychain.roles.Retailer;
import de.tudresden.socialrbscdeterministic.examples.supplychain.roles.Wholesaler;

/**
 * Test suite for Supply Chain use case.
 * Tests all roles, compartments, and intentions in the supply chain scenario.
 *
 * @author Generated from Supply Chain Use Case
 */
class SupplyChainUseCaseTest {
    
    private Factory factory;
    private Retailer retailer;
    private Wholesaler wholesaler;
    private Producer producer;
    
    @BeforeEach
    void setUp() {
        factory = new Factory();
        retailer = factory.getRetailer();
        wholesaler = factory.getWholesaler();
        producer = factory.getProducer();
    }
    
    // ================= Retailer Role Tests =================
    
    @Test
    @DisplayName("Test Retailer supply chain calculation with positive demand")
    void testRetailerSupplyChainCalculator() {
        int demand = 10;
        int result = retailer.supplyChainCalculator(demand);
        
        assertEquals(11, result, "Supply should be demand + 1");
        assertEquals(11, Retailer.supplyChainTestParam, "Test parameter should be updated");
        assertTrue(Retailer.supplyChainTestParam > 0, "Postcondition: supplyChainTestParam > 0");
    }
    
    @Test
    @DisplayName("Test Retailer supply chain calculator guarantees positive result")
    void testRetailerPostconditionEnforcement() {
        int demand = 0;
        int result = retailer.supplyChainCalculator(demand);
        
        assertEquals(1, result, "Even with 0 demand, result should be 1");
        assertTrue(result > 0, "Result must be positive (postcondition)");
    }
    
    @Test
    @DisplayName("Test Retailer initial agent state")
    void testRetailerAgentState() {
        assertEquals("RUNNABLEAGENT", retailer.getAgentState(), "Initial agent state should be RUNNABLEAGENT");
    }
    
    // ================= Wholesaler Role Tests =================
    
    @Test
    @DisplayName("Test Wholesaler receives products")
    void testWholesalerReceiveProducts() {
        wholesaler.receiveProducts(50);
        assertEquals(50, wholesaler.getInventory(), "Inventory should increase by received quantity");
    }
    
    @Test
    @DisplayName("Test Wholesaler distributes products")
    void testWholesalerDistributeProducts() {
        wholesaler.receiveProducts(100);
        wholesaler.distributeProducts(30);
        assertEquals(70, wholesaler.getInventory(), "Inventory should decrease by distributed quantity");
    }
    
    @Test
    @DisplayName("Test Wholesaler cannot distribute more than available")
    void testWholesalerDistributeConstraint() {
        wholesaler.receiveProducts(50);
        wholesaler.distributeProducts(100);
        // Distribution should fail, inventory remains unchanged
        assertEquals(50, wholesaler.getInventory(), "Cannot distribute more than available");
    }
    
    @Test
    @DisplayName("Test Wholesaler starts with empty inventory")
    void testWholesalerInitialInventory() {
        assertEquals(0, wholesaler.getInventory(), "Initial inventory should be 0");
    }
    
    // ================= Producer Role Tests =================
    
    @Test
    @DisplayName("Test Producer produces goods based on demand forecast")
    void testProducerProduceGoods() {
        int demandForecast = 50;
        int produced = producer.produceGoods(demandForecast);
        
        assertEquals(50, produced, "Produced quantity should match demand forecast");
        assertEquals(50, producer.getProduced(), "Produced state should be updated");
    }
    
    @Test
    @DisplayName("Test Producer respects production capacity limit")
    void testProducerCapacityLimit() {
        int demandForecast = 150; // Exceeds capacity of 100
        int produced = producer.produceGoods(demandForecast);
        
        assertEquals(100, produced, "Production should not exceed capacity");
        assertTrue(produced <= producer.getProductionCapacity(), "Produced should respect capacity");
    }
    
    @Test
    @DisplayName("Test Producer initial capacity")
    void testProducerInitialCapacity() {
        assertEquals(100, producer.getProductionCapacity(), "Initial production capacity should be 100");
    }
    
    // ================= Factory Compartment Tests =================
    
    @Test
    @DisplayName("Test Factory initialization")
    void testFactoryInitialization() {
        assertNotNull(factory.getRetailer(), "Retailer should be initialized");
        assertNotNull(factory.getWholesaler(), "Wholesaler should be initialized");
        assertNotNull(factory.getProducer(), "Producer should be initialized");
    }
    
    @Test
    @DisplayName("Test Factory runCycle executes all role interactions")
    void testFactoryRunCycle() {
        // This test verifies that the cycle runs without errors
        assertDoesNotThrow(() -> factory.runCycle(), "Factory runCycle should execute successfully");
    }
    
    @Test
    @DisplayName("Test Factory processSupplyChainCycle with demand-driven production")
    void testFactoryProcessSupplyChainCycle() {
        int customerDemand = 20;
        int result = factory.processSupplyChainCycle(customerDemand);
        
        assertEquals(21, result, "Supply should be demand + 1 (from retailer calculator)");
        assertEquals(21, factory.getProducer().getProduced(), "Producer should produce calculated supply");
        assertEquals(21, factory.getWholesaler().getInventory(), "Wholesaler should have produced quantity");
    }
    
    // ================= Intention Tests =================
    
    @Test
    @DisplayName("Test UserRegistration intention")
    void testUserRegistrationIntention() {
        UserRegistration userReg = new UserRegistration();
        boolean registered = userReg.registeringUser();
        
        assertTrue(registered, "User registration should succeed with 'sampleName'");
        assertEquals("sampleName", UserRegistration.getUserName(), "Username should be set");
    }
    
    @Test
    @DisplayName("Test UserExist intention verification")
    void testUserExistIntention() {
        UserExist userExist = new UserExist();
        
        // Initially without username and ID
        assertFalse(userExist.checkingFunc(), "User should not exist without username and ID");
        
        // Set username and ID
        UserExist.setUserName("TestUser");
        UserExist.setUserID("USER001");
        
        assertTrue(userExist.checkingFunc(), "User should exist with both username and ID");
    }
    
    @Test
    @DisplayName("Test UserExist partial precondition failure")
    void testUserExistPartialPrecondition() {
        UserExist userExist = new UserExist();
        UserExist.setUserName("TestUser");
        // No ID set
        
        assertFalse(userExist.checkingFunc(), "User should not exist without ID even with username");
    }
    
    @Test
    @DisplayName("Test ProductReady intention")
    void testProductReadyIntention() {
        ProductReady productReady = new ProductReady();
        boolean ready = productReady.productStatusCheck();
        
        assertTrue(ready, "Product should be ready after status check");
        assertTrue(ProductReady.getProductStatus(), "Product status should be set to true");
    }
    
    @Test
    @DisplayName("Test ProductReady status modification")
    void testProductReadyStatusModification() {
        ProductReady.setProductStatus(false);
        assertFalse(ProductReady.getProductStatus(), "Product status should be false");
        
        ProductReady productReady = new ProductReady();
        boolean ready = productReady.productStatusCheck();
        
        assertTrue(ready, "Product should be ready after status check");
        assertTrue(ProductReady.getProductStatus(), "Product status should be true");
    }
    
    // ================= Integration Tests =================
    
    @Test
    @DisplayName("Test complete supply chain flow with all components")
    void testCompleteSupplyChainFlow() {
        // Step 1: Customer demand
        int customerDemand = 15;
        
        // Step 2: Retailer calculates supply
        int calculatedSupply = retailer.supplyChainCalculator(customerDemand);
        assertEquals(16, calculatedSupply, "Supply should be demand + 1");
        
        // Step 3: Producer manufactures
        int produced = producer.produceGoods(calculatedSupply);
        assertEquals(16, produced, "Producer should produce calculated supply");
        
        // Step 4: Wholesaler receives and stores
        wholesaler.receiveProducts(produced);
        assertEquals(produced, wholesaler.getInventory(), "Wholesaler should have all produced goods");
        
        // Step 5: Verify all intentions
        UserRegistration userReg = new UserRegistration();
        assertTrue(userReg.registeringUser(), "User should register successfully");
        
        ProductReady productReady = new ProductReady();
        assertTrue(productReady.productStatusCheck(), "Product should be marked as ready");
        
        // Final assertion on inventory
        assertTrue(wholesaler.getInventory() > 0, "Wholesaler should have inventory");
    }
    
    @Test
    @DisplayName("Test supply chain with multiple cycles")
    void testMultipleCycles() {
        int[] demands = {5, 10, 15, 8};
        int totalInventory = 0;
        
        for (int demand : demands) {
            int supply = retailer.supplyChainCalculator(demand);
            int produced = producer.produceGoods(supply);
            wholesaler.receiveProducts(produced);
            totalInventory = wholesaler.getInventory();
        }
        
        int expectedTotal = 6 + 11 + 16 + 9; // Each demand + 1
        assertEquals(expectedTotal, totalInventory, "Total inventory should be sum of all productions");
    }
}
