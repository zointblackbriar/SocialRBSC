package de.tudresden.usecasesupplychain;

import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;

/**
 * Annotated class representing the supply‑chain use case.  The
 * {@code @Deploy} annotation triggers generation of a web3j-based deployer
 * class when the annotation processor runs during compilation.
 *
 * The generated runner (SupplyChainUseCaseDeployer.java) will contain
 * stubs for each of the methods below, giving a starting point for
 * writing Java tests that deploy and interact with the Solidity contracts
 * produced by this project.
 */
@Deploy
public class SupplyChainUseCase {
    public void createRetailer() {}
    public void createWholesaler() {}
    public void assignCoordinator() {}
    public void scheduleJob() {}
}
