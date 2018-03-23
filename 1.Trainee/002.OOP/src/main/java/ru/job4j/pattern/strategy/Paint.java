package ru.job4j.pattern.strategy;

/**
 * Auxilary class to drawing of shapes to test polymorphism.
 */
public class Paint {
	/**
	 * Draws a shape.
	 *
	 * @param shape Any shape that must be drawn.
	 */
	public void draw(Shape shape) {
		System.out.print(shape.picture());
	}
}
