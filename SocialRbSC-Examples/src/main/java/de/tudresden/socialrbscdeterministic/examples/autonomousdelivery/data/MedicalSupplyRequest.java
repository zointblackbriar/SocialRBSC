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
 * Medical Supply Request - Represents a request for medical supply delivery.
 * 
 * @author SocialRbSC Framework
 */
public class MedicalSupplyRequest {
    
    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    private String supplyType;
    private String destination;
    private String urgencyLevel;

    //~ Constructor ------------------------------------------------------------------------------------------------------------------

    /**
     * Create a new medical supply request.
     * 
     * @param type Supply type
     * @param dest Destination location
     * @param urgency Urgency level
     */
    public MedicalSupplyRequest(String type, String dest, String urgency) {
        this.supplyType = type;
        this.destination = dest;
        this.urgencyLevel = urgency;
    }

    //~ Methods ------------------------------------------------------------------------------------------------------------------

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
}
