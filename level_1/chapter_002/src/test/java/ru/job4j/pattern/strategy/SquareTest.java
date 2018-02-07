package ru.job4j.pattern.strategy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

/**
 * Test drawing square.
 */
public class SquareTest {
	/**
	 * Expected result.
	 */
	private String expected;

	/**
	 * Initializes test data.
	 */
	@Before
	public void init() {
		// New line symbol.
		String nl = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();

		this.expected = sb
				.append("------------").append(nl)
				.append("|          |").append(nl)
				.append("|          |").append(nl)
				.append("|          |").append(nl)
				.append("------------").append(nl)
				.toString();
	}

	/**
	 * Returns the square shape.
	 */
	@Test
	public void testDrawSquare() {
		Square square = new Square();
		square.setSquareSize(3);
		String result = square.picture();

		assertThat(result, is(this.expected));
	}
}
