package ru.job4j.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование пешки.
 *
 * @author Denis.Kitrish
 */
public class PawnTest {
	/**
	 * Правило для тестирования исключений.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Ход пешкой с начальной позиции.
	 */
	@Test
	public void testWayInitialMove() {
		Square b2 = new Square(2, 2);
		Square b4 = new Square(2, 4);
		Piece pawn = new Pawn(b2);
		Square[] result = pawn.way(b2, b4);
		Square[] expected = {new Square(2, 3), new Square(2, 4)};
		assertThat(result, is(expected));
	}

	/**
	 * Обычный ход пешкой в середине игры. Движение вперёд на одну клетку.
	 */
	@Test
	public void testWayRegularMove() {
		Square f4 = new Square(6, 4);
		Square f5 = new Square(6, 5);
		Piece pawn = new Pawn(f4);
		Square[] result = pawn.way(f4, f5);
		Square[] expected = {new Square(6, 5)};
		assertThat(result, is(expected));
	}

	/**
	 * Тест копирования пешки.
	 */
	@Test
	public void testCopy() {
		Square g8 = new Square(7, 8);
		Square g9 = new Square(7, 9);
		Piece pawn = new Pawn(g8);
		Piece result = pawn.copy(g9);
		Piece expected = new Pawn(new Square(7, 9));
		assertThat(result, is(expected));
	}

	/**
	 * Проверка исключительной ситуации, когда количество клеток хода с начала игры
	 * превышено.
	 */
	@Test
	public void testWrongWayWhenInitialMoveSquaresNumIsExceeded() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Ход невозможен: превышено количество клеток в ходе."));
		Square b2 = new Square(2, 2);
		Square b5 = new Square(2, 5);
		Piece pawn = new Pawn(b2);
		pawn.way(b2, b5);
	}

	/**
	 * Проверка исключительной ситуации, когда количество клеток при обычном ходе
	 * превышает 1 клетку.
	 */
	@Test
	public void testWrongRegularWayWhenMoveSquaresNumIsExceeded() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Ход невозможен: превышено количество клеток в ходе."));
		Square b3 = new Square(2, 3);
		Square b5 = new Square(2, 5);
		Piece pawn = new Pawn(b3);
		pawn.way(b3, b5);
	}

	/**
	 * Проверка исключительной ситуации, когда пешкой пытаются сделать ход в
	 * сторону.
	 */
	@Test
	public void testWrongWayWhenMoveAside() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Ход невозможен: пешка не может ходить в сторону."));
		Square b2 = new Square(2, 2);
		Square c2 = new Square(3, 2);
		Piece pawn = new Pawn(b2);
		pawn.way(b2, c2);
	}

	/**
	 * Проверка исключительной ситуации, когда пешкой пытаются сделать ход назад.
	 */
	@Test
	public void testWayWhenMoveBackward() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Ход назад невозможен."));
		Square d5 = new Square(4, 5);
		Square d4 = new Square(4, 4);
		Piece pawn = new Pawn(d5);
		pawn.way(d5, d4);
	}

	/**
	 * Проверка исключительной ситуации, когда конечная клетка выходит за верхнюю
	 * границу доски со стороны игрока.
	 */
	@Test
	public void testWayWhenDestSquareIsOutOfUpperBoundOfBoard() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Конечная позиция находится за пределами доски."));
		Square g8 = new Square(7, 8);
		Square g9 = new Square(7, 9);
		Piece pawn = new Pawn(g8);
		pawn.way(g8, g9);
	}

	/**
	 * Проверка исключительной ситуации, когда конечная клетка выходит за левую
	 * границу доски со стороны игрока.
	 * n - negative - отрицательное значение.
	 */
	@Test
	public void testWayWhenDestSquareIsOutOfLeftBoundOfBoard() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Конечная позиция находится за пределами доски."));
		Square a5 = new Square(1, 5);
		Square na5 = new Square(0, 5);
		Piece pawn1 = new Pawn(a5);
		pawn1.way(a5, na5);
	}

	/**
	 * Проверка исключительной ситуации, когда конечная клетка выходит за правую
	 * границу доски со стороны игрока.
	 */
	@Test
	public void testWayDestSquareIsOutOfRightBoundOfBoard() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Конечная позиция находится за пределами доски."));
		Square h4 = new Square(8, 4);
		Square i4 = new Square(9, 4);
		Piece pawn1 = new Pawn(h4);
		pawn1.way(h4, i4);
	}

	/**
	 * Тест получения информации о позиции фигуры на доске.
	 */
	@Test
	public void testGetPosition() {
		Square g8 = new Square(7, 8);
		Square g9 = new Square(7, 9);
		Piece pawn = new Pawn(g8);

		Square result = pawn.getPosition();
		Square expected = new Square(7, 8);
		assertThat(result, is(expected));

		pawn = pawn.copy(g9);
		result = pawn.getPosition();
		expected = new Square(7, 9);
		assertThat(result, is(expected));
	}
}
