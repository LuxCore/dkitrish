package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование динамического списка на базе массива.
 */
public class DynamicArrayListTest {
	/**
	 * Проверка исключения в случае, когда в конструктор передаётся отрицательная
	 * длина массива.
	 */
	@Test(expected = NegativeArraySizeException.class)
	public void testNegativeArraySizeConstructor() {
		new DynamicArrayList<Integer>(-10);
	}

	/**
	 * Тест добавления элементов с автоматическим расширением по достижении
	 * предела размера контейнера объектов.
	 */
	@Test
	public void testAdditionWithAutogrowth() {
		DynamicArrayList<Integer> dal = new DynamicArrayList<>();
		for (int i = 0; i < 30;) {
			dal.add(++i);
		}
		assertThat(dal.elementsCount(), is(30));
		assertThat(dal.size(), is(38));
	}

	/**
	 * Тест нормального итерирования по коллекции.
	 */
	@Test
	public void testIterator() {
		DynamicArrayList<Integer> dal = new DynamicArrayList<>(5);
		for (int i = 0; i < 6;) {
			dal.add(++i);
		}
		Iterator<Integer> it = dal.iterator();
		assertThat(it.next(), is(1));
		assertThat(it.hasNext(), is(true));
		assertThat(it.hasNext(), is(true));
		assertThat(it.next(), is(2));
		assertThat(it.next(), is(3));
		assertThat(it.hasNext(), is(true));
		assertThat(it.hasNext(), is(true));
		assertThat(it.next(), is(4));
		assertThat(it.next(), is(5));
		assertThat(it.next(), is(6));
	}

	/**
	 * Тест исключения во время работы итератора, когда в контейнере
	 * происходят изменения и вызывается метод hasNext().
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void testConcurrentModificationExceptionWhenHasNextInvoked() {
		DynamicArrayList<Integer> dal = new DynamicArrayList<>(5);
		for (int i = 0; i < 5;) {
			dal.add(++i);
		}
		Iterator<Integer> it = dal.iterator();
		assertThat(it.next(), is(1));
		assertThat(it.hasNext(), is(true));
		assertThat(it.hasNext(), is(true));
		assertThat(it.next(), is(2));
		dal.add(69);
		it.hasNext();
	}

	/**
	 * Тест исключения во время работы итератора, когда в контейнере
	 * происходят изменения и вызывается метод next().
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void testConcurrentModificationExceptionWhenNextInvoked() {
		DynamicArrayList<Integer> dal = new DynamicArrayList<>(5);
		for (int i = 0; i < 6;) {
			dal.add(++i);
		}
		Iterator<Integer> it = dal.iterator();
		assertThat(it.next(), is(1));
		assertThat(it.hasNext(), is(true));
		assertThat(it.hasNext(), is(true));
		assertThat(it.next(), is(2));
		dal.add(69);
		it.next();
	}

	/**
	 * Тест исключения во время работы итератора.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testIteratorNoSuchElementException() {
		DynamicArrayList<Integer> dal = new DynamicArrayList<>(5);
		for (int i = 0; i < 6;) {
			dal.add(++i);
		}
		Iterator<Integer> it = dal.iterator();
		assertThat(it.next(), is(1));
		assertThat(it.next(), is(2));
		assertThat(it.next(), is(3));
		assertThat(it.next(), is(4));
		assertThat(it.next(), is(5));
		assertThat(it.next(), is(6));
		it.next();
	}

	/**
	 * Тест исключения во время работы итератора.
	 */
	@Test
	public void testIteratorHasNextFalse() {
		DynamicArrayList<Integer> dal = new DynamicArrayList<>(5);
		for (int i = 0; i < 5;) {
			dal.add(++i);
		}
		Iterator<Integer> it = dal.iterator();
		assertThat(it.next(), is(1));
		assertThat(it.next(), is(2));
		assertThat(it.next(), is(3));
		assertThat(it.next(), is(4));
		assertThat(it.next(), is(5));
		assertThat(it.hasNext(), is(false));
	}

	/**
	 * Проверка получения объектов из контейнера.
	 */
	@Test
	public void testGettingElement() {
		DynamicArrayList<Character> dal = new DynamicArrayList<>(10);
		dal.add('A');
		dal.add('B');
		dal.add('c');
		dal.add('d');
		dal.add('E');
		assertThat(dal.get(0), is('A'));
		assertThat(dal.get(1), is('B'));
		assertThat(dal.get(2), is('c'));
		assertThat(dal.get(3), is('d'));
		assertThat(dal.get(4), is('E'));
	}

	/**
	 * Проверка исключения, когда вызывается метод get(int) с отрицательным
	 * значением индекса.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGettingElementWithNegativeIndex() {
		DynamicArrayList<Integer> dal = new DynamicArrayList<>();
		dal.add(69);
		dal.get(-69);
	}

	/**
	 * Проверка исключения, когда вызывается метод get(int) с превышающим
	 * максимальное значение индекса.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGettingElementWithIndexOverThenMaximum() {
		DynamicArrayList<Integer> dal = new DynamicArrayList<>();
		dal.add(69);
		dal.get(69);
	}
}
