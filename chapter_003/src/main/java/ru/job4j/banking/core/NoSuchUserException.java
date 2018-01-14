package ru.job4j.banking.core;

/**
 * This exception tells to app user of absence of client in bank system.
 * <p>It is happened when searching of client performs by nonexistent passport.
 */
public class NoSuchUserException extends RuntimeException {
	/**
	 * Constructor of exception based on message.
	 *
	 * @param message Readable message to user about absence of client.
	 */
	public NoSuchUserException(String message) {
		super(message);
	}
}
