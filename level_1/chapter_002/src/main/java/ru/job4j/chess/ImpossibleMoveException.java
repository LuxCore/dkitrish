package ru.job4j.chess;

/**
 * Исключение о невозможности сделать ход фигурой.
 */
public class ImpossibleMoveException extends RuntimeException {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1009136323642455551L;

	/**
	 *
	 * @param message Человекочитаемое сообщение об ошибке.
	 */
	public ImpossibleMoveException(String message) {
		super(message);
	}
}
