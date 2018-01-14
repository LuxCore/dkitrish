package ru.job4j.banking.menu.action;

/**
 * Action must be executed after user choice.
 */
public interface BaseAction {
	/**
	 * Executes an appropriate method of {@link Bank} class.
	 */
	void execute();
}
