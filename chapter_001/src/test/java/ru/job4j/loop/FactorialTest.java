package ru.job4j.loop;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *	Tests counting factrial.
 *
 *	@author Denis.Kitrish
 *	@since 01.10.2017
 *	@version 1.0
 */
public class FactorialTest {

	/**
	 *	Tests counting factorial with for loop.
	 */
	@Test
	public void testFactorial() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(5);

		assertThat(result, is(120));
	}

	/**
	 *	Tests counting factorial with for loop with 0.
	 */
	@Test
	public void testFactorialZero() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(0);

		assertThat(result, is(1));
	}
}