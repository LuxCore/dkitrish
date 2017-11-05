package ru.job4j.professions;

import java.time.Period;

public class Profession {
	/** Имя человека. */
	private Name name;

	/** Образование. */
	private Education education;

	/** Опыт/стаж. */
	private Period experience;

	/** Конструируем профессию. */
	public Profession(Name name, Education education, Period experience) {
		this.name = name;
		this.education = education;
		this.experience = experience;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Name getName() {
		return this.name;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Education getEducation() {
		return this.education;
	}

	public void setExperience(Period experience) {
		this.experience = experience;
	}

	public Period getExperience() {
		return this.experience;
	}
}
