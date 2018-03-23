package ru.job4j.pattern.strategy;

/**
 * Square.
 */
public class Square implements Shape {
	/**
	 * Square size.
	 * Must be greater then 0.
	 */
	private int squareSize;

	/**
	 * Returns the square shape.
	 *
	 * @return String Square in console.
	 */
	@Override
	public String picture() {
		// New line symbol.
		String nl = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();

		// Our square should look more or less pretty therefore it
		// should have minimum permissible height and width: 3 lines and 4 columns
		// accrodingly. Height can be increased by 1 line, and width - by 4 columns.
		int minHeight = 3;
		int minWidth = 4;
		int height = this.squareSize + minHeight - 1;
		int width = this.squareSize * minWidth;

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (0 == row || (height - 1) == row) {
					sb.append("-");
				} else {
					sb.append(0 == col || (width - 1) == col ? "|" : " ");
				}

				if (col == width - 1) {
					sb.append(nl);
				}
			}
		}

		return sb.toString();
	}

	/**
	 * Sets a size of square. Size must be greater then 0.
	 *
	 * @param squareSize Size of square.
	 */
	public void setSquareSize(int squareSize) {
		this.squareSize = squareSize == 0 ? 1 : squareSize;
	}
}
