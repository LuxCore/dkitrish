package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests inverting of an array.
 *
 * @author Denis.Kitrish
 * @since 02.10.2017
 * @version 1.0
 */
public class TurnTest {

	/**
	 *
	 */
	private Turn turn = new Turn();

	/**
	 * Test of inverting array {2, 6, 1, 4}.
	 */
	@Test
	public void testInvertArrayWithEvenNumberOfElements() {
		int[] array = new int[]{2, 6, 1, 4};
		int[] invertedArray = turn.back(array);

		int[] expected = new int[]{4, 1, 6, 2};

		assertThat(invertedArray, is(expected));
	}

	/**
	 * Test of inverting array {1, 2, 3, 4, 5}.
	 */
	@Test
	public void testInvertArrayWithOddNumberOfElements() {
		int[] array = new int[]{1, 2, 3, 4, 5};
		int[] invertedArray = turn.back(array);

		int[] expected = new int[]{5, 4, 3, 2, 1};

		assertThat(invertedArray, is(expected));
	}
}