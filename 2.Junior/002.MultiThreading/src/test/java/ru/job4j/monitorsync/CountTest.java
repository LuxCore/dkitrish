package ru.job4j.monitorsync;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Проверка запуска двух потоков для изменения общиего ресурса.
 */
public class CountTest {
	/**
	 * Поток, инкрементирующий счётчик.
	 */
	private class IncrementThread extends Thread {
		/**
		 * Счётчик.
		 */
		private final Count count;

		/**
		 * Конструктор, принимающий счётчик.
		 *
		 * @param count Счётчик.
		 */
		IncrementThread(Count count) {
			this.count = count;
		}

		@Override
		public void run() {
			count.increment();
		}
	}

	/**
	 * Тест, как изменяется общий ресурс, когда с ним работает два потока.
	 *
	 * @throws InterruptedException Исключение прерывания выполнения потока.
	 */
	@Test
	public void testWhenExecuteTwoThreads() throws InterruptedException {
		final Count count = new Count();
		Thread thread1 = new IncrementThread(count);
		Thread thread2 = new IncrementThread(count);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		assertEquals(count.getValue(), 2);
	}
}