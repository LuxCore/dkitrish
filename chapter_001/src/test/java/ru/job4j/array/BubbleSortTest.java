package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test sorting of an array by bubble method.
 *
 * @author Denis.Kitrish
 * @since 02.10.2017
 * @version 1.0
 */
public class BubbleSortTest {

	/**
	 * Tests sorting of array with 10 elements.
	 */
	@Test
	public void testSortArrayWithTenElements() {
		BubbleSort bs = new BubbleSort();
		int[] array = new int[]{1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
		int[] result = bs.sort(array);
		int[] expected = new int[]{0, 1, 1, 2, 3, 4, 5, 5, 7, 8};

		assertThat(result, is(expected));
	}
}