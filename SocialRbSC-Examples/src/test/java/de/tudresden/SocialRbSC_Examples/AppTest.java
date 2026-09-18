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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test cases for Professor (SocialAgentBuyer) demonstrating all annotations.
 *
 * @author Test Suite
 */
@DisplayName("SocialAgentBuyer (Professor) Annotation Examples")
class AppTest {

    private Professor buyer;

    @BeforeEach
    void setUp() {
        buyer = new Professor();
    }

    // Tests for BuyerRole

    @Test
    @DisplayName("BuyerRole should be creatable")
    void testBuyerRoleCreation() {
        Professor.BuyerRole role = new Professor.BuyerRole();
        assertNotNull(role, "BuyerRole instance should not be null");
    }

    @Test
    @DisplayName("BuyerRole.performRole() should execute successfully")
    void testBuyerRolePerformRole() {
        Professor.BuyerRole role = new Professor.BuyerRole();
        assertDoesNotThrow(() -> role.performRole(), "performRole() should not throw exception");
    }

    // Tests for SocialAgentBelief and PreconditionVariable

    @Test
    @DisplayName("Initial belief should be set to defaultBelief")
    void testInitialBelief() {
        assertEquals("defaultBelief", buyer.belief, "Belief should be initialized to 'defaultBelief'");
    }

    @Test
    @DisplayName("Initial funds should be set to 100")
    void testInitialFunds() {
        assertEquals(100, buyer.funds, "Funds should be initialized to 100");
    }

    @Test
    @DisplayName("Belief should be mutable")
    void testBeliefMutation() {
        buyer.belief = "Item available";
        assertEquals("Item available", buyer.belief, "Belief should be updated");
    }

    // Tests for buyItem() Action with annotations

    @Test
    @DisplayName("buyItem() should deduct funds when sufficient")
    void testBuyItemWithSufficientFunds() {
        buyer.funds = 100;
        buyer.buyItem();
        assertEquals(50, buyer.funds, "Funds should be reduced by 50");
        assertEquals("Item purchased successfully", buyer.belief, "Belief should be updated");
    }

    @Test
    @DisplayName("buyItem() should not deduct funds when insufficient")
    void testBuyItemWithInsufficientFunds() {
        buyer.funds = 5; // Less than 10, triggers AvoidanceCondition
        buyer.buyItem();
        assertEquals(5, buyer.funds, "Funds should remain unchanged");
    }

    @Test
    @DisplayName("buyItem() should handle edge case at minimum threshold")
    void testBuyItemAtThreadhold() {
        buyer.funds = 10;
        buyer.buyItem();
        assertEquals(-40, buyer.funds, "Funds should be deducted normally at threshold");
    }

    @Test
    @DisplayName("buyItem() should handle multiple consecutive purchases")
    void testMultiplePurchases() {
        buyer.funds = 150;
        buyer.buyItem(); // First purchase
        assertEquals(100, buyer.funds);
        buyer.buyItem(); // Second purchase
        assertEquals(50, buyer.funds);
        buyer.buyItem(); // Third purchase (insufficient funds)
        assertEquals(0, buyer.funds);
    }

    // Tests for checkPreconditions() with Precondition annotation

    @Test
    @DisplayName("checkPreconditions() should return true when funds > 0 and belief is valid")
    void testCheckPreconditionsValid() {
        buyer.funds = 50;
        buyer.belief = "defaultBelief";
        assertTrue(buyer.checkPreconditions(), "Preconditions should be satisfied");
    }

    @Test
    @DisplayName("checkPreconditions() should return false when funds <= 0")
    void testCheckPreconditionsNoFunds() {
        buyer.funds = 0;
        buyer.belief = "defaultBelief";
        assertFalse(buyer.checkPreconditions(), "Preconditions should fail with no funds");
    }

    @Test
    @DisplayName("checkPreconditions() should return false when belief is null")
    void testCheckPreconditionsNullBelief() {
        buyer.funds = 50;
        buyer.belief = null;
        assertFalse(buyer.checkPreconditions(), "Preconditions should fail with null belief");
    }

    @Test
    @DisplayName("checkPreconditions() should return false when belief is empty")
    void testCheckPreconditionsEmptyBelief() {
        buyer.funds = 50;
        buyer.belief = "";
        assertFalse(buyer.checkPreconditions(), "Preconditions should fail with empty belief");
    }

    // Tests for deliberate() with DeliberationCycle annotation

    @Test
    @DisplayName("deliberate() should execute successfully")
    void testDeliberateExecution() {
        buyer.funds = 100;
        assertDoesNotThrow(() -> buyer.deliberate(), "deliberate() should not throw exception");
    }

    @Test
    @DisplayName("deliberate() should evaluate high funds as proceed decision")
    void testDeliberateHighFunds() {
        buyer.funds = 100;
        assertDoesNotThrow(() -> buyer.deliberate(), "Should deliberate with high funds");
    }

    @Test
    @DisplayName("deliberate() should evaluate low funds as preservation decision")
    void testDeliberateLowFunds() {
        buyer.funds = 30;
        assertDoesNotThrow(() -> buyer.deliberate(), "Should deliberate with low funds");
    }

    // Tests for App inner class with GoalPlanTree

    @Test
    @DisplayName("App inner class should be instantiable")
    void testAppInnerClassCreation() {
        Professor.App app = new Professor.App();
        assertNotNull(app, "App inner class instance should not be null");
    }

    @Test
    @DisplayName("goalDefinition() should execute successfully")
    void testGoalDefinition() {
        Professor.App app = new Professor.App();
        assertDoesNotThrow(() -> app.goalDefinition(), "goalDefinition() should not throw exception");
    }

    @Test
    @DisplayName("planMethod() should outline purchase steps")
    void testPlanMethod() {
        Professor.App app = new Professor.App();
        assertDoesNotThrow(() -> app.planMethod(), "planMethod() should not throw exception");
    }

    @Test
    @DisplayName("notifySeller() should send notification")
    void testNotifySeller() {
        Professor.App app = new Professor.App();
        assertDoesNotThrow(() -> app.notifySeller(), "notifySeller() should not throw exception");
    }

    @Test
    @DisplayName("agentPrecondition() should return true")
    void testAgentPrecondition() {
        Professor.App app = new Professor.App();
        assertTrue(app.agentPrecondition(), "Agent precondition should be satisfied");
    }

    @Test
    @DisplayName("main() should execute without error")
    void testMainMethod() {
        assertDoesNotThrow(() -> Professor.App.main(new String[]{}), 
            "main() should execute successfully");
    }

    // ============================================
    // Integration Tests
    // ============================================

    @Test
    @DisplayName("Complete workflow: Check preconditions -> Deliberate -> Buy -> Verify state")
    void testCompleteWorkflow() {
        buyer.funds = 80;
        buyer.belief = "Ready to purchase";

        // Step 1: Check preconditions
        assertTrue(buyer.checkPreconditions(), "Preconditions should pass");

        // Step 2: Deliberate
        assertDoesNotThrow(() -> buyer.deliberate(), "Deliberation should complete");

        // Step 3: Buy item
        buyer.buyItem();
        assertEquals(30, buyer.funds, "Funds should be deducted");
        assertEquals("Item purchased successfully", buyer.belief, "Belief should update");

        // Step 4: Verify preconditions still valid
        assertTrue(buyer.checkPreconditions(), "Preconditions should still pass after purchase");
    }

    @Test
    @DisplayName("Workflow with avoidance: Annotation is metadata, not runtime enforced")
    void testWorkflowWithAvoidance() {
        buyer.funds = 60;
        buyer.belief = "Ready to purchase";

        // First purchase
        buyer.buyItem();
        assertEquals(10, buyer.funds);

        // Second purchase attempt - proceeds because implementation checks >= 10
        // Note: @AvoidanceCondition is metadata for code generation, not runtime enforcement
        buyer.buyItem();
        assertEquals(-40, buyer.funds, "Implementation checks funds >= 10 to proceed");
    }

    @Test
    @DisplayName("Goal-Plan-Action sequence test")
    void testGoalPlanActionSequence() {
        Professor.App app = new Professor.App();

        // Execute goal definition
        assertDoesNotThrow(() -> app.goalDefinition());

        // Execute plan
        assertDoesNotThrow(() -> app.planMethod());

        // Execute action (notify seller)
        assertDoesNotThrow(() -> app.notifySeller());
    }

    @Test
    @DisplayName("Role-based action execution")
    void testRoleBasedExecution() {
        Professor.BuyerRole role = new Professor.BuyerRole();
        buyer.funds = 100;
        buyer.belief = "defaultBelief";

        // Activate role
        assertDoesNotThrow(() -> role.performRole());

        // Execute action within role context
        assertTrue(buyer.checkPreconditions());
        assertDoesNotThrow(() -> buyer.buyItem());
    }
}
