package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
* Converts List.
*/
public class ConvertList {
	/**
	 * Converts List<int[]> into List<Integer>.
	 *
	 * @param listOfArrays
	 *            List of integer arrays that must be converted to List of
	 *            Integers.
	 *
	 * @return Converted List of integer arrays into List of Integers.
	 */
	public List<Integer> listOfArraysToList(List<int[]> listOfArrays) {
		List<Integer> list = new ArrayList<>();

		for (int[] arrayItem : listOfArrays) {
			for (int i = 0; i < arrayItem.length; i++) {
				list.add(arrayItem[i]);
			}
		}

		return list;
	}

	/**
	 * Fills given List with arrays of different dimensions.
	 *
	 * @param listOfArrays
	 *            List that we must to fill with arrays.
	 * @param randomSeed
	 *            Random static integer used for setting of max value of array
	 *            elements.
	 * @param amountOfListElements
	 *            Amount of array elements in list.
	 * @param amountOfArrayElements
	 *            Max amount of array elements.
	 */
	public static void fillListWithArrays(List<int[]> listOfArrays, int randomSeed, int amountOfListElements,
			int amountOfArrayElements) {
		Random rnd = new Random(randomSeed);
		int[] array = null;

		for (int i = 0; i < amountOfListElements; i++) {
			int jmax = rnd.nextInt(amountOfArrayElements);
			array = new int[jmax];

			for (int j = 0; j < jmax; j++) {
				array[j] = rnd.nextInt(randomSeed);
			}

			listOfArrays.add(array);
		}
	}
}
