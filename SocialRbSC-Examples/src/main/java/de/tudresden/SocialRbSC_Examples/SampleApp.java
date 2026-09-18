/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.gasmaskexamples.SocialRbSC_Examples;

import de.tudresden.socialrbscdeterministic.mas.annotation.PreconditionSocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.PreconditionVariable;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;


/**
 * TODO DOCUMENT ME!
 *
 * @author Orcun Oruc
 */
@SocialAgent(agentName = "SocialAgentBuyer", removeRole = true)
class Worker {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @PreconditionVariable
    public static int moneyToBeSent;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    @PreconditionSocialAgent
    public boolean moneyToBeSent() {
        if (moneyToBeSent > 0) {
            return true;
        }

        return false;
    }
}
