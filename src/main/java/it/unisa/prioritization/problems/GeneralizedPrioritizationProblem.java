package it.unisa.prioritization.problems;

import it.unisa.prioritization.criterion.CoverageMatrix;
import it.unisa.prioritization.criterion.ExecutionCostVector;
import java.util.ArrayList;
import java.util.List;
import org.uma.jmetal.problem.PermutationProblem;
import org.uma.jmetal.solution.PermutationSolution;

/**
 * Super class representing a Testing Prioritization problem with any number of
 * testing criteria.
 *
 * @author Dario Di Nucci
 */
public abstract class GeneralizedPrioritizationProblem implements PermutationProblem<PermutationSolution<Integer>> {

    public List<CoverageMatrix> coverageCriteria = new ArrayList<>();
    public ExecutionCostVector costCriterion;
    public CoverageMatrix faultMatrix;

    /**
     * Public constructor
     *
     * @param coverageFilenames List of files (absolute path) storing the
     * coverage matrices
     * @param costFilename file containing the execution cost info
     * @param faultFilename file containing the fault coverage matrix
     * @param compacted
     */
    public GeneralizedPrioritizationProblem(List<String> coverageFilenames, String costFilename, String faultFilename, boolean compacted) {
        // load all coverage matrices
        for (String filename : coverageFilenames) {
            CoverageMatrix cov;
            cov = new CoverageMatrix(filename, true);
            this.coverageCriteria.add(cov);
            System.out.println("Read " + cov.getSize() + " elements from the coverage matrix '" + filename + "'");
        }

        // load cost info
        costCriterion = new ExecutionCostVector(costFilename);
        System.out.println("Read " + costCriterion.size() + " elements from the cost array.");

        // past fault matrix must not be compacted beacuse it is used for computing APFDc
        faultMatrix = new CoverageMatrix(faultFilename, compacted);
        System.out.println("Read " + faultMatrix.getSize() + " elements from the coverage matrix '" + faultFilename + "'");
    }

    @Override
    public int getPermutationLength() {
        return this.costCriterion.size();
    }

}
