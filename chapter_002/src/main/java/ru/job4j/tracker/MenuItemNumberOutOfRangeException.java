package ru.job4j.tracker;

/**
 * This exception is printed out when user type wrong number of menu item.
 */
public class MenuItemNumberOutOfRangeException extends RuntimeException {
	/**
	 * Special constructor based on parent standard constructor.
	 *
	 * @param message Readable message for user about wrong number he typed.
	 */
	public MenuItemNumberOutOfRangeException(String message) {
		super(message);
	}
}
