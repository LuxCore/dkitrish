package ru.job4j.sort;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests comparison of List instances.
 */
public class ListCompareTest {
	/**
	 * Tests two Lists when they are equal by length and elements.
	 */
	@Test
	public void testListsWhenTheyAreEqual() {
		ListCompare lstComp = new ListCompare();
		int result = lstComp.compare(
			Arrays.asList(1, 2, 3),
			Arrays.asList(1, 2, 3)
		);

		assertThat(result, is(0));
	}

	/**
	 * Tests two Lists when length of first List is less then second one
	 * but according elements are equal.
	 */
	@Test
	public void testListsWhenFirstIsLess() {
		ListCompare lstComp = new ListCompare();
		int result = lstComp.compare(
			Arrays.asList(1),
			Arrays.asList(1, 2, 3)
		);

		assertThat(result, is(-1));
	}

	/**
	 * Tests two Lists when lenths of Lists are equal.
	 */
	@Test
	public void testListsWhenFirstIsGreater() {
		ListCompare lstComp = new ListCompare();
		int result = lstComp.compare(
			Arrays.asList(1, 2),
			Arrays.asList(1, 1)
		);

		assertThat(result, is(1));
	}

	/**
	 * Tests two Lists when first List is greater then second one.
	 */
	@Test
	public void testListsWhenFirstLengthIsGreaterAndElemsAreLess() {
		ListCompare lstComp = new ListCompare();
		int result = lstComp.compare(
			Arrays.asList(1, 2, 3),
			Arrays.asList(2, 3)
		);

		assertThat(result, is(-1));
	}
}
