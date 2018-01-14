package ru.job4j.banking.menu.action;

import ru.job4j.banking.core.Bank;
import ru.job4j.banking.input.Input;

/**
 * Abstract action that contains a description of action.
 */
public abstract class UserAction implements BaseAction {
	/**
	 * Description of action.
	 */
	private String description;

	/**
	 * Type of input which action accpets answers from user.
	 */
	private Input input;

	/**
	 * Provider of base methods for actions.
	 */
	private Bank bank;

	/**
	 * Constructor defines the description of user action.
	 *
	 * @param description Description of action.
	 * @param input Type of input.
	 * @param bank Provider of methods for actions.
	 */
	public UserAction(String description, Input input, Bank bank) {
		this.description = description;
		this.input = input;
		this.bank = bank;
	}

	/**
	 * This method executes appropriate method of {@link Bank} class.
	 */
	public abstract void execute();

	/**
	 * Gets description of action.
	 *
	 * @return Description of action.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets input of application.
	 *
	 * @return Input of application.
	 */
	public Input getInput() {
		return this.input;
	}

	/**
	 * Gets provider of base methods for actions.
	 *
	 * @return Provider of methods.
	 */
	public Bank getBank() {
		return this.bank;
	}
}
