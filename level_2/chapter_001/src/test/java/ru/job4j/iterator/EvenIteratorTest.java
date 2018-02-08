package ru.job4j.iterator;

// import java.util.Iterator;
import java.util.NoSuchElementException;

// import org.junit.Rule;
// import org.junit.rules.ExpectedException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование итератора по чётным числам в массиве.
 * =============================================================================
 * Testing of iterator by even integers in array.
 */
public class EvenIteratorTest {
	/**
	 * Тест метода {@code hasNext()} в случае наличия чётного целого.
	 * =========================================================================
	 * Testing of {@code hasNext()} when array has at least one even value.
	 */
	@Test
	public void testCheckWhenArrayHasEven() {
		int[] array = new int[] {1, 2};
		EvenIterator evnitr = new EvenIterator(array);
		boolean result = evnitr.hasNext();
		boolean expected = true;
		assertThat(result, is(expected));
	}

	/**
	 * Тест метода {@code hasNext()} в случае отсутствия чётных целых в массиве.
	 * =========================================================================
	 * Testing of {@code hasNext()} when array has no even values.
	 */
	@Test
	public void testCheckWhenArrayHasNoEven() {
		int[] array = new int[] {1};
		EvenIterator evnitr = new EvenIterator(array);
		boolean result = evnitr.hasNext();
		boolean expected = false;
		assertThat(result, is(expected));
	}

	/**
	 * Тестирование итератора по чётным числам в массиве, который содержит
	 * любые типы целых чисел.
	 * =========================================================================
	 * Testing of iterator going through an array of integers of different
	 * types.
	 */
	@Test
	public void testWalkThroughArrayWithDifferentTypesOfIntegers() {
		EvenIterator evnitr = new EvenIterator();
		int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		evnitr.setArray(array);
		evnitr.next();
		evnitr.next();
		evnitr.next();
		StringBuilder sb = new StringBuilder();
		String result = new String();
		for (int i : evnitr) {
			sb.append(", ").append(i);
		}

		if (!"".equals(sb.toString())) {
			result = sb.substring(2);
		}
		String expected = "8, 10";
		assertThat(result, is(expected));
	}

	/**
	 * Тестирование итератора целочисленного массива, который содержит только
	 * чётные целых чисел.
	 * =========================================================================
	 * Testing of iterator going through an array of only even integers.
	 */
	@Test
	public void testWalkThroughArrayContainingOnlyEvenIntegers() {
		int[] array = new int[] {2, 4, 6, 8, 10};
		EvenIterator evnitr = new EvenIterator(array);
		evnitr.next();
		evnitr.next();
		StringBuilder sb = new StringBuilder();
		String result = new String();
		for (int i : evnitr) {
			sb.append(", ").append(i);
		}

		if (!"".equals(sb.toString())) {
			result = sb.substring(2);
		}
		String expected = "6, 8, 10";
		assertThat(result, is(expected));
	}

	/**
	 * Тест массива с нулевой длиной.
	 * =========================================================================
	 * Test of a zero-length array.
	 */
	@Test
	public void testZeroLengthArray() {
		int[] array = new int[] {};
		EvenIterator evnitr = new EvenIterator(array);
		boolean result = evnitr.hasNext();
		boolean expected = false;
		assertThat(result, is(expected));
	}

	/**
	 * Тест исключительной ситуации, когда метод {@code next} не находит
	 * следующего элемента с чётным целым.
	 * =========================================================================
	 * Test of exceptional situation when method {@code next} does not find
	 * an element with even value.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testExceptionWhenNotFoundNextElement() {
		int[] array = new int[] {1, 2};
		EvenIterator evnitr = new EvenIterator(array);
		evnitr.next();
		evnitr.next();
	}
}
