package ru.job4j.professions;

/**
 * Patient.
 */
public class Patient {
	/**
	 * Name of patient.
	 */
	private Name name;

	/**
	 * Constructs a patient.
	 *
	 * @param name Name of patient.
	 */
	public Patient(Name name) {
		this.name = name;
	}

	/**
	 * Sets name of a patient.
	 *
	 * @param name Name of patient.
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * Gets a name of patient.
	 *
	 * @return Name
	 */
	public Name getName() {
		return this.name;
	}

	/**
	 * Converts to string.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return this.name.toString();
	}
}
