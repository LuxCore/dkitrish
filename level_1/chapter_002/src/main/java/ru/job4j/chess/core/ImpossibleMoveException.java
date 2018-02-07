package ru.job4j.chess.core;

/**
 * Impossible move of figure.
 */
public class ImpossibleMoveException extends RuntimeException {
	/**
	 * Exception about figure can not go by the path.
	 *
	 * @param message Readble message to player.
	 */
	public ImpossibleMoveException(String message) {
		super(message);
	}
}
