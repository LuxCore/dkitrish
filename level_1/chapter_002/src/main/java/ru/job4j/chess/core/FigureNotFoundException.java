package ru.job4j.chess.core;

/**
 * Figure not found on the cell.
 */
public class FigureNotFoundException extends RuntimeException {
	/**
	 * Figure not found on the call.
	 *
	 * @param message Readble message to player.
	 */
	public FigureNotFoundException(String message) {
		super(message);
	}
}
