// package de.tudresden.multiagentsystem.annotationprocessor;

// import static com.google.testing.compile.CompilationSubject.assertThat;
// import static com.google.testing.compile.Compiler.javac;

// import com.google.testing.compile.Compilation;
// import com.google.testing.compile.JavaFileObjects;
// import org.junit.jupiter.api.Test;

// import javax.tools.JavaFileObject;

// public class GoalPlanProcessorTest {

//     @Test
//     public void testGoalPlanGeneration() {
//         JavaFileObject source = JavaFileObjects.forSourceString("test.MealGoal",
//                 "package test;\n" +
//                         "import de.tudresden.socialrbscdeterministic.mas.annotation.Goal;\n" +
//                         "import de.tudresden.socialrbscdeterministic.mas.annotation.Plan;\n" +
//                         "import de.tudresden.socialrbscdeterministic.mas.annotation.SubGoal;\n" +
//                         "import de.tudresden.socialrbscdeterministic.mas.annotation.Action;\n" +
//                         "import de.tudresden.socialrbscdeterministic.mas.annotation.AvoidanceCondition;\n" +
//                         "import de.tudresden.socialrbscdeterministic.mas.annotation.WillingnessCondition;\n" +
//                         "@Goal(\"Prepare a Meal\")\n" +
//                         "public class MealGoal {\n" +
//                         "    @Plan(value=\"Make pasta\", avoidance=\"Avoid peak electricity\", willingness=\"Prefers warm meals\")\n" +
//                         "    public static class MakePasta {\n" +
//                         "        @SubGoal(\"Boil water\")\n" +
//                         "        public static class BoilWater {\n" +
//                         "            @Action(\"Turn on stove\")\n" +
//                         "            @AvoidanceCondition(\"check peak\")\n" +
//                         "            public void turnOnStove() {}\n" +
//                         "        }\n" +
//                         "    }\n" +
//                         "}\n");

//         Compilation compilation = javac().withProcessors(new GoalPlanProcessor()).compile(source);
//         assertThat(compilation).succeeded();
//         assertThat(compilation).generatedFileNamed(javax.tools.StandardLocation.CLASS_OUTPUT, "", "generatedsmartcontract/contracts/Prepare a Meal.sol");
//     }
// }
