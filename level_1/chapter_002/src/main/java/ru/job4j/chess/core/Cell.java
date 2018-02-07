package ru.job4j.chess.core;

/**
 * Cell of playing board.
 */
public class Cell {
	/**
	 * Location of cell on x-axis.
	 */
	private int x;

	/**
	 * Location of cell on y-axis.
	 */
	private int y;

	/**
	 * Constructs a cell of the board.
	 *
	 * @param x Location of cell on x-axis.
	 * @param y Location of cell on y-axis.
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets location on x-axis.
	 *
	 * @param x Location of cell on x-axis.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets location of cell on x-axis.
	 *
	 * @return Location of cell on x-axis.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Sets location on y-axis.
	 *
	 * @param y Location of cell on y-axis.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets location of cell on y-axis.
	 *
	 * @return Location of cell on y-axis.
	 */
	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "Cell {x = " + x + "; y = " + y + "}";
	}
}
