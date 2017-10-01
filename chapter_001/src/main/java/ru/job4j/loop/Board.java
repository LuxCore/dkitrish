package ru.job4j.loop;

/**
 *	Painting chess board.
 *
 *	@author Denis.Kitrish
 *	@since 01.10.2017
 *	@version 1.0
 */
public class Board {

	/**
	 *	Generates chess board.
	 *
	 *	@param width Width of chess board.
	 *	@param height Height of chess board.
	 *
	 *	@return String Chess board painting with "X" and " ".
	 */
	public String paint(int width, int height) {
		StringBuilder board = new StringBuilder();
		final String newLine = System.getProperty("line.separator");

		for (int row = 1; row <= height; row++) {
			for (int col = 1; col <= width; col++) {
				if ((row % 2 > 0 && col % 2 > 0) || (row % 2 == 0 && col % 2 == 0)) {
					board.append("X");
				} else {
					board.append("-");
				}

				/*if (row % 2 > 0) {
					if (col % 2 > 0) {
						board.append("X");
					} else {
						board.append("-");
					}
				} else {
					if (col % 2 > 0) {
						board.append("-");
					} else {
						board.append("X");
					}
				}*/
			}

			if (row != height) {
				board.append(newLine);
			}
		}

		return board.toString();
	}
}