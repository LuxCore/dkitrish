package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Searching a substring in string.
 *
 * @author Denis.Kitrish
 * @since 22.10.2017
 * @version 1.0
 */
public class SearchSubstringTest {
	/**
	 * Checks whether orogin string contains substring.
	 */
	@Test
	public void testIfStringContainsSubstring() {
		SearchSubstring s = new SearchSubstring();

		String str = "ПривПривет";
		String sub = "иве";

		boolean result = s.contains(str, sub);
		boolean expected = true;

		assertThat(result, is(expected));
	}
}