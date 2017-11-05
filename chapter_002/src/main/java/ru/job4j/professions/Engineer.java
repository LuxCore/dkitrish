package ru.job4j.professions;

import java.time.Period;

public class Engineer extends Profession {
	/** Категория. */
	private Category category;

	public Engineer(Name name, Education education, Period experience,
			Category category) {
		super(name, education, experience);
		this.category = category;
	}

	/** Выполнять должностные инструкции. */
	public String increaseCategory(Category category) {
		return "Инженер " + this.getName() + " выполняет задание для повышения "
				+ "категории с " + this.category
				+ " на " + category;
	}

	/** Создание презентации. */
	public String doPresentation() {
		return "Инженер " + this.getName() + " готовит презентацию.";
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return this.category;
	}
}
