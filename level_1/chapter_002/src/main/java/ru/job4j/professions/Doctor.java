package ru.job4j.professions;

import java.time.Period;

/**
 * Doctor profession.
 */
public class Doctor extends Profession {
	/**
	 * Constructs doctor.
	 *
	 * @param name Name of doctor.
	 * @param education Education of doctor.
	 * @param experience Experience of doctor.
	 */
	public Doctor(Name name, Education education, Period experience) {
		super(name, education, experience);
	}

	/**
	 * Examine the patient and make a diagnosis.
	 *
	 * @param patient Patient inspected by doctor.
	 *
	 * @return String Process of inspecting of patient.
	 */
	public String inspect(Patient patient) {
		return "Доктор " + this.getName() + " осматривает пациента " + patient;
	}

	/**
	 * Doctor make a diagnosis.
	 *
	 * @param patient Patient inspected by doctor.
	 *
	 * @return Diagnosis
	 */
	public Diagnosis makeDiagnosis(Patient patient) {
		Diagnosis diagnosis =
				new Diagnosis("Доктор " + this.getName() + " ставит диагноз "
				+ "ЗАМЕЧАТЕЛЬНОЕ НАСТРОЕНИЕ ОТ ПРОХОЖДЕНИЯ КУРСОВ JOB4J"
				+ " пациенту " + patient);
		return  diagnosis;
	}

	/**
	 * Prescribe treatment to the patient.
	 *
	 * @param patient Patient inspected by doctor.
	 *
	 * @return String
	 */
	public String writeTreatment(Patient patient) {
		return "Доктор " + this.getName() + " выписал лечение пациенту "
				+ patient;
	}
}
