package it.unisa.prioritization.criterion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unisa.prioritization.criterion.CoverageMatrix;

public class CoverageMatrixTest {

    @Test
    public void test() {
        CoverageMatrix coverageMatrix = new CoverageMatrix("io/test/comp_coverage.csv", false);
        assertTrue(coverageMatrix.getElement(0, 0) == 2);
        assertTrue(coverageMatrix.getElement(1, 3) == 3);
        assertTrue(coverageMatrix.numberOfTargets() == 6);
        assertTrue(coverageMatrix.numberOfTests() == 4);
    }

}
