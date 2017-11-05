package ru.job4j.professions;

/** Студент. */
public class Student {
	/** Имя студента. */
	private Name name;

	public Student(Name name) {
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
