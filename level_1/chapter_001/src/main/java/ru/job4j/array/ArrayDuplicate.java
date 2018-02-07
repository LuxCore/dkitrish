package ru.job4j.array;

import java.util.Arrays;

/**
 * Deleting duplicates from array.
 *
 * @author Denis.Kitrish
 * @since 13.10.2017
 * @version 1.0
 */
public class ArrayDuplicate {

	/**
	 * Deleting duplicates from array with help of BubbleSort.
	 *
	 * @param array Given array.
	 *
	 * @return String[] Array without duplicates.
	 */
	public String[] remove(String[] array) {
		// String to compare with current array's element.
		String compStr = null;

		// Maximum array's element we modify to avoid repeated
		// traversal along duplicates at the end of an array.
		int imax = array.length;

		// Count of found duplicates.
		int dupCount = 0;

		for (int j = 0; j < imax; j++) {
			compStr = array[j];
			for (int i = j + 1; i < imax; i++) {
				if (compStr == array[i]) {
					dupCount++;
					if (i < imax - 1) {
						moveToBeginOfDuplicateQueue(array, i, imax - 1);
					}

					i--;
					imax--;
				}
			}
		}

		return Arrays.copyOf(array, array.length - dupCount);
	}

	/**
	 * Moving of found duplicate to the end of an array with BubbleSort.
	 *
	 * @param array Given array.
	 * @param j Index of element to move to the end of an array.
	 * @param i Last index where the element must be moved.
	 */
	private static void moveToBeginOfDuplicateQueue(String[] array, int j, int i) {
		String tmp = null;
		for (int k = j; k < i; k++) {
			tmp = array[k];
			array[k] = array[k + 1];
			array[k + 1] = tmp;
		}
	}
}