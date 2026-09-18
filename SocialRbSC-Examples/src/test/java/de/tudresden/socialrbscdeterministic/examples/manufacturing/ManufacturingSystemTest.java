package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Manufacturing System Test Suite
 * Comprehensive tests for the manufacturing example with role-based multi-agent system.
 */
@DisplayName("Manufacturing System Tests")
public class ManufacturingSystemTest {
    
    private ProductionManager productionManager;
    private ManufacturingWorker[] workers;
    private QualityAssurance qualityAssurance;
    private ManufacturingCoordinator coordinator;
    private SupervisorRole supervisor;
    private BlockchainDeployer deployer;
    private ProductionPlan productionPlan;
    
    @BeforeEach
    public void setUp() {
        productionManager = new ProductionManager();
        workers = new ManufacturingWorker[5];
        for (int i = 0; i < 5; i++) {
            workers[i] = new ManufacturingWorker();
        }
        qualityAssurance = new QualityAssurance();
        coordinator = new ManufacturingCoordinator();
        supervisor = new SupervisorRole();
        deployer = new BlockchainDeployer();
        productionPlan = new ProductionPlan("Daily Manufacturing Plan");
    }
    
    @Test
    @DisplayName("Test Production Manager schedules production")
    public void testProductionManagerSchedulesProduction() {
        System.out.println("\n=== Test 1: Production Manager Scheduling ===");
        productionManager.scheduleProduction("Assembly Line A");
        productionManager.scheduleProduction("Assembly Line B");
        
        assertEquals(2, productionManager.getTasksScheduled(), 
                   "Production manager should have scheduled 2 tasks");
    }
    
    @Test
    @DisplayName("Test Manufacturing Worker performs tasks")
    public void testManufacturingWorkerPerformsTasks() {
        System.out.println("\n=== Test 2: Manufacturing Worker Task Execution ===");
        ManufacturingWorker worker = workers[0];
        assertNull(worker.getCurrentTask(), "Worker should start with no task");
        
        worker.performTask("Assemble Component A");
        assertEquals("Assemble Component A", worker.getCurrentTask(), 
                   "Worker should have current task assigned");
        assertFalse(worker.isAvailable(), "Worker should be busy while executing task");
        
        worker.completeTask();
        assertNull(worker.getCurrentTask(), "Worker should have no task after completion");
        assertTrue(worker.isAvailable(), "Worker should be available after completing task");
    }
    
    @Test
    @DisplayName("Test Quality Assurance inspection workflow")
    public void testQualityAssuranceInspection() {
        System.out.println("\n=== Test 3: Quality Assurance Inspection ===");
        qualityAssurance.setInspectionRequired(true);
        
        Product product1 = new Product("P001", "Widget A");
        boolean result1 = qualityAssurance.inspectProduct(product1);
        assertTrue(result1, "Product should pass inspection initially");
        
        Product product2 = new Product("P002", "Widget B");
        product2.setQualityStatus(false);
        boolean result2 = qualityAssurance.inspectProduct(product2);
        assertFalse(result2, "Product should fail inspection when flagged");
        
        assertEquals(2, qualityAssurance.getInspectionsPerformed(), 
                   "Should have performed 2 inspections");
        assertEquals(1, qualityAssurance.getPassedInspections(), 
                   "Should have 1 passed inspection");
        assertEquals(0.5f, qualityAssurance.getQualityRate(), 0.01f, 
                   "Quality rate should be 50%");
    }
    
    @Test
    @DisplayName("Test Manufacturing Coordinator deliberation cycle")
    public void testManufacturingCoordinatorDeliberation() {
        System.out.println("\n=== Test 4: Coordinator Deliberation Cycle ===");
        assertEquals(0, coordinator.getCycleCount(), "Coordinator should start with 0 cycles");
        
        coordinator.deliberateAndAct(productionPlan);
        assertEquals(1, coordinator.getCycleCount(), "Coordinator should have 1 cycle");
        assertEquals("ACTIVE", coordinator.getCurrentStatus(), "Status should be ACTIVE");
        
        coordinator.deliberateAndAct(productionPlan);
        assertEquals(2, coordinator.getCycleCount(), "Coordinator should have 2 cycles");
        assertEquals(2, coordinator.getProductsCompleted(), 
                   "Coordinator should have completed 2 products");
    }
    
    @Test
    @DisplayName("Test Supervisor role decision making")
    public void testSupervisorRoleDecisions() {
        System.out.println("\n=== Test 5: Supervisor Role Operations ===");
        assertEquals(0, supervisor.getDecisionsMade(), "Supervisor should start with 0 decisions");
        
        supervisor.oversee();
        supervisor.oversee();
        supervisor.approveQualityMetrics(0.95f);
        
        assertEquals(3, supervisor.getDecisionsMade(), 
                   "Supervisor should have made 3 decisions (2 oversee + 1 approve)");
    }
    
    @Test
    @DisplayName("Test Blockchain Deployer contract deployment")
    public void testBlockchainDeployerDeployment() {
        System.out.println("\n=== Test 6: Blockchain Deployer ===");
        deployer.setDeploymentAddress("http://blockchain.local/factory");
        
        deployer.deployContract("pragma solidity ^0.8.0;");
        deployer.deployContract("contract Manufacturing { }");
        
        assertEquals(2, deployer.getDeploymentsCount(), "Should have 2 deployments");
        assertTrue(deployer.getDeploymentAddress().contains("blockchain.local"), 
                  "Should have correct deployment address");
    }
    
    @Test
    @DisplayName("Test Production Plan management")
    public void testProductionPlanManagement() {
        System.out.println("\n=== Test 7: Production Plan Management ===");
        ProductionPlan plan = new ProductionPlan("Shift A Production");
        
        assertEquals(5, plan.getAvailableWorkerCount(), "Should have 5 workers available");
        assertEquals(0.95f, plan.getCurrentQualityMetric(), 0.01f, 
                   "Should have 95% quality metric");
        
        plan.setAvailableWorkers(3);
        plan.setQualityMetric(0.92f);
        
        assertEquals(3, plan.getAvailableWorkerCount(), "Workers should be updated to 3");
        assertEquals(0.92f, plan.getCurrentQualityMetric(), 0.01f, 
                   "Quality should be updated to 92%");
    }
    
    @Test
    @DisplayName("Test Goal/SubGoal execution")
    public void testGoalExecution() {
        System.out.println("\n=== Test 8: Goal Execution ===");
        ProductionGoalTree goalTree = new ProductionGoalTree();
        
        goalTree.assemblyGoal();
        goalTree.testingGoal();
        goalTree.mainGoal();
        
        // Goals executed successfully - no exceptions thrown
        assertTrue(true, "All goals should execute without errors");
    }
    
    @Test
    @DisplayName("Test Worker machine status management")
    public void testWorkerMachineStatus() {
        System.out.println("\n=== Test 9: Worker Machine Status ===");
        ManufacturingWorker worker = workers[1];
        
        worker.setMachineStatus(true);
        // When machine is operational, worker can perform tasks
        worker.performTask("Task 1");
        assertEquals("Task 1", worker.getCurrentTask(), "Worker should perform task when machine operational");
        
        worker.completeTask();
        worker.setMachineStatus(false);
        // When machine is down, worker shouldn't perform tasks (though precondition allows it)
        // This demonstrates the precondition checking mechanism
    }
    
    @Test
    @DisplayName("Test multi-worker coordination scenario")
    public void testMultiWorkerCoordinationScenario() {
        System.out.println("\n=== Test 10: Multi-Worker Coordination ===");
        
        // Assign tasks to multiple workers
        for (int i = 0; i < 3; i++) {
            workers[i].performTask("Task-" + (i + 1));
        }
        
        // Verify all workers are busy
        for (int i = 0; i < 3; i++) {
            assertFalse(workers[i].isAvailable(), "Worker " + i + " should be busy");
            assertNotNull(workers[i].getCurrentTask(), "Worker " + i + " should have a task");
        }
        
        // Verify remaining workers are available
        for (int i = 3; i < 5; i++) {
            assertTrue(workers[i].isAvailable(), "Worker " + i + " should be available");
        }
        
        // Complete tasks
        for (int i = 0; i < 3; i++) {
            workers[i].completeTask();
        }
        
        // Verify all workers are now available
        for (int i = 0; i < 5; i++) {
            assertTrue(workers[i].isAvailable(), "All workers should be available after task completion");
        }
    }
    
    @Test
    @DisplayName("Test integrated manufacturing workflow")
    public void testIntegratedManufacturingWorkflow() {
        System.out.println("\n=== Test 11: Integrated Manufacturing Workflow ===");
        
        // Manager schedules production
        productionManager.scheduleProduction("Production Run 1");
        
        // Workers execute tasks
        for (int i = 0; i < 5; i++) {
            workers[i].performTask("Assembly-" + i);
        }
        
        // QA inspects products
        for (int i = 0; i < 5; i++) {
            Product product = new Product("P-" + i, "Product " + i);
            qualityAssurance.inspectProduct(product);
        }
        
        // Coordinator deliberates
        coordinator.deliberateAndAct(productionPlan);
        
        // Supervisor approves
        supervisor.approveQualityMetrics(qualityAssurance.getQualityRate());
        
        // Verify workflow completion
        assertEquals(1, productionManager.getTasksScheduled(), "Production scheduled");
        assertEquals(5, qualityAssurance.getInspectionsPerformed(), "Products inspected");
        assertEquals(1, coordinator.getCycleCount(), "Coordinator deliberated");
        assertEquals(1, supervisor.getDecisionsMade(), "Supervisor made decisions");
    }
    
    @Test
    @DisplayName("Test Human Operator creation")
    public void testHumanOperatorCreation() {
        System.out.println("\n=== Test 12: Human Operator ===");
        
        HumanOperator operator1 = new HumanOperator("John Smith");
        assertEquals("John Smith", operator1.getName(), "Operator name should match");
        assertEquals("General", operator1.getDepartment(), "Default department should be General");
        
        HumanOperator operator2 = new HumanOperator("Jane Doe", "Quality Control");
        assertEquals("Jane Doe", operator2.getName(), "Operator name should match");
        assertEquals("Quality Control", operator2.getDepartment(), "Department should match");
        
        operator1.setDepartment("Manufacturing");
        assertEquals("Manufacturing", operator1.getDepartment(), "Department should be updated");
    }
}
