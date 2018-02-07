package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

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
}
