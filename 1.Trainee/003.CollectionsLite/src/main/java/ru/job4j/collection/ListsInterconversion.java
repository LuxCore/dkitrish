package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Interconversion of List and array into each other.
 */
public class ListsInterconversion {
	/**
	 * Converts two dimensional array into List&lt;Integer&gt;.
	 *
	 * @param array Two dimensional array of integers.
	 *
	 * @return Converted array into List.
	 */
	public List<Integer> toList(int[][] array) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				list.add(array[i][j]);
			}
		}

		return list;
	}

	/**
	 * Converts List&lt;Integer&gt; array into two dimensional.
	 *
	 * @param list List of integers.
	 *
	 * @return Converted List into array.
	 */
	public int[][] toArray(List<Integer> list) {
		double res = Math.sqrt((double) list.size());
		int dim = (int) Math.ceil(res);

		Iterator<Integer> iter = list.iterator();

		int[][] array = new int[dim][dim];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = iter.hasNext() ? iter.next() : 0;
			}
		}
		return array;
	}

	/**
	 * Fills given list with static random integers.
	 *
	 * @param list List that must be filled in.
	 * @param amount Amount of elements must be added into collection.
	 *
	 * @return Filled list with static random integers.
	 */
	public static List<Integer> fillListWithRandomInts(List<Integer> list, int amount) {
		Random rnd = new Random(69);
		// List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= amount; i++) {
			list.add(rnd.nextInt(69));
		}
		return list;
	}
}
