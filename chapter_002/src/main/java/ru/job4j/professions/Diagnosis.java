package ru.job4j.professions;

/** Диагноз. */
public class Diagnosis {
	/** Зафиксированный в карточке диагноз. */
	private String record;

	public Diagnosis(String record) {
		this.record = record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getRecord() {
		return this.record;
	}

	public String toString() {
		return this.record;
	}
}
