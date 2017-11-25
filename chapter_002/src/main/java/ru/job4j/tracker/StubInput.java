package ru.job4j.tracker;

/**
 * Stub input for test typed data from user.
 */
public class StubInput implements Input {
	/**
	 * Sequence of answers of virtual test user.
	 */
	private String[] answers;

	/**
	 * Number of answer in array of answers.
	 */
	private int position = 0;

	/**
	 * Constructs virtual input with predefined answers.
	 *
	 * @param answers Predefined answers from virtual user.
	 */
	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**
	 * Stub implementaion of asking question to user and returning result from console.
	 *
	 * @param question Simply non-usable parameter.
	 *
	 * @return Answer from virtual user.
	 */
	@Override
	public String ask(String question) {
		return answers[position++];
	}

	/**
	 * Stub implementaion of asking question to user and returning result from console.
	 *
	 * @param question Simply non-usable parameter.
	 * @param range Range of valid numbers of menu items.
	 *
	 * @return Answer from virtual user.
	 */
	@Override
	public int ask(String question, int[] range) {
		return Integer.valueOf(answers[position++]);
	}
}
