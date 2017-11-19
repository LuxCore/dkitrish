package ru.job4j.tracker;

/**
 * Interface of interaction with user.
 */
public interface Input {
	/**
	 * Asks a question to user.
	 *
	 * @param question Any question to user after wich user must type answer.
	 *
	 * @return String User answer.
	 */
	String ask(String question);
}
