package ru.job4j.condition;

/**
 *	Point.
 *
 *	@author Denis.Kitrish
 *	@since 24.09.2017
 *	@version 1.0
 */
public class Point {

	/**
	 *	Coordinate on x-axis.
	 */
	private int x;

	/**
	 *	Coordinate on y-axis.
	 */
	private int y;

	/**
	 *	Initializes coordinates of a point.
	 *
	 *	@param x x-axis coordinate.
	 *	@param y y-axis coordinate.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 *	Checks whether a point belongs to a line.
	 *
	 *	@param k First number coefficient of line function.
	 *	@param b Second number coefficient of line function.
	 *
	 *	@return boolean
	 */
	public boolean isPointBelongToALine(int k, int b) {
		return y == k * x + b;
	}

	/**
	 *	Getter of x coordinate.
	 *
	 *	@return int
	 */
	public int getX() {
		return x;
	}

	/**
	 *	Getter of y coordinate.
	 *
	 *	@return int
	 */
	public int getY() {
		return y;
	}
}