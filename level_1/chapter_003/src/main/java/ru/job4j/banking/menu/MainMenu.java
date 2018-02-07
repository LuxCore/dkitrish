package ru.job4j.banking.menu;

import java.util.Map;

import ru.job4j.banking.menu.action.UserAction;
import ru.job4j.banking.input.Input;
// import ru.job4j.banking.menu.action.AddUserAction;
// import ru.job4j.banking.menu.action.QuitAppAction;

/**
 * Main menu of the online-banking of the most honest bank.
 */
public class MainMenu {
	/**
	 * The one instance of menu for all application.
	 */
	private static MainMenu instance;
	/**
	 * Available actions to user.
	 */
	private Map<Integer, UserAction> userActions;

	/** Exit code from app. */
	private int exitNum;

	/**
	 * Type of input information from user.
	 */
	private Input input;

	/**
	 * Private constructor of main menu.
	 */
	private MainMenu() {
	}

	/**
	 * Gets the single instance of amin menu.
	 *
	 * @return An instance of main menu.
	 */
	public static MainMenu getInstance() {
		if (MainMenu.instance == null) {
			MainMenu.instance = new MainMenu();
		}

		return MainMenu.instance;
	}

	/**
	 * Shows menu of system to user.
	 */
	public void show() {
		StringBuilder menu = new StringBuilder();
		String nl = System.getProperty("line.separator");

		menu.append(nl).append("--- САМЫЙ ЧЕСТНЫЙ БАНК ---").append(nl)
			.append("Онлайн-банкинг").append(nl)
			.append(this.getMenu()).append(nl);

		System.out.print(menu);
	}

	/**
	 * Asks user to select number of menu.
	 *
	 * @return Typed by user number of menu.
	 */
	public int readItemNumber() {
		return this.input.ask("Выберите необходимый пункт меню: ", this.exitNum);
	}

	/**
	 * Executes an appropriate selected action.
	 *
	 * @param menuNumber Selected by user number of menu.
	 */
	public void performItemAction(int menuNumber) {
		this.userActions.get(menuNumber).execute();
	}

	/**
	 * Gets a line of menu that consists of menu number and menu description.
	 *
	 * @return Line of menu that consists of menu number and menu description.
	 */
	private String getMenu() {
		StringBuilder menu = new StringBuilder();

		for (Map.Entry<Integer, UserAction> entry : this.userActions.entrySet()) {
			menu.append("\t").append(entry.getKey()).append(". ")
					.append(entry.getValue().getDescription()).append("\n");
		}

		return menu.toString();
	}

	/**
	 * Assigns user actions.
	 *
	 * @param userActions Available actions to user.
	 */
	public void setUserActions(Map<Integer, UserAction> userActions) {
		this.userActions = userActions;
	}

	/**
	 * Gets available actions to user.
	 *
	 * @return Available actions to user.
	 */
	public Map<Integer, UserAction> setUserActions() {
		return this.userActions;
	}

	/**
	 * Sets exit num.
	 *
	 * @param exitNum Number of exit menu item.
	 */
	public void setExitNum(int exitNum) {
		if (this.exitNum == 0) {
			this.exitNum = exitNum;
		}
	}

	/**
	 * Gets exit number.
	 *
	 * @return Number of exit menu item.
	 */
	public int getExitNum() {
		return this.exitNum;
	}

	/**
	 * Sets input.
	 *
	 * @param input Prime input of application.
	 */
	public void setInput(Input input) {
		this.input = input;
	}

	/**
	 * Gets input.
	 *
	 * @return Prime input of application.
	 */
	public Input getInput() {
		return this.input;
	}
}
