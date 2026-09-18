/*
 * Copyright 2022-2025 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */

package de.tudresden.SocialRbSC_Examples;

import de.tudresden.socialrbscdeterministic.mas.annotation.Action;
import de.tudresden.socialrbscdeterministic.mas.annotation.AvoidanceCondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;
import de.tudresden.socialrbscdeterministic.mas.annotation.Goal;
import de.tudresden.socialrbscdeterministic.mas.annotation.GoalPlanTree;
import de.tudresden.socialrbscdeterministic.mas.annotation.Plan;
import de.tudresden.socialrbscdeterministic.mas.annotation.Precondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.PreconditionSocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.Society;
import de.tudresden.socialrbscdeterministic.mas.annotation.SubGoal;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentPlan;
import de.tudresden.socialrbscdeterministic.mas.annotation.WillingnessCondition;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
@SocialAgent(agentName = "SocialAgentBuyer", addRole = true)
@Society
@Deploy
class Professor {

    public void initializeAgent() {
        System.out.println("Agent initialized: buyer-001");
    }

    protected String belief = "defaultBelief";
    protected int funds = 100;

    @Role
    public static class BuyerRole {
        @RoleMethod
        public void performRole() {
            System.out.println("Buyer role is active. Ready to execute purchasing actions.");
        }
    }

    @Action("attemptPurchase")
    @AvoidanceCondition("funds < 10")
    @Plan(value = "purchasePlan", avoidance = "avoidLowFunds")
    @WillingnessCondition("high")
    public void buyItem() {
        if (funds >= 10) {
            funds -= 50; // Deduct purchase amount
            belief = "Item purchased successfully";
            System.out.println("Purchase executed. Remaining funds: " + funds);
        } else {
            System.out.println("Insufficient funds to complete purchase.");
        }
    }

    @Precondition
    @PreconditionSocialAgent
    public boolean checkPreconditions() {
        boolean hasFunds = funds > 0;
        boolean hasValidBelief = belief != null && !belief.isEmpty();
        System.out.println("Preconditions check - Funds available: " + hasFunds + ", Belief valid: " + hasValidBelief);
        return hasFunds && hasValidBelief;
    }

    public void deliberate() {
        System.out.println("Deliberating: Current belief=" + belief + ", Funds=" + funds);
        if (funds > 50) {
            System.out.println("Decision: Proceed with purchase plan");
        } else {
            System.out.println("Decision: Avoid purchase to preserve funds");
        }
    }

    //~ Inner Classes ------------------------------------------------------------------------------------------------------------

    @SocialAgentPlan(triggeredPlan = "fulfillPurchase", defineSociety = "BuyerSociety")
    public static class App {

        @Goal("obtainItem")
        public static class ObtainItemGoal {
            @SubGoal("secureFunds")
            public static class SecureFundsSubGoal {
                public void goalDefinition() {
                    System.out.println("Goal defined: Obtain item");
                    System.out.println("Subgoal: Ensure sufficient funds are secured");
                }
            }
        }

        @Compartment(bindingRole = "BuyerRole")
        public interface BuyerCompartment {
            void initializeCompartment();
        }

        public void buildGoalPlanTree() {
            System.out.println("Building Goal-Plan Tree structure");
        }

        public void goalDefinition() {
            System.out.println("Goal defined: Obtain item");
            System.out.println("Subgoal: Ensure sufficient funds are secured");
        }

        @Plan("fulfillPurchase")
        public void planMethod() {
            System.out.println("Executing plan: Fulfill Purchase");
            System.out.println("Step 1: Verify availability of funds");
            System.out.println("Step 2: Execute buyItem action");
            System.out.println("Step 3: Notify seller of purchase");
        }

        @Action("notifySeller")
        public void notifySeller() {
            System.out.println("Notifying seller: Purchase order confirmed");
            System.out.println("Transaction details sent to seller infrastructure");
        }

        @PreconditionSocialAgent
        public boolean agentPrecondition() {
            System.out.println("Evaluating social agent preconditions...");
            return true; // Precondition satisfied for this agent
        }

        public static void main(String[] args) {
            System.out.println("Extended annotation example");
        }

        // Demonstrate nested compartment interface
        interface LocalCompartment {
            void helloworld();
        }
    }
}
