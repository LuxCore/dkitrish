package ru.job4j.chess.core;

/**
 * Says that another figure on the way.
 */
public class OccupiedWayException extends RuntimeException {
	/**
	 * Exception about occupancy of the path by another figure.
	 *
	 * @param message Readble message to player.
	 */
	public OccupiedWayException(String message) {
		super(message);
	}
}
