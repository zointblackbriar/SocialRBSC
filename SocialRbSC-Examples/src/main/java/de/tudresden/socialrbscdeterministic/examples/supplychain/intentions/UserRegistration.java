/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.supplychain.intentions;

/**
 * UserRegistration intention for Supply Chain use case.
 * This represents the intention to register a user in the system.
 *
 * @author Generated from Supply Chain Use Case
 */
public class UserRegistration {
    
    /**
     * Username that must exist as a precondition.
     */
    public static String userName;
    
    /**
     * Deliberation cycle method for user registration.
     * This method will be called during each deliberation cycle.
     *
     * @return true if registration is successful, false otherwise
     */
    public boolean registeringUser() {
        userName = "sampleName";
        if (userName != null && userName.equalsIgnoreCase("sampleName")) {
            return true;
        }
        return false;
    }
    
    /**
     * Get the registered username.
     */
    public static String getUserName() {
        return userName;
    }
}
