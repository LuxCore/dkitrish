package ru.job4j.professions;

/** Тема предмета. */
public class Topic {
	/** Название темы. */
	private String name;

	public Topic(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.name;
	}
}
