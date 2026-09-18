package de.tudresden.socialrbscdeterministic.examples.autonomousdelivery;

import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;

/**
 * Example class annotated with @Deploy to trigger generation of the Java runtime
 * environment under the SocialRbSC-Examples module. This class is intentionally
 * minimal; the annotation processor will create the runtime folder and runtime
 * Java file when this class is compiled.
 */
@Deploy
public class DeployExample {
    // This class intentionally left blank; add nested annotated elements if you
    // want the processor to scan for Roles, Goals, Plans, etc.
}

