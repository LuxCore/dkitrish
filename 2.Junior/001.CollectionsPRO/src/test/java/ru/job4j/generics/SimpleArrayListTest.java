package ru.job4j.generics;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование простого списка на базе массива.
 */
public class SimpleArrayListTest {
	/**
	 * Тестируемое ожидаемое исключение.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Тест конструктора, которому передётся готовый массив объектов.
	 */
	@Test
	public void testConstructorFromExistingArray() {
		Integer[] intArray = new Integer[]{1, 3, 5, 7, 9, 10, 12, 14, 16};
		SimpleArrayList<Integer> list = new SimpleArrayList<>(intArray);
		assertThat(list.elementsCount(), is(9));
		assertThat(list.size(), is(9));
		list.add(18);
		assertThat(list.elementsCount(), is(10));
		assertThat(list.size(), is(14));
	}

	/**
	 *
	 */
	@Test(expected = NegativeArraySizeException.class)
	public void testConstructorNegativeArraySizeException() {
		new SimpleArrayList<>(Integer.class, -64);
//		new SimpleArrayList<>(-64);
	}

	/**
	 * Тест итератора по простому списку.
	 */
	@Test
	public void testIterate() {
		SimpleArrayList<Integer> list = new SimpleArrayList<>(Integer.class);
//		SimpleArrayList<Integer> list = new SimpleArrayList<>();
		for (int i = 1; i < 10; i++) {
			list.add(i);
		}
		Iterator<Integer> itr = list.iterator();
		assertThat(itr.hasNext(), is(true));
		assertThat(itr.next(), is(1));
		assertThat(itr.hasNext(), is(true));
		assertThat(itr.next(), is(2));
		assertThat(itr.hasNext(), is(true));
		assertThat(itr.next(), is(3));
	}

	/**
	 * Тест итератора на выброс исключения.
	 */
	@Test
	public void testIteratorNextException() {
		thrown.expect(NoSuchElementException.class);
		thrown.expectMessage(is("Элемент не найден."));
		SimpleArrayList<Integer> list = new SimpleArrayList<>(Integer.class, 2);
//		SimpleArrayList<Integer> list = new SimpleArrayList<>(2);
		list.add(1);
		list.add(2);
		Iterator<Integer> itr = list.iterator();
		assertThat(itr.next(), is(1));
		assertThat(itr.next(), is(2));
		assertThat(itr.next(), is(3));
	}

	/**
	 * Тест правильного расширения массива в случае добавления элемента, когда
	 * в массиве больше нет места.
	 */
	@Test
	public void testEnsureCapacity() {
		SimpleArrayList<String> list = new SimpleArrayList<>(String.class);
//		SimpleArrayList<String> list = new SimpleArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("" + (i + 1));
		}
		list.add("one");
		assertThat(list.size(), is(25));
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("five");
		assertThat(list.size(), is(38));
	}

	/**
	 * Тест добавления объектов.
	 */
	@Test
	public void testAdditionOfElements() {
		SimpleArrayList<String> list = new SimpleArrayList<>(String.class);
//		SimpleArrayList<String> list = new SimpleArrayList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("five");
		list.add("six");
		list.add("seven");
		String actual = list.toString();
		String expected = "[one, two, three, four, five, six, seven]";
		assertThat(actual, is(expected));
		list.add("five");
		list.add("six");
		list.add("seven");
		actual = list.toString();
		expected = "[one, two, three, four, five, six, seven, five, six, seven]";
		assertThat(actual, is(expected));
	}

	/**
	 * Тест замены существующего объекта.
	 */
	@Test
	public void testSetNewValueInsteadOfExisting() {
		SimpleArrayList<String> list = new SimpleArrayList<>(String.class);
//		SimpleArrayList<String> list = new SimpleArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("" + (i + 1));
		}
		list.set(5, "69");
		String actual = list.toString();
		String expected = "[1, 2, 3, 4, 5, 69, 7, 8, 9, 10]";
		assertThat(actual, is(expected));
	}

	/**
	 * Тест исключения выхода за рамки массива при установке нового значения.
	 */
	@Test
	public void testIndexOutBoundsWhenElementIsSet() {
		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage(is("Индекс находится за пределами массива."));
		SimpleArrayList<String> list = new SimpleArrayList<>(String.class);
//		SimpleArrayList<String> list = new SimpleArrayList<>();
		list.set(33, "index out of bounds");
	}

	/**
	 * Тест обычного удаления объектов.
	 */
	@Test
	public void testDeletionOfElements() {
		SimpleArrayList<Integer> list = new SimpleArrayList<>(Integer.class);
//		SimpleArrayList<Integer> list = new SimpleArrayList<>();
		for (Integer i = 0; i < 10; i++) {
			list.add(i + 1);
		}
		list.delete(0);
		assertThat("[2, 3, 4, 5, 6, 7, 8, 9, 10]", is(list.toString()));
		list.delete(8);
		assertThat("[2, 3, 4, 5, 6, 7, 8, 9]", is(list.toString()));
		list.delete(4);
		assertThat("[2, 3, 4, 5, 7, 8, 9]", is(list.toString()));
		Integer[] intArray = new Integer[]{2, 3, 4, 5, 7, 8, 9};
		SimpleArrayList<Integer> expected = new SimpleArrayList<>(intArray);
		assertThat(list, is(expected));
	}

	/**
	 * Тест исключения во время удаления объекта.
	 */
	@Test
	public void testIndexOutBoundsWhenElementIsDeleted() {
		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage(is("Индекс находится за пределами массива."));
		SimpleArrayList<Double> list = new SimpleArrayList<>(Double.class, 32);
//		SimpleArrayList<Double> list = new SimpleArrayList<>(32);
		for (int i = 0; i < 20; i++) {
			list.add(i / 3.5);
		}
		list.delete(33);
	}

	/**
	 * Тест обычного получения элемента.
	 */
	@Test
	public void testGetElement() {
		SimpleArrayList<Integer> list = new SimpleArrayList<>(Integer.class);
//		SimpleArrayList<Integer> list = new SimpleArrayList<>();
		for (Integer i = 0; i < 10; i++) {
			list.add(i + 1);
		}
		assertThat(list.get(5), is(6));
		assertThat(list.get(9), is(10));
	}

	/**
	 * Тест получения элемента с выбросом исключения.
	 * <p>
	 * 1) 16 * 3 / 2 + 1 = 25.
	 * 2) 25 * 3 / 2 + 1 = 38.
	 * 3) 38 * 3 / 2 + 1 = 58.
	 */
	@Test
	public void testIndexOutOfBoundsWhenGetElement() {
		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage(is("Индекс находится за пределами массива."));
		SimpleArrayList<Integer> list = new SimpleArrayList<>(Integer.class);
//		SimpleArrayList<Integer> list = new SimpleArrayList<>();
		for (Integer i = 0; i < 58; i++) {
			list.add(i + 1);
		}
		list.get(69);
	}
}
