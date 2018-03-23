package ru.job4j.array;

/**
 * Inverts an array.
 *
 * @author Denis.Kitrish
 * @since 02.10.2017
 * @version 1.0
 */
public class Turn {

	/**
	 * Inverts a given array.
	 *
	 * @param array Given array.
	 *
	 * @return int[] Inverted array.
	 */
	public int[] back(int[] array) {
		int tmp = 0;
		int halfArr = array.length % 2 == 0 ? array.length / 2 : (array.length - 1) / 2;
		for (int i = 0; i <= halfArr - 1; i++) {
			// Remember element from left side into temporary variable.
			tmp = array[i];

			// Write mirror element from right side to left.
			array[i] = array[array.length - 1 - i];

			// Write remembered element into cell of right side.
			array[array.length - 1 - i] = tmp;
		}

		return array;
	}
}