package ru.job4j.condition;

/**
 *	Triangle based on Point.
 *
 *	@author Denis.Kitrish
 *	@since 25.09.2017
 *	@version 1.0
 */
public class Triangle {

	/**
	 *	Top of a triangle.
	 */
	private Point a;

	/**
	 *	Top of a triangle.
	 */
	private Point b;

	/**
	 *	Top of a triangle.
	 */
	private Point c;

	/**
	 *	Make a triangle based on points.
	 *
	 *	@param a First top of a triangle.
	 *	@param b First top of a triangle.
	 *	@param c First top of a triangle.
	 */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 *	Calculating distance between points <code>left</code> and <code>right</code>.
	 *
	 *	Formula:
	 *	<code>AB = sqrt((xb - xa)^2 + (yb - ya)^2)</code>
	 *
	 *	@param left Left point.
	 *	@param right Right point.
	 *
	 *	@return Distance between left and right points.
	 */
	public double distance(Point left, Point right) {
		return Math.sqrt(Math.pow(right.getX() - left.getX(), 2) + Math.pow(right.getY() - left.getY(), 2));
	}

	/**
	 *	Calculating triangle perimeter.
	 *
	 *	Formula:
	 *	<code>P = ab + ac + bc</code>
	 *
	 *	@param ab Distance between points a &amp; b.
	 *	@param ac Distance between points a &amp; c.
	 *	@param bc Distance between points b &amp; c.
	 *
	 *	@return Perimeter.
	 */
	public double perimeter(double ab, double ac, double bc) {
		double result = -1;
		if (exists(ab, ac, bc)) {
			result = ab + ac + bc;
		}
		return result;
	}

	/**
	 *	Calculating triangle area.
	 *
	 *	Formula:
	 *	<code>S = sqrt(p *(p - ab) * (p - ac) * (p - bc))</code>
	 *
	 *	@return If triangle exists then methiod returns his else it returns -1.
	 */
	public double area() {
		double result = -1;
		double ab = distance(a, b);
		double ac = distance(a, c);
		double bc = distance(b, c);
		double p = perimeter(ab, ac, bc) / 2D;

		if (exists(ab, ac, bc)) {
			result = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
		}

		return result;
	}

	/**
	 *	Method checks whether it might to build a triangle with given sides' length.
	 *
	 *	@param ab Length form a to b.
	 *	@param ac Length form a to c.
	 *	@param bc Length form b to c.
	 *
	 *	@return Possibility of the existence of a triangle.
	 */
	private boolean exists(double ab, double ac, double bc) {
		if (ab <= 0 && bc <= 0 && ac <= 0) {
			return false;
		}

		if (ab > ac + bc) {
			return false;
		}

		if (ac > ab + bc) {
			return false;
		}

		if (bc > ab + bc) {
			return false;
		}

		return true;
	}
}
