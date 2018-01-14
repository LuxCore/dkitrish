package ru.job4j.banking.input;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.banking.menu.MenuNumberOutOfRangeException;

/**
 * Implementattion of valid input of menu number from user.
 */
public class ValidMenuNumberInput implements ValidInput {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(ValidMenuNumberInput.class);

	/**
	 * Input type.
	 */
	private Input input;

	/**
	 * Constructs new instance.
	 *
	 * @param input Input type.
	 */
	public ValidMenuNumberInput(Input input) {
		this.input = input;
	}

	/**
	 * Empty implementation.
	 *
	 * @param question Question to user.
	 *
	 * @return Answer from user.
	 */
	public String ask(String question) {
		return this.input.ask(question);
	}

	/**
	 * Method outputs a question to user and then obtains answer from him.
	 * <p>This method needed for validation of typed menu number from user.
	 *
	 * @param question Question to user.
	 * @param checkValue Check value means max number of menu.
	 *
	 * @return Answer from user.
	 */
	public int ask(String question, int checkValue) {
		int answer;

		try {
			answer = Integer.valueOf(this.input.ask(question));
		} catch (MenuNumberOutOfRangeException e) {
			LOG.error(e.getMessage());
			answer = Integer.valueOf(ask("Введите, пожалуйста, правильный номер меню: ", checkValue));
		} catch (NumberFormatException e) {
			LOG.error("Вместо числового номера меню Вы ввели буквенные символы.");
			answer = Integer.valueOf(ask("Введите, пожалуйста, правильный номер меню: ", checkValue));
		}

		return answer;
	}
}
