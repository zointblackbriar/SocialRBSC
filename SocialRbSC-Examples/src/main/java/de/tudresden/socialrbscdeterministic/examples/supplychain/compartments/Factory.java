/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.socialrbscdeterministic.examples.supplychain.compartments;

import de.tudresden.socialrbscdeterministic.examples.supplychain.roles.Producer;
import de.tudresden.socialrbscdeterministic.examples.supplychain.roles.Retailer;
import de.tudresden.socialrbscdeterministic.examples.supplychain.roles.Wholesaler;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;

/**
 * Factory compartment for Supply Chain use case.
 * This compartment orchestrates the interactions between Retailer, Wholesaler, and Producer roles.
 * When @Compartment is used, @Role and @SocialAgent must also be present.
 *
 * @author Generated from Supply Chain Use Case
 */
@Deploy
@Compartment
@Role
@SocialAgent(agentName = "FactoryAgent")
public class Factory {
    
    private Retailer retailer;
    private Wholesaler wholesaler;
    private Producer producer;
    
    /**
     * Constructor initializing all roles.
     */
    public Factory() {
        this.retailer = new Retailer();
        this.wholesaler = new Wholesaler();
        this.producer = new Producer();
    }
    
    /**
     * Run one cycle of supply chain operations.
     * This method simulates a complete interaction between all roles in the compartment.
     */
    @RoleMethod
    public void runCycle() {
        // Execute supply chain cycle
        int result = retailer.supplyChainCalculator(10);
        System.out.println("Supply chain result: " + result);
        
        // Producer produces goods
        int produced = producer.produceGoods(15);
        System.out.println("Producer produced: " + produced);
        
        // Wholesaler receives from producer
        wholesaler.receiveProducts(produced);
        System.out.println("Wholesaler inventory after receiving: " + wholesaler.getInventory());
        
        // Wholesaler distributes to retailer
        wholesaler.distributeProducts(10);
        System.out.println("Wholesaler inventory after distribution: " + wholesaler.getInventory());
    }
    
    /**
     * Detailed supply chain processing with demand-driven production.
     *
     * @param customerDemand the demand from customers
     * @return the supply chain calculation result
     */
    @RoleMethod
    public int processSupplyChainCycle(int customerDemand) {
        // Calculate supply based on demand
        int calculatedSupply = retailer.supplyChainCalculator(customerDemand);
        
        // Producer adjusts production based on calculated supply
        int produced = producer.produceGoods(calculatedSupply);
        wholesaler.receiveProducts(produced);
        
        System.out.println("Customer Demand: " + customerDemand);
        System.out.println("Calculated Supply: " + calculatedSupply);
        System.out.println("Produced: " + produced);
        System.out.println("Wholesaler Inventory: " + wholesaler.getInventory());
        
        return calculatedSupply;
    }
    
    /**
     * Get the retailer role.
     */
    public Retailer getRetailer() {
        return retailer;
    }
    
    /**
     * Get the wholesaler role.
     */
    public Wholesaler getWholesaler() {
        return wholesaler;
    }
    
    /**
     * Get the producer role.
     */
    public Producer getProducer() {
        return producer;
    }
}
