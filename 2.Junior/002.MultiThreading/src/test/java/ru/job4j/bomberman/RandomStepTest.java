package ru.job4j.bomberman;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Тестирование генератора случайных ходов.
 */
public class RandomStepTest {
	/**
	 * Тест генератора случайных ходов.
	 *
	 * @throws InterruptedException Выброс при прерывании потока.
	 */
	@Test
	public void run() throws InterruptedException {
		LinkedBlockingQueue<Cell> queue = new LinkedBlockingQueue<>();
		Thread randomTest = new Thread(new RandomStep(queue));
		randomTest.start();
		Thread.sleep(5000);
		randomTest.interrupt();
		randomTest.join();
	}
}