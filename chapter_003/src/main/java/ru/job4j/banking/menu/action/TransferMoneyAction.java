package ru.job4j.banking.menu.action;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.banking.core.Bank;
import ru.job4j.banking.core.NoSuchUserException;
import ru.job4j.banking.core.NoSuchUserAccountException;
import ru.job4j.banking.input.Input;

/**
 * Manages the money tranfer.
 */
public final class TransferMoneyAction extends UserAction {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			(Logger) LoggerFactory.getLogger(TransferMoneyAction.class);

	/**
	 * Constructor defines the action description of money tranfer from one
	 * account to another one.
	 *
	 * @param input Type of input.
	 * @param bank Provider of methods for actions.
	 */
	public TransferMoneyAction(Input input, Bank bank) {
		super("Перевести деньги с одного счёта на другой.", input, bank);
	}

	/**
	 * Executes method to add a user.
	 */
	public void execute() {
		String srcPassport = this.getInput().ask("Введите номер паспорта отправителя: ");
		String srcRequisites = this.getInput().ask("Введите реквизиты счёта отправителя: ");
		String destPassport = this.getInput().ask("Введите номер паспорта получателя: ");
		String destRequisites = this.getInput().ask("Введите реквизиты счёта получателя: ");
		String strAmount = this.getInput().ask("Введите сумму для перевода: ");

		try {
			double amount = Double.valueOf(strAmount);
			boolean isSuccess = this.getBank().transferMoney(
					srcPassport, srcRequisites,
					destPassport, destRequisites,
					amount);

			if (isSuccess) {
				LOG.info("Указанная сумма успешно переведена.");
			} else {
				LOG.info("Указанная сумма не была переведена.");
			}
		} catch (NoSuchUserException | NoSuchUserAccountException
				| IllegalArgumentException e) {
			LOG.error(e.getMessage());
		}
	}
}
