package ru.job4j.banking.start;

import ru.job4j.banking.core.Bank;
import ru.job4j.banking.input.Input;
import ru.job4j.banking.input.InputFactory;
import ru.job4j.banking.input.InputType;
import ru.job4j.banking.menu.MainMenu;
import ru.job4j.banking.menu.MenuUser;
import ru.job4j.banking.menu.MenuFactory;

/**
 * Main launch class.
 */
public class StartUI {
	/**
	 * Main menu of application.
	 */
	private final MainMenu menu;

	/**
	 * Main constructor.
	 *
	 * @param menu Main menu of application. It is defferent for different
	 * types of users.
	 */
	public StartUI(MainMenu menu) {
		this.menu = menu;
		this.init();
	}

	/**
	 * Prime method of application performing.
	 */
	private void init() {
		int userChoice = 0;
		do {
			this.menu.show();
			userChoice = this.menu.readItemNumber();
			this.menu.performItemAction(userChoice);
		} while (this.menu.getExitNum() != userChoice);
	}

	/**
	 * Main entry point.
	 *
	 * @param args Console arguments.
	 */
	public static void main(String[] args) {
		Bank bank = new Bank();
		Input input = new InputFactory().createConsole(InputType.VALID);
		MainMenu menu = new MenuFactory().create(bank, MenuUser.EMPLOYEE, input);
		new StartUI(menu);
	}
}
