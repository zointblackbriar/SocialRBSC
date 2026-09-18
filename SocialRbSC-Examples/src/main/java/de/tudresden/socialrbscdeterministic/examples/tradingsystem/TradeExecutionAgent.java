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
 * Trade Execution Agent example. Receives signals and executes trades.
 */
@SocialAgent(agentName="TradeExecutionAgent", addRole=true)
public class TradeExecutionAgent {

    @RoleMethod
    public void executeTrade(String symbol, int size, double price) {
        // stub
    }
}
