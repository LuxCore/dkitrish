package ru.job4j.professions;

import java.time.Period;

/**
 * Profession.
 */
public class Profession {
	/**
	 * Name of person.
	 */
	private Name name;

	/**
	 * Education.
	 */
	private Education education;

	/**
	 * Experience.
	 */
	private Period experience;

	/**
	 * Constructs a profession.
	 *
	 * @param name Name of person.
	 * @param education Education of person.
	 * @param experience Experience of person.
	 */
	public Profession(Name name, Education education, Period experience) {
		this.name = name;
		this.education = education;
		this.experience = experience;
	}

	/**
	 * Sets name of person.
	 *
	 * @param name Name of person.
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * Gets name of person.
	 *
	 * @return Name
	 */
	public Name getName() {
		return this.name;
	}

	/**
	 * Sets education of person.
	 *
	 * @param education Education of person.
	 */
	public void setEducation(Education education) {
		this.education = education;
	}

	/**
	 * Gets education of person.
	 *
	 * @return Education
	 */
	public Education getEducation() {
		return this.education;
	}

	/**
	 * Sets period of experience of person.
	 *
	 * @param experience Period of experience of person.
	 */
	public void setExperience(Period experience) {
		this.experience = experience;
	}

	/**
	 * Gets period of experience of person.
	 *
	 * @return Period
	 */
	public Period getExperience() {
		return this.experience;
	}
}
