package ru.job4j.testtask;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class processes structure of departments.
 */
public class DepStructure {
	/**
	 * Method searches sequence of departments in srcStruct and if there is no
	 * such then it adds one at first in Set<String>. At the end Set is checked
	 * on emptyness - if Set is not empty then it is created new array with
	 * length equals to sum of lengths of source array and of set and it is
	 * full-filled with their values.
	 * RegEx is used here.
	 *
	 * В исходной структуре департаментов srcStruct смотрим отсутсвие искомой
	 * последовательности департаментов. По окончании старую структуру мы
	 * возвращаем, а новую назначаем переменной srcStruct.
	 *
	 * @param srcStruct Source array of structure.
	 *
	 * @return Old structure.
	 */
	public String[] correct(String[] srcStruct) {
		Pattern p = null;
		Matcher m = null;
		String depSeq = null;
		boolean depsFound = false;
		Set<String> depsSet = new HashSet<>();

		for (int i = 0; i < srcStruct.length; i++) {
			int slashes = srcStruct[i].length() - srcStruct[i].replaceAll("\\\\", "").length();
			for (int j = 0; j <= slashes; j++) {
				p = Pattern.compile("\\w+(\\\\\\w+){" + j + "}");
				m = p.matcher(srcStruct[i]);
				depsFound = false;
				if (m.find()) {
					depSeq = m.group();
				}
				for (int k = 0; k < srcStruct.length; k++) {
					if (srcStruct[k].equals(depSeq)) {
						depsFound = true;
						break;
					}
				}
				if (!depsFound) {
					depsSet.add(depSeq);
				}
			}
		}

		String[] newStruct = null;
		if (!depsSet.isEmpty()) {
			newStruct = new String[srcStruct.length + depsSet.size()];
			System.arraycopy(srcStruct, 0, newStruct, 0, srcStruct.length);
			Object[] tmpArr = depsSet.toArray();
			System.arraycopy(tmpArr, 0, newStruct, srcStruct.length, tmpArr.length);
		}

		return newStruct;
	}

	/**
	 * Descending order of department sequences.
	 */
	public static final Comparator<String> DESCENDING_ORDER = new DescendingOrder();

	/**
	 * Implementation of reverse ordering of department structure.
	 * First of all two departments is sorted by first-level
	 * department in reverse order. After that departments on all next levels
	 * are sorted in natural order.
	 */
	private static class DescendingOrder implements Comparator<String> {
		/**
		 * Compares two sequences of departments.
		 *
		 * @param o1 First sequence of departments.
		 * @param o2 Second sequence of departments.
		 *
		 * @return Integer result of comparing.
		 */
		@Override
		public int compare(String o1, String o2) {
			String[] deps1 = o1.split("\\\\");
			String[] deps2 = o2.split("\\\\");
			// Minimum department length.
			int dlm = Math.min(deps1.length, deps2.length);
			int res = 0;
			for (int i = 0; i < dlm; i++) {
				// 1. Сравниваем департаменты, находящиеся на одном уровне.
				// 1. Comparing of departments that are on the same level.
				if (!deps1[i].equals(deps2[i])) {
					if (i > 0) {
						res = deps1[i].compareTo(deps2[i]);
					} else {
						res = deps2[i].compareTo(deps1[i]);
					}
					break;
				} else {
					// 2. Сравниваем по длине массивы: с меньшей длиной - выше.
					// 2. If all departments are matched on all leves then
					// its comparing by length.
					res = deps1.length - deps2.length;
				}
			}

			return res;
		}
	}

	/**
	 * Ascending order of department sequences.
	 */
	public static final Comparator<String> ASCENDING_ORDER = new AscendingOrder();

	/**
	 * Implementation of ascending ordering of department structure.
	 * At first step every level of two sequences of departments is sorted in
	 * natural order. After that departments are sorted by length.
	 */
	private static class AscendingOrder implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			String[] deps1 = o1.split("\\\\");
			String[] deps2 = o2.split("\\\\");
			// Minimum department length.
			int dlm = Math.min(deps1.length, deps2.length);
			int res = 0;
			for (int i = 0; i < dlm; i++) {
				// 1. Сравниваем департаменты, находящиеся на одном уровне.
				if (!deps1[i].equals(deps2[i])) {
					res = deps1[i].compareTo(deps2[i]);
					break;
				} else {
					// 2. Сравниваем по длине массивы: с меньшей длиной - выше.
					res = deps1.length - deps2.length;
				}
			}

			return res;
		}
	}
}
