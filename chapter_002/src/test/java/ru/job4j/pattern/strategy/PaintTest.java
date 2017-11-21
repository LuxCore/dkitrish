package ru.job4j.pattern.strategy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

/**
 * Test of drawing polyshape.
 */
public class PaintTest {
	/**
	 * Predefined square to test drawing of square.
	 */
	private String square;

	/**
	 * Predefined triangle to test drawing of triangle.
	 */
	private String triangle;

	/**
	 * Initializing of shapes.
	 */
	@Before
	public void init() {
		// New line symbol.
		String nl = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();

		this.square = sb
				.append("----------------").append(nl)
				.append("|              |").append(nl)
				.append("|              |").append(nl)
				.append("|              |").append(nl)
				.append("|              |").append(nl)
				.append("----------------").append(nl)
				.toString();

		this.triangle = sb
			.append("      ^      ").append(nl)
			.append("     / \\     ").append(nl)
			.append("    /   \\    ").append(nl)
			.append("   /     \\   ").append(nl)
			.append("  /       \\  ").append(nl)
			.append(" /         \\ ").append(nl)
			.append("-------------").append(nl)
			.toString();
	}

	/**
	 * let`s draw some shapes ;-).
	 */
	@Test
	public void testDrawShape() {
		// Remember standard console output stream.
		PrintStream stdout = System.out;
		// Buffer to storage incoming data.
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// Replace the statndard output with buffer.
		System.setOut(new PrintStream(out));

		// Perform drawing square into console.
		Square square = new Square();
		square.setSquareSize(4);
		new Paint().draw(square);
		assertThat(new String(out.toByteArray()), is(this.square));

		// Perform drawing triangle into console.
		Triangle triangle = new Triangle();
		triangle.setHeight(7);
		new Paint().draw(triangle);
		assertThat(new String(out.toByteArray()), is(this.triangle));

		// Return standard output to its lawful place.
		System.setOut(stdout);
	}
}
