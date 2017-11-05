package ru.job4j.professions;

/** Пациент. */
public class Patient {
	/** Имя пациента. */
	private Name name;

	public Patient(Name name) {
		this.name = name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Name getName() {
		return this.name;
	}

	public String toString() {
		return this.name.toString();
	}
}
