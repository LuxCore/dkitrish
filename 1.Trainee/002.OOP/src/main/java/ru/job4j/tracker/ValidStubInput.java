package ru.job4j.tracker;

import java.util.List;

/**
 * Stub input handling wrong selection of number of menu item.
 */
public class ValidStubInput extends StubInput {
	/**
	 * Constructs virtual input with predefined answers.
	 *
	 * @param answers Predefined answers from virtual user.
	 */
	public ValidStubInput(String[] answers) {
		super(answers);
	}

	/**
	 * Stub input with handling wrong answers from user.
	 * Handling of exceptions performs recursively.
	 *
	 * @param question Question to user.
	 * @param range Available range of action numbers user can type to answer.
	 *
	 * @return Answer from user.
	 */
	@Override
	public int ask(String question, List<Integer> range) {
		int answer = -1;

		try {
			answer = super.ask(question, range);
		} catch (MenuItemNumberOutOfRangeException e) {
			answer = ask("Введите, пожалуйста, правильный номер пункта меню: ", range);
		} catch (NumberFormatException e) {
			answer = ask("Вы ввели символ(ы), отличный(е) от числа. Введите, пожалуйста, номер пункта меню: ", range);
		}

		return answer;
	}
}
