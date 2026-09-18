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
 * Sector Analysis Agent example. Aggregates signals and formulates recommendations.
 */
@SocialAgent(agentName="SectorAnalysisAgent", addRole=true)
public class SectorAnalysisAgent {

    @RoleMethod
    public void receiveSentiment(String symbol, byte score) {
        // ingest sentiment data
    }

    @RoleMethod
    public String makeRecommendation(String symbol) {
        return "HOLD";
    }
}
