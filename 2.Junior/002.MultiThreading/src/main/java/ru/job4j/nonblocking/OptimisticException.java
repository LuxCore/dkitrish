package ru.job4j.nonblocking;

/**
 * Оптимистическое исключение о том, что объект был изменён
 * другим потоком.
 */
public class OptimisticException extends RuntimeException {
	/**
	 * Конструктор, принимающий сообщение для пользователя.
	 *
	 * @param message Сообщение для пользователя.
	 */
	public OptimisticException(String message) {
		super(message);
	}
}
