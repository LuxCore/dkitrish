package ru.job4j.coffeemachine;

/**
 * Types of coffee and its price.
 */
public enum Coffee {
	/**
	 * Americano.
	 */
	AMERICANO(26),

	/**
	 * Espresso.
	 */
	ESPRESSO(35),

	/**
	 * Latte.
	 */
	LATTE(32),

	/**
	 * Irish whiskey.
	 */
	IRISH_WHISKУY(47);

	/**
	 * Price of a coffee.
	 */
	private int price;

	/**
	 * Constructs our coffee and gives it price.
	 *
	 * @param price Price of a coffee.
	 */
	Coffee(int price) {
		this.price = price;
	}

	/**
	 * Gets a price of a coffee.
	 *
	 * @return Price of a coffee.
	 */
	public int getPrice() {
		return this.price;
	}
}
