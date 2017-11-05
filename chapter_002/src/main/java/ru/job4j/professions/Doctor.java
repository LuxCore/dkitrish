package ru.job4j.professions;

import java.time.Period;

/** Профессия доктора. */
public class Doctor extends Profession {

	public Doctor(Name name, Education education, Period experience) {
		super(name, education, experience);
	}

	/** Осмотреть пациента и поставить диагноз */
	public String inspect(Patient patient) {
		return "Доктор " + this.getName() + " осматривает пациента " + patient;
	}

	/** Ставить диагноз пациенту */
	public Diagnosis makeDiagnosis(Patient patient) {
		Diagnosis diagnosis =
				new Diagnosis("Доктор " + this.getName() + " ставит диагноз "
				+ "ЗАМЕЧАТЕЛЬНОЕ НАСТРОЕНИЕ ОТ ПРОХОЖДЕНИЯ КУРСОВ JOB4J"
				+ " пациенту " + patient);
		return  diagnosis;
	}

	/** Выписать лечение пациенту */
	public String writeTreatment(Patient patient) {
		return "Доктор " + this.getName() + " выписал лечение пациенту "
				+ patient;
	}
}
