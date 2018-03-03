package ru.job4j.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование слона.
 *
 * @author denis.kitrish
 */
public class BishopTest {
	/**
	 * Правило для тестирования исключений.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Начальный ход, когда слон делает ход с начальной позиции всей игры. Проверка
	 * хода вправо и влево по диагонали.
	 */
	@Test
	public void testWayInitialMove() {
		Square f1 = new Square(6, 1);
		Square c4 = new Square(3, 4);
		Bishop bishop = new Bishop(f1);
		Square[] result = bishop.way(f1, c4);
		Square[] expected = {
				new Square(5, 2), new Square(4, 3),
				new Square(3, 4)
		};
		assertThat(result, is(expected));

		Square h3 = new Square(8, 3);
		bishop = new Bishop(f1);
		result = bishop.way(f1, h3);
		expected = new Square[]{new Square(7, 2), new Square(8, 3)};
		assertThat(result, is(expected));
	}

	/**
	 * Обычный ход слоном в середине игры. Проверка хода по диагонали во все
	 * стороны.
	 */
	@Test
	public void testWayRegularMove() {
		Square e4 = new Square(5, 4);
		Square h7 = new Square(8, 7);
		Bishop bishop = new Bishop(e4);
		Square[] result = bishop.way(e4, h7);
		Square[] expected = {new Square(6, 5), new Square(7, 6), new Square(8, 7)};
		assertThat(result, is(expected));

		Square d3 = new Square(4, 3);
		bishop = new Bishop(e4);
		result = bishop.way(e4, d3);
		expected = new Square[]{new Square(4, 3)};
		assertThat(result, is(expected));

		// !!! Проверить дома покрытость этих двух условий !!!
		Square g2 = new Square(7, 2);
		bishop = new Bishop(e4);
		result = bishop.way(e4, g2);
		expected = new Square[]{new Square(6, 3), new Square(7, 2)};
		assertThat(result, is(expected));

		Square a8 = new Square(1, 8);
		bishop = new Bishop(e4);
		result = bishop.way(e4, a8);
		expected = new Square[]{new Square(4, 5), new Square(3, 6), new Square(2, 7), new Square(1, 8)};
		assertThat(result, is(expected));
	}

	/**
	 * Тест копирования слона.
	 */
	@Test
	public void testCopy() {
		Square c5 = new Square(3, 5);
		Square e7 = new Square(5, 7);
		Bishop bishop = new Bishop(c5);
		bishop.copy(e7);
	}

	/**
	 * Проверка исключения, когда начальная и конечная клетки хода совпадают.
	 */
	@Test
	public void testWayWhenSourceEqualsDest() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Начальная и конечная клетки не должны совпадать."));
		Square c51 = new Square(3, 5);
		Square c52 = new Square(3, 5);
		Bishop bishop = new Bishop(c51);
		bishop.way(c51, c52);
	}

	/**
	 * Проверка исключения, когда ход слоном не соответствует правилам. Ход по
	 * диагонали вправо вверх.
	 */
	@Test
	public void testWayWhenMoveUpperRightIsWrong() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Ход невозможен, т.к. он не соответствует правилам."));
		Square e4 = new Square(5, 4);
		Square g7 = new Square(7, 7);
		Bishop bishop = new Bishop(e4);
		bishop.way(e4, g7);
	}

	/**
	 * Проверка исключения, когда ход слоном не соответствует правилам. Ход по
	 * диагонали вправо вниз.
	 */
	@Test
	public void testWayWhenMoveDownRightIsWrong() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Ход невозможен, т.к. он не соответствует правилам."));
		Square e4 = new Square(5, 4);
		Square h2 = new Square(8, 2);
		Bishop bishop = new Bishop(e4);
		bishop.way(e4, h2);
	}

	/**
	 * Проверка исключения, когда ход слоном не соответствует правилам. Ход по
	 * диагонали влево вниз.
	 */
	@Test
	public void testWayWhenMoveDownLeftIsWrong() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Ход невозможен, т.к. он не соответствует правилам."));
		Square e4 = new Square(5, 4);
		Square a1 = new Square(1, 1);
		Bishop bishop = new Bishop(e4);
		bishop.way(e4, a1);
	}

	/**
	 * Проверка исключения, когда ход слоном не соответствует правилам. Ход по
	 * диагонали влево вверх.
	 */
	@Test
	public void testWayWhenMoveUpperLeftIsWrong() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Ход невозможен, т.к. он не соответствует правилам."));
		Square e4 = new Square(5, 4);
		Square b8 = new Square(2, 8);
		Bishop bishop = new Bishop(e4);
		bishop.way(e4, b8);
	}

	/**
	 * Проверка исключения, когда конечная клетка выходит за верхнюю границу. Ход по
	 * диагонали влево вверх.
	 */
	@Test
	public void testWayWrongMoveUpperLeftWhenDestOutOfUpperBound() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Конечная позиция находится за пределами доски."));
		Square f5 = new Square(6, 5);
		Square b9 = new Square(2, 9);
		Bishop bishop = new Bishop(f5);
		bishop.way(f5, b9);
	}

	/**
	 * Проверка исключения, когда конечная клетка выходит за верхнюю границу. Ход по
	 * диагонали влево вверх.
	 */
	@Test
	public void testWayWrongMoveUpperRightWhenDestOutOfRightBound() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Конечная позиция находится за пределами доски."));
		Square f5 = new Square(6, 5);
		Square i8 = new Square(9, 8);
		Bishop bishop = new Bishop(f5);
		bishop.way(f5, i8);
	}

	/**
	 * Проверка исключения, когда конечная клетка выходит за верхнюю границу. Ход по
	 * диагонали влево вверх. n - negative - отрицательное значение.
	 */
	@Test
	public void testWayWrongMoveDownRightWhenDestOutOfDownBound() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Конечная позиция находится за пределами доски."));
		Square e2 = new Square(5, 2);
		Square gn1 = new Square(7, -1);
		Bishop bishop = new Bishop(e2);
		bishop.way(e2, gn1);
	}

	/**
	 * Проверка исключения, когда конечная клетка выходит за верхнюю границу. Ход по
	 * диагонали влево вверх. n - negative - отрицательное значение.
	 */
	@Test
	public void testWayWrongMoveDownLeftWhenDestOutOfLeftBound() {
		thrown.expect(ImpossibleMoveException.class);
		thrown.expectMessage(is("Конечная позиция находится за пределами доски."));
		Square b5 = new Square(2, 5);
		Square na3 = new Square(-1, 3);
		Bishop bishop = new Bishop(b5);
		bishop.way(b5, na3);
	}

	/**
	 * Тест получения информации о позиции фигуры на доске.
	 */
	@Test
	public void testGetPosition() {
		Square b5 = new Square(2, 5);
		Bishop bishop = new Bishop(b5);
		Square result = bishop.getPosition();
		Square expected = new Square(2, 5);
		assertThat(result, is(expected));
	}
}
