package ru.job4j.testtask;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование разбора структуры департаментов с приведением её к правильному
 * виду. А также сортировка оргструктуры по убыванию и возрастанию.
 */
public class DepStructureTest {
	/**
	 * Тест корректировки организационной структуры.
	 */
	@Test
	public void testCorrectionOfSourceOrgStructure() {
		String[] expected = {
				"A1",
				"A1\\A2",
				"A1\\A2\\A4",
				"A1\\A2\\A33",
				"A11",
				"A11\\A22",
				"A11\\A22\\A33",
				"MainDivision",
				"MainDivision\\OxygenShop",
				"A2",
				"A2\\A22",
				"A2\\A22\\A3"
		};
		String[] orgStructure = {
				"A1\\A2\\A4",
				"A1\\A2\\A33",
				"A11",
				"A11\\A22\\A33",
				"MainDivision\\OxygenShop",
				"A2\\A22\\A3"
		};
		String[] result = DepStructure.correct(orgStructure);
		assertThat(result, is(expected));
	}

	/**
	 * Проверка сортировки департаментов по убыванию.
	 */
	@Test
	public void testDescendingOrder() {
		String[] orgStructure;
		String[] expected;
		expected = new String[]{
				"K44",
				"K4",
				"K2",
				"K2\\SK1",
				"K2\\SK1\\SSK2",
				"K2\\SK1\\SSK1",
				"K111",
				"K111\\SK33",
				"K111\\SK1",
				"K11",
				"K1",
				"K1\\SK2",
				"K1\\SK1",
				"K1\\SK1\\SSK2",
				"K1\\SK1\\SSK1"
		};
		orgStructure = new String[]{
				"K111\\SK33",
				"K2\\SK1",
				"K1",
				"K2\\SK1\\SSK2",
				"K111",
				"K1\\SK2",
				"K1\\SK1\\SSK1",
				"K2",
				"K4",
				"K2\\SK1\\SSK1",
				"K44",
				"K1\\SK1",
				"K111\\SK1",
				"K1\\SK1\\SSK2",
				"K11"
		};
		Arrays.sort(orgStructure, DepStructure.DESCENDING_ORDER);
		assertThat(orgStructure, is(expected));
	}

	/**
	 * Проверка сортировки департаментов по возрастанию.
	 */
	@Test
	public void testAscendingOrder() {
		String[] orgStructure;
		String[] expected;
		expected = new String[]{
				"K1",
				"K1\\SK1",
				"K1\\SK1\\SSK1",
				"K1\\SK1\\SSK2",
				"K1\\SK2",
				"K11",
				"K111",
				"K111\\SK1",
				"K111\\SK33",
				"K2",
				"K2\\SK1",
				"K2\\SK1\\SSK1",
				"K2\\SK1\\SSK2",
				"K4",
				"K44"
		};
		orgStructure = new String[]{
				"K111\\SK33",
				"K2\\SK1",
				"K1",
				"K2\\SK1\\SSK2",
				"K111",
				"K1\\SK2",
				"K1\\SK1\\SSK1",
				"K2",
				"K4",
				"K2\\SK1\\SSK1",
				"K44",
				"K1\\SK1",
				"K111\\SK1",
				"K1\\SK1\\SSK2",
				"K11"
		};
		Arrays.sort(orgStructure, DepStructure.ASCENDING_ORDER);
		assertThat(orgStructure, is(expected));
	}
}
