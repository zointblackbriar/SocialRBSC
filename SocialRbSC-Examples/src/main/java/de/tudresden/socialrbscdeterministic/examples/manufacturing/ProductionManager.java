package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Production Manager Role
 * Oversees the manufacturing process and coordinates production tasks.
 */
@Role
public class ProductionManager {
    private int tasksScheduled = 0;
    
    @RoleMethod
    public void scheduleProduction(String productionPlan) {
        tasksScheduled++;
        System.out.println("[MANAGER] Scheduled production: " + productionPlan);
    }
    
    public int getTasksScheduled() {
        return tasksScheduled;
    }
}
