package ru.job4j.loop;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *	Tests painting chess board.
 *
 *	@author Denis.Kitrish
 *	@since 01.10.2017
 *	@version 1.0
 */
public class BoardTest {

	/**
	 *	Tests chess board 3 x 3.
	 */
	@Test
	public void testPaintChessBoard3X3() {
		Board board = new Board();
		String result = board.paint(3, 3);

		final String nl = System.getProperty("line.separator");
		String expect = String.format("X-X%s-X-%sX-X", nl, nl);

		assertThat(result, is(expect));
	}

	/**
	 *	Tests chess board 6 x 9.
	 */
	@Test
	public void testPaintChessBoard6X9() {
		Board board = new Board();
		String result = board.paint(6, 9);

		final String nl = System.getProperty("line.separator");
		String expect = String.format("X-X-X-%s-X-X-X%sX-X-X-%s-X-X-X%sX-X-X-%s-X-X-X%sX-X-X-%s-X-X-X%sX-X-X-", nl, nl, nl, nl, nl, nl, nl, nl);

		assertThat(result, is(expect));
	}
}