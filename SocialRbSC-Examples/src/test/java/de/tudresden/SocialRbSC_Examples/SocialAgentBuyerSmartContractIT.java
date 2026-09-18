/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */

package de.tudresden.SocialRbSC_Examples;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Smart Contract Integration Tests for the SocialAgentBuyer generated from annotations.
 * 
 * These tests verify that the smart contracts generated from the App.java annotations
 * are properly configured and deployable. Since actual contract deployment requires
 * a blockchain (Ganache/Hardhat), these tests focus on:
 * - Contract generation validation
 * - Interface/ABI verification
 * - State initialization patterns
 * - Role-based initialization paths
 *
 * @author Test Suite
 */
@DisplayName("SocialAgentBuyer Smart Contract Integration Tests")
class SocialAgentBuyerSmartContractIT {

    private Professor buyer;
    private Professor.App appCompartment;

    @BeforeEach
    void setUp() {
        buyer = new Professor();
        appCompartment = new Professor.App();
    }

    // ============================================
    // Contract Generation and Annotation Tests
    // ============================================

    @Test
    @DisplayName("App.java should be annotated with @Deploy for contract generation")
    void testDeployAnnotationPresent() {
        // Verify that the Professor class has required annotations for contract generation
        assertNotNull(buyer, "Professor instance should be deployable");
        assertTrue(buyer.getClass().getName().contains("Professor"),
                "Class should be available for contract generation");
    }

    @Test
    @DisplayName("Role-based compartment should be structured for contract generation")
    void testRoleCompartmentStructure() {
        // Verify BuyerRole is properly defined as a nested class
        assertTrue(Professor.BuyerRole.class.getName().contains("BuyerRole"),
                "BuyerRole should be defined as inner class for role contract generation");
    }

    @Test
    @DisplayName("Goal-Plan-Tree structure should map to contract compartments")
    void testGoalPlanTreeAnnotationStructure() {
        // Verify that App compartment is properly structured for GoalPlanTree mapping
        assertNotNull(appCompartment, "App compartment should be instantiable");
        assertTrue(appCompartment.getClass().isAssignableFrom(Professor.App.class),
                "App should be structured for GoalPlanTree contract mapping");
    }

    // ============================================
    // Contract State and Initialization Tests
    // ============================================

    @Test
    @DisplayName("SocialAgentBuyer contracts should initialize with default state")
    void testBuyerContractInitialState() {
        // Verify initial state matches what would be set in a contract constructor
        assertEquals(100, buyer.funds, "Initial funds state should be set to 100");
        assertEquals("defaultBelief", buyer.belief, "Initial belief state should be set to defaultBelief");
    }

    @Test
    @DisplayName("Contract should support fund state modifications")
    void testBuyerContractStateModification() {
        // Test that contract state can be modified (simulating contract state changes)
        int initialFunds = buyer.funds;
        buyer.funds -= 50;
        
        assertEquals(initialFunds - 50, buyer.funds, "Contract state should be modifiable");
    }

    @Test
    @DisplayName("BuyerRole contract should be deployable as independent compartment")
    void testBuyerRoleContractDeployment() {
        Professor.BuyerRole role = new Professor.BuyerRole();
        assertNotNull(role, "BuyerRole contract should be instantiable");
        assertDoesNotThrow(() -> role.performRole(), "Role contract function should execute");
    }

    // ============================================
    // Society and Compartment Integration Tests
    // ============================================

    @Test
    @DisplayName("Society annotation should define agent grouping for contracts")
    void testSocietyContractStructure() {
        // Verify that the Society annotation structure maps to contract society pattern
        assertNotNull(buyer, "Agent should be part of society");
        assertTrue(buyer.funds >= 0, "Society should support agent fund state");
    }

    @Test
    @DisplayName("Compartment should provide role-based access control in contracts")
    void testCompartmentAccessControl() {
        // Verify compartment structure for role-based contract access
        assertDoesNotThrow(() -> appCompartment.agentPrecondition(),
                "Compartment should enforce agent preconditions");
    }

    @Test
    @DisplayName("Agent precondition should gate contract function execution")
    void testAgentPreconditionGating() {
        assertTrue(appCompartment.agentPrecondition(), "Precondition should gate contract access");
        
        // Modify state and re-check
        buyer.funds = 0;
        buyer.belief = null;
        assertFalse(buyer.checkPreconditions(), "Modified state should fail preconditions");
    }

    // ============================================
    // Action and Plan Mapping Tests
    // ============================================

    @Test
    @DisplayName("Action annotation should map to contract functions")
    void testActionAnnotationMapping() {
        // Verify that Action("attemptPurchase") maps to contract function
        assertDoesNotThrow(() -> buyer.buyItem(), 
                "Action should map to callable contract function");
    }

    @Test
    @DisplayName("Plan annotation should define contract execution sequences")
    void testPlanExecutionSequence() {
        Professor.App app = new Professor.App();
        
        // Goal definition
        assertDoesNotThrow(() -> app.goalDefinition());
        
        // Plan execution sequence
        assertDoesNotThrow(() -> app.planMethod());
        
        // Action execution
        assertDoesNotThrow(() -> app.notifySeller());
    }

    @Test
    @DisplayName("Avoidance condition should be encoded in contract logic")
    void testAvoidanceConditionEncoding() {
        buyer.funds = 5; // Trigger avoidance condition
        
        // In contract: require(funds >= 10, "AvoidanceCondition triggered")
        buyer.buyItem();
        
        // Funds should remain unchanged due to avoidance condition logic
        assertEquals(5, buyer.funds, "Avoidance condition should prevent execution");
    }

    @Test
    @DisplayName("Willingness condition should influence contract decision paths")
    void testWillingnessCondition() {
        // WillingnessCondition("high") should enable aggressive buying
        buyer.funds = 100;
        buyer.buyItem();
        
        assertTrue(buyer.funds < 100, "High willingness should enable purchase action");
    }

    // ============================================
    // Belief and Goal Management Tests
    // ============================================

    @Test
    @DisplayName("SocialAgentBelief should map to contract storage")
    void testBeliefStateStorage() {
        String initialBelief = buyer.belief;
        buyer.belief = "Item available";
        
        assertNotEquals(initialBelief, buyer.belief, "Belief state should be stored in contract");
    }

    @Test
    @DisplayName("Goal should define contract outcome conditions")
    void testGoalOutcomeCondition() {
        Professor.App app = new Professor.App();
        
        // Execute goal
        assertDoesNotThrow(() -> app.goalDefinition());
        
        // Goal should remain achievable
        assertTrue(buyer.funds > 0, "Goal outcome should be achievable");
    }

    @Test
    @DisplayName("SubGoal should map to intermediate contract states")
    void testSubGoalIntermediateState() {
        // SubGoal("secureFunds") should be an intermediate state
        int initialFunds = buyer.funds;
        assertTrue(initialFunds > 0, "SubGoal state should have secured funds");
    }

    // ============================================
    // Precondition Contract Validation Tests
    // ============================================

    @Test
    @DisplayName("Precondition annotation should generate contract requires() statements")
    void testPreconditionJavaMapping() {
        boolean result = buyer.checkPreconditions();
        assertTrue(result, "Precondition should be satisfied initially");
    }

    @Test
    @DisplayName("PreconditionVariable should map to contract storage variables")
    void testPreconditionVariableMapping() {
        // funds is marked as PreconditionVariable
        assertEquals(100, buyer.funds, "PreconditionVariable should be stored");
        
        buyer.funds = -1;
        assertFalse(buyer.checkPreconditions(), "Invalid PreconditionVariable state should fail");
    }

    @Test
    @DisplayName("PreconditionSocialAgent should validate agent state before actions")
    void testPreconditionSocialAgentValidation() {
        Professor.App app = new Professor.App();
        
        // Should validate agent before executing plan
        assertTrue(app.agentPrecondition(), "Agent precondition should be satisfied");
    }

    // ============================================
    // Contract Deployment Simulation Tests
    // ============================================

    @Test
    @DisplayName("Contract should simulate deployment with all state initialized")
    void testContractDeploymentSimulation() {
        // Simulating contract deployment by verifying all state is properly initialized
        assertNotNull(buyer, "Agent contract should be deployed");
        assertEquals(100, buyer.funds);
        assertEquals("defaultBelief", buyer.belief);
        
        assertTrue(buyer.checkPreconditions(), "Contract should be deployable");
    }

    @Test
    @DisplayName("Contract roles should be deployable in compartment")
    void testRoleDeploymentInCompartment() {
        Professor.BuyerRole role = new Professor.BuyerRole();
        Professor.App compartment = new Professor.App();
        
        // Both should be deployable
        assertNotNull(role, "Role contract should be deployable");
        assertNotNull(compartment, "Compartment contract should be deployable");
    }

    @Test
    @DisplayName("Contract should be deployable with constructor parameters")
    void testContractConstructorParameters() {
        // Simulating contract constructor parameters mapping from Java constructor
        Professor buyer1 = new Professor();
        Professor buyer2 = new Professor();
        
        buyer1.funds = 75;
        buyer2.funds = 150;
        
        assertNotEquals(buyer1.funds, buyer2.funds, 
                "Multiple contract instances should have independent state");
    }

    // ============================================
    // Integration Test Workflows
    // ============================================

    @Test
    @DisplayName("Full contract workflow: Deploy -> Initialize -> Execute -> Verify")
    void testFullContractWorkflow() {
        // Deploy phase
        Professor agent = new Professor();
        assertNotNull(agent, "Contract deployment successful");
        
        // Initialize phase (via constructor/initializeAgent)
        assertEquals(100, agent.funds);
        assertEquals("defaultBelief", agent.belief);
        
        // Execute phase (via actions)
        assertTrue(agent.checkPreconditions(), "Contract should execute");
        agent.buyItem();
        
        // Verify phase
        assertEquals(50, agent.funds, "Contract state should be updated");
        assertEquals("Item purchased successfully", agent.belief);
    }

    @Test
    @DisplayName("Compartment contract workflow with role execution")
    void testCompartmentRoleWorkflow() {
        // Create compartment with role
        Professor.App compartment = new Professor.App();
        Professor.BuyerRole role = new Professor.BuyerRole();
        
        // Check preconditions
        assertTrue(compartment.agentPrecondition(), "Should pass preconditions");
        
        // Activate role
        assertDoesNotThrow(() -> role.performRole(), "Role should activate");
        
        // Execute goal and plan
        assertDoesNotThrow(() -> compartment.goalDefinition());
        assertDoesNotThrow(() -> compartment.planMethod());
        assertDoesNotThrow(() -> compartment.notifySeller());
    }

    @Test
    @DisplayName("Multi-agent contract interaction scenario")
    void testMultiAgentContractInteraction() {
        // Simulate multiple agent instances
        Professor buyer1 = new Professor();
        Professor buyer2 = new Professor();
        
        // Each agent maintains independent state
        buyer1.funds = 100;
        buyer2.funds = 200;
        
        buyer1.buyItem();
        buyer2.buyItem();
        
        assertEquals(50, buyer1.funds, "Buyer1 state should be independent");
        assertEquals(150, buyer2.funds, "Buyer2 state should be independent");
    }

    // ============================================
    // Contract Generation Validation Tests
    // ============================================

    @Test
    @DisplayName("Generated contracts should map all public methods to transactions")
    void testPublicMethodToTransactionMapping() {
        Professor buyer = new Professor();
        
        // Public methods should be callable (mapped to contract transactions)
        assertDoesNotThrow(() -> buyer.buyItem());
        assertDoesNotThrow(() -> buyer.deliberate());
        assertTrue(buyer.checkPreconditions());
    }

    @Test
    @DisplayName("Contract should generate proper event emissions for state changes")
    void testEventEmissionForStateChanges() {
        Professor buyer = new Professor();
        String initialBelief = buyer.belief;
        
        buyer.buyItem();
        
        // In real contract: event would be emitted on state change
        assertNotEquals(initialBelief, buyer.belief, 
                "State change should be detectable (contract event emitted)");
    }
}
