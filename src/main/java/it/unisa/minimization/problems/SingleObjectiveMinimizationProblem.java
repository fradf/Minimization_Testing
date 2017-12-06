package it.unisa.minimization.problems;

import it.unisa.minimization.criterion.CumulativeCoverage;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.solution.impl.DefaultIntegerPermutationSolution;

import java.util.ArrayList;
import java.util.List;

public class SingleObjectiveMinimizationProblem extends GeneralizedMinimizationProblem {
   public SingleObjectiveMinimizationProblem (List<String> coverageFilenames, String costFilename, String faultFilename, boolean compacted) {

        super(coverageFilenames, costFilename, faultFilename, compacted);
    }

    private static final long serialVersionUID = 1L;
    public ArrayList<Integer> appoggio= new ArrayList<Integer>();

    /**
     * Evaluates a solution
     *
     * @param solution The solution to evaluate
     */
    @Override
    public void evaluate(PermutationSolution<Integer> solution) {
        // let's create cumulative coverage analyzers (one analyzer for each coverage matrix)
        List<CumulativeCoverage> objectives = new ArrayList<>();
        for (int i = 0; i < this.coverageCriteria.size(); i++) {
            objectives.add(new CumulativeCoverage(this.coverageCriteria.get(i)));
        }

        double cost = 0;
        // initialize the cumulative coverage scores to 0
        List<Double> coverages = new ArrayList<>();
        for (int i = 0; i < this.coverageCriteria.size(); i++) {
            coverages.add(i, 0.0);
        }
        double hyperVolume = 0;
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            double previousCost = cost;

            cost = cost + costCriterion.getCostOfTest(solution.getVariableValue(i));

            // let's update the cumulative coverage scores
            for (int index = 0; index < this.coverageCriteria.size(); index++) {
                double cumulativeCoverage = objectives.get(index).updateCoverage(solution.getVariableValue(i));
                coverages.set(index, cumulativeCoverage);
            }

            // let's update the hyperVolume
            double delta = 1;
            for (int index = 0; index < this.coverageCriteria.size(); index++) {
                delta = delta * coverages.get(index);
            }
            hyperVolume = hyperVolume + (cost - previousCost) * delta;

            // if all coverage scores achieved the maximum values let's stop the cycle
            boolean isMaximum = true;
            for (int index = 0; index < this.coverageCriteria.size(); index++) {
                isMaximum = isMaximum & objectives.get(index).hasReachedMaxCoverage();
                indexMax=index;
            }
            if (isMaximum) {
                break;
            }
        }
        // we add the remaining part of the hyperVolume (due to early stop when all coverage
        // criteria reach the maximum coverage
        double delta = 1;
        for (int index = 0; index < this.coverageCriteria.size(); index++) {
            delta = delta * this.coverageCriteria.get(index).maxCoverage_;
        }
        hyperVolume = hyperVolume + delta * (costCriterion.getMaxCost() - cost);

        // let's normalize the hypervolume
        for (int index = 0; index < this.coverageCriteria.size(); index++) {
            hyperVolume = hyperVolume / objectives.get(index).getMaxCoverage();
        }
        hyperVolume = hyperVolume / costCriterion.getMaxCost();
        solution.setObjective(0, -hyperVolume);
    }

    @Override
    public int getNumberOfVariables() {
        return this.costCriterion.size();
    }

    @Override
    public int getNumberOfObjectives() {
        return this.coverageCriteria.size();
    }

    @Override
    public int getNumberOfConstraints() {
        return 0;
    }

    @Override
    public String getName() {
        return "SingleObjectiveMinimizationProblem";
    }

    @Override
    public DefaultIntegerPermutationSolution createSolution() {
        return new DefaultIntegerPermutationSolution(this);
    }

}
