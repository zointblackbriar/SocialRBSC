/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.roles;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;


/**
 * Seller Role - Represents the role of a seller in a commercial transaction.
 * This role defines responsibilities and methods specific to seller behavior.
 *
 * @author SocialRbSC Framework
 */
@Role
public class SellerRole {

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Send quotation to buyer.
     */
    @RoleMethod
    public void sendQuotation() {
        // TODO: Implement quotation sending logic
    }

    /**
     * Accept order from buyer.
     */
    @RoleMethod
    public void acceptOrder() {
        // TODO: Implement order acceptance logic
    }

    /**
     * Ship goods to buyer.
     */
    @RoleMethod
    public void shipGoods() {
        // TODO: Implement goods shipment logic
    }

}
