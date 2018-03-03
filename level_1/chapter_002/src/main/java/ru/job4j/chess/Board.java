package ru.job4j.chess;

/**
 * Chessboard.
 */
public class Board {
	/**
	 * Массив фигур.
	 */
	private Piece[] pieces = new Piece[32];

	/**
	 * Счётчик количества фигур на доске.
	 */
	private int countPieces;

	/**
	 * @param piece Шахматная фигура.
	 */
	public void add(Piece piece) {
		if (this.countPieces < pieces.length) {
			this.pieces[this.countPieces++] = piece;
		}
	}

	/**
	 * Ход фигурой на доске.
	 *
	 * @param source Начальная клетка движения фигуры.
	 * @param dest   Конечная клетка движения фигуры.
	 * @return Правда, если фигура переместилась, и ложь, если фигура не смогла
	 * переместиться.
	 * @throws PieceNotFoundException  Фигура на доске не найдена.
	 * @throws OccupiedWayException    Путь занят другой фигурой.
	 * @throws ImpossibleMoveException Ход невозможен.
	 */
	public boolean move(Square source, Square dest)
			throws PieceNotFoundException, OccupiedWayException, ImpossibleMoveException {
		Piece piece = null;
		int pieceNum = 0;
		for (int i = 0; i < this.pieces.length; i++) {
			if (this.pieces[i] != null) {
				if (source.equals(this.pieces[i].getPosition())) {
					piece = pieces[i];
					pieceNum = i;
				}
			} else {
				break;
			}
		}
		if (piece == null) {
			throw new PieceNotFoundException("Фигура на клетке [" + source + "] не найдена!");
		}

		Square[] path = piece.way(source, dest);
		checkForOccupiedWay(path);

		this.pieces[pieceNum] = piece.copy(dest);

		return true;
	}

	/**
	 * Метод проверяет занятость пути другой фигурой.
	 *
	 * @param path Путь, который должна пройти фигура.
	 * @throws OccupiedWayException Исключение о том, что путь занят другой фигурой.
	 */
	private void checkForOccupiedWay(Square[] path) throws OccupiedWayException {
		for (Square square : path) {
			for (Piece piece : this.pieces) {
				if (piece != null && piece.getPosition().equals(square)) {
					throw new OccupiedWayException("Нет возможности сделать ход, так как путь занят другой фигурой.");
				} else if (piece == null) {
					break;
				}
			}
		}
	}

	/**
	 * Получение массива имеющихся фигур на доске.
	 *
	 * @return Массив имеющихся фигур на доске.
	 */
	public Piece[] getPieces() {
		return pieces;
	}

	/**
	 * Возвращает количество фигур на доске.
	 *
	 * @return Количество фигур на доске.
	 */
	public int getCountPieces() {
		return countPieces;
	}
}
