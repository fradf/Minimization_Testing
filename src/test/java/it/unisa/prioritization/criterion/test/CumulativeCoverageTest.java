package it.unisa.prioritization.criterion.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.unisa.prioritization.criterion.CoverageMatrix;
import it.unisa.prioritization.criterion.CumulativeCoverage;

public class CumulativeCoverageTest {

    private CoverageMatrix coverageMatrix;

    @Before
    public void initialize() {
        coverageMatrix = new CoverageMatrix("io/test/comp_coverage.csv", false);
    }

    @Test
    public void testOrder1() {
        CumulativeCoverage cumulative = new CumulativeCoverage(coverageMatrix);
        assertEquals(10, cumulative.getMaxCoverage(), 0.00001);
        assertEquals(8, cumulative.updateCoverage(0), 0.00001);
        assertEquals(10, cumulative.updateCoverage(1), 0.00001);
        assertEquals(10, cumulative.updateCoverage(2), 0.00001);
        assertEquals(10, cumulative.updateCoverage(3), 0.00001);
    }

    @Test
    public void testOrder2() {
        CumulativeCoverage cumulative = new CumulativeCoverage(coverageMatrix);
        assertEquals(10, cumulative.getMaxCoverage(), 0.00001);
        assertEquals(9, cumulative.updateCoverage(1), 0.00001);
        assertEquals(10, cumulative.updateCoverage(2), 0.00001);
        assertEquals(10, cumulative.updateCoverage(0), 0.00001);
        assertEquals(10, cumulative.updateCoverage(3), 0.00001);
    }

    @Test
    public void testOrder3() {
        CumulativeCoverage cumulative = new CumulativeCoverage(coverageMatrix);
        assertEquals(10, cumulative.getMaxCoverage(), 0.00001);
        assertEquals(4, cumulative.updateCoverage(2), 0.00001);
        assertEquals(6, cumulative.updateCoverage(3), 0.00001);
        assertEquals(9, cumulative.updateCoverage(0), 0.00001);
        assertEquals(10, cumulative.updateCoverage(1), 0.00001);
    }

}
