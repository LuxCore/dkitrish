package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Проверка различных методов, работающих с Stream API.
 */
public class StreamAPIUtilTest {
	/**
	 * Тест преобразования списка в карту.
	 */
	@Test
	public void testConvert() {
		List<Student> students = List.of(
				new Student("Петров", 12),
				new Student("Сидоров", 12),
				new Student("Иванов", 12),
				new Student("Сидоров", 10),
				new Student("Иванов", 10),
				new Student("Васичкин", 12),
				new Student("Толстой", 20),
				new Student("Петров", 15),
				new Student("Толстой", 21)
		);

		Map<String, List<Student>> expected = Map.ofEntries(
				Map.entry("Петров", List.of(
						new Student("Петров", 12),
						new Student("Петров", 15))
				),
				Map.entry("Сидоров", List.of(
						new Student("Сидоров", 12),
						new Student("Сидоров", 10))
				),
				Map.entry("Иванов", List.of(
						new Student("Иванов", 12),
						new Student("Иванов", 10))
				),
				Map.entry("Васичкин", List.of(new Student("Васичкин", 12))),
				Map.entry("Толстой", List.of(
						new Student("Толстой", 20),
						new Student("Толстой", 21))
				)
		);
		assertEquals(expected, new StreamAPIUtil().listToMap(students));
	}

	/**
	 * Проверка преобразования матрицы в список.
	 */
	@Test
	public void testMatrixToList() {
		Integer[][] matrix = {
				{9, 8, 7, 6},
				{5, 4, 3},
				{2, 1}
		};
		List<Integer> expected = List.of(9, 8, 7, 6, 5, 4, 3, 2, 1);
		List<Integer> actual = new StreamAPIUtil().matrixToList(matrix);
		assertEquals(expected, actual);
	}

	/**
	 * Проверка фильтрации студентов, когда список:
	 * - содержит пустые элементы;
	 * - несортированный.
	 */
	@Test
	public void testLevelOf() {
		List<Student> actual = new ArrayList<>();
		actual.add(new Student("Пионеров", 119));
		actual.add(null);
		actual.add(new Student("Сидоров", 80));
		actual.add(new Student("Васичкин", 106));
		actual.add(null);
		actual.add(new Student("Обломов", 55));
		actual.add(new Student("Петров", 119));

		List<Student> expected = List.of(
				new Student("Петров", 119),
				new Student("Пионеров", 119),
				new Student("Васичкин", 106)
		);
		assertEquals(expected, new StreamAPIUtil().levelOf(actual, 96));
	}

	/**
	 * Проверка фильтрации студентов, когда список:
	 * - отсортированный.
	 */
	@Test
	public void testLevelOfWhenSorted() {
		List<Student> actual = new ArrayList<>();
		actual.add(new Student("Петров", 119));
		actual.add(new Student("Пионеров", 119));
		actual.add(new Student("Васичкин", 106));
		actual.add(new Student("Сидоров", 80));
		actual.add(new Student("Обломов", 55));

		List<Student> expected = List.of(
				new Student("Петров", 119),
				new Student("Пионеров", 119),
				new Student("Васичкин", 106),
				new Student("Сидоров", 80)
		);
		assertEquals(expected, new StreamAPIUtil().levelOf(actual, 78));
	}

	/**
	 * Проверка фильтрации студентов, когда список:
	 * - содержит только пустые элементы.
	 */
	@Test
	public void testLevelOfWhenOnlyNull() {
		List<Student> actual = new ArrayList<>();
		actual.add(null);

		List<Student> expected = List.of();
		assertEquals(expected, new StreamAPIUtil().levelOf(actual, 100));
	}
}