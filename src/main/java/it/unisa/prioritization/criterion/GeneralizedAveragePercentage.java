package it.unisa.prioritization.criterion;

import org.uma.jmetal.solution.PermutationSolution;

/**
 *
 * @author dardin88
 */
public class GeneralizedAveragePercentage {

    public static double calculate(PermutationSolution<Integer> solution, CoverageMatrix coverageMatrix, ExecutionCostVector costVector, boolean compacted) {
        double AFDPC = 0;
        int mi = coverageMatrix.numberOfTargets();
        int m = coverageMatrix.numberOfOriginalTargets();
        int n = coverageMatrix.numberOfTests();
        int[] TF = new int[mi];

        if (m == 0) {
            m = mi;
        }

        for (int i = 0; i < TF.length; i++) {
            TF[i] = -1;
        }

        for (int i = 0; i < mi; i++) {
            for (int j = 0; j < n; j++) {
                if (coverageMatrix.getElement(solution.getVariableValue(j), i) > 0) {
                    TF[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < mi; i++) {
            double sum = 0;
            if (TF[i] == -1) {
                continue;
            }
            for (int j = TF[i]; j < n; j++) {
                sum += costVector.getCostOfTest(solution.getVariableValue(j));
            }
            sum = sum - (1d / 2d * costVector.getCostOfTest(solution.getVariableValue(TF[i])));

            if (compacted) {
                int ci = coverageMatrix.getElement(solution.getVariableValue(TF[i]), i);
                AFDPC += ci * sum;
            } else {
                AFDPC += sum;
            }
        }
        double denominator = 0;
        for (int j = 0; j < n; j++) {
            denominator += costVector.getCostOfTest(j) * m;
        }
        AFDPC /= denominator;
        return AFDPC;
    }
}
