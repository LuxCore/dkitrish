package ru.job4j.professions;

/** Учёная степень. */
public class Degree {
	/** Наименование учёной степени. */
	private String name;

	public Degree(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
