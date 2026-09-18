package de.tudresden.socialrbscdeterministic.examples.manufacturing;

/**
 * Product Data Class
 * Represents a product in the manufacturing system.
 */
public class Product {
    private String productId;
    private String productName;
    private boolean meetsQualityStandards;
    
    public Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
        this.meetsQualityStandards = true;
    }
    
    public boolean meetsStandards() {
        return meetsQualityStandards;
    }
    
    public void setQualityStatus(boolean passes) {
        meetsQualityStandards = passes;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    @Override
    public String toString() {
        return "Product{" + "id=" + productId + ", name=" + productName + 
               ", qualityPass=" + meetsQualityStandards + "}";
    }
}
