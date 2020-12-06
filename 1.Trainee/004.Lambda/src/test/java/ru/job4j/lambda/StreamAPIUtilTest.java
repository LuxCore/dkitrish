package ru.job4j.lambda;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Проверка различных методов, работающих с Stream API.
 */
public class StreamAPIUtilTest {
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
}