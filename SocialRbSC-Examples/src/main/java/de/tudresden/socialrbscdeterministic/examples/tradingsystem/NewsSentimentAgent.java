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
 * News Sentiment Agent example.  Ingests headlines and produces sentiment signals.
 */
@SocialAgent(agentName="NewsSentimentAgent", addRole=true)
public class NewsSentimentAgent {

    @Precondition
    @RoleMethod
    public void ingestHeadline(String headline) {
        // stub for ingestion logic
    }

    @RoleMethod
    public int analyzeSentiment(String text) {
        // stub sentiment analysis
        return 0;
    }
}
