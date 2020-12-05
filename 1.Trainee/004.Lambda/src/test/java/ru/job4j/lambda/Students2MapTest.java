package ru.job4j.lambda;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Проверка преобразования списка в карту.
 */
public class Students2MapTest {

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
		assertEquals(expected, new Students2Map().convert(students));
	}
}