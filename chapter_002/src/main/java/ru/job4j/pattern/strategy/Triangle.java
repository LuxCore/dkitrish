package ru.job4j.pattern.strategy;

/**
 * Triangle.
 */
public class Triangle implements Shape {
	/**
	 * Height of pyramid measured in number of lines of console.
	 */
	private int height;

	/**
	 * Returns the triangle shape.
	 */
	@Override
	public String picture() {
		// New line symbol.
		String nl = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();

		int width = (height * 2) - 1;

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				// Topic of pyramid.
				if (0 == row) {
					sb.append(col != height - 1 ? " " : "^");
				} else

				// Middle of pyramid.
				if (0 < row && height - 1 > row) {
					sb.append(col == height - 1 - row ? "/" : col == height - 1 + row ? "\\" : " ");
				} else

				// The lowest level (basement) of pyramid.
				if (row == height - 1) {
					sb.append("-");
				}

				// Type new line symbol at the end of level.
				if (col == width - 1) {
					sb.append(nl);
				}
			}
		}

		return sb.toString();
	}

	/**
	 * Sets a height of triangle.
	 * Height must be greater then 0.
	 *
	 * @param height Height of triangle.
	 */
	public void setHeight(int height) {
		this.height = height == 0 ? 1 : height;
	}
}
