package it.unisa.prioritization.algorithm;

import it.unisa.prioritization.criterion.CumulativeCoverage;
import it.unisa.prioritization.problems.GeneralizedPrioritizationProblem;
import it.unisa.prioritization.problems.SingleObjectiveGeneralizedPrioritizationProblem;
import java.util.ArrayList;
import java.util.List;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.solution.impl.DefaultIntegerPermutationSolution;

public class AdditionalGreedyPrioritization implements Algorithm {

    private static final long serialVersionUID = 1L;

    private final GeneralizedPrioritizationProblem problem;
    private List<CumulativeCoverage> cumulativeCoverages;
    private List<Integer> coverages;
    private final int length;
    private final int numberOfCoverageCriterion;
    private List<Integer> usedValues;
    private PermutationSolution<Integer> solution;

    public AdditionalGreedyPrioritization(GeneralizedPrioritizationProblem problem) {
        this.problem = problem;
        this.length = problem.costCriterion.size();
        this.numberOfCoverageCriterion = problem.coverageCriteria.size();
    }

    @Override
    public void run() {
        this.cumulativeCoverages = new ArrayList<>();
        this.coverages = new ArrayList<>();
        this.usedValues = new ArrayList<>();

        for (int i = 0; i < this.numberOfCoverageCriterion; i++) {
            this.cumulativeCoverages.add(new CumulativeCoverage(this.problem.coverageCriteria.get(i)));
        }

        for (int i = 0; i < this.numberOfCoverageCriterion; i++) {
            this.coverages.add(i, 0);
        }

        this.solution = new DefaultIntegerPermutationSolution(problem);

        int step = 0;

        while (step < this.length) {
            boolean isMaximum = true;
            this.solution.setVariableValue(step, this.getGreedyStep(this.solution, step, this.coverages));
            System.out.println("Greedy algorithm step: " + (step));
            step++;

            // if all coverage scores achieved the maximum values let's stop the cycle
            for (int index = 0; index < this.numberOfCoverageCriterion; index++) {
                isMaximum = isMaximum & this.cumulativeCoverages.get(index).hasReachedMaxCoverage();
            }
            if (isMaximum) {
                System.out.println("Rebooting at step:" + step);
                for (int i = 0; i < this.numberOfCoverageCriterion; i++) {
                    this.cumulativeCoverages.set(i, new CumulativeCoverage(this.problem.coverageCriteria.get(i)));
                    this.coverages.set(i, 0);
                }
            }
        }
    }

    private int getGreedyStep(PermutationSolution solution, int step, List<Integer> previousCoverages) {
        double maxim = 0;
        int bestStep = 0;

        for (int i = 0; i < length; i++) {
            if (!usedValues.contains(i)) {
                solution.setVariableValue(step, i);
                List<CumulativeCoverage> tmpObjectives = new ArrayList<>();
                for (int j = 0; j < this.numberOfCoverageCriterion; j++) {
                    tmpObjectives.add(this.cumulativeCoverages.get(j).copy());
                }
                List<Integer> tmpCoverages = new ArrayList<>();

                // let's update the cumulative coverage scores
                for (int index = 0; index < this.problem.coverageCriteria.size(); index++) {
                    int cumulativeCoverage = tmpObjectives.get(index).updateCoverage(i);
                    tmpCoverages.add(cumulativeCoverage);
                }

                double greedy = 0;
                for (int j = 0; j < this.numberOfCoverageCriterion; j++) {
                    greedy += Math.abs(tmpCoverages.get(j) - previousCoverages.get(j));
                }
                greedy = greedy / this.problem.costCriterion.getCostOfTest(step);

                if (greedy > maxim || maxim == 0) {
                    maxim = greedy;
                    bestStep = i;
                }
            }
        }
        // let's update the cumulative coverage scores
        for (int index = 0; index < this.numberOfCoverageCriterion; index++) {
            int coverage = this.cumulativeCoverages.get(index).updateCoverage(bestStep);
            this.coverages.set(index, coverage);
        }
        this.usedValues.add(bestStep);
        return bestStep;
    }

    @Override
    public PermutationSolution getResult() {
        return this.solution;
    }

    @Override
    public String getName() {
        return "AdditionalGreedyPrioritization";
    }

    @Override
    public String getDescription() {
        return "This is the implementation of the test case prioritization greedy algorithm"; 
    }
}
