package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Blockchain Deployer Role
 * Handles deployment of smart contracts to blockchain networks.
 */
@Role
public class BlockchainDeployer {
    private String deploymentAddress = "http://blockchain.example.com/factory";
    private int deploymentsCount = 0;
    
    @RoleMethod
    public void deployContract(String code) {
        deploymentsCount++;
        System.out.println("[DEPLOYER] Deploying contract #" + deploymentsCount + 
                         " to " + deploymentAddress);
    }
    
    public void setDeploymentAddress(String address) {
        this.deploymentAddress = address;
    }
    
    public String getDeploymentAddress() {
        return deploymentAddress;
    }
    
    public int getDeploymentsCount() {
        return deploymentsCount;
    }
}
