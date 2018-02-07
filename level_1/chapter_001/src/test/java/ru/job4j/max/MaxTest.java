package ru.job4j.max;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *	Maximum test.
 *
 *	@author Denis.Kitrish
 *	@since 24.09.2017
 *	@version 1.0
 */
public class MaxTest {

	/**
	 *	Tests max(int first, int second) function.
	 */
	@Test
	public void testMax() {
		Max max = new Max();
		int result = max.max(54, 69);
		assertThat(result, is(69));
	}

	/**
	 *	Tests max(int first, int second, int third) function.
	 */
	@Test
	public void testMaxOfThreeInt() {
		Max max = new Max();
		int result = max.max(23, 46, 69);
		assertThat(result, is(69));
	}
}