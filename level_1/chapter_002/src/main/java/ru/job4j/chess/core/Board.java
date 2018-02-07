package ru.job4j.chess.core;

/**
 * Сhess board.
 */
public class Board {
	/**
	 * Cells of board with szie 8 x 8.
	 */
	private Figure[][] figures = new Figure[8][8];

	/**
	 * Move on the board.
	 *
	 * @param srcCell Initial cell of movement.
	 * @param destCell Destination cell of movement.
	 *
	 * @throws ImpossibleMoveException Say that figure can not move that way.
	 * @throws OccupiedWayException Say that way is occupied by another figure.
	 * @throws FigureNotFoundException Say that no figure on selected cell.
	 *
	 * @return If figure can move in certain way.
	 */
	public boolean move(Cell srcCell, Cell destCell) throws FigureNotFoundException,
			OccupiedWayException, ImpossibleMoveException {
		if (this.figures[srcCell.getY()][srcCell.getX()] == null) {
			throw new FigureNotFoundException("В начальной клетке нет фигуры.");
		}

		if (this.figures[destCell.getY()][destCell.getX()] != null) {
			throw new OccupiedWayException("На пути фигуры стоит другая фигура.");
		}

		// if (!this.figures[srcCell.y][srcCell.x].way(srcCell, destCell)) {
		// 	throw new ImpossibleMoveException("Нет возможности передвинуть фигуру.");
		// }

		Figure figure = this.figures[srcCell.getY()][srcCell.getX()].copy(destCell);

		return this.figures[destCell.getY()][destCell.getX()] != null;
	}

	/**
	 * Locates given figure on board using figure's coordinates.
	 *
	 * @param figure Figure.
	 *
	 * @return Success of operation.
	 */
	public boolean locate(Figure figure) {
		Figure f = this.figures[figure.getPosition().getY()][figure.getPosition().getX()];
		if (f != null) {
			return false;
		}

		this.figures[figure.getPosition().getY()][figure.getPosition().getX()] =
				figure;

		return true;
	}
}
