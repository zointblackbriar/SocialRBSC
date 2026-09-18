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
 * Delivery Assignment - Represents a delivery assignment to a UAV or delivery unit.
 * 
 * @author SocialRbSC Framework
 */
public class DeliveryAssignment {
    
    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    private String requestId;
    private String supplyType;
    private String destination;
    private String urgencyLevel;
    private long timestamp;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Set request ID.
     */
    public void setRequestId(String id) {
        this.requestId = id;
    }

    /**
     * Set supply type.
     */
    public void setSupplyType(String type) {
        this.supplyType = type;
    }

    /**
     * Set destination.
     */
    public void setDestination(String dest) {
        this.destination = dest;
    }

    /**
     * Set urgency level.
     */
    public void setUrgencyLevel(String level) {
        this.urgencyLevel = level;
    }

    /**
     * Set timestamp.
     */
    public void setTimestamp(long ts) {
        this.timestamp = ts;
    }

    /**
     * Get supply type.
     */
    public String getSupplyType() {
        return supplyType;
    }

    /**
     * Get destination.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Get urgency level.
     */
    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    /**
     * Get request ID.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Get timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }
}
