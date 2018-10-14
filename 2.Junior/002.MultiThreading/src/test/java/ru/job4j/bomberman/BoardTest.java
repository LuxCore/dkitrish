package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование игрового поля.
 */
public class BoardTest {
	/**
	 * Тестирование блокировки клетки на поле и хода.
	 *
	 * @throws InterruptedException Выбрасывается при прерывании потока.
	 */
	@Test
	public void test() throws InterruptedException {
		Board board = new Board(15, 9);
		assertTrue(board.lockCell(new Cell(0, 0)));
		assertTrue(board.move(new Cell(0, 0), new Cell(1, 0)));
		assertTrue(board.move(new Cell(1, 0), new Cell(1, 1)));
		assertTrue(board.move(new Cell(1, 1), new Cell(2, 1)));
		assertTrue(board.move(new Cell(2, 1), new Cell(1, 1)));
		assertTrue(board.move(new Cell(1, 1), new Cell(2, 1)));
	}
}