package ru.job4j.professions;

/**
 * Diagnosis.
 */
public class Diagnosis {
	/**
	 * Record of diagnosis in personal card of patient.
	 */
	private String record;

	/**
	 * Constructor of diagnosis.
	 *
	 * @param record Record in card.
	 */
	public Diagnosis(String record) {
		this.record = record;
	}

	/**
	 * Setter of record in patient`s personal card.
	 *
	 * @param record Record in card.
	 */
	public void setRecord(String record) {
		this.record = record;
	}

	/**
	 * Getter of record in patient`s personal card.
	 *
	 * @return String
	 */
	public String getRecord() {
		return this.record;
	}

	/**
	 * Conversion of diagnosis to string.
	 *
	 * @return String
	 */
	public String toString() {
		return this.record;
	}
}
