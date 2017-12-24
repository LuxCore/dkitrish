package ru.job4j.sort;

/**
 * User.
 *
 * @author denis.kitrish
 */
public class User implements Comparable<User> {
	/**
	 * Name of user.
	 */
	private String name;

	/**
	 * Name of user.
	 */
	private int age;

	/**
	 * Constructs user with his name and age.
	 *
	 * @param name Name of user.
	 * @param age Name of user.
	 */
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * Gets name of user.
	 *
	 * @return Name of user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of user.
	 *
	 * @param name Name of user.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets age of user.
	 *
	 * @return Age of user.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets age of user.
	 *
	 * @param age Age of user.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Compairs current user with given one.
	 *
	 * @param o User for comparison.
	 *
	 * @return Integer result of comparison: 0, greater then 0 or less then 0.
	 */
	@Override
	public int compareTo(User o) {
		int res = 0;
		if (!this.name.equals(o.name)) {
			res = this.name.compareTo(o.name);
		} else {
			res = this.age == o.age ? 0 : this.age > o.age ? 1 : -1;
		}
		return res;
	}

	/**
	 * Prime information about user.
	 *
	 * @return Prime information about user.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append(this.name).append("; age=").append(this.age).append("}");
		return sb.toString();
	}
}
