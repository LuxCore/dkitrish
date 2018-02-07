package ru.job4j.testtask;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing of parse organization structure and sorting it.
 */
public class DepStructureTest {
	/**
	 * Here we test method that verify given array of strings (string contains
	 * sequence of department structure). If the array does not contain
	 * some sequence of deps then it adds one.
	 */
	@Test
	public void testCorrectionOrganizationStructure() {
		DepStructure depStructure = new DepStructure();
		String[] deps = {
			"K1\\K2\\K3\\K4",
			"A11\\A22\\A33",
			"A1\\A22\\A33",
			"K1\\K2",
			"K1\\K22\\K3\\K4",
			"B111",
			"B111\\B2\\B33\\B4\\B5"
		};
		String[] result = depStructure.correct(deps);
		String[] expected = {
			"K1\\K2\\K3\\K4",
			"A11\\A22\\A33",
			"A1\\A22\\A33",
			"K1\\K2",
			"K1\\K22\\K3\\K4",
			"B111",
			"B111\\B2\\B33\\B4\\B5",
			"A1",
			"A11",
			"B111\\B2\\B33",
			"K1",
			"K1\\K2\\K3",
			"K1\\K22\\K3",
			"A11\\A22",
			"B111\\B2\\B33\\B4",
			"A1\\A22",
			"K1\\K22",
			"B111\\B2"
		};
		assertThat(result, is(expected));
	}

	/**
	 * Test when structure is rigth and Set does not filled.
	 */
	@Test
	public void testWhenDepStructureIsRight() {
		DepStructure depStructure = new DepStructure();
		String[] deps = {
			"K1",
			"K1\\K2",
			"K1\\K2\\K3",
			"K1\\K2\\K3\\K4",
		};
		String[] result = depStructure.correct(deps);
		String[] expected = null;
		assertThat(result, is(expected));
	}

	/**
	 * Here we test our comparator that sorts given array of department
	 * sequences in descending order.
	 */
	@Test
	public void testDescendingOrderOfOrganizationStructure() {
		DepStructure depStructure = new DepStructure();
		String[] deps = {
			"K1\\K2\\K3\\K4",
			"A11\\A22\\A33",
			"A1\\A22\\A33",
			"K1\\K2",
			"K1\\K22\\K3\\K4",
			"B111",
			"B111\\B2\\B33\\B4\\B5"
		};
		String[] result = depStructure.correct(deps);
		Arrays.sort(result, DepStructure.DESCENDING_ORDER);
		String[] expected = {
			"K1",
			"K1\\K2",
			"K1\\K2\\K3",
			"K1\\K2\\K3\\K4",
			"K1\\K22",
			"K1\\K22\\K3",
			"K1\\K22\\K3\\K4",
			"B111",
			"B111\\B2",
			"B111\\B2\\B33",
			"B111\\B2\\B33\\B4",
			"B111\\B2\\B33\\B4\\B5",
			"A11",
			"A11\\A22",
			"A11\\A22\\A33",
			"A1",
			"A1\\A22",
			"A1\\A22\\A33"
		};
		assertThat(result, is(expected));
	}

	/**
	 * Here we test our comparator that sorts given array of department
	 * sequences in ascending order.
	 */
	@Test
	public void testAscendingOrderOfOrganizationStructure() {
		DepStructure depStructure = new DepStructure();
		String[] deps = {
			"K1\\K2\\K3\\K4",
			"A11\\A22\\A33",
			"A1\\A22\\A33",
			"K1\\K2",
			"K1\\K22\\K3\\K4",
			"B111",
			"B111\\B2\\B33\\B4\\B5"
		};
		String[] result = depStructure.correct(deps);
		Arrays.sort(result, DepStructure.ASCENDING_ORDER);
		String[] expected = {
			"A1",
			"A1\\A22",
			"A1\\A22\\A33",
			"A11",
			"A11\\A22",
			"A11\\A22\\A33",
			"B111",
			"B111\\B2",
			"B111\\B2\\B33",
			"B111\\B2\\B33\\B4",
			"B111\\B2\\B33\\B4\\B5",
			"K1",
			"K1\\K2",
			"K1\\K2\\K3",
			"K1\\K2\\K3\\K4",
			"K1\\K22",
			"K1\\K22\\K3",
			"K1\\K22\\K3\\K4"
		};
		assertThat(result, is(expected));
	}
}
