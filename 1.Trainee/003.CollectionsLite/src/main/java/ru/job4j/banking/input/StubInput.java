package ru.job4j.banking.input;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.banking.menu.MenuNumberOutOfRangeException;

/**
 * Stub input for tests.
 */
public class StubInput implements Input {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(StubInput.class);

	/**
	 * List of stub answers.
	 */
	// private List<String> answers;
	private String[] answers;

	/**
	 * Number of answer in {@link StubInput#answers} for automatic sort out of
	 * answers.
	 */
	private int answerNum;

	/**
	 * Accepts stub answers.
	 *
	 * @param answers Stub answers.
	 */
	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**
	 * Method outputs a message to user and then obtains answer from him.
	 *
	 * @param question Message to user.
	 *
	 * @return Answer from user.
	 */
	public String ask(String question) {
		LOG.info(question + this.answers[this.answerNum]);
		return this.answers[this.answerNum++];
	}

	/**
	 * Method outputs a message to user and then obtains answer from him.
	 * <p>This method needed for validation of typed menu number from user.
	 *
	 * @param question Message to user.
	 * @param checkValue Check value means max number of menu.
	 *
	 * @return Answer from user.
	 */
	public int ask(String question, int checkValue) {
		LOG.info(question + this.answers[this.answerNum]);
		int answer = Integer.valueOf(this.answers[this.answerNum++]);

		if (answer < 1 && answer > checkValue) {
			throw new MenuNumberOutOfRangeException("Введённый номер пункта меню находится за пределами.");
		}

		return answer;
	}
}
