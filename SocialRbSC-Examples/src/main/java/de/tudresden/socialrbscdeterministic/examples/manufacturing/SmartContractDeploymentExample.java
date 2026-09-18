/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program.
 * 
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentGoal;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentPlan;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Smart Contract Deployment Example
 * 
 * Demonstrates how @Deploy scans the entire file for annotations and generates:
 * 1. Solidity contracts for each annotated component
 * 2. An Orchestrator contract managing component interactions
 * 3. A Java-to-Solidity runtime environment
 * 4. A deployer class with web3j integration
 * 
 * When compiled:
 * - Generates: generatedsmartcontract/contracts/{Component}.sol
 * - Generates: generatedsmartcontract/contracts/SmartContractDeploymentExampleOrchestrator.sol
 * - Generates: generatedsmartcontract/runner/SmartContractDeploymentExampleDeployer.java
 * - Generates: {package}/runtime/SmartContractDeploymentExampleSolidityRuntime.java
 */
@Deploy
@SocialAgent(agentName = "ManufacturingAgent", addRole = true)
public class SmartContractDeploymentExample {

    // ========== SOCIAL AGENT STATE ==========
    private String agentId = "mfg-agent-001";
    private String agentName = "Manufacturing Coordinator";
    private boolean isActive = true;

    // ========== GOALS AND PLANS ==========
    @SocialAgentGoal
    public static class ManufacturingGoal {
        public void achieve() {
            System.out.println("[GOAL] Manufacturing excellence goal");
        }
    }

    @SocialAgentPlan(triggeredPlan = "coordinationPlan", defineSociety = "ManufacturingEcosystem")
    public static class CoordinationPlan {
        public void execute() {
            System.out.println("[PLAN] Executing coordination plan");
        }
    }

    // ========== COMPARTMENTS ==========
    /**
     * Production Compartment - Context for production activities
     */
    @Compartment
    public interface ProductionCompartment {
        void startProduction();
        void stopProduction();
        void monitorProgress();
    }

    /**
     * Quality Compartment - Context for quality assurance
     */
    @Compartment
    public interface QualityCompartment {
        void checkQuality();
        void reportQualityMetrics();
    }

    // ========== ROLES ==========
    /**
     * Production Manager Role - Coordinates production activities
     */
    @Role
    public static class ProductionManagerRole {
        private int tasksScheduled = 0;

        @RoleMethod
        public void scheduleProduction(String plan) {
            tasksScheduled++;
            System.out.println("[PRODUCTION ROLE] Scheduled production: " + plan);
        }

        @RoleMethod
        public void monitorExecution() {
            System.out.println("[PRODUCTION ROLE] Monitoring execution. Tasks: " + tasksScheduled);
        }

        @RoleMethod
        public int getTaskCount() {
            return tasksScheduled;
        }
    }

    /**
     * Quality Assurance Role - Ensures quality standards
     */
    @Role
    public static class QualityAssuranceRole {
        private float qualityScore = 0.0f;

        @RoleMethod
        public void performQualityCheck(String productName) {
            qualityScore = 95.5f;
            System.out.println("[QUALITY ROLE] Checked " + productName + ": " + qualityScore);
        }

        @RoleMethod
        public void reportMetrics() {
            System.out.println("[QUALITY ROLE] Current quality score: " + qualityScore);
        }
    }

    /**
     * Supply Chain Role - Manages supply chain operations
     */
    @Role
    public static class SupplyChainRole {
        private int inventoryLevel = 100;

        @RoleMethod
        public void updateInventory(int quantity) {
            inventoryLevel += quantity;
            System.out.println("[SUPPLY CHAIN ROLE] Inventory updated: " + inventoryLevel);
        }

        @RoleMethod
        public void procureRawMaterials(String material) {
            System.out.println("[SUPPLY CHAIN ROLE] Procuring: " + material);
        }
    }

    /**
     * Coordination Role - Coordinates between different components
     */
    @Role
    public static class CoordinationRole {
        @RoleMethod
        public void synchronizeComponents() {
            System.out.println("[COORDINATION ROLE] Synchronizing all components");
        }

        @RoleMethod
        public void resolveConflicts() {
            System.out.println("[COORDINATION ROLE] Resolving resource conflicts");
        }
    }

    // ========== GOAL MANAGEMENT ==========
    /**
     * Root Goal - Achieve manufacturing excellence
     */
    public static class ManufacturingExcellenceGoal {
        @RoleMethod
        public void achieve() {
            System.out.println("[GOAL] Pursuing manufacturing excellence");
        }
    }

    /**
     * Subgoal - Optimize production efficiency
     */
    public static class ProductionEfficiencyGoal {
        @RoleMethod
        public void achieve() {
            System.out.println("[SUBGOAL] Optimizing production efficiency");
        }
    }

    /**
     * Subgoal - Maintain quality standards
     */
    public static class QualityMaintenanceGoal {
        @RoleMethod
        public void achieve() {
            System.out.println("[SUBGOAL] Maintaining quality standards");
        }
    }

    // ========== PUBLIC INTERFACE ==========
    /**
     * Initializes the manufacturing agent
     */
    public void initialize() {
        System.out.println("Initializing " + agentName + " (" + agentId + ")");
    }

    /**
     * Executes the agent's main logic
     */
    public void execute() {
        System.out.println("Executing manufacturing coordination");
    }

    /**
     * Shuts down the agent
     */
    public void shutdown() {
        isActive = false;
        System.out.println("Agent " + agentId + " shutdown complete");
    }

    public String getAgentId() {
        return agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public boolean isActive() {
        return isActive;
    }
}
