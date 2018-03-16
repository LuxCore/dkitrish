package ru.job4j.chess;

/**
 * Шахматная фигура слон. === Chess piece.
 */
public class Bishop extends Piece {
	/**
	 * Конструктор слона с занимаемой позицией на доске.
	 *
	 * @param position Клетка на доске, которую занимает слон. === Square on chessboard
	 *                 that is occupied by bishop.
	 */
	public Bishop(Square position) {
		super(position);
	}

	/**
	 * Метод проверяет возможность хода из занимаемой фигурой клетки в назначенную
	 * клетку. === Method checks possibility of movement from init square to
	 * destination square.
	 *
	 * @param source Начальная клетка, движение с которой необходимо проверить. ===
	 *               Initial square of piece move.
	 * @param dest   Конечная клетка, на которую должна попасть фигура. === End square.
	 * @return Корректный путь, который должен состоять из клеток, по которым фигура
	 * должна пройти. === Correct path consists of correct squares.
	 * @throws ImpossibleMoveException Выбрасывается в случае, когда ход невозможен. === Thrown when
	 *                                 move is impossible.
	 */
	@Override
	public Square[] way(Square source, Square dest) throws ImpossibleMoveException {
		this.checkForMatchSourceAndDest(source, dest);
		this.isDestSquareOutOfBoard(dest);


		int deltaX = dest.getX() - source.getX();
		int deltaY = dest.getY() - source.getY();
		int absDeltaX = Math.abs(deltaX);
		int absDeltaY = Math.abs(deltaY);

		if (absDeltaX != absDeltaY) {
			throw new ImpossibleMoveException("Ход невозможен, т.к. он не соответствует правилам.");
		}

		Square[] path = new Square[absDeltaX];
		int incX = Integer.compare(deltaX, 0);
		int incY = Integer.compare(deltaY, 0);
		for (int i = 0, x = source.getX(), y = source.getY(); i < absDeltaX; i++) {
			x += incX;
			y += incY;
			path[i] = new Square(x, y);
		}

		return path;
	}

	@Override
	public Piece copy(Square dest) {
		return new Bishop(dest);
	}
}
