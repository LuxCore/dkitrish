package ru.job4j.sort;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Compares instances of List.
 */
public class ListCompare implements Comparator<List<Integer>> {
	/**
	 * Compares two lists of integers.
	 *
	 * @param one First List to compare with second one.
	 * @param two Second List to compare with first one.
	 *
	 * @return Result that can return one of three values: -1, 0, 1
	 */
	@Override
	public int compare(List<Integer> one, List<Integer> two) {
		Integer result = 0;
		Iterator iter2 = two.iterator();
		for (Integer item1 : one) {
			if (iter2.hasNext()) {
				Integer item2 = (Integer) iter2.next();
				result += Integer.compare(item1, item2);
			}
		}

		int len1 = one.size();
		int len2 = two.size();
		if (result == 0 && len1 != len2) {
			result = len1 > len2 ? 1 : -1;
		}

		if (result != 0) {
			result = result < 0 ? -1 : 1;
		}

		return result;
	}
}
