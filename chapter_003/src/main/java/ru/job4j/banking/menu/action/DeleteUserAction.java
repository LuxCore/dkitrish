package ru.job4j.banking.menu.action;

import ch.qos.logback.classic.Logger;

import java.util.List;

import org.slf4j.LoggerFactory;

import ru.job4j.banking.core.Account;
import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.NoSuchUserException;
import ru.job4j.banking.core.User;
import ru.job4j.banking.input.Input;

/**
 * Deletes a user from bank system.
 */
public final class DeleteUserAction extends UserAction {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(DeleteUserAction.class);

	/**
	 * Constructor defines the description of deletion user action.
	 *
	 * @param input Type of input.
	 * @param bank Provider of methods for actions.
	 */
	public DeleteUserAction(Input input, Bank bank) {
		super("Удалить пользователя.", input, bank);
	}

	/**
	 * Executes method to delete a user.
	 */
	public void execute() {
		String userPassport = this.getInput().ask("Введите номер паспорта пользователя: ");
		User user = null;
		try {
			user = this.getBank().getUser(userPassport);
			List<Account> isUserAdded = this.getBank().deleteUser(user);
			LOG.info("Пользователь успешно удалён из системы.");
		} catch (NoSuchUserException e) {
			LOG.error(e.getMessage());
		}
	}
}
