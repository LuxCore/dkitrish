package ru.job4j.bomberman;

import org.junit.Test;

/**
 * Тестирование игры.
 */
public class GameTest {
	/**
	 * Тестовый запуск игры.
	 *
	 * @throws InterruptedException Выбрасывается при прерывании потока.
	 */
	@Test
	public void start() throws InterruptedException {
		Game game = new Game();
		game.start();
	}
}