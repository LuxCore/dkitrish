package ru.job4j.bomberman;

import java.util.Objects;

/**
 * Квадрат игрового поля.
 */
public class Cell {
	/**
	 * Координата нахождения игрока по оси x.
	 */
	private int x;

	/**
	 * Координата нахождения игрока по оси y.
	 */
	private int y;

	/**
	 * Конструктор игрового квадрата.
	 *
	 * @param x Координата нахождения игрока по оси x.
	 * @param y Координата нахождения игрока по оси y.
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Получение координаты по оси X.
	 *
	 * @return Координата по оси X.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Установка координаты по оси X.
	 *
	 * @param x Координата по оси X.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Получение координаты по оси Y.
	 *
	 * @return Координата по оси Y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Установка координаты по оси Y.
	 *
	 * @param y Координата по оси Y.
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Cell cell = (Cell) o;
		return x == cell.x && y == cell.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return String.format("Cell{x=%d, y=%d}", x, y);
	}
}
