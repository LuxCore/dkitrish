package ru.job4j.chess.figure;

import ru.job4j.chess.core.Cell;
import ru.job4j.chess.core.Figure;
import ru.job4j.chess.core.ImpossibleMoveException;

/**
 * Figure bishop.
 */
public class Bishop extends Figure {
	/**
	 * Constructs bishop figure.
	 *
	 * @param position Initial position of bishop figure.
	 */
	public Bishop(Cell position) {
		super(position);
	}

	/**
	 * Checks if the figure can move by specified path.
	 *
	 * @param srcCell Initial cell from which figure begins movement.
	 * @param destCell Destination cell that figure must take
	 *
	 * @throws ImpossibleMoveException Say that figure can not go by the path.
	 *
	 * @return Path figure must go.
	 */
	@Override
	public Cell[] way(Cell srcCell, Cell destCell) throws ImpossibleMoveException {
		// Figure can not go outside the board. Moreover bishop can only move
		// diagonally.
		if (destCell.getX() > 7 || destCell.getY() > 7
				|| destCell.getX() < 1 || destCell.getY() < 1
				|| srcCell.getX() - destCell.getX() != srcCell.getY() - destCell.getY()) {
			throw new ImpossibleMoveException("Невозможно переместить слона на заданную клетку.");
		}

		int cells = destCell.getX() - srcCell.getX();
		Cell[] wayCells = new Cell[Math.abs(cells)];
		if (cells < 0) {
			for (int i = 0; i < cells; i++) {
				wayCells[i] = new Cell(srcCell.getX() - i - 1, srcCell.getY() - i - 1);
			}
		} else if (cells > 0) {
			for (int i = 0; i < cells; i++) {
				wayCells[i] = new Cell(srcCell.getX() + i + 1, srcCell.getY() + i + 1);
			}
		}

		return wayCells;
	}

	/**
	 * Sets a new location of figure.
	 *
	 * @param destination Place where figure must get.
	 *
	 * @return Figure that have gone in destination cell.
	 */
	@Override
	public Figure copy(Cell destination) {
		return new Bishop(destination);
	}
}
