package it.unisa.prioritization.test;

import it.unisa.prioritization.problems.SingleObjectiveGeneralizedPrioritizationProblem;
import java.util.ArrayList;
import java.util.List;
import org.uma.jmetal.solution.Solution;
import it.unisa.prioritization.criterion.GeneralizedAveragePercentage;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.solution.impl.DefaultIntegerPermutationSolution;

/**
 *
 * @author apanichella
 */
public class APFDcTest {
    
    private SingleObjectiveGeneralizedPrioritizationProblem problem;

	@Before 
	public void initialize(){
            List<String> list = new ArrayList<>();
            list.add("io/test/APFDc/coverage.csv");
            problem  = new SingleObjectiveGeneralizedPrioritizationProblem(list, "io/test/APFDc/cost.csv", "io/test/APFDc/faults.csv", false);
	
        }

    @Test
    public void testPermutation1() throws ClassNotFoundException{
        PermutationSolution<Integer> solution = new DefaultIntegerPermutationSolution(problem);
        solution.setVariableValue(0, 0);
        solution.setVariableValue(1, 1);
        solution.setVariableValue(2, 2);
        solution.setVariableValue(3, 3);
        solution.setVariableValue(4, 4);
        double value = GeneralizedAveragePercentage.calculate(solution, problem.faultMatrix, problem.costCriterion, false);
        assertEquals(94d/(15d*10d), value, 0.000001);
    }
    
    @Test
    public void testPermutation2() throws ClassNotFoundException{
        PermutationSolution<Integer> solution = new DefaultIntegerPermutationSolution(problem);
        solution.setVariableValue(0, 2);
        solution.setVariableValue(1, 4);
        solution.setVariableValue(2, 0);
        solution.setVariableValue(3, 1);
        solution.setVariableValue(4, 3);
        double value = GeneralizedAveragePercentage.calculate(solution, problem.faultMatrix, problem.costCriterion, false);
        assertEquals(123d/(15d*10d), value, 0.000001);
    }
    
        @Test
    public void testPermutation3() throws ClassNotFoundException{
        PermutationSolution<Integer> solution = new DefaultIntegerPermutationSolution(problem);
        solution.setVariableValue(0, 2);
        solution.setVariableValue(1, 0);
        solution.setVariableValue(2, 4);
        solution.setVariableValue(3, 1);
        solution.setVariableValue(4, 3);
        double value = GeneralizedAveragePercentage.calculate(solution, problem.faultMatrix, problem.costCriterion, false);
        assertEquals(120d/(15d*10d), value, 0.000001);
    }
   
}
