/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.agents;

import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentGoal;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;


/**
 * Social Agent Buyer - Example of a buyer social agent in a supply chain system.
 * This agent represents an entity that purchases goods with specific preconditions.
 *
 * @author SocialRbSC Framework
 */
@SocialAgent(agentName = "SocialAgentBuyer", addRole = true)
public class SocialAgentBuyer {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Initiate purchase request with preconditions.
     * This method can only be invoked when certain preconditions are met.
     */
    @Precondition
    @RoleMethod
    public void initiatePurchase() {
        // TODO: Implement purchase logic
    }

    /**
     * Check account balance before purchase.
     * 
     * @return true if sufficient balance available
     */
    @Precondition
    @RoleMethod
    public boolean checkBalance() {
        // TODO: Implement balance checking logic
        return true;
    }

}
