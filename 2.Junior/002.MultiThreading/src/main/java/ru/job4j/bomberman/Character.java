package ru.job4j.bomberman;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Бомбермен.
 */
public class Character implements Runnable {
	/**
	 * Игровое поле.
	 */
	private final Board board;

	/**
	 * Текущая позиция игрока.
	 */
	private Cell position;

	/**
	 * Очередь ходов.
	 */
	private LinkedBlockingQueue<Cell> input;

	/**
	 * Конструктор игрока.
	 *
	 * @param board    Игровое поле.
	 * @param position Текущая позиция игрока.
	 * @param input    Очередь ходов.
	 */
	public Character(Board board, Cell position, LinkedBlockingQueue<Cell> input) {
		this.board = board;
		this.position = position;
		this.input = input;

	}

	@Override
	public void run() {
		try {
			this.board.lockCell(this.position);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Cell dest;
				do {
					Cell delta = this.input.take();
					dest = new Cell(
							this.position.getX() + delta.getX(),
							this.position.getY() + delta.getY()
					);
				} while (!this.board.move(this.position, dest));
				this.position = dest;
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
