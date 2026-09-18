/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery.data;

/**
 * Disruption Event - Represents an emergency or disruption event in the delivery system.
 * 
 * @author SocialRbSC Framework
 */
public class DisruptionEvent {
    
    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    private String eventType;
    private long eventTime;

    //~ Constructor ------------------------------------------------------------------------------------------------------------------

    /**
     * Create a new disruption event.
     * 
     * @param type Type of disruption event
     */
    public DisruptionEvent(String type) {
        this.eventType = type;
        this.eventTime = System.currentTimeMillis();
    }

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Get event type.
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Get event time.
     */
    public long getEventTime() {
        return eventTime;
    }
}
