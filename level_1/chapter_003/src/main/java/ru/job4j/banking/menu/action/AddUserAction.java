package ru.job4j.banking.menu.action;

import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.User;
import ru.job4j.banking.input.Input;

/**
 * Adds a user to bank system.
 */
public final class AddUserAction extends UserAction {
	/**
	 * Constructor defines the description of add user action.
	 *
	 * @param input Type of input.
	 * @param bank Provider of methods for actions.
	 */
	public AddUserAction(Input input, Bank bank) {
		super("Добавить нового пользователя.", input, bank);
	}

	/**
	 * Executes method to add a user.
	 */
	public void execute() {
		String userName = this.getInput().ask("Введите имя нового пользователя: ");
		String userPassport = this.getInput().ask("Введите номер паспорта для "
				+ userName + ": ");
		User user = new User(userName, userPassport);
		boolean isUserAdded = this.getBank().addUser(user);

		if (isUserAdded) {
			System.out.println("Пользователь успешно зарегистрирован в системе.");
		} else {
			System.out.println("Такой пользователь уже зарегистрирован в системе!");
		}
	}
}
