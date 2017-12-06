package it.unisa.prioritization.test;

import it.unisa.prioritization.problems.SingleObjectiveGeneralizedPrioritizationProblem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.uma.jmetal.solution.PermutationSolution;
import it.unisa.prioritization.criterion.GeneralizedAveragePercentage;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.solution.impl.DefaultIntegerPermutationSolution;

/**
 *
 * @author apanichella
 */
public class APFDcTest4 {

    private SingleObjectiveGeneralizedPrioritizationProblem problem;

    @Before
    public void initialize() {
        List<String> list = new ArrayList<>();
        list.add("io/test/APFDc/printtokens_test/coverage_matrix_b");
        problem = new SingleObjectiveGeneralizedPrioritizationProblem(list, "io/test/APFDc/printtokens_test/cost_array", "io/test/APFDc/printtokens_test/fault_matrix", false);
    }

    @Test
    public void testPermutation1() throws ClassNotFoundException {
        try {
            PermutationSolution<Integer> solution = new DefaultIntegerPermutationSolution(problem);
            int A[] = this.readSolution("io/test/APFDc/printtokens_test/solution_points");
            for(int i=0; i<A.length; i++)
            {
                solution.setVariableValue(i, A[i]);
            }
            double value = GeneralizedAveragePercentage.calculate(solution, problem.faultMatrix, problem.costCriterion, false);
            assertEquals(0.03, value, 0.01);
        } catch (IOException ex) {
            Logger.getLogger(APFDcTest4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int[] readSolution(String solutionFile) throws IOException{
        FileReader fr = new FileReader(solutionFile);
        BufferedReader br = new BufferedReader(fr);
        String solutionLine = br.readLine();
        StringTokenizer st = new StringTokenizer(solutionLine, " ");
        int[] solution = new int[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()){
            solution[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        return solution;
    }

}
