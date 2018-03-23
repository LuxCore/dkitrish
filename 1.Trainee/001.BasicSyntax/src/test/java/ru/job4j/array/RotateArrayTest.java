package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests rotating of array 90 degrees clockwise.
 *
 * @author Denis.Kitrish
 * @since 07.10.2017
 * @version 1.0
 */
public class RotateArrayTest {

	/**
	 * RotateArray object.
	 */
	private RotateArray ra = new RotateArray();

	/**
	 * Tests rotating of an array with 2X2 elements.
	 */
	@Test
	public void testRotateArrayWithTwoByTwoElements() {
		int[][] array = new int[2][2];
		ra.initArray(array);
		int[][] result = ra.rotate(array);
		int[][] expected = new int[][]{{3, 1}, {4, 2}};

		assertThat(result, is(expected));
	}

	/**
	 * Tests rotating of an array with 3X3 elements.
	 */
	@Test
	public void testRotateArrayWithThreeByThreeElements() {
		int[][] array = new int[3][3];
		ra.initArray(array);
		int[][] result = ra.rotate(array);
		int[][] expected = new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};

		assertThat(result, is(expected));
	}

	/**
	 * Tests rotating of an array with 10X10 elements.
	 */
	@Test
	public void testRotateArrayWithTenByTenElements() {
		int[][] array = new int[10][10];
		ra.initArray(array);
		int[][] result = ra.rotate(array);
		int[][] expected = new int[][]{{91, 81, 71, 61, 51, 41, 31, 21, 11, 1}, {92, 82, 72, 62, 52, 42, 32, 22, 12, 2}, {93, 83, 73, 63, 53, 43, 33, 23, 13, 3}, {94, 84, 74, 64, 54, 44, 34, 24, 14, 4}, {95, 85, 75, 65, 55, 45, 35, 25, 15, 5}, {96, 86, 76, 66, 56, 46, 36, 26, 16, 6}, {97, 87, 77, 67, 57, 47, 37, 27, 17, 7}, {98, 88, 78, 68, 58, 48, 38, 28, 18, 8}, {99, 89, 79, 69, 59, 49, 39, 29, 19, 9}, {100, 90, 80, 70, 60, 50, 40, 30, 20, 10}};

		assertThat(result, is(expected));
	}
}