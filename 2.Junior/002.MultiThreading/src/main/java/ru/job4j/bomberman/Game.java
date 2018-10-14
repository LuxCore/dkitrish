package ru.job4j.bomberman;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Класс запуска игры Бомбермен.
 */
public class Game {
	/**
	 * Создание бомбермена с генератором шагов, доски и запуск всех потоков.
	 *
	 * @throws InterruptedException Выбрсывается при прерывании.
	 */
	public void start() throws InterruptedException {
		LinkedBlockingQueue<Cell> input = new LinkedBlockingQueue<>();
		RandomStep randomInput = new RandomStep(input);
		Board board = new Board(15, 9);
		Character bomber = new Character(board, new Cell(5, 5), input);
		Thread random = new Thread(randomInput);
		random.start();
		Thread bomberThread = new Thread(bomber);
		bomberThread.start();
		Thread.sleep(10000);
		bomberThread.interrupt();
		random.interrupt();
		bomberThread.join();
		random.join();
	}
}
