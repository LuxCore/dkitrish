package ru.job4j.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Проверка шахмат.
 *
 * @author Denis.Kitrish
 */
public class ChessTest {
	/**
	 * Правило для тестирования исключений.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Обычный ход слоном.
	 * <ol>
	 * <li>Проверка внутренней переменной-счётчика фигур.
	 * <li>Проверка количества фигур подсчётом количества элементов в массиве.
	 * </ol>
	 */
	@Test
	public void testBishopMoveAndCountPiecesAfter() {
		Board board = new Board();
		Square c1 = new Square(3, 1);
		Piece bishop = new Bishop(c1);
		board.add(bishop);
		Square g5 = new Square(7, 5);
		boolean result = board.move(c1, g5);
		assertThat(result, is(true));
	}

	/**
	 * Проверка исключительной ситуации, когда слон не найден на клетке.
	 */
	@Test
	public void testBishopMoveWhenPieceNotFound() {
		Square b2 = new Square(2, 2);
		thrown.expect(PieceNotFoundException.class);
		thrown.expectMessage(is("Фигура на клетке [" + b2 + "] не найдена!"));
		Board board = new Board();
		Square c3 = new Square(3, 3);
		Square f6 = new Square(6, 6);
		Piece bishop = new Bishop(c3);
		board.add(bishop);
		board.move(b2, f6);
	}

	/**
	 * Проверка исключительной ситуации, когда на пути слона стоит, например, пешка.
	 */
	@Test
	public void testBishopMoveWhenOccupiedWay() {
		thrown.expect(OccupiedWayException.class);
		thrown.expectMessage(is("Нет возможности сделать ход, так как путь занят другой фигурой."));
		Board board = new Board();
		Square c3 = new Square(3, 3);
		Square f6 = new Square(6, 6);
		Piece bishop = new Bishop(c3);
		Square e5 = new Square(5, 5);
		Piece pawn = new Pawn(e5);
		board.add(bishop);
		board.add(pawn);
		board.move(c3, f6);
	}

	/**
	 * Тест исключительной ситуации, когда клетка назначения находится за границами
	 * доски.
	 */
	@Test
	public void testBishopImpossibleMoveWhenOutOfBoard() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Конечная позиция находится за пределами доски."));
		Board board = new Board();
		Square c3 = new Square(3, 3);
		Square i9 = new Square(9, 9);
		Piece bishop = new Bishop(c3);
		board.add(bishop);
		board.move(c3, i9);
	}

}
