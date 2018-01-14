package ru.job4j.banking.core;

/**
 * Client of bank (simplified).
 */
public class User implements Comparable<User> {
	/**
	 * Name of client.
	 */
	private String name;

	/**
	 * Client's passport data.
	 */
	private String passport;

	/**
	 * Creates client with name and passport.
	 *
	 * @param name Name of client.
	 * @param passport Client's passport data.
	 */
	public User(String name, String passport) {
		this.name = name;
		this.passport = passport;
	}

	/**
	 * Sets client's name.
	 *
	 * @param name Client's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets client's name.
	 *
	 * @return Client's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets client's passport.
	 *
	 * @param passport Client's passport.
	 */
	public void setPassport(String passport) {
		this.passport = passport;
	}

	/**
	 * Gets client's passport.
	 *
	 * @return Client's passport.
	 */
	public String getPassport() {
		return this.passport;
	}

	/**
	 * Equals two users.
	 *
	 * @param o Other user.
	 *
	 * @return Returns true if both users are equal and false in other way.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof User)) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}

		User other = (User) o;

		if (!this.name.equals(other.name)) {
			return false;
		}
		if (!this.passport.equals(other.passport)) {
			return false;
		}

		return true;
	}

	/**
	 * Calculate the hash code for user.
	 */
	@Override
	public int hashCode() {
		final int prime = 69;
		int result = 1;
		result = prime * result + this.name.hashCode();
		result = prime * result + this.passport.hashCode();
		return result;
	}

	/**
	 * Equlas two clients.
	 *
	 * @return a value after comparison of argument user and this user.
	 */
	@Override
	public int compareTo(User anotherUser) {
		int result = this.name.compareTo(anotherUser.name);
		if (result == 0) {
			result = this.passport.compareTo(anotherUser.passport);
		}
		return result;
	}

	/**
	 * Presents our user in string.
	 *
	 * @return Main info about user.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User {name = ").append(name)
				.append("; passport = ").append(passport).append("}");
		return sb.toString();
	}
}
