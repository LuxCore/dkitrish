package ru.job4j.chess;

/**
 * Одна клетка доски.
 */
public class Square {
	/**
	 * Позиция клетки по оси X.
	 */
	private int x;

	/**
	 * Позиция клетки по оси Y.
	 */
	private int y;

	/**
	 * Конструктор клетки.
	 *
	 * @param x Координата по оси X.
	 * @param y Координата по оси Y.
	 */
	public Square(int x, int y) {
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
	 * Получение координаты по оси X.
	 *
	 * @return Координата по оси Y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Hash code.
	 *
	 * @return Hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * Equlas method.
	 *
	 * @param obj Other object.
	 * @return Равенство объектов.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Square other = (Square) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	/**
	 * Отображение информации об объектет в строковом представлении.
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}


}
