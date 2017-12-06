package it.unisa.minimization.problems;

import it.unisa.minimization.criterion.CoverageMatrix;
import it.unisa.minimization.criterion.ExecutionCostVector;
import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.solutionattribute.impl.NumberOfViolatedConstraints;
import org.uma.jmetal.util.solutionattribute.impl.OverallConstraintViolation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class MultiObjectiveMinimizationProblem extends AbstractBinaryProblem implements ConstrainedProblem<BinarySolution> {


    private List<CoverageMatrix> coverageCriteria;
    private ExecutionCostVector costCriterion;
    private CoverageMatrix faultMatrix;
    private OverallConstraintViolation<BinarySolution> overallConstraintViolationDegree;
    private NumberOfViolatedConstraints<BinarySolution> numberOfViolatedConstraints;

    private int[] solutionObjectives;

    @Override
    protected int getBitsPerVariable(int i) {
        return this.costCriterion.size();
    }

    public MultiObjectiveMinimizationProblem(List<String> coverageFilenames, String costFilename, String faultFilename, boolean compacted) {
        //carico le matrici
        for (String filename : coverageFilenames) {
            CoverageMatrix cov;
            cov = new CoverageMatrix(filename, true);
            this.coverageCriteria.add(cov);
            System.out.println("Read " + cov.getSize() + " element from the coverage matrix '" + filename + "'");
        }
        //carico vettore costi
        costCriterion = new ExecutionCostVector(costFilename);
        System.out.println("Read " + costCriterion.size() + " elements from the cost array.");

        faultMatrix = new CoverageMatrix(faultFilename, compacted);
        System.out.println("Read " + faultMatrix.getSize() + " elements from the coverage matrix '" + faultFilename + "'");

        setNumberOfVariables(1);
        setNumberOfObjectives(this.coverageCriteria.size());
        setNumberOfConstraints(1);
        overallConstraintViolationDegree = new OverallConstraintViolation<>();
        numberOfViolatedConstraints = new NumberOfViolatedConstraints<>();
    }

    @Override
    public void evaluate(BinarySolution solution) {
        int numberOfTests = this.coverageCriteria.get(0).numberOfTests();
        int numberOfTargets = this.coverageCriteria.get(0).numberOfTargets();
        for (int i = 0; i < numberOfTargets; i++) {
            solution.setObjective(i, -solutionObjectives[i]);
        }

        int cost = 0;

        for (int i = 0; i < numberOfTests; i++) {
            if (solution.getVariableValue(0).get(i) == true) {
                cost += this.costCriterion.getCostOfTest(i);
            }
        }

        solution.setObjective(this.coverageCriteria.size(), cost);

    }

    @Override
    public void evaluateConstraints(BinarySolution solution) {
        int numberOfTests = this.coverageCriteria.get(0).numberOfTests();
        int numberOfTargets = this.coverageCriteria.get(0).numberOfTargets();
        int[] binaryCoverage = new int[numberOfTests];
        double overallConstraintViolation = 0.0;
        int violatedConstraints = 0;

        solutionObjectives = new int[this.coverageCriteria.size() + 1];

        for (int i = 0; i < this.coverageCriteria.size(); i++) {

            CoverageMatrix cov = this.coverageCriteria.get(i);

            for (int j = 0; j < numberOfTests; j++) {
                for (int z = 0; z < numberOfTargets; z++) {
                    binaryCoverage[z] = Math.max(binaryCoverage[z], cov.getElement(j, z));
                }
            }

            int numberOfCoveredTargets = 0;
            for (int j = 0; j < numberOfTargets; j++) {
                numberOfCoveredTargets += binaryCoverage[j];
            }

            solutionObjectives[i] = numberOfCoveredTargets;

            if (numberOfCoveredTargets != numberOfTests) {
                overallConstraintViolation += (numberOfTests - numberOfCoveredTargets);
                violatedConstraints++;
            }
        }

        overallConstraintViolationDegree.setAttribute(solution, overallConstraintViolation);
        numberOfViolatedConstraints.setAttribute(solution, violatedConstraints);
    }
}
