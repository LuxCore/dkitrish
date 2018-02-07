package ru.job4j.banking.core;

/**
 * Bank account of client.
 */
public class Account {
	/**
	 * Amount of money in the account.
	 */
	private double value;

	/**
	 * Requisites of account. It can be only the one for the account.
	 */
	private final String requisites;

	/**
	 * Creates an account with zero amount of money and requisites.
	 *
	 * @param requisites Requisites of acoount.
	 */
	public Account(String requisites) {
		this.requisites = requisites;
	}

	/**
	 * Creates an account with initial amount of money and requisites.
	 *
	 * @param value Amount of money.
	 * @param requisites Requisites of acoount.
	 */
	public Account(String requisites, double value) {
		this.requisites = requisites;
		this.value = value;
	}

	/**
	 * Changes amount of money.
	 *
	 * @param value Amount
	 */
	public void setValue(double value) {
		if (value < 0.0) {
			throw new IllegalArgumentException("Сумма не должна буть отрицательной!");
		}
		this.value = value;
	}

	/**
	 * Gets amount of money.
	 *
	 * @return Amount of money in the account.
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * Gets requisites of client's account.
	 *
	 * @return Requisites of account.
	 */
	public String getRequisites() {
		return this.requisites;
	}

	/**
	 * Equals two accounts.
	 *
	 * @param o Other account.
	 *
	 * @return Returns true if both accounts are equal and false in other way.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Account)) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}

		Account other = (Account) o;

		if (!this.requisites.equals(other.requisites)) {
			return false;
		}

		return true;
	}

	/**
	 * Calculate the hash code for account.
	 */
	@Override
	public int hashCode() {
		final int prime = 69;
		int result = 1;
		result = prime * result + this.requisites.hashCode();
		return result;
	}

	/**
	 * Presents our account in string.
	 *
	 * @return Main info about account.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Счёт {реквизиты = ").append(requisites)
				.append("; сумма = ").append(value).append("}");
		return sb.toString();
	}
}
