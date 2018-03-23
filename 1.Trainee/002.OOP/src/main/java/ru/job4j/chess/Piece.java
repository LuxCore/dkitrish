package ru.job4j.chess;

/**
 * Шаблон шахматной фигуры.
 */
public abstract class Piece {
	/**
	 * Занимаемая фигурой клетка на доске.
	 */
	private final Square position;

	/**
	 * Конструктор фигуры.
	 *
	 * @param position Позиция на доске.
	 */
	Piece(Square position) {
		this.position = position;
	}

	/**
	 * Проверка корректности хода.
	 *
	 * @param source Начальная клетка хода.
	 * @param dest   Конечная клетка хода.
	 * @return Массив клеток, которые должна пройти фигура.
	 * @throws ImpossibleMoveException Ход невозможен.
	 */
	abstract Square[] way(Square source, Square dest) throws ImpossibleMoveException;

	/**
	 * Копирование/пересоздание фигуры.
	 * После проверки корректности хода фигуре не присваивается конечная клетка хода.
	 * Вместо этого создаётся новая фигура с конечной позицией.
	 *
	 * @param dest Конечная позиция хода фигуры.
	 * @return Фигура с конечной позицией хода.
	 */
	abstract Piece copy(Square dest);

	/**
	 * Проверка того, что конечная ячейка доски не выходит за пределы этой доски.
	 *
	 * @param dest Конечная позиция хода фигуры.
	 * @throws ImpossibleMoveException Ход невозможен.
	 */
	void isDestSquareOutOfBoard(Square dest) throws ImpossibleMoveException {
		if (dest.getX() < 1 || dest.getX() > 8 || dest.getY() < 1 || dest.getY() > 8) {
			throw new ImpossibleMoveException("Конечная позиция находится за пределами доски.");
		}
	}

	/**
	 * Проверяем, чтобы начальная и конечная позиции хода фигуры не совпадали.
	 *
	 * @param source Начальная клетка хода фигуры.
	 * @param dest   Конечная клетка хода фигуры.
	 */
	void checkForMatchSourceAndDest(Square source, Square dest) {
		if (source.equals(dest)) {
			throw new ImpossibleMoveException("Начальная и конечная клетки не должны совпадать.");
		}
	}

	/**
	 * Получатель позиции фигуры на доске.
	 *
	 * @return Занимаемая клетка фигурой.
	 */
	public Square getPosition() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

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
		Piece other = (Piece) obj;
		if (position == null) {
			return other.position == null;
		} else {
			return position.equals(other.position);
		}
	}
}
