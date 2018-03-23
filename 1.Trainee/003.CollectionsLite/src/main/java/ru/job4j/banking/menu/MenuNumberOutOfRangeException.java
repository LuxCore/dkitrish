package ru.job4j.banking.menu;

/**
 * Exception that saying a user typed wrong number of menu.
 */
public class MenuNumberOutOfRangeException extends RuntimeException {
	/**
	 * Constructor.
	 *
	 * @param message Readable message about exception to user.
	 */
	public MenuNumberOutOfRangeException(String message) {
		super(message);
	}
}
