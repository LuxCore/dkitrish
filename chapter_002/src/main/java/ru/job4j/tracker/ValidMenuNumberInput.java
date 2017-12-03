package ru.job4j.tracker;

/**
 * Checks if user inputs right number of menu item.
 */
public class ValidMenuNumberInput extends ValidInput {
	/**
	 * Kind of input.
	 */
	private Input input;

	/**
	 * Constructor.
	 *
	 * @param input Kind of input.
	 */
	public ValidMenuNumberInput(Input input) {
		this.input = input;
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
		return this.input.ask(question);
	}

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
			// answer = super.ask(question, range);
			answer = this.input.ask(question, range);
		} catch (MenuItemNumberOutOfRangeException e) {
			answer = ask("Введите, пожалуйста, правильный номер пункта меню: ", range);
		} catch (NumberFormatException e) {
			answer = ask("Вы ввели символ(ы), отличный(е) от числа. Введите, пожалуйста, номер пункта меню: ", range);
		}

		return answer;
	}
}
