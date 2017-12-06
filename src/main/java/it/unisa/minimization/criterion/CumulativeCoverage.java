package it.unisa.minimization.criterion;

/**
 * Class used to compute the cumulative coverage for a specific coverage
 * criterion
 *
 * @author Annibale Panichella
 */
public class CumulativeCoverage {

    private int[] binaryCoverage;
    private CoverageMatrix cov;
    private boolean reachedMaxCoverage = false;

    public CumulativeCoverage(CoverageMatrix coverageMatrix_) {
        binaryCoverage = new int[coverageMatrix_.numberOfTargets()];
        this.cov = coverageMatrix_;
    }

    public int updateCoverage(int testIndex) {
        int cumulativeCoverage = 0;
        for (int j = 0; j < cov.numberOfTargets(); j++) {
            binaryCoverage[j] = Math.max(binaryCoverage[j], (int) cov.getElement(testIndex, j));
            cumulativeCoverage += binaryCoverage[j];
        }
        if (cumulativeCoverage == cov.maxCoverage_) {
            reachedMaxCoverage = true;
        }

        return cumulativeCoverage;
    }

    public CumulativeCoverage copy() {
        CumulativeCoverage copy = new CumulativeCoverage(this.cov);

        int cumulativeCoverage = 0;
        for (int j = 0; j < cov.numberOfTargets(); j++) {
            copy.binaryCoverage[j] = binaryCoverage[j];
            cumulativeCoverage += binaryCoverage[j];
        }
        if (cumulativeCoverage == cov.maxCoverage_) {
            reachedMaxCoverage = true;
        }

        return copy;
    }

    public boolean hasReachedMaxCoverage() {
        return reachedMaxCoverage;
    }

    public int getMaxCoverage() {
        return this.cov.maxCoverage_;
    }
}
