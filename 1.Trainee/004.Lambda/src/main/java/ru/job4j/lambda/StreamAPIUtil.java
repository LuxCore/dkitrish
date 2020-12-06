package ru.job4j.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Вспомогательные методы для работы с Stream API.
 * В данном классе собраны несколько задач из курсы.
 */
public class StreamAPIUtil {
	/**
	 * Преобразование списка студентов в карту.
	 * @param students Список студентов.
	 * @return карта студентов.
	 */
	public Map<String, List<Student>> listToMap(List<Student> students) {
		return students.stream().collect(Collectors.groupingBy(Student::getSurname));
	}

	/**
	 * Преобразование матрицы (двумерный массив) в список.
	 * @param matrix Матрица.
	 * @return Список значений из матрицы.
	 */
	public List<Integer> matrixToList(Integer[][] matrix) {
		return Arrays.stream(matrix)
				.flatMap(Arrays::stream)
				.collect(Collectors.toList());
	}
}
