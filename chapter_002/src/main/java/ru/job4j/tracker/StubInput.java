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

	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**
	 * Stub implementaion of asking question to user and returning result from console.
	 */
	public String ask(String question) {
		return answers[position++];
	}
}
