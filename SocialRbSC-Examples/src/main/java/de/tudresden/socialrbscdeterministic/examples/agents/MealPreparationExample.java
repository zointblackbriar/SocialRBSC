/*
 * Copyright 2022-2025 Orcun Oruc
 */
package de.tudresden.socialrbscdeterministic.examples.agents;

import de.tudresden.socialrbscdeterministic.mas.annotation.Action;
import de.tudresden.socialrbscdeterministic.mas.annotation.AvoidanceCondition;
import de.tudresden.socialrbscdeterministic.mas.annotation.Goal;
import de.tudresden.socialrbscdeterministic.mas.annotation.Plan;
import de.tudresden.socialrbscdeterministic.mas.annotation.SubGoal;
import de.tudresden.socialrbscdeterministic.mas.annotation.WillingnessCondition;

/**
 * Example representation of the meal preparation goal-plan tree using annotations.
 */
@Goal("Prepare a Meal")
public class MealPreparationExample {

    // Plan 1: Make pasta
    @Plan(value = "Make pasta", avoidance = "Avoid peak electricity usage", willingness = "Prefers warm meals")
    public static class MakePasta {

        @SubGoal("Boil water")
        public static class BoilWater {
            @Action("Fill the pot with water")
            public void fillPot() {}

            @Action("Place pot on stove")
            public void placePot() {}

            @Action("Turn on the stove")
            @AvoidanceCondition("Check if it's peak electricity usage. If so, avoid this plan.")
            public void turnOnStove() {}
        }

        @SubGoal("Cook pasta")
        public static class CookPasta {
            @Action("Add pasta to boiling water")
            public void addPasta() {}

            @Action("Stir occasionally")
            public void stir() {}

            @AvoidanceCondition("Avoid leaving the stove unattended for too long.")
            public void safety() {}
        }

        @SubGoal("Serve pasta")
        public static class ServePasta {
            @Action("Serve warm pasta")
            @WillingnessCondition("High priority if the agent prefers a warm, filling meal at this time.")
            public void serve() {}
        }
    }

    // Plan 2: Make a sandwich
    @Plan(value = "Make sandwich", avoidance = "Avoid high-carb meals", willingness = "Quick/Light preference")
    public static class MakeSandwich {
        @SubGoal("Get ingredients")
        public static class GetIngredients {
            @Action("Get bread, cheese, ham, etc.")
            @AvoidanceCondition("Avoid this sub-goal if the agent's diet restricts carbs (bread).")
            public void getIngredients() {}
        }

        @SubGoal("Assemble sandwich")
        public static class Assemble {
            @Action("Place ingredients between slices of bread")
            @WillingnessCondition("High willingness if the agent wants a quick meal.")
            public void assemble() {}
        }

        @SubGoal("Serve sandwich")
        public static class Serve {
            @Action("Serve sandwich")
            @WillingnessCondition("High priority if the agent prefers a light snack over a full meal.")
            public void serve() {}
        }
    }

    // Plan 3: Make a salad
    @Plan(value = "Make salad", avoidance = "Avoid missing/expired ingredients", willingness = "Prefers healthy fresh meal")
    public static class MakeSalad {
        @SubGoal("Get ingredients")
        public static class GetIngredients {
            @Action("Get lettuce, tomatoes, cucumbers, dressing, etc.")
            @AvoidanceCondition("Avoid if some ingredients are missing or expired.")
            public void getIngredients() {}
        }

        @SubGoal("Assemble salad")
        public static class Assemble {
            @Action("Combine ingredients")
            @WillingnessCondition("High willingness if the agent prefers a healthy meal.")
            public void assemble() {}
        }

        @SubGoal("Serve salad")
        public static class Serve {
            @Action("Serve salad")
            @WillingnessCondition("Prioritize if the agent is focusing on health or wants a fresh, cold meal.")
            public void serve() {}
        }
    }
}
