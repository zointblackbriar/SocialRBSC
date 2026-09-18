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
 * UserExist intention for Supply Chain use case.
 * This represents the intention to verify user existence in the system.
 *
 * @author Generated from Supply Chain Use Case
 */
public class UserExist {
    
    /**
     * Username precondition.
     */
    public static String userName;
    
    /**
     * User ID precondition.
     */
    public static String userID;
    
    /**
     * Check if user exists in the system.
     * This is a postcondition method that validates user existence.
     *
     * @return true if both username and user ID are not null, false otherwise
     */
    public boolean checkingFunc() {
        if (userName != null && userID != null) {
            return true;
        }
        return false;
    }
    
    /**
     * Set the username.
     */
    public static void setUserName(String name) {
        userName = name;
    }
    
    /**
     * Set the user ID.
     */
    public static void setUserID(String id) {
        userID = id;
    }
    
    /**
     * Get the username.
     */
    public static String getUserName() {
        return userName;
    }
    
    /**
     * Get the user ID.
     */
    public static String getUserID() {
        return userID;
    }
}
