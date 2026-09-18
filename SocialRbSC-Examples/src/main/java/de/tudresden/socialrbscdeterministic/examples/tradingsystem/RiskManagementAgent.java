/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.tradingsystem;

import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Risk Management Agent example. Performs risk checks.
 */
@SocialAgent(agentName="RiskManagementAgent", addRole=true)
public class RiskManagementAgent {

    @RoleMethod
    public boolean validateTrade(String symbol, int size) {
        return true;
    }
}
