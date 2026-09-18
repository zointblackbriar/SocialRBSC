package de.tudresden.socialrbscdeterministic.examples.manufacturing;

/**
 * Production Goal Tree
 * Hierarchical goal structure for manufacturing operations.
 */
public class ProductionGoalTree {
    
    /**
     * Main production goal: produce target quantity of units
     */
    public void mainGoal() {
        System.out.println("[GOALS] Starting production of 100 units");
        new AssemblySubGoal().execute();
        new TestingSubGoal().execute();
        System.out.println("[GOALS] Production goal completed");
    }
    
    /**
     * Assembly goal branch
     */
    public void assemblyGoal() {
        new AssemblySubGoal().execute();
    }
    
    /**
     * Testing goal branch
     */
    public void testingGoal() {
        new TestingSubGoal().execute();
    }
}
