package ru.job4j.chess;

/**
 * Исключение о том, что путь занят другой фигурой.
 */
public class OccupiedWayException extends RuntimeException {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 9208350358296033910L;

	/**
	 * Конструктор исключения.
	 *
	 * @param message Человекочитаемое сообщение об ошибке.
	 */
	public OccupiedWayException(String message) {
		super(message);
	}
}
