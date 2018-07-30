package ru.job4j.waitnotify;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Тестирование простой блокирующей очереди.
 */
public class SimpleBlockingQueueTest {
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
		Thread consumer = new Thread(() -> {
			for (int i = 0; i < 15; i++) {
				System.out.println("Получил: " + q.poll());
			}
		}, "Consumer");
		producer.start();
		consumer.start();
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}