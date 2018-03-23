package ru.job4j.professions;

/**
 * Student.
 */
public class Student {
	/**
	 * Name of student.
	 */
	private Name name;

	/**
	 * Constructs a student.
	 *
	 * @param name Name of student.
	 */
	public Student(Name name) {
		this.name = name;
	}

	/**
	 * Sets a student.
	 *
	 * @param name Name of student.
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * Gets a student.
	 *
	 * @return Name
	 */
	public Name getName() {
		return this.name;
	}

	/**
	 * Converts to string.
	 */
	@Override
	public String toString() {
		return this.name.toString();
	}
}
