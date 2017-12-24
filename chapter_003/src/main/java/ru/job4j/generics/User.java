package ru.job4j.generics;

/**
 * User.
 */
public class User {
	/**
	 * ID of user.
	 */
	private Integer id;

	/**
	 * Name of user.
	 */
	private String name;

	/**
	 * City user living in.
	 */
	private String city;

	/**
	 * Constructs user with all parameters.
	 *
	 * @param id
	 *            ID of user.
	 * @param name
	 *            Name of user.
	 * @param city
	 *            City user living in.
	 */
	public User(Integer id, String name, String city) {
		this.id = id;
		this.name = name;
		this.city = city;
	}

	/**
	 * Gets ID of user.
	 *
	 * @return ID of user.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets ID of user.
	 *
	 * @param id ID of user.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name of user.
	 *
	 * @return Name of user.
	 */
	public String getName() {
		return name;
	}

	/**
	* Sets the name of user.
	*
	* @param name Name of user.
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the city the user live in.
	 *
	 * @return City the user live in.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city the user live in.
	 *
	 * @param city City the user live in.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * For present of user in string.
	 *
	 * @return Name of user.
	 */
	@Override
	public String toString() {
		return this.name;
	}
}
