package ru.job4j.waitnotify;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование простой блокирующей очереди.
 */
public class SimpleBlockingQueueTest {
	/**
	 * Тест на ожидание главным потоком окончания работы производителя
	 * и потребителя.
	 *
	 * @throws InterruptedException Исключение прерывания.
	 */
	@Test
	public void whenFetchAllThenGetIt() throws InterruptedException {
		final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
		final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
		Thread producer = new Thread(() -> IntStream.range(0, 10).forEach(queue::offer));
		producer.start();
		Thread consumer = new Thread(() -> {
			while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
				try {
					buffer.add(queue.poll());
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		});
		consumer.start();
		producer.join();
		consumer.interrupt();
		consumer.join();
		assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
	}

	/**
	 * Тест блокирующей очереди с помощью производтеля и потребителей.
	 */
	@Test
	@Ignore
	public void testProducerConsumer() {
		SimpleBlockingQueue<Integer> q = new SimpleBlockingQueue<>();
		Thread producer = new Thread(() -> {
			for (int i = 0; i < 15; i++) {
				q.offer(i);
				System.out.println("Отправил: " + i);
			}
		}, "Producer");
		producer.start();
		Thread consumer = new Thread(() -> {
			for (int i = 0; i < 15; i++) {
				try {
					System.out.println("Получил: " + q.poll());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Consumer");
		consumer.start();
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}