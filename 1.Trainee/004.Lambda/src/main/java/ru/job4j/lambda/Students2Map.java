package ru.job4j.lambda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Преобразование списка студентов в карту студентов. Однофамильцы группируются
 * в одном списке.
 */
public class Students2Map {
	/**
	 * Преобразование списка студентов в карту.
	 * @param students Список студентов.
	 * @return карта студентов.
	 */
	public Map<String, List<Student>> convert(List<Student> students) {
		return students.stream().collect(Collectors.groupingBy(Student::getSurname));
	}
}
