package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;

/**
 * Manufacturing Coordinator Social Agent
 * Coordinates production activities, manages beliefs, goals, and deliberation cycles.
 */
@SocialAgent(agentName = "ManufacturingCoordinator", addRole = true)
public class ManufacturingCoordinator {
    // Beliefs
    private String currentProductionStatus = "IDLE";
    private int availableWorkers = 5;
    private float qualityScore = 0.95f;
    
    // Goals and Plans
    private String primaryGoal = "Maximize production output while maintaining quality";
    private String activeStrategy = "EFFICIENT_PRODUCTION";
    
    private int cycleCount = 0;
    private int productsCompleted = 0;
    
    /**
     * Deliberation cycle: sense, deliberate, act
     */
    public void deliberateAndAct(ProductionPlan plan) {
        cycleCount++;
        System.out.println("[COORDINATOR_CYCLE_" + cycleCount + "] Starting deliberation");
        updateBeliefs(plan);
        deliberateOptions(plan);
        selectAndExecutePlan(plan);
        updateIntentions();
    }
    
    private void updateBeliefs(ProductionPlan plan) {
        currentProductionStatus = "ACTIVE";
        availableWorkers = plan.getAvailableWorkerCount();
        qualityScore = plan.getCurrentQualityMetric();
        System.out.println("[COORDINATOR] Updated beliefs: status=" + currentProductionStatus + 
                         ", workers=" + availableWorkers + ", quality=" + qualityScore);
    }
    
    private void deliberateOptions(ProductionPlan plan) {
        System.out.println("[COORDINATOR] Deliberating " + plan.getTaskCount() + " tasks");
    }
    
    private void selectAndExecutePlan(ProductionPlan plan) {
        System.out.println("[COORDINATOR] Executing plan: " + plan.getPlanDescription());
        productsCompleted++;
    }
    
    private void updateIntentions() {
        System.out.println("[COORDINATOR] Intentions updated for next cycle");
    }
    
    public int getCycleCount() {
        return cycleCount;
    }
    
    public int getProductsCompleted() {
        return productsCompleted;
    }
    
    public String getCurrentStatus() {
        return currentProductionStatus;
    }
    
    public float getQualityScore() {
        return qualityScore;
    }
}
