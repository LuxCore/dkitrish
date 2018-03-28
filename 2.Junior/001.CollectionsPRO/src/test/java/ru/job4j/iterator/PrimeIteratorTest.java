package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование итератора простых чисел.
 */
public class PrimeIteratorTest {
	/**
	 * Итератор целых чисел.
	 */
	private Iterator<Integer> iterator;

	/**
	 * Настройка тестов.
	 */
	@Before
	public void setUp() {
		iterator = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9,
				10, 11, 12, 13, 14, 3571, 5, 6, 7, 8, 9, 10, 127});
	}

	/**
	 * Проверка последовательного вызова метода PrimeIterator#next().
	 */
	@Test
	public void testSequenceOfNextMethod() {
		assertThat(this.iterator.next(), is(2));
		assertThat(this.iterator.next(), is(3));
		assertThat(this.iterator.next(), is(5));
		assertThat(this.iterator.next(), is(7));
		assertThat(this.iterator.next(), is(11));
		assertThat(this.iterator.next(), is(13));
		assertThat(this.iterator.next(), is(3571));
	}

	/**
	 * Проверка правильного выброса исключения, когда следующее простое число
	 * не может быть найдено.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testNoSuchElementException() {
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(2));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(3));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(5));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(7));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(11));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(13));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(3571));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(5));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(7));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(127));
		assertThat(this.iterator.hasNext(), is(false));
		this.iterator.next();
	}

	/**
	 * Проверка последовательного вызова метода PrimeIterator#hasNext(), чтобы
	 * убедиться в том, что он никак не влияет на выполнение итератора.
	 */
	@Test
	public void testSequenceOfHasNextMethod() {
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(2));
		assertThat(this.iterator.next(), is(3));
		assertThat(this.iterator.next(), is(5));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(7));
		assertThat(this.iterator.next(), is(11));
		assertThat(this.iterator.next(), is(13));
		assertThat(this.iterator.next(), is(3571));
	}

	/**
	 *
	 */
	@Test
	public void testWhenPrimeNumbersAreAbsent() {
		this.iterator = new PrimeIterator(new int[]{4, 6});
		assertThat("should return false, cause there is no any prime number",
				this.iterator.hasNext(), is(false));
	}
}
