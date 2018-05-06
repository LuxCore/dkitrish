package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование простого множества, основанного на связном списке.
 */
public class SimpleLinkedSetTest {
	/**
	 * Проверка обычного добавления элементов.
	 */
	@Test
	public void testAdd() {
		SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
		set.add(3);
		set.add(1);
		set.add(3);
		set.add(1);
		set.add(1);
		set.add(1);
		set.add(2);
		set.add(2);
		set.add(3);
		set.add(69);
		set.add(777);
		set.add(666);
		set.add(3);
		set.add(777);
		set.add(69);
		int[] actual = new int[6];
		int i = 0;
		for (Integer e : set) {
			actual[i++] = e;
		}
		assertThat(actual, is(new int[]{3, 1, 2, 69, 777, 666}));
		assertThat(set.toString(), is("[3, 1, 2, 69, 777, 666]"));
	}

	/**
	 * Проверка обычного добавления элементов.
	 */
	@Test
	public void testAddNull() {
		SimpleLinkedSet<String> set = new SimpleLinkedSet<>();
		set.add(null);
		set.add("69");
		set.add("null");
		set.add("null");
		set.add("69");
		set.add(null);
		assertThat(set.toString(), is("[null, 69, null]"));
	}
}
