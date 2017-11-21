package ru.job4j.professions;

/**
 * Education.
 */
public class Education {
	/**
	 * Specialty.
	 */
	private String specialty;

	/**
	 * Diploma.
	 */
	private String diploma;

	/**
	 * Constructor of education.
	 *
	 * @param specialty Specialty of person.
	 * @param diploma Diploma of gotten education.
	 */
	public Education(String specialty, String diploma) {
		this.specialty = specialty;
		this.diploma = diploma;
	}

	/**
	 * Sets a specialty.
	 *
	 * @param specialty Specialty of person.
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * Gets a specialty.
	 *
	 * @return String
	 */
	public String getSpecialty() {
		return this.specialty;
	}

	/**
	 * Sets a diploma.
	 *
	 * @param diploma Diploma of gotten education.
	 */
	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	/**
	 * Gets a diploma.
	 *
	 * @return String
	 */
	public String getDiploma() {
		return this.diploma;
	}
}
