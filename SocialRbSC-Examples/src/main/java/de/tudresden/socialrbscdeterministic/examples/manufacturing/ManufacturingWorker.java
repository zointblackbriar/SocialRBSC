package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Manufacturing Worker Role
 * Executes assigned manufacturing tasks on the production line.
 */
@Role
public class ManufacturingWorker {
    private String currentTask;
    private boolean workerAvailable = true;
    
    @RoleMethod
    public void performTask(String task) {
        currentTask = task;
        workerAvailable = false;
        System.out.println("[WORKER] Executing task: " + task);
    }
    
    public void completeTask() {
        workerAvailable = true;
        currentTask = null;
        System.out.println("[WORKER] Task completed - Ready for next assignment");
    }
    
    public String getCurrentTask() {
        return currentTask;
    }
    
    public boolean isAvailable() {
        return workerAvailable;
    }
    
    private boolean machineOperational = true;
    
    public void setMachineStatus(boolean operational) {
        machineOperational = operational;
    }
}
