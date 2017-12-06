package it.unisa.prioritization.problems;

import it.unisa.prioritization.criterion.GeneralizedAveragePercentage;
import java.util.List;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.solution.impl.DefaultIntegerPermutationSolution;

/**
 * Class representing a Testing Prioritization problem with any number of
 * testing criteria and a multiple objective.
 *
 * @author Dario Di Nucci
 */
public class MultiObjectiveGeneralizedPrioritizationProblem extends GeneralizedPrioritizationProblem {

    /**
     * Public constructor
     *
     * @param coverageFilenames List of files (absolute path) storing the
     * coverage matrices
     * @param costFilename file containing the execution cost info
     * @param faultFilename file containing the fault coverage matrix
     */
    public MultiObjectiveGeneralizedPrioritizationProblem(List<String> coverageFilenames, String costFilename, String faultFilename, boolean compacted) {
        super(coverageFilenames, costFilename, faultFilename, compacted);
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
        return "MultiObjectiveGeneralizedPrioritizationProblem";
    }

    @Override
    public void evaluate(PermutationSolution<Integer> solution) {
        for (int i = 0; i < this.coverageCriteria.size(); i++) {
            //Coverage criteria are set in order to be maximized
            double criteriaAveragePercentage = GeneralizedAveragePercentage.calculate(solution, this.coverageCriteria.get(i), this.costCriterion, true);
            solution.setObjective(i, -criteriaAveragePercentage);
        }
    }

    @Override
    public DefaultIntegerPermutationSolution createSolution() {
        return new DefaultIntegerPermutationSolution(this);
    }

}
