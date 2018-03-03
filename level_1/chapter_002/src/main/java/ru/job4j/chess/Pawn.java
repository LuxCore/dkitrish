package ru.job4j.chess;

/**
 * Пешка.
 */
public class Pawn extends Piece {
	/**
	 * Конструктор пешки, располагающий её на определённой клетке.
	 *
	 * @param position Позиция пешки на доске.
	 */
	public Pawn(Square position) {
		super(position);
	}

	/**
	 * Проверка корректности хода.
	 *
	 * @param source Начальная клетка хода.
	 * @param dest   Конечная клетка хода.
	 * @return Массив клеток, которые должна пройти фигура.
	 * @throws ImpossibleMoveException Ход невозможен.
	 */
	@Override
	public Square[] way(Square source, Square dest) throws ImpossibleMoveException {
		this.checkForMatchSourceAndDest(source, dest);
		this.isDestSquareOutOfBoard(dest);

		int deltaX = dest.getX() - source.getX();
		int deltaY = dest.getY() - source.getY();

		if (0 != deltaX) {
			throw new ImpossibleMoveException("Ход невозможен: пешка не может ходить в сторону.");
		}

		if (0 > deltaY) {
			throw new ImpossibleMoveException("Ход назад невозможен.");
		}

		if (source.getY() == 2 && deltaY > 2 || source.getY() > 2 && deltaY > 1) {
			throw new ImpossibleMoveException("Ход невозможен: превышено количество клеток в ходе.");
		}

		Square[] path = new Square[deltaY];

		for (int i = 0, x = source.getX(), y = source.getY(); i < deltaY; i++) {
			path[i] = new Square(x, ++y);
		}

		return path;
	}

	/**
	 * Копирование/перемещение пешки в конечную позицию хода.
	 *
	 * @param dest Конечная позиция хода фигуры.
	 * @return Пешка в новой позиции после совершения хода.
	 */
	@Override
	public Piece copy(Square dest) {
		return new Pawn(dest);
	}
}
