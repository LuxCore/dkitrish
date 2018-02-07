package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 *	Test Triangle based on Point.
 *
 *	@author Denis.Kitrish
 *	@since 25.09.2017
 *	@version 1.0
 */
public class TriangleTest {

	/** Point a. */
	private Point a = new Point(1, 2);

	/** Point b. */
	private Point b = new Point(5, 5);

	/** Point c. */
	private Point c = new Point(7, 4);

	/** Triangle object. */
	private Triangle triangle = new Triangle(a, b, c);

	/**
	 *	Test calculating triangle distance.
	 */
	@Test
	public void testCalcDistanceBetweenTwoPoints() {
		double result = triangle.distance(a, c);
		double expected = 6.3;

		assertThat(result, is(closeTo(expected, 0.03)));
	}

	/**
	 *	Test calculating triangle perimeter.
	 */
	@Test
	public void testCalcPerimeter() {
		double ab = triangle.distance(a, b);
		double ac = triangle.distance(a, c);
		double bc = triangle.distance(b, c);

		double result = triangle.perimeter(ab, ac, bc);
		double expected = 13.5;

		assertThat(result, is(closeTo(expected, 0.07)));
	}

	/**
	 *	Test calculating triangle area.
	 */
	@Test
	public void testCalcArea() {
		double expected = 5.0;

		assertThat(triangle.area(), is(closeTo(expected, 0.01)));
	}
}