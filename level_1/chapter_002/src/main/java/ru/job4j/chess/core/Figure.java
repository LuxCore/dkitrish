package ru.job4j.chess.core;

/**
 * Any figure on board.
 */
public abstract class Figure {
	/**
	 * Current position of figure.
	 */
	private final Cell position;

	/**
	 * Future behavior of concrete figure.
	 */
	// private FigureMovement figureMovement;

	/**
	 * Constructs a figure.
	 *
	 * @param position Initial position in the beginning of the game.
	 */
	public Figure(Cell position) {
		this.position = position;
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
	public abstract Cell[] way(Cell srcCell, Cell destCell) throws ImpossibleMoveException;

	/**
	 * Sets a new location of figure.
	 *
	 * @param destination Place where figure must get.
	 *
	 * @return Figure that have gone in destination cell.
	 */
	public abstract Figure copy(Cell destination);

	/**
	 * Sets behavior of figure.
	 *
	 * @param figureMovement Behavior of figure.
	 */
	/*public void setFigureMovement(FigureMovement figureMovement) {
		this.figureMovement = figureMovement;
	}*/

	/**
	 * Gets position of figure.
	 *
	 * @return Current position of a figure.
	 */
	public Cell getPosition() {
		return position;
	}
}
