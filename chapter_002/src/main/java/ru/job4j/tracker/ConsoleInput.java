package ru.job4j.tracker;

import java.util.List;
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

	/**
	 * Ask a question to user.
	 *
	 * @param question Question to user.
	 * @param range Available range of action numbers user can type to answer.
	 *
	 * @return Answer from user.
	 */
	@Override
	public int ask(String question, List<Integer> range) {
		System.out.print(question);
		Integer answer = Integer.valueOf(scanner.nextLine());
		boolean actionNumExists = range.contains(answer);

		if (!actionNumExists) {
			throw new MenuItemNumberOutOfRangeException("Введённый номер действия не соответствует ни одному номеру меню.");
		}

		return answer;
	}
}
