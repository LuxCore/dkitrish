package ru.job4j.banking.menu.action;

import ru.job4j.banking.core.Bank;
import ru.job4j.banking.input.Input;

/**
 * Quits the application.
 * <p>This class does not do anything. It is only needed like a stub for exit
 * application.
 */
public final class QuitAppAction extends UserAction {
	/**
	 * Constructor defines the description of quit application action.
	 *
	 * @param input Type of input.
	 * @param bank Provider of methods for actions.
	 */
	public QuitAppAction(Input input, Bank bank) {
		super("Выйти из приложения.", input, bank);
	}

	/**
	 * Empty implementation of exection of action.
	 */
	public void execute() {
	}
}
