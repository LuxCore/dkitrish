package ru.job4j.pattern.strategy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

/**
 * Test drawing triangle.
 */
public class TriangleTest {
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
			.append("     ^     ").append(nl)
			.append("    / \\    ").append(nl)
			.append("   /   \\   ").append(nl)
			.append("  /     \\  ").append(nl)
			.append(" /       \\ ").append(nl)
			.append("-----------").append(nl)
			.toString();
	}

	/**
	 * Returns the triangle shape.
	 */
	@Test
	public void testDrawTriangle() {
		Triangle triangle = new Triangle();
		triangle.setHeight(6);
		String result = triangle.picture();

		assertThat(result, is(this.expected));
	}
}
