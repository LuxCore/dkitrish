package ru.job4j.banking.input;

/**
 * Defines methods to interact with user.
 */
public interface Input {
	/**
	 * Method outputs a message to user and then obtains answer from him.
	 *
	 * @param question Message to user.
	 *
	 * @return Answer from user.
	 */
	String ask(String question);

	/**
	 * Method outputs a message to user and then obtains answer from him.
	 * <p>This method needed for validation of typed menu number from user.
	 *
	 * @param question Message to user.
	 * @param checkValue Check value means max number of menu.
	 *
	 * @return Answer from user.
	 */
	int ask(String question, int checkValue);
}
