package ru.job4j.banking.core;

/**
 * This exception tells to app user of absence of client account in bank system.
 */
public class NoSuchUserAccountException extends RuntimeException {
	/**
	 * Constructor of exception based on message.
	 *
	 * @param message Readable message to user about absence of client account.
	 */
	public NoSuchUserAccountException(String message) {
		super(message);
	}
}
