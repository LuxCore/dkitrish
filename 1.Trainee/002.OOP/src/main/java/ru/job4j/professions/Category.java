package ru.job4j.professions;

/**
 * Category of engineer.
 */
public class Category {
	/**
	 * Number of category.
	 */
	private int number;

	/**
	 * Constructor of category.
	 *
	 * @param number Number of category.
	 */
	public Category(int number) {
		this.number = number;
	}

	/**
	 * Sets a category.
	 *
	 * @param number Number of category.
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Gets a category.
	 *
	 * @return int Number of Category.
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Conversion of category to string.
	 */
	@Override
	public String toString() {
		return Integer.toString(this.number);
	}
}
