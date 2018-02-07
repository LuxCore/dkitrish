package ru.job4j.banking.input;

import java.util.Scanner;

import ru.job4j.banking.menu.MenuNumberOutOfRangeException;

/**
 * These calss provide interaction with client via console.
 */
public class ConsoleInput implements Input {
	/**
	 * Object for reading typed text by user.
	 */
	private Scanner scanner;

	/**
	 * Constructor fo console input.
	 */
	public ConsoleInput() {
		this.scanner = new Scanner(System.in);
	}

	/**
	 * Asks client different questions.
	 *
	 * @param question Message to user.
	 *
	 * @return Answer from client.
	 */
	@Override
	public String ask(String question) {
		System.out.print(question);
		return this.scanner.nextLine();
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
		System.out.print(question);
		int answer = Integer.valueOf(this.scanner.nextLine());

		if (answer < 1 && answer > checkValue) {
			throw new MenuNumberOutOfRangeException("Введённый номер пункта меню находится за пределами.");
		}

		return answer;
	}
}
