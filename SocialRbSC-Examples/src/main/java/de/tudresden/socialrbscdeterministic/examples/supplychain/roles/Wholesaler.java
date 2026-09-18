/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.supplychain.roles;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Wholesaler role in the Supply Chain society.
 * Represents the wholesale distributor between retailers and producers.
 *
 * @author Generated from Supply Chain Use Case
 */
@Role
public class Wholesaler {
    
    private int inventory = 0;
    
    /**
     * Distribute products from wholesaler to retailers.
     *
     * @param quantity the quantity to distribute
     */
    @RoleMethod
    public void distributeProducts(int quantity) {
        if (inventory >= quantity) {
            inventory -= quantity;
        }
    }
    
    /**
     * Receive products from producer.
     *
     * @param quantity the quantity received
     */
    @RoleMethod
    public void receiveProducts(int quantity) {
        inventory += quantity;
    }
    
    /**
     * Get current inventory level.
     */
    public int getInventory() {
        return inventory;
    }
}
