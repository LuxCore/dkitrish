package ru.job4j.professions;

import java.time.Period;

/** Профессия учителя. */
public class Teacher extends Profession {
	/** Учёная степень или звание преподавателя. */
	Degree degree;

	/** Предмет, который преподаватель ведёт. */
	String subject;

	public Teacher(Name name, Education education, Period experience,
			Degree degree, String subject) {
		super(name, education, experience);
		this.degree = degree;
		this.subject = subject;
	}

	/** Принять экзамен */
	public String takeExam(Student student) {
		return student + " сдаёт экзамен по предмету " + subject;
	}

	/** Задать вопрос учащемуся */
	public String ask(Student student) {
		return "Учитель " + this.getName() + " задаёт вопрос студенту по имени " + student;
	}

	/** Объяснение темы/материала на уроке/паре */
	public String explainTopic(Topic topic) {
		return "Учитель " + this.getName() + " объясняет тему \"" + topic + "\"";
	}

	/** Проверка работы (контрольной, лабораторной и т.д.) */
	public String verifyWork(Student student) {
		return "Учитель " + this.getName() + " проверяет работу студента по имени " + student;
	}
}
