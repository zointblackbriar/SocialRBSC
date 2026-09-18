package de.tudresden.socialrbscdeterministic.examples.manufacturing;

import de.tudresden.socialrbscdeterministic.mas.annotation.Society;

/**
 * Manufacturing Ecosystem Society
 * Defines the collection of roles and agents in the manufacturing system.
 */
@Society
public interface ManufacturingEcosystem {
    // Key roles in the manufacturing ecosystem
    ProductionManager manager = new ProductionManager();
    ManufacturingWorker[] workers = new ManufacturingWorker[5];
    QualityAssurance qa = new QualityAssurance();
    ManufacturingCoordinator coordinator = new ManufacturingCoordinator();
    SupervisorRole supervisor = new SupervisorRole();
    BlockchainDeployer deployer = new BlockchainDeployer();
}
