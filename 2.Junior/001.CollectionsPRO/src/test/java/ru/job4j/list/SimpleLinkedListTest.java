package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест простого связного списка.
 */
public class SimpleLinkedListTest {
	/**
	 * Тест добавления элементов.
	 */
	@Test
	public void testAddValue() {
		SimpleLinkedList<String> sll = new SimpleLinkedList<>();
		for (Integer i = 1; i <= 10; i++) {
			sll.add(i.toString());
		}
		assertThat(sll.size(), is(10));
	}

	/**
	 * Тест получения элементов.
	 */
	@Test
	public void testGetValue() {
		SimpleLinkedList<String> sll = new SimpleLinkedList<>();
		for (Integer i = 1; i <= 10; i++) {
			sll.add(i.toString());
		}
		assertThat(sll.get(0), is("1"));
		assertThat(sll.get(4), is("5"));
		assertThat(sll.get(5), is("6"));
		assertThat(sll.get(7), is("8"));
		assertThat(sll.get(9), is("10"));

		sll.add("11");
		assertThat(sll.get(0), is("1"));
		assertThat(sll.get(4), is("5"));
		assertThat(sll.get(5), is("6"));
		assertThat(sll.get(7), is("8"));
		assertThat(sll.get(10), is("11"));
	}

	/**
	 * Тест исключения при получения элементов, когда индекс меньше 0.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsWhenGetValueByNegativeIndex() {
		SimpleLinkedList<String> sll = new SimpleLinkedList<>();
		sll.add("one");
		sll.get(-1);
	}

	/**
	 * Тест исключения при получения элементов, когда индекс првышает длину
	 * коллекции.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsWhenGetValueByIndexOverSize() {
		SimpleLinkedList<String> sll = new SimpleLinkedList<>();
		sll.add("one");
		sll.get(2);
	}

	/**
	 * Тест итератора в обычном режиме.
	 */
	@Test
	public void testIterator() {
		SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
		for (Integer i = 1; i <= 5; i++) {
			sll.add(i);
		}
		Iterator<Integer> it = sll.iterator();
		assertThat(it.hasNext(), is(true));
		assertThat(it.hasNext(), is(true));
		assertThat(it.next(), is(1));
		assertThat(it.next(), is(2));
		assertThat(it.next(), is(3));
		assertThat(it.next(), is(4));
		assertThat(it.hasNext(), is(true));
		assertThat(it.hasNext(), is(true));
		assertThat(it.next(), is(5));
	}

	/**
	 * Тест итератора в случае, когда происходит изменение коллекции во время
	 * итерирования.
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void testIteratorWhenConcurrentModification() {
		SimpleLinkedList<Double> sll = new SimpleLinkedList<>();
		sll.add(0.5);
		Iterator<Double> it = sll.iterator();
		sll.add(0.5);
		it.next();
	}

	/**
	 * Тест исключения на отсутствие следующего элемента итератора.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testIteratorNoSuchElement() {
		SimpleLinkedList<Double> sll = new SimpleLinkedList<>();
		sll.add(0.5);
		Iterator<Double> it = sll.iterator();
		assertThat(it.next(), is(0.5));
		it.next();
	}

	/**
	 * Тест toString().
	 */
	@Test
	public void testToString() {
		SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
		for (Integer i = 1; i <= 5; i++) {
			sll.add(i);
		}
		String expected = "[1, 2, 3, 4, 5]";
		assertThat(sll.toString(), is(expected));
	}

	/**
	 * Тест toString() с пустой коллекцией.
	 */
	@Test
	public void testEmptyListToString() {
		SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
		assertThat(sll.toString(), is("[]"));
	}
}
