package ru.job4j.banking.input;

/**
 * Types of input.
 */
public enum InputType {
	/**
	 * Points on creation of valid input.
	 * <p>It uses handling of exceptions without interrupting of normal flow
	 * of application.
	 */
	VALID,

	/**
	 * Points on creation of input wothout catching exceptions.
	 */
	UNCHECKED

	/*==========================================================================
		This enum can be extanded.
	==========================================================================*/
}
