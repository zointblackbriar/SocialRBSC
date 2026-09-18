/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.supplychain.intentions;

/**
 * ProductReady intention for Supply Chain use case.
 * This represents the intention to verify product readiness.
 *
 * @author Generated from Supply Chain Use Case
 */
public class ProductReady {
    
    /**
     * Product status precondition - will be represented as require() in smart contract.
     */
    public static boolean productStatus;
    
    /**
     * Check the product status.
     * Implementation of actions to achieve the goal of product readiness.
     *
     * @return true if product is ready, false otherwise
     */
    public boolean productStatusCheck() {
        productStatus = true;
        return productStatus;
    }
    
    /**
     * Get the current product status.
     */
    public static boolean getProductStatus() {
        return productStatus;
    }
    
    /**
     * Set the product status.
     */
    public static void setProductStatus(boolean status) {
        productStatus = status;
    }
}
