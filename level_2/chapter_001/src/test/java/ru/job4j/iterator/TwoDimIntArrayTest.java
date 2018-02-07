package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование перебираемого класса с двумерным массивом.
 */
public class TwoDimIntArrayTest {
	/**
	 * ExpectedException.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Тест перебора двумерного зубчатого массива.
	 */
	@Test
	public void testIterationOfJagged2DArray() {
		int[][] values = new int[][] {
			{1},
			{2, 3},
			{4, 5, 6},
			{7, 8, 9, 10},
			{11, 12, 13, 14, 15}
		};
		TwoDimIntArray twoDArr = new TwoDimIntArray(values);
		Iterator<Integer> itr = twoDArr.iterator();
		String result = new String();
		String expected = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15";

		while (itr.hasNext()) {
			result += ", " + itr.next();
		}
		result = result.substring(2);

		assertThat(result, is(expected));
	}

	/**
	 * Тест перебора двумерного равномерного массива.
	 */
	@Test
	public void testIterationOfUniform2DArray() {
		TwoDimIntArray twoDArr = new TwoDimIntArray();
		int[][] values = new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};
		twoDArr.setArray(values);
		Iterator<Integer> itr = twoDArr.iterator();
		String result = new String();
		String expected = "1, 2, 3, 4, 5, 6, 7, 8, 9";

		while (itr.hasNext()) {
			result += ", " + itr.next();
		}
		result = result.substring(2);

		assertThat(result, is(expected));
	}

	/**
	 * Тест перебора двумерного массива с одним элементом в каждом измерении.
	 */
	@Test
	public void testIterationOf2DArrayWith1Element() {
		TwoDimIntArray twoDArr = new TwoDimIntArray();
		int[][] values = new int[][] {{123}};
		twoDArr.setArray(values);
		Iterator<Integer> itr = twoDArr.iterator();
		int result = 0;
		int expected = 123;

		while (itr.hasNext()) {
			result = itr.next();
		}

		assertThat(result, is(expected));
	}

	/**
	 * Тест перебора двумерного зубчатого массива в упрощённом цикле {@code for}.
	 */
	@Test
	public void testIterationOfJagged2DArrayWithFORCycle() {
		int[][] values = new int[][] {
			{1},
			{2, 3},
			{4, 5, 6},
			{7, 8, 9, 10},
			{11, 12, 13, 14, 15}
		};
		TwoDimIntArray twoDArr = new TwoDimIntArray(values);
		Iterator<Integer> itr = twoDArr.iterator();
		String result = new String();
		String expected = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15";

		for (int i : twoDArr) {
			result += ", " + i;
		}
		result = result.substring(2);

		assertThat(result, is(expected));
	}

	/**
	 * Тест перебора двумерного массива нулевой длины.
	 */
	@Test
	public void testIterationOfZeroLength2DArray() {
		TwoDimIntArray twoDArr = new TwoDimIntArray();
		int[][] values = new int[][] {};
		twoDArr.setArray(values);
		Iterator<Integer> itr = twoDArr.iterator();
		boolean result = itr.hasNext();
		boolean expected = false;

		assertThat(result, is(expected));
	}

	/**
	 * Тест выбрасываемого исключения при переборе двумерного массива.
	 */
	@Test
	public void testExceptionWhenNotFoundNextElement() {
		thrown.expect(NoSuchElementException.class);
		int[][] values = new int[][] {{1}};
		TwoDimIntArray twoDArr = new TwoDimIntArray(values);
		Iterator<Integer> itr = twoDArr.iterator();
		itr.next();
		itr.next();
	}

	/**
	 * Тест выброса исключения, когда итератор пытается получить следующий из
	 * двумерного массива нулевой длины.
	 */
	@Test
	public void testExceptionWhenCallNextForZeroLength2DArray() {
		thrown.expect(NoSuchElementException.class);
		TwoDimIntArray twoDArr = new TwoDimIntArray();
		int[][] values = new int[0][];
		twoDArr.setArray(values);
		twoDArr.iterator().next();
	}
}
