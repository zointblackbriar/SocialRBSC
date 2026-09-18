package de.tudresden.socialrbscdeterministic.examples.manufacturing;

/**
 * Production Plan Data Class
 * Contains production scheduling and quality metrics.
 */
public class ProductionPlan {
    private int availableWorkers = 5;
    private float qualityMetric = 0.95f;
    private int taskCount = 8;
    private String planDescription = "Daily Production Plan";
    private long planStartTime;
    
    public ProductionPlan(String description) {
        this.planDescription = description;
        this.planStartTime = System.currentTimeMillis();
    }
    
    public int getAvailableWorkerCount() {
        return availableWorkers;
    }
    
    public void setAvailableWorkers(int count) {
        this.availableWorkers = count;
    }
    
    public float getCurrentQualityMetric() {
        return qualityMetric;
    }
    
    public void setQualityMetric(float metric) {
        this.qualityMetric = Math.min(1.0f, Math.max(0.0f, metric));
    }
    
    public int getTaskCount() {
        return taskCount;
    }
    
    public void setTaskCount(int count) {
        this.taskCount = count;
    }
    
    public String getPlanDescription() {
        return planDescription;
    }
    
    public long getPlanStartTime() {
        return planStartTime;
    }
    
    @Override
    public String toString() {
        return "ProductionPlan{" + "description=" + planDescription + 
               ", workers=" + availableWorkers + ", quality=" + qualityMetric + 
               ", tasks=" + taskCount + "}";
    }
}
