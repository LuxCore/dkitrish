package ru.job4j.professions;

public class Category {
	/** Категория. */
	private int number;

	public Category(int number) {
		this.number = number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	public String toString() {
		return Integer.toString(this.number);
	}
}
