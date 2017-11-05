package ru.job4j.professions;

/**
 * Образование.
 */
public class Education {
	/** Специальность */
	private String specialty;

	/** Диплом */
	private String diploma;

	/** Обучение */
	public Education(String specialty, String diploma) {
		this.specialty = specialty;
		this.diploma = diploma;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getSpecialty() {
		return this.specialty;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getDiploma() {
		return this.diploma;
	}
}
