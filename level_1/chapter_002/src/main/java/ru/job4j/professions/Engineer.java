package ru.job4j.professions;

import java.time.Period;

/**
 * Engineer.
 */
public class Engineer extends Profession {
	/**
	 * Category.
	 */
	private Category category;

	/**
	 * Constructor of engineer.
	 *
	 * @param name Name of engineer.
	 * @param education Education of
	 * @param experience Experience of engineer.
	 * @param category Category of engineer.
	 */
	public Engineer(Name name, Education education, Period experience,
			Category category) {
		super(name, education, experience);
		this.category = category;
	}

	/**
	 * Выполнять должностные инструкции.
	 *
	 * @param category Category of engineer.
	 *
	 * @return String
	 */
	public String increaseCategory(Category category) {
		return "Инженер " + this.getName() + " выполняет задание для повышения "
				+ "категории с " + this.category
				+ " на " + category;
	}

	/**
	 * Make a presentation.
	 *
	 * @return String
	 */
	public String doPresentation() {
		return "Инженер " + this.getName() + " готовит презентацию.";
	}

	/**
	 * Sets a category of engineer.
	 *
	 * @param category Category of engineer.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets a category of engineer.
	 *
	 * @return Category
	 */
	public Category getCategory() {
		return this.category;
	}
}
