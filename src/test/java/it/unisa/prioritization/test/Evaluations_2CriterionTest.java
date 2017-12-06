package it.unisa.prioritization.test;

import it.unisa.prioritization.problems.MultiObjectiveGeneralizedPrioritizationProblem;
import it.unisa.prioritization.problems.SingleObjectiveGeneralizedPrioritizationProblem;
import java.util.ArrayList;
import java.util.List;
import org.uma.jmetal.solution.PermutationSolution;
import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.solution.impl.DefaultIntegerPermutationSolution;

/**
 *
 * @author dardin88
 */
public class Evaluations_2CriterionTest {

    private List<String> list;

    @Before
    public void initialize() {
        list = new ArrayList<>();
        list.add("io/test/grep/comp_coverage_matrix_s.csv");
    }

    @Test
    public void testSingleObjectiveProblem() throws ClassNotFoundException {
        SingleObjectiveGeneralizedPrioritizationProblem problem = new SingleObjectiveGeneralizedPrioritizationProblem(list, "io/test/grep/cost_array.csv", "io/test/grep/fault_matrix.csv", false);
        PermutationSolution<Integer> solution = new DefaultIntegerPermutationSolution(problem);
        int solutionLength = problem.getPermutationLength();
        for (int i = 0; i < solutionLength; i++) {
            solution.getVariableValue(i).intValue();
            //((Permutation) solution.getDecisionVariables()[0]).vector_[i] = i;
        }

        long start = System.currentTimeMillis();
        problem.evaluate(solution);
        long end = System.currentTimeMillis();
        long time = end - start;
        
        System.out.println("Single objective time: " + time + "ms");
    }
    
    @Test
    public void testMultiObjectiveProblem() throws ClassNotFoundException {
        MultiObjectiveGeneralizedPrioritizationProblem problem = new MultiObjectiveGeneralizedPrioritizationProblem(list, "io/test/grep/cost_array.csv", "io/test/grep/fault_matrix.csv", false);
        PermutationSolution<Integer> solution = new DefaultIntegerPermutationSolution(problem);
        int solutionLength = problem.getPermutationLength();
        for (int i = 0; i < solutionLength; i++) {
            solution.getVariableValue(i);
            //((Permutation) solution.getDecisionVariables()[0]).vector_[i] = i;
        }

        long start = System.currentTimeMillis();
        problem.evaluate(solution);
        long end = System.currentTimeMillis();
        long time = end - start;
        
        System.out.println("Multi objective time: " + time + "ms");
    }

}
