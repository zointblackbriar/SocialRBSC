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
 * Retailer role in the Supply Chain society.
 * Represents the retail merchant that interacts with customers.
 *
 * @author Generated from Supply Chain Use Case
 */
@Role
public class Retailer {
    
    /**
     * Supply chain test parameter for demonstration purposes.
     */
    public static int supplyChainTestParam;
    
    /**
     * Agent state indicating the current state of the agent.
     */
    public static String agentState = "RUNNABLEAGENT";
    
    /**
     * Supply chain calculator method that computes supply based on demand.
     * This method demonstrates role interaction with postcondition enforcement.
     *
     * @param demand the demand from customers
     * @return the calculated supply chain test parameter
     */
    @RoleMethod
    public int supplyChainCalculator(int demand) {
        supplyChainTestParam = demand + 1;
        // Postcondition: supplyChainTestParam > 0
        return supplyChainTestParam;
    }
    
    /**
     * Get the current supply chain test parameter value.
     */
    public int getSupplyChainTestParam() {
        return supplyChainTestParam;
    }
    
    /**
     * Get the current agent state.
     */
    public String getAgentState() {
        return agentState;
    }
}
