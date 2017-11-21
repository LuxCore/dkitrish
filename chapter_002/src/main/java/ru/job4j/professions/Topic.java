package ru.job4j.professions;

/**
 * Тема предмета.
 */
public class Topic {
	/**
	 * Название темы.
	 */
	private String name;

	/**
	 * Constructs a topic of theme.
	 *
	 * @param name Name of topic.
	 */
	public Topic(String name) {
		this.name = name;
	}

	/**
	 * Sets a name of topic.
	 *
	 * @param name Name of a topic.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets name of a topic.
	 *
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Converts to string.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return this.name;
	}
}
