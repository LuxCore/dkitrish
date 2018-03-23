package ru.job4j.loop;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *	Tests painting pyramid using "^" symbol.
 *
 *	@author Denis.Kitrish
 *	@since 01.10.2017
 *	@version 1.0
 */
public class PaintTest {

	/**
	 * Tests two-level pyramid.
	 */
	@Test
	public void testTwoLevelPyramid() {
		Paint paint = new Paint();
		String result = paint.pyramid(2);

		final String nl = System.getProperty("line.separator");
		final String expected = String.format(" ^ %s^^^", nl);

		assertThat(result, is(expected));
	}

	/**
	 * Tests five-level pyramid.
	 */
	@Test
	public void testFiveLevelPyramid() {
		Paint paint = new Paint();
		String result = paint.pyramid(5);

		final String nl = System.getProperty("line.separator");
		final String expected = String.format("    ^    %s   ^^^   %s  ^^^^^  %s ^^^^^^^ %s^^^^^^^^^", nl, nl, nl, nl);

		assertThat(result, is(expected));
	}
}