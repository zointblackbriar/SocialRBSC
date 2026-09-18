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
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;


/**
 * Social Agent Seller - Example of a seller social agent in a supply chain system.
 * This agent represents an entity that sells goods with specific preconditions.
 *
 * @author SocialRbSC Framework
 */
@SocialAgent(agentName = "SocialAgentSeller", addRole = true)
public class SocialAgentSeller {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Prepare goods for shipment.
     * This method can only be invoked when certain preconditions are met.
     */
    @Precondition
    @RoleMethod
    public void prepareGoodsForShipment() {
        // TODO: Implement shipment preparation logic
    }

    /**
     * Verify inventory availability.
     * 
     * @return true if goods are in stock
     */
    @Precondition
    @RoleMethod
    public boolean verifyInventory() {
        // TODO: Implement inventory verification logic
        return true;
    }

}
