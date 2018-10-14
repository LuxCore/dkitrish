package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Генератор шагов игрока.
 * Шаги генерируются в одном из 8 направлений.
 */
public class RandomStep implements Runnable {
	/**
	 * Очередь ходов.
	 */
	private LinkedBlockingQueue<Cell> input;

	/**
	 * Генератор случайных значений.
	 */
	private Random random = new Random();

	/**
	 * Конструктор генератора случайных ходов.
	 *
	 * @param input Очередь ходов.
	 */
	public RandomStep(LinkedBlockingQueue<Cell> input) {
		this.input = input;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Cell newCell = new Cell(
						this.random.nextInt(3) - 1,
						this.random.nextInt(3) - 1
				);
				this.input.put(newCell);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
