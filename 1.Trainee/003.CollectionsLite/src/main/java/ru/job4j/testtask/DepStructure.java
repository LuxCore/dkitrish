package ru.job4j.testtask;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Класс для работы со структурой департаментов.
 *
 * @author Denis.Kitrish
 */
public class DepStructure {
	/**
	 * Компаратор для сортировки по убыванию.
	 */
	public static final Comparator<String> DESCENDING_ORDER = new DescendingOrder();

	/**
	 * Компаратор для сортировки по возрастанию.
	 */
	public static final Comparator<String> ASCENDING_ORDER = new AscendingOrder();

	/**
	 * Корректировка исходной организационной структуры.
	 * <p>
	 * Вычленение высших уровней подразделений, если таковые в структуре
	 * отсутствуют. В результирующем массиве содержатся все ветки структуры.
	 *
	 * @param orgStructure Исходная организационная структура.
	 * @return Корректная организационная структура.
	 */
	public static String[] correct(String[] orgStructure) {
		Set<String> set = new LinkedHashSet<>();

		for (String s : orgStructure) {
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '\\') {
					set.add(s.substring(0, i));
				} else if (i == s.length() - 1) {
					set.add(s.substring(0, i + 1));
				}
			}
		}

		return set.toArray(new String[set.size()]);
	}

	/**
	 * Компаратор для сортировки по убыванию.
	 */
	private static class DescendingOrder implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			int result = 0;
			int minLen = Math.min(o1.length(), o2.length());

			// 1. Проходим по наименьшей длине из двух строк до первых различных
			// символов.
			for (int i = 0; i < minLen; i++) {
				if (o1.charAt(i) != o2.charAt(i)) {
					if (o1.charAt(i) != '\\' && o2.charAt(i) != '\\') {
						result = Character.compare(o2.charAt(i), o1.charAt(i));
					} else {
						result = o2.charAt(i) == '\\' ? -1 : 1;
					}
					break;
				}
			}
			// 2. Если все соответствующие символы равны при проходе по наименьшей
			// длине, то производим сравнение по длине.
			if (result == 0) {
				if (o1.length() > o2.length()) {
					result = o1.charAt(minLen) == '\\' ? 1 : -1;
				} else {
					result = o2.charAt(minLen) == '\\' ? -1 : 1;
				}
			}

			return result;
		}
	}

	/**
	 * Компаратор для сортировки по возрастанию.
	 */
	private static class AscendingOrder implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			int result = 0;
			int minLen = Math.min(o1.length(), o2.length());

			// 1. Проходим по наименьшей длине из двух строк до первых различных
			// символов.
			for (int i = 0; i < minLen; i++) {
				if (o1.charAt(i) != o2.charAt(i)) {
					if (o1.charAt(i) != '\\' && o2.charAt(i) != '\\') {
						result = Character.compare(o1.charAt(i), o2.charAt(i));
					} else {
						result = o1.charAt(i) == '\\' ? -1 : 1;
					}
					break;
				}
			}
			// 2. Если все соответствующие символы равны при проходе по наименьшей
			// длине, то производим сравнение по длине.
			if (result == 0) {
				if (o1.length() < o2.length()) {
					result = -1;
				}
			}

			return result;
		}
	}
}
