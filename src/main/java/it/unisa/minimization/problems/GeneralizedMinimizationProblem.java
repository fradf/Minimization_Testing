package it.unisa.minimization.problems;

import it.unisa.minimization.criterion.CoverageMatrix;
import it.unisa.minimization.criterion.ExecutionCostVector;
import org.uma.jmetal.problem.PermutationProblem;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public abstract class GeneralizedMinimizationProblem implements PermutationProblem<PermutationSolution<Integer>> {



    public List<CoverageMatrix> coverageCriteria = new ArrayList<>();
    public ExecutionCostVector costCriterion;
    public CoverageMatrix faultMatrix;

    public GeneralizedMinimizationProblem(List<String> coverageFilenames, String costFilename, String faultFilename, boolean compacted) {
        //carico le matrici
        for (String filename: coverageFilenames) {
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

    }
    public int indexMax;

    public int getPermutationLength(){return this.costCriterion.size();}
    public int max(int i, int j){
        if(i<=j) return j;
        else return i;
    }

}