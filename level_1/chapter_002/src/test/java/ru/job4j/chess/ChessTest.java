package ru.job4j.chess;

import org.junit.Test;

import ru.job4j.chess.core.Board;
import ru.job4j.chess.core.Cell;
import ru.job4j.chess.core.Figure;
import ru.job4j.chess.figure.Bishop;

/**
 * Here we will test our game.
 */
public class ChessTest {
	/**
	 * Here we test how can move the bishop.
	 */
	@Test
	public void test() {
		Board board = new Board();
		Cell c1 = new Cell(3, 1);
		Figure bishop = new Bishop(c1);
		board.locate(bishop);

		Cell e3 = new Cell(5, 3);
		board.move(c1, e3);
	}
}
