package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Тест итератора итераторов.
 */
public class IteratorOfIteratorsTest {
	/**
	 * Итератор.
	 */
	private Iterator<Integer> iterator;

	/**
	 * Предварительная настройка данных.
	 */
	@Before
	public void setUp() {
		Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
		Iterator<Integer> it2 = Arrays.asList(4, 5, 6).iterator();
		Iterator<Integer> it3 = Arrays.asList(7, 8, 9).iterator();
		Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
		IteratorOfIterators itr = new IteratorOfIterators();
		iterator = itr.convert(its);
	}

	/**
	 * Последовательные вызовы hasNext() + next().
	 */
	@Test
	public void testOfHasNextAndNextToEndOfIterator() {
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(1));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(2));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(3));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(4));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(5));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(6));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(7));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(8));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(9));
		assertThat(iterator.hasNext(), is(false));
	}

	/**
	 * Последовательные вызовы next().
	 */
	@Test
	public void testSequenceOfNextMethod() {
		assertThat(iterator.next(), is(1));
		assertThat(iterator.next(), is(2));
		assertThat(iterator.next(), is(3));
		assertThat(iterator.next(), is(4));
		assertThat(iterator.next(), is(5));
		assertThat(iterator.next(), is(6));
		assertThat(iterator.next(), is(7));
		assertThat(iterator.next(), is(8));
		assertThat(iterator.next(), is(9));
	}

	/**
	 * Несколько вызовов подряд hasNext() никак не должны повлиять на изменение
	 * курсора внутри итератора.
	 */
	@Test
	public void testHasNextDoesNotAffectChanges() {
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(1));
		assertThat(iterator.next(), is(2));
		assertThat(iterator.next(), is(3));
		assertThat(iterator.next(), is(4));
		assertThat(iterator.next(), is(5));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(6));
		assertThat(iterator.next(), is(7));
		assertThat(iterator.next(), is(8));
		assertThat(iterator.next(), is(9));
	}

	/**
	 * Если все внутренние итераторы пустые, то hasNext() обязан вернуть false.
	 */
	@Test
	public void hasNextShouldReturnFalseIfIteratorIsEmpty() {
		Iterator<Integer> it1 = (new ArrayList<Integer>()).iterator();
		Iterator<Integer> it2 = (new ArrayList<Integer>()).iterator();
		Iterator<Integer> it3 = (new ArrayList<Integer>()).iterator();
		Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
		IteratorOfIterators itr = new IteratorOfIterators();
		iterator = itr.convert(its);
		assertThat(iterator.hasNext(), is(false));
	}

	/**
	 * Проверка исключения, когда следующего элемента в итераторе нет.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testNoSuchElementException() {
		Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
		Iterator<Iterator<Integer>> its = Arrays.asList(it1).iterator();
		IteratorOfIterators itr = new IteratorOfIterators();
		iterator = itr.convert(its);
		assertThat(iterator.next(), is(1));
		assertThat(iterator.next(), is(2));
		assertThat(iterator.next(), is(3));
		iterator.next();
	}
}
