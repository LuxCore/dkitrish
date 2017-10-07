package ru.job4j.array;

/**
 * Sorting of an array by bubble method.
 *
 * @author Denis.Kitrish
 * @since 02.10.2017
 * @version 1.0
 */
public class BubbleSort {

	/**
	 * BubbleSort.
	 *
	 * @param array Given array.
	 *
	 * @return int[] Inverted array.
	 */
	public int[] sort(int[] array) {
		if (array.length == 0) {
			return null;
		}

		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j + 1);
				}
			}
		}

		return array;
	}

	/**
	 * Helps to swap elements.
	 *
	 * @param array Given array.
	 * @param first First element that must be moved in place of second element.
	 * @param second Second element that must be moved in place of first element.
	 */
	public void swap(int[] array, int first, int second) {
		int tmp = array[first];
		array[first] = array[second];
		array[second] = tmp;
	}
}