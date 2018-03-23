package ru.job4j.tracker;

import java.util.List;

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

	/**
	 * Asks a question to user.
	 *
	 * @param question Any question to user after wich user must type answer.
	 * @param range Available range of numbers of actions user can select/type.
	 *
	 * @return Converted user answer from string to int.
	 */
	int ask(String question, List<Integer> range);
}
