package ru.job4j.banking.menu.action;

import ch.qos.logback.classic.Logger;

import java.util.List;

import org.slf4j.LoggerFactory;

import ru.job4j.banking.core.Account;
import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.NoSuchUserException;
import ru.job4j.banking.input.Input;

/**
 * Shows all accounts user has.
 */
public final class ShowUserAccountsAction extends UserAction {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(ShowUserAccountsAction.class);

	/**
	 * Constructor defines the description of showing all of user accounts action.
	 *
	 * @param input Type of input.
	 * @param bank Provider of methods for actions.
	 */
	public ShowUserAccountsAction(Input input, Bank bank) {
		super("Показать все счета пользователя.", input, bank);
	}

	/**
	 * Executes method to add a new user account.
	 */
	public void execute() {
		String userPassport = this.getInput().ask("Введите номер паспорта пользователя: ");

		try {
			List<Account> userAccounts = this.getBank()
					.getUserAccounts(userPassport);
			this.printAllUserAccounts(userAccounts);
		} catch (NoSuchUserException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * Outputs all found accounts of user in console.
	 *
	 * @param userAccounts All of user accounts.
	 */
	private void printAllUserAccounts(List<Account> userAccounts) {
		if (userAccounts != null && !userAccounts.isEmpty()) {
			for (Account account : userAccounts) {
				LOG.info(account.toString());
			}
		} else {
			LOG.info("У пользователя пока нет ни одного счёта.");
		}
	}
}
