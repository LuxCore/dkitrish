package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Тестирование простого стэка.
 */
public class SimpleStackTest {
	/**
	 * Проверка на добавление элемента в стэк и обратный выбор его.
	 * Принцип: первый зашёл - последний вышел.
	 */
	@Test
	public void testPushAndPollElement() {
		SimpleStack<String> queue = new SimpleStack<>();
		assertNull(queue.poll());

		queue.push("One");
		queue.push("Two");
		assertThat(queue.poll(), is("Two"));
		queue.push("Three");
		queue.push("Four");
		queue.push("Five");
		assertThat(queue.poll(), is("Five"));
		assertThat(queue.poll(), is("Four"));
		assertThat(queue.poll(), is("Three"));
		queue.push("Two");
		assertThat(queue.poll(), is("Two"));
		assertThat(queue.poll(), is("One"));
		assertNull(queue.poll());
	}
}
