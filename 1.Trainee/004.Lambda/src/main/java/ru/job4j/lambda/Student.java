package ru.job4j.lambda;

import java.util.Objects;

/**
 * Ученик.
 */
public class Student {
	/**
	 * Фамилия.
	 */
	private String surname;
	/**
	 * Баллы.
	 */
	private int score;

	/**
	 * Конструктор ученика.
	 * @param surname Фамилия.
	 * @param score Баллы.
	 */
	public Student(String surname, int score) {
		this.surname = surname;
		this.score = score;
	}

	public String getSurname() {
		return surname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Student student = (Student) o;
		return score == student.score
				&& Objects.equals(surname, student.surname);
	}

	@Override
	public int hashCode() {
		return Objects.hash(surname, score);
	}

	@Override
	public String toString() {
		return "Student{"
				+ "surname='" + surname + '\''
				+ ", score=" + score
				+ '}';
	}
}
