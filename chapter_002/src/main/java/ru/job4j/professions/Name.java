package ru.job4j.professions;

/** Полное имя. */
public class Name {
	/** Имя */
	private String firstName;

	/** Фамилия */
	private String middleName;

	/** Отчество */
	private String lastName;

	/**
	 * Конструктор имени.
	 */
	public Name(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Конструктор имени.
	 */
	public Name(String firstName, String middleName) {
		this.firstName = firstName;
		this.middleName = middleName;
	}

	/**
	 * Конструктор полного имени.
	 */
	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	/** Установка имени */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String toString() {
		return this.firstName;
	}
}
