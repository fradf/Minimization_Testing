package it.unisa.prioritization.criterion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unisa.prioritization.criterion.ExecutionCostVector;

public class ExecutionCostVectorTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		ExecutionCostVector cost = new ExecutionCostVector("io/test/cost.csv");
		assertEquals(4, cost.size());
		assertEquals(4, cost.getCostOfTest(0),0.001);
		assertEquals(5, cost.getCostOfTest(1),0.001);
		assertEquals(3, cost.getCostOfTest(2),0.001);
		assertEquals(3, cost.getCostOfTest(3),0.001);
	}

}
