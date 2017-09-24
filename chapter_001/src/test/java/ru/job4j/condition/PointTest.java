package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *	Test point.
 *
 *	@author Denis.Kitrish
 *	@since 24.09.2017
 *	@version 1.0
 */
public class PointTest {

	/**
	 *	Method tests whether a point belongs to a line.
	 */
	@Test
	public void testBelongingPointToALine() {
		Point point = new Point(23, 69);
		boolean result = point.isPointBelongToALine(3, 0);
		assertThat(result, is(true));
	}
}