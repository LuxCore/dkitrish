package ru.job4j.loop;

/**
 *	Painting pyramid using "^" symbol.
 *
 *	@author Denis.Kitrish
 *	@since 01.10.2017
 *	@version 1.0
 */
public class Paint {

	/**
	 * Generates pyramid.
	 *
	 * @param height Height of pyramid.
	 *
	 * @return String Painted pyramid.
	 */
	public String pyramid(int height) {
		// Formula for claculating width depending on height.
		int width = height * 2 - 1;

		// Number of spaces from one side depending on row and height.
		int spaceCount = 0;

		// Number of lids.
		//int lidCount = 1;

		StringBuilder sb = new StringBuilder();
		final String nl = System.getProperty("line.separator");

		for (int row = 1; row <= height; row++) {
			spaceCount = height - row;

			for (int col = 1; col <= width; col++) {
				if (col <= spaceCount || col > width - spaceCount) {
					sb.append(" ");
				} else {
					sb.append("^");
				}
			}

			if (row < height) {
				sb.append(nl);
			}
		}

		return sb.toString();
	}
}