package ru.job4j.set;

import org.junit.Test;
import ru.job4j.list.Node;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Тестирование простого множества.
 */
public class SimpleArraySetTest {
	/**
	 * Тест добавления элементов.
	 */
	@Test
	public void testAdd() {
		SimpleArraySet<String> set = new SimpleArraySet<>();
		set.add("One");
		set.add("One");
		set.add("One");
		assertThat(set.elementsCount(), is(1));
		set.add("Two");
		set.add("Two");
		set.add("Two");
		set.add("One");
		set.add("One");
		assertThat(set.elementsCount(), is(2));
		set.add("Two");
		set.add("Two");
		set.add("Three");
		set.add("One");
		set.add("One");
		set.add("Three");
		assertThat(set.elementsCount(), is(3));
	}

	/**
	 * Проверка добавление элементов, которые не имеют перегруженных методов
	 * equals() и hashCode().
	 */
	@Test
	public void testAddWhenSetHasNoEqualsMethod() {
		SimpleArraySet<Node> set = new SimpleArraySet<>();
		set.add(new Node<>(1));
		set.add(new Node<>(1));
		set.add(new Node<>(1));
		assertThat(set.elementsCount(), is(3));
	}

	/**
	 * Проверка на добавление null элементов.
	 */
	@Test
	public void testAddWhenNewElementIsNull() {
		SimpleArraySet<String> set = new SimpleArraySet<>();
		set.add(null);
		set.add(null);
		set.add(null);
		assertThat(set.elementsCount(), is(0));
		set.add("null");
		assertThat(set.elementsCount(), is(1));
	}

	/**
	 * Проверка итерируемости простого множества.
	 */
	@Test
	public void testIterator() {
		SimpleArraySet<Integer> set = new SimpleArraySet<>();
		set.add(3);
		set.add(2);
		set.add(3);
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(1);
		set.add(3);
		int[] actual = new int[3];
		int i = 0;
		for (Integer e : set) {
			actual[i++] = e;
		}
		assertThat(actual, is(new int[]{3, 2, 1}));
	}
}
