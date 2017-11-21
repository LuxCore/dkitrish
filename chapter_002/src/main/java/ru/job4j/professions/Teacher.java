package ru.job4j.professions;

import java.time.Period;

/**
 * Teacher profession.
 */
public class Teacher extends Profession {
	/**
	 * Учёная степень или звание преподавателя.
	 */
	private Degree degree;

	/**
	 * Предмет, который преподаватель ведёт.
	 */
	private String subject;

	/**
	 * Constructs a teacher.
	 *
	 * @param name Name of a teacher.
	 * @param education Education of a teacher.
	 * @param experience Experience of a teacher.
	 * @param degree Degree of a teacher.
	 * @param subject Subject that teacher teaches.
	 */
	public Teacher(Name name, Education education, Period experience,
			Degree degree, String subject) {
		super(name, education, experience);
		this.degree = degree;
		this.subject = subject;
	}

	/**
	 * Take an exam.
	 *
	 * @param student Student.
	 *
	 * @return String
	 */
	public String takeExam(Student student) {
		return student + " сдаёт экзамен по предмету " + subject;
	}

	/**
	 * Задать вопрос учащемуся.
	 *
	 * @param student Student.
	 *
	 * @return String
	 */
	public String ask(Student student) {
		return "Учитель " + this.getName() + " задаёт вопрос студенту по имени " + student;
	}

	/**
	 * Объяснение темы/материала на уроке/паре.
	 *
	 * @param topic Topic to explain to students.
	 *
	 * @return String
	 */
	public String explainTopic(Topic topic) {
		return "Учитель " + this.getName() + " объясняет тему \"" + topic + "\"";
	}

	/**
	 * Проверка работы (контрольной, лабораторной и т.д.).
	 *
	 * @param student Student.
	 *
	 * @return String
	 */
	public String verifyWork(Student student) {
		return "Учитель " + this.getName() + " проверяет работу студента по имени " + student;
	}
}
