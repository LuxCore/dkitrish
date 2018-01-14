package ru.job4j.banking.menu.action;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.banking.core.Account;
import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.NoSuchUserException;
import ru.job4j.banking.core.User;
import ru.job4j.banking.input.Input;

/**
 * Adds an account to user.
 */
public final class AddUserAccountAction extends UserAction {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(AddUserAccountAction.class);

	/**
	 * Constructor defines the description of add user account action.
	 *
	 * @param input Type of input.
	 * @param bank Provider of methods for actions.
	 */
	public AddUserAccountAction(Input input, Bank bank) {
		super("Создать новый счёт для пользователя.", input, bank);
	}

	/**
	 * Executes method to add a new user account.
	 */
	public void execute() {
		String userPassport = this.getInput().ask("Введите номер паспорта пользователя: ");
		String userReqisites = this.getInput().ask("Введите реквизиты счёта: ");
		String strAmount = this.getInput().ask("Введите начальную сумму для счёта: ");
		User user = null;
		Account userAccount = null;

		try {
			double amount = Double.valueOf(strAmount);
			userAccount = new Account(userReqisites, amount);
			boolean isAdded = this.getBank().addUserAccount(userPassport, userAccount);
			if (isAdded) {
				LOG.info("Счёт успешно добавлен.");
			} else {
				LOG.info("Счёт не был добавлен.");
			}
		} catch (NoSuchUserException | IllegalArgumentException e) {
			LOG.error(e.getMessage());
		}
	}
}
