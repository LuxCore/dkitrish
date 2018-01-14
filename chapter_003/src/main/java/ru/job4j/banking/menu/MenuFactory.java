package ru.job4j.banking.menu;

import java.util.Map;
import java.util.TreeMap;

import ru.job4j.banking.core.Bank;
import ru.job4j.banking.input.Input;
import ru.job4j.banking.menu.action.AddUserAccountAction;
import ru.job4j.banking.menu.action.AddUserAction;
import ru.job4j.banking.menu.action.DeleteUserAccountAction;
import ru.job4j.banking.menu.action.DeleteUserAction;
import ru.job4j.banking.menu.action.QuitAppAction;
import ru.job4j.banking.menu.action.ShowUserAccountsAction;
import ru.job4j.banking.menu.action.TransferMoneyAction;
import ru.job4j.banking.menu.action.UserAction;

/**
 * Simple factory of menu.
 */
public class MenuFactory {
	/**
	 * Method creator of menu.
	 *
	 * @param bank Prime storage of data and manipulation methods.
	 * @param menuUser Type of user menu.
	 * @param input Implementation of input.
	 *
	 * @return Map of actions with appropriate numbers.
	 */
	public static MainMenu create(Bank bank, MenuUser menuUser, Input input) {
		MainMenu menu = MainMenu.getInstance();
		menu.setInput(input);
		Map<Integer, UserAction> userActions = new TreeMap<>();
		menu.setUserActions(userActions);
		int menuNumber = 1;

		if (menuUser == MenuUser.EMPLOYEE) {
			userActions.put(menuNumber++, new AddUserAction(input, bank));
			userActions.put(menuNumber++, new DeleteUserAction(input, bank));
			userActions.put(menuNumber++, new AddUserAccountAction(input, bank));
			userActions.put(menuNumber++, new DeleteUserAccountAction(input, bank));
			userActions.put(menuNumber++, new ShowUserAccountsAction(input, bank));
			userActions.put(menuNumber++, new TransferMoneyAction(input, bank));
			userActions.put(menuNumber++, new QuitAppAction(input, bank));
		}

		menu.setExitNum(--menuNumber);

		return menu;
	}
}
