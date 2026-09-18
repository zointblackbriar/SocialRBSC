package de.tudresden.socialrbscdeterministic.mas.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies on-chain deployment location and smart contract address for blockchain deployment.
 *
 * This annotation marks classes that should trigger deployment runner generation. The annotation
 * processor will scan the class for fields and methods and create a simple web3j-based Java test
 * class capable of deploying the contracts generated from the other annotations to a blockchain
 * network (e.g., Ethereum, compatible networks).
 *
 * The deployment process includes:
 * - Contract compilation to bytecode
 * - Contract deployment to the blockchain
 * - Storage of the deployment address and ABI for interaction
 *
 * Example usage:
 * <pre>
 * {@code
 * @Deploy
 * @SocialAgent(agentName = "BlockchainAgent")
 * public class DeploymentRunner {
 *     // deployment configuration
 * }
 * }
 * </pre>
 *
 * @author Orcun Oruc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Deploy {
}
