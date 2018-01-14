package ru.job4j.banking.menu;

/**
 * There are list of user kinds who can use the system. Different type users
 * see different menu.
 */
public enum MenuUser {
	/**
	 * Type of menu for employees of bank.
	 */
	EMPLOYEE,

	/*==========================================================================
		Other types are for example to show that menu of application can be
		extandale.
	==========================================================================*/

	/**
	 * Type of menu for clients of bank.
	 */
	CLIENT,

	/**
	 * Type of menu for guests.
	 */
	GUEST
}
