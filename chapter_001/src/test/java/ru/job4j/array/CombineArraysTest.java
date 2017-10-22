package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test combining of two sorted arrays.
 *
 * @author Denis.Kitrish
 * @since 22.10.2017
 * @version 1.0
 */
public class CombineArraysTest {
	/**
	 * Test combining of two sorted arrays using API.
	 */
	@Test
	public void testCombineOfTwoSortedArrays() {
		CombineArrays ca = new CombineArrays();

		byte[] array1 = {1, 3, 5, 15, 15, 16, 20, 29, 39, 64, 65, 69};
		byte[] array2 = {1, 9, 15, 25, 29, 29, 55};

		byte[] result = ca.combine(array1, array2);
		byte[] expected = {1, 1, 3, 5, 9, 15, 15, 15, 16, 20, 25, 29, 29, 29, 39, 55, 64, 65, 69};

		assertThat(result, is(expected));
	}
}