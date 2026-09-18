package de.tudresden.socialrbscdeterministic.examples.tradingsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic compilation test for trading system example classes.
 */
public class TradingSystemExamplesTest {

    @Test
    public void exerciseAgentsAndRoles() {
        NewsSentimentAgent news = new NewsSentimentAgent();
        news.ingestHeadline("hello");

        SectorAnalysisAgent sector = new SectorAnalysisAgent();
        sector.receiveSentiment("SYM", (byte)1);
        assertEquals("HOLD", sector.makeRecommendation("SYM"));

        TradeExecutionAgent exec = new TradeExecutionAgent();
        exec.executeTrade("SYM", 10, 100.0);

        RiskManagementAgent risk = new RiskManagementAgent();
        assertTrue(risk.validateTrade("SYM", 5));
    }

    @Test
    public void compartmentInstantiatesRoles() {
        TradingSystemCompartment comp = new TradingSystemCompartment();
        assertNotNull(comp);
    }
}
