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
	 *	Tests max function.
	 */
	@Test
	public void testMax() {
		Max max = new Max();
		int result = max.max(54, 69);
		assertThat(result, is(69));
	}
}