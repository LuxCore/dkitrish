package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Console input implementation.
 */
public class ConsoleInput implements Input {
	/**
	 * Scanner of input from user.
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Ask a question to user.
	 *
	 * @param question Question to user.
	 *
	 * @return Answer from user.
	 */
	@Override
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
}
