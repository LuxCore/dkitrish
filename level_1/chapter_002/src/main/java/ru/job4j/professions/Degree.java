package ru.job4j.professions;

/**
 * Scientific degree.
 */
public class Degree {
	/**
	* Name of degree.
	*/
	private String name;

	/**
	 * Constructor of degree.
	 *
	 * @param name Name of degree.
	 */
	public Degree(String name) {
		this.name = name;
	}

	/**
	 * Sets a name of degree.
	 *
	 * @param name Name of degree.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets a name of degree.
	 *
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
}
