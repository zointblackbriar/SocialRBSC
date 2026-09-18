package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Supervisor Role
 * Higher-level role for overseeing production operations.
 */
@Role
public class SupervisorRole {
    private int decisionsMade = 0;
    
    @RoleMethod
    public void oversee() {
        decisionsMade++;
        System.out.println("[SUPERVISOR] Overseeing production - Decision #" + decisionsMade);
    }
    
    @RoleMethod
    public void approveQualityMetrics(float qualityScore) {
        decisionsMade++;
        System.out.println("[SUPERVISOR] Quality metrics approved: " + qualityScore);
    }
    
    public int getDecisionsMade() {
        return decisionsMade;
    }
}
