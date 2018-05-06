package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Тест простой очереди.
 */
public class SimpleQueueTest {
	/**
	 * Проверка на добавление элемента в очередь и обратный выбор его.
	 * Принцип: первый зашёл - первый вышел.
	 */
	@Test
	public void testPushAndPollElement() {
		SimpleQueue<String> queue = new SimpleQueue<>();
		assertNull(queue.poll());
		queue.push("One");
		assertThat(queue.poll(), is("One"));
		queue.push("One");
		queue.push("Two");
		queue.push("Three");
		queue.push("Four");
		assertThat(queue.poll(), is("One"));
		queue.push("Five");
		assertThat(queue.poll(), is("Two"));
		assertThat(queue.poll(), is("Three"));
		assertThat(queue.poll(), is("Four"));
		assertThat(queue.poll(), is("Five"));
		assertNull(queue.poll());
	}
}
