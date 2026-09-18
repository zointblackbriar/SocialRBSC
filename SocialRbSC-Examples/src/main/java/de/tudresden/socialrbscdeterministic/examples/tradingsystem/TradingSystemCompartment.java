/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.tradingsystem;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;

@Compartment
public class TradingSystemCompartment {

    private NewsSentimentRole newsRole;
    private SectorAnalysisRole sectorRole;
    private TradeExecutionRole execRole;
    private RiskManagementRole riskRole;

    public TradingSystemCompartment() {
        this.newsRole = new NewsSentimentRole();
        this.sectorRole = new SectorAnalysisRole();
        this.execRole = new TradeExecutionRole();
        this.riskRole = new RiskManagementRole();
    }

    public void initialize() {
        // custom initialization
    }

    public void runCycle() {
        // simulate one reasoning/decision cycle
    }
}