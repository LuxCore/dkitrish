package ru.job4j.calculator;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *	Arithmetic tests.
 *
 *	@author Denis.Kitrish
 *	@since 23.09.2017
 *	@version 1.0
 */
public class CalculatorTest {
	/**
	 *	Calc object.
	 */
	private final Calculator calc = new Calculator();

	/**
	 *	Tests sum of two doubles.
	 */
	@Test
	public void sumOfTwoDoubles() {
		calc.add(60, 9);
		double result = calc.getResult();
		double expected = 69D;
		assertThat(result, is(expected));
	}

	/**
	 *	Tests difference of two doubles.
	 */
	@Test
	public void subtractFromOneDoubleAnother() {
		calc.subtract(78, 9);
		double result = calc.getResult();
		double expected = 69D;
		assertThat(result, is(expected));
	}

	/**
	 *	Tests quotient of two doubles.
	 */
	@Test
	public void divOneDoubleByAnother() {
		calc.div(897, 13);
		double result = calc.getResult();
		double expected = 69D;
		assertThat(result, is(expected));
	}

	/**
	 *	Tests product of two doubles.
	 */
	@Test
	public void multiplyOfTwoDoubles() {
		calc.multiply(23, 3);
		double result = calc.getResult();
		double expected = 69D;
		assertThat(result, is(expected));
	}
}