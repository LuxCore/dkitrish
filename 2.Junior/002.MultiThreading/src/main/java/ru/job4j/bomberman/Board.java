package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Игровое поле для бомберменов.
 */
public class Board {
	/**
	 * Игровое поле.
	 */
	private final ReentrantLock[][] board;

	/**
	 * Конструктор размерности игрового поля.
	 *
	 * @param xSize Размер по оси X.
	 * @param ySize Размер по оси Y.
	 */
	public Board(int xSize, int ySize) {
		this.board = new ReentrantLock[ySize][xSize];
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[y].length; x++) {
				this.board[y][x] = new ReentrantLock();
			}
		}
	}

	/**
	 * Передвижение игрока.
	 *
	 * @param source Начальная клетка движения.
	 * @param dest   Конечная клетка движения.
	 * @return результат операции.
	 * @throws InterruptedException Выбрасывается при прерывании потока.
	 */
	public boolean move(Cell source, Cell dest) throws InterruptedException {
		boolean result = false;
		if (dest.getX() >= 0 && dest.getX() < this.board[0].length
				&& dest.getY() >= 0 && dest.getY() < this.board.length
				&& this.lockCell(dest)) {
			this.board[source.getY()][source.getX()].unlock();
			result = true;
		}
		return result;
	}

	/**
	 * Попытка блокировки клетки игрового поля.
	 *
	 * @param cell Клетка назначения, на которую нужно перейти игроку.
	 * @return true в случае успешного занятия клетки назначения, иначе false.
	 * @throws InterruptedException Выбрасывается при прерывании потока.
	 */
	public boolean lockCell(Cell cell) throws InterruptedException {
		return this.board[cell.getY()][cell.getX()].tryLock()
				|| this.board[cell.getY()][cell.getX()].tryLock(500, TimeUnit.MILLISECONDS);
	}
}
