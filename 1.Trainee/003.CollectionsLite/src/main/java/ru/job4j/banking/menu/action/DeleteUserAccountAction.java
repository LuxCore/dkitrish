package ru.job4j.banking.menu.action;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.banking.core.Account;
import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.NoSuchUserAccountException;
import ru.job4j.banking.core.NoSuchUserException;
import ru.job4j.banking.core.User;
import ru.job4j.banking.input.Input;

import java.util.Optional;

/**
 * Deletes a user account.
 */
public final class DeleteUserAccountAction extends UserAction {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(DeleteUserAccountAction.class);

	/**
	 * Constructor defines the description of deletion user account action.
	 *
	 * @param input Type of input.
	 * @param bank Provider of methods for actions.
	 */
	public DeleteUserAccountAction(Input input, Bank bank) {
		super("Удалить счёт пользователя.", input, bank);
	}

	/**
	 * Executes method to delete a user account.
	 */
	public void execute() {
		String userPassport = this.getInput()
				.ask("Введите номер паспорта пользователя: ");
		String userAccountRequisites = this.getInput()
				.ask("Введите реквизиты счёта, который необходимо удалить: ");
		Optional<User> user;
		Optional<Account> userAccount = Optional.empty();

		try {
			user = this.getBank().getUser(userPassport);
			if (user.isPresent()) {
				userAccount = this.getBank().getUserAccount(user.get(), userAccountRequisites);
			}
			if (userAccount.isPresent()) {
				this.getBank().deleteUserAccount(userPassport, userAccount.get());
				LOG.info("Счёт успешно удалён.");
			}
		} catch (NoSuchUserException | NoSuchUserAccountException e) {
			LOG.error(e.getMessage());
		}
	}
}
