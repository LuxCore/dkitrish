package ru.job4j.marketdepth;

/**
 * Тип цены заявки.
 */
public enum PriceType {
	/**
	 * Цена на продажу.
	 */
	ASK("продажа"),

	/**
	 * Цена на покупку.
	 */
	BID("покупка");

	/**
	 * Описание типа цены заявки.
	 */
	private String description;

	/**
	 * Конструктор с установкой типа цены заявки.
	 *
	 * @param description Тип цены заявки.
	 */
	PriceType(String description) {
		this.description = description;
	}

	/**
	 * Получение типа цены заявки.
	 *
	 * @return Тип цены заявки.
	 */
	public String getDescription() {
		return description;
	}
}
