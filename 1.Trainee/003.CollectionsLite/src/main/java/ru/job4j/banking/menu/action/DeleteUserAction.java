package ru.job4j.banking.menu.action;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.NoSuchUserException;
import ru.job4j.banking.core.User;
import ru.job4j.banking.input.Input;

import java.util.Optional;

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
		Optional<User> user;
		try {
			user = this.getBank().getUser(userPassport);
			user.ifPresent(value -> this.getBank().deleteUser(value));
			LOG.info("Пользователь успешно удалён из системы.");
		} catch (NoSuchUserException e) {
			LOG.error(e.getMessage());
		}
	}
}
