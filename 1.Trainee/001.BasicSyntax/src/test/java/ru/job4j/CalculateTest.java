package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *	Test class.
 *
 *	@author Denis.Kitrish (Denis.Kitrish@Yandex.ua)
 *	@since 21.09.2017
 *	@version 1.0
 */
public class CalculateTest {

	/**
	 *	Test method.
	 */
	@Test
	public void whenTakeNameThenThreeEchoPlusName() {
		String input = "Denis Kitrish";
		String expect = "Echo, echo, echo: Denis Kitrish";
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}