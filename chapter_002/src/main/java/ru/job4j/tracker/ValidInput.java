package ru.job4j.tracker;

/**
 * This exception is printed out when user type wrong number of menu item.
 *
 * !!! Actually I think this class would be good to make an abstract
 * !!! and implement Input interface.
 */
public class ValidInput extends ConsoleInput {
	/**
	 * Console input with handling wrong answers from user.
	 * Handling of exceptions performs recursively.
	 *
	 * @param question Question to user.
	 * @param range Available range of action numbers user can type to answer.
	 *
	 * @return Answer from user.
	 */
	@Override
	public int ask(String question, int[] range) {
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
