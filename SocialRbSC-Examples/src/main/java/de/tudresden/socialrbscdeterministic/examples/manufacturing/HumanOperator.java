package de.tudresden.socialrbscdeterministic.examples.manufacturing;

/**
 * Human Operator Natural Type
 * Represents a human operator in the manufacturing system.
 */
public class HumanOperator {
    private String name;
    private String department;
    
    public HumanOperator(String name) {
        this.name = name;
        this.department = "General";
    }
    
    public HumanOperator(String name, String department) {
        this.name = name;
        this.department = department;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String dept) {
        this.department = dept;
    }
    
    @Override
    public String toString() {
        return "HumanOperator{" + "name='" + name + "', department='" + department + "'}";
    }
}
