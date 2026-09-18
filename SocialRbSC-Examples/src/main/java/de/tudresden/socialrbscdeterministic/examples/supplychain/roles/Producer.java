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
 * Producer role in the Supply Chain society.
 * Represents the manufacturing entity that produces goods.
 *
 * @author Generated from Supply Chain Use Case
 */
@Role
public class Producer {
    
    private int productionCapacity = 100;
    private int produced = 0;
    
    /**
     * Produce goods based on demand forecast.
     *
     * @param demandForecast the forecasted demand
     * @return the actual quantity produced
     */
    @RoleMethod
    public int produceGoods(int demandForecast) {
        int quantityToProduceMask = Math.min(demandForecast, productionCapacity);
        produced = quantityToProduceMask;
        return produced;
    }
    
    /**
     * Get the current production capacity.
     */
    public int getProductionCapacity() {
        return productionCapacity;
    }
    
    /**
     * Get the quantity produced.
     */
    public int getProduced() {
        return produced;
    }
}
