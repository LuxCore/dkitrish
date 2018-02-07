package ru.job4j.professions;

/**
 * Full name of person.
 */
public class Name {
	/**
	 * First name of person.
	 */
	private String firstName;

	/**
	 * Middle name of person.
	 */
	private String middleName;

	/**
	 * Last name of person.
	 */
	private String lastName;

	/**
	 * Constructor of name.
	 *
	 * @param firstName First name of person.
	 */
	public Name(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Constructor of name.
	 *
	 * @param firstName First name of person.
	 * @param middleName Middle name of person.
	 */
	public Name(String firstName, String middleName) {
		this.firstName = firstName;
		this.middleName = middleName;
	}

	/**
	 * Constructor of name.
	 *
	 * @param firstName First name of person.
	 * @param middleName Middle name of person.
	 * @param lastName Last name of person.
	 */
	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	/**
	 * Sets a first name.
	 *
	 * @param firstName First name of person.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets a first name.
	 *
	 * @return String
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets a middle name.
	 *
	 * @param middleName Middle name of person.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets a middle name.
	 *
	 * @return String
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Sets a last name.
	 *
	 * @param lastName Last name of person.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets a last name.
	 *
	 * @return String
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Converts full name to string.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return this.firstName;
	}
}
