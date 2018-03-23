package ru.job4j.loop;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *	Test counting nums in different ways.
 *
 *	@author Denis.Kitrish
 *	@since 01.10.2017
 *	@version 1.0
 */
public class CounterTest {

	/**
	 *	Test counting sum of even numbers.
	 */
	@Test
	public void testSumEvenNums() {
		Counter c = new Counter();
		int result = c.sumEvenNums(0, 12);

		assertThat(result, is(42));
	}
}