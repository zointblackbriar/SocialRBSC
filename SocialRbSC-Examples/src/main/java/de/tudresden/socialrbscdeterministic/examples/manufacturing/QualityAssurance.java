package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Quality Assurance Role
 * Ensures products meet quality standards through inspection and validation.
 */
@Role
public class QualityAssurance {
    private int inspectionsPerformed = 0;
    private int passedInspections = 0;
    
    @RoleMethod
    @Precondition(internalparam = "product != null && inspectionRequired")
    public boolean inspectProduct(Product product) {
        inspectionsPerformed++;
        boolean passed = product.meetsStandards();
        if (passed) {
            passedInspections++;
        }
        System.out.println("[QA] Inspection " + inspectionsPerformed + ": " + (passed ? "PASS" : "FAIL"));
        return passed;
    }
    
    public float getQualityRate() {
        if (inspectionsPerformed == 0) return 0f;
        return (float) passedInspections / inspectionsPerformed;
    }
    
    public int getInspectionsPerformed() {
        return inspectionsPerformed;
    }
    
    public int getPassedInspections() {
        return passedInspections;
    }
    
    private boolean inspectionRequired = true;
    
    public void setInspectionRequired(boolean required) {
        inspectionRequired = required;
    }
}
