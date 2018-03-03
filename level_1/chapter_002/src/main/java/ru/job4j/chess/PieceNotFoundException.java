package ru.job4j.chess;

/**
 * Исключение о том, что фигура, которой игрок хочет сделать ход, не найдена
 * на желаемой клетке доски.
 */
public class PieceNotFoundException extends RuntimeException {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -5127917660099731270L;

	/**
	 * Конструктор исключения.
	 * @param message Человекочитаемое сообщение об ошибке.
	 */
	public PieceNotFoundException(String message) {
		super(message);
	}
}
